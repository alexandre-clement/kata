package command;

import context.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public abstract class Action implements Command
{
    private Drone drone;
    private Container target;
    private Item item;
    private int number;
    private CommandEnum commandEnum;

    Action(CommandEnum commandEnum, Drone drone, Container target, Item item, int number)
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

    public Drone getDrone()
    {
        return drone;
    }

    public Container getTarget()
    {
        return target;
    }

    Item getItem()
    {
        return item;
    }

    int getNumber()
    {
        return number;
    }

    @Override
    public String toString()
    {
        return String.format("Drone %d %s %d %s at %s", drone.getId(), this.getClass().getSimpleName(), number, item, target);
    }

    @Override
    public CommandEnum getCommandEnum()
    {
        return commandEnum;
    }
}
