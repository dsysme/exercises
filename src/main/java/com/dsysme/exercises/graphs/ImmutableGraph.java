package com.dsysme.exercises.graphs;

import java.util.*;


public final class ImmutableGraph {

    private final HashMap<Integer, List<Integer>> vertexToEdges;

    public ImmutableGraph(HashMap<Integer, List<Integer>> graph) {
        vertexToEdges = new HashMap<>();
        graph.entrySet().forEach(entry -> vertexToEdges.put(entry.getKey(), Collections.unmodifiableList(entry.getValue())));
    }

    public List<Integer> getVertexEdges(int vertex) {
        return Collections.unmodifiableList(vertexToEdges.get(vertex));
    }

    public Set<Integer> getVertexes() {
        return Collections.unmodifiableSet(vertexToEdges.keySet());
    }
}
