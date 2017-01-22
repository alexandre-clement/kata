package context;

import java.util.List;

/**
 * @author Alexandre Clement
 * @since 21/01/2017.
 */
public interface Context
{
    Fleet getFleet();

    List<Warehouse> getWarehouses();

    List<Order> getOrders();

    List<Item> getItems();
}
