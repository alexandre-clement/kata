package scheduler;

import command.Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Output
{
    public static final String OUT_FILE = "scheduler.out";
    private List<Command> planification;
    private final PrintWriter printWriter;

    public Output(List<Command> planification) throws IOException
    {
        this(planification, Output.OUT_FILE);
    }

    public Output(List<Command> planification, String filename) throws IOException
    {
        this.planification = planification;
        printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
    }

    public void generate()
    {
        printWriter.println(planification.size());

        for (Command command : planification)
        {
            printWriter.println(command.print());
        }

        printWriter.close();
    }
}
