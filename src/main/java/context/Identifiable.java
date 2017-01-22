package context;

/**
 * @author Alexandre Clement
 * @since 22/01/2017.
 */
class Identifiable
{
    private int id;

    Identifiable(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
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
        Identifiable identifiable = (Identifiable) object;
        return id == identifiable.id;
    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
