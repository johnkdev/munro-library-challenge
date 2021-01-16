package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortTest {

    @Test
    public void testHeightInMetersAscending() {
        Sort sort = Sort.HEIGHT_IN_METERS_ASC;

        assertEquals(Sort.HEIGHT_IN_METERS_ASC, sort);

        assertNotEquals(Sort.HEIGHT_IN_METERS_DESC, sort);
        assertNotEquals(Sort.NAME_ASC, sort);
        assertNotEquals(Sort.NAME_DESC, sort);
    }

    @Test
    public void testHeightInMetersDescending() {
        Sort sort = Sort.HEIGHT_IN_METERS_DESC;

        assertEquals(Sort.HEIGHT_IN_METERS_DESC, sort);

        assertNotEquals(Sort.HEIGHT_IN_METERS_ASC, sort);
        assertNotEquals(Sort.NAME_ASC, sort);
        assertNotEquals(Sort.NAME_DESC, sort);
    }

    @Test
    public void testNameAscending() {
        Sort sort = Sort.NAME_ASC;

        assertEquals(Sort.NAME_ASC, sort);

        assertNotEquals(Sort.HEIGHT_IN_METERS_ASC, sort);
        assertNotEquals(Sort.HEIGHT_IN_METERS_DESC, sort);
        assertNotEquals(Sort.NAME_DESC, sort);
    }

    @Test
    public void testNameDescending() {
        Sort sort = Sort.NAME_DESC;

        assertEquals(Sort.NAME_DESC, sort);

        assertNotEquals(Sort.HEIGHT_IN_METERS_ASC, sort);
        assertNotEquals(Sort.HEIGHT_IN_METERS_DESC, sort);
        assertNotEquals(Sort.NAME_ASC, sort);
    }

    @Test
    public void testToValidSort() {
        assertEquals(Sort.fromQueryParameterValue("heightInMeters asc"), Sort.HEIGHT_IN_METERS_ASC);
        assertEquals(Sort.fromQueryParameterValue("heightInMeters desc"), Sort.HEIGHT_IN_METERS_DESC);
        assertEquals(Sort.fromQueryParameterValue("name asc"), Sort.NAME_ASC);
        assertEquals(Sort.fromQueryParameterValue("name desc"), Sort.NAME_DESC);
    }

    @Test()
    public void testToInvalidSort() {
        assertNull(Sort.fromQueryParameterValue(""));
        assertNull(Sort.fromQueryParameterValue("streetmap desc"));
    }
}
