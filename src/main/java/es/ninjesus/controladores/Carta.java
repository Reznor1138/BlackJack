package es.ninjesus.controladores;

public class Carta {

    private int numCarta;
    private int valorAs;
    private String front;

    Carta(int numCarta, int valorAs, String front) {
        this.numCarta = numCarta;
        this.valorAs = valorAs;
        this.front = front;
    }

    public boolean esAs() {
        return valorAs == 0;
    }

    public int rank() {
        if (valorAs == 0) {
            return 1;
        }
        if (valorAs >= 9) {
            return 10;
        }
        return valorAs + 1;
    }

    public String toString() {
        return this.front;
    }
}
