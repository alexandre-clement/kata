package scheduler;

import command.Command;
import command.Wait;
import context.Context;
import context.Drone;
import context.NotEnoughTurns;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 21/01/2017.
 */
public class Planification
{
    private Context context;

    public Planification(Context context)
    {
        this.context = context;
    }

    public List<Command> buildPlanification() throws NotEnoughTurns
    {
        return new ArrayList<>();
    }

    public List<Command> completeWithWait() throws NotEnoughTurns
    {
        List<Command> wait = new ArrayList<>();
        for (Drone drone : context.getFleet())
        {
            wait.add(new Wait(drone, drone.getTurns()));
            drone.waitTime(drone.getTurns());
        }
        return wait;
    }
}
