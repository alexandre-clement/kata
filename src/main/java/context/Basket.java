package context;

import java.util.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Basket extends ArrayDeque<Item>
{
    Basket()
    {
        super();
    }

    Basket(Item... items)
    {
        super(Arrays.asList(items));
    }

    Basket(Deque<Item> items)
    {
        super(items);
    }

    public int countItem(Item item)
    {
        return (int) super.stream().filter(i -> i.equals(item)).count();
    }

    public int payload()
    {
        return super.stream().map(Item::getPayload).reduce(Integer::sum).orElse(0);
    }
}
