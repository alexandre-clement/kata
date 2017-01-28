package score;

import command.Command;
import context.Context;
import context.DroneException;

import java.io.PrintStream;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 27/01/2017.
 */
public class Benchmark
{
    private PrintStream out = System.out;
    private final Context context;
    private final List<Command> planification;
    private final KPI[] kpiArray;

    public Benchmark(Context context, List<Command> planification)
    {
        this.context = context;
        this.planification = planification;
        kpiArray = new KPI[]{new SoonerIsBetter(context, planification)};
    }

    public int score() throws DroneException
    {
        int score = 0;

        for (KPI kpi : kpiArray)
        {
            score += kpi.score();
        }
        return score;
    }

    public void printResult() throws DroneException
    {
        out.println("Score : " + score());
    }
}
