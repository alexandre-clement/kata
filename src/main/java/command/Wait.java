package command;

import context.Drone;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Wait implements Command
{
    private static final CommandEnum COMMAND_ENUM = CommandEnum.W;
    private Drone drone;
    private int number;

    public Wait(Drone drone, int number)
    {
        this.drone = drone;
        this.number = number;
    }

    @Override
    public String print()
    {
        return String.format("%d %s %d", drone.getId(), COMMAND_ENUM.toString(), number);
    }
}
