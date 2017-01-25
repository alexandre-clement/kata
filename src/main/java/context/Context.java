package context;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 21/01/2017.
 */
public class Context
{
    private final List<Container> warehouses;
    private final List<Container> orders;
    private final List<Item> items;
    private final Fleet fleet;

    public Context(List<Container> warehouses, List<Container> orders, List<Item> items, Fleet fleet)
    {
        this.warehouses = warehouses.stream().map(Container::new).collect(Collectors.toList());
        this.orders = orders.stream().map(Container::new).collect(Collectors.toList());
        this.items = items;
        this.fleet = new Fleet(fleet);
    }

    public Context(Context context)
    {
        this(context.getWarehouses(), context.getOrders(), context.getItems(), context.getFleet());
    }

    public List<Container> getWarehouses()
    {
        return warehouses;
    }

    public List<Container> getOrders()
    {
        return orders;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public Fleet getFleet()
    {
        return fleet;
    }
}
