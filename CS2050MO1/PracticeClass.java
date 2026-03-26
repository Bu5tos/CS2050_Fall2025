import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 */

/**
 * 
 */
public class PracticeClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		int[] calorieArray = {0, 400, 400, 300, 250, 450, 450};
		Athlete athlete1 = new Athlete("Heap", "Shit", 100.5, 64, 22, calorieArray);
		
		System.out.println("first name: " + athlete1.getFirstName());
		System.out.println("last name: " + athlete1.getLastName());
		System.out.println("BMI: " + athlete1.calculateBMI());
		System.out.println("Average calories: " + Math.round(athlete1.getAverageCaloriesBurned()));
		System.out.println("Max Heart Rate: " + athlete1.calculateMaxHeartRate());
		System.out.println("Category: " + athlete1.getBMICategory());
		
		System.out.println("");
		
		Athlete[] athleteArray = new Athlete[3];
		athleteArray[0] = athlete1;
		athleteArray[1] = new Athlete("Alexis", "Bustos", 50.1, 222, 37, calorieArray);
		athleteArray[2] = new Athlete("Bella", "Donke", 25.9, 111, 18, calorieArray);
		
		
		
		//Gym gym1 = new Gym("Gym Rats", athleteArray, 3);
		
		//System.out.println("Gym: " + gym1.getGymName());
		
		
		
	}//End Main

}

//Athlete class
class Athlete {
	
	//Instance variables
	private String firstName;
	private String lastName;
	private double weight;
	private double height;
	private int age;
	private int[] dailyCaloriesBurned;
	
	//Constructors 
	public Athlete(String firstName, String lastName, double weight, double height, int age, int[] dailyCaloriesBurned) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.weight = weight;
		this.height = height;
		this.age = age;
		this.dailyCaloriesBurned = dailyCaloriesBurned;
	}
	
	//Methods
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public double calculateBMI() {
		double bmi = Math.round(weight * 703 / (height * height));
		return bmi;
	}
	
	public double getAverageCaloriesBurned() {
		double totalCalories = 0;
		double sum = 0;
		
		//Sum daily burned calories 
		for (int index = 0; index < dailyCaloriesBurned.length; index++) {
			sum = 0 + dailyCaloriesBurned[index];
			totalCalories = totalCalories + sum;
		}
		return totalCalories/7;
	}//getAverageCaloriesBurned
	
	public int calculateMaxHeartRate() {
		final int MAX_HEART_RATE = 220; //Constant max heart rate
		int bpm = (MAX_HEART_RATE - age);
		return bpm;
	}//End calculateMaxHeartRate
	
	public String getBMICategory() {
		final double MAX_UNDER_WEIGHT = 18.4;
		final double MIN_NORMAL = 18.5;
		final double MIN_OVER_WEIGHT = 25.0;
		final double MIN_OBESE = 40.0;
		
		String category = " ";
		double bmi = calculateBMI();
		
		//Determine BMI category
		if (bmi <= MAX_UNDER_WEIGHT){
			    category = "Underweight";
			}
			else if (bmi >= MIN_OBESE ){
				category = "Obese";
			}
			else if (bmi >= MIN_OVER_WEIGHT){
				category = "Overweight";
			}
			else if (bmi >= MIN_NORMAL){
				category = "Normal";
			}
		return category;
	}//End calculateBMI
	
}//End Athlete Class

//Gym Class
class Gym {
	
	private String name;
	private Athlete[] athletes;
	private int numAthletes;
	
	public Gym(String name, int maxAthletes) {
		this.name = name;
		athletes = new Athlete[maxAthletes];
		numAthletes = 0;
	}
	
	
	
	public String getGymName() {
		return name;
	}
	
	public void addAthlete(Athlete athlete) {
		if (numAthletes < athletes.length) {
			athletes[numAthletes] = athlete;
			numAthletes++;
		} else {
			System.out.println("Gym is full. ");
		}
	}//End addAthlete
	
	public void displayAthleteSummaries() {
		for (int i = 0; i < numAthletes; i++) {
			
			//
			Athlete athlete = athletes[i];
			
			//Print summary for each athlete #
	    	System.out.println(name);
		    System.out.println("        Max Heart Rate: " + athlete.calculateMaxHeartRate() + " bpm");
		    System.out.println("        Average Daily Calories Burned: " + athlete.getAverageCaloriesBurned());
		    System.out.println("        BMI: " + athlete.calculateBMI() + "     Category: " + athlete.getBMICategory());
		    System.out.println(" ");
		}
	}//End displayAthleteSummaries
	
	//public void saveReportToFile()
	
	private void findTopAthlete() {
		double largestCalories = athletes[0].getAverageCaloriesBurned();
		int topAthlete = 1;
		
		for (int i = 1; i < numAthletes; i++) {
			if (athletes[i].getAverageCaloriesBurned() > largestCalories) {
				largestCalories = athletes[i].getAverageCaloriesBurned();
				topAthlete = i + 1;
		}
	}
		  System.out.println("Top Athlete: " + athletes[topAthlete]);
	}//End findTopAthlete
	
	private void displayUnderweightAthletes() {
		double lowestCalories = athletes[0].getAverageCaloriesBurned();
		int underweightAthlete = 1;
		boolean underweight = true;
		
		for (int i = 1; i < numAthletes; i++) {
			if (athletes[i].getAverageCaloriesBurned() < lowestCalories) {
				lowestCalories = athletes[i].getAverageCaloriesBurned();
				underweightAthlete = i + 1;
				underweight = false;
			}
			else if (lowestCalories != athletes[i].getAverageCaloriesBurned()){
				underweight = true;
			}
			else {
				System.out.println("No underweight Athlete");
			}
				}
		  System.out.println("Underweight Athlete: " + athletes[underweightAthlete]);
	}//End displayUnderweightAthletes
	
}//End Gym Class 