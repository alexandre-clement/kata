package engine;

import command.Command;
import context.Context;
import context.DroneOverload;
import context.NotEnoughTurns;

import java.io.PrintStream;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class ExecuteScheduler
{
    private PrintStream out = System.out;
    private Context context;
    private List<Command> planification;

    public ExecuteScheduler(Context context, List<Command> planification)
    {
        this.context = context;
        this.planification = planification;
    }

    public void execute() throws NotEnoughTurns, DroneOverload
    {
        for (Command command : planification)
        {
            command.execute();
        }
    }

    public void printResult()
    {
        context.getFleet().forEach(out::println);
        context.getOrders().forEach(out::println);
    }

    public Context getContext()
    {
        return context;
    }
}
