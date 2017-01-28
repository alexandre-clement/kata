package score;

import command.Action;
import command.Command;
import command.CommandEnum;
import context.Context;
import context.DroneException;
import engine.ExecuteScheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 27/01/2017.
 */
public class SoonerIsBetter extends ExecuteScheduler implements KPI
{
    private int score;

    public SoonerIsBetter(Context context, List<Command> planification)
    {
        super(context, planification);
        score = 0;
    }

    @Override
    public int score() throws DroneException
    {
        super.execute();
        return score;
    }

    @Override
    protected void executeCommand(Command command) throws DroneException
    {
        super.executeCommand(command);
        if (command.getCommandEnum() == CommandEnum.W)
            return;
        Action action = (Action) command;
        if (action.getTarget().isEmpty())
            score += completedOrder(action.getDrone().getTurns()+1);
    }

    int completedOrder(int turns)
    {
        return (int) Math.ceil(turns * 100d / super.getContext().getFleet().getTurns());
    }

    private long getCount()
    {
        return getContext().getOrders().stream().filter(ArrayList::isEmpty).count();
    }
}
