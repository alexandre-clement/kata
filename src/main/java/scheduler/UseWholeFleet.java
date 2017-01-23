package scheduler;

import command.Command;
import context.Context;
import context.NotEnoughTurns;

import java.util.List;

/**
 * @author Alexandre Clement
 * @since 21/01/2017.
 */
public class UseWholeFleet extends Planification
{
    public UseWholeFleet(Context context)
    {
        super(context);
    }

    @Override
    public List<Command> buildPlanification() throws NotEnoughTurns
    {
        return null;
    }
}
