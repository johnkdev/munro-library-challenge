package models;

import java.util.Objects;

public class Limit {
    private final int value;

    public Limit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Limit limit = (Limit) o;

        return getValue() == limit.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
