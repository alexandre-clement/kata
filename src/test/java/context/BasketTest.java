package context;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class BasketTest
{
    private Basket basket;
    private Basket expected;
    private Item item0;
    private Item item1;

    @Before
    public void setUp() throws Exception
    {
        item0 = new Item(0, 100);
        item1 = new Item(1, 50);
        basket = new Basket(item0, item0, item0, item1, item1);
    }

    @Test
    public void remove() throws Exception
    {
        basket.remove(item0, 2);
        basket.remove(item1, 1);
        expected = new Basket(item0, item1);
        assertEquals(expected, basket);
    }
}