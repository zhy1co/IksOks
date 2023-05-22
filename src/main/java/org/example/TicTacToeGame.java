package org.example;

import java.util.Scanner;

public class TicTacToeGame {
    private final char[][] board;
    private final char[] playerSymbols;
    private int currentPlayer;


    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 1;
        playerSymbols = new char[]{'X', 'O'};

        // Initialize the board with empty values
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    private void displayBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]);
                if (col < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row < 2) {
                System.out.println("---------");
            }
        }
    }

    public void playGame() {
        boolean gameOver = false;
        int currentPlayer = 1;

        while (!gameOver) {
            displayBoard();

            // Get the current player's move
            int[] move = getPlayerMove(currentPlayer);

            // Update the board with the move
            board[move[0]][move[1]] = currentPlayer == 1 ? 'X' : 'O';

            // Check if the current player wins or the game ends in a draw
            if (checkWin(currentPlayer)) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (checkDraw()) {
                displayBoard();
                System.out.println("The game ends in a draw!");
                gameOver = true;
            }

            // Switch to the other player
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
    }

    private int[] getPlayerMove(int player) {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];

        System.out.println("Player " + player + ", enter your move (row [1-3] column [1-3]): ");
        move[0] = scanner.nextInt() - 1;
        move[1] = scanner.nextInt() - 1;

        // Validate the move
        while (!isValidMove(move)) {
            System.out.println("Invalid move. Please enter your move again (row [1-3] column [1-3]): ");
            move[0] = scanner.nextInt() - 1;
            move[1] = scanner.nextInt() - 1;
        }

        return move;
    }

    public boolean isValidMove(int[] move) {
        int row = move[0];
        int col = move[1];

        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false; // Move out of bounds
        }

        return board[row][col] == ' '; // Cell is empty
    }

    public boolean checkWin(int player) {
        char symbol = (player == 1) ? 'X' : 'O';

        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == symbol && board[row][1] == symbol && board[row][2] == symbol) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == symbol && board[1][col] == symbol && board[2][col] == symbol) {
                return true;
            }
        }

        // Check diagonals
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
                || (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);// No win condition
    }

    public boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false; // Empty cell found
                }
            }
        }

        return true; // All cells are occupied, the game is a draw
    }

    public void makeMove(int[] move) {
        int row = move[0];
        int col = move[1];

        board[row][col] = playerSymbols[currentPlayer - 1];
        switchPlayer();
    }


    public void resetGame() {
        // Clear the board and reset the current player
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }

        currentPlayer = 1;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }


    public int getCurrentPlayer() {
        return currentPlayer;
    }

}
