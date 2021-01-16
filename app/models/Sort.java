package models;

public enum Sort {

    /**
     * Sort the results by height in meters ascending.
     */
    HEIGHT_IN_METERS_ASC,

    /**
     * Sort the results by height in meters descending.
     */
    HEIGHT_IN_METERS_DESC,

    /**
     * Sort the results by name ascending.
     */
    NAME_ASC,

    /**
     * Sort the results by name descending.
     */
    NAME_DESC;

    public static Sort fromQueryParameterValue(String value) {
        switch (value) {
            case "heightInMeters asc":
                return Sort.HEIGHT_IN_METERS_ASC;
            case "heightInMeters desc":
                return Sort.HEIGHT_IN_METERS_DESC;
            case "name asc":
                return Sort.NAME_ASC;
            case "name desc":
                return Sort.NAME_DESC;
            default:
                return null;
        }
    }
}
