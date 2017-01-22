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

}