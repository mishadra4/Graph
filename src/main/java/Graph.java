import java.util.*;

public class Graph implements Comparable<Graph> {
    private List<List<Integer>> matrix = new ArrayList<>();
    private List<Node> nodeList = new ArrayList<>();

    public Graph(List<List<Integer>> matrix) {
        this.matrix = matrix;
        for (int i = 0; i < matrix.size(); i++) {
            nodeList.add(new Node(i + 1));
        }
    }

    // Constructor to create copies
    public Graph(Graph graph) {
        this.matrix = new ArrayList<>(graph.matrix);
        this.nodeList = new ArrayList<>(graph.nodeList);
    }

    public void addPath(Integer node1, Integer node2, Integer weight) {
        matrix.get(node1 - 1).set(node2 - 1, weight);
    }

    public void removePath(Integer node1, Integer node2) {
        matrix.get(node1).set(node2, 0);
    }

    /*
     * Method to add a node to the graph without any connections with other nodes.
     * Take in a label of the node.
     */
    public void addNode(int label) {
        // Create new Node
        nodeList.add(new Node(label));

        // Iterate over all existing nodes (except the new one).
        for (int nodeIndex = 0; nodeIndex < nodeList.size() - 1; nodeIndex++) {
            // Set the relation to the new node as "0".
            matrix.get(nodeIndex).add(0);
        }

        // Create new List that will indicate to which nodes the new node is connected.
        // This list will be added to the matrix List.
        List<Integer> adjacentList = new ArrayList<>();

        // Set all connections as "0".
        for (int nodeIndex = 0; nodeIndex < nodeList.size(); nodeIndex++) {
            adjacentList.add(0);
        }
        // Append the list to the matrix.
        matrix.add(adjacentList);
    }

    /** Method to add a node to the graph with set connections with other nodes.
     * Take in a label of the node and two lists: the first one with the numbers of
     * nodes, _which_ will be connected to the new node. The second one with
     * the numbers of nodes, _to which_ the new node will be connected. The lists
     * contain numbers in natural order (i.e. "1" means the node with the index [0]).
     */
    public void addNode(int label, List<Integer> connectedFrom, List<Integer> connectedTo) {
        // Create new Node
        nodeList.add(new Node(label));

        // Iterate over all existing nodes (except the new one).
        // If a node is present in connectedFrom list from parameters, the relation with the new node
        // will be set as "1" in the matrix. Otherwise, as "0".
        for (int nodeIndex = 0; nodeIndex < nodeList.size() - 1; nodeIndex++) {
            if(!connectedFrom.isEmpty()){
                for (Integer indexOfConnectedFromNode : connectedFrom) {
                    if (nodeIndex == indexOfConnectedFromNode.intValue() - 1) {
                        matrix.get(nodeIndex).add(1);
                    } else{
                        matrix.get(nodeIndex).add(0);
                    }
                }
            }
            // If connectedFrom is empty, set the relation from existing nodes to the new one as "0".
            else {
                matrix.get(nodeIndex).add(0);
            }
        }
        // Create new List that will indicate to which nodes the new node is connected.
        // This list will be added to the matrix List.
        List<Integer> adjacentList = new ArrayList<>();

        // Iterate over all Nodes and check if the index of a node is present in connectedTo list
        // from parameters.
        for (int nodeIndex = 0; nodeIndex < nodeList.size(); nodeIndex++) {
            // If the connectedTo list is empty, set the relation with the current iterated node as "0".
            if(connectedTo.isEmpty()){
                adjacentList.add(0);
                continue;
            }
            // Otherwise, iterate over the connectedTo list and check if the node is present in this list.
            // If present, set the relation as "1" and remove the node from connectedTo (it's unnecessary now,
            // because we have set the relation already).
            else {
                for (int indexOfConnectedToListElement = 0; // It's just index of connectedTo's element!
                                                            // Not the index of a node.
                     indexOfConnectedToListElement < connectedTo.size();
                     indexOfConnectedToListElement++) {
                    // And here is the index of a node which should be connected to.
                    int indexOfConnectedToNode = connectedTo.get(indexOfConnectedToListElement) - 1;
                    if (nodeIndex == indexOfConnectedToNode) {
                        adjacentList.add(1);
                        connectedTo.remove(indexOfConnectedToListElement);
                    } else {
                        adjacentList.add(0);
                    }
                    break;
                }
            }
        }
        // Finally, add the list with connectedTo relations to the matrix.
        matrix.add(adjacentList);
    }

