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

    /*
     * Test for addNode(). Check the new node's relations _to_ other nodes
     * in matrix's list. Also, check the newly established relation _from_
     * another node, which was existed before adding the new one.
     */
    @Test
    public void addNode() {
        List<Integer> connectedFrom = new ArrayList<>(Arrays.asList(2));
        List<Integer> connectedTo = new ArrayList<>(Arrays.asList(1, 2, 4));
        graph.addNode(5, connectedFrom, connectedTo);
        assertEquals(Arrays.asList(1, 1, 0, 1, 0), graph.getMatrix().get(4));
        assertEquals(Arrays.asList(0, 0, 0, 1, 1), graph.getMatrix().get(1));
        assertEquals(5, graph.getNodeList().size());
    }

    @Test
    public void addNodeWithEmptyLists() {
        List<Integer> connectedFrom = new ArrayList<>();
        List<Integer> connectedTo = new ArrayList<>();
        graph.addNode(5, connectedFrom, connectedTo);
        assertEquals(Arrays.asList(0, 0, 0, 0, 0), graph.getMatrix().get(4));
        assertEquals(Arrays.asList(0, 0, 0, 1, 0), graph.getMatrix().get(1));
        assertEquals(5, graph.getNodeList().size());
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
}