package strings;

public class LongestPalindromeInString {

	 // Dynamic programming Time O(n^2) Space O(n^2)
	static String longestPlaindrome(String data) {
		if (data == null || data.length() < 1) {
			return data;
		}

		int len = data.length();
		int maxLen = 1;
		//create 2D matrix of length
		boolean[][] dp = new boolean[len][len];

		String longest = null;
		//iterate still character length
		for (int dataIndex = 0; dataIndex < data.length(); dataIndex++) {
			//now iterate again for filling matrix 
			// row should be lenght minus dataIndex
			for (int row = 0; row < len - dataIndex; row++) {
				int col = row + dataIndex;
				/*
				 * if row char is same col char &&
				 * col-row <=2 if string length is less than 2  or
				 * matrix 1+row and col-1  field is true
				 */
				if (data.charAt(row) == data.charAt(col) && (col - row <= 2 || dp[row + 1][col - 1])) {
					dp[row][col] = true;
					// palindrome string is greater than maxlength
					if (col - row + 1 > maxLen) {
						maxLen = col - row + 1;
						longest = data.substring(row, col + 1);
					}
				}
			}
		}

		return longest;

	}
	
	
	// 2nd method Space O(1)
	
	public String longestPalindrome(String s) {
		if (s.isEmpty()) {
			return null;
		}
	 
		if (s.length() == 1) {
			return s;
		}
	 
		String longest = s.substring(0, 1);
		for (int i = 0; i < s.length(); i++) {
			// get longest palindrome with center of i
			String tmp = helper(s, i, i);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
	 
			// get longest palindrome with center of i, i+1
			tmp = helper(s, i, i + 1);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
		}
	 
		return longest;
	}
	 
	// Given a center, either one letter or two letter, 
	// Find longest palindrome
	public String helper(String s, int begin, int end) {
		while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
			begin--;
			end++;
		}
		return s.substring(begin + 1, end);
	}
	
	
	//   Manacher's Algorithm O(n)
	
	 static String processString(String input) {
		 StringBuilder sb = new StringBuilder("$#");
		    for (char c : input.toCharArray()) {
		        sb.append(c);
		        sb.append("#");
		    }
		    sb.append("@");
		    return sb.toString();
	 }
	 
	 
	 public static String longestPalindromeLinear(String input) {
		 
			if (input == null || input.length() <= 1) {
				return input;
			}

		    //process String to generate new STring with special characters 
			String newInput = processString(input);
			
			//integer array for palindrome count
			int[] palLenArray = new int[newInput.length()];
			
			int center = 0;  // used for calculating mirror
			int rightBoundary = 0;  
			int maxPalLength = 0;
			int maxCenterIndex = 0;
			for(int currentIndex = 1; currentIndex < newInput.length(); currentIndex++){
				int mirror = 2*center-currentIndex; // 2 * center - currentIndex
				// if rightboundary is greater then currentIndex
				if (rightBoundary > currentIndex) { 
					
					palLenArray[currentIndex] =  Math.min(rightBoundary-currentIndex, palLenArray[mirror]);	
				}
				// iterate if currentIndex & palLenArray val of currentIndex 
				while(currentIndex + palLenArray[currentIndex] < newInput.length()){
					// keep checking char by one plus and one minus to update palindrome length
					if(newInput.charAt(currentIndex+palLenArray[currentIndex]) == newInput.charAt(currentIndex-palLenArray[currentIndex])){
						palLenArray[currentIndex]++;
					}
					else{
						break; // when mismatch break it.
					}
				}
				// if rightboundary is less than  currentIndex + palLenArray val of currentIndex  then max is changed
				// calcuate new center and rightboundary
				if(rightBoundary < currentIndex + palLenArray[currentIndex]){
					center = currentIndex;
					rightBoundary = currentIndex + palLenArray[currentIndex];
				}
				//if new max is greater store max length and centerIndex
				if(maxPalLength <= palLenArray[currentIndex]){
					maxPalLength = palLenArray[currentIndex];
					maxCenterIndex = currentIndex;
				}	
				System.out.println(palLenArray[currentIndex]);
			}
			//if length needs to be return maxPalLength -1  since # is also considered.
			System.out.println("max "+ maxPalLength);
			return input.substring((maxCenterIndex-maxPalLength)/2, (maxCenterIndex-maxPalLength)/2 + (maxPalLength-1));
	    }
	

	// Best  & fastest
	 
	 public static String longestPalindromef(String s) {
	        if(s==null || s.length()==0) return "";
	        if(s.length()==1)return s;
	        if(s.length()==2) {
	            if(s.charAt(0)==s.charAt(1)) return s;
	            else return String.valueOf(s.charAt(1));
	        }
	        char[] a = s.toCharArray();
	        
	        int longestR=0;
	        int longestL=0;
	        int longestDiff=0;
	        int len = a.length;
	        
	        for(int i = 0; i < len ; i++) {
	            int left = i-1;
	            int right = i;
	            
	            while(right < len && a[right] == a[i]){
	                right++;
	            }
	            i = right-1;
	            
	            while(left >= 0 && right < len && a[left]==a[right]) {
	                left--;
	                right++;
	            }
	            left++;
	            right--;
	            
	            int diff = right-left;
	            if(diff >= longestDiff) {
	                longestDiff = diff;
	                longestR = right;
	                longestL = left;
	            }
	        }
	        return s.substring(longestL,longestR+1);
	    }
	 
	 // Know minimum cuts
	 public int minCut(String s) {
		    char[] c = s.toCharArray();
		    int n = c.length;
		    int[] cut = new int[n];
		    boolean[][] pal = new boolean[n][n];
		    
		    for(int index = 0; index < n; index++) {
		        int min = index;
		        for(int row = 0; row <= index; row++) {
		            if(c[row] == c[index] && (row + 1 > index - 1 || pal[row + 1][index - 1])) {
		                pal[row][index] = true;  
		                min = row == 0 ? 0 : Math.min(min, cut[row - 1] + 1);
		            }
		        }
		        cut[index] = min;
		    }
		    return cut[n - 1];
		}
	    
	public static void main(String[] args) {
		String val ="banana";
		System.out.println(longestPalindromef(val));
		System.out.println(longestPalindromeLinear(val));
	}

}
