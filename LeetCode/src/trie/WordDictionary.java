package trie;

/*
 Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 */
class DictionaryTrieNode {
    // change this value to adapt to different cases
    final int N = 26;
	public DictionaryTrieNode[] children = new DictionaryTrieNode[N];
    
    // you might need some extra values according to different cases
	boolean isWord;
}

public class WordDictionary {

	DictionaryTrieNode root;
	
	 /** Initialize your data structure here. */
    public WordDictionary() {
    	root = new DictionaryTrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
    	DictionaryTrieNode cur = root;
		for (int i = 0; i < word.length(); i++) {
			if (cur.children[word.charAt(i) - 'a'] == null) {
				cur.children[word.charAt(i) - 'a'] = new DictionaryTrieNode();
			}
			cur = cur.children[word.charAt(i) - 'a'];
		}
		cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
    	return  searchword(word, 0, root);
    }
    
    boolean searchword(String word,int index, DictionaryTrieNode cur ) {
    	boolean present = false;
    	if (cur == null && index == word.length()) {
    		return false;
    	}
    	
    	if (index == word.length()) {
    		return cur.isWord;
    	}
    	
    	if (word.charAt(index) == '.') {
    		for (int i=0;i<26;i++) {
    			if (cur.children[i] != null) {
    				present = searchword(word,index +1, cur.children[i]);
    				if (present) {
    					break;
    				}
    			}
        	}	
    	} else 	if (cur.children[word.charAt(index) - 'a'] != null) {
    		present = searchword(word,index +1, cur.children[word.charAt(index) - 'a']);
    	}
    	
    	return present;
    }

 
    public static void main(String[] args) {
    	WordDictionary obj = new WordDictionary();
    	obj.addWord("at");
    	obj.addWord("and");
    	obj.addWord("an");
    	obj.addWord("add");
//    	System.out.println(obj.search("a"));
//    	System.out.println(obj.search(".at"));
    	obj.addWord("bat");
//    	System.out.println(obj.search(".at"));
//    	System.out.println(obj.search("an."));
//    	System.out.println(obj.search("a.d."));
//    	System.out.println(obj.search("b."));
    	System.out.println(obj.search("a.d"));
//    	System.out.println(obj.search("."));
//    	
//    	obj.addWord("a");
//    	obj.addWord("a");
//    	System.out.println(obj.search("aa"));
	}
}
