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
     * Check if the 3rd node has nodeInfo "Node #3".
     */
    @Test
    public void nodeInfo() {
        assertEquals("Node #3", graph.getNodeList().get(2).getNodeInfo());
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
        assertEquals("Node #4", foundNodes.get(2).getNodeInfo());
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
        assertEquals("Node #2", foundNodes.get(1).getNodeInfo());
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

    @Test
    public void addNode() {
        List<Integer> connectedFrom = new ArrayList<>(Arrays.asList(2));
        List<Integer> connectedTo = new ArrayList<>(Arrays.asList(1, 2, 4));
        graph.addNode(connectedFrom, connectedTo);
        assertEquals(Arrays.asList(1, 1, 0, 1, 0), graph.getMatrix().get(4));
        assertEquals(Arrays.asList(0, 0, 0, 1, 1), graph.getMatrix().get(1));
        assertEquals(5, graph.getNodeList().size());
    }
}