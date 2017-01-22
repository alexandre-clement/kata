package command;

import context.*;

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

    @Override
    public void execute(Context context) throws NotEnoughTurns
    {
        super.execute(context);
        getDrone().remove(getItem(), getNumber());
        getTarget().add(getItem(), getNumber());
        getDrone().waitTime(1);
    }
}
