package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private final JButton[][] buttons;
    private final TicTacToeGame game;

    public TicTacToeGUI() {
        game = new TicTacToeGame();
        buttons = new JButton[3][3];

        // Set up the main frame
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3));

        // Create and add buttons to the frame
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // Find the button's position in the array
        int buttonRow = -1;
        int buttonCol = -1;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col] == clickedButton) {
                    buttonRow = row;
                    buttonCol = col;
                    break;
                }
            }
        }

        // Make the move and update the button's text
        if (buttonRow != -1 && buttonCol != -1) {
            int[] move = {buttonRow, buttonCol};
            int currentPlayer = game.getCurrentPlayer();

            if (game.isValidMove(move)) {
                game.makeMove(move);
                clickedButton.setText(currentPlayer == 1 ? "X" : "O");

                if (game.checkWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (game.checkDraw()) {
                    JOptionPane.showMessageDialog(this, "The game ends in a draw!");
                    resetGame();
                }
            }
        }
    }


    private void resetGame() {
        game.resetGame();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }
}

