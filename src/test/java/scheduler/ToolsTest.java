package scheduler;

import context.Container;
import context.Context;
import context.InputParser;
import context.MockedContext;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 23/01/2017.
 */
public class ToolsTest
{
    private Context context;
    private Tools tools;

    @Before
    public void setUp() throws Exception
    {
        context = new MockedContext().getContext();
        tools = new Tools(context);
    }

    @Test
    public void completeWithWait() throws Exception
    {

    }

    @Test
    public void getDistances() throws Exception
    {

    }

    @Test
    public void getSingletons() throws Exception
    {

    }

    @Test
    public void getDependencies() throws Exception
    {
    }

    @Test
    public void getClusters() throws Exception
    {
        List<Container> kernels = tools.getSingletons(context.getWarehouses());
        List<Container> electrons = tools.getSingletons(context.getOrders());
        List<Cluster> clusters = tools.getCluster(kernels, electrons, 1);
        List<Cluster> regroup = tools.getFinalCluster(clusters);
    }
}