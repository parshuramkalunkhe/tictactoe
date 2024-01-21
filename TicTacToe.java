import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> computerPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] board = {
            { ' ', '|', ' ', '|', ' ' },
            { '-', '+', '-', '+', '-' },
            { ' ', '|', ' ', '|', ' ' },
            { '-', '+', '-', '+', '-' },
            { ' ', '|', ' ', '|', ' ' }
        };

        printBoard(board);

        Scanner scr = new Scanner(System.in);

        while (true) {
            int playerPos = getPlayerPos(scr);

            while (playerPositions.contains(playerPos) || computerPositions.contains(playerPos)) {
                System.out.println("Position taken!");
                playerPos = getPlayerPos(scr);
            }

            placePosInBoard(board, playerPos, "player");
            String result = checkWinner(board);
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int computerPos = getRandomComputerPos();
            while (playerPositions.contains(computerPos) || computerPositions.contains(computerPos)) {
                computerPos = getRandomComputerPos();
            }
            placePosInBoard(board, computerPos, "cpu");

            result = checkWinner(board);
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }

            printBoard(board);
        }
        
        scr.close();
    }

    public static int getPlayerPos(Scanner scr) {
        int playerPos = 0;
        try {
            System.out.print("Enter your position (1-9) : ");
            playerPos = scr.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input. Please enter a number.");
            scr.nextLine(); // Consume the invalid input
            playerPos = getPlayerPos(scr); // Recursive call to get a valid input
        }
        return playerPos;
    }

    public static int getRandomComputerPos() {
        return new Random().nextInt(9) + 1;
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePosInBoard(char[][] board, int pos, String user) {
        char symbol = (user.equals("player")) ? 'X' : 'O';

        switch (pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
        }

        if (user.equals("player")) {
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            computerPositions.add(pos);
        }
    }

    public static String checkWinner(char[][] board) {
        List<List<Integer>> winningConditions = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9),
                Arrays.asList(1, 4, 7),
                Arrays.asList(2, 5, 8),
                Arrays.asList(3, 6, 9),
                Arrays.asList(1, 5, 9),
                Arrays.asList(7, 5, 3)
        );

        for (List<Integer> condition : winningConditions) {
            if (playerPositions.containsAll(condition)) {
                printBoard(board);
                return "Congratulations! You have won!";
            } else if (computerPositions.containsAll(condition)) {
                printBoard(board);
                return "Computer has won!";
            }
        }

        if (playerPositions.size() + computerPositions.size() == 9) {
            printBoard(board);
            return "It's a draw!";
        }

        return "";
    }
}
