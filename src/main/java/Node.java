public class Node {
    NodeStatus nodeStatus;
    String nodeInfo = "I`m node ¹";

    public Node(int orderNumber) {
        nodeStatus = NodeStatus.BLACK;
        this.nodeInfo += orderNumber;
    }

    public NodeStatus getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(NodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(String nodeInfo) {
        this.nodeInfo = nodeInfo;
    }
}
