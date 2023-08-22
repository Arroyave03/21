package Practica1;

/**
 * Clase Carta
 *
 * @author Samuel Arroyave Romero
 */

// Descrita por la combinacion de pinta y valor

public class Carta {
    private String pinta;
    private char valor;

    public Carta(String pinta, char valor) {
        this.pinta = pinta;
        this.valor = valor;
    }

    public String getPinta() {
        return pinta;
    }

    public char getValor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }

    public void setPinta(String pinta) {
        this.pinta = pinta;
    }

    public String toString() {
        return "Cartas: " + valor + " de " + pinta + "\n";
    }

    // Metodo para obtener el valor numerico de la carta ya que se está usando char
    public int getValorNumerico() {
        int valor = 0;
        switch (this.valor) {
            case '0': // El 0 tomará el valor de 10
                valor = 10;
                break;
            case 'J':
                valor = 10;
                break;
            case 'Q':
                valor = 10;
                break;
            case 'K':
                valor = 10;
                break;
            default:
                valor = Character.getNumericValue(this.valor);
                break;
        }
        return valor;
    }

    public static void main(String[] args) {
        Carta c = new Carta("Picas", '0');
        System.out.println(c.toString());
        System.out.println(c.getValorNumerico());

    }
}
