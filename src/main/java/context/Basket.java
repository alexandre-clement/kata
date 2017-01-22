package context;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Basket extends ArrayList<Item>
{
    Basket()
    {
        super();
    }

    Basket(Item... items)
    {
        super(Arrays.asList(items));
    }

    Basket(Collection<Item> items)
    {
        super(items);
    }

    public void add(Item item, int number)
    {
        IntStream.range(0, number).forEach(i -> this.add(item));
    }

    public void remove(Item item, int number)
    {
        IntStream.range(0, number).forEach(i -> this.remove(item));
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
