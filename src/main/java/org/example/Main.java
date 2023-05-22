package org.example;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        //
        // uncomment to play console game
//        TicTacToeGame game = new TicTacToeGame();
//        game.playGame();

        // uncomment to play GUI game
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI gui = new TicTacToeGUI();
        });
    }
}