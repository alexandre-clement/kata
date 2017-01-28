package score;

import command.Command;
import context.Context;
import context.MockedContext;
import org.junit.Before;
import org.junit.Test;
import scheduler.MockedPlanification;
import scheduler.Strategy;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 27/01/2017.
 */
public class SoonerIsBetterTest
{
    private SoonerIsBetter soonerIsBetter;
    private Context context;
    private List<Command> planification;

    @Before
    public void setUp() throws Exception
    {
        context = new MockedContext().getContext();
        planification = new MockedPlanification().getPlanification();
        soonerIsBetter = new SoonerIsBetter(context, planification);

    }

    @Test
    public void score() throws Exception
    {
        assertEquals(96, soonerIsBetter.score());
    }

    @Test
    public void executeCommand() throws Exception
    {

    }

    @Test
    public void completedOrder() throws Exception
    {

    }

}