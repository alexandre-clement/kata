package command;

import context.Drone;
import context.Item;
import context.Order;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Deliver extends Action<Order>
{
    private static final CommandEnum COMMAND_ENUM = CommandEnum.D;

    public Deliver(Drone drone, Order target, Item item, int number)
    {
        super(COMMAND_ENUM, drone, target, item, number);
    }
}
