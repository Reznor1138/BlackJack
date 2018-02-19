package es.ninjesus.modelo;

public class Tablero {

    final static int TABLERO_SIZE = 52;
    private Carta[] cartas;
    private int N = 0;

    public Tablero() {
        cartas = new Carta[TABLERO_SIZE];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cartas[N] = new Carta(N, j, i + "" + j + ".png");
                N++;
            }
        }
    }

    public Carta dealFrom() {
        return cartas[--N];
    }

    public boolean isEmpty() {
        return (N == 0);
    }

    public int size() {
        return N;
    }

    public void shuffle() {
        for (int i = 0; i < N; i++) {
            int r = (int) (Math.random() * i);
            Carta swap = cartas[i];
            cartas[i] = cartas[r];
            cartas[r] = swap;
        }
    }
}
