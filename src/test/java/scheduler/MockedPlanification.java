package scheduler;

import command.Command;
import command.Deliver;
import command.Load;
import command.Wait;
import context.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class MockedPlanification
{
    private final ArrayList<Command> commands;
    private final Context context;
    private final Fleet fleet;
    private final List<Item> items;
    private final List<Container> warehouses;
    private final List<Container> orders;

    public MockedPlanification()
    {
        context = new MockedContext().getContext();
        fleet = context.getFleet();
        items = context.getItems();
        warehouses = context.getWarehouses();
        orders = context.getOrders();
        commands = new ArrayList<>();
        commands.add(new Load(fleet.get(0), warehouses.get(0), items.get(0), 2));
        commands.add(new Load(fleet.get(0), warehouses.get(0), items.get(1), 3));

        commands.add(new Deliver(fleet.get(0), orders.get(0), items.get(0), 1));
        commands.add(new Deliver(fleet.get(0), orders.get(1), items.get(1), 2));
        commands.add(new Deliver(fleet.get(0), orders.get(2), items.get(0), 1));
        commands.add(new Deliver(fleet.get(0), orders.get(2), items.get(1), 1));

        commands.add(new Load(fleet.get(1), warehouses.get(1), items.get(2), 1));
        commands.add(new Load(fleet.get(1), warehouses.get(1), items.get(1), 1));

        commands.add(new Deliver(fleet.get(1), orders.get(1), items.get(1), 1));
        commands.add(new Deliver(fleet.get(1), orders.get(0), items.get(2), 1));

        commands.add(new Load(fleet.get(0), warehouses.get(1), items.get(2), 1));
        commands.add(new Deliver(fleet.get(0), orders.get(2), items.get(2), 1));

        commands.add(new Wait(fleet.get(0), 4));
        commands.add(new Wait(fleet.get(1), 6));
    }

    public List<Command> getPlanification() throws Exception
    {
        return commands;
    }

}