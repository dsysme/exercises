package com.dsysme.exercises;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public void generateRandomIntArray() {
        int size = RandomUtils.nextInt(5, 20);
        List<Integer> myList = new ArrayList<>();
        List<Integer> myListCopy = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            int value = RandomUtils.nextInt(0, 100);
            myList.add(value);
            myListCopy.add(value);
        }

    }
}
