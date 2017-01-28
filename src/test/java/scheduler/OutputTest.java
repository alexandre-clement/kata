package scheduler;

import command.Command;
import context.Context;
import context.MockedContext;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 28/01/2017.
 */
public class OutputTest
{
    private List<Command> planification;

    @Test
    public void generate() throws Exception
    {
        planification = new MockedPlanification().getPlanification();
        Output output = new Output(planification, "target/scheduler.out");
        output.generate();
    }

}