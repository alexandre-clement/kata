package main;

import context.Context;
import context.InputParser;
import context.NotEnoughTurns;
import scheduler.DoNothing;
import scheduler.Output;
import scheduler.Planification;
import scheduler.Strategy;

import java.io.IOException;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Scheduler
{
    private Scheduler()
    {
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length < 1)
            throw new IllegalArgumentException();
        String filename = args[0];
        InputParser inputParser = new InputParser(filename);
        Context context = inputParser.getContext();
        Planification planification = new Strategy(new Context(context));
        Output output = new Output(planification.buildPlanification());
        output.generate();
    }
}
