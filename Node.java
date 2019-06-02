package ex03;

import java.util.ArrayList;

class Node {

    String name;
    private ArrayList<Edge> edgesForNode = new ArrayList<>();

    Node(String name) {
        this.name = name;
    }

    public void addEdges(Edge edge) {
        edgesForNode.add(edge);
    }
    public String getName(){
        return name;
    }

    public ArrayList<Edge> getEdges(){
        return edgesForNode;
    }

    public Edge deleteEdge() {
        if (edgesForNode.size() != 0) {
            return edgesForNode.remove(edgesForNode.size() - 1);
        } else {
            return null;
        }
    }
}
