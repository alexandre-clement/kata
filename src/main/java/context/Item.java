package context;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
public class Item
{
    private int id;
    private int payload;

    Item(int id, int payload)
    {
        this.id = id;
        this.payload = payload;
    }

    public int getId()
    {
        return id;
    }

    public int getPayload()
    {
        return payload;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "=" + id;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object == null || getClass() != object.getClass() || this != object)
            return false;
        Item identifiable = (Item) object;
        return id == identifiable.id;
    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
