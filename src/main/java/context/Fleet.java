package context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Fleet extends ArrayList<Drone>
{
    Fleet()
    {
        super();
    }

    Fleet(Drone... drones)
    {
        super(Arrays.asList(drones));
    }

    Fleet(List<Drone> drones)
    {
        super(drones);
    }
}
