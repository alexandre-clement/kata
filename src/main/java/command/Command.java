package command;

import context.DroneException;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public interface Command
{
    String print();

    void execute() throws DroneException;

    CommandEnum getCommandEnum();
}
