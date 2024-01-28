package com.order.management.model.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.order.management.model.ListInfo;
import com.order.management.model.SortOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ResponseStatusDataDiffblueTest {
    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance() {
        Throwable exception = new Throwable();
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.CONTINUE, exception, listInfo);
        assertEquals("failed", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(100, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetInstance2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.HttpStatus.is4xxClientError()" because "httpStatus" is null
        //       at com.order.management.model.response.ResponseStatusData.getInstance(ResponseStatusData.java:56)
        //   See https://diff.blue/R013 to resolve this issue.

        Throwable exception = new Throwable();
        ResponseStatusData.getInstance(null, exception, new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC));
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance3() {
        Throwable exception = new Throwable();
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.SWITCHING_PROTOCOLS, exception,
                listInfo);
        assertEquals("failed", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(101, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance4() {
        Throwable exception = new Throwable();
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.PROCESSING, exception, listInfo);
        assertEquals("failed", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(102, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance5() {
        Throwable exception = new Throwable();
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.CHECKPOINT, exception, listInfo);
        assertEquals("failed", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(103, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance6() {
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.CONTINUE, null, listInfo);
        assertEquals("success", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(100, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance7() {
        Throwable exception = new Throwable();
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.OK, exception, listInfo);
        assertEquals("failed", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(200, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetInstance8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.HttpStatus.isError()" because "httpStatus" is null
        //       at com.order.management.model.response.ResponseStatusData.getInstance(ResponseStatusData.java:29)
        //   See https://diff.blue/R013 to resolve this issue.

        ResponseStatusData.getInstance(null, null, new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC));
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance9() {
        Throwable exception = new Throwable();
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.CREATED, exception, listInfo);
        assertEquals("failed", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(201, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance10() {
        Throwable exception = new Throwable();
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.ACCEPTED, exception, listInfo);
        assertEquals("failed", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(202, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance11() {
        Throwable exception = new Throwable();
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.NON_AUTHORITATIVE_INFORMATION,
                exception, listInfo);
        assertEquals("failed", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(203, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }

    /**
     * Method under test:
     * {@link ResponseStatusData#getInstance(HttpStatus, Throwable, ListInfo)}
     */
    @Test
    void testGetInstance12() {
        Throwable exception = new Throwable();
        ListInfo listInfo = new ListInfo(1, 3L, 3, 3, "Sort Field", SortOrder.ASC);

        ResponseStatusData actualInstance = ResponseStatusData.getInstance(HttpStatus.NO_CONTENT, exception, listInfo);
        assertEquals("failed", actualInstance.getStatus());
        assertEquals(1, actualInstance.getMessages().size());
        assertEquals(204, actualInstance.getStatusCode());
        assertSame(listInfo, actualInstance.getListInfo());
    }
}
