package com.dsysme.exercises.graphs;

import java.util.*;
import java.util.stream.Stream;

public class GraphsAlgorithms {

    // TODO: Add function to return connected sub-graphs for a none connected graph
    // graph is assumed here to be connected graph
    // keys assumed to have values between 0-n where n is the number of nodes in the graph
    private static void getDFSForConnectedGraph(List<Integer> accumulator, int root, Map<Integer, List<Integer>> graph) {
        if (graph.isEmpty()) {
            return;
        }

        accumulator.add(root);
        List<Integer> rootChildren = graph.getOrDefault(root, Collections.EMPTY_LIST);
        for (Integer child: rootChildren) {
            if (!accumulator.contains(child)) {
                getDFSForConnectedGraph(accumulator, child, graph);
            }
        }
    }

    private static void getDFSForAsyclicConnectedGraph(List<Integer> accumulator, int root, Map<Integer, List<Integer>> graph) {
        if (graph.isEmpty()) {
            return;
        }

        accumulator.add(root);
        List<Integer> rootChildren = graph.getOrDefault(root, Collections.EMPTY_LIST);
        for (Integer child: rootChildren) {
            if (accumulator.contains(child)) {
                throw new IllegalArgumentException("Input graph contain cycles.");
            }
            getDFSForConnectedGraph(accumulator, child, graph);
        }
    }

    // TODO should I work on unmodifiable copy of graph or count on the caller?
    public static List<Integer> getDFS(Map<Integer, List<Integer>> graph) {
        List<Map<Integer, List<Integer>>> components = getConnectedComponents(graph);
        List<Integer> result = new ArrayList<>();
        components.stream().forEach(subGraph ->
                getDFSForConnectedGraph(result, subGraph.keySet().stream().reduce(Integer.MAX_VALUE, Math::min),subGraph));
        return result;
    }

    //  graph is assumed here to be connected graph
    private static List<Integer> getBFSForConnectedGraph(List<Integer> accumulator, Integer root, Map<Integer, List<Integer>> graph) {

        if (graph.isEmpty()) {
            return accumulator;
        }

        if (accumulator.isEmpty()) {
            accumulator.add(root);
        }

        Queue<Integer> next = new LinkedList<>();
        List<Integer> children = graph.getOrDefault(root, Collections.EMPTY_LIST);
        for (Integer child: children) {
            if (!accumulator.contains(child)) {
                accumulator.add(child);
                next.add(child);
            }
        }

        for (Integer child: next) {
            getBFSForConnectedGraph(accumulator, child, graph);
        }

        return accumulator;
    }

    // TODO should I work on unmodifiable copy of graph or count on the caller?
    public static List<Integer> getBFS(Map<Integer, List<Integer>> graph) {
        List<Map<Integer, List<Integer>>> components = getConnectedComponents(graph);
        List<Integer> result = new ArrayList<>();
        components.stream().forEach(subGraph ->
                getBFSForConnectedGraph(result, subGraph.keySet().stream().reduce(Integer.MAX_VALUE, Math::min),subGraph));
        return result;
    }


    // TODO how to handle bidirectional not as cycle
    public static boolean isCyclicHelper(List<Integer> visited, Integer root, Map<Integer, List<Integer>> graph) {
        if (graph.isEmpty()) {
            return false;
        }

        if (visited.contains(root)) {
            return true;
        }
        visited.add(root);

        List<Integer> children = graph.getOrDefault(root, Collections.EMPTY_LIST);
        for (Integer child: children) {
            if (isCyclicHelper(visited, child, graph)) {
                return true;
            }
        }

        return false;

    }

    /**
     *
     * @param graph
     * @return true if graph is cyclic
     */
    public static boolean isCyclic(Map<Integer, List<Integer>> graph) {
        List<Integer> visited = new ArrayList<>();
        return isCyclicHelper(visited, 1, graph);
    }

