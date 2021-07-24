public class OffByN implements CharacterComparator {

    int n;
    public OffByN(int k) {
        this.n = k;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return (x - y == this.n) || (x - y == -this.n);
    }
}
