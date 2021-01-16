package models;

import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertTrue;

public class SearchParamsTest {

    @Test
    public void testToMunroSearchPredicate() {
        SearchParams searchParams = new SearchParams(null, null, null, null, null);

        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        assertTrue(predicate.test(new Munro()));
    }
}
