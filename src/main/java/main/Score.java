package main;

import context.Context;
import context.DroneException;
import context.InputParser;
import engine.OutputParser;
import score.Benchmark;

import java.io.IOException;

/**
 * @author Alexandre Clement
 * @since 27/01/2017.
 */
public class Score
{
    public static void main(String[] args) throws IOException, DroneException
    {
        if (args.length < 1)
            throw new IllegalArgumentException();
        String filename = args[0];
        InputParser inputParser = new InputParser(filename);
        Context context = inputParser.getContext();
        OutputParser outputParser = new OutputParser(new Context(context));
        Benchmark benchmark = new Benchmark(outputParser.getContext(), outputParser.getCommands());
        benchmark.printResult();

    }
}
