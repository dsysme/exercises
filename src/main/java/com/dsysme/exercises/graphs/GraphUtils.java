package com.dsysme.exercises.graphs;

import javax.xml.transform.stream.StreamSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphUtils {
    private static String pattern = "(\\[)(\\d+)(\\])";
    private static Pattern r = Pattern.compile(pattern);

    // [1, (2,3,4,5,6)]|[2, (1,3,4,5,6)]|[3, (1,2,4,5,6)]|[4, (1,2,3,5,6)]|[5, (1,2,3,4,6)]|[6, (1,2,3,4,5)]
    public static String[] fromString(String graph) {
        String[] entries = graph.split("\\|");
        return entries;
    }

    public static void main(String[] args) {
        String graph = "[1, (2,3,4,5,6)]|[2, (1,3,4,5,6)]|[3, (1,2,4,5,6)]|[4, (1,2,3,5,6)]|[5, (1,2,3,4,6)]|[6, (1,2,3,4,5)]";
        String[] entries = GraphUtils.fromString(graph);
        Matcher m = r.matcher(entries[0]);
        Arrays.stream(entries).forEach(entry -> System.out.println(r.matcher(entry).group(1)));
    }
}
