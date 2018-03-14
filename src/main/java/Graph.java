import java.util.*;

/**
 * Graph based on adjacency matrix.
 */
public class Graph implements Comparable<Graph> {
    private List<List<Integer>> matrix; // adjacency matrix
    private List<Node> nodeList;        // list of nodes

    /**
     * Empty constructor which initialize lists.
     */
    public Graph() {
        matrix = new ArrayList<>();
        nodeList = new ArrayList<>();
    }

    /**
     * Creates nodeList based on the taken in adjacency matrix.
     * @param matrix is a adjacency matrix with set relations of nodes
     */
    public Graph(List<List<Integer>> matrix) {
        this();
        this.matrix = matrix;
        for (int i = 0; i < matrix.size(); i++) {
            nodeList.add(new Node(i + 1));
        }
    }

    /**
     * Add edge to the node.
     * @param source is a starting node.
     * @param destination is a destination node.
     * @param weight is a weight of the relation.
     */
    public void addEdge(int source, int destination, int weight) {
        matrix.get(source - 1).set(destination - 1, weight);
    }

    /**
     * Remove edge of the node.
     * @param source is a starting node.
     * @param destination is a destination node.
     */
    public void removeEdge(int source, int destination) {
        matrix.get(source - 1).set(destination - 1, 0);
    }

    /**
     * Return the weight of nodes' edge.
     * @param source is a starting node.
     * @param destination is a destination node.
     */
    public int getEdgeWeight(int source, int destination) {
        return matrix.get(source - 1).get(destination - 1);
    }

    /**
     * Check if the edge has relation.
     * @param source is a starting node.
     * @param destination is a destination node.
     */
    public boolean hasEdge(int source, int destination) {
        return matrix.get(source - 1).get(destination - 1) != 0;
    }

    /**
     * Add an isolated node to the graph.
     * @param label is a label of new node.
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

    /** Add a node to the graph with set connections to other nodes.
     * @param label is a label of the new node.
     * @param connectedFrom is a list  with the numbers of nodes, _which_ will be
     *                      connected to the new node.
     * @param connectedTo is a list with the numbers of nodes, _to which_ the new
     *                    node will be connected.
     * The lists contain numbers in natural order (i.e. "1" means the node with
     * the index [0]).
     * The method uses addNodeWithWeight() with Maps as parameters.
     */
    public void addNode(int label, List<Integer> connectedFrom, List<Integer> connectedTo) {
        Map<Integer, Integer> connectedFromMap = new LinkedHashMap<>();
        // Convert the list to the Map with "1" as values
        for (Integer nodeNumber : connectedFrom) {
            connectedFromMap.put(nodeNumber, 1);
        }
        Map<Integer, Integer> connectedToMap = new LinkedHashMap<>();
        // Convert the list to the Map with "1" as values
        for (Integer nodeNumber : connectedTo) {
            connectedToMap.put(nodeNumber, 1);
        }
        // Delegate to the method addNodeWithWeight
        addNodeWithWeight(label, connectedFromMap, connectedToMap);
    }

