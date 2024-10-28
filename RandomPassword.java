package pratic;

import java.security.SecureRandom;

public class RandomPassword {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        int passwordLength = 12;
        String randomPassword = generateRandomPassword(passwordLength);
        System.out.println("Generated random password: " + randomPassword);
    }
    public static String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder(length);
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }
        return shuffleString(password.toString());
    }
    private static String shuffleString(String input) {
        StringBuilder result = new StringBuilder(input.length());
        char[] characters = input.toCharArray();

        for (int i = input.length(); i > 0; i--) {
            int randomIndex = random.nextInt(i);
            result.append(characters[randomIndex]);
            characters[randomIndex] = characters[i - 1];
        }
        return result.toString();
    }
}
