package bearmaps;
import org.junit.Ignore;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private int size;
    private HashMap<T, Integer> hs;
    private ArrayList<ArrayHeapMinPQ.PriorityNode> pq;

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    public ArrayHeapMinPQ() {
        pq = new ArrayList<>();
        hs = new HashMap<>();
        size = 0;
    }

    @Override
    public void add(T item, double priority) {
        if (size == 0) {
            pq.add(new PriorityNode(item, priority));
            hs.put(item, 0);
            size++;
        }
        else {
            if (contains(item)) {
                throw new IllegalArgumentException("Existing item");
            }
            else {
                hs.put(item, size);
                PriorityNode pn = new PriorityNode(item, priority);
                pq.add(pn);
                size++;
                int temp = size - 1;
                while (temp > 0) {
                    int k = pn.compareTo(pq.get((int)(temp - 1)/2));
                    if (k >= 0) {
                        break;
                    }
                    else {
                        Collections.swap(pq, temp, (int)(temp - 1)/2);
                        hs.put(item, (temp - 1)/2);
                        hs.put((T) pq.get((temp - 1)/2).getItem(), temp);
                        temp = (temp - 1)/2;
                    }
                }
            }
        }
    }

    @Override
    public boolean contains(T item) {
        return hs.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size == 0) {throw new NoSuchElementException("No Such Element"); };
        return (T) pq.get(0).getItem();
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {throw new NoSuchElementException(); }
        size--;
        T ans = (T) pq.get(0).getItem();
        Collections.swap(pq, 0, size);
        hs.put((T) pq.get(0).getItem(), 0);
        T it = (T) pq.get(0).getItem();
        pq.remove(size);
        hs.remove(ans);
        int i = 0;
        while (i < size - 1) {
            int left = (i + 1) * 2 - 1;
            int right = (i + 1) * 2 ;
            if (left >= size && right >= size) {
                break;
            }
            if (right >= size) {
                if (pq.get(i).compareTo(pq.get(left)) > 0) {
                    hs.put(it, left);
                    hs.put((T) pq.get(left).getItem(), i);
                    Collections.swap(pq, i, left);
                    break;
                }
                else {break; }
            }
            else {
                int t1 = pq.get(i).compareTo(pq.get(left));
                int t2 = pq.get(i).compareTo(pq.get(right));
                if (t1 > 0 && t2 > 0) {
                    int km = pq.get(left).compareTo(pq.get(right));
                    if (km <= 0) {
                        hs.put((T) it, left);
                        hs.put((T) pq.get(left).getItem(), i);
                        Collections.swap(pq, i, left);
                        i = left;
                    }
                    else {
                        hs.put((T) it, right);
                        hs.put((T) pq.get(right).getItem(), i);
                        Collections.swap(pq, i, right);
                        i = right;
                    }
                }
                else if (t1 > 0) {
                    hs.put((T) it, left);
                    hs.put((T) pq.get(left).getItem(), i);
                    Collections.swap(pq, i, left);
                    i = left;
                }
                else if (t2 > 0){
                    hs.put((T) it, right);
                    hs.put((T) pq.get(right).getItem(), i);
                    Collections.swap(pq, i, right);
                    i = right;
                }
                else {
                    break;
                }
            }
        }
        return ans;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item) || size == 0) {
            throw new NoSuchElementException();
        }
        int no = hs.get(item);
        double orig = pq.get(no).getPriority();
        pq.get(no).setPriority(priority);
        if (orig < priority) {
            int i = no;
            while (i < size - 1) {
                int left = (i + 1) * 2 - 1;
                int right = (i + 1) * 2 ;
                if (left >= size && right >= size) {
                    break;
                }
                if (right >= size) {
                    if (pq.get(i).compareTo(pq.get(left)) > 0) {
                        hs.put((T) pq.get(i).getItem(), left);
                        hs.put((T) pq.get(left).getItem(), i);
                        Collections.swap(pq, i, left);
                        break;
                    }
                    else {break; }
                }
                else {
                    int t1 = pq.get(i).compareTo(pq.get(left));
                    int t2 = pq.get(i).compareTo(pq.get(right));
                    if (t1 > 0 && t2 > 0) {
                        int km = pq.get(left).compareTo(pq.get(right));
                        if (km <= 0) {
                            hs.put((T) pq.get(i).getItem(), left);
                            hs.put((T) pq.get(left).getItem(), i);
                            Collections.swap(pq, i, left);
                            i = left;
                        }
                        else {
                            hs.put((T) pq.get(i).getItem(), right);
                            hs.put((T) pq.get(right).getItem(), i);
                            Collections.swap(pq, i, right);
                            i = right;
                        }
                    }
                    else if (t1 > 0) {
                        hs.put((T) pq.get(i).getItem(), left);
                        hs.put((T) pq.get(left).getItem(), i);
                        Collections.swap(pq, i, left);
                        i = left;
                    }
                    else if (t2 > 0){
                        hs.put((T) pq.get(i).getItem(), right);
                        hs.put((T) pq.get(right).getItem(), i);
                        Collections.swap(pq, i, right);
                        i = right;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        else {
            int temp = no;
            while (temp > 0) {
                PriorityNode pn = new PriorityNode(item, priority);
                int k = pn.compareTo(pq.get((int)(temp - 1)/2));
                if (k >= 0) {
                    break;
                }
                else {
                    Collections.swap(pq, temp, (int)(temp - 1)/2);
                    hs.put(item, (temp - 1)/2);
                    hs.put((T) pq.get((temp - 1)/2).getItem(), temp);
                    temp = (temp - 1)/2;
                }
            }
        }
    }
}
