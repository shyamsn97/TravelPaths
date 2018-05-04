package graph;
import java.io.*;

/** Custom implementation of a hash table using open hashing, separate chaining.
 *  Each key is a String (name of the city), each value is an integer (node id). */
public class HashTable {

    public HashObject[] hasharray;
    public int idcount;
    public int a; // used as a constant for hashing strings


    public HashTable(int length,int a) {
        this.hasharray = new HashObject[length];
        this.a = a;
    }

    public int createHash(String key) {
        double hashvalue = 0;
        double power = key.length() - 1;
        double constant;
        for (int i = 0; i < key.length(); i++) {
            constant = Math.pow(a,power);
            power--;
            hashvalue += (constant * (int) key.charAt(i));
        }
        return (int) hashvalue % hasharray.length;
    }

    public void add(String inputKey) {

        int place = createHash(inputKey);
        System.out.println("ADDED: " + inputKey + " HASH: " + place + " NODE ID: " + idcount);
        HashObject newobj = new HashObject(inputKey,idcount);
        idcount++;
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

        int place = createHash(inputKey);

        HashObject placeobj = hasharray[place];
        while(placeobj != null) {
            if (placeobj.getKey().equals(inputKey)) {
                return placeobj.getValue();
            }
            placeobj = placeobj.getNext();
        }
        System.out.println("Key not found!");
        return -1;
    }

    public void printString() {

        System.out.println("Hash Table");
        String s;
        HashObject obj;
        for (int i = 0; i < hasharray.length; i++) {
            s = i + ": ";
            obj = hasharray[i];
            while(obj != null) {
                s += obj.getKey() + " ";
                obj = obj.getNext();
            }
            System.out.println(s);
        }
    }

}

class HashObject {
    private String key; // id of the neighbor ("destination" vertex of this edge)
    private int value; // cost of this edge
    private HashObject next; // reference to the next "edge" in the linked list

    HashObject(String string, int val) {
        key = string;
        value = val;
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