package command;

import context.Drone;
import context.NotEnoughTurns;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Wait implements Command
{
    private static final CommandEnum COMMAND_ENUM = CommandEnum.W;
    private Drone drone;
    private int time;

    public Wait(Drone drone, int number)
    {
        this.drone = drone;
        this.time = number;
    }

    @Override
    public String print()
    {
        return String.format("%d %s %d", drone.getId(), COMMAND_ENUM.toString(), time);
    }

    @Override
    public void execute() throws NotEnoughTurns
    {
        drone.waitTime(time);
    }

    @Override
    public String toString()
    {
        return String.format("Drone %d wait %d turns.", drone.getId(), time);
    }

    @Override
    public CommandEnum getCommandEnum()
    {
        return COMMAND_ENUM;
    }
}
