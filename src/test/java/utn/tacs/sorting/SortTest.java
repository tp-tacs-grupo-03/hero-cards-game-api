package utn.tacs.sorting;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import utn.tacs.sorting.exceptions.SortingException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

class SortTest {

    @Test
    public void testConstructor() throws SortingException {
        Sort sort = new Sort("id", "asc");
        Assert.assertEquals(sort.getSortField(), SortField.ID);
        assertTrue(sort.isAsc());

        Sort sortName = new Sort("name", "desc");
        Assert.assertEquals(sortName.getSortField(), SortField.NAME);
        Assert.assertFalse(sortName.isAsc());

        Sort sortStatus = new Sort("status", "desc");
        Assert.assertEquals(sortStatus.getSortField(), SortField.STATUS);
        Assert.assertFalse(sortStatus.isAsc());

        Sort sortDate = new Sort("date", "desc");
        Assert.assertEquals(sortDate.getSortField(), SortField.DATE);
        Assert.assertFalse(sortDate.isAsc());
    }

    @Test
    public void whenExceptionThrownFieldIsWrong() {
        Exception exception = assertThrows(SortingException.class, () -> {
            new Sort("pepe", "desc");
        });

        String expectedMessage = "Invalid field value";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenExceptionThrownOrderByIsWrong() {
        Exception exception = assertThrows(SortingException.class, () -> {
            new Sort("id", "pipi");
        });

        String expectedMessage = "Invalid sort Direction value";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