    /**
     * I assume here that all edges even if disconnected are keys in graph
     * @param graph
     * @param includeVertex
     * @return
     */
    public static HashMap<Integer, List<Integer>> subGraphIncluding(Map<Integer, List<Integer>> graph, Collection<Integer> includeVertex) {
        HashMap<Integer, List<Integer>> subGraph = new HashMap<>();
        Stream.of(includeVertex.toArray()).forEach(v -> subGraph.put((Integer)v, graph.get((v))));
        return subGraph;
    }

    public static HashMap<Integer, List<Integer>> subGraphExcluding(Map<Integer, List<Integer>> graph, Collection<Integer> excludeVertex) {
        HashMap<Integer, List<Integer>> subGraph = new HashMap<>();
        graph.keySet();
        Stream.of(graph.keySet().toArray()).filter(vertex -> !excludeVertex.contains(vertex)).forEach(v -> subGraph.put((Integer)v, graph.get((v))));
        return subGraph;
    }

    /**
     * TODO is it better to return stream here?
     * return all connected components for the input graph
     * input graph assumed to be bi directed with all edges referenced from both vertexes.
     * @param graph
     * @return
     */
    public static List<Map<Integer, List<Integer>>> getConnectedComponents(Map<Integer, List<Integer>> graph) {

        List<Map<Integer, List<Integer>>> result = new ArrayList<>();
        Set<Integer> remainderVertexes = new HashSet<>();
        remainderVertexes.addAll(graph.keySet());
        while (!remainderVertexes.isEmpty()) {
            int root = remainderVertexes.stream().reduce(Integer.MAX_VALUE, Math::min);
            List<Integer> vertexes = new ArrayList<>();
            getDFSForConnectedGraph(vertexes, root, graph);
            Map<Integer, List<Integer>> connectedComponent = subGraphIncluding(graph, vertexes);
            result.add(Collections.unmodifiableMap(connectedComponent));
            remainderVertexes.removeAll(vertexes);
        }
        return result;
    }

    public static List<Integer> getOrderedList(Map<Integer, List<Integer>> graph) {

        List<Integer> result = new ArrayList<>();
        Set<Integer> remainderVertexes = new HashSet<>();
        remainderVertexes.addAll(graph.keySet());
        while (!remainderVertexes.isEmpty()) {
            int root = remainderVertexes.stream().reduce(Integer.MAX_VALUE, Math::min);
            List<Integer> vertexes = new ArrayList<>();
            getDFSForAsyclicConnectedGraph(vertexes, root, graph);
            result.addAll(vertexes);
            remainderVertexes.removeAll(vertexes);
        }
        return result;
    }

    /**
     *
     * @param paths
     * @param graph
     * @param to
     * @return
     */
    private static List<Integer> findMinimumPathHelper(Set<List<Integer>> paths,
                                             Map<Integer, List<Integer>> graph,
                                             Integer to) {
        if (paths.isEmpty()) {
            // there is no path
            return Collections.EMPTY_LIST;
        }

        Set<List<Integer>> nextRoundPaths = new HashSet<>();
        for (List<Integer> path: paths) {
            int vertex = path.get(path.size() - 1);
            List<Integer> neighbours = graph.get(vertex);
            for (Integer neighbour : neighbours) {
                if (path.contains(neighbour)) {
                    // we found a cycle
                    continue;
                }
                List<Integer> newPath = new ArrayList<>();
                newPath.addAll(path);
                newPath.add(neighbour);
                if (neighbour == to) {
                    // we found the druid we were looking for
                    return newPath;
                }
                nextRoundPaths.add(newPath);
            }
        }
        return findMinimumPathHelper(nextRoundPaths, graph, to);
    }

    public static List<Integer> findMinimumPath(Map<Integer, List<Integer>> graph, Integer from, Integer to) {

        List<Integer> neighbours = graph.get(from);
        if (neighbours.contains(to)) {
            // immediate neighbour
            Integer[] minimumPath = {from, to};
            return Arrays.asList(minimumPath);
        }
        Set<List<Integer>> paths = new HashSet<>();
        for (Integer neighbour : neighbours) {
            List<Integer> path = new ArrayList<>();
            path.add(from);
            path.add(neighbour);
            paths.add(path);
        }
        return findMinimumPathHelper(paths, graph, to);
    }


}
