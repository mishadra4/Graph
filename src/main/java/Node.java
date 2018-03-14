public class Node {
    NodeStatus nodeStatus;
    Integer label;

    public Node(int label) {
        nodeStatus = NodeStatus.BLACK;
        this.label = label;
    }

    public NodeStatus getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(NodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
    }


    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Node{" +
                "label=" + label +
                ", nodeStatus=" + nodeStatus +
                '}';
    }
}
