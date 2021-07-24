package es.datastructur.synthesizer;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer array with zeros.
        int k = (int) Math.round(SR/frequency);
        this.buffer = new ArrayRingBuffer<>(k);
        for (int i=0; i < k; i++) {
            this.buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < this.buffer.capacity(); i++) {
            double r = Math.random() - 0.5;
            this.buffer.dequeue();
            this.buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double temp1 = this.buffer.dequeue();
        double temp2 = this.buffer.peek();
        double temp = (temp1 + temp2) * 0.5 * 0.996;
        this.buffer.enqueue(temp);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return this.buffer.peek();
    }
}
