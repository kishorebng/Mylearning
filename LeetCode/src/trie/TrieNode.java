package trie;

class TrieNode {
    // change this value to adapt to different cases
    static final int N = 256;
	public TrieNode[] children = new TrieNode[N];
    
    // you might need some extra values according to different cases
	boolean isWord;
	int searchCount;
	int value;
}

/** Usage:
 *  Initialization: TrieNode root = new TrieNode();
 *  Return a specific child node with char c: root.children[c - 'a']
 */

