package scheduler;

import context.Container;
import context.Context;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 26/01/2017.
 */
public class Csv
{
    private static final String warehouse = "W";
    private static final String order = "O";
    private static final String delimiter = ",";
    private static final String filename = "map.csv";
    private final int rows;
    private final int columns;
    private final Map<Point, Integer> warehouses;
    private final Map<Point, Integer> orders;

    public Csv(Context context)
    {
        rows = context.getRows();
        columns = context.getColumns();

        warehouses = new HashMap<>();
        for (Container container : context.getWarehouses())
            warehouses.put(container.getLocation(), container.getId());

        orders = new HashMap<>();
        for (Container container : context.getOrders())
            orders.put(container.getLocation(), container.getId());
    }

    public void createFile() throws IOException
    {
        createFile(filename);
    }

    public void createFile(String filename) throws IOException
    {
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        printWriter.print(createMap());
        printWriter.close();
    }

    public String createMap()
    {
        Point position = new Point();
        StringBuilder map = new StringBuilder();

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                position.setLocation(i, j);
                if (warehouses.containsKey(position))
                    map.append(warehouse).append(warehouses.get(position));
                else if (orders.containsKey(position))
                    map.append(order).append(orders.get(position));
                if (j < columns - 1)
                    map.append(delimiter);
            }
            map.append('\n');
        }
        return map.toString();
    }
}
