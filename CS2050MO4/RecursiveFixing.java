/**
 * 
 */

/**
 * 
 */
public class RecursiveFixing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		mystery(0);
		countdown(3);
		badRecursion(3);
		
	}
	
	public static void mystery(int n){
		if (n <= 0){
			return;
		}
		System.out.print(n + " ");
		mystery(n - 1);
		System.out.print(n + " ");
	}
	
	public static void countdown(int n){
		if (n == 0) {
			System.out.println("Blast off!");
			return; //End at 0 to prevent infinite loop error

	}
		System.out.println(n);
		countdown(n - 1);
	}

	public static int badRecursion(int n){
		if (n == 1){
			return 1;
		}
		return n * badRecursion(n - 2);
	}


}
