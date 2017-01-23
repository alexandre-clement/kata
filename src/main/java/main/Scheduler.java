package main;

import command.Command;
import context.Context;
import context.InputParser;
import context.NotEnoughTurns;
import engine.ExecuteScheduler;
import engine.OutputParser;
import scheduler.DoNothing;
import scheduler.Output;
import scheduler.Planification;

import java.io.IOException;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Scheduler
{
    public static void main(String[] args) throws IOException, NotEnoughTurns
    {
        if (args.length < 1)
            throw new IllegalArgumentException();
        String filename = args[0];
        InputParser inputParser = new InputParser(filename);
        Context context = inputParser.getContext();
        Planification planification = new DoNothing(new Context(context));
        Output output = new Output(planification.buildPlanification());
        output.generate();
    }
}
