package services;

import com.opencsv.bean.CsvToBeanBuilder;
import config.Constants;
import models.Munro;
import play.Environment;
import play.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Singleton
public class MunroCSVFile {
    private final Logger.ALogger log = Logger.of(this.getClass());

    private static final Predicate<Munro> EXCLUDE_NULLS =
            entry -> entry.getName() != null && entry.getName().length() > 0;

    private static final Predicate<Munro> EXCLUDE_BLANKS =
            entry -> entry.getPostYear1997() != null && entry.getPostYear1997().length() > 0;

    private List<Munro> munros;

    @Inject
    public MunroCSVFile(Environment env) {
        File csvFile = env.getFile(Constants.MUNRO_CSV_FILE_PATH);

        try {
            munros = new CsvToBeanBuilder<Munro>(new FileReader(csvFile))
                    .withType(Munro.class)
                    .build()
                    .parse()
                    .stream()
                    .filter(EXCLUDE_NULLS.and(EXCLUDE_BLANKS))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            log.error(Constants.ErrorMessages.UNABLE_TO_FIND_CSV_FILE, e);
        } catch (Exception e) {
            log.error(Constants.ErrorMessages.UNABLE_TO_PARSE_CSV_FILE, e);
        }
    }

    public List<Munro> getMunros() {
        return munros;
    }
}
