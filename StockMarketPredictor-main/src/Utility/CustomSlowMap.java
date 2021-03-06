package Utility;

public class CustomSlowMap<K,V> {
    private final K[] keys;
    private final V[] values;

    public CustomSlowMap(K[] keys, V[] values) {
        this.keys = keys;
        this.values = values;
    }

    public V getValue(K key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }
}
