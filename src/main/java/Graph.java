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

    public void BFS(){
        Queue<Integer> nodeQueue = new PriorityQueue<>();
        int currentNode = 0;
        nodeList.get(currentNode).setNodeStatus(NodeStatus.GREY);
        nodeQueue.add(currentNode);
        int vertex;


        System.out.println(nodeList.get(currentNode).getNodeInfo());

        /*while(!nodeQueue.isEmpty())
        {
            int current = nodeQueue.remove();
            while((vertex = getSuccessor(current)) != -1)
            {
                nodeList.get(vertex).setNodeStatus(NodeStatus.GREY);
                nodeQueue.add(vertex);
                System.out.println(nodeList.get(vertex).getNodeInfo());
            }

        }*/

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

