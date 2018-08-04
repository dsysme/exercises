package com.dsysme.exercises.strings;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringExercisesTest {

    @Test
    void isUniuqe() {
        // 1.1 is string unique
        String unique = "abcdefg";
        String notUnique = "akaidy";
        assertFalse(StringExercises.isUnique(notUnique), String.format("isUnique %s should return false.",notUnique));
        assertTrue(StringExercises.isUnique(unique), String.format("isUnique %s should return true.",unique));
    }

    @Test
    void checkPermutation() {
        // 1.2 check permutations
        String first = RandomStringUtils.randomAscii(3, 200);
        String second = RandomStringUtils.randomAscii(3, 200);
        System.out.print(String.format("first=%s, second=%s", first, second));
        assertTrue(StringExercises.checkPermutation(first, first), "checkPermutation should return true when given same string.");
        assertFalse(StringExercises.checkPermutation(first, second), String.format("checkPermutation should return false for first=%s, second=%s", first, second));
    }
}