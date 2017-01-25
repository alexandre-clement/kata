package context;

import java.util.*;
import java.util.stream.Collectors;
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

    public Basket union(Basket basket)
    {
        Basket union = new Basket(this);
        union.addAll(basket);
        return union;
    }

    public Basket intersection(Basket basket)
    {
        Basket intersect = new Basket(this);
        intersect.retainAll(basket);
        return intersect;
    }

    public void intersect(Basket basket)
    {
        Basket temp = new Basket(basket);
        Basket remove = new Basket();
        for (Item item : this)
        {
            if (temp.contains(item))
                temp.remove(item);
            else
                remove.add(item);
        }
        for (Item item : remove)
        {
            this.remove(item);
        }
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

    public Basket sort()
    {
        return new Basket(this.stream().sorted((i1, i2) -> Integer.compare(i2.getPayload(), i1.getPayload())).collect(Collectors.toList()));
    }

}
