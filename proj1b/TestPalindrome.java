import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testWordToDeque2() {
        Deque d = palindrome.wordToDeque("");
        String actual = "";
        for (int i = 0; i < "".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("", actual);
    }

    @Test
    public void testisDeque() {

        assertTrue(palindrome.isPalindrome("laal"));
        assertTrue(palindrome.isPalindrome("lapal"));
        assertTrue(palindrome.isPalindrome("l"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("laalp"));
        assertFalse(palindrome.isPalindrome("lapbl"));
    }

    @Test
    public void testoneoff() {
        assertTrue(palindrome.isPalindrome("l", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertFalse(palindrome.isPalindrome("laal", offByOne));
    }


}