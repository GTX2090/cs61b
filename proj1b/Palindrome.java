public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ans = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            ans.addLast(word.charAt(i));
        }
        return ans;
    }

    static Palindrome palindrome = new Palindrome();

    public boolean isPalindrome(String word) {
        Deque<Character> de = palindrome.wordToDeque(word);
        if (de.size() <= 1) {
            return true;
        }
        while (de.size() > 1) {
            Character a1 = de.removeFirst();
            Character a2 = de.removeLast();
            if (a1 != a2) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> de = palindrome.wordToDeque(word);
        while (de.size() > 1) {
            Character a1 = de.removeFirst();
            Character a2 = de.removeLast();
            if (cc.equalChars(a1, a2) == false) {
                return false;
            }
        }
        return true;
    }


}
