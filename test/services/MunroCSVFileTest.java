package services;

import config.Constants;
import models.Munro;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.Environment;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import utils.TestAppender;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MunroCSVFileTest extends WithApplication {
    private Munro expectedMunro1;

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Before
    public void setUp() throws MalformedURLException {
        expectedMunro1 = new Munro();
        expectedMunro1.setRunningNo("1");
        expectedMunro1.setDobihNumber("1");
        expectedMunro1.setStreetmap(new URL("http://www.streetmap.co.uk/newmap.srf?x=277324&y=730857&z=3&sv=277324,730857&st=4&tl=~&bi=~&lu=N&ar=y"));
        expectedMunro1.setGeograph(new URL("http://www.geograph.org.uk/gridref/NN7732430857"));
        expectedMunro1.setHillBagging(new URL("http://www.hill-bagging.co.uk/mountaindetails.php?qu=S&rf=1"));
        expectedMunro1.setName("Ben Chonzie");
        expectedMunro1.setSmcSection(1);
        expectedMunro1.setRhbSection("01A");
        expectedMunro1.setSection(1.1);
        expectedMunro1.setHeightInMeters(931.0);
        expectedMunro1.setHeightInFeet(3054.0);
        expectedMunro1.setMap150("51 52");
        expectedMunro1.setMap125("OL47W 368W 379W");
        expectedMunro1.setGridRef("NN773308");
        expectedMunro1.setGridRefXY("NN7732430857");
        expectedMunro1.setXcoord(277324);
        expectedMunro1.setYcoord(730857);
        expectedMunro1.setYear1891("MUN");
        expectedMunro1.setYear1921("MUN");
        expectedMunro1.setYear1933("MUN");
        expectedMunro1.setYear1953("MUN");
        expectedMunro1.setYear1969("MUN");
        expectedMunro1.setYear1974("MUN");
        expectedMunro1.setYear1981("MUN");
        expectedMunro1.setYear1984("MUN");
        expectedMunro1.setYear1990("MUN");
        expectedMunro1.setYear1997("MUN");
        expectedMunro1.setPostYear1997("MUN");
        expectedMunro1.setComments("");
    }

    @Test
    public void testCSVFileCanBeLoaded() {
        Environment env = app.injector().instanceOf(Environment.class);

        MunroCSVFile csvFile = new MunroCSVFile(env);

        List<Munro> munros = csvFile.getMunros();

        assertTrue(munros != null);
        assertTrue(munros.size() == 509);
    }

    @Test
    public void testEntryIsValid() {
        Environment env = app.injector().instanceOf(Environment.class);

        MunroCSVFile csvFile = new MunroCSVFile(env);

        List<Munro> munros = csvFile.getMunros();

        Munro first = munros.get(0);

        assertEquals(first, expectedMunro1);
    }

    @Test
    public void testNoBlank1997ColumnEntry() {
        Environment env = app.injector().instanceOf(Environment.class);

        MunroCSVFile csvFile = new MunroCSVFile(env);

        List<Munro> munros = csvFile.getMunros();

        assertTrue(munros.stream().allMatch(munro -> munro.getPostYear1997() != null && munro.getPostYear1997().length() > 0));
    }

    @Test
    public void testNoNullEntries() {
        Environment env = app.injector().instanceOf(Environment.class);

        MunroCSVFile csvFile = new MunroCSVFile(env);

        List<Munro> munros = csvFile.getMunros();

        assertTrue(munros.stream().allMatch(munro -> munro.getRunningNo() != null && munro.getRunningNo().length() > 0));
    }

    @Test
    public void testErrorLogOccursWhenCSVFileIsNotFound() {
        Environment env = mock(Environment.class);

        when(env.getFile(any(String.class))).thenReturn(new File(""));

        MunroCSVFile csvFile = new MunroCSVFile(env);

        assertFalse(TestAppender.events.isEmpty());
        assertEquals(1, TestAppender.events.size());
        assertEquals(TestAppender.events.get(0).getMessage(), Constants.ErrorMessages.UNABLE_TO_FIND_CSV_FILE);
    }

    @Test
    public void testErrorLogOccursWhenCSVFileCannotBeParsed() {
        Environment env = mock(Environment.class);

        when(env.getFile(any(String.class))).thenReturn(new File(getClass().getResource("/invalid.csv").getPath()));

        MunroCSVFile csvFile = new MunroCSVFile(env);

        assertFalse(TestAppender.events.isEmpty());
        assertTrue(TestAppender.events.size() == 1);
        assertEquals(TestAppender.events.get(0).getMessage(), Constants.ErrorMessages.UNABLE_TO_PARSE_CSV_FILE);
    }

    @After
    public void clearEvents() {
        TestAppender.events.clear();
    }
}
