package prj;

import java.util.Random;
import java.util.Scanner;

public class Rock_Paper_Scissors {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		String chars = "rps";
		Random random = new Random();
		while(true) {
			char computer = chars.charAt(random.nextInt(chars.length()));
			System.out.println("What is your choice?\n 'r' for Rock\n 'p' for Paper\n 's' for Scissors\n 'exit' for exit the Game");
			String choice = "try";
			choice = input.nextLine();
			if (choice.equals("exit")) {
                System.out.println("Thanks for playing!");
                break;
            }
			if (!(choice.charAt(0) == 'r') && !(choice.charAt(0) == 's') && !(choice.charAt(0)== 'p')) {
				System.out.println("Invalid input\n");
				continue;
			}
			if ((choice.charAt(0) == 'r' && computer == 's') || (choice.charAt(0) == 's' && computer == 'p') || (choice.charAt(0) == 'p' && computer == 'r')){
				System.out.println("Player win.\n");
            }else if (choice.charAt(0) == computer){
				System.out.println("Tie!!!\n");
			}else {
				System.out.println("Computer win.\n");
			}
		
	}
		input.close();
	}
}
