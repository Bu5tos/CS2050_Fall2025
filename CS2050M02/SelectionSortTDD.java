public class SelectionSortTDD
{
	public static void main(String[] args)
	{
		System.out.println("Testing Selection Sort\n");

		int[][] testCases = { 
				{ 72, 50, 10, 44, 8, 20 }, // Regular case
				{}, // Empty array
				{ 5 }, // Single element
				{ 1, 2, 3, 4, 5 }, // Already sorted
				{ 9, 7, 5, 3, 1 }, // Reverse sorted
				{ 4, 2, 7, 2, 5 } // Array with duplicates
		};

		for (int i = 0; i < testCases.length; i++)
		{
			System.out.println("Test Case " + (i + 1) + ": Before Sorting:");
			printArray(testCases[i]); 
			selectionSort(testCases[i]);
			System.out.println("After Sorting:");
			printArray(testCases[i]);
			System.out.println();
						
		}
		

	}

	//Finding shortest number
	public static void selectionSort(int[] array)
	{
		int shortestNumber = array[0];
		//Checks if i is less than array indexes
		for (int i = 1; i < array.length; i++) {
			//if index value is < CURRENT value
			if (array[i] < shortestNumber) {
				//Initialize new shortest value
				shortestNumber = array[i];
			}
		}
		System.out.println("Shortest number is " + shortestNumber);
	}

	public static void printArray(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}


}