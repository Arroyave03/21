package Practica1;

/**
 * Clase Mano
 *
 * @author Samuel Arroyave Romero
 */

/*
 * Conjunto de cartas de cada jugador. Inicialmente vacio.
 * El ADT Mano tiene una operacion valor que retorna el mayor valor
 * inferior o igual a 21 del conjunto de cartas en la mano.
 */

public class Mano {
    private Carta[] cartas;
    private int numCartas;

    public Mano() {
        cartas = new Carta[10];
        numCartas = 0;
    }

    public void agregarCarta(Carta c) {
        cartas[numCartas] = c;
        numCartas++;
    }

    public int valor() {
        int valor = 0;
        boolean as = false;
        for (int i = 0; i < numCartas; i++) {
            valor += cartas[i].getValorNumerico();
            if (cartas[i].getValorNumerico() == 1) {
                as = true;
            } else if (valor > 21) {
                return 0;
            }
        }
        if (as == true && valor <= 11) {
            valor += 10;
        }

        return valor;
    }

    public boolean equals(Mano m) {
        /*
         * if (this.numCartas != m.numCartas) {
         * return false;
         * }
         */
        /*
         * Decimos que dos manos son iguales si tienen el mismo valor total
         * por lo que la cantidad de cartas no importa
         */

        int valor = 0, valorm = 0;
        boolean as = false, asm = false;
        for (int i = 0; i < this.numCartas; i++) {
            valor += this.cartas[i].getValorNumerico();
            if (this.cartas[i].getValorNumerico() == 1) {
                as = true;
            }
            if (as && valor <= 11) {
                valor += 10;
            }
        }
        for (int i = 0; i < m.numCartas; i++) {
            valorm += m.cartas[i].getValorNumerico();
            if (m.cartas[i].getValorNumerico() == 1) {
                asm = true;
            }
            if (asm && valorm <= 11) {
                valorm += 10;
            }
        }
        if (valor != valorm)
            return false;
        else
            return true;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < numCartas; i++) {
            s += cartas[i].toString() + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        Mano m1 = new Mano();
        m1.agregarCarta(new Carta("Picas", 'Q'));
        m1.agregarCarta(new Carta("Corazon", '1'));
        m1.agregarCarta(new Carta("Corazon", 'K'));

        Integer m1valor = m1.valor();
        System.out.println(m1valor);

        Mano m2 = new Mano();
        m2.agregarCarta(new Carta("Trebol", '5'));
        m2.agregarCarta(new Carta("Trebol", '8'));
        m2.agregarCarta(new Carta("Trebol", '6'));

        Integer m2valor = m2.valor();
        System.out.println(m2valor);

        Mano m3 = new Mano();
        m3.agregarCarta(new Carta("Diamante", '8'));
        m3.agregarCarta(new Carta("Picas", '0'));
        m3.agregarCarta(new Carta("Trebol", '1'));

        Integer m3valor = m3.valor();
        System.out.println(m3valor);

        assert m1valor == 23;
        assert m1.equals(m3);
        assert m1.equals(m2);
    }

}
