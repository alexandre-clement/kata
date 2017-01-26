package context;

import command.Command;
import command.Deliver;
import command.Load;
import command.Unload;

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

    public void moveTo(Container container) throws NotEnoughTurns
    {
        int time = distance(container);
        if (turns - time < 0)
            throw new NotEnoughTurns();
        super.setLocation(container);
        turns -= time;
    }

    public void waitTime(int time) throws NotEnoughTurns
    {
        if (turns - time < 0)
            throw new NotEnoughTurns();
        turns -= time;
    }

    public Command load(Container warehouse, Item item, int n) throws NotEnoughTurns, DroneOverload
    {
        moveTo(warehouse);
        warehouse.remove(item, n);
        add(item, n);
        if (getPayload() > payload)
            throw new DroneOverload();
        waitTime(1);
        return new Load(this, warehouse, item, n);
    }

    public Command unload(Container warehouse, Item item, int n) throws NotEnoughTurns
    {
        moveTo(warehouse);
        remove(item, n);
        warehouse.add(item, n);
        waitTime(1);
        return new Unload(this, warehouse, item, n);
    }

    public Command deliver(Container order, Item item, int n) throws NotEnoughTurns
    {
        moveTo(order);
        remove(item, n);
        order.remove(item, n);
        waitTime(1);
        return new Deliver(this, order, item, n);
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Drone items = (Drone) o;

        if (payload != items.payload)
            return false;
        return turns == items.turns;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + payload;
        result = 31 * result + turns;
        return result;
    }
}
