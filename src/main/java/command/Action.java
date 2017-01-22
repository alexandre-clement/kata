package command;

import context.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Action<T extends Container> implements Command
{
    private Drone drone;
    private T target;
    private Item item;
    private int number;
    private CommandEnum commandEnum;

    public Action(CommandEnum commandEnum, Drone drone, T target, Item item, int number)
    {
        this.commandEnum = commandEnum;
        this.drone = drone;
        this.target = target;
        this.item = item;
        this.number = number;
    }

    @Override
    public String print()
    {
        return String.format("%d %s %d %d %d", drone.getId(), commandEnum.toString(), target.getId(), item.getId(), number);
    }

    @Override
    public void execute(Context context) throws NotEnoughTurns
    {
        drone.moveTo(target.getLocation());
    }

    public CommandEnum getCommandEnum()
    {
        return commandEnum;
    }

    public Drone getDrone()
    {
        return drone;
    }

    public T getTarget()
    {
        return target;
    }

    public Item getItem()
    {
        return item;
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public String toString()
    {
        return String.format("Drone %d %s %d %s at %s", drone.getId(), this.getClass().getSimpleName(), number, item, target);
    }
}
