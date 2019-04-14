package strings;

import java.util.Arrays;

public class Anagram {

	/* n log n
	 * 2 words is anagram if we can form 1 string by scrambling  2nd one.
	 */
	 static boolean isAnagram(String one, String two) {
		 
		 if (one ==null || two==null) {
			 return false;
		 }
         if (one.length() != two.length()) {
			  return false;
		  }
         one = one.toLowerCase();
         two = two.toLowerCase();
		 char oneArray [] = one.toCharArray();
		 char twoArray [] = two.toCharArray();
		 
		 Arrays.sort(oneArray); 
		 Arrays.sort(twoArray);
		 
		 for (int i=0;i<oneArray.length;i++) {
			 
			 if (oneArray[i] != twoArray[i]) {
				 return false;
			 }
		 }
		 
		 return true;
	 }
	 
	 /*
	  * O(n)
	  */
	 static boolean isAnagram1(String one, String two) {
		 final int MAX_CHAR = 256;
		 if (one ==null || two==null) {
			 return false;
		 }
         if (one.length() != two.length()) {
			  return false;
		  }
         one = one.toLowerCase();
         two = two.toLowerCase();
         
         int s1 [] = new int [MAX_CHAR];
         int s2 [] = new int [MAX_CHAR];
         
         char oneArray [] = one.toCharArray();
		 char twoArray [] = two.toCharArray();
         
         Arrays.fill(s1, 0);
         Arrays.fill(s2, 0);
         
         for (int i=0;i<oneArray.length;i++) {
        	 s1[oneArray[i]]++;
        	 s2[twoArray[i]]++;
         }
         
         for (int i=0;i< MAX_CHAR;i++) {
        	 if (s1[i] != s2[i]) {
				 return false;
			 }
         }
		 
		 return true;
	 }
	 
	 
	public static void main(String[] args) {
		
		System.out.println(" Anagram "+isAnagram1("listen", "silent"));
		
	}
}
