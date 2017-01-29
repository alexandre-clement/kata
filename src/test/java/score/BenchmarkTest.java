package score;

import command.Command;
import context.Context;
import context.MockedContext;
import org.junit.Before;
import org.junit.Test;
import scheduler.MockedPlanification;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 28/01/2017.
 */
public class BenchmarkTest
{
    private Benchmark benchmark;
    private Context context;
    private List<Command> planification;

    @Before
    public void setUp() throws Exception
    {
        context = new MockedContext().getContext();
        planification = new MockedPlanification().getPlanification();
        benchmark = new Benchmark(context, planification);
    }

    @Test
    public void score() throws Exception
    {
        assertEquals(96, benchmark.score());
    }

}