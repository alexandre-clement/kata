package context;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Item extends Identifiable
{
    private int payload;

    Item(int id, int payload)
    {
        super(id);
        this.payload = payload;
    }

    int getPayload()
    {
        return payload;
    }
}
