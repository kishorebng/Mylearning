package arrays;

import java.util.Comparator;

public class DominantIndex {

	public static void main(String[] args) {
		 Comparator<Integer> testComparartor = new Comparator<Integer>() {
	            @Override
	            public int compare(Integer s1, Integer s2) {
	                return s2-s1;
	            }
	     };
	        
	}
}
