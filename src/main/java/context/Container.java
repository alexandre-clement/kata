package context;

import java.awt.*;
import java.util.Iterator;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
class Container extends Basket
{
    private int id;
    private Point location;

    Container(int id, Point location)
    {
        this.id = id;
        this.location = location;
    }

    Container(int id, Point location, Basket basket)
    {
        super(basket);
        this.id = id;
        this.location = location;
    }

    Container(Container container)
    {
        this(container.getId(), new Point(container.getLocation()), new Basket(container));
    }

    public int getId()
    {
        return id;
    }

    public Point getLocation()
    {
        return location;
    }

    @Override
    public String toString()
    {
        return String.format("%s=%d(%d, %d)%s", this.getClass().getSimpleName(), id, location.x, location.y, super.toString());
    }
}
