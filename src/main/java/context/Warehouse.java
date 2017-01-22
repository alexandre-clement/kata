package context;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Warehouse extends Container
{
    Warehouse(int id, Point location)
    {
        super(id, location);
    }

    Warehouse(int id, Point location, Basket basket)
    {
        super(id, location, basket);
    }

    Warehouse(Warehouse warehouse)
    {
        this(warehouse.getId(), warehouse.getLocation(), warehouse);
    }
}