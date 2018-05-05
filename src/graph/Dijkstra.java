package graph;

/** Class Dijkstra. Implementation of Dijkstra's
 *  algorithm on the graph for finding the shortest path.
 *  Fill in code. You may add additional helper methods or classes.
 */

import java.util.*;
import java.awt.Point;

public class Dijkstra {
	private Graph graph; // stores the graph of CityNode-s and edges connecting them
    private List<Integer> shortestPath = null; // nodes that are part of the shortest path

    /** Constructor
	 *
	 * @param filename name of the file that contains info about nodes and edges
     * @param graph graph
	 */
	public Dijkstra(String filename, Graph graph) {
	    this.graph = graph;
		graph.loadGraph(filename);
	}


	public void printTable(double[][] table) {
		for (int i = 0; i < table.length; i++) {
			System.out.println("NODEID: " + i + " " + "COST: " + table[i][0] + " " + "PATH: " + table[i][1]);
		}
	}
	/**
	 * Returns the shortest path between the origin vertex and the destination vertex.
	 * The result is stored in shortestPathEdges.
	 * This function is called from GUIApp, when the user clicks on two cities.
	 * @param origin source node
	 * @param destination destination node
     * @return the ArrayList of nodeIds (of nodes on the shortest path)
	 */
	public List<Integer> computeShortestPath(CityNode origin, CityNode destination) {

		double[][] table = new double[graph.numNodes()][2];
		boolean[] visited = new boolean[graph.numNodes()];
		PriorityQueue priority = new PriorityQueue(graph.numNodes());

		priority.insert(graph.getId(origin),0);
		for (int i = 1; i < graph.numNodes(); i++) {
			priority.insert(i,Integer.MAX_VALUE);
		}

		for (int i = 1; i < table.length; i++) {
			table[i][0] = Double.POSITIVE_INFINITY;
		}



		Edge[] adjacency = graph.getAdjacencyList();

		double tmp = 0;
		int index = 0;

		table[graph.getId(origin)][0] = 0;
		table[graph.getId(origin)][1] = -1;


		while (!priority.isEmpty()) {

			index = priority.removeMin();
			visited[index] = true;
			Edge curr = adjacency[index];
			tmp = table[index][0];

			while (curr != null) {
				if (!visited[curr.getNeighbor()]) {
					System.out.println("City found with vertex: " + index + " " + graph.returnCity(index).getCity() + "|||" + " " + graph.returnCity(curr.getNeighbor()).getCity());
					visited[curr.getNeighbor()] = true; //not really visited, but used to prevent a lot of useless backtracking
				}

				if (tmp + curr.getCost() < table[curr.getNeighbor()][0]) {
					table[curr.getNeighbor()][0] = tmp + curr.getCost();
					table[curr.getNeighbor()][1] = index;
					priority.reduceKey(curr.getNeighbor(), (int)(tmp + curr.getCost()));
				}
				curr = curr.getNext();
			}
		}


		ArrayList<Integer> list = new ArrayList<Integer>();

		printTable(table);

		int check = graph.getId(destination);

		while (check != -1) {
			list.add(check);
			check = (int)table[check][1];
		}



		// Create and initialize a Priority Queue

        // Run Dijkstra

        // Compute the nodes on the shortest path by "backtracking" using the table

        // The result should be in an instance variable called "shortestPath" and
        // should also be returned by the method
		this.shortestPath = list;
	    return list; // don't forget to change it
    }

    /**
     * Return the shortest path as a 2D array of Points.
     * Each element in the array is another array that has 2 Points:
     * these two points define the beginning and end of a line segment.
     * @return 2D array of points
     */
    public Point[][] getPath() {
        if (shortestPath == null){
			return null;
		}
        return graph.getPath(shortestPath); // delegating this task to the Graph class
    }

    /** Set the shortestPath to null.
     *  Called when the user presses Reset button.
     */
    public void resetPath() {
        shortestPath = null;
    }

}