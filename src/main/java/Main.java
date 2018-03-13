import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(0,1,0,0));
        matrix.add(Arrays.asList(0,0,0,1));
        matrix.add(Arrays.asList(1,0,0,1));
        matrix.add(Arrays.asList(1,0,1,0));
        Graph graph = new Graph(matrix);
        graph.BreadthFirstSearch();
        Map<Integer, Integer> paths=new HashMap<>();
        paths.put(2,3);
        graph.addNode(5,paths);
        graph.BreadthFirstSearch();
        System.out.println(graph.toString());
        graph.DepthFirstSearch();
    }
}
