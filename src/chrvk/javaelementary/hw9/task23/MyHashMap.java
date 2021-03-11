package chrvk.javaelementary.hw9.task23;

import java.util.ArrayList;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 4;

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private int capacity;

    private static final float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

    private final float loadFactorThreshold;

    private int size = 0;

    ArrayList<MyMap.Entry<K, V>> table;

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY) {
            this.capacity = MAXIMUM_CAPACITY;
        } else {
            this.capacity = trimToPowerOf2(initialCapacity);
        }

        this.loadFactorThreshold = loadFactorThreshold;
        table = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }
    }

    @Override
    public void clear() {
        size = 0;
        removeEntries();
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if (table.get(i) != null && table.get(i).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public java.util.Set<MyMap.Entry<K, V>> entrySet() {
        java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table.get(i) != null) {
                set.add(table.get(i));
            }
        }

        return set;
    }

    @Override
    public V get(K key) {
        int index = hash(key.hashCode());
        int j = 0;

        while (table.get(index) != null) {
            if (table.get(index).getKey().equals(key)) {
                return table.get(index).getValue();
            }
            index += Math.pow(j++, 2);
            index %= capacity;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public java.util.Set<K> keySet() {
        java.util.Set<K> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table.get(i) != null) {
                set.add(table.get(i).getKey());
            }
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key.hashCode());
        int j = 0;

        while (table.get(index) != null) {
            if (table.get(index).getKey().equals(key)) {
                Entry<K, V> entry = table.get(index);
                V oldValue = entry.getValue();
                entry.value = value;
                table.set(index, entry);
                return oldValue;
            }
            index += Math.pow(j++, 2);
            index %= capacity;
        }

        if (size >= capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
            rehash();
        }

        table.set(index, new MyMap.Entry<>(key, value));

        size++;

        return value;
    }

    @Override
    public void remove(K key) {
        int index = hash(key.hashCode());
        int j = 0;

        while (table.get(index) != null) {
            if (table.get(index).getKey().equals(key)) {
                table.remove(index);
                size--;
                break;
            }
            index += Math.pow(j++, 2);
            index %= capacity;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public java.util.Set<V> values() {
        java.util.Set<V> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table.get(i) != null) {
                set.add(table.get(i).getValue());
            }
        }
        return set;
    }

    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    public int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }

    private void removeEntries() {
        table.clear();
    }

    private void rehash() {
        java.util.Set<Entry<K, V>> set = entrySet();
        capacity <<= 1;
        size = 0;
        table.clear();
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }

        for (Entry<K, V> entry : set) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (Entry<K, V> entry : table) {
            if (entry != null) {
                builder.append(entry);
            }
        }

        builder.append("]");
        return builder.toString();
    }
}
