package logic;

public class StringReversal {

	public static void main(String[] args) {

		String str = "Hello, World!";
		String reversed = "";

		for(int i = str.length() - 1; i >= 0; i--) {
			reversed += str.charAt(i);
		}
		
		System.out.println("Original String: " + str);
		System.out.println("Reversed String: " + reversed);
	}

}