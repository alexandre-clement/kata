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
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Item item = (Item) object;

        return id == item.getId();
    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
