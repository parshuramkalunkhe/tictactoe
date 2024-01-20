import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

	public static void main(String[] args) {

		char[][] board = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

		printBoard(board);

		
		while (true) {
			Scanner scr = new Scanner(System.in);
			
			int playerPos = playerPos(scr);
			
			while (playerPositions.contains(playerPos) || computerPositions.contains(playerPos)) {
				System.out.println("Position take!");
				playerPos = playerPos(scr);
			}
			
			placePosInBoard(board, playerPos, "player");
			String res = winner(board);
			if (res.length() > 0) {
				System.out.println(res);
				break;
			}
			
			Random random = new Random();
			int computerPos = random.nextInt(9) + 1;
			while (playerPositions.contains(computerPos) || computerPositions.contains(computerPos)) {
				computerPos = scr.nextInt();
			}
			placePosInBoard(board, computerPos, "cpu");

			res = winner(board);
			if (res.length() > 0) {
				System.out.println(res);
				break;
			}
			printBoard(board);
		}
	}
	
	public static int playerPos(Scanner scr) {
		int playerPos = 0;
		try {
			System.out.print("Enter your position (1-9) : ");
			playerPos = scr.nextInt();
			
		} catch (Exception e) {
			System.out.println("Invalid Input.");
			playerPos(new Scanner(System.in));
		}
		return playerPos;
	}

	public static void printBoard(char[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
	}

	public static void placePosInBoard(char[][] board, int pos, String user) {
		char symbol = ' ';

		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if (user.equals("cpu")) {
			symbol = 'O';
			computerPositions.add(pos);
		}

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
	}

	public static String winner(char[][] board) {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List bottomRow = Arrays.asList(7, 8, 9);

		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);

		List rightDia = Arrays.asList(1, 5, 9);
		List leftDia = Arrays.asList(7, 5, 3);

		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(bottomRow);
		winningConditions.add(leftCol);
		winningConditions.add(midCol);
		winningConditions.add(rightCol);
		winningConditions.add(rightDia);
		winningConditions.add(leftDia);

		for (List l : winningConditions) {
			if (playerPositions.containsAll(l)) {
				printBoard(board);
				return "Congratulations you have won!!";
			} else if (computerPositions.containsAll(l)) {
				printBoard(board);
				return "Computer is won!!";
			} else if (playerPositions.size() + computerPositions.size() == 9) {
				printBoard(board);
				return "Draw !!!";
			}
		}

		return "";
	}
}
