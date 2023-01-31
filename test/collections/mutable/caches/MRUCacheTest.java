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
