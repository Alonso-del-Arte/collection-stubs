package collections.mutable.caches;

import static collections.CollectionTest.RANDOM;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of the LRUCache&lt;N, V&gt; class.
 */
class LRUCacheTest {

    private static final int DEFAULT_SIZE = 7;

    @Test
    void testAddToCache() {
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String expected = "^[a-zA-Z0–9+_.-]+@[a-zA-Z0–9.-]+$";
        String actual = cache.retrieve(expected).pattern();
        assertEquals(expected, actual);
    }

    @Test
    void testRetrieve() {
        System.out.println("retrieve");
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String romanName = "^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";
        Pattern expected = cache.retrieve(romanName);
        Pattern actual = cache.retrieve(romanName);
        assertEquals(expected, actual);
    }

    private static class LRUCacheImpl extends LRUCache<String, Pattern> {

        int createCallCount = 0;

        @Override
        protected Pattern create(String name) {
            createCallCount++;
            return Pattern.compile(name);
        }

        LRUCacheImpl(int capacity) {
            super(capacity);
        }

    }

}
