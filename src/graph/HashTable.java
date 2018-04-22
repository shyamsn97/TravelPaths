package graph;
import java.util.ArrayList;
import java.io.*;

/** Custom implementation of a hash table using open hashing, separate chaining.
 *  Each key is a String (name of the city), each value is an integer (node id). */
public class HashTable {

    public HashObject[] hasharray;
    public ArrayList<String> keys; //will probably change

    public HashTable(int length) {
        this.hasharray = new HashObject[length];
    }

    public int createHash(String key,int a) {
        int hashvalue = 0;
        double power = key.length() - 1;
        double constant = 0;
        for (int i = 0; i < key.length(); i++) {
            constant = Math.pow(a,power);
            power--;
            hashvalue += (int) (constant * (int) key.charAt(i));
        }
        return hashvalue % hasharray.length;
    }

    public void add(String inputKey,int value) {

        int place = createHash(inputKey,37);
        HashObject newobj = new HashObject(inputKey,value);
        if (hasharray[place] == null) {
            hasharray[place] = newobj;
        }
        else {
            HashObject current = hasharray[place];
            while(current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newobj);
        }
    }

    public int get(String inputKey) {

        int place = createHash(inputKey,37);

        HashObject placeobj = hasharray[place];
        while(placeobj != null) {
            if (placeobj.getKey().equals(inputKey)) {
                return placeobj.getValue();
            }
        }
        
        return 0;
    }

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

    HashObject(String string, int value) {
        key = string;
        value = value;
        next = null;
    }

    public void setNext(HashObject object) {
        this.next = object;
    }

    public HashObject getNext() {
        return next;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

}