package scheduler;

import command.Command;
import context.NotEnoughTurns;

import java.util.List;

/**
 * @author Alexandre Clement
 * @since 23/01/2017.
 */
@FunctionalInterface
public interface Planification
{
    List<Command> buildPlanification();
}
