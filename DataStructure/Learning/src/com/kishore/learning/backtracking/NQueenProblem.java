package com.kishore.learning.backtracking;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 *
 */
public class NQueenProblem {

	/**
	 * 
	 * To store queens position and final result
	 *
	 */
	class QueenPosition {

		int row;
		int col;

		QueenPosition(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		NQueenProblem problem = new NQueenProblem();
		problem.findSolution(n);

	}

	private void findSolution(int noOfQueens) {
		// Valid solution we should have N position
		QueenPosition[] positions = new QueenPosition[noOfQueens];
		if (findQueenPositions(noOfQueens, 0, positions)) {
			// has solution print postion
			for (int i = 0; i < noOfQueens; i++) {
				System.out.println("("+ positions[i].row+","+positions[i].col+")" );
			}
		} else {
			System.out.println("No Solution Given " + noOfQueens + " Queen");
		}

	}

	private boolean findQueenPositions(int noOfQueens, int row, QueenPosition[] positions) {
		if (noOfQueens == row) {
			return true;
		}
		/*
		 * Need to iterate column now
		 */

		for (int col = 0; col < noOfQueens; col++) {
			// assume current position safe eg: (0,0) to start with
			boolean safe = true;

			// iterate through rows now queen
			for (int queen = 0; queen < row; queen++) {

				/**
				 * Same column is not allowed 
				 * (Row - Col) must be equal to Postions (row - col) not allowed and 
				 * (Row + Col) must be equal to Postions (row + col) not allowed
				 */
				if (positions[queen].col == col || positions[queen].row - positions[queen].col == row - col
						|| positions[queen].row + positions[queen].col == row + col) {
					safe = false;
					break;
				}
			}
			// if safe store the position
			if (safe) {
				positions[row] = new QueenPosition(row, col);
				if (findQueenPositions(noOfQueens, row +1 , positions)) {
					return true;
				}

			}

		}

		return false;
	}

}
