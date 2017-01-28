package score;

import context.DroneException;

/**
 * @author Alexandre Clement
 * @since 27/01/2017.
 */
public interface KPI
{
    int score() throws DroneException;
}
