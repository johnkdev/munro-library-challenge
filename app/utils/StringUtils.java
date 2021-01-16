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
}
