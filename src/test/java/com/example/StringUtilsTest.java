package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"level", "RaceCar", ""})
    void isPalindromeTrue(String input) {
        assertTrue(StringUtils.isPalindrome(input));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"hello"})
        // RaceCar — false? нет, true!
    void isPalindromeFalse(String input) {
        assertFalse(StringUtils.isPalindrome(input));
    }


    @ParameterizedTest
    @CsvSource({
            "hello, 2",
            "xyz, 0",
            "'', 0",
            "AEIOU, 5",
    })
    void countVowelsTest(String input, int expectedLength) {
        int result = StringUtils.countVowels(input);
        assertEquals(expectedLength, result);
    }

    @Test
    void countVowelsNull() {
        assertEquals(0, StringUtils.countVowels(null));
    }
}
