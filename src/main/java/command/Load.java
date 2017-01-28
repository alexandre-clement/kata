package command;

import context.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Load extends Action
{
    private static final CommandEnum COMMAND_ENUM = CommandEnum.L;

    public Load(Drone drone, Container target, Item item, int number)
    {
        super(COMMAND_ENUM, drone, target, item, number);
    }

    @Override
    public void execute() throws DroneException
    {
        getDrone().load(getTarget(), getItem(), getNumber());
    }
}
