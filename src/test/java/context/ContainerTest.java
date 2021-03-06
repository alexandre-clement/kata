package context;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class ContainerTest
{
    private Basket basket;
    private Basket expected;
    private Item item0;
    private Item item1;
    private Container container;

    @Before
    public void setUp() throws Exception
    {
        item0 = new Item(0, 100);
        item1 = new Item(1, 50);
        basket = new Basket(item0, item0, item0, item1, item1);
        container = new Container(0, new Point(2, 3), basket);
    }

    @Test
    public void getSingletons() throws Exception
    {

    }

    @Test
    public void equalsTest() throws Exception
    {
        Container container1 = new Container(container);
        Container container2 = new Container(container);
        Container container3 = new Container(container.getId()-1, container.getLocation(), container);
        container1.remove(item1);
        container2.getLocation().translate(10, 0);
    }
}