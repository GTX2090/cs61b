package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    public int capacity() {
        return this.rb.length;
    }
    public int fillCount() {
        return this.fillCount;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.rb = (T[]) new Object[capacity];
    }

    private class ArrayRingIterator implements Iterator<T> {
        private int Pos;
        public ArrayRingIterator() {Pos = first; }
        public boolean hasNext() {return Pos < fillCount; }
        public T next() {
            int temp = Pos;
            if (Pos >= capacity()) {
                temp = temp - capacity();
            }
            T returnitem = rb[temp];
            Pos++;
            return returnitem;
        }
    }
    public Iterator<T> iterator() {
        return new ArrayRingIterator();
    }


    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (this.fillCount == this.capacity()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last++;
        if (last >= this.capacity()) {
            last = 0;
        }
        this.fillCount++;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public boolean equals(Object o) {
        ArrayRingBuffer xx = (ArrayRingBuffer) o;
        int x1 = xx.fillCount;
        if (x1 != this.fillCount) {return false; }
        Iterator<T> aseer = xx.iterator();
        Iterator<T> aseer2 = this.iterator();
        while (aseer.hasNext()) {
            if (!aseer.next().equals(aseer2.next())) {
                return false;
            }
        }
        return true;
    }

    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (this.fillCount <= 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T deq = rb[first];
        first++;
        if (first >= this.capacity()) {
            first = 0;
        }
        this.fillCount--;
        return deq;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
