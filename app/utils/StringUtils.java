package utils;

import java.util.Optional;

public class StringUtils {
    public static Optional<Integer> toPositiveNonZeroInteger(String str) {
        try {
            int number = Integer.parseInt(str);
            if (number > 0)
                return Optional.of(number);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    public static Optional<Double> toPositiveNonZeroDouble(String str) {
        try {
            double number = Double.parseDouble(str);

            if (number > 0.0)
                return Optional.of(number);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }
}
