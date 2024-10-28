package pratic;

	import java.util.Random;
	import java.util.Scanner;

	public class GuessTheNumber {
	    public static void main(String[] args) {
	        Scanner input = new Scanner(System.in);
	        
	        Random random = new Random();
	        int numberToGuess = random.nextInt(101);
	        int numberOfTrys = 0;
	        int userGuess = -1;
	        
	        System.out.println("Welcome to the Guess the Number game!");
	        System.out.println("I have selected a number between 0 and 100. Try to guess it!");

	        for(int i = 1; i <= 6; i++) {
	            System.out.print("Enter your guess: ");
	            userGuess = input.nextInt();
	            numberOfTrys++;
	            
	            if (numberOfTrys == 5) {
	                System.out.println("Sorry Your are guess is finished Restart the Game.");
	                break;
	            } else if (userGuess > numberToGuess) {
	                System.out.println("Too high please Try again.");
	            } else if(userGuess < numberToGuess){
	            	System.out.println("Too low please Try again.");
	            }else if (userGuess == numberToGuess){
	                System.out.println("Congratulations! You are guess the number!");
	                System.out.println("It took you " + numberOfTrys + " tries.");
	            }
	        }
	        input.close();
	    }
	}
	