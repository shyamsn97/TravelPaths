package graph;


/** The Driver class for project Dijkstra */
public class Driver {
    public static void main(String[] args) {
        Graph g = new Graph();

        // Create an instance of the Dijkstra class
        Dijkstra dijkstra = new Dijkstra("USA.txt", g);

        // Create a graphical user interface and wait for user to click
        // on two cities:
        GUIApp app = new GUIApp(dijkstra, g);
    }
}
