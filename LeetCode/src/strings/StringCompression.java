package strings;

/*
 Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.

 
Follow up:
Could you solve it using only O(1) extra space?

["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".

"a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
 */
public class StringCompression {
	
    static public int compress(char[] chars) {
    	int newLength = 0, index = 0;
    	
    	// iterate through all the characters 
        while(index < chars.length){
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar){
                index++;
                count++;
            }
            chars[newLength++] = currentChar;
            // if counter is greater than 1 append to string else ingnore            
            if(count != 1)
                for(char c : Integer.toString(count).toCharArray()) 
                    chars[newLength++] = c;
        }
        
        for (int j=0;j< newLength;j++) {
        	System.out.print(chars[j]);
        }
        System.out.println();
        return newLength;
    }

	public static void main(String[] args) {
		String input = "aaabbc";
		int newLength = compress(input.toCharArray());
		System.out.println(newLength);
	}
}
