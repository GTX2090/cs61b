public class LinkedListDeque<T> implements Deque<T> {

    public static class DNode<T> {
        DNode next;
        DNode before;
        T val;

        public DNode(T j, DNode ne, DNode be) {
            this.val = j;
            this.next = ne;
            this.before = be;
        }
        public DNode(DNode ne, DNode be) {
            this.next = ne;
            this.before = be;
        }
        public DNode(T j) {
            this.val = j;
        }
        public DNode() { }
    }

    int size;
    DNode sentinel;
    public LinkedListDeque() {
        sentinel = new DNode();
        sentinel.next = sentinel;
        sentinel.before = sentinel;
        size = 0;
    }



    @Override
    public void addFirst(T item) {
        DNode temp = new DNode(item);
        temp.next = sentinel.next;
        sentinel.next.before = temp;
        sentinel.next = temp;
        temp.before = sentinel;
        size++;
    }

    @Override
    public void addLast(T item) {
        DNode temp = new DNode(item);
        temp.before = sentinel.before;
        temp.next = sentinel;
        sentinel.before.next = temp;
        sentinel.before = temp;
        size++;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        DNode temp = sentinel.next;
        while (temp != sentinel) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

    @Override
    public T removeFirst() {
        T k = (T) sentinel.next.val;
        sentinel.next.next.before = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return k;
    }

    @Override
    public T removeLast() {
        T k = (T) sentinel.before.val;
        sentinel.before.before.next = sentinel;
        sentinel.before = sentinel.before.before;
        size--;
        return k;
    }

    @Override
    public T get(int index) {
        DNode temp = sentinel;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return (T) temp.val;
    }


}
