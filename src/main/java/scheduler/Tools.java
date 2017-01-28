package scheduler;

import command.Command;
import command.Wait;
import context.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 21/01/2017.
 */
class Tools
{
    private final List<Container> warehouses;
    private final List<Container> orders;
    private final List<Item> items;
    private final Fleet fleet;
    private Context context;

    Tools(Context context)
    {
        this.context = context;
        warehouses = context.getWarehouses();
        orders = context.getOrders();
        items = context.getItems();
        fleet = context.getFleet();
    }

    List<Command> completeWithWait()
    {
        List<Command> wait = new ArrayList<>();
        for (Drone drone : context.getFleet())
        {
            wait.add(new Wait(drone, drone.getTurns()));
            try
            {
                drone.waitTime(drone.getTurns());
            }
            catch (NotEnoughTurns notEnoughTurns)
            {
                System.err.println(notEnoughTurns); // Not supposed to happens
            }
        }
        return wait;
    }

    DoubleEntryMap<Container, Integer> getDistances(List<? extends Container> containers)
    {
        DoubleEntryMap<Container, Integer> distanceMap = new DoubleEntryMap<>();

        for (int i = 0; i < containers.size(); i++)
        {
            for (int j = i + 1; j < containers.size(); j++)
            {
                distanceMap.put(containers.get(i), containers.get(j), containers.get(i).distance(containers.get(j)));
            }
        }
        return distanceMap;
    }

    DoubleEntryMap<Container, Basket> getDependencies(List<Container> warehouses, List<Container> orders)
    {
        DoubleEntryMap<Container, Basket> dependencyMap = new DoubleEntryMap<>(false);

        for (Container order : orders)
        {
            for (Container warehouse : warehouses)
            {
                dependencyMap.put(order, warehouse, order.intersection(warehouse));
            }
        }
        return dependencyMap;
    }

    List<Container> getSingletons(List<? extends Container> containers)
    {
        List<Container> singletons = new ArrayList<>();

        for (Container container : containers)
        {
            singletons.addAll(container.getSingletons());
        }
        return singletons;
    }

    List<Cluster> getCluster(List<? extends Container> kernels, List<? extends Container> electrons, int clusterSize)
    {
        List<Cluster> clusters = new ArrayList<>();
        List<Cluster> temp = new ArrayList<>();

        for (Container kernel : kernels)
        {
            temp.add(new Cluster(kernel));
        }

        for (Container electron : electrons)
        {
            Optional<Cluster> optional = temp.stream().sorted((c1, c2) -> Integer.compare(c1.distance(electron), c2.distance(electron))).findFirst();
            if (optional.isPresent())
            {
                optional.get().add(electron);
                if (optional.get().size() == clusterSize)
                {
                    temp.remove(optional.get());
                    clusters.add(optional.get());
                }
            }
        }
        clusters.addAll(temp);
        return clusters;
    }

    List<Cluster> regroup(List<Cluster> clusters)
    {
        List<Cluster> temp = clusters.stream().map(Cluster::new).collect(Collectors.toList());
        List<Cluster> regroup = new ArrayList<>();
        int maxPayload = fleet.getPayload();

        while (!temp.isEmpty())
        {
            Cluster head = temp.get(0);
            Comparator<Cluster> cmp = (c1, c2) -> Integer.compare(c1.distance(head), c2.distance(head));
            temp.remove(0);
            Optional<Cluster> optional = temp.stream().filter(cluster -> cluster.getKernel().distance(head.getKernel()) == 0).sorted(cmp).findFirst();

            if (optional.isPresent() && optional.get().getPayload() + head.getPayload() < maxPayload)
            {
                head.union(optional.get());
                temp.remove(optional.get());
            }
            regroup.add(head);
        }
        return regroup;
    }

    List<Cluster> getFinalCluster(List<Cluster> clusters)
    {
        List<Cluster> finalClusters = regroup(clusters);
        List<Cluster> temp = regroup(finalClusters);

        while (!temp.equals(finalClusters))
        {
            finalClusters = temp;
            temp = regroup(finalClusters);
        }
        return finalClusters;
    }

    Optional<Drone> findADrone(Fleet fleet)
    {
        Comparator<Drone> cmp = (d1, d2) -> Integer.compare(d2.getTurns(), d1.getTurns());
        return fleet.stream().sorted(cmp).findFirst();
    }

    Optional<Cluster> findACluster(List<Cluster> clusters, Container container)
    {
        Comparator<Cluster> cmp = (c1, c2) -> Integer.compare(c1.distance(container), c2.distance(container));
        return clusters.stream().filter(c1 -> !c1.isEmpty()).sorted(cmp).findFirst();
    }

    List<Command> commandCluster(Cluster cluster, Drone drone) throws DroneException
    {
        List<Command> commands = new ArrayList<>();
        Container warehouse = cluster.getKernel();
        Item item;
        cluster.close();

        while (warehouse.iterator().hasNext())
        {
            item = warehouse.iterator().next();
            commands.add(drone.load(warehouse, item, warehouse.countItem(item)));
        }

        Optional<Container> order = closestOrder(cluster, drone);

        while (order.isPresent())
        {
            commands.add(drone.deliver(order.get(), order.get().get(0), order.get().size()));
            cluster.removeEmpty();
            order = closestOrder(cluster, drone);
        }
        return commands;
    }

    Optional<Container> closestOrder(Cluster cluster, Drone drone)
    {
        Comparator<Container> cmp = (c1, c2) -> Integer.compare(c1.distance(drone), c2.distance(drone));
        return cluster.stream().sorted(cmp).findFirst();
    }

    List<Container> getWarehouses()
    {
        return warehouses;
    }

    List<Container> getOrders()
    {
        return orders;
    }

    List<Item> getItems()
    {
        return items;
    }

    Fleet getFleet()
    {
        return fleet;
    }
}
