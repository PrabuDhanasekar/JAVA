import java.util.Scanner;

public class sudoku_solver {

    public static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true; // Solved
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-".repeat(25));
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print(" | ");
                }
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] getSudokuInput() {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[9][9];
        System.out.println("Enter the Sudoku board row by row. Use '0' for empty cells.");

        for (int i = 0; i < 9; i++) {
            while (true) {
                try {
                    System.out.print("Enter row " + (i + 1) + " (e.g., 5,3,0,0,7,0,0,0,0): ");
                    String[] rowInput = scanner.nextLine().trim().split(",");
                    if (rowInput.length != 9) {
                        throw new IllegalArgumentException("Row must contain exactly 9 numbers.");
                    }

                    for (int j = 0; j < 9; j++) {
                        int value = Integer.parseInt(rowInput[j]);
                        if (value < 0 || value > 9) {
                            throw new IllegalArgumentException("Numbers must be between 0 and 9.");
                        }
                        board[i][j] = value;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. " + e.getMessage());
                }
            }
        }

        return board;
    }
    
    public static void main(String[] args) {
        System.out.println("Input Sudoku Board:");
        int[][] sudokuBoard = getSudokuInput();

        System.out.println("\nOriginal Sudoku Board:");
        printBoard(sudokuBoard);

        if (solveSudoku(sudokuBoard)) {
            System.out.println("\nSolved Sudoku Board:");
            printBoard(sudokuBoard);
        } else {
            System.out.println("\nNo solution exists.");
        }
    }
}
