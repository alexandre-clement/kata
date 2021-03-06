package context;

import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Container> getSingletons()
    {
        return this.stream().map(item -> new Container(id, new Point(location), new Basket(item))).collect(Collectors.toList());
    }

    public void setLocation(Container container)
    {
        location.setLocation(container.getLocation());
    }

    public int distance(Container container)
    {
        return distance(container.getLocation());
    }

    private int distance(Point location)
    {
        return (int) Math.ceil(this.location.distance(location));
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
        result = 31 * result + location.x;
        result = 31 * result + location.y;
        result = 31 * result + id;
        return result;
    }

    public static int compare(Container c1, Container c2)
    {

        return c1.distance(c2);
    }
}
