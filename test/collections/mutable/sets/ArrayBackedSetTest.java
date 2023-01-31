package collections.mutable.sets;

import java.math.BigInteger;
import java.sql.Clob;
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

    // TODO: Rewind remove() to failing and break this test up into smaller
    //  tests
    @Test
    void testRemove() {
        System.out.println("remove");
        int capacity = RANDOM.nextInt(64) + 16;
        ArrayBackedSet<Integer> set = new ArrayBackedSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            set.add(i);
        }
        int markedNumber = RANDOM.nextInt(capacity);
        boolean opResult = set.remove(markedNumber);
        String msg = "Should be able to remove " + markedNumber
                + " from set containing numbers 0 to " + (capacity - 1);
        assert opResult : msg;
        assert !set.contains(markedNumber);
        int expected = capacity - 1;
        int actual = set.size();
        assertEquals(expected, actual);
        for (int j = 0; j < capacity; j++) {
            if (j != markedNumber) {
                String stillPresentMsg = "After removing " + markedNumber
                        + ", set should still contain " + j;
                assert set.contains(j) : stillPresentMsg;
            }
        }
    }

    @Test
    void testIsEmpty() {
        System.out.println("isEmpty");
        ArrayBackedSet<Clob> set = new ArrayBackedSet<>();
        String msg = "Newly created set should be empty";
        assert set.isEmpty() : msg;
    }

    @Test
    void testIsNotEmpty() {
        ArrayBackedSet<LocalDateTime> set = new ArrayBackedSet<>();
        LocalDateTime now = LocalDateTime.now();
        set.add(now);
        String msg = "After adding " + now + " to set, it should not be empty";
        assert !set.isEmpty() : msg;
    }

    @Test
    void testClear() {
        System.out.println("clear");
        int capacity = RANDOM.nextInt(64) + 16;
        ArrayBackedSet<BigInteger> set = new ArrayBackedSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            set.add(number);
        }
        String preMsg = "Set with " + set.size()
                + " elements should not be empty";
        assert !set.isEmpty() : preMsg;
        set.clear();
        String msg = "After calling clear, set should be empty";
        assert set.isEmpty() : msg;
        assertEquals(0, set.size(), msg);
    }

    @Test
    void testConstructorRejectsInitialCapacityOne() {
        int badCapacity = 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            ArrayBackedSet<Clob> set = new ArrayBackedSet<>(badCapacity);
            System.out.println("Should not have been able to create " + set
                    + " with initial capacity " + badCapacity);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsInitialCapacityZero() {
        int badCapacity = 0;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            ArrayBackedSet<Clob> set = new ArrayBackedSet<>(badCapacity);
            System.out.println("Should not have been able to create " + set
                    + " with initial capacity " + badCapacity);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorRejectsInitialCapacityNegative() {
        int badCapacity = -RANDOM.nextInt(8192) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            ArrayBackedSet<Clob> set = new ArrayBackedSet<>(badCapacity);
            System.out.println("Should not have been able to create " + set
                    + " with initial capacity " + badCapacity);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
