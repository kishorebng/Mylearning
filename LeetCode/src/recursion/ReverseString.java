package recursion;

public class ReverseString {

	
	 public static void reverseString(char[] s) {
		 reverse_i(s,0,s.length-1);
	 }
	 
	 static void reverse(char[] s, int start, int end) {
		 if (start > end) {
			 return;
		 }
		 char temp = s [end];
		 s [end] = s[start];
		 s[start] = temp;
		 reverse(s, ++start, --end);
	 }
	 
	 static void reverse_i(char[] s, int start, int end) {
		 while (start < end) {
			 char temp = s [end];
			 s [end] = s[start];
			 s[start] = temp;
			 start++;
			 end--;
		 }
	 }
	 
	 public static void main(String[] args) {
		 String data = "hello";
		 char [] s = data.toCharArray();
		 reverseString(s);
		 for (char c : s) {
			 System.out.println(c+" ");
		 }
	}
}
