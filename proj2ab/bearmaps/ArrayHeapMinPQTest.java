package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void test1() {
        ArrayHeapMinPQ<Integer> mpq = new ArrayHeapMinPQ<>();
        mpq.add(1, 2.0);
        mpq.add(2, 3.0);
        mpq.add(4, 1.0);
        mpq.add(6, 0.0);
        int k = mpq.removeSmallest();
        assertEquals(k, 6);
        k = mpq.removeSmallest();
        assertEquals(k, 4);

        k = mpq.getSmallest();
        assertEquals(k, 1);
        mpq.changePriority(1, 4.0);
        k = mpq.getSmallest();
        assertEquals(k, 2);
    }
}
