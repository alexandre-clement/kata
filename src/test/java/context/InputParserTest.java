package context;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class InputParserTest
{
    private InputParser inputParser;
    private MockedContext mockedParser;

    @Before
    public void setUp() throws Exception
    {
        inputParser = new InputParser("input.ini");
        mockedParser = new MockedContext();
    }

    @Test
    public void getFleet() throws Exception
    {
        assertEquals(mockedParser.getFleet(), inputParser.getFleet());
    }

    @Test
    public void getWarehouses() throws Exception
    {
        assertEquals(mockedParser.getWarehouses(), inputParser.getWarehouses());
    }

    @Test
    public void getOrders() throws Exception
    {
        assertEquals(mockedParser.getOrders(), inputParser.getOrders());
    }

    @Test
    public void getItems() throws Exception
    {
        assertEquals(mockedParser.getItems(), inputParser.getItems());
    }

}