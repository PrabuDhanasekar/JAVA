package logic;
import java.util.Scanner;

public class CharCounter {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String inputString = scanner.nextLine();
		System.out.print("Enter a character to count: ");
		char targetChar = scanner.nextLine().charAt(0);
		int charCount = 0;
		
		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) == targetChar) {
				charCount++;

			}
		}
		System.out.println("The character " + targetChar + " appears " + charCount + " times in " + inputString + ".");
	}

}