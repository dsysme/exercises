package com.dsysme.exercises.graphs;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GraphsAlgorithmsTest {


    private Map<Integer, List<Integer>> readGraphFromFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        String graphString = "";
        try {
            graphString = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            fail("failed to read test case from file: " + filename);
        }
        GraphParser parser = new GraphParser();
        return Collections.unmodifiableMap(parser.parseGraph(graphString).getGraph());
    }

    private List<Integer> readExpectedResult(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        String result = "";
        try {
            result = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            fail("failed to read test case from file: " + filename);
        }
        // TODO what happens if exception is thrown?
        return Arrays.stream(result.split("\\s*,\\s*")).map(element -> Integer.valueOf(element)).collect(Collectors.toList());
    }

    // expected result depends on the order right? In theory at least the returned list may be {1,2,3,6,4,5} right?
    // TODO should I create list of valid results?
    @Test
    void DFSOnBiDirectionalCyclicGraph() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/bid-cone");
        List<Integer> expected = readExpectedResult("graphs/expected/dfs-bid-cone");
        int root = 1;
        List<Integer> actual = GraphsAlgorithms.getDFS(graph);
        assertEquals(expected, actual);
    }

    @Test
    void DFSOnUniDirectionalACyclicGraph() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/unid-acyclic-cone");
        List<Integer> expected = readExpectedResult("graphs/expected/dfs-unid-acyclic-cone");
        int root = 1;
        List<Integer> actual = GraphsAlgorithms.getDFS(graph);
        assertEquals(expected, actual);
    }

    @Test
    void DFSOnUniDirectionalCyclicGraph() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/unid-cyclic-cone");
        List<Integer> expected = readExpectedResult("graphs/expected/dfs-unid-cyclic-cone");
        List<Integer> actual = GraphsAlgorithms.getDFS(graph);
        assertEquals(expected, actual);
    }

    @Test
    void BFSOnBiDirectionalCyclicGraph() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/bid-cone");
        List<Integer> expected = readExpectedResult("graphs/expected/bfs-bid-cone");
        int root = 1;
        List<Integer> actual = GraphsAlgorithms.getBFS(graph);
        assertEquals(expected, actual);
    }

    @Test
    void BFSOnUniDirectionalACyclicGraph() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/unid-acyclic-cone");
        List<Integer> expected = readExpectedResult("graphs/expected/bfs-unid-acyclic-cone");
        List<Integer> actual = GraphsAlgorithms.getBFS(graph);
        assertEquals(expected, actual);
    }

    @Test
    void BFSOnUniDirectionalCyclicGraph() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/unid-cyclic-cone");
        List<Integer> expected = readExpectedResult("graphs/expected/bfs-unid-cyclic-cone");
        List<Integer> actual = GraphsAlgorithms.getBFS(graph);
        assertEquals(expected, actual);
    }

    @Test
    void isCyclicBiDirectionalCyclicOneConnectedComponent() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/bid-cone");
        assertTrue(GraphsAlgorithms.isCyclic(graph));
    }

    @Test
    void isCyclicUniDirectionalAcyclicOneConnectedComponent() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/unid-acyclic-cone");
        assertFalse(GraphsAlgorithms.isCyclic(graph));
    }

    @Test
    void isCyclicUniDirectionalCyclicOneConnectedComponent() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/unid-cyclic-cone");
        assertTrue(GraphsAlgorithms.isCyclic(graph));
    }

    @Test
    void getConnectedComponentsForGraphWithOneComponent() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/bid-cone");
        List<Map<Integer, List<Integer>>> graphs = GraphsAlgorithms.getConnectedComponents(graph);
        assertTrue(graphs.size() == 1);
        assertEquals(graph, graphs.get(0));
    }

    @Test
    void getConnectedComponentsForBiDirectionalGraphWithManyComponent() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/bid-cmany");
        Map<Integer, List<Integer>> expectedFirstComponent = readGraphFromFile("graphs/expected/cc1-bid-cmany");
        Map<Integer, List<Integer>> expectedSecondComponent = readGraphFromFile("graphs/expected/cc2-bid-cmany");
        List<Map<Integer, List<Integer>>> graphs = GraphsAlgorithms.getConnectedComponents(graph);
        assertTrue(graphs.size() == 2);
        assertEquals(expectedFirstComponent, graphs.get(0));
        assertEquals(expectedSecondComponent, graphs.get(1));
    }

    @Test
    void getConnectedComponentsForUniDirectionalGraphWithManyComponent() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/unid-acyclic-cmany");
        Map<Integer, List<Integer>> expectedFirstComponent = readGraphFromFile("graphs/expected/cc1-unid-acyclic-cmany");
        Map<Integer, List<Integer>> expectedSecondComponent = readGraphFromFile("graphs/expected/cc2-unid-acyclic-cmany");
        List<Map<Integer, List<Integer>>> graphs = GraphsAlgorithms.getConnectedComponents(graph);
        assertTrue(graphs.size() == 2);
        assertEquals(expectedFirstComponent, graphs.get(0));
        assertEquals(expectedSecondComponent, graphs.get(1));
    }

    @Test
    void findMinimumPathOfSizeOne() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/big-graph");
        List<Integer> actual = GraphsAlgorithms.findMinimumPath(graph,3,5);
        Integer[] expectedArr = {3,5};
        List<Integer> expected = Arrays.asList(expectedArr);
        assertEquals(expected, actual);
    }

    @Test
    void findMinimumPathOfWhenPathDoesNotExists() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/big-graph-cmany");
        List<Integer> actual = GraphsAlgorithms.findMinimumPath(graph,7,4);
        assertEquals(Collections.EMPTY_LIST, actual);
    }

    @Test
    void findMinimumPath() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/big-graph");
        List<Integer> actual = GraphsAlgorithms.findMinimumPath(graph,14,2);
        Integer[] expectedArr = {14,11,10,6,2};
        List<Integer> expected = Arrays.asList(expectedArr);
        assertEquals(expected, actual);

    }

    @Test
    void getOrderedListForDAG() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/unid-acyclic-cmany");
        List<Integer> actual = GraphsAlgorithms.getOrderedList(graph);
        System.out.println(actual);
    }

    @Test
    void getOrderedListForCyclicGraph() {
        Map<Integer, List<Integer>> graph = readGraphFromFile("graphs/big-graph");
        assertThrows(IllegalArgumentException.class, () -> { GraphsAlgorithms.getOrderedList(graph); });
    }
}



