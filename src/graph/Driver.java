package graph;


/** The Driver class for project Dijkstra */
public class Driver {
    public static void main(String[] args) {
        HashTable hash = new HashTable(11,5); //hashtable of length 11, using constant a = 5 for when creating the polynomial hash.
        hash.readFile("/home/shyam/Desktop/USF/spring2018/cs245/projects/project4-shyamsn97/usatest.txt");
        hash.printString();
        int nodeId = hash.get("SanFrancisco");
        System.out.println("Node id of SanFrancisco: " + nodeId);

        nodeId = hash.get("Sacramento");
        System.out.println("Node id of Sacramento: " + nodeId);

        // Initialize a graph
        Graph graph = new Graph();

        // Create an instance of the Dijkstra class
        Dijkstra dijkstra = new Dijkstra("USA.txt", graph);

        // Create a graphical user interface and wait for user to click
        // on two cities:
        GUIApp app = new GUIApp(dijkstra, graph);
    }
}
