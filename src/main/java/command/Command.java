package command;

import context.Context;
import context.NotEnoughTurns;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public interface Command
{
    String print();

    void execute(Context context) throws NotEnoughTurns;
}