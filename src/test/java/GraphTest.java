import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class GraphTest {
    Graph graph;

    /*
     * Initial set-up for all tests. Set the matrix and use it to create the graph.
     */
    @Before
    public void testSetUp() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(0,1,0,0)));
        matrix.add(new ArrayList<>(Arrays.asList(0,0,0,1)));
        matrix.add(new ArrayList<>(Arrays.asList(1,0,0,1)));
        matrix.add(new ArrayList<>(Arrays.asList(1,0,1,0)));
        graph = new Graph(matrix);
    }

    /*
     * Check the amount of nodes.
     */
    @Test
    public void nodeListSize() {
        List<Node> nodeList = graph.getNodeList();
        assertEquals(4, nodeList.size());
    }

    /*
     * Check if the 3rd node has lavel "3"
     */
    @Test
    public void nodeInfo() {
        assertEquals(Integer.valueOf(3), graph.getNodeList().get(2).getLabel());
    }

    /*
     * Test for breadthFirstSearch(). Check if the amount of found nodes is equal to the amount of
     * all nodes of the graph.
     */
    @Test
    public void bfsListSize() {
        List<Node> foundNodes = graph.breadthFirstSearch();
        assertEquals(graph.getNodeList().size(), foundNodes.size());
    }

    /*
     * Test for breadthFirstSearch(). Check if the 3rd found node is the "Node #4".
     */
    @Test
    public void bfsNode() {
        List<Node> foundNodes = graph.breadthFirstSearch();
        assertEquals(Integer.valueOf(4), foundNodes.get(2).getLabel());
    }

    /*
     * Test for breadthFirstSearch(). Check if all nodes were set to have NodeStatus.BLACK
     * after the search.
     */
    @Test
    public void bfsNodeStatuses() {
        List<Node> foundNodes = graph.breadthFirstSearch();
        boolean allNodesAreBlack = true;
        for (Node node : foundNodes) {
            if(!node.getNodeStatus().equals(NodeStatus.BLACK)){
                allNodesAreBlack = false;
                break;
            }
        }
        assertTrue(allNodesAreBlack);
    }

    /*
     * Test for depthFirstSearch(). Check if the amount of found nodes is equal to
     * the amount of all nodes of the graph.
     */
    @Test
    public void dfsListSize() {
        List<Node> foundNodes = graph.depthFirstSearch();
        assertEquals(4, foundNodes.size());
    }

    /*
     * Test for depthFirstSearch(). Check if the 2nd found node is the "Node #2".
     */
    @Test
    public void dfsNode() {
        List<Node> foundNodes = graph.depthFirstSearch();
        assertEquals(Integer.valueOf(2), foundNodes.get(1).getLabel());
    }

    /*
     * Test for breadthFirstSearch(). Check if all nodes were set to have NodeStatus.BLACK
     * after the search.
     */
    @Test
    public void dfsNodeStatuses() {
        List<Node> foundNodes = graph.depthFirstSearch();
        boolean allNodesAreBlack = true;
        for (Node node : foundNodes) {
            if(!node.getNodeStatus().equals(NodeStatus.BLACK)){
                allNodesAreBlack = false;
                break;
            }
        }
        assertTrue(allNodesAreBlack);
    }

    /*
     * Test for simple addNode().
     */
    @Test
    public void addNode() {
        graph.addNode(99);
        System.out.println(graph);
        assertEquals(5, graph.getNodeList().size());
        assertEquals(Arrays.asList(0, 1, 0, 0, 0), graph.getMatrix().get(0));
        assertEquals(Arrays.asList(0, 0, 0, 1, 0), graph.getMatrix().get(1));
        assertEquals(Arrays.asList(1, 0, 0, 1, 0), graph.getMatrix().get(2));
        assertEquals(Arrays.asList(1, 0, 1, 0, 0), graph.getMatrix().get(3));
        assertEquals(Arrays.asList(0, 0, 0, 0, 0), graph.getMatrix().get(4));
    }

    /*
     * Test for addNode(). Check the new node's relations _to_ other nodes
     * in matrix's list. Also, check the newly established relation _from_
     * another node, which was existed before adding the new one.
     */
    @Test
    public void addNodeWithLists() {
        List<Integer> connectedFrom = new ArrayList<>(Arrays.asList(2));
        List<Integer> connectedTo = new ArrayList<>(Arrays.asList(1, 2, 4));
        graph.addNode(5, connectedFrom, connectedTo);
        assertEquals(Arrays.asList(1, 1, 0, 1, 0), graph.getMatrix().get(4));
        assertEquals(Arrays.asList(0, 0, 0, 1, 1), graph.getMatrix().get(1));
        assertEquals(5, graph.getNodeList().size());
    }

    /*
    * Test for addNode() with two empty lists as parameters indicating the connections
    * with other nodes.
    */
    @Test
    public void addNodeWithEmptyLists() {
        List<Integer> connectedFrom = new ArrayList<>();
        List<Integer> connectedTo = new ArrayList<>();
        graph.addNode(5, connectedFrom, connectedTo);
        assertEquals(Arrays.asList(0, 0, 0, 0, 0), graph.getMatrix().get(4));
        assertEquals(Arrays.asList(0, 0, 0, 1, 0), graph.getMatrix().get(1));
        assertEquals(5, graph.getNodeList().size());
    }

    /**
    * Test for addNodeWithWeight().
    */
    @Test
    public void addNodeWithWeight() {
        Map<Integer, Integer> connectedFrom = new LinkedHashMap<>();
        connectedFrom.put(3, 333);
        connectedFrom.put(2, 222);
        connectedFrom.put(1, 11);

        Map<Integer, Integer> connectedTo = new LinkedHashMap<>();
        connectedTo.put(2, 95);
        connectedTo.put(4, 12);
        connectedTo.put(3, 8541);
        connectedTo.put(1, 108);

        graph.addNodeWithWeight(7, connectedFrom, connectedTo);

        assertEquals(Arrays.asList(0, 1, 0, 0, 11), graph.getMatrix().get(0));
        assertEquals(Arrays.asList(0, 0, 0, 1, 222), graph.getMatrix().get(1));
        assertEquals(Arrays.asList(1, 0, 0, 1, 333), graph.getMatrix().get(2));
        assertEquals(Arrays.asList(1, 0, 1, 0, 0), graph.getMatrix().get(3));
        assertEquals(Arrays.asList(108, 95, 8541, 12, 0), graph.getMatrix().get(4));
        assertEquals(5, graph.getMatrix().size());
    }

    /**
     * Test for addNodeWithWeight() with empty Maps in parameters.
     */
    @Test
    public void addNodeWithEmptyMaps() {
        Map<Integer, Integer> connectedFrom = new LinkedHashMap<>();
        Map<Integer, Integer> connectedTo = new LinkedHashMap<>();
        graph.addNodeWithWeight(7, connectedFrom, connectedTo);

        assertEquals(5, graph.getMatrix().size());
        assertEquals(Arrays.asList(0, 1, 0, 0, 0), graph.getMatrix().get(0));
        assertEquals(Arrays.asList(0, 0, 0, 1, 0), graph.getMatrix().get(1));
        assertEquals(Arrays.asList(1, 0, 0, 1, 0), graph.getMatrix().get(2));
        assertEquals(Arrays.asList(1, 0, 1, 0, 0), graph.getMatrix().get(3));
        assertEquals(Arrays.asList(0, 0, 0, 0, 0), graph.getMatrix().get(4));
    }

    /*
     * Test for removeNode(). Check if the method returns false whenever
     * it takes in the wrong parameter.
     */
    @Test
    public void removeNodeWrongParameter() {
        assertEquals(false, graph.removeNode(66));
        assertEquals(false, graph.removeNode(-2));
    }

    /*
     * Test for removeNode(). Check if the relations _to_ the removed node
     * were deleted in all the other nodes in the matrix.
     */
    @Test
    public void removeNode() {
        graph.removeNode(2);
        assertEquals(Arrays.asList(0,0,0), graph.getMatrix().get(0));
        assertEquals(Arrays.asList(1,0,1), graph.getMatrix().get(1));
        assertEquals(Arrays.asList(1,1,0), graph.getMatrix().get(2));
    }

    /*
    * Test for removeNode(). Check if the size of the matrix was changed.
    */
    @Test
    public void removeNodeMatrixSize() {
        graph.removeNode(2);
        assertEquals(3, graph.getMatrix().size());
    }

    /*
    * Test for removeNode(). Check if the size of the nodeList was changed.
    */
    @Test
    public void removeNodeNodelistSize() {
        graph.removeNode(2);
        assertEquals(3, graph.getNodeList().size());
    }

    /*
    * Test for merge().
    */
    @Test
    public void mergeGraphs() {
        // Create matrix and use it to create new graph
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(0,0,1)));
        matrix.add(new ArrayList<>(Arrays.asList(1,0,0)));
        matrix.add(new ArrayList<>(Arrays.asList(1,0,0)));
        Graph newGraph = new Graph(matrix);

        graph.merge(newGraph,1, 1);
        // Check the 1st, 4th and 6th rows of the matrix
        assertEquals(Arrays.asList(0, 1, 0, 0, 1, 0, 0), graph.getMatrix().get(0));
        assertEquals(Arrays.asList(1, 0, 1, 0, 0, 0, 0), graph.getMatrix().get(3));
        assertEquals(Arrays.asList(0, 0, 0, 0, 1, 0, 0), graph.getMatrix().get(6));
        assertEquals(7, graph.getMatrix().size());
    }

    /*
    * Test for merge() with merging empty graph.
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void mergeEmptyGraph() {
        List<List<Integer>> matrix = new ArrayList<>();
        Graph newGraph = new Graph(matrix);
        graph.merge(newGraph, 1, 1);
    }
}