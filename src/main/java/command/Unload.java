package command;

import context.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Unload extends Action
{
    private static final CommandEnum COMMAND_ENUM = CommandEnum.U;

    public Unload(Drone drone, Container target, Item item, int number)
    {
        super(COMMAND_ENUM, drone, target, item, number);
    }

    @Override
    public void execute() throws NotEnoughTurns
    {
        getDrone().unload(getTarget(), getItem(), getNumber());
    }
}
