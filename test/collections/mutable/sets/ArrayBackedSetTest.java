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
        assert set.add(dateTime) : msg;
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

}
