package context;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 27/01/2017.
 */
public class IniTest
{
    @Test
    public void generate() throws Exception
    {
        new Ini(new MockedContext().getContext(), "target/input.ini").generate();
    }

    @Test
    public void initOrders() throws Exception
    {

    }

    @Test
    public void initWarehouses() throws Exception
    {

    }

    @Test
    public void header() throws Exception
    {

    }

    @Test
    public void initItems() throws Exception
    {

    }

}