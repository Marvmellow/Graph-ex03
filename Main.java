package ex03;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        /// read file into graph
        // Je nachdem wo das Programm ausgeführt wird, Pfad anpassen
        File file = new File("src/graph.data");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // iterate every line
            while ((line = reader.readLine()) != null) {
                String[] object = line.split(",");
                if (object.length != 3) {
                    System.err.println("\"" + line + "\" is not an edge!");
                }
                Node firstNode = graph.addNode(object[0]);
                Node secondNode = graph.addNode(object[1]);
                // add nodes and edges to graph
                
                graph.addEdge(new Edge(firstNode, secondNode, Integer.parseInt(object[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(graph.checkConnectivity()) {
        	System.out.println("graph is connected!");
        } else if (graph.checkConnectivity() == false){
            System.out.println("graph is not connected!");
        }
        graph.iteratoredgefirst();
        graph.iteratoredgesecond();
        graph.iteratorweight();
        graph.iteratornode();
        System.out.println("Expected result:");
        System.out.println("Path to A: A");
        System.out.println("Path to B: A -> B");
        System.out.println("Path to C: A -> C");
        System.out.println("Path to D: A -> B -> D");
        System.out.println("Path to E: A -> B -> D -> E");
        System.out.println("Path to F: A -> B -> D -> F");

        // Hier kann eigener Code eingefügt werden, um die gefundenen
        // kürzesten Pfade auszugeben.
        
    }
}
