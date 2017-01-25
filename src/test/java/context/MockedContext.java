package context;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class MockedContext
{
    private final Item item0;
    private final Item item1;
    private final Item item2;
    private final Basket basketWarehouse1;
    private final Basket basketWarehouse2;
    private final Basket basketOrder1;
    private final Basket basketOrder2;
    private final Basket basketOrder3;
    private final Container warehouse1;
    private final Container warehouse2;
    private final Container order1;
    private final Container order2;
    private final Container order3;
    private final ArrayList warehouses;
    private final ArrayList orders;
    private final ArrayList items;
    private final Fleet fleet;

    public MockedContext()
    {
        item0 = new Item(0, 100);
        item1 = new Item(1, 5);
        item2 = new Item(2, 450);
        basketWarehouse1 = new Basket(item0, item0, item1, item1, item1, item1, item1);
        basketWarehouse2 = new Basket(item1, item2, item2, item2);
        basketOrder1 = new Basket(item0, item2);
        basketOrder2 = new Basket(item1, item1, item1);
        basketOrder3 = new Basket(item0, item1, item2);
        warehouse1 = new Container(0, new Point(), basketWarehouse1);
        warehouse2 = new Container(1, new Point(5, 5), basketWarehouse2);
        order1 = new Container(0, new Point(1, 2), basketOrder1);
        order2 = new Container(1, new Point(4, 3), basketOrder2);
        order3 = new Container(2, new Point(5, 6), basketOrder3);

        warehouses = new ArrayList<>(Arrays.asList(warehouse1, warehouse2));
        orders = new ArrayList<>(Arrays.asList(order1, order2, order3));
        items = new ArrayList<>(Arrays.asList(item0, item1, item2));
        fleet = new Fleet(2, 500, 25, new Point());
    }

    public Context getContext()
    {
        return new Context(warehouses, orders, items, fleet);
    }
}
