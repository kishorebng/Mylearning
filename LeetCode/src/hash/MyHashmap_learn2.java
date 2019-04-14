package hash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MyHashMap {

	List<Integer> hashmap; 
	
    /** Initialize your data structure here. */
    public MyHashMap() {
    	hashmap = new ArrayList<Integer>();
    }
    
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
    	hashmap.set(key, key);
    }
    
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
    	return hashmap.get(key);
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
    	hashmap.remove(key);
    }
}

public class MyHashmap_learn2 {

	public static void main(String[] args) {
		/**
		 * Your MyHashSet object will be instantiated and called as such: */
		MyHashMap obj = new MyHashMap();
		 obj.put(1,100);
		 obj.remove(1);
		 int param_3 = obj.get(1);
		 
	}
}
