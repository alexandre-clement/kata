package context;

import java.awt.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Drone extends Container
{
    private int payload;
    private int turns;

    Drone(int id, Point location, int payload, int turns)
    {
        this(id, location, payload, turns, new Basket());
    }

    Drone(int id, Point location, int payload, int turns, Basket basket)
    {
        super(id, location, basket);
        this.payload = payload;
        this.turns = turns;
    }

    Drone(Drone drone)
    {
        this(drone.getId(), drone.getLocation(), drone.getPayload(), drone.getTurns());
    }

    public void moveTo(Point location) throws NotEnoughTurns
    {
        int time = distance(location);
        if (turns - time < 0)
            throw new NotEnoughTurns();
        super.getLocation().setLocation(location);
        turns -= time;
    }

    public void waitTime(int time) throws NotEnoughTurns
    {
        if (turns - time < 0)
            throw new NotEnoughTurns();
        turns -= time;
    }

    public int getPayload()
    {
        return payload;
    }

    public int getTurns()
    {
        return turns;
    }

    @Override
    public String toString()
    {
        return super.toString() + '.' + turns;
    }
}
