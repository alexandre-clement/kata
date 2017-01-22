package context;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Drone extends Container
{
    Drone(int id, Point location)
    {
        super(id, location);
    }

    Drone(int id, Point location, Basket basket)
    {
        super(id, location, basket);
    }

    Drone(Drone drone)
    {
        this(drone.getId(), drone.getLocation());
    }
}
