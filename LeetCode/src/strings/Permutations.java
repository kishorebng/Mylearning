package strings;

/*
   example
     abc  ->  abc, acb, bac, bca, cab, cba 
   
 */
public class Permutations {

	void permutation (String input) {
		permutations("", input);
	}
	
	/***
	 *    we need to divide problem like a "bc" a "cb"
	 *    so we use prefix, suffix and iterate
	 */
	
	void permutations (String prefix, String suffix) {
		System.out.println("Prefix "+prefix +" suffix "+suffix);
		if (suffix.length() == 0) {
			System.out.println(prefix);
		} else {
			    for (int i =0; i <suffix.length();i++) {
			    	permutations(prefix+ suffix.charAt(i),suffix.substring(0,i)+suffix.substring(i+1,suffix.length()));
			    }
		}
		
	}
	
	
	public static void main(String[] args) {
		
		Permutations obj = new Permutations();
		obj.permutation("abc");
	}
}
