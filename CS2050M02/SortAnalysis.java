import java.util.Random;

public class SortAnalysis
{
	public static void main(String[] args)
	{
		System.out.println("Testing Selection Sort");

		int[] sizes = { 5, 10, 100, 1000 };
		for (int n : sizes)
		{
			System.out.printf("%nSort Size n=%d\n", n);

			// ---- reversed array (hard case for insertion) ----
			int[] baseReversed = new int[n];
			for (int i = 0; i < n; i++)
				baseReversed[i] = n - i;

			int stepsSelRev = selectionSortCount(baseReversed.clone()); // fresh clone
			int stepsInsRev = insertionSortCount(baseReversed.clone()); // fresh clone

			System.out.printf("Reversed:   Selection %d comparisons\n", stepsSelRev);
			System.out.printf("            Insertion %d comparisons\n", stepsInsRev);

			// ---- already sorted array (best case for insertion) ----
			int[] baseSorted = new int[n];
			for (int i = 0; i < n; i++)
				baseSorted[i] = i;

			int stepsSelSorted = selectionSortCount(baseSorted.clone());
			int stepsInsSorted = insertionSortCount(baseSorted.clone());

			System.out.printf("Sorted:     Selection %d comparisons\n", stepsSelSorted);
			System.out.printf("            Insertion %d comparisons\n", stepsInsSorted);

			int[] baseRandom = new int[n];
			Random rand = new Random();
			for (int i = 0; i < n; i++)
			{
				baseRandom[i] = rand.nextInt(1000); // numbers 0–999
			}

			int stepsSelRandom = selectionSortCount(baseRandom.clone());
			int stepsInsRandom = insertionSortCount(baseRandom.clone());

			System.out.printf("Random:     Selection %d comparisons\n", stepsSelRandom);
			System.out.printf("            Insertion %d comparisons\n", stepsInsRandom);
		}

		int[][] testCases =
		{
				{ 4, 2, 7, 1, 5 }, // Regular case
				{}, // Empty array
				{ 5 }, // Single element
				{ 1, 2, 3, 4, 5 }, // Already sorted
				{ 9, 7, 5, 3, 1 }, // Reverse sorted
				{ 4, 2, 7, 2, 5 } // Array with duplicates
		};

	}

	public static int selectionSortCount(int[] a)
	{
		int comparisons = 0;
		for (int i = 0; i < a.length - 1; i++)
		{
			int minIdx = i;
			for (int j = i + 1; j < a.length; j++)
			{
				comparisons++; // count each comparison a[j] < a[minIdx]
				if (a[j] < a[minIdx])
					minIdx = j;
			}
			if (minIdx != i)
			{
				int t = a[minIdx];
				a[minIdx] = a[i];
				a[i] = t;
			}
		}
		return comparisons;
	}

	public static int insertionSortCount(int[] a)
	{
		int comparisons = 0;
		for (int i = 1; i < a.length; i++)
		{
			int key = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > key)
			{
				comparisons++; // counted when condition true
				a[j + 1] = a[j];
				j--;
			}
			// count the comparison that fails the while when j >= 0 (i.e., a[j] <= key)
			if (j >= 0)
				comparisons++;
			a[j + 1] = key;
		}
		return comparisons;
	}

}