
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConsecutiveSequenceFinder {

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int maxLength = 0;
        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                maxLength = Math.max(maxLength, currentStreak);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        int[] nums = new int[6];

        System.out.println("Enter 6 numbers:");
        for (int i = 0; i < 6; i++) {
            nums[i] = scanner.nextInt();
        }

        int result = longestConsecutive(nums);
        System.out.println("Therefore its length is: " + result);
    }
}

