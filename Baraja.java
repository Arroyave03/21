package Practica1;

/**
 * Clase Baraja
 *
 * @author Samuel Arroyave Romero
 */

/*
 * El conjunto completo de 52 cartas. Ofrece metodos para barajar las
 * cartas, sacar la siguiente carta.
 */

public class Baraja {
    private Carta[] cartas;
    private int numCartas;

    public Baraja() {
        cartas = new Carta[52];
        numCartas = 0;
        // Primero se crean las cartas del 0 al 9, donde el 0 es el 10 y el as es el 1
        for (byte i = 48; i <= 57; i++) {
            cartas[numCartas] = new Carta("Picas", (char) i);
            numCartas++;
            cartas[numCartas] = new Carta("Corazones", (char) i);
            numCartas++;
            cartas[numCartas] = new Carta("Diamantes", (char) i);
            numCartas++;
            cartas[numCartas] = new Carta("Treboles", (char) i);
            numCartas++;
        }
        // Luego se crean las cartas J, Q y K que desde la clase Carta valen 10
        for (char c : new char[] { 'J', 'Q', 'K' }) {
            for (int j = 0; j < 4; j++) {
                String palo;
                switch (j) {
                    case 0:
                        palo = "Picas";
                        break;
                    case 1:
                        palo = "Corazones";
                        break;
                    case 2:
                        palo = "Diamantes";
                        break;
                    default:
                        palo = "Treboles";
                        break;
                }
                cartas[numCartas] = new Carta(palo, c);
                numCartas++;
            }
        }
    }

    public void barajar() {
        for (int i = 0; i < numCartas; i++) {
            int j = (int) (Math.random() * numCartas);
            Carta temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }
    }

    public Carta sacarCarta() {
        numCartas--;
        return cartas[numCartas];
    }

    public int cartasDisponibles() {
        return numCartas;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < numCartas; i++) {
            s += cartas[i].toString() + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        Baraja b = new Baraja();
        System.out.println(b.toString());
        b.barajar();
        System.out.println(b.toString());
        System.out.println(b.cartasDisponibles());
        System.out.println(b.sacarCarta().toString());
        System.out.println(b.cartasDisponibles());
    }
}
