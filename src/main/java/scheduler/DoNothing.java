package scheduler;

import command.Command;
import context.Context;
import context.NotEnoughTurns;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class DoNothing extends Tools implements Planification
{
    public DoNothing(Context context)
    {
        super(context);
    }

    @Override
    public List<Command> buildPlanification()
    {
        return super.completeWithWait();
    }
}
