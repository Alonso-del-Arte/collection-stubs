package collections.mutable.lists;

import static collections.CollectionTest.RANDOM;

import java.time.chrono.ChronoLocalDate;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SortedListTest {

    @Test
    void testNewListShouldBeEmpty() {
        SortedList<Date> list = new SortedList<>();
        String msg = "Newly created list with no elements should be empty";
        assert list.isEmpty() : msg;
    }

    @Test
    void testNoIndexOfForAnyElementInEmptyList() {
        SortedList<ChronoLocalDate> list = new SortedList<>();
        ChronoLocalDate date = LocalDate.now();
        String msg = date + " should not be in empty list of dates";
        assert list.indexOf(date) < 0 : msg;
    }

    @Test
    void testCanAddToList() {
        SortedList<String> list = new SortedList<>();
        String msg = "Should have been able to add this message to the list";
        boolean opResult = list.add(msg);
        assert opResult : msg;
    }

    @Test
    void testIterator() {
        System.out.println("iterator");
        int capacity = RANDOM.nextInt(16) + 64;
        LocalDate[] oneDateSeveralTimes = new LocalDate[capacity];
        LocalDate expected = LocalDate.now();
        Arrays.fill(oneDateSeveralTimes, expected);
        SortedList<ChronoLocalDate> list
                = new SortedList<>(oneDateSeveralTimes);
        Iterator<ChronoLocalDate> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            ChronoLocalDate actual = iterator.next();
            assertEquals(expected, actual);
            index++;
        }
        String msg = "Iterator should have given all " + capacity + " elements";
        assertEquals(capacity, index, msg);
    }

    private static final class WrappedInteger
            implements Comparable<WrappedInteger> {

        private static int equalsCallCount = 0;

        private static int compareToCallCount = 0;

        private final int wrapped;

        @Override
        public boolean equals(Object obj) {
            equalsCallCount++;
            if (obj instanceof WrappedInteger) {
                return this.wrapped == ((WrappedInteger) obj).wrapped;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return this.wrapped;
        }

        @Override
        public int compareTo(WrappedInteger other) {
            compareToCallCount++;
            return this.wrapped - other.wrapped;
        }

        WrappedInteger(int n) {
            this.wrapped = n;
        }

    }

}
