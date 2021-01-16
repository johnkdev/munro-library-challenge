package models;

import java.util.Objects;

public class MinHeight {
    private final double value;

    public MinHeight(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MinHeight minHeight = (MinHeight) o;

        return getValue() == minHeight.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
