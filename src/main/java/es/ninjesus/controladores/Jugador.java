package es.ninjesus.modelo;

public class Jugador {

    final static int MAX_CARDS = 52;
    public Carta[] cartas = new Carta[MAX_CARDS];
    private int N = 0;
    private String nombre;

    public Jugador(String name) {
        this.nombre = nombre;
    }

    public int inHand() {
        return N;
    }

    public Carta dealTo(Carta c) {
        cartas[N++] = c;
        return c;
    }

    public void reset() {
        N = 0;
    }

    public int value() {
        int val = 0;
        boolean hasAce = false;
        for (int i = 0; i < N; i++) {
            val = val + cartas[i].rank();
            if (cartas[i].esAs()) {
                hasAce = true;
            }
        }
        if (hasAce && (val <= 11)) {
            val = val + 10;
        }
        return val;
    }
}
