package com.dsysme.exercises.strings;

import java.util.ArrayList;

public class MyStringBuilder {

    private ArrayList<String> words;
    private int count;

    public MyStringBuilder() {
        words = new ArrayList();
    }

    public MyStringBuilder append(String aWord) {
        words.add(aWord);
        count += aWord.length();
        return this;
    }

    @Override
    public String toString() {
        char[] result = new char[count];
        int count = 0;
        for (int i = 0; i < words.size(); ++i) {
            String word = words.get(i);
            for (int j = 0; j < word.length(); ++j) {
                result[count] = word.charAt(j);
            }
        }
        return new String(result);
    }
}
