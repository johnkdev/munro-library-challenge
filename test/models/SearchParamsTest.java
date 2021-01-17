package models;

import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchParamsTest {

    @Test
    public void testToMunroSearchPredicate() {
        SearchParams searchParams = new SearchParams(null, null, null, null, null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        assertTrue(predicate.test(new Munro()));
    }

    @Test
    public void testToMunroSearchPredicateWithValidMunCategory() {
        SearchParams searchParams = new SearchParams(Category.MUN, null, null, null, null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setPostYear1997("MUN");

        assertTrue(predicate.test(munro));
    }

    @Test
    public void testToMunroSearchPredicateWithInvalidMunCategory() {
        SearchParams searchParams = new SearchParams(Category.MUN, null, null, null, null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setPostYear1997("TOP");

        assertFalse(predicate.test(munro));
    }

    @Test
    public void testToMunroSearchPredicateWithValidMunTopCategory() {
        SearchParams searchParams = new SearchParams(Category.MUN_TOP, null, null, null, null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setPostYear1997("TOP");

        assertTrue(predicate.test(munro));
    }

    @Test
    public void testToMunroSearchPredicateWithInvalidMunTopCategory() {
        SearchParams searchParams = new SearchParams(Category.MUN_TOP, null, null, null, null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setPostYear1997("MUN");

        assertFalse(predicate.test(munro));
    }

    @Test
    public void testToMunroSearchPredicateWithMunOrMunTopCategory() {
        SearchParams searchParams = new SearchParams(Category.MUN_OR_MUN_TOP, null, null, null, null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setPostYear1997("MUN");

        assertTrue(predicate.test(munro));
    }

    @Test
    public void testToMunroSearchPredicateWithMunOrMunTopCategoryAlt() {
        SearchParams searchParams = new SearchParams(Category.MUN_OR_MUN_TOP, null, null, null, null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setPostYear1997("TOP");

        assertTrue(predicate.test(munro));
    }

    @Test
    public void testToMunroSearchPredicateWithValidMinHeight() {
        SearchParams searchParams = new SearchParams(null, null, null, new MinHeight(10), null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setHeightInMeters(10);

        assertTrue(predicate.test(munro));
    }

    @Test
    public void testToMunroSearchPredicateWithInvalidMinHeight() {
        SearchParams searchParams = new SearchParams(null, null, null, new MinHeight(10), null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setHeightInMeters(5);

        assertFalse(predicate.test(munro));
    }

    @Test
    public void testToMunroSearchPredicateWithValidMaxHeight() {
        SearchParams searchParams = new SearchParams(null, null, null, null, new MaxHeight(10));

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setHeightInMeters(10);

        assertTrue(predicate.test(munro));
    }

    @Test
    public void testToMunroSearchPredicateWithInvalidMaxHeight() {
        SearchParams searchParams = new SearchParams(null, null, null, null, new MaxHeight(10));

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        Munro munro = new Munro();
        munro.setHeightInMeters(15);

        assertFalse(predicate.test(munro));
    }
}
