package main;

import context.Context;
import context.InputParser;
import context.NotEnoughTurns;
import engine.ExecuteScheduler;
import engine.OutputParser;

import java.io.IOException;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Runner
{
    private Runner()
    {
    }

    public static void main(String[] args) throws NotEnoughTurns, IOException
    {
        if (args.length < 1)
            throw new IllegalArgumentException();
        String filename = args[0];
        InputParser inputParser = new InputParser(filename);
        Context context = inputParser.getContext();
        OutputParser outputParser = new OutputParser(new Context(context));
        ExecuteScheduler executeScheduler = new ExecuteScheduler(outputParser.getContext(), outputParser.getCommands());
        executeScheduler.execute();
        executeScheduler.printResult();
    }
}
