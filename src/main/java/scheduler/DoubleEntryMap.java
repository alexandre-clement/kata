package scheduler;

import context.Container;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alexandre Clement
 * @since 23/01/2017.
 */
class DoubleEntryMap<K extends Container, V>
{
    private boolean symmetric;
    private Map<K, Map<K, V>> map;

    DoubleEntryMap()
    {
        symmetric = true;
        map = new HashMap<>();
    }

    DoubleEntryMap(boolean symmetric)
    {
        this();
        this.symmetric = symmetric;
    }

    void put(K key1, K key2, V value)
    {
        if (!map.containsKey(key1))
            map.put(key1, new HashMap<>());
        if (!map.containsKey(key2) && symmetric)
            map.put(key2, new HashMap<>());
        map.get(key1).put(key2, value);
        if (symmetric)
            map.get(key2).put(key1, value);
    }

    V get(K key1, K key2)
    {
        if (map.containsKey(key1) && map.get(key1).containsKey(key2))
            return map.get(key1).get(key2);
        if (map.containsKey(key2) && map.get(key2).containsKey(key1) && symmetric)
            return map.get(key2).get(key1);
        return null;
    }

    @Override
    public String toString()
    {
        return '{' + map.entrySet().stream().map(entry -> print(entry.getKey(), entry.getValue())).collect(Collectors.joining("\n")) + '}';
    }

    private String print(K key, Map<K, V> value)
    {
        String format = "(O%d, W%d): %s";
        return value.entrySet().stream().map(entry -> String.format(format, key.getId(), entry.getKey().getId(), entry.getValue())).collect(Collectors.joining("; "));
    }

}
