package collections.mutable.lists;

import static collections.CollectionTest.RANDOM;

import java.time.chrono.ChronoLocalDate;
import java.time.LocalDate;
import java.util.Date;

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

}
