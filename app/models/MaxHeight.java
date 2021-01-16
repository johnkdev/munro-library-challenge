package models;

import java.util.Objects;

public class MaxHeight {
    private final double value;

    public MaxHeight(double value) {
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

        MaxHeight maxHeight = (MaxHeight) o;

        return getValue() == maxHeight.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
