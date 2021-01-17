package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class LimitTest {

    @Test
    public void testEqualsAndHashCode() {
        Limit limit1 = new Limit(5);
        Limit limit2 = new Limit(5);

        assertEquals(limit1, limit1);
        assertEquals(limit2, limit2);

        assertNotEquals(limit1, new MaxHeight(5));
        assertNotEquals(limit2, new MaxHeight(5));

        assertFalse(limit1.equals(null));
        assertFalse(limit2.equals(null));

        assertFalse(limit1.equals(new Limit(10)));
        assertFalse(limit2.equals(new Limit(10)));

        assertTrue(limit1.equals(limit2) && limit2.equals(limit1));
        assertEquals(limit1.hashCode(), limit2.hashCode());
    }
}
