package main;

import context.Context;
import context.InputParser;
import scheduler.Planification;

import java.io.IOException;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        if (args.length < 1)
            throw new IllegalArgumentException();
        String filename = args[0];
        Context context = new InputParser(filename);
        Planification planification = new Planification();

    }
}
