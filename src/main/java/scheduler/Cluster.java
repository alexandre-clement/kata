package scheduler;

import context.Basket;
import context.Container;
import context.Item;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 24/01/2017.
 */
class Cluster extends ArrayList<Container>
{
    private Container kernel;

    Cluster(Container kernel)
    {
        super();
        this.kernel = kernel;
    }

    Cluster(Cluster cluster)
    {
        super(cluster);
        this.kernel = cluster.getKernel();
    }

    int distance(Container container)
    {
        if (!kernel.sort().equals(container.sort()))
            return Integer.MAX_VALUE;
        return kernel.distance(container);
    }

    int distance(Cluster cluster)
    {
        return (int) getCenter().distance(cluster.getCenter());
    }

    Point getCenter()
    {
        Point center = new Point(kernel.getLocation());
        if (size() == 0)
            return center;
        for (Container container : this)
        {
            center.translate(container.getLocation().x, container.getLocation().y);
        }
        center.translate((int) center.getX() / size(), (int) center.getY() / size());
        return center;
    }

    void union(Cluster cluster)
    {
        kernel.addAll(cluster.getKernel());
        this.addAll(cluster);
    }

    void close()
    {
        this.stream().map(container -> (Basket) container).reduce(Basket::union).ifPresent(kernel::intersect);
        regroup();
        removeEmpty();
    }

    void removeEmpty()
    {
        this.removeAll(this.stream().filter(Container::isEmpty).collect(Collectors.toList()));
    }

    void regroup()
    {
        Map<Integer, Integer> link = new HashMap<>();

        for (int i = 0; i < this.size(); i++)
        {
            if (link.containsKey(hash(get(i))))
                get(link.get(hash(get(i)))).addAll(get(i));
            else if (!get(i).isEmpty())
                link.put(hash(get(i)), i);
        }
        for (int i = this.size() - 1; i >= 0; i--)
        {
            if (!link.values().contains(i))
                this.remove(i);
        }
    }

    static int hash(Container container)
    {
        if (container.isEmpty())
            return 0;
        return container.getLocation().hashCode() + container.get(0).hashCode();
    }


    int getPayload()
    {
        return this.stream().map(Container::payload).reduce(Integer::sum).orElse(0);
    }

    @Override
    public String toString()
    {
        return String.format("%s : %s", kernel, super.toString());
    }

    public Container getKernel()
    {
        return kernel;
    }

}
