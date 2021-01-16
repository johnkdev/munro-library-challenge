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

    @Test
    public void testToPositiveNonZeroDouble() {
        assertEquals(StringUtils.toPositiveNonZeroDouble("0.9"), Optional.of(0.9));
        assertEquals(StringUtils.toPositiveNonZeroDouble("1"), Optional.of(1.0));
        assertEquals(StringUtils.toPositiveNonZeroDouble("10"), Optional.of(10.0));

        assertEquals(StringUtils.toPositiveNonZeroDouble(""), Optional.empty());
        assertEquals(StringUtils.toPositiveNonZeroDouble("0"), Optional.empty());
        assertEquals(StringUtils.toPositiveNonZeroDouble("0.0"), Optional.empty());

        assertEquals(StringUtils.toPositiveNonZeroDouble("foo"), Optional.empty());
    }
}
