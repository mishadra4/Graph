import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        int matr[][] = {{0,1,1,0},{0,0,1,0},{1,0,0,1},{1,0,0,0}};
        matrix.add(Arrays.asList(0,1,1,0));
        matrix.add(Arrays.asList(0,0,1,0));
        matrix.add(Arrays.asList(1,0,0,1));
        matrix.add(Arrays.asList(1,0,0,0));
        Graph graph = new Graph(matrix);
        graph.BreadthFirstSearch();
    }
}
