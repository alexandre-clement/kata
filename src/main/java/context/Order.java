package context;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Order extends Container
{
    Order(int id, Point location)
    {
        super(id, location);
    }

    Order(int id, Point location, Basket basket)
    {
        super(id, location, basket);
    }

    Order(Order order)
    {
        this(order.getId(), order.getLocation(), order);
    }
}
