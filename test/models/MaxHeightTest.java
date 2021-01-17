package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxHeightTest {

    @Test
    public void testEqualsAndHashCode() {
        MaxHeight maxHeight1 = new MaxHeight(5);
        MaxHeight maxHeight2 = new MaxHeight(5);

        assertEquals(maxHeight1, maxHeight1);
        assertEquals(maxHeight2, maxHeight2);

        assertNotEquals(maxHeight1, new MinHeight(5));
        assertNotEquals(maxHeight2, new MinHeight(5));

        assertFalse(maxHeight1.equals(null));
        assertFalse(maxHeight2.equals(null));

        assertFalse(maxHeight1.equals(new MaxHeight(10)));
        assertFalse(maxHeight2.equals(new MaxHeight(10)));

        assertTrue(maxHeight1.equals(maxHeight2) && maxHeight2.equals(maxHeight1));
        assertEquals(maxHeight1.hashCode(), maxHeight2.hashCode());
    }
}
