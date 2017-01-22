package command;

import context.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Unload extends Action<Warehouse>
{
    private static final CommandEnum COMMAND_ENUM = CommandEnum.U;

    public Unload(Drone drone, Warehouse target, Item item, int number)
    {
        super(COMMAND_ENUM, drone, target, item, number);
    }

    @Override
    public void execute(Context context) throws NotEnoughTurns
    {
        super.execute(context);
        getTarget().remove(getItem(), getNumber());
        getDrone().add(getItem(), getNumber());
        getDrone().waitTime(1);
    }
}