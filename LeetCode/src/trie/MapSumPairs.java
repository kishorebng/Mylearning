package trie;

/*
 Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5
 */

class MapTrieNode {
    // change this value to adapt to different cases
    final int N = 26;
	public MapTrieNode[] children = new MapTrieNode[N];
    
    // you might need some extra values according to different cases
	boolean isWord;
	int value;
}

public class MapSumPairs {

	MapTrieNode root;
	
	/** Initialize your data structure here. */
    public MapSumPairs() {
        root = new MapTrieNode();    
    }
    
    public void insert(String key, int val) {
    	MapTrieNode cur = root;
		for (int i = 0; i < key.length(); i++) {
			if (cur.children[key.charAt(i)-'a'] == null) {
				cur.children[key.charAt(i)-'a'] = new MapTrieNode();
			}
			cur = cur.children[key.charAt(i)-'a'];
		}
		cur.isWord = true;
		cur.value = val;
    }
    
    public int sum(String prefix) {
    	MapTrieNode cur = root;
		for (int i = 0; i < prefix.length(); i++) {
			if (cur.children[prefix.charAt(i)-'a'] != null) {
				cur = cur.children[prefix.charAt(i)-'a'];
			} else {
				return 0;
			}
		}
		return calculate(cur);
    }
    
  
    
    int calculate(MapTrieNode cur) {
        	int sum =0;
    	if (cur == null) {
    		return 0;
    	}
    	for (int i=0;i<26;i++) {
			if (cur.children[i] != null) {
				 sum += calculate(cur.children[i]);
			}
    	}		
    	return sum + cur.value;
    }
    
	
	public static void main(String[] args) {

		MapSumPairs obj = new MapSumPairs();
		 obj.insert("aa",3);
		 System.out.println(obj.sum("a"));
		 obj.insert("ab",2);
		 System.out.println(obj.sum("a"));
		
		 
	
	} 
}
