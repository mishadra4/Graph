import java.util.*;


public class Graph  {
    List<List<Integer>> matrix = new ArrayList<>();
    List<Node> nodeList = new ArrayList<>();

    public Graph(List<List<Integer>> matrix) {
        this.matrix = matrix;
        for (int i = 0; i < matrix.size(); i++) {
            nodeList.add(new Node(i+1));
        }
    }

    public void BreadthFirstSearch(){
        Queue<Integer> nodeQueue = new PriorityQueue<>();
        int currentNode = 0;
        nodeList.get(currentNode).setNodeStatus(NodeStatus.GREY);
        nodeQueue.add(currentNode);
        System.out.println(nodeList.get(currentNode).getNodeInfo());//Open first node and push it to queue

        while(!nodeQueue.isEmpty()) { //cycle discovers all neighbour nodes
            currentNode = nodeQueue.remove();
            for (int i = 0; i < nodeList.size(); i++){ //work out all ways which are going from current queue node
                if(matrix.get(currentNode).get(i) != 0 && nodeList.get(currentNode).getNodeStatus() != NodeStatus.WHITE){
                    nodeList.get(i).setNodeStatus(NodeStatus.GREY);
                    nodeQueue.add(i);
                    System.out.println(nodeList.get(i).getNodeInfo());
                }
            }
            nodeList.get(currentNode).setNodeStatus(NodeStatus.WHITE);
        }

        for (Node node : nodeList) {
            node.setNodeStatus(NodeStatus.BLACK);
        }
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
}

