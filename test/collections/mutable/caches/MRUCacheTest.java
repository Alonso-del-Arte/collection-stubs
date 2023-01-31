package collections.mutable.caches;

import static collections.CollectionTest.RANDOM;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of the MRUCache&lt;N, V&gt; class.
 */
class MRUCacheTest {

    private static final int DEFAULT_SIZE = 7;

    // TODO: Write tests here

    @Test
    void testConstructorRejectsNegativeCapacity() {
        int badCapacity = -RANDOM.nextInt(256) - 1;
        String msg = "Should not be able to create cache with capacity "
                + badCapacity + " which is below MINIMUM_CAPACITY = "
                + Cache.MINIMUM_CAPACITY;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            MRUCacheImpl badCache = new MRUCacheImpl(badCapacity);
            System.out.println("Created " + badCache + " with capacity "
                    + badCapacity);
        }, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsCapacityZero() {
        int badCapacity = 0;
        String msg = "Should not be able to create cache with capacity "
                + badCapacity + " which is below MINIMUM_CAPACITY = "
                + Cache.MINIMUM_CAPACITY;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            MRUCacheImpl badCache = new MRUCacheImpl(badCapacity);
            System.out.println("Created " + badCache + " with capacity "
                    + badCapacity);
        }, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsLowCapacity() {
        int badCapacity = RANDOM.nextInt(Cache.MINIMUM_CAPACITY);
        String msg = "Should not be able to create cache with capacity "
                + badCapacity + " which is below MINIMUM_CAPACITY = "
                + Cache.MINIMUM_CAPACITY;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            MRUCacheImpl badCache = new MRUCacheImpl(badCapacity);
            System.out.println("Created " + badCache + " with capacity "
                    + badCapacity);
        }, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsExcessiveCapacity() {
        int badCapacity = Cache.MAXIMUM_CAPACITY + RANDOM.nextInt(256) + 1;
        String msg = "Should not be able to create cache with capacity "
                + badCapacity + " which is beyond MAXIMUM_CAPACITY = "
                + Cache.MAXIMUM_CAPACITY;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            MRUCacheImpl badCache = new MRUCacheImpl(badCapacity);
            System.out.println("Created " + badCache + " with capacity "
                    + badCapacity);
        }, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    // TODO: Look for a better MRU cache use case than <String, Pattern>
    private static class MRUCacheImpl extends MRUCache<String, Pattern> {

        int createCallCount = 0;

        @Override
        protected Pattern create(String name) {
            createCallCount++;
            return Pattern.compile(name);
        }

        MRUCacheImpl(int capacity) {
            super(capacity);
        }

    }

}
