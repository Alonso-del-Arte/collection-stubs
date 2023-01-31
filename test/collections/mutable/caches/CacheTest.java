package collections.mutable.caches;

import static collections.CollectionTest.RANDOM;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of the Cache&lt;N, V&gt; class.
 */
class CacheTest {

    @Test
    void testConstructorRejectsNegativeCapacity() {
        int badCapacity = -RANDOM.nextInt(256) - 1;
        String msg = "Should not be able to create cache with capacity "
                + badCapacity + " which is below MINIMUM_CAPACITY = "
                + Cache.MINIMUM_CAPACITY;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            CacheImpl badCache = new CacheImpl(badCapacity);
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
            CacheImpl badCache = new CacheImpl(badCapacity);
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
            CacheImpl badCache = new CacheImpl(badCapacity);
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
            CacheImpl badCache = new CacheImpl(badCapacity);
            System.out.println("Created " + badCache + " with capacity "
                    + badCapacity);
        }, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    private static class CacheImpl extends Cache<Integer, BigInteger> {

        private int[] names = new int[Cache.MINIMUM_CAPACITY];

        private BigInteger[] values = new BigInteger[Cache.MINIMUM_CAPACITY];

        private int index = 0;

        // TODO: Reevaluate after getting done with constructor tests
        @Override
        protected BigInteger create(Integer name) {
            BigInteger root = BigInteger.valueOf(name);
            return root.pow(name).pow(name);
        }

        // TODO: Reevaluate after getting done with constructor tests
        @Override
        protected boolean has(BigInteger value) {
            return false;
        }

        // TODO: Reevaluate after getting done with constructor tests
        @Override
        public BigInteger retrieve(Integer name) {
            return null;
        }

        // TODO: Reevaluate after getting done with constructor tests
        CacheImpl(int capacity) {
            super(capacity);
        }

    }

}
