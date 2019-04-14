package hash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MyHashSet {

	List<Integer> hashset; 
	
    /** Initialize your data structure here. */
    public MyHashSet() {
    	hashset = new ArrayList<Integer>();
    }
    
    public void add(int key) {
    	if(!hashset.contains(key)) {
    		hashset.add(key);
    	}        
    }
    
    public void remove(int key) {
        Iterator<Integer> it = hashset.iterator();
        while(it.hasNext()) {
            int val = it.next();
            if (val == key) {
                it.remove();
                return;
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
    	return hashset.contains(key);
    }
}

public class MyHashSet_learn {

	public static void main(String[] args) {
		/**
		 * Your MyHashSet object will be instantiated and called as such: */
		 MyHashSet obj = new MyHashSet();
		 obj.add(1);
		 obj.remove(1);
		 boolean param_3 = obj.contains(1);
		 
	}
}
