package arrays;

public class RemoveDuplicatesSorted {

	static public int removeDuplicates(int[] nums) {
		int i = 0;
		for (int j=0;j<nums.length;j++) {
			if (nums[j]!=nums[i]) {
				i++;
				nums[i] = nums[j];
			}
		}
		return i +1;
	}
	
	public static void main(String[] args) {
		int a[] = {1,2,2,3,4,4};
		for (int t : a) {
			System.out.print(t+ " ");
		}
		int length = removeDuplicates(a);
		System.out.println("Remove duplicate");
		for (int j=0;j<length;j++) {
			System.out.print(a[j]+ " ");
		}
	}
}
