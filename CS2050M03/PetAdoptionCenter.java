/**
 * 
 */

/**
 * 
 */
import java.util.ArrayList;

public class PetAdoptionCenter
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// ============================================
		// Part 1: Setup an ArrayList of interface type
		// ============================================
		ArrayList<Pet> pets = new ArrayList<>();

		// Add some example pets
		// pets.add(new Bulldog("Bear"));
		// pets.add(new Cat("Mittens"));

		// Task 1: Add at least TWO more Pet types/objects to the list.
		// a) Create a new Pet class (e.g., Parrot, Goldfish, Gecko).
		// b) Make it extend Animal and implement Pet.
		// c) Add 1–2 instances to this ArrayList.
		// pets.add(new Parrot("Rio"));

		// ============================================
		// Part 2: Polymorphic behavior with the interface
		// ============================================
		System.out.println("— Meet Our Pets —");
		for (int i = 0; i < pets.size(); i++)
		{
			Pet currentPet = pets.get(i);
			// NOTE: We only know currentPet is a Pet. These calls dispatch to the right
			// class.
			currentPet.beFriendly();
			currentPet.play();
		}

		// ============================================
		// Part 3: Shared behavior from the abstract class
		// ============================================
		System.out.println("\n— Snack Time —");
		// We can still call Animal behaviors, but we need an Animal reference to do so.
		// Since the list is Pet-typed, show safe upcast via a helper:
		feedAll(pets);

		// ============================================
		// Part 4: ArrayList operations (add, remove, size, get)
		// ============================================
		System.out.println("\n— Adoption Updates —");
		System.out.println("Total pets before adoption: " + pets.size());

		// Example: remove a pet by index (if index valid)
		if (pets.size() > 0)
		{
			Pet adopted = pets.remove(0);
			System.out.println("Adopted out: " + adopted);
		}
		System.out.println("Total pets after adoption: " + pets.size());

		// Task 2: Write a method findByName(ArrayList<Pet>, String) that returns
		// the Pet whose Animal.getName() matches (case-insensitive), or null.
		// Then call it here to try a couple of searches.

		// Task 3 Write a method removeByName(ArrayList<Pet>, String) that
		// removes the first match and returns true/false.
		// Call it and print the result.

		// Task 4 Maintain a separate ArrayList<Pet> called favorites.
		// Add any pet whose name starts with 'M' (or any rule),
		// then print both lists.
		
	} //End main
	
	interface pet {
		void beFriendly();
		void play();
	}//End interface pet
	
	abstract class Animal {
		private String name;
		
		public Animal(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
	    }
		
		public abstract void eat();
		
		public String toString() {
			
	    }
	}//End Animal class
	
	class Spider extends Animal { //subclass
		
		private String getName;
		private String eat;
		
		@Override
		public String toString() {
			return "Spider name: " + getName + 
		}
		
	}//End Spider class
	
	class Marmot extends Animal { //subclass 2
		
		
	}//End Marmot class
	

	// Helper Method: demonstrate calling abstract-class behavior safely
	private static void feedAll(ArrayList<Pet> pets)
	{
		for (int i = 0; i < pets.size(); i++)
		{
			Pet currentPet = pets.get(i);
			// We know every Pet here is also an Animal because our concrete classes extend
			// Animal.
			// Safe cast because we control the types added to the list in this lab.
			Animal animal = (Animal) currentPet;
			animal.eat();
		}
	}
	// Task 2: find the first pet by name (case-insensitive)
	// Hints: check for null String entry; use trim(), toLowerCase(), equals()
//	public static Pet findByName(ArrayList<Pet> pets, String nameToFind) {
	// TODO: return the first match or null if none

//	}

	// Task 3: remove the first match by name (case-insensitive) and return
	// true/false
	// IMPORTANT: use an index-based for loop when removing from an ArrayList
//	public static boolean removeByName(ArrayList<Pet> pets, String nameToRemove) {
	// TODO: loop with index; on match do pets.remove(i); return true;

//	}

	// Task 4: build a new list of pets whose names start with the given letter
	// (case-insensitive)
	// Hints: getName(), isEmpty(), charAt(0), Character.toLowerCase()
//	public static ArrayList<Pet> buildFavoritesStartingWith(ArrayList<Pet> pets, char letter) {
	// TODO: create new ArrayList<Pet> favorites; add matches; return favorites

//	}
}