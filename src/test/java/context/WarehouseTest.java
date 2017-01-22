package context;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class WarehouseTest
{
    @Test
    public void test() throws Exception
    {
        Container container = new Warehouse(0, new Point(10, 2), new Basket(new Item(0, 10), new Item(1, 200)));
        System.out.println(container);
    }
}