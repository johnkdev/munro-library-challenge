package services;

import models.Munro;
import models.SearchParams;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Singleton
public class MunroSearchService {

    private MunroCSVFile munroCSVFile;

    @Inject
    public MunroSearchService(MunroCSVFile munroCSVFile) {
        this.munroCSVFile = munroCSVFile;
    }

    public List<Munro> search(SearchParams searchParams) {
        Predicate<Munro> predicate = searchParams.toMunroSearchPredicate();

        List<Munro> munros = munroCSVFile.getMunros()
                .stream()
                .filter(predicate)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        return munros;
    }
}
