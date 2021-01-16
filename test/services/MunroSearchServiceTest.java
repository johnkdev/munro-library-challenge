package services;

import models.*;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class MunroSearchServiceTest extends WithApplication {
    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testSearchWithNoSearchParamsDefined() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, null, null, null, null)
        );

        assertFalse(munros.isEmpty());
        assertEquals(509, munros.size());
    }

    @Test
    public void testSearchWithOnlyMunCategory() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(Category.MUN, null, null, null, null)
        );

        assertFalse(munros.isEmpty());
        assertEquals(282, munros.size());
        assertTrue(munros.stream().allMatch(munro -> munro.getPostYear1997().equals("MUN")));
    }

    @Test
    public void testSearchWithOnlyMunTopCategory() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(Category.MUN_TOP, null, null, null, null)
        );

        assertFalse(munros.isEmpty());
        assertEquals(227, munros.size());
        assertTrue(munros.stream().allMatch(munro -> munro.getPostYear1997().equals("TOP")));
    }

    @Test
    public void testSearchWithOnlyMunOrMunTopCategory() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(Category.MUN_OR_MUN_TOP, null, new Limit(50), null, null)
        );

        assertFalse(munros.isEmpty());
        assertEquals(50, munros.size());
        assertTrue(munros.stream().allMatch(munro -> munro.getPostYear1997().equals("MUN") || munro.getPostYear1997().equals("TOP")));
    }

    @Test
    public void testSearchWithSortByHeightInMetersAscending() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, Sort.HEIGHT_IN_METERS_ASC, null, null, null)
        );

        boolean sortedAscending = IntStream.
                range(0, munros.size() - 1)
                .allMatch(
                        index -> munros.get(index).getHeightInMeters() <= munros.get(index + 1).getHeightInMeters()
                );

        assertFalse(munros.isEmpty());
        assertTrue(sortedAscending);
    }

    @Test
    public void testSearchWithSortByHeightInMetersDescending() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, Sort.HEIGHT_IN_METERS_DESC, null, null, null)
        );

        boolean sortedDescending = IntStream.
                range(0, munros.size() - 1)
                .allMatch(
                        index -> munros.get(index).getHeightInMeters() >= munros.get(index + 1).getHeightInMeters()
                );

        assertFalse(munros.isEmpty());
        assertTrue(sortedDescending);
    }

    @Test
    public void testSearchWithSortByNameAscending() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, Sort.NAME_ASC, null, null, null)
        );

        boolean sortedAscending = IntStream.
                range(0, munros.size() - 1)
                .allMatch(
                        index -> munros.get(index).getName().compareTo(munros.get(index + 1).getName()) <= 0
                );

        assertFalse(munros.isEmpty());
        assertTrue(sortedAscending);
    }

    @Test
    public void testSearchWithSortByNameDescending() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, Sort.NAME_DESC, null, null, null)
        );

        boolean sortedDescending = IntStream.
                range(0, munros.size() - 1)
                .allMatch(
                        index -> munros.get(index).getName().compareTo(munros.get(index + 1).getName()) >= 0
                );

        assertFalse(munros.isEmpty());
        assertTrue(sortedDescending);
    }

    @Test
    public void testSearchWithSortNotDefined() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, null, null, null, null)
        );

        boolean sortedAscending = IntStream.
                range(0, munros.size() - 1)
                .allMatch(
                        index -> munros.get(index).getName().compareTo(munros.get(index + 1).getName()) <= 0
                );

        assertFalse(munros.isEmpty());
        assertTrue(sortedAscending);
    }

    @Test
    public void testSearchWithLimit() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, null, new Limit(10), null, null)
        );

        assertFalse(munros.isEmpty());
        assertEquals(10, munros.size());
    }

    @Test
    public void testSearchLimitNotDefined() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, null, null, null, null)
        );

        assertFalse(munros.isEmpty());
        assertEquals(509, munros.size());
    }

    @Test
    public void testSearchWithMinHeight() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, null, null, new MinHeight(979.0), null)
        );

        boolean entriesValid = munros.stream().allMatch(entry -> entry.getHeightInMeters() >= 979.0);

        assertFalse(munros.isEmpty());
        assertTrue(entriesValid);
    }

    @Test
    public void testSearchWithMaxHeight() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, null, null, null, new MaxHeight(960.0))
        );

        boolean entriesValid = munros.stream().allMatch(entry -> entry.getHeightInMeters() <= 960.0);

        assertFalse(munros.isEmpty());
        assertTrue(entriesValid);
    }

    @Test
    public void testSearchWithMinHeightAndMaxHeight() {
        MunroCSVFile munroCSVFile = app.injector().instanceOf(MunroCSVFile.class);

        MunroSearchService searchService = new MunroSearchService(munroCSVFile);

        List<Munro> munros = searchService.search(
                new SearchParams(null, null, null, new MinHeight(960.0), new MaxHeight(990.0))
        );

        boolean entriesValid = munros.stream().allMatch(entry -> entry.getHeightInMeters() >= 960.0 && entry.getHeightInMeters() <= 990.0);

        assertFalse(munros.isEmpty());
        assertTrue(entriesValid);
    }
}
