import org.junit.*;
import static org.junit.Assert.*;
public class testUF {
    @Test
    public void testUF() {
        UnionFind uf = new UnionFind(10);
        int t1 = uf.sizeOf(1);
        uf.connect(1,2);
        uf.connect(3,2);
        uf.connect(5,6);
        uf.connect(6,7);
        uf.connect(5,8);
        uf.connect(2,8);
        assertTrue(uf.isConnected(2,8));
        assertFalse(uf.isConnected(2,9));
    }
}
