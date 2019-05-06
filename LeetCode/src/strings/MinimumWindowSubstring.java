package strings;

public class MinimumWindowSubstring {

	
	   /*
		 Input: S = "ADOBECODEBANC", T = "ABC"
	       Output: "BANC"
		 */
	     static public String minWindow(String s, String t) {
	    	 String minSubString = "";
	    	 
	    	 if (s==null || s.isEmpty() || t==null || t.isEmpty()) {
	    		 return minSubString; 
	    	 }
	    	 int searchlength = t.length();
	    	 int stringlength = s.length();    	 
	    	 final int MAX_CHAR = 256;
	    	 int map [] = new int [MAX_CHAR];
	    	 //initialize map  which store char counters for which we need to substring
	    	 for (int i=0;i<searchlength;i++) {
	    		 map[t.charAt(i)]++;
	    	 }
	    	 
		 
	    	 int left =0;
	    	 int counter = 0;
	    	 int right =0;
	    	 int minSubStringLength = Integer.MAX_VALUE;
	    	 
	    	 while (right < stringlength) {
	    		 // if there is match in char increment counter
	    		 if (map[s.charAt(right++)]-- >0) {
	    			 counter++;
	    		 }
	    		 
	    		 // when all character are found then get substring
	    		 while (counter == searchlength) {
	    			 if ( minSubStringLength > right-left) {
	    				 minSubStringLength = right-left;
	    				 minSubString = s.substring(left, right);
	    			 }
	    			 // if left pointer character matches decrease the counter so right will check next item
	    			 if (++ map[s.charAt(left++)] > 0) {
	    				 counter --;
	    			 }
	    		 }
	    	 }
	    	 return minSubString;
	        
	    }
//	/*
//	 * Input: S = "ADOBECODEBANC", T = "ABC" Output: "BANC"
//	 */
//	static public String minWindowhasp(String s, String t) {
//		String minSubString = "";
//
//		if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
//			return minSubString;
//		}
//		int searchlength = t.length();
//		int stringlength = s.length();
//
//		HashMap<Character, Integer> searchMap = new HashMap<Character, Integer>();
//		for (int i = 0; i < searchlength; i++) {
//			if (searchMap.containsKey(t.charAt(i))) {
//				searchMap.put(t.charAt(i), searchMap.get(t.charAt(i)) + 1);
//			} else {
//				searchMap.put(t.charAt(i), 1);
//			}
//		}
//
//		int right = 0;
//		int left = 0;
//		int counter = 0;
//		int minSubStringLength = Integer.MAX_VALUE;
//
//		while (right < stringlength) {
//
//			char rightChar = (s.charAt(right++)); 
//			if (!searchMap.containsKey(rightChar)) {
//				continue;
//			}
//			searchMap.put(rightChar, searchMap.get(rightChar) - 1);
//			if (searchMap.get(rightChar) >= 0) {
//				counter++;
//			}
//
//			while (counter == searchlength) {
//
//				if (minSubStringLength > right - left) {
//					minSubStringLength = right - left;
//					minSubString = s.substring(left, right);
//				}
//
//				char leftChar = (s.charAt(left++));
//				if (!searchMap.containsKey(leftChar)) {
//					continue;
//				}
//				searchMap.put(leftChar, searchMap.get(leftChar) + 1);
//				if (searchMap.get(leftChar) > 0) {
//					counter--;
//				}
//			}
//
//		}
//		return minSubString;
//
//	}
     
  
     
     public static void main(String[] args) {
		
		System.out.println("hash minWindow "+minWindow("abaa", "aa"));
	}
}
