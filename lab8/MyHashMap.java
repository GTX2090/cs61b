import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashMap<K, V> implements Map61B<K, V> {
    double loadFactor = 0.75;
    int initialSize = 16;
    int capa;
    int size;
    LinkedList<Node>[] buckets;
    HashSet<K> keyset;

    private static class Node<K, V> {
        K key;
        V value;
        Node next;
        public Node() {}
        public Node(K k, V v) {
            key = k;
            value = v;
        }
        public V get(K key) {
            return value;
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }
    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        keyset = new HashSet<>();
        buckets = new LinkedList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.loadFactor = loadFactor;
        size = 0;
        capa = initialSize;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        return keyset.contains(key);
    }

    @Override
    public void put(K key, V value) {
        K k = (K) key;
        V v =(V) value;
        if (containsKey(key)) {
            Node start = buckets[hash(key) % capa].getFirst();
            while (start != null) {
                if (start.key != k) {start = start.next; }
                else {
                    start.value = v;
                    break;
                }
            }
        }
        else {
            size++;
            keyset.add(k);
            buckets[hash(k) % capa].add(new Node(k, v));
            if (size / capa >= loadFactor) {
                resize();
            }
        }
    }

    @Override
    public V get(K key) {
            Node temp = null;
            for (int i = 0; i < buckets[hash(key) % capa].size(); i++) {
                if (buckets[hash(key) % capa].get(i).key.equals(key)) {
                    temp = buckets[hash(key) % capa].get(i);
                }
            }
            if (temp == null) {
                return null;
            }
            return (V) temp.value;
    }

    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            Node start = buckets[hash(key) % capa].getFirst();
            V val;
            Node sentinel = new Node();
            while (start.key != key) {
                sentinel = start;
                start = start.next;
            }
            val = (V) start.value;
            if (start.next == null) {
                buckets[hash(key) % capa].removeLast();
            }
            sentinel.next = start.next;
            return val;
        }
        return null;
    }

    @Override
    public void clear() {
        capa = 16;
        buckets = new LinkedList[16];
        for (int i = 0; i < capa; i++) {
            buckets[i] = new LinkedList<>();
        }
        keyset = new HashSet<>();
        size = 0;
    }

    public int hash(K key) {
        int h;
        return (key == null) ? 0 : (key.hashCode() & 0x7fffffff);
    }

    private void resize() {
        LinkedList<Node>[] newBuckets = new LinkedList[capa * 2];
        for (int i = 0; i < 2 * capa; i++) {
            newBuckets[i] = new LinkedList<>();
        }
        for (LinkedList<Node> list : buckets) {
            for (Node node : list) {
                newBuckets[hash((K) node.key) % (2 * capa)].add(node);
            }
        }

        buckets = newBuckets;
        capa = capa * 2;
    }

    public HashSet<K> keySet() {
        return keyset;
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


}
