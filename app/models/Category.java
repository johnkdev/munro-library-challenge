package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Category {

    /**
     * "MUN" is used for limiting the search category.
     */
    MUN("MUN"),

    /**
     * "TOP" is used for limiting the search category.
     */
    MUN_TOP("TOP"),

    /**
     * "MUN" or "TOP" is used for limiting the search category.
     */
    MUN_OR_MUN_TOP("MUN", "TOP");

    private final List<String> values;

    Category(String... values) {
        this.values = new ArrayList<>(Arrays.asList(values));
    }

    public List<String> getValues() {
        return values;
    }

    public static Category fromQueryParameterValue(String value) {
        switch (value) {
            case "munro":
                return Category.MUN;
            case "munro top":
                return Category.MUN_TOP;
            case "munro,munro top":
            case "munro top,munro":
                return Category.MUN_OR_MUN_TOP;
            default:
                return null;
        }
    }
}
