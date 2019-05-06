package strings;

public class ZigZagString {

/*	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
	eg Number of rows 3 
	P   A   H   N
    A P L S I I G
    Y   I   R
    "PAHNAPLSIIGYIR"
	*/	
	
	static String zigzagConversion(String s,int numRows) {
		if (numRows > s.length() || numRows == 1) {
			return s;
		}
		char zigZag [] = new char[s.length()];
		
		//calculate interval
		int interval = 2 *numRows -2;
		int count =0; // to fill  zigzag string
		//iterate row times 
		for (int i =0;i< numRows;i++) {
			int step = interval - 2 * i; // it is needed for storing single character column
            // iterate through strings start with alternative character
			for (int j =i; j<s.length(); j+=interval) {
				zigZag[count] = s.charAt(j);
				count++;
				 // step should greater than zero & less than interval &&  j  
				 if (step > 0 && step < interval && j + step < s.length()) {
					 zigZag[count] = s.charAt(j + step);
	                    count++;
	             } 
			}
			
		}
		
		return new String(zigZag); 
				
	}
	public static void main(String[] args) {
		System.out.println(zigzagConversion("PAYPALISHIRING", 3));
				
	}
	
}
