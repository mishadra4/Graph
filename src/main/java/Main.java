import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create adjacency matrix
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(0, 1, 0, 0)));
        matrix.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(1, 0, 0, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(1, 0, 1, 0)));
        // Create graph using the matrix
        Graph graph = new Graph(matrix);

        // Delete the 2nd element
        graph.removeNode(2);

        // Add edge from node #1 to node #3 with weight "789"
        graph.addEdge(2, 3, 789);

        // Add new isolated node with the label "64"
        graph.addNode(64);

        // Create new matrix and new graph
        List<List<Integer>> newMatrix = new ArrayList<>();
        newMatrix.add(new ArrayList<>(Arrays.asList(0, 34, 1)));
        newMatrix.add(new ArrayList<>(Arrays.asList(1, 0, 0)));
        newMatrix.add(new ArrayList<>(Arrays.asList(55, 0, 0)));
        Graph newGraph = new Graph(newMatrix);

        // Merge our graph on the 2nd node with the newly created one on the 3rd node
        graph.merge(newGraph, 2, 3);

        // Make search of all elements using the bread first search algorithm
        List<Node> foundNodes = graph.breadthFirstSearch();
        System.out.println(foundNodes);

        // Adding new node
        // Here we create maps with connections and weights
        Map<Integer, Integer> connectedFrom = new LinkedHashMap<>();
        connectedFrom.put(3, 333);
        connectedFrom.put(2, 222);
        connectedFrom.put(1, 11);

        Map<Integer, Integer> connectedTo = new LinkedHashMap<>();
        connectedTo.put(2, 95);
        connectedTo.put(4, 12);
        connectedTo.put(3, 8541);
        connectedTo.put(1, 108);

        // And here we add the node
        graph.addNodeWithWeight(25, connectedFrom, connectedTo);

        System.out.println(graph);
    }
}