    /**
     * Remove node from the graph. Take in natural order of the node.
     */
    public boolean removeNode(int node) {
        // Return false if the input value is out of the bounds of nodeList.
        if (node <= 0 || node >= nodeList.size()) {
            return false;
        }

        // Remove the Node from the nodeList.
        nodeList.remove(node - 1);
        // Remove the list of the Node from the matrix.
        matrix.remove(node - 1);

        // Iterate over lists of all nodes in matrix and delete relations with
        // the node to be deleted.
        for (List<Integer> list : matrix) {
            list.remove(node - 1);
        }
        return true;
    }

    public List<Node> breadthFirstSearch(){
        List<Node> result = new ArrayList<>(); // result list returned by method
        Queue<Integer> nodeQueue = new PriorityQueue<>();
        int currentNode = 0;
        nodeList.get(currentNode).setNodeStatus(NodeStatus.GREY);
        nodeQueue.add(currentNode);
        result.add(nodeList.get(currentNode));//Open first node and push it to queue

        while (!nodeQueue.isEmpty()) { //cycle discovers all neighbour nodes
            currentNode = nodeQueue.remove();
            for (int i = 0; i < nodeList.size(); i++) { //work out all ways which are going from current queue node
                if (matrix.get(currentNode).get(i) != 0 && nodeList.get(i).getNodeStatus() == NodeStatus.BLACK) {
                    nodeList.get(i).setNodeStatus(NodeStatus.GREY);
                    if (nodeList.get(i).getNodeStatus() != NodeStatus.WHITE) {
                        nodeQueue.add(i);
                    }
                    result.add(nodeList.get(i));
                }
            }
            nodeList.get(currentNode).setNodeStatus(NodeStatus.WHITE);
        }

        for (Node node : nodeList) {
            node.setNodeStatus(NodeStatus.BLACK);
        }
        return result;
    }

    public List<Node> depthFirstSearch() {
        List<Node> result = new ArrayList<>();
        int currentNode = 0;
        Queue<Integer> nodeStack = new ArrayDeque();
        //nodeList.get(currentNode).setNodeStatus(NodeStatus.GREY);
        nodeStack.add(currentNode); //Open first node and push it to stack
        result.add(nodeList.get(currentNode));

        while (!nodeStack.isEmpty()) //cycle discovers all neighbour nodes
        {
            currentNode = nodeStack.peek();
            for (int i = 0; i < nodeList.size(); i++) { //work out all ways which are going from current queue node
                if (matrix.get(currentNode).get(i) != 0 && nodeList.get(i).getNodeStatus() == NodeStatus.BLACK) {
                    if (nodeList.get(i).getNodeStatus() == NodeStatus.BLACK) {
                        nodeStack.add(i);
                        //nodeList.get(i).setNodeStatus(NodeStatus.WHITE);
                    }
                    result.add(nodeList.get(i));
                }
            }
            nodeStack.remove();
            nodeList.get(currentNode).setNodeStatus(NodeStatus.WHITE);
        }
        for (Node node : nodeList) {
            node.setNodeStatus(NodeStatus.BLACK);
        }
        return result;
    }

    public List<List<Integer>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<Integer>> matrix) {
        this.matrix = matrix;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "matrix=" + matrix +
                ", nodeList=" + nodeList +
                '}';
    }

    @Override
    public int compareTo(Graph o) {
        if (this.getNodeList().size() > o.getNodeList().size()) {
            return 1;
        }
        if (this.getNodeList().size() < o.getNodeList().size()) {
            return -1;
        }
        return 0;
    }
}