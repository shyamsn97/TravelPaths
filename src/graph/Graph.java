package graph;

/**
 * A class that represents a graph where nodes are cities (of type CityNode)
 * and edges connect them and the cost of each edge is the distance between
 * the cities.
 * Fill in code in this class. You may add additional methods and variables.
 * You are required to implement a HashTable and a PriorityQueue from scratch.
 */
import java.util.*;
import java.io.*;
import java.awt.Point;

public class Graph {
    public final int EPS_DIST = 5;

    private CityNode[] nodes; // nodes of the graph
	private int numNodes;     // total number of nodes
	private int numEdges; // total number of edges
	private Edge[] adjacencyList; // adjacency list; for each vertex stores a linked list of edges
	public HashTable table = new HashTable(11,5);//hashtable of length 11, using constant a = 5 for when creating the polynomial hash.
    // Your HashTable that maps city names to node ids should probably be here as well

	/**
	 * Read graph info from the given file, and create nodes and edges of
	 * the graph.
	 *
	 * @param filename name of the file that has nodes and edges
	 */
	public void loadGraph(String filename) {

		boolean checker = false;
		BufferedReader bufferedReader = null;
		CityNode city_node;
		Edge city_edge;
		try {
			bufferedReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;
		try {
			line = bufferedReader.readLine(); //skips first two lines
			line = bufferedReader.readLine();
			nodes = new CityNode[Integer.parseInt(line.trim())];
			adjacencyList = new Edge[Integer.parseInt(line.trim())];
			line = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] arr;
		while (line != null && line.length() > 0) {
			line = line.trim();
			if(line.equals("ARCS")) {
				checker = true;
			}
			arr  = line.split(" ");
			if (checker == false) {
				table.add(arr[0]);
				city_node = new CityNode(arr[0],Float.parseFloat(arr[1]),Float.parseFloat(arr[2]));
				addNode(city_node);
			}
			else {
				if (!line.equals("ARCS")) {
					city_edge = new Edge(table.get(arr[1]),Integer.parseInt(arr[2]));
					addEdge(table.get(arr[0]),city_edge);
					city_edge = new Edge(table.get(arr[0]),Integer.parseInt(arr[2]));
					addEdge(table.get(arr[1]),city_edge);
				}
			}
			try {
				line = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public HashTable returnHashTable() {
		return  table;
	}

	/**
	 * Add a node to the array of nodes.
	 * Increment numNodes variable.
     * Called from loadGraph.
	 *
	 * @param node a CityNode to add to the graph
	 */
	public void addNode(CityNode node) {
		nodes[table.get(node.getCity())] = node;
		numNodes++;
	}

	/**
	 * Return the number of nodes in the graph
	 * @return number of nodes
	 */
	public int numNodes() {
		return numNodes;
	}

	/**
	 * Adds the edge to the linked list for the given nodeId
	 * Called from loadGraph.
     *
	 * @param nodeId id of the node
	 * @param edge edge to add
	 */
	public void addEdge(int nodeId, Edge edge) {

		if (adjacencyList[nodeId] == null) {
			adjacencyList[nodeId] = edge;
		}
		else {
			adjacencyList[nodeId].setNext(edge);
		}
		numEdges++;
	}

	/**
	 * Returns an integer id of the given city node
	 * @param city node of the graph
	 * @return its integer id
	 */
	public int getId(CityNode city) {

        return -1; // Don't forget to change this
    }

	/**
	 * Return the edges of the graph as a 2D array of points.
	 * Called from GUIApp to display the edges of the graph.
	 *
	 * @return a 2D array of Points.
	 * For each edge, we store an array of two Points, v1 and v2.
	 * v1 is the source vertex for this edge, v2 is the destination vertex.
	 * This info can be obtained from the adjacency list
	 */
	public Point[][] getEdges() {
		int i = 0;
		Point[][] edges2D = new Point[numEdges][2];
		// FILL IN CODE

		return edges2D;
	}

	/**
	 * Get the nodes of the graph as a 1D array of Points.
	 * Used in GUIApp to display the nodes of the graph.
	 * @return a list of Points that correspond to nodes of the graph.
	 */
	public Point[] getNodes() {
	    if (this.nodes == null) {
            System.out.println("Graph has no nodes. Write loadGraph method first. ");
            return null;
        }
		Point[] pnodes = new Point[this.nodes.length];
		// FILL IN CODE

		return pnodes;
	}

	/**
	 * Used in GUIApp to display the names of the airports.
	 * @return the list that contains the names of cities (that correspond
	 * to the nodes of the graph)
	 */
	public String[] getCities() {
        if (this.nodes == null) {
            System.out.println("Graph has no nodes. Write loadGraph method first. ");
            return null;
        }
		String[] labels = new String[nodes.length];
		// FILL IN CODE


		return labels;

	}

	/** Take a list of node ids on the path and return an array where each
	 * element contains two points (an edge between two consecutive nodes)
	 * @param pathOfNodes A list of node ids on the path
	 * @return array where each element is an array of 2 points
	 */
	public Point[][] getPath(List<Integer> pathOfNodes) {
		int i = 0;
		Point[][] edges2D = new Point[pathOfNodes.size()-1][2];
        // Each "edge" is an array of size two (one Point is origin, one Point is destination)
        // FILL IN CODE

        return edges2D;
	}

	/**
	 * Return the CityNode for the given nodeId
	 * @param nodeId id of the node
	 * @return CityNode
	 */
	public CityNode getNode(int nodeId) {
		return nodes[nodeId];
	}

	/**
	 * Take the location of the mouse click as a parameter, and return the node
	 * of the graph at this location. Needed in GUIApp class.
	 * @param loc the location of the mouse click
	 * @return reference to the corresponding CityNode
	 */
	public CityNode getNode(Point loc) {
		for (CityNode v : nodes) {
			Point p = v.getLocation();
			if ((Math.abs(loc.x - p.x) < EPS_DIST) && (Math.abs(loc.y - p.y) < EPS_DIST))
				return v;
		}
		return null;
	}
}