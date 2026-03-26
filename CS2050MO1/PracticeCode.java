//import java.util.Scanner;

public class PracticeCode {

	public static void main(String[] args) {
		
		//Constant variables to be absolute
		final int TABLE_ROW_SIZE = 5;
		final int TABLE_COL_SIZE = 6;
		
		//Declaring a 2D Array named table
		int[][] table = new int [TABLE_ROW_SIZE][TABLE_COL_SIZE];
		
		/*Nested for loop
		 *Outer loop goes through each row
		 *Inner loop goes through each column for CURRENT row
		 * */
		for (int row = 0; row < TABLE_ROW_SIZE; row++) 
		{
		   for (int col = 0; col < TABLE_COL_SIZE; col++)
		   {
			   table[row][col] = (row + 1) * (col + 1);
		   }
		}// End loop
		
		Example.method();//Calling method from class Example without needing an object.
		
	}//Main End
	
	class Example{
		static void method() {
			System.out.println("Static method called");
		}
		
	}//End class

}//PracticeClass End