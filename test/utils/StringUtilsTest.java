package utils;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void testToPositiveNonZeroInteger() {
        assertEquals(StringUtils.toPositiveNonZeroInteger("1"), Optional.of(1));
        assertEquals(StringUtils.toPositiveNonZeroInteger("10"), Optional.of(10));

        assertEquals(StringUtils.toPositiveNonZeroInteger(""), Optional.empty());
        assertEquals(StringUtils.toPositiveNonZeroInteger("0"), Optional.empty());
        assertEquals(StringUtils.toPositiveNonZeroInteger("foo"), Optional.empty());
    }
}
