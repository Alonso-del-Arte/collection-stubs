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
        String msg = "List " + list + " should not have changed "
                + Arrays.toString(expected);
        assertArrayEquals(expected, actual, msg);
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

    @Test
    void testIndexOfForNullCausesException() {
        String[] words = {"Example", "Examples"};
        SortedList<String> list = new SortedList<>(words);
        Throwable t = assertThrows(NullPointerException.class, () -> {
            int badIndex = list.indexOf(null);
            System.out.println("Null said to be at index " + badIndex);
        });
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    void testIndexOf() {
        System.out.println("indexOf");
        int capacity = RANDOM.nextInt(64) + 16;
        Integer[] numbers = new Integer[capacity];
        for (int i = 0; i < capacity; i++) {
            numbers[i] = i;
        }
        SortedList<Integer> list = new SortedList<>(numbers);
        for (int expected = 0; expected < capacity; expected++) {
            int actual = list.indexOf(expected);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testIndexOfIsEfficient() {
        int capacity = 1024;
        WrappedInteger[] numbers = new WrappedInteger[capacity];
        for (int i = 0; i < capacity; i++) {
            numbers[i] = new WrappedInteger(i);
        }
        SortedList<WrappedInteger> list = new SortedList<>(numbers);
        WrappedInteger.equalsCallCount = 0;
        WrappedInteger.compareToCallCount = 0;
        int expected = capacity / 2 + RANDOM.nextInt(capacity / 8) + 1;
        WrappedInteger element = new WrappedInteger(expected);
        int actual = list.indexOf(element);
        int callCount = WrappedInteger.equalsCallCount;
        assertEquals(expected, actual);
        int maximumAcceptableCalls = capacity / 4;
        String msg = "indexOf() should've found element at index " + expected
                + " out of " + capacity + " with fewer than "
                + maximumAcceptableCalls + " equals() calls, it took "
                + callCount + " calls";
        System.out.println(msg);
        assert callCount <= maximumAcceptableCalls : msg;
        int compareToCallCount = WrappedInteger.compareToCallCount;
        String compareMsg = "indexOf() should've found element at index "
                + expected + " out of " + capacity + " with fewer than "
                + maximumAcceptableCalls + " compareTo() calls, it took "
                + compareToCallCount + " calls";
        System.out.println(compareMsg);
        assert compareToCallCount <= maximumAcceptableCalls : compareMsg;
    }

    @Test
    void testIndexOfForAbsentElementSuggestsInsertionPoint() {
        int capacity = RANDOM.nextInt(16) + 4;
        Integer[] evens = new Integer[capacity];
        for (int i = 0; i < capacity; i++) {
            evens[i] = 2 * RANDOM.nextInt();
        }
        SortedList<Integer> list = new SortedList<>(evens);
        int odd = evens[1] + 2 * RANDOM.nextInt(65536) + 1;
        int expected = Arrays.binarySearch(evens, odd);
        int actual = list.indexOf(odd);
        String msg = "Expecting indexOf for " + odd
                + " in list of even numbers to be less than -1";
        assertEquals(expected, actual, msg);
    }

//    @Test
    void testListCanExpandCapacityAndMaintainSort() {
        int capacity = RANDOM.nextInt(16) + 4;
        Integer[] numbers = new Integer[capacity];
        for (int i = 0; i < capacity; i++) {
            numbers[i] = 2 * RANDOM.nextInt();
        }
        SortedList<Integer> list = new SortedList<>(numbers);
        int twiceCapacity = 2 * capacity;
        Integer[] expected = new Integer[2 * twiceCapacity];
        System.arraycopy(numbers, 0, expected, 0, capacity);
        for (int j = capacity; j < twiceCapacity; j++) {
            expected[j] = numbers[j - capacity] * RANDOM.nextInt() + 1;
            list.add(expected[j]);
        }
        Arrays.sort(expected);
        fail("FINISH WRITING TEST");
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
