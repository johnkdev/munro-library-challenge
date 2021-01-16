package models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchParams {
    private final Category category;
    private final Limit limit;
    private final MaxHeight maxHeight;
    private final MinHeight minHeight;
    private final Sort sort;

    public SearchParams(Category category, Sort sort, Limit limit, MinHeight minHeight, MaxHeight maxHeight) {
        this.category = category;
        this.sort = sort;
        this.limit = limit;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public Category getCategory() {
        return category;
    }

    public Sort getSort() {
        return sort;
    }

    public Limit getLimit() {
        return limit;
    }

    public MinHeight getMinHeight() {
        return minHeight;
    }

    public MaxHeight getMaxHeight() {
        return maxHeight;
    }

    public Predicate<Munro> toMunroSearchPredicate() {
        List<Predicate<Munro>> predicates = new ArrayList<>();

        if (category != null) {
            predicates.add(entry -> category.getValues().contains(entry.getPostYear1997()));
        }

        if (minHeight != null) {
            predicates.add(entry -> entry.getHeightInMeters() >= minHeight.getValue());
        }

        if (maxHeight != null) {
            predicates.add(entry -> entry.getHeightInMeters() <= maxHeight.getValue());
        }

        return predicates.stream().reduce(Predicate::and).orElse(entry -> true);
    }
}
