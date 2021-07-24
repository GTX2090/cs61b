import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offByN = new OffByN(5);
    // Your tests go here.
    @Test
    public void testoffbyone() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('c', 'b'));
        assertFalse(offByOne.equalChars('a', 'd'));
    }

    @Test
    public void testoffbyN() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'a'));
        assertFalse(offByN.equalChars('a', 'b'));
    }



}