package ex03;
class Edge {

    private Node firstNode;
    private Node secondNode;
    private int weight;

    Edge(Node firstNode, Node secondNode, int weight) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.weight = weight;
    }

    public Node getFirstNode(){
        return firstNode;
    }

    public int getWeight(){
        return weight;
    }

    public Node getSecondNode(){
        return secondNode;
    }

}
