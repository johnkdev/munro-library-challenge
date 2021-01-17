package models;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class MunroTest {

    @Test
    public void testEqualsAndHashCode() throws MalformedURLException {
        Munro munro1 = new Munro();
        munro1.setRunningNo("1");
        munro1.setDobihNumber("1");
        munro1.setStreetmap(new URL("http://www.streetmap.co.uk/newmap.srf?x=277324&y=730857&z=3&sv=277324,730857&st=4&tl=~&bi=~&lu=N&ar=y"));
        munro1.setGeograph(new URL("http://www.geograph.org.uk/gridref/NN7732430857"));
        munro1.setHillBagging(new URL("http://www.hill-bagging.co.uk/mountaindetails.php?qu=S&rf=1"));
        munro1.setName("Ben Chonzie");
        munro1.setSmcSection(1);
        munro1.setRhbSection("01A");
        munro1.setSection(1.1);
        munro1.setHeightInMeters(931.0);
        munro1.setHeightInFeet(3054.0);
        munro1.setMap150("51 52");
        munro1.setMap125("OL47W 368W 379W");
        munro1.setGridRef("NN773308");
        munro1.setGridRefXY("NN7732430857");
        munro1.setXcoord(277324);
        munro1.setYcoord(730857);
        munro1.setYear1891("MUN");
        munro1.setYear1921("MUN");
        munro1.setYear1933("MUN");
        munro1.setYear1953("MUN");
        munro1.setYear1969("MUN");
        munro1.setYear1974("MUN");
        munro1.setYear1981("MUN");
        munro1.setYear1984("MUN");
        munro1.setYear1990("MUN");
        munro1.setYear1997("MUN");
        munro1.setPostYear1997("MUN");
        munro1.setComments("");

        Munro munro1copy = new Munro();
        munro1copy.setRunningNo("1");
        munro1copy.setDobihNumber("1");
        munro1copy.setStreetmap(new URL("http://www.streetmap.co.uk/newmap.srf?x=277324&y=730857&z=3&sv=277324,730857&st=4&tl=~&bi=~&lu=N&ar=y"));
        munro1copy.setGeograph(new URL("http://www.geograph.org.uk/gridref/NN7732430857"));
        munro1copy.setHillBagging(new URL("http://www.hill-bagging.co.uk/mountaindetails.php?qu=S&rf=1"));
        munro1copy.setName("Ben Chonzie");
        munro1copy.setSmcSection(1);
        munro1copy.setRhbSection("01A");
        munro1copy.setSection(1.1);
        munro1copy.setHeightInMeters(931.0);
        munro1copy.setHeightInFeet(3054.0);
        munro1copy.setMap150("51 52");
        munro1copy.setMap125("OL47W 368W 379W");
        munro1copy.setGridRef("NN773308");
        munro1copy.setGridRefXY("NN7732430857");
        munro1copy.setXcoord(277324);
        munro1copy.setYcoord(730857);
        munro1copy.setYear1891("MUN");
        munro1copy.setYear1921("MUN");
        munro1copy.setYear1933("MUN");
        munro1copy.setYear1953("MUN");
        munro1copy.setYear1969("MUN");
        munro1copy.setYear1974("MUN");
        munro1copy.setYear1981("MUN");
        munro1copy.setYear1984("MUN");
        munro1copy.setYear1990("MUN");
        munro1copy.setYear1997("MUN");
        munro1copy.setPostYear1997("MUN");
        munro1copy.setComments("");

        Munro munro2 = new Munro();
        munro2.setRunningNo("2");
        munro2.setDobihNumber("17");
        munro2.setStreetmap(new URL("http://www.streetmap.co.uk/newmap.srf?x=262911&y=718916&z=3&sv=262911,718916&st=4&tl=~&bi=~&lu=N&ar=y"));
        munro2.setGeograph(new URL("http://www.geograph.org.uk/gridref/NN6291118916"));
        munro2.setHillBagging(new URL("http://www.hill-bagging.co.uk/mountaindetails.php?qu=S&rf=17"));
        munro2.setName("Ben Vorlich");
        munro2.setSmcSection(1);
        munro2.setRhbSection("01B");
        munro2.setSection(1.2);
        munro2.setHeightInMeters(985.0);
        munro2.setHeightInFeet(3232.0);
        munro2.setMap150("57");
        munro2.setMap125("OL46N OL47W 365N 368W");
        munro2.setGridRef("NN629189");
        munro2.setGridRefXY("NN6291118916");
        munro2.setXcoord(262911);
        munro2.setYcoord(718916);
        munro2.setYear1891("MUN");
        munro2.setYear1921("MUN");
        munro2.setYear1933("MUN");
        munro2.setYear1953("MUN");
        munro2.setYear1969("MUN");
        munro2.setYear1974("MUN");
        munro2.setYear1981("MUN");
        munro2.setYear1984("MUN");
        munro2.setYear1990("MUN");
        munro2.setYear1997("MUN");
        munro2.setPostYear1997("MUN");
        munro2.setComments("");

        assertEquals(munro1, munro1);
        assertEquals(munro1copy, munro1copy);

        assertNotEquals(munro1, new MaxHeight(5));
        assertNotEquals(munro1copy, new MaxHeight(5));

        assertFalse(munro1.equals(null));
        assertFalse(munro1copy.equals(null));

        assertFalse(munro1.equals(munro2));
        assertFalse(munro1copy.equals(munro2));

        assertTrue(munro1.equals(munro1copy) && munro1copy.equals(munro1));
        assertEquals(munro1.hashCode(), munro1copy.hashCode());
    }
}
