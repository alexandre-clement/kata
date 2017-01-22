package context;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Fleet extends ArrayList<Drone>
{
    Fleet(int number, int payload, int turns, Point initLocation)
    {
        this(IntStream.range(0, number).mapToObj(i -> new Drone(i, initLocation, payload, turns)).collect(Collectors.toList()));
    }

    Fleet(Drone... drones)
    {
        this(Arrays.asList(drones));
    }

    Fleet(List<Drone> drones)
    {
        super(drones);
    }
}
