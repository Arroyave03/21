package Practica1;

import javax.swing.JOptionPane;

/**
 * Main principal del juego 21
 *
 * @author Samuel Arroyave Romero
 */

public class Taller1 {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,
                "Bienvenido al juego de 21\n Recuerde que el 0 es el 10 y el As es el 1");
        byte jugadores = Byte.parseByte(JOptionPane.showInputDialog("Ingrese el numero de jugadores: "));
        String[] nombres = new String[jugadores];
        Mano[] manos = new Mano[jugadores];
        boolean ganador = false;

        // Se toman los nombres de los jugadores
        for (int i = 0; i < jugadores; i++) {
            nombres[i] = JOptionPane.showInputDialog("Ingrese el nombre del jugador " + (i + 1) + ": ");
        }

        Baraja b = new Baraja();
        b.barajar(); // Se baraja la baraja

        // Se reparten las primeras dos cartas y se imprimer la mano de cada jugador
        for (byte i = 0; i < jugadores; i++) {
            manos[i] = entregaDealer(b, new Mano());
            System.out.println("*****" + nombres[i] + "*****\n" + manos[i].toString());

            // Si en las primeras dos cartas el jugador obtiene 21, gana automaticamente
            if (manos[i].valor() == 21) {
                System.out.println(nombres[i] + " ganó con un total de: " + manos[i].valor());
                return;
            }
        }

        // Que accion va a realizar el jugador, si tomar otra carta o pasar
        for (byte i = 0; i < jugadores; i++) {
            if (JOptionPane.showConfirmDialog(null,
                    nombres[i] + " ¿Desea otra carta?\n " + manos[i].toString()) == 0) {
                manos[i].agregarCarta(b.sacarCarta());
                System.out.println("*****" + nombres[i] + "*****\n" + manos[i].toString());

                // Si al tomar otra carta, este numero supera los 21, el jugador sale del juego
                if (manos[i].valor() == 0) {
                    System.out.println(nombres[i] + " perdio\n");
                    nombres = eliminarJugador(nombres, i);
                    manos = eliminarMano(manos, i);
                    jugadores--;
                    i--;
                } else if (manos[i].valor() == 21) { // O con los 21 exactos, gana
                    ganador = true;
                    System.out.println(nombres[i] + " ganó con un total de: " + manos[i].valor());
                    break;
                }
            }
        }

        /*
         * Luego de pasar la ronda, si no hay ganador, se busca el jugador o los
         * jugadores (en caso de empate) con el valor mas alto
         */
        if (ganador == false) {
            int valorw = manos[0].valor();
            String[] nombrew = new String[jugadores];
            nombrew[0] = nombres[0];
            String mensajeNombres = "", mensaje = nombrew[0] + " ganó con un total de: " + valorw;
            byte i, j = 1;
            for (i = 1; i < nombres.length; i++) {
                if (manos[i].valor() > valorw) {
                    valorw = manos[i].valor();
                    nombrew[0] = nombres[i];
                    mensaje = nombrew[0] + " ganó con un total de: " + valorw;
                } else if (manos[i].valor() == valorw) {
                    nombrew[j] = nombres[i];
                    j++;
                    for (int k = 0; k < nombrew.length; k++) {
                        mensajeNombres += " / " + nombrew[k];
                    }
                    mensaje = mensajeNombres + " empataron con un total de: " + valorw;
                }
            }
            System.out.println(mensaje);
        }

    }

    // Repartir primeras dos cartas
    public static Mano entregaDealer(Baraja b, Mano m) {

        m.agregarCarta(b.sacarCarta());
        m.agregarCarta(b.sacarCarta());

        return m;
    }

    // Eliminar jugador
    public static String[] eliminarJugador(String[] nombres, byte pos) {

        String[] nombrestmp = new String[nombres.length - 1];

        System.arraycopy(nombres, 0, nombrestmp, 0, pos); // Copiar los elementos antes de la posición
        System.arraycopy(nombres, pos + 1, nombrestmp, pos, nombres.length - pos - 1); // Copiar después de la posición

        nombres = nombrestmp;

        return nombres;
    }

    // Eliminar mano del jugador
    public static Mano[] eliminarMano(Mano[] manos, byte pos) {

        Mano[] manostmp = new Mano[manos.length - 1];

        System.arraycopy(manos, 0, manostmp, 0, pos);
        System.arraycopy(manos, pos + 1, manostmp, pos, manos.length - pos - 1);

        manos = manostmp;

        return manos;
    }
}
