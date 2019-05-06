package trie;

import java.util.Arrays;
import java.util.List;

/*
 In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:

Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
 
 */


class ReplaceTrieNode {
    // change this value to adapt to different cases
    final int N = 26;
	public ReplaceTrieNode[] children = new ReplaceTrieNode[N];
    
    // you might need some extra values according to different cases
	boolean isWord;
}

public class ReplaceWord {

	ReplaceTrieNode root;
	ReplaceWord () {
		root = new ReplaceTrieNode();
	}
	
	public void contructTrie(List<String> dict) {
		for (String key : dict) {
			ReplaceTrieNode cur = root;
			for (int i = 0; i < key.length(); i++) {
				if (cur.children[key.charAt(i) - 'a'] == null) {
					cur.children[key.charAt(i) - 'a'] = new ReplaceTrieNode();
				}
				cur = cur.children[key.charAt(i) - 'a'];
			}
			cur.isWord = true;
		}
	}
	 
     public String replaceWords(List<String> dict, String sentence) {
        contructTrie(dict);
        String tokens[] = sentence.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String token : tokens) {
            stringBuilder.append(replace(token));
            stringBuilder.append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.length()-1);
     }
     
     public String replace(String word) {
    	 ReplaceTrieNode cur = root;
    	 StringBuilder stringBuilder = new StringBuilder();
 		for (int i = 0; i < word.length(); i++) {
 			stringBuilder.append(word.charAt(i));
 			if (cur.children[word.charAt(i)-'a'] != null) {
 				if (cur.children[word.charAt(i)-'a'].isWord) {
 					return stringBuilder.toString();
 				}
 				cur = cur.children[word.charAt(i)-'a'];
 			} else {
 				return word;
 			}
 		}
 		return word;
     }

	
	public static void main(String[] args) {
		ReplaceWord obj = new ReplaceWord();
		String [] dict = {"cat", "bat", "rat"};
		String sentence = "the cattle was rattled by the battery";
		System.out.println(obj.replaceWords(Arrays.asList(dict), sentence));
	}
}
