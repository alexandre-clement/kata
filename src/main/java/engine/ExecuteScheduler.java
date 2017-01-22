package engine;

import command.Command;
import context.Context;
import context.Drone;
import context.NotEnoughTurns;

import java.util.List;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class ExecuteScheduler
{
    private Context context;
    private List<Command> planification;

    public ExecuteScheduler(Context context, List<Command> planification)
    {
        this.context = context;
        this.planification = planification;
    }

    public void execute() throws NotEnoughTurns
    {
        for (Command command : planification)
        {
            command.execute(context);
        }
    }

    public void printResult()
    {
        context.getFleet().forEach(System.out::println);
        context.getOrders().forEach(System.out::println);
    }

    public Context getContext()
    {
        return context;
    }
}
