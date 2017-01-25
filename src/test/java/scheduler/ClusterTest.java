package scheduler;

import context.Context;
import context.MockedContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 25/01/2017.
 */
public class ClusterTest
{
    private Context context;
    private Cluster cluster;

    @Before
    public void setUp() throws Exception
    {
        context = new MockedContext().getContext();
        cluster = new Cluster(context.getWarehouses().get(0));

    }

    @Test
    public void sort() throws Exception
    {

    }

    @Test
    public void getCenter() throws Exception
    {

    }

    @Test
    public void getVariance() throws Exception
    {

    }

    @Test
    public void comparator() throws Exception
    {

    }

}