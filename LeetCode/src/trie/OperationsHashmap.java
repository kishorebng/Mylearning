package trie;

public class OperationsHashmap {

	static void insert(TrieNodeHashmap root, String word) {
		TrieNodeHashmap cur = root;
		for (int i = 0; i < word.length(); i++) {
			if (cur.children.get(word.charAt(i)) == null) {
				cur.children.put(word.charAt(i), new TrieNodeHashmap()) ;
			}
			cur = cur.children.get(word.charAt(i));
		}
		cur.isWord = true;
	}

	static boolean search(TrieNodeHashmap root, String key) {

		TrieNodeHashmap cur = root;
		for (int i = 0; i < key.length(); i++) {
			if (cur.children.get(key.charAt(i)) != null) {
				cur = cur.children.get(key.charAt(i));
			} else {
				return false;
			}
		}
		return (cur != null && cur.isWord);

	}
	
	
	 /** Returns if there is any word in the trie that starts with the given prefix. */
    static public boolean startsWith(TrieNodeHashmap root,String prefix) {
    	TrieNodeHashmap cur = root;
		for (int i = 0; i < prefix.length(); i++) {
			if (cur.children.get(prefix.charAt(i))!= null) {
				cur = cur.children.get(prefix.charAt(i));
			}else {
				return false;
			}
		}
		return (cur!=null);
    }

  
	public static void main(String[] args) {

		TrieNodeHashmap root = new TrieNodeHashmap();

		// [null,null,false,false,true,true,false,true]
//		String keys[] = {"the", "a", "there", "answer", "any", 
//                "by", "bye", "their"}; 
//		for (String word : keys) {
//			insert(root,word);
//		}
//		System.out.println(search(root,"ans"));
//		System.out.println(search(root,"answer"));
//		System.out.println(startsWith(root,"ans"));
		insert(root,"hello");
		System.out.println(search(root,"hell"));
		System.out.println(search(root,"helloa"));
		System.out.println(search(root,"hello"));
		System.out.println(startsWith(root,"hell"));
		System.out.println(startsWith(root,"helloa"));
		System.out.println(startsWith(root,"hello"));
		
	}

}
