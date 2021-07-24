import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    Node<K, V> root;
    int size;

    private static class Node<K, V> {
        Node<K, V> left = null;
        Node<K, V> right = null;
        K key;
        V val;
        private Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public BSTMap() {
        this.size = 0;
        this.root = null;
    }
    public BSTMap(K key, V val) {
        this.size = 1;
        this.root = new Node<>(key, val);
    }
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (this.root == null) {return false; }
        Node<K, V> h = this.root;
        int cmp;
        while (h != null) {
            cmp = h.key.compareTo(key);
            if (cmp == 0) {
                return true;
            }
            else if (cmp > 0) {
                h = h.left;
            }
            else {
                h = h.right;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (this.root == null) {return null; }
        Node<K, V> h = this.root;
        int cmp;
        while (h != null) {
            cmp = h.key.compareTo(key);
            if (cmp == 0) {
                return h.val;
            }
            else if (cmp > 0) {
                h = h.left;
            }
            else {
                h = h.right;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if (!this.containsKey(key)) {this.size++; }
        if (this.root == null) {
            this.root = new Node<>(key, value);
            return;
        }
        Node<K, V> h = this.root;
        Node<K, V> sentinel = this.root;
        int cmp = h.key.compareTo(key);
        while (h != null) {
            sentinel = h;
            cmp = h.key.compareTo(key);
            if (cmp == 0) {
                h.val = value;
                return;
            }
            else if (cmp > 0) {
                h = h.left;
            }
            else {
                h = h.right;
            }
        }
        if (cmp > 0) {
            sentinel.left = new Node<>(key, value);
        }
        else {
            sentinel.right = new Node<>(key, value);
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("not supported method");
    }
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("not supported method");
    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("not supported method");
    }
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("not supported method");
    }

    public void printInOrder() {
        inOrder(this.root);
    }

    public void inOrder(Node<K, V> n) {
        if (n == null) {return; }
        inOrder(n.left);
        System.out.println(n.val);
        inOrder(n.right);
    }
    public static void main(String[] args) {
        System.out.println("star".compareTo("KISS"));
    }
}
