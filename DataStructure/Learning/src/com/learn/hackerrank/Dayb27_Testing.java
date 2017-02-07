package com.learn.hackerrank;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Kishore Problem Statement
 * 
 *         A Discrete Mathematics professor has a class of students. Frustrated
 *         with their lack of discipline, the professor decides to cancel class
 *         if fewer than students are present when class starts. Given the
 *         arrival time of each student, determine if the class is canceled.
 *         Input Format
 * 
 *         The first line of input contains , the number of lectures. The
 *         information for each lecture spans two lines. The first line has two
 *         space-separated integers, (the number of students in the class) and
 *         (the cancelation threshold). The second line contains space-separated
 *         integers describing the array of students' arrival times ().
 * 
 *         Note: Non-positive arrival times () indicate the student arrived
 *         early or on time; positive arrival times () indicate the student
 *         arrived minutes late. If a student arrives exactly on time , the
 *         student is considered to have entered before the class started.
 *         Output Format
 * 
 *         For each test case, print the word YES if the class is canceled or NO
 *         if it is not. Example
 * 
 *         When properly solved, this input: 2 4 3 -1 -3 4 2 4 2 0 -1 2 1
 *         Produces this output: YES NO For the first test case, . The professor
 *         wants at least students in attendance, but only arrive on time ( and
 *         ). Thus, the class is canceled.
 * 
 *         For the second test case, . The professor wants at least students in
 *         attendance, and do arrive on time ( and ). Thus, the class is not
 *         canceled.
 */


class Class {

	int totalStudent;
	int studentThreshold;
	int studentTimediff[];
	String result;

	Class(int totalStudent, int studentThreshold, int timediff[]) {
		this.totalStudent = totalStudent;
		this.studentThreshold = studentThreshold;
		studentTimediff = timediff;
	}

	public int getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}

	public int getStudentThreshold() {
		return studentThreshold;
	}

	public void setStudentThreshold(int studentThreshold) {
		this.studentThreshold = studentThreshold;
	}

	public int[] getStudentTimediff() {
		return studentTimediff;
	}

	public void setStudentTimediff(int[] studentTimediff) {
		this.studentTimediff = studentTimediff;
	}
	
	public void determineClass() {
		System.out.println(totalStudent + " "+studentThreshold);		
		int totalLate =0;
		for (int i=0; i<totalStudent;i++) {
			System.out.print(studentTimediff[i]+" ");
			if(studentTimediff[i] > 0) {
				totalLate++;
			}
		}
		System.out.println();
		if (totalStudent-totalLate < studentThreshold) {
			result = "YES";
		}else {
			result = "NO";
		}		
	}

}




public class Dayb27_Testing {
	
	 public static void main(String args[]) {
		/* Enter your code here. */

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<Class> classes = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int students = in.nextInt();
			int threshold = in.nextInt();
			int timing[] = new int[students];
			for (int t = 0; t < students; t++) {
				timing[t] = in.nextInt();
			}
		    Class obj = new Class(students, threshold, timing);
		  	classes.add(obj);
     	}
		System.out.println(n);
		for (Class lClass:classes) {
			lClass.determineClass();			
		}
		for (Class lClass:classes) {		
			System.out.println(lClass.result);
		}
	}
}
