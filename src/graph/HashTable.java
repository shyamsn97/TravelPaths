package graph;
import java.util.ArrayList;
import java.io.*;

/** Custom implementation of a hash table using open hashing, separate chaining.
 *  Each key is a String (name of the city), each value is an integer (node id). */
public class HashTable {

    public HashObject[] hasharray;
    public ArrayList<String> keys; //will probably change

    public void readFile(String filename) {
        keys = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null && line.length() > 0) {
            keys.add(line);
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}

class HashObject {
    private String key; // id of the neighbor ("destination" vertex of this edge)
    private int value; // cost of this edge
    private HashObject next; // reference to the next "edge" in the linked list
}