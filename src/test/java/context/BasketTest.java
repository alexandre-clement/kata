package context;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class BasketTest
{
    @Test
    public void test() throws Exception
    {
        Basket basket = new Basket(new Item(0, 100), new Item(1, 50));
    }

}