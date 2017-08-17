/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.ntxuva;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author feler
 */
public class Game {

    public Ntxuva ntxuva = new Ntxuva();

//    public Ntxuva ntxuva = new Ntxuva(new String[]{"000001", "001000", "110000", "000200"}, 'x');
//    public Ntxuva ntxuva = new Ntxuva(new String[]{"000001", "001000", "110000", "000200"}, 'x');
//    public Ntxuva ntxuva = new Ntxuva(new String[]{"100000", "000000", "001000", "000001"}, 'x');
//    public Ntxuva ntxuva = new Ntxuva(new String[]{"000000", "100000", "000000", "000001"}, 'x');
//    public Ntxuva ntxuva = new Ntxuva(new String[] {"000100", "100000", "001010", "000000"}, 'x');
    
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ntxuva");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(Ntxuva.ROWS, Ntxuva.COLUMNS));

            final Game game = new Game();
            final JButton[][] buttons = new JButton[Ntxuva.ROWS][Ntxuva.COLUMNS];

            for (int i = 0; i < Ntxuva.ROWS; i++) {
                for (int j = 0; j < Ntxuva.COLUMNS; j++) {
                    JButton btn = new JButton();
                    btn.setPreferredSize(new Dimension(200, 200));

                    if (game.ntxuva.turn == 'x') {

                        if (i == 1) {
//                        if (i < 2) {
                            btn.setBackground(Color.CYAN);
                            btn.setEnabled(true);
                        } else {

                            btn.setBackground(Color.WHITE);
                            btn.setEnabled(false);
                        }
                    } else {

                        if (i < 2) {
                            btn.setBackground(Color.WHITE);
                            btn.setEnabled(false);
                        } else {
                            btn.setBackground(Color.CYAN);
                            btn.setEnabled(true);
                        }
                    }

                    btn.setOpaque(true);

                    String s = "";
                    for (int m = 0; m < game.ntxuva.board[i][j]; m++) {
                        s += "o";
                    }
                    btn.setText(s);

                    btn.setFont(new Font(null, Font.PLAIN, 30));
                    btn.setActionCommand("btn_" + i + "_" + j);

                    btn.addActionListener((ActionEvent e) -> {
                        String comando = e.getActionCommand();

                        String[] split = comando.split("_");

                        System.out.println("X play " + new Position(Integer.parseInt(split[1]), Integer.parseInt(split[2])));
                        game.ntxuva = game.ntxuva.move(new Position(Integer.parseInt(split[1]), Integer.parseInt(split[2])));

                        for (int k = 0; k < Ntxuva.ROWS; k++) {
                            for (int l = 0; l < Ntxuva.COLUMNS; l++) {
                                String stones = "";
                                for (int m = 0; m < game.ntxuva.board[k][l]; m++) {
                                    stones += "o";
                                }
                                buttons[k][l].setText(stones);

                                if (game.ntxuva.turn == 'x') {
                                    if (k < 2) {
                                        buttons[k][l].setBackground(Color.CYAN);
                                        buttons[k][l].setEnabled(true);
                                    } else {

                                        buttons[k][l].setBackground(Color.WHITE);
                                        buttons[k][l].setEnabled(false);
                                    }
                                } else {
                                    if (k < 2) {
                                        buttons[k][l].setBackground(Color.WHITE);
                                        buttons[k][l].setEnabled(false);
                                    } else {
                                        buttons[k][l].setBackground(Color.CYAN);
                                        buttons[k][l].setEnabled(true);
                                    }
                                }
                            }
                        }

                        if (!game.ntxuva.gameEnd()) {
                            // find the best move
                            // move the mest move
                        }

                        StringBuilder message = new StringBuilder();
                        if (game.ntxuva.gameEnd()) {

                            if (game.ntxuva.win('x')) {
                                message.append("You Won");
                            } else if (game.ntxuva.win('o')) {
                                message.append("The computer Won");
                            } else {
                                message.append("Its a draw");
                            }

                            JOptionPane.showMessageDialog(null, message.toString());
                        }

                    });

                    buttons[i][j] = btn;
                    frame.add(btn);
                }

            }

            frame.pack();
            frame.setVisible(true);
        });

    }

}
