package ex03;

import java.util.*;

class Graph {

    private HashSet<Node> nodes = new HashSet<>(100);
    private HashSet<Edge> edges = new HashSet<>(100);

    /**
     * Adds node to graph if it is valid.
     *
     * @param name name of node to add
     * @return node if node added, null otherwise
     */
    public Node addNode(String name) {
        Node tmpNode = new Node(name);
        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(tmpNode.getName())) {
                return tmpNode;
            }
        }
        if (nodes.add(tmpNode)) {
            return tmpNode;
        } else {
            return null;
        }
    }

    /**
     * Adds an edge to the graph if it is valid.
     *
     * @param edge edge to addreturn false;
     * @return edge if edge added, exception otherwise
     */
    public Edge addEdge(Edge edge) {
        if (edge.getWeight() < 0) {
            throw new NegativeWeight();
        }
        Iterator<Edge> itfirst = edges.iterator();
        Iterator<Edge> itsecond = edges.iterator();
        while (itfirst.hasNext()) {
            Node first = itfirst.next().getFirstNode();
            Node second = itsecond.next().getSecondNode();
            if (first.getName().equals(edge.getFirstNode().getName()) && second.getName().equals(edge.getSecondNode().getName())) {
                return edge;
            } else if (second.getName().equals(edge.getFirstNode().getName()) && first.getName().equals(edge.getSecondNode().getName())) {
                return edge;
            }
        }
        if (edges.add(edge)) {
            edge.getFirstNode().addEdges(edge);
            edge.getSecondNode().addEdges(edge);
            return edge;
        } else {
            return null;
        }
    }

    /**
     * Removes the given node.
     *
     * @param node node to remove
     * @return true if removed, false if it was not inside the graph
     */
    public boolean removeNode(Node node) {
        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(node.getName())) {
                Edge stash;
                boolean tmp = true;
                while (tmp) {
                    if ((stash = node.deleteEdge()) != null) {
                        removeEdge(stash);
                    }
                    if (stash == null) {
                        tmp = false;
                    }
                }
                nodes.remove(node);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the given edge.
     *
     * @param edge edge to remove
     * @return true if removed, false if it was not inside the graph
     */
    public boolean removeEdge(Edge edge) {
        Iterator<Edge> itfirst = edges.iterator();
        Iterator<Edge> itsecond = edges.iterator();
        while (itfirst.hasNext()) {
            Node first = itfirst.next().getFirstNode();
            Node second = itsecond.next().getSecondNode();
            if (first.getName().equals(edge.getFirstNode().getName()) && second.getName().equals(edge.getSecondNode().getName())) {
                edges.remove(edge);
                return true;
            } else if (second.getName().equals(edge.getFirstNode().getName()) && first.getName().equals(edge.getSecondNode().getName())) {
                edges.remove(edge);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the graph is connected or not.
     * This means that a path exists from any node to any other node.
     *
     * @return true if the graph is connected and false otherwise
     */

    //BFS with Queue, Set and List
    public boolean checkConnectivity() {
        if (nodes.isEmpty()) {
            return true;
        }

        List<Node> allNode = new ArrayList<>();
        Queue<Node> nodesFound = new LinkedList<>();
        Set<Node> nodesConnected = new HashSet<>();
        List<Edge> allEdge = new ArrayList<>();

        allNode.addAll(nodes);
        nodesFound.offer(allNode.get(0));
        nodesConnected.add(allNode.get(0));

        while (!nodesFound.isEmpty()) {
            Node node = nodesFound.poll();
            allEdge = node.getEdges();
            for (int i = allEdge.size()-1; i > 0; i--) {
                if (allEdge.get(i).getFirstNode() == node) {
                    if (!nodesConnected.contains((allEdge.get(i).getSecondNode()))) {
                        nodesConnected.add(allEdge.get(i).getSecondNode());
                        nodesFound.add(allEdge.get(i).getSecondNode());
                    }
                } else if (allEdge.get(i).getSecondNode() == node) {
                    if (!nodesConnected.contains((allEdge.get(i).getFirstNode()))) {
                        nodesConnected.add(allEdge.get(i).getFirstNode());
                        nodesFound.add(allEdge.get(i).getFirstNode());
                    }
                }
            }
            if (nodesConnected.size() == allNode.size()) {
                return true;
            }
        }
        return false;

    }

    /**
     * @param firstNode the node where to start
     * @return every node mapped to the edge to reach it
     */
    // Rückgabewert auf genutzte Collection ändern!
    public Object calculateShortestPaths(Node firstNode) {
        return null;
    }

    public void iteratoredgefirst() {
        Iterator<Edge> it = edges.iterator();
        while (it.hasNext()) {
            System.out.println("First Node of edge " + it.next().getFirstNode().getName());
        }
        System.out.println("--------------------------------------------------------");

    }

    public void iteratoredgesecond() {
        Iterator<Edge> it = edges.iterator();
        while (it.hasNext()) {
            System.out.println("Second Node of edge " + it.next().getSecondNode().getName());
        }
        System.out.println("--------------------------------------------------------");
    }

    public void iteratorweight() {
        Iterator<Edge> it = edges.iterator();
        while (it.hasNext()) {
            System.out.println("Weight of edge " + it.next().getWeight());
        }
        System.out.println("--------------------------------------------------------");
    }

    public void iteratornode() {
        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
            System.out.println("Name of Node " + it.next().getName());
        }
        System.out.println("--------------------------------------------------------");
    }
}

// Exception
class NegativeWeight extends ArrayIndexOutOfBoundsException {
    public NegativeWeight() {
        this("Edge already exists");
    }

    public NegativeWeight(String exception) {
        super(exception);
    }
}