package com.dsysme.exercises.lists;

import com.dsysme.exercises.lists.ListExercises;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListExercisesTest {

    @Test
    void removeDupsNoDup() {
        Integer[] myIntArray = {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(myIntArray);
        ListExercises.Node n = ListExercises.Node.fromList(list);
        assertTrue(n.toString().intern() == "[1]->[2]->[3]->[4]->[5]->[]");
        ListExercises.removeDups(n);
        assertTrue(n.toString().intern() == "[1]->[2]->[3]->[4]->[5]->[]");
    }

    @Test
    void removeDupsWithDup() {
        Integer[] myIntArray = {1, 2, 3, 2, 5};
        List<Integer> list = Arrays.asList(myIntArray);
        ListExercises.Node n = ListExercises.Node.fromList(list);
        assertTrue(n.toString().intern() == "[1]->[2]->[3]->[2]->[5]->[]");
        ListExercises.removeDups(n);
        assertTrue(n.toString().intern() == "[1]->[2]->[3]->[5]->[]");
    }

    @Test
    void removeDupEmptyList() {
        assertEquals(ListExercises.removeDups(null), null);
    }

}