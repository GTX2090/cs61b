package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer x = new ArrayRingBuffer(4);
        x.dequeue();
        x.enqueue(33.1);
        x.enqueue(44.8);
        x.enqueue(62.3);
        x.enqueue(-3.4);
        x.enqueue(-3.4);

        ArrayRingBuffer x2 = new ArrayRingBuffer(4);
        x2.enqueue(33.1);
        x2.enqueue(44.8);
        x2.enqueue(62.3);
        assertFalse(x.equals(x2));
    }
}
