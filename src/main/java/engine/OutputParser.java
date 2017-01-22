package engine;

import command.*;
import context.Context;
import context.Drone;
import context.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class OutputParser
{
    private static final String OUT_FILE = "scheduler.out";
    private final int size;
    private Context context;
    private final BufferedReader bufferedReader;
    private final Pattern pattern;

    public OutputParser(Context context) throws IOException
    {
        this(context, OutputParser.OUT_FILE);
    }

    public OutputParser(Context context, String filename) throws IOException
    {
        this.context = context;
        bufferedReader = new BufferedReader(new FileReader(filename));
        size = Integer.parseInt(bufferedReader.readLine());
        pattern = Pattern.compile("^(\\d+) (W|D|U|L) (\\d+)(?: (\\d+) (\\d+))?$");
    }

    public Command nextCommand() throws IOException
    {
        String nextCommand = bufferedReader.readLine();
        if (nextCommand == null)
            return null;
        Matcher matcher = pattern.matcher(nextCommand);
        if (!matcher.matches())
            return null;
        CommandEnum commandEnum = CommandEnum.valueOf(matcher.group(2));
        Drone drone = context.getFleet().get(Integer.valueOf(matcher.group(1)));
        int group3 = Integer.parseInt(matcher.group(3));

        if (commandEnum == CommandEnum.W)
            return new Wait(drone, group3);

        Item item = context.getItems().get(Integer.valueOf(matcher.group(4)));
        int number = Integer.parseInt(matcher.group(5));

        switch (commandEnum)
        {
            case D:
                return new Deliver(drone, context.getOrders().get(group3), item, number);
            case L:
                return new Load(drone, context.getWarehouses().get(group3), item, number);
            case U:
                return new Unload(drone, context.getWarehouses().get(group3), item, number);
            default:
                return null;
        }
    }

    public List<Command> getCommands() throws IOException
    {
        List<Command> commands = new ArrayList<>();
        Command command;
        for (command = nextCommand(); command != null; command = nextCommand())
        {
            commands.add(command);
        }
        if (size != commands.size())
            throw new IOException();
        return commands;
    }

    public void close() throws IOException
    {
        bufferedReader.close();
    }
}
