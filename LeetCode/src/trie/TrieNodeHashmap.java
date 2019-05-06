package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNodeHashmap {

	  public Map<Character, TrieNodeHashmap> children = new HashMap<>();
	    
	    // you might need some extra values according to different cases
	  
	    boolean isWord;
		int searchCount;
		int value;
	}

	/** Usage:
	 *  Initialization: TrieNode root = new TrieNode();
	 *  Return a specific child node with char c: root.children.get(c)
	 */
