public class Node {
    NodeStatus nodeStatus;
    String nodeInfo = "Node #";
    Integer sequenceNumber;

    public Node(int sequenceNumber) {
        nodeStatus = NodeStatus.BLACK;
        this.nodeInfo += sequenceNumber;
        this.sequenceNumber = sequenceNumber;
    }

    public Node(String nodeInfo, int sequenceNumber) {
        nodeStatus = NodeStatus.BLACK;
        this.nodeInfo =nodeInfo+ sequenceNumber;
        this.sequenceNumber = sequenceNumber;
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

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeInfo='" + nodeInfo + '\'' +
                '}';
    }
}
