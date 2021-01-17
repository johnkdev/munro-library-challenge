package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinHeightTest {

    @Test
    public void testEqualsAndHashCode() {
        MinHeight minHeight1 = new MinHeight(5);
        MinHeight minHeight2 = new MinHeight(5);

        assertEquals(minHeight1, minHeight1);
        assertEquals(minHeight2, minHeight2);

        assertNotEquals(minHeight1, new MaxHeight(5));
        assertNotEquals(minHeight2, new MaxHeight(5));

        assertFalse(minHeight1.equals(null));
        assertFalse(minHeight2.equals(null));

        assertFalse(minHeight1.equals(new MinHeight(10)));
        assertFalse(minHeight2.equals(new MinHeight(10)));

        assertTrue(minHeight1.equals(minHeight2) && minHeight2.equals(minHeight1));
        assertEquals(minHeight1.hashCode(), minHeight2.hashCode());
    }
}
