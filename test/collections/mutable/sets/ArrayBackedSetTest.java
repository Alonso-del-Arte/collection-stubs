package collections.mutable.sets;

import java.math.BigInteger;
import java.sql.NClob;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static collections.CollectionTest.RANDOM;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBackedSetTest {

    @Test
    void testAdd() {
        System.out.println("add");
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        LocalDateTime dateTime = LocalDateTime.now();
        String msg = "Should be able to add " + dateTime + " to set of times";
        boolean opResult = set.add(dateTime);
        assert opResult : msg;
    }

    @Test
    void testAddRejectsNull() {
        ArrayBackedSet<NClob> set = new ArrayBackedSet<>();
        boolean opResult = set.add(null);
        String msg = "Should not have been able to add null to set";
        assert !opResult : msg;
    }

    @Test
    void testContains() {
        System.out.println("contains");
        ArrayBackedSet<BigInteger> set = new ArrayBackedSet<>();
        BigInteger number = new BigInteger(72, RANDOM);
        set.add(number);
        String msg = "After adding " + number
                + " to set, set should contain that element";
        assert set.contains(number) : msg;
    }

    @Test
    void testDoesNotContain() {
        int capacity = RANDOM.nextInt(12) + 4;
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>(capacity);
        LocalDateTime curr = LocalDateTime.now();
        for (int i = 0; i < capacity; i++) {
            set.add(curr.minusHours(i));
        }
        LocalDateTime futureTime = curr.plusMonths(18);
        String msg = "Set of times in the past should not contain "
                + futureTime;
        assert !set.contains(futureTime) : msg;
    }

    @Test
    void testSize() {
        int capacity = RANDOM.nextInt(16) + 4;
        ArrayBackedSet<Integer> set = new ArrayBackedSet<>(capacity);
        for (int expected = 0; expected < capacity; expected++) {
            int actual = set.size();
            assertEquals(expected, actual);
            set.add(expected);
        }
    }

    @Test
    void testNoDuplicateElements() {
        ArrayBackedSet<String> set = new ArrayBackedSet<>();
        String msg = "Should not be able to add same element twice";
        set.add(msg);
        int expected = set.size();
        boolean reAddFlag = set.add(msg);
        assert !reAddFlag : msg;
        int actual = set.size();
        assertEquals(expected, actual);
    }

    @Test
    void testAddCanExpandCapacity() {
        int capacity = RANDOM.nextInt(16) + 4;
        ArrayBackedSet<BigInteger> set = new ArrayBackedSet<>(capacity);
        BigInteger start = new BigInteger(84, RANDOM);
        BigInteger stop = start.add(BigInteger.valueOf(capacity));
        for (BigInteger n = start;
             n.compareTo(stop) < 0;
             n = n.add(BigInteger.ONE)) {
            set.add(n);
        }
        String msg = "Set filled to initial capacity " + capacity
                + " should expand for one more element";
        assertDoesNotThrow(() -> {
            set.add(stop);
        }, msg);
    }

    @Test
    void testNoRemoveFromEmptySet() {
        LocalDateTime time = LocalDateTime.now();
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        boolean opResult = set.remove(time);
        String msg = "Should not be able to remove " + time + " from empty set";
        assert !opResult : msg;
    }

}
