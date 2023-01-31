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

    @Test
    void testCacheRetainsValueWhileCapacityAvailable() {
        LRUCacheImpl cache = new LRUCacheImpl(Cache.MINIMUM_CAPACITY);
        String timeName = "^(?:\\d|[01]\\d|2[0-3]):[0-5]\\d$";
        Pattern expected = cache.retrieve(timeName);
        for (int i = 1; i < Cache.MINIMUM_CAPACITY; i++) {
            String fillerName = "^" + i + "*$";
            cache.retrieve(fillerName);
        }
        Pattern actual = cache.retrieve(timeName);
        assertEquals(expected, actual);
    }

    @Test
    void testHas() {
        System.out.println("has");
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String ssnName = "\\d{3}-\\d{2}-\\d{4}";
        Pattern pattern = cache.retrieve(ssnName);
        String msg = "After adding pattern " + pattern.toString()
                + " to cache, cache should still have it";
        assert cache.has(pattern) : msg;
    }

    @Test
    void testDoesNotHave() {
        LRUCacheImpl cache = new LRUCacheImpl(DEFAULT_SIZE);
        String numberName = "([-+]?\\d*)";
        Pattern pattern = Pattern.compile(numberName);
        String msg = "New cache should not have " + pattern
                + " or any other value";
        assert !cache.has(pattern) : msg;
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