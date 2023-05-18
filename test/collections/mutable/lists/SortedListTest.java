package collections.mutable.lists;

import static collections.CollectionTest.RANDOM;

import java.math.BigInteger;
import java.time.chrono.ChronoLocalDate;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

    @Test
    void testExhaustedIteratorThrowsException() {
        int capacity = RANDOM.nextInt(4) + 1;
        LocalDate[] dateArray = new LocalDate[capacity];
        LocalDate today = LocalDate.now();
        for (int i = 0; i < capacity; i++) {
            dateArray[i] = today.minusDays(i);
        }
        SortedList<ChronoLocalDate> dates = new SortedList<>(dateArray);
        Iterator<ChronoLocalDate> iterator = dates.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        Throwable t = assertThrows(NoSuchElementException.class, () -> {
            ChronoLocalDate badDate = iterator.next();
            System.out.println("Somehow exhausted iterator gave element "
                    + badDate.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testConstructorSortsOriginalElements() {
        int capacity = 12;
        BigInteger[] numbers = new BigInteger[capacity];
        for (int i = 0; i < capacity; i++) {
            numbers[i] = new BigInteger(72, RANDOM);
        }
        BigInteger[] expected = new BigInteger[capacity];
        System.arraycopy(numbers, 0, expected, 0, capacity);
        Arrays.sort(expected);
        SortedList<BigInteger> list = new SortedList<>(numbers);
        BigInteger[] actual = new BigInteger[capacity];
        Iterator<BigInteger> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            actual[index] = iterator.next();
            index++;
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testConstructorDoesNotChangeCallersArray() {
        int capacity = 12;
        BigInteger[] actual = new BigInteger[capacity];
        for (int i = 0; i < capacity; i++) {
            actual[i] = new BigInteger(72, RANDOM);
        }
        BigInteger[] expected = new BigInteger[capacity];
        System.arraycopy(actual, 0, expected, 0, capacity);
        SortedList<BigInteger> list = new SortedList<>(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testGetRejectsNegativeIndex() {
        int badIndex = -RANDOM.nextInt(1024) - 1;
        SortedList<ChronoLocalDate> dates = new SortedList<>();
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> {
            ChronoLocalDate badDate = dates.get(badIndex);
            System.out.println("Trying to access element at index " + badIndex
                    + " somehow gave " + badDate.toString());
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testNoIndexOfIfNotInList() {
        int capacity = 20;
        Integer[] evenNumbers = new Integer[capacity];
        for (int i = 0; i < capacity; i++) {
            evenNumbers[i] = 2 * i * RANDOM.nextInt();
        }
        SortedList<Integer> list = new SortedList<>(evenNumbers);
        int oddNumber = 2 * RANDOM.nextInt() + 1;
        String msg = "List of even numbers should not contain " + oddNumber;
        assert list.indexOf(oddNumber) < 0 : msg;
    }

    @Test
    void testGet() {
        System.out.println("get");
        int capacity = RANDOM.nextInt(32) + 8;
        BigInteger[] expected = new BigInteger[capacity];
        for (int i = 0; i < capacity; i++) {
            expected[i] = new BigInteger(72, RANDOM);
        }
        Arrays.sort(expected);
        SortedList<BigInteger> list = new SortedList<>(expected);
        for (int j = 0; j < capacity; j++) {
            BigInteger actual = list.get(j);
            assertEquals(expected[j], actual);
        }
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