    /** Add a node to the graph with set connections with other nodes
     * and set weight.
     * @param label is a label of new node.
     * @param connectedFrom is a map with the numbers of nodes, _which_ will be
     *                      connected to the new node, as keys and with
     *                      weight as values.
     * @param connectedTo is a map with the numbers of nodes, _to which_ the new
     *                    node will be connected, as keys and with wight
     *                    as values.
     * The maps contain numbers in natural order (i.e. "1" means the node with
     * the index [0]).
     */
    public void addNodeWithWeight(int label, Map<Integer, Integer> connectedFrom,
                                  Map<Integer, Integer> connectedTo) {
        // Create new Node
        nodeList.add(new Node(label));

        // Get list of connectedFrom map's keys
        List<Integer> connectedFromMapKeys = new LinkedList<>(connectedFrom.keySet());

        // Iterate over all existing nodes (except the new one).
        // If a node is present in connectedFrom's key list, the relation with it
        // will be set in the matrix as weight parameter. Otherwise, as "0"
        nodesIteration: for (int nodeIndex = 0; nodeIndex < matrix.size(); nodeIndex++) {
            // Check if the connectedFrom map is not empty
            if(!connectedFromMapKeys.isEmpty()){
                for (int indexOfConnectedFromMapElement = 0;    // index of connectedFromMapKeys' element
                     indexOfConnectedFromMapElement < connectedFromMapKeys.size();
                     indexOfConnectedFromMapElement++) {
                    // Get node's index from connectedFromMapKeys
                    int indexOfConnectedFromNode = connectedFromMapKeys.get(indexOfConnectedFromMapElement) - 1;
                    // If current nodeIndex of nodesIteration is present in connectedFrom map,
                    // get the weight from map and set it as the relation
                    if (nodeIndex == indexOfConnectedFromNode) {
                        // Add weight
                        matrix.get(nodeIndex).add(connectedFrom.get(indexOfConnectedFromNode + 1));
                        // Remove the key, 'cause it has been already set
                        connectedFromMapKeys.remove(indexOfConnectedFromMapElement);
                        continue nodesIteration; //  Go to the next matrix's node in iteration
                    }
                }
                // If current iterated node hasn't been found in the connectedFrom map,
                // set the relation as "0"
                matrix.get(nodeIndex).add(0);
            }
            // If connectedFrom map is empty, set the relation from current iterated node
            // to the new one as "0".
            else {
                matrix.get(nodeIndex).add(0);
            }
        }

        // Create new List that will indicate to which nodes the new node is connected.
        // This list will be added to the matrix List.
        List<Integer> adjacentList = new ArrayList<>();

        // Get list of connectedTo map's keys
        List<Integer> connectedToMapKeys = new LinkedList<>(connectedTo.keySet());

        // Iterate over all Nodes and check if the index of a node is present in connectedTo map
        connectedToIteration: for (int nodeIndex = 0; nodeIndex < matrix.size() + 1; nodeIndex++) {
            // If the connectedTo is empty, set the relation with the current iterated node as "0".
            if(connectedToMapKeys.isEmpty()){
                adjacentList.add(0);
            }
            // Otherwise, iterate over the connectedTo's keys list and check if the node is present
            // in this list. If so, set the relation as the value of weight from map and remove the
            // node from connectedToMapKeys.
            else {
                for (int indexOfConnectedToKeysElement = 0; // It's just an index of connectedTo's element!
                                                            // Not the index of a node.
                     indexOfConnectedToKeysElement < connectedToMapKeys.size();
                     indexOfConnectedToKeysElement++) {
                    // And here is the index of a node which should be connected to.
                    int indexOfConnectedToNode = connectedToMapKeys.get(indexOfConnectedToKeysElement) - 1;
                    // If current nodeIndex of connectedToIteration is present in connectedTo map,
                    // get the weight from map and set it as the relation
                    if (nodeIndex == indexOfConnectedToNode) {
                        // Add the weight
                        adjacentList.add(connectedTo.get(indexOfConnectedToNode + 1));
                        // Remove the key, 'cause it has been already set
                        connectedToMapKeys.remove(indexOfConnectedToKeysElement);
                        continue connectedToIteration;  //  Go to the next matrix's node in iteration
                    }
                }
                adjacentList.add(0);
            }
        }
        // Finally, add the list with connectedTo relations to the matrix.
        matrix.add(adjacentList);

    }

    /**
     * Remove node from the graph.
     * @param node is a node to delete in natural order
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

    /**
     * Make search of all nodes using bread first search algorithm.
     */
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

    /**
     * Make search of all nodes using depth first search algorithm.
     */
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
    
    /** Merge graph with other one and build new path between them.
     * Modifies the object.
     * @param graph graph to merge
     * @param mergeNode1 node label from graph to build path from 
     * @param mergeNode2 node label from parameter graph to build path to.
     */
    public void merge(Graph graph, int mergeNode1, int mergeNode2) {
    	// merge matrix
    	List<List<Integer>> newMatrix = new ArrayList<>();
    	for(List<Integer> row : matrix) {
    		newMatrix.add(new ArrayList<Integer>(row));
    	}
    	List<Integer> zeroList1 = new ArrayList<>();
    	for(int i = 0; i < graph.matrix.size(); i++) {
    		zeroList1.add(0);
    	}
    	List<Integer> zeroList2 = new ArrayList<>();
    	for(int i = 0; i < newMatrix.size(); i++) {
    		zeroList2.add(0);
    	}
    	for(List<Integer> row : newMatrix) {
    		row.addAll(zeroList1);
    	}
    	for(List<Integer> row : graph.matrix) {
    		List<Integer> newRow = new ArrayList<>();
    		newRow.addAll(zeroList2);
    		newRow.addAll(row);
    		newMatrix.add(newRow);
    	}
    	
    	this.matrix = newMatrix;
    	
    	//merge nodeList
    	int oldSize = nodeList.size();
    	for(Node newNode : graph.nodeList) {
    		nodeList.add(new Node(newNode.getLabel()+oldSize));
    	}
    	
    	//add Path
    	addEdge(mergeNode1, mergeNode2+oldSize, 1);
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
        StringBuilder result = new StringBuilder();
        result.append("Adjacency matrix:\n");
        for (List<Integer> matrixRow : matrix) {
            result.append(matrixRow).append("\n");
        }
        result.append("\nNode list: \n");
        for (Node node : nodeList) {
            result.append(node.toString() + "\n");
        }
        return result.toString();
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