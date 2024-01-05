import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);

        char[][] board = new char[3][3];
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                board[row][col] = ' ';
            }
        }

         char player = 'x';
         boolean gameOver = false;

         while(!gameOver){
            try {
                printBoard(board);
                System.out.print("Player " + player + " enter  : ");
                int row = scr.nextInt();
                int col = scr.nextInt();

                if (board[row][col] == ' '){
                    board[row][col] = player;
                    gameOver = haveWon(board, player);
                    if (gameOver){
                        System.out.println("Player " + player + " has won!!");
                    } else {
                        player = (player == 'x') ? 'o' : 'x';
                    }
                } else {
                    System.out.println("Invalid Move. Try again!");
                }
            } catch(Exception e){
                System.out.println("Invalid Position.");
            }
        }
        printBoard(board);
    }

    public static void printBoard(char[][] board){
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }

    public static boolean haveWon(char[][] board, char player){
        for (int row = 0; row < board.length; row++){
            // row
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player){
                return true;
            }

            // col
            for (int col = 0; col < board[row].length; col++){
                if (board[0][col] == player && board[1][col] == player && board[2][col] == player){
                    return true;
                }
            }

            // diagnoal
            if (board[0][0] == player && board[1][1] == player && board[2][2] == player){
                return true;
            }

            if (board[0][2] == player && board[1][1] == player && board[2][0] == player){
                return true;
            }

        }
        return false;
    }
}
