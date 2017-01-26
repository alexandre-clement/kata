package context;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class InputParser
{
    private static final String INIT = "^(\\d+) (\\d+) (\\d+) (\\d+) (\\d+)$";
    private static final String OBJECT_NUMBER = "^(\\d+)$";
    private static final String MULTI_OBJECT = "^(?:(\\d+) )*(\\d+)$";
    private static final String LOCATION = "^(\\d+) (\\d+)$";
    private final BufferedReader bufferedReader;
    private int rows;
    private int columns;
    private Fleet fleet;
    private List<Item> items;
    private List<Container> warehouses;
    private List<Container> orders;


    public InputParser(String filename) throws IOException
    {
        bufferedReader = new BufferedReader(new FileReader(filename));
    }

    public Context getContext() throws IOException
    {
        initFleet();
        initOrders();
        return new Context(rows, columns, warehouses, orders, items, fleet);
    }

    void initFleet() throws IOException
    {
        Pattern init = Pattern.compile(INIT);
        Matcher matcher = init.matcher(bufferedReader.readLine());
        if (!matcher.matches())
            throw new NotWellFormedInput();
        rows = Integer.parseInt(matcher.group(1));
        columns = Integer.parseInt(matcher.group(2));
        int numberOfDrone = Integer.parseInt(matcher.group(3));
        int turns = Integer.parseInt(matcher.group(4));
        int dronePayload = Integer.parseInt(matcher.group(5));

        initItem(dronePayload);
        initWarehouse();

        fleet = new Fleet(numberOfDrone, dronePayload, turns, warehouses.get(0).getLocation());

    }

    private void initOrders() throws IOException
    {
        int orderNumber = getObjectNumber();
        Point location;
        Container order;
        int check;
        Integer[] number;

        orders = new ArrayList<>();
        for (int i = 0; i < orderNumber; i++)
        {
            location = getLocation();
            order = new Container(i, location);
            check = getObjectNumber();
            number = matchMultiObject(check);
            fillOrder(order, number);
            orders.add(order);
        }
    }

    private void initWarehouse() throws IOException
    {
        int warehouseNumber = getObjectNumber();
        int numberOfItem = items.size();
        Point location;
        Container warehouse;
        Integer[] number;

        warehouses = new ArrayList<>();
        for (int i = 0; i < warehouseNumber; i++)
        {
            location = getLocation();
            warehouse = new Container(i, location);
            number = matchMultiObject(numberOfItem);
            fillWarehouse(warehouse, number);
            warehouses.add(warehouse);
        }
    }

    Point getLocation() throws IOException
    {
        Matcher matcher = Pattern.compile(LOCATION).matcher(bufferedReader.readLine());
        if (!matcher.matches())
            throw new NotWellFormedInput();

        return new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }

    int getObjectNumber() throws IOException
    {
        Matcher matcher = Pattern.compile(OBJECT_NUMBER).matcher(bufferedReader.readLine());
        if (!matcher.matches())
            throw new NotWellFormedInput();

        return Integer.parseInt(matcher.group(1));
    }

    private void fillOrder(Container order, Integer[] number) throws NotWellFormedInput
    {
        int size = items.size();
        for (Integer integer : number)
        {
            if (integer >= size)
                throw new NotWellFormedInput();
            order.add(items.get(integer));
        }
    }

    void fillWarehouse(Container container, Integer[] number) throws NotWellFormedInput
    {
        for (int j = 0; j < items.size(); j++)
        {
            if (number[j] < 0)
                throw new NotWellFormedInput();
            container.add(items.get(j), number[j]);
        }
    }

    void initItem(int dronePayload) throws IOException
    {
        int itemNumber = getObjectNumber();
        Integer[] payload = matchMultiObject(itemNumber);

        items = new ArrayList<>();
        for (int i = 0; i < payload.length; i++)
        {
            if (payload[i] < 0 || payload[i] > dronePayload)
                throw new NotWellFormedInput();
            items.add(new Item(i, payload[i]));
        }
    }

    Integer[] matchMultiObject(int check) throws IOException
    {
        Matcher matcher;
        matcher = Pattern.compile(MULTI_OBJECT).matcher(bufferedReader.readLine());
        if (!matcher.matches())
            throw new NotWellFormedInput();

        Integer[] integers = Arrays.stream(matcher.group().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        if (integers.length != check)
            throw new NotWellFormedInput();
        return integers;
    }
}
