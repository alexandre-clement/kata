package scheduler;

import command.Command;
import context.Container;
import context.Context;
import context.Drone;
import context.NotEnoughTurns;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexandre Clement
 * @since 23/01/2017.
 */
public class Strategy extends Tools implements Planification
{
    public Strategy(Context context)
    {
        super(context);
    }

    @Override
    public List<Command> buildPlanification()
    {
        List<Command> planification = new ArrayList<>();
        List<Container> kernels = getSingletons(getWarehouses());
        List<Container> electrons = getSingletons(getOrders());
        List<Cluster> clusters = getCluster(kernels, electrons, 1);
        List<Cluster> regroup = getFinalCluster(clusters);
        Optional<Drone> drone = findADrone(getFleet());


        while (drone.isPresent())
        {
            Optional<Cluster> cluster = findACluster(regroup, drone.get());
            if (!cluster.isPresent())
                break;
            try
            {
                planification.addAll(commandCluster(cluster.get(), drone.get()));
            }
            catch (NotEnoughTurns notEnoughTurns)
            {
                regroup.remove(cluster.get());
            }
            drone = findADrone(getFleet());
        }

        planification.addAll(completeWithWait());
        return planification;
    }
}
