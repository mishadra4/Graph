import java.util.*;


public class Graph implements Comparable<Graph> {
    List<List<Integer>> matrix = new ArrayList<>();
    List<Node> nodeList = new ArrayList<>();

    public Graph(List<List<Integer>> matrix) {
        this.matrix = matrix;
        for (int i = 0; i < matrix.size(); i++) {
            nodeList.add(new Node(i + 1));
        }
    }

    public void addPath(Integer node1, Integer node2, Integer weight) {
        matrix.get(node1 - 1).set(node2 - 1, weight);
    }

    public void removePath(Integer node1, Integer node2) {
        matrix.get(node1).set(node2, 0);
    }


    public List<Node> breadthFirstSearch() {
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
                "nodeList=" + nodeList +
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


    /**
     * Add new node to the graph.
     */
    public void addNode(Integer node, Map<Integer, Integer> paths) {

        if (node <= nodeList.size()) {
            System.out.println("node already exists.");
        }
        nodeList.add(new Node(node));//adding new node without paths
        List<Integer> list = new ArrayList<>();//paths for this node
        matrix.add(list);

        for (int i = 0; i < node - 1; i++) {
            for (Integer j : paths.keySet()
                    ) {
                if (i == j) {//checking if index i equals to number of node to which we have to build path
                    list.add(i, paths.get(i));//if yes adding weight
                    addPath(node, i, paths.get(i));
                }
                list.add(i, 0);//if no? adding zero
            }
        }


    }

    /**
     * remove node to the graph with its paths.
     */
    public boolean removeNode(Integer node) {

        if (node >= nodeList.size() || node<=0) {
            System.out.println("node doesnt exist.");
            return false;
        }

        nodeList.remove(node - 1);//removind node from nodelist
        matrix.remove(node - 1);//removing from matrix
        return true;
    }

}

