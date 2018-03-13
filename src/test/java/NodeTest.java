import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {
    Node node;

    /*
     * Initial set-up for all tests.
     */
    @Before
    public void testSetUp() {
        node = new Node(42);
    }

    /*
     * Check if the node has right nodeInfo.
     */
    @Test
    public void getNodeInfo() {
        assertEquals("Node #42", node.getNodeInfo());
    }

    /*
     * Check if the new node has NodeStatus.BLACK
     */
    @Test
    public void getNodeStatus() {
        assertEquals(NodeStatus.BLACK, node.getNodeStatus());
    }

    /*
     * Check if the node has right sequence number.
     */
    @Test
    public void getSequenceNumber() {
        assertEquals(Integer.valueOf(42), node.getSequenceNumber());
    }
}