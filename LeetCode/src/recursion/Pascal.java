package recursion;

import java.util.ArrayList;
import java.util.List;

public class Pascal {
	

	 /*
	  
	  
	  vector<vector<int>> solution1(int numRows){
    if(!numRows) return v;
    subSol1(-1, numRows);
    return v;
}
void subSol1(int cur, int row){
    if(cur + 1 == row) return;
    v.push_back(vector<int>());
    v[cur + 1].push_back(1);
    if(row == 1) return;
    if(cur >= 0){
        for(int i = 1; i <= cur; i++)
            v[cur + 1].push_back(v[cur][i - 1] + v[cur][i]);
        v[cur + 1].push_back(1);
    }
    subSol1(cur + 1, row);
}
	  [
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
   ]
	  */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> allrows = new ArrayList<List<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		//construct row items on every iteration
		for (int i = 0; i < numRows; i++) {
			row.add(0, 1);
			for (int j = 1; j < row.size() - 1; j++) {
				row.set(j, row.get(j) + row.get(j + 1));
			}
			allrows.add(new ArrayList<Integer>(row));
		}
		return allrows;

	}
	
	/*
	 * f(i,j)=f(i−1,j−1)+f(i−1,j)
	 * 
	 * 
	 * f(i,j)=1wherej=1orj=i
	 */
	
	
	public List<List<Integer>> generate_r(int numRows) {
		List<List<Integer>> allrows = new ArrayList<List<Integer>>();
		pascal_r(0,0,numRows,allrows) ;
		return allrows;
	}
	
	
	void pascal_r(int row, int col, int numRows, List<List<Integer>> result) {
		if (numRows<=0) {
			return;
		}
		if (col == 0) {
			result.add(new ArrayList<Integer>());
		}
		if ( row ==0 || col == 0) {
			result.get(row).add(col,1);
			col++;
		}
		while (col <= row) {
			if (row == col) {
				result.get(row).add(col,1);
			} else {
			  List<Integer> prev = result.get(row-1);
			  result.get(row).add(col, prev.get(col-1)+prev.get(col));
			}
			col++;
		}
		pascal_r(++row,0,--numRows,result) ;
		
	}


	

	public static void main(String[] args) {
		Pascal obj = new Pascal();
		List<List<Integer>> perresult = obj.generate_r(5);
		for (List<Integer> per : perresult) {
			System.out.print(" [");
			for (Integer val : per) {
				System.out.print(" " + val);
			}
			System.out.println(" ]");
		}

	}
	 
}
