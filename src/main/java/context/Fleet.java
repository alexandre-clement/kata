package context;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Fleet extends ArrayList<Drone>
{
    private int payload;
    private int turns;

    Fleet(int number, int payload, int turns, Point initLocation)
    {
        this(IntStream.range(0, number).mapToObj(i -> new Drone(i, new Point(initLocation), payload, turns)).collect(Collectors.toList()));
        this.payload = payload;
        this.turns = turns;
    }

    Fleet(Drone... drones)
    {
        this(Arrays.asList(drones));
    }

    Fleet(Collection<? extends Drone> drones)
    {
        super(drones.stream().map(Drone::new).collect(Collectors.toList()));
        if (!isEmpty())
        {
            payload = get(0).getPayload();
            turns = get(0).getTurns();
        }
    }

    public int getPayload()
    {
        return payload;
    }

    public int getTurns()
    {
        return turns;
    }
}
