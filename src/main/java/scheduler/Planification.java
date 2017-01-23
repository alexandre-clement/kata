package scheduler;

import command.*;
import context.*;

import java.util.*;

/**
 * @author Alexandre Clement
 * @since 21/01/2017.
 */
public abstract class Planification
{
    private final List<Warehouse> warehouses;
    private final List<Order> orders;
    private final List<Item> items;
    private final Fleet fleet;
    private Context context;

    public Planification(Context context)
    {
        this.context = context;
        warehouses = context.getWarehouses();
        orders = context.getOrders();
        items = context.getItems();
        fleet = context.getFleet();
    }

    public abstract List<Command> buildPlanification() throws NotEnoughTurns;

    List<Command> completeWithWait() throws NotEnoughTurns
    {
        List<Command> wait = new ArrayList<>();
        for (Drone drone : context.getFleet())
        {
            wait.add(new Wait(drone, drone.getTurns()));
            drone.waitTime(drone.getTurns());
        }
        return wait;
    }

    DistanceMap getDistances()
    {
        DistanceMap distanceMap = new DistanceMap();
        ArrayList<Container> containers = new ArrayList<>(warehouses);
        containers.addAll(orders);

        for (int i = 0; i < containers.size(); i++)
        {
            for (int j = i+1; j < containers.size(); j++)
            {
                distanceMap.put(containers.get(i), containers.get(j), containers.get(i).distance(containers.get(j).getLocation()));
            }
        }
        return distanceMap;
    }

    class DistanceMap
    {
        private Map<Container, Map<Container, Integer>> distanceMap;

        DistanceMap()
        {
            distanceMap = new HashMap<>();
        }

        public void put(Container key1, Container key2, Integer distance)
        {
            if (!distanceMap.containsKey(key1))
                distanceMap.put(key1, new HashMap<>());
            if (!distanceMap.containsKey(key2))
                distanceMap.put(key2, new HashMap<>());
            distanceMap.get(key1).put(key2, distance);
            distanceMap.get(key2).put(key1, distance);
        }

        public Integer get(Container key1, Container key2)
        {
            if (distanceMap.containsKey(key1) && distanceMap.get(key1).containsKey(key2))
                return distanceMap.get(key1).get(key2);
            if (distanceMap.containsKey(key2) && distanceMap.get(key2).containsKey(key1))
                return distanceMap.get(key2).get(key1);
            return null;
        }
    }
}
