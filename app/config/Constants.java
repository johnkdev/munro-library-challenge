package config;

import models.Category;
import models.Limit;
import models.Sort;
import play.libs.typedmap.TypedKey;

public class Constants {
    public static final String MUNRO_CSV_FILE_PATH = "/conf/munrotab_v6.2.csv";

    public static class Attrs {
        public static final TypedKey<Category> CATEGORY = TypedKey.create("category");
        public static final TypedKey<Sort> SORT = TypedKey.create("sort");
        public static final TypedKey<Limit> LIMIT = TypedKey.create("limit");
    }

    public class ErrorMessages {
        public static final String UNABLE_TO_PARSE_CSV_FILE = "Unable to parse CSV file '" + MUNRO_CSV_FILE_PATH + "'";

        public static final String UNABLE_TO_FIND_CSV_FILE = "Unable to find CSV file '" + MUNRO_CSV_FILE_PATH + "'";

        public static final String MUNRO_DATA_NOT_LOADED = "Munro data was not loaded from CSV file on API startup";

        public static final String INVALID_CATEGORY_QUERY_PARAMETER =
                "Invalid value for search parameter 'category'. Please specify one of the following values:\n" +
                        "munro\n" +
                        "munro top\n" +
                        "munro,munro top\n" +
                        "munro top,munro\n";

        public static final String INVALID_SORT_QUERY_PARAMETER =
                "Invalid value for search parameter 'sort'. Please specify one of the following values:\n" +
                        "heightInMetres asc\n" +
                        "heightInMetres desc\n" +
                        "name asc\n" +
                        "name desc\n";

        public static final String INVALID_LIMIT_QUERY_PARAMETER =
                "Invalid value for search parameter 'limit'. Please specify an integer value greater than 0.";
    }
}
