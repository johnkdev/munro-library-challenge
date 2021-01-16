package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void testMun() {
        Category category = Category.MUN;

        assertEquals(category.getValues().size(), 1);

        assertEquals("MUN", category.getValues().get(0));
        assertEquals(Category.MUN, category);

        assertNotEquals(Category.MUN_TOP, category);
        assertNotEquals(Category.MUN_OR_MUN_TOP, category);
    }

    @Test
    public void testMunTop() {
        Category category = Category.MUN_TOP;

        assertEquals(category.getValues().size(), 1);

        assertEquals("TOP", category.getValues().get(0));
        assertEquals(Category.MUN_TOP, category);

        assertNotEquals(Category.MUN, category);
        assertNotEquals(Category.MUN_OR_MUN_TOP, category);
    }

    @Test
    public void testMunOrMunTop() {
        Category category = Category.MUN_OR_MUN_TOP;

        assertEquals(category.getValues().size(), 2);

        assertEquals("MUN", category.getValues().get(0));
        assertEquals("TOP", category.getValues().get(1));
        assertEquals(Category.MUN_OR_MUN_TOP, category);

        assertNotEquals(Category.MUN, category);
        assertNotEquals(Category.MUN_TOP, category);
    }

    @Test
    public void testToValidCategory() {
        assertEquals(Category.fromQueryParameterValue("munro"), Category.MUN);

        assertEquals(Category.fromQueryParameterValue("munro top"), Category.MUN_TOP);

        assertEquals(Category.fromQueryParameterValue("munro,munro top"), Category.MUN_OR_MUN_TOP);
        assertEquals(Category.fromQueryParameterValue("munro top,munro"), Category.MUN_OR_MUN_TOP);
    }

    @Test()
    public void testToInvalidCategory() {
        assertNull(Category.fromQueryParameterValue(""));
        assertNull(Category.fromQueryParameterValue("munro2"));
    }
}
