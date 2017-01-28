package context;

import scheduler.Output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 27/01/2017.
 */
public class Ini
{
    public static final String INI_FILE = "input.ini";
    private Context context;
    private final PrintWriter printWriter;

    public Ini(Context context) throws IOException
    {
        this(context, Output.OUT_FILE);
    }

    Ini(Context context, String filename) throws IOException
    {
        this.context = context;
        printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
    }

    public void generate()
    {
        header();
        initItems();
        initWarehouses();
        initOrders();
        printWriter.close();
    }

    void initOrders()
    {
        printWriter.println(context.getOrders().size());
        for (Container container : context.getOrders())
        {
            printWriter.println(container.getLocation().x + " " + container.getLocation().y);
            printWriter.println(container.size());
            printWriter.println(container.stream().map(Item::getId).map(Object::toString).collect(Collectors.joining(" ")));
        }
    }

    void initWarehouses()
    {
        printWriter.println(context.getWarehouses().size());
        for (Container container : context.getWarehouses())
        {
            printWriter.println(container.getLocation().x + " " + container.getLocation().y);
            printWriter.println(context.getItems().stream().map(container::countItem).map(Object::toString).collect(Collectors.joining(" ")));
        }
    }

    void header()
    {
        int rows = context.getRows();
        int columns = context.getColumns();
        int drones = context.getFleet().size();
        int turns = context.getFleet().getTurns();
        int payload = context.getFleet().getPayload();
        printWriter.println(String.format("%d %d %d %d %d", rows, columns, drones, turns, payload));
    }

    void initItems()
    {
        printWriter.println(context.getItems().size());
        printWriter.println(context.getItems().stream().map(Item::getPayload).map(Object::toString).collect(Collectors.joining(" ")));
    }

}
