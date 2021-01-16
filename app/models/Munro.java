package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.opencsv.bean.CsvBindByName;

import java.net.URL;
import java.util.Objects;

@JsonIgnoreProperties({
        "runningNo",
        "dobihNumber",
        "streetmap",
        "geograph",
        "hillBagging",
        "smcSection",
        "rhbSection",
        "section",
        "heightInFeet",
        "map150",
        "map125",
        "gridRefXY",
        "xcoord",
        "ycoord",
        "year1891",
        "year1921",
        "year1933",
        "year1953",
        "year1969",
        "year1974",
        "year1981",
        "year1984",
        "year1990",
        "year1997",
        "postYear1997",
        "comments"
})
@JsonPropertyOrder({"name", "heightInMetres", "hillCategory", "gridReference"})
public class Munro implements Comparable<Munro> {
    @CsvBindByName(column = "Running No")
    private String runningNo;

    @CsvBindByName(column = "DoBIH Number")
    private String dobihNumber;

    @CsvBindByName(column = "Streetmap", capture = "\"([^\"]*)\"")
    private URL streetmap;

    @CsvBindByName(column = "Geograph")
    private URL geograph;

    @CsvBindByName(column = "Hill-bagging")
    private URL hillBagging;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "SMC Section")
    private int smcSection;

    @CsvBindByName(column = "RHB Section")
    private String rhbSection;

    @CsvBindByName(column = "_Section")
    private double section;

    @CsvBindByName(column = "Height (m)")
    private double heightInMeters;

    @CsvBindByName(column = "Height (ft)")
    private double heightInFeet;

    @CsvBindByName(column = "Map 1:50")
    private String map150;

    @CsvBindByName(column = "Map 1:25")
    private String map125;

    @CsvBindByName(column = "Grid Ref")
    private String gridRef;

    @CsvBindByName(column = "GridRefXY")
    private String gridRefXY;

    @CsvBindByName(column = "xcoord")
    private int xcoord;

    @CsvBindByName(column = "ycoord")
    private int ycoord;

    @CsvBindByName(column = "1891")
    private String year1891;

    @CsvBindByName(column = "1921")
    private String year1921;

    @CsvBindByName(column = "1933")
    private String year1933;

    @CsvBindByName(column = "1953")
    private String year1953;

    @CsvBindByName(column = "1969")
    private String year1969;

    @CsvBindByName(column = "1974")
    private String year1974;

    @CsvBindByName(column = "1981")
    private String year1981;

    @CsvBindByName(column = "1984")
    private String year1984;

    @CsvBindByName(column = "1990")
    private String year1990;

    @CsvBindByName(column = "1997")
    private String year1997;

    @CsvBindByName(column = "Post 1997")
    private String postYear1997;

    @CsvBindByName(column = "Comments")
    private String comments;

    public Munro() {

    }

    public String getRunningNo() {
        return runningNo;
    }

    public void setRunningNo(String runningNo) {
        this.runningNo = runningNo;
    }

    public String getDobihNumber() {
        return dobihNumber;
    }

    public void setDobihNumber(String dobihNumber) {
        this.dobihNumber = dobihNumber;
    }

    public URL getStreetmap() {
        return streetmap;
    }

    public void setStreetmap(URL streetmap) {
        this.streetmap = streetmap;
    }

    public URL getGeograph() {
        return geograph;
    }

    public void setGeograph(URL geograph) {
        this.geograph = geograph;
    }

    public URL getHillBagging() {
        return hillBagging;
    }

    public void setHillBagging(URL hillBagging) {
        this.hillBagging = hillBagging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSmcSection() {
        return smcSection;
    }

    public void setSmcSection(int smcSection) {
        this.smcSection = smcSection;
    }

    public String getRhbSection() {
        return rhbSection;
    }

    public void setRhbSection(String rhbSection) {
        this.rhbSection = rhbSection;
    }

    public double getSection() {
        return section;
    }

    public void setSection(double section) {
        this.section = section;
    }

    public double getHeightInMeters() {
        return heightInMeters;
    }

    @JsonProperty("heightInMeters")
    public String getHeightInMetersAsString() {
        return heightInMeters % 1 == 0 ? String.valueOf((int)heightInMeters) : String.valueOf(heightInMeters);
    }

    public void setHeightInMeters(double heightInMeters) {
        this.heightInMeters = heightInMeters;
    }

    public double getHeightInFeet() {
        return heightInFeet;
    }

    public void setHeightInFeet(double heightInFeet) {
        this.heightInFeet = heightInFeet;
    }

    public String getMap150() {
        return map150;
    }

    public void setMap150(String map150) {
        this.map150 = map150;
    }

    public String getMap125() {
        return map125;
    }

    public void setMap125(String map125) {
        this.map125 = map125;
    }

    @JsonProperty("gridReference")
    public String getGridRef() {
        return gridRef;
    }

    public void setGridRef(String gridRef) {
        this.gridRef = gridRef;
    }

    public String getGridRefXY() {
        return gridRefXY;
    }

    public void setGridRefXY(String gridRefXY) {
        this.gridRefXY = gridRefXY;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public String getYear1891() {
        return year1891;
    }

    public void setYear1891(String year1891) {
        this.year1891 = year1891;
    }

    public String getYear1921() {
        return year1921;
    }

    public void setYear1921(String year1921) {
        this.year1921 = year1921;
    }

    public String getYear1933() {
        return year1933;
    }

    public void setYear1933(String year1933) {
        this.year1933 = year1933;
    }

    public String getYear1953() {
        return year1953;
    }

    public void setYear1953(String year1953) {
        this.year1953 = year1953;
    }

    public String getYear1969() {
        return year1969;
    }

    public void setYear1969(String year1969) {
        this.year1969 = year1969;
    }

    public String getYear1974() {
        return year1974;
    }

    public void setYear1974(String year1974) {
        this.year1974 = year1974;
    }

    public String getYear1981() {
        return year1981;
    }

    public void setYear1981(String year1981) {
        this.year1981 = year1981;
    }

    public String getYear1984() {
        return year1984;
    }

    public void setYear1984(String year1984) {
        this.year1984 = year1984;
    }

    public String getYear1990() {
        return year1990;
    }

    public void setYear1990(String year1990) {
        this.year1990 = year1990;
    }

    public String getYear1997() {
        return year1997;
    }

    public void setYear1997(String year1997) {
        this.year1997 = year1997;
    }

    @JsonProperty("hillCategory")
    public String getPostYear1997() {
        return postYear1997;
    }

    public void setPostYear1997(String postYear1997) {
        this.postYear1997 = postYear1997;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int compareTo(Munro other) {
        return name.compareTo(other.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Munro munro = (Munro) o;

        return getSmcSection() == munro.getSmcSection() &&
                Double.compare(munro.getSection(), getSection()) == 0 &&
                Double.compare(munro.getHeightInMeters(), getHeightInMeters()) == 0 &&
                Double.compare(munro.getHeightInFeet(), getHeightInFeet()) == 0 &&
                getXcoord() == munro.getXcoord() &&
                getYcoord() == munro.getYcoord() &&
                getRunningNo().equals(munro.getRunningNo()) &&
                getDobihNumber().equals(munro.getDobihNumber()) &&
                getStreetmap().equals(munro.getStreetmap()) &&
                getGeograph().equals(munro.getGeograph()) &&
                getHillBagging().equals(munro.getHillBagging()) &&
                getName().equals(munro.getName()) &&
                getRhbSection().equals(munro.getRhbSection()) &&
                getMap150().equals(munro.getMap150()) &&
                getMap125().equals(munro.getMap125()) &&
                getGridRef().equals(munro.getGridRef()) &&
                getGridRefXY().equals(munro.getGridRefXY()) &&
                getYear1891().equals(munro.getYear1891()) &&
                getYear1921().equals(munro.getYear1921()) &&
                getYear1933().equals(munro.getYear1933()) &&
                getYear1953().equals(munro.getYear1953()) &&
                getYear1969().equals(munro.getYear1969()) &&
                getYear1974().equals(munro.getYear1974()) &&
                getYear1981().equals(munro.getYear1981()) &&
                getYear1984().equals(munro.getYear1984()) &&
                getYear1990().equals(munro.getYear1990()) &&
                getYear1997().equals(munro.getYear1997()) &&
                getPostYear1997().equals(munro.getPostYear1997()) &&
                getComments().equals(munro.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRunningNo(),
                getDobihNumber(),
                getStreetmap(),
                getGeograph(),
                getHillBagging(),
                getName(),
                getSmcSection(),
                getRhbSection(),
                getSection(),
                getHeightInMeters(),
                getHeightInFeet(),
                getMap150(),
                getMap125(),
                getGridRef(),
                getGridRefXY(),
                getXcoord(),
                getYcoord(),
                getYear1891(),
                getYear1921(),
                getYear1933(),
                getYear1953(),
                getYear1969(),
                getYear1974(),
                getYear1981(),
                getYear1984(),
                getYear1990(),
                getYear1997(),
                getPostYear1997(),
                getComments());
    }
}
