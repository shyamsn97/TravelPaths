package graph;

/** Custom implementation of a hash table using open hashing, separate chaining.
 *  Each key is a String (name of the city), each value is an integer (node id). */
public class HashTable {

    public HashObject[] hasharray;
    

}

class HashObject {
    private String key; // id of the neighbor ("destination" vertex of this edge)
    private int value; // cost of this edge
    private HashObject next; // reference to the next "edge" in the linked list
}