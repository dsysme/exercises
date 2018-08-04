package com.dsysme.exercises.graphs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GraphParser {
    private HashMap<Integer, List<Integer>> graph;

    public GraphParser() {
        graph = new HashMap<>();
    }

    /**
     * accepts input like "1 2,3,4,5,6" and adds the edges from 1 to any of 2,3,4,5,6
     * @param string
     * @return
     */
    private GraphParser parseVertexEdges(String string) {
        String[] elements = string.split(" ");
        if (elements.length < 1 || elements.length > 2) {
            throw new IllegalArgumentException("Cannot pares graph from input string " + string);
        }
        int vertex = Integer.valueOf(elements[0]);
        if (elements.length < 2) {
            graph.put(vertex, Collections.EMPTY_LIST);
        } else {
            List<Integer> edges = Arrays.stream(elements[1].split(",")).map(Integer::valueOf).collect(Collectors.toList());
            graph.put(vertex, edges);
        }
        return this;
    }

    public GraphParser parseGraph(String string) {
        String[] elements = string.split(String.format("%n"));
        Arrays.stream(elements).forEach(element -> parseVertexEdges(element));
        return this;
    }

    public HashMap<Integer, List<Integer>> getGraph() {
        return graph;
    }

    public static void main(String[] args) {
        GraphParser parser = new GraphParser();
        parser.parseGraph("1 2,3,4,5,6\n" +
                "2 1,3,4,5,6\n" +
                "3 1,2,4,5,6\n" +
                "4 1,2,3,5,6\n" +
                "5 1,2,3,4,6\n" +
                "6 1,2,3,4,5");
        parser.plot();
    }

    public void plot() {
        for (Integer key : graph.keySet()) {
            System.out.println(String.format("%d %s%n", key, graph.get(key)));
        }
    }
}
