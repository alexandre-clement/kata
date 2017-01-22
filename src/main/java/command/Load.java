package command;

import context.Drone;
import context.Item;
import context.Warehouse;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Load extends Action<Warehouse>
{
    private static final CommandEnum COMMAND_ENUM = CommandEnum.L;

    public Load(Drone drone, Warehouse target, Item item, int number)
    {
        super(COMMAND_ENUM, drone, target, item, number);
    }
}
