package trie;

public class Operations {

	static void insert(TrieNode root, String word) {
		TrieNode cur = root;
		for (int i = 0; i < word.length(); i++) {
			if (cur.children[word.charAt(i)] == null) {
				cur.children[word.charAt(i)] = new TrieNode();
			}
			cur = cur.children[word.charAt(i)];
		}
		cur.isWord = true;
	}

	static boolean search(TrieNode root, String key) {

		TrieNode cur = root;
		for (int i = 0; i < key.length(); i++) {
			if (cur.children[key.charAt(i)] != null) {
				cur = cur.children[key.charAt(i)];
			} else {
				return false;
			}
		}
		return (cur != null && cur.isWord);

	}
	
	
	 /** Returns if there is any word in the trie that starts with the given prefix. */
    static public boolean startsWith(TrieNode root,String prefix) {
    	TrieNode cur = root;
		for (int i = 0; i < prefix.length(); i++) {
			if (cur.children[prefix.charAt(i)] != null) {
				cur = cur.children[prefix.charAt(i)];
			}else {
				return false;
			}
		}
		return (cur!=null);
    }

    
   	public static void main(String[] args) {

		TrieNode root = new TrieNode();

		// [null,null,false,false,true,true,false,true]
//		String keys[] = {"the", "a", "there", "answer", "any", 
//                "by", "bye", "their"}; 
//		for (String word : keys) {
//			insert(root,word);
//		}
		insert(root,"hello");
		System.out.println(search(root,"hell"));
		System.out.println(search(root,"helloa"));
		System.out.println(search(root,"hello"));
		System.out.println(startsWith(root,"hell"));
		System.out.println(startsWith(root,"helloa"));
		System.out.println(startsWith(root,"hello"));
		
	}

}
