package config;

public class Constants {
    public static final String MUNRO_CSV_FILE_PATH = "/conf/munrotab_v6.2.csv";

    public class ErrorMessages {
        public static final String UNABLE_TO_PARSE_CSV_FILE = "Unable to parse CSV file '" + MUNRO_CSV_FILE_PATH + "'";

        public static final String UNABLE_TO_FIND_CSV_FILE = "Unable to find CSV file '" + MUNRO_CSV_FILE_PATH + "'";
    }
}
