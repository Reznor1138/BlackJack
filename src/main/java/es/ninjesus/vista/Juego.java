package es.ninjesus.vista;

import es.ninjesus.controladores.Carta;
import es.ninjesus.controladores.Jugador;
import es.ninjesus.controladores.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Juego extends JFrame implements ActionListener{

    private Tablero tablero;
    public Jugador jugador = new Jugador("jugador");
    public Jugador croupier = new Jugador("croupier");

    private JButton jbtnHit = new JButton("Pedir Carta");
    private JButton jbtnStay = new JButton("Plantarse");
    private JButton jbtnDeal = new JButton("Ronda");

    private JLabel jlblStatus = new JLabel(" ", JLabel.CENTER);

    JPanel playerPanel = new JPanel();
    JPanel dealerPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    JPanel statusPanel = new JPanel();

    Juego() {
        JFrame gameFrame = new JFrame("BlackJack");
        gameFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/antoniojesus/IntelliJ\\ proyectos/BlackJack/cards/10.png \n"));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonsPanel.add(jbtnHit);
        buttonsPanel.add(jbtnStay);
        buttonsPanel.add(jbtnDeal);
        statusPanel.add(jlblStatus);

        jbtnHit.addActionListener(this);
        jbtnStay.addActionListener(this);
        jbtnDeal.addActionListener(this);

        jbtnHit.setEnabled(false);
        jbtnStay.setEnabled(false);

        dealerPanel.setBackground(Color.pink);
        playerPanel.setBackground(Color.pink);
        buttonsPanel.setBackground(Color.pink);
        statusPanel.setBackground(Color.pink);

        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(dealerPanel, BorderLayout.NORTH);
        gameFrame.add(playerPanel, BorderLayout.CENTER);
        gameFrame.add(buttonsPanel, BorderLayout.SOUTH);
        gameFrame.add(statusPanel, BorderLayout.WEST);
        gameFrame.repaint();
        gameFrame.setSize(450, 350);
        gameFrame.setVisible(true);
    }

    private void pedirJugador() {
        Carta newCard = jugador.dealTo(tablero.dealFrom());
        playerPanel.add(new JLabel(new ImageIcon("cards/" + newCard.toString())));
        playerPanel.updateUI();
    }

    private void levantarCartaCroupier() {
        Carta newCard = croupier.dealTo(tablero.dealFrom());
        dealerPanel.add(new JLabel(new ImageIcon("cards/b2fv.png")));
        dealerPanel.updateUI();
    }

    private void pedirCroupier() {
        Carta newCard = croupier.dealTo(tablero.dealFrom());
        dealerPanel.add(new JLabel(new ImageIcon("cards/" + newCard.toString())));
        dealerPanel.updateUI();
    }

    private void deal() {
        playerPanel.removeAll();
        dealerPanel.removeAll();
        playerPanel.updateUI();
        dealerPanel.updateUI();
        jugador.reset();
        croupier.reset();
        if (tablero == null || tablero.size() < 15) {
            tablero = new Tablero();
            tablero.shuffle();
            jlblStatus.setText("Barajando...");
        }
        pedirJugador();
        levantarCartaCroupier();
        pedirJugador();
        pedirCroupier();
    }

    private void checkWinner() {
        dealerPanel.removeAll();
        for (int i = 0; i < croupier.inHand(); i++) {
            dealerPanel.add(new JLabel(new ImageIcon("cards/" + croupier.cartas[i].toString())));
        }
        if (jugador.value() > 21) {
            jlblStatus.setText("El jugador se pasa!");
        } else if (croupier.value() > 21) {
            jlblStatus.setText("La Casa se pasa");
        } else if (croupier.value() == jugador.value()) {
            jlblStatus.setText("Empate");
        } else if (croupier.value() < jugador.value()) {
            jlblStatus.setText("Gana el jugador");
        } else {
            jlblStatus.setText("Gana la casa");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtnHit) {
            pedirJugador();
            if (jugador.value() > 21) {
                checkWinner();
                jbtnHit.setEnabled(false);
                jbtnStay.setEnabled(false);
                jbtnDeal.setEnabled(true);
            }
        }

        if (e.getSource() == jbtnStay) {
            while (croupier.value() < 17 || jugador.value() > croupier.value()) {
                pedirCroupier();
            }
            checkWinner();
            jbtnHit.setEnabled(false);
            jbtnStay.setEnabled(false);
            jbtnDeal.setEnabled(true);
        }

        if (e.getSource() == jbtnDeal) {
            deal();
            jlblStatus.setText(" ");
            jbtnHit.setEnabled(true);
            jbtnStay.setEnabled(true);
            jbtnDeal.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new Juego();
    }
}
