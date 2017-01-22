package context;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Container extends Basket
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Container container = (Container) o;

        if (id != container.id)
            return false;
        return location != null ? location.equals(container.location) : container.location == null;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
