import javax.swing.*;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

import java.awt.Font;
import java.util.*;

public class MinMax {
    protected Integer[][] gameValues = new Integer[3][3];
    public static Integer Depth = Gato1.nivdif/* representa nivel de dificultad */, contPlay = 0;
    protected static Integer endBoard = 3;

    // initializing matriz on minmax
    public void initValues() {
        for (int i = 0; i < endBoard; i++) {
            for (int j = 0; j < endBoard; j++)
                gameValues[i][j] = 0;
        }
    }

    // print on console all the plays
    protected void consoleLog() {
        System.out.println("Nivel de Dificultad: " + Depth);
        contPlay = contPlay + 1;
        System.out.println("Jugada: " + contPlay);
        for (int i = 0; i < endBoard; i++) {
            for (int j = 0; j < endBoard; j++) {
                System.out.print("| " + gameValues[i][j] + " | \t");
            }
            System.out.println("");
        }
    }

    // this evaluate if the board is full
    public boolean isFull() {
        for (int i = 0; i < endBoard; i++) {
            for (int j = 0; j < endBoard; j++) {
                if (gameValues[j][i] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // win evalutate
    public int isWin() {
        for (int i = 0; i < endBoard; i++) {
            for (int j = 0; j < endBoard; j++) {
                if (j + 2 < endBoard) {
                    if (gameValues[j][i] == 1 && gameValues[j + 1][i] == 1 && gameValues[j + 2][i] == 1) {
                        return 1;
                    }
                }
                if (i + 2 < endBoard) {
                    if (gameValues[j][i] == 1 && gameValues[j][i + 1] == 1 && gameValues[j][i + 2] == 1) {
                        return 1;
                    }
                }

                if (i + 2 < endBoard && j + 2 < endBoard) {
                    if (gameValues[j][i] == 1 && gameValues[j + 1][i + 1] == 1 && gameValues[j + 2][i + 2] == 1) {
                        return 1;
                    }
                }
                if (i - 2 > -1 && j + 2 < endBoard) {
                    if (gameValues[j][i] == 1 && gameValues[j + 1][i - 1] == 1 && gameValues[j + 2][i - 2] == 1) {
                        return 1;
                    }
                }
                if (i + 2 < endBoard && j - 2 > -1) {
                    if (gameValues[j][i] == 1 && gameValues[j - 1][i + 1] == 1 && gameValues[j - 2][i + 2] == 1) {
                        return 1;
                    }
                }

                if (j + 2 < endBoard) {
                    if (gameValues[j][i] == 2 && gameValues[j + 1][i] == 2 && gameValues[j + 2][i] == 2) {
                        return 2;
                    }
                }
                if (i + 2 < endBoard) {
                    if (gameValues[j][i] == 2 && gameValues[j][i + 1] == 2 && gameValues[j][i + 2] == 2) {
                        return 2;
                    }
                }

                if (i + 2 < endBoard && j + 2 < endBoard) {
                    if (gameValues[j][i] == 2 && gameValues[j + 1][i + 1] == 2 && gameValues[j + 2][i + 2] == 2) {
                        return 2;
                    }
                }
                if (i - 2 > -1 && j + 2 < endBoard) {
                    if (gameValues[j][i] == 2 && gameValues[j + 1][i - 1] == 2 && gameValues[j + 2][i - 2] == 2) {
                        return 2;
                    }
                }
                if (i + 2 < endBoard && j - 2 > -1) {
                    if (gameValues[j][i] == 2 && gameValues[j - 1][i + 1] == 2 && gameValues[j - 2][i + 2] == 2) {
                        return 2;
                    }
                }

            }
        }
        return 0;
    }

    protected void endMessage() {
        if (isWin() == 1) {
            Gato1.etiqueta6.setText("GANASTE!"); 

            //JOptionPane.showMessageDialog(cruzCero.mDialog, "                 GANASTE!");
            //cruzCero.wGame.dispose();
            //Menu.wMenu.setVisible(true);
            for(int i=0; i<3; i++){

                for(int j=0; j<3; j++){
    
                    Gato1.botonpc[i][j].setEnabled(false); 
    
                }
    
            }
        } else if (isWin() == 2) {
            Gato1.etiqueta6.setText("GANO LA COMPUTADORA!"); 
            for(int i=0; i<3; i++){

                for(int j=0; j<3; j++){
    
                    Gato1.botonpc[i][j].setEnabled(false); 
    
                }
    
            }

            //JOptionPane.showMessageDialog(cruzCero.mDialog, "GANO LA COMPUTADORA!");
           // cruzCero.wGame.dispose();
            //Menu.wMenu.setVisible(true);

        }


    }




    // ! minMax algorithm//

    protected void miniMax() {
        int bestRow = -1, bestColumn = -1;
        int max, maxActual;
        max = Integer.MIN_VALUE; /*Inicializa max con el menor valor posible */
        for (int i = 0; i < endBoard; i++) {
            for (int j = 0; j < endBoard; j++) {
                if (gameValues[j][i] == 0) {
                    int tempRow, tempColumn;
                    gameValues[j][i] = 2; /*Movimiento de la computadora, este movimiento es solo de prueba */
                    tempRow = i;
                    tempColumn = j;

                    maxActual = ValorMin(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    /*El codigo de arriba llama a la variable ValorMin que representa predicciones acerca de futuros movimientos del jugador */
                    gameValues[tempColumn][tempRow] = 0; /*Devuelve todo a la normalidad */

                    if (max < maxActual) {
                        max = maxActual;
                        bestRow = tempRow;
                        bestColumn = tempColumn;
                    }

                }
            }
        }
        gameValues[bestColumn][bestRow] = 2;
       // boton.setFont(new Font("Arial", Font.BOLD, 30));
        Gato1.botonpc[bestColumn][bestRow].setFont(new Font("Arial", Font.BOLD, 30));
        Gato1.botonpc[bestColumn][bestRow].setText(Gato1.targetPC);
        Gato1.botonpc[bestColumn][bestRow].setEnabled(false);
        consoleLog();
        // endMessage(); desactive la llamada al metodo para que no muestre en
        // JOptionPane 2 veces

    }

    public int ValorMin(int deph, int alfa, int beta) {

        Depth = Gato1.nivdif;/* representa nivel de dificultad */
        if (isWin() == 1 || isWin() == 2) {
            return heuristics(deph);
        } else if (isFull()) {
            return heuristics(deph);
        } else if (Depth < deph) {
            return heuristics(deph);
        } else {
            for (int i = 0; i < endBoard; i++) {
                for (int j = 0; j < endBoard; j++) {
                    if (gameValues[j][i] == 0) {
                        int tempRow, tempColumn;
                        gameValues[j][i] = 1; /*Esto es tu movimiento, tomando en cuenta que en el codigo tus movimientos son representados
                        * con 1 y los de la laptop son representados con un 2, asi que esta seccion del codigo esta siendo utilizada 
                        * para predecir tus posibles movimientos despues del realizado por la laptop */
                        tempRow = i; 
                        tempColumn = j;

                        beta = min(beta, ValorMax(deph + 1, alfa, beta));
                        /*Es en estas secciones en las que deph va tomando valores, en realidad lo que ocurre es que como estamos
                         * en una llamada recursiva gracias al ciclo for podemos observar que se esta llamando repetitivamente a 
                         * ValorMax y cada vez que se le llama se le da un valor deph mayor, se le esta llamando para cada celda
                         * de la matriz lo que quiere decir que esta revisando la matriz 
                         * Cabe recalcar algo importante, lo que esta haciendo esto es una comparacion, en esta comparacion
                         * min se encarga de analizar cual es menor...el valor beta actual o el nuevo que se obtuvo al llamar a ValorMax
                         * 
                         */

                        gameValues[tempColumn][tempRow] = 0; /*Devuelve todo a la normalidad */


                        /*Esto se encarga realmente de la aplicacion del alpha beta */

                        if (alfa >= beta) {
                            return alfa;
                        }

                    }
                }
            }
            return beta;
        }

    }

    public int ValorMax(int deph, int alfa, int beta) {
        if (isWin() == 1 || isWin() == 2) {
            return heuristics(deph);
        } else if (isFull()) {
            return heuristics(deph);
        } else {
            for (int i = 0; i < endBoard; i++) {
                for (int j = 0; j < endBoard; j++) {
                    if (gameValues[j][i] == 0) {
                        int tempRow, tempColumn;
                        gameValues[j][i] = 2;
                        tempRow = i;
                        tempColumn = j;

                        alfa = max(alfa, ValorMin(deph + 1, alfa, beta));
                        /*Aqui se hace algo muy parecido a lo que se hace en valorMin solo que en este caso se busca el valor mas grande
                         * entre el actual y el nuevo y actualiza la variable con cualquier de los dos 
                         */

                        gameValues[tempColumn][tempRow] = 0;

                        /*ALPHA BETA */

                        if (alfa >= beta) {
                            return beta;
                        }

                    }
                }
            }
            return alfa;
        }

    }

    public int heuristics(int deph) {
        int cost = 0;
        cost = Cost(2, deph) - Cost(1, deph);
        return cost;
    }

    public int Cost(int jugador, int deph) {
        int value = 0;

        for (int i = 0; i < endBoard; i++) {
            for (int j = 0; j < endBoard; j++) {

                // Para 3
                if (j + 2 < endBoard) {
                    if (gameValues[j][i] == jugador && gameValues[j + 1][i] == jugador
                            && gameValues[j + 2][i] == jugador) {
                        value = 1000 - deph;
                    }
                }
                if (i + 2 < endBoard) {
                    if (gameValues[j][i] == jugador && gameValues[j][i + 1] == jugador
                            && gameValues[j][i + 2] == jugador) {
                        value = 1000 - deph;
                    }
                }

                if (i + 2 < endBoard && j + 2 < endBoard) {
                    if (gameValues[j][i] == jugador && gameValues[j + 1][i + 1] == jugador
                            && gameValues[j + 2][i + 2] == jugador) {
                        value = 1000 - deph;
                    }
                }
                if (i - 2 > -1 && j + 2 < endBoard) {
                    if (gameValues[j][i] == jugador && gameValues[j + 1][i - 1] == jugador
                            && gameValues[j + 2][i - 2] == jugador) {
                        value = 1000 - deph;
                    }
                }
                if (i + 2 < endBoard && j - 2 > -1) {
                    if (gameValues[j][i] == jugador && gameValues[j - 1][i + 1] == jugador
                            && gameValues[j - 2][i + 2] == jugador) {
                        value = 1000 - deph;
                    }
                }

                if (j + 1 < endBoard) {
                    if (gameValues[j][i] == jugador && gameValues[j + 1][i] == jugador) {
                        if (value < 300) {
                            value = 300 + deph;
                        }
                    }
                }
                if (i + 1 < endBoard) {
                    if (gameValues[j][i] == jugador && gameValues[j][i + 1] == jugador) {
                        if (value < 300) {
                            value = 300 + deph;
                        }
                    }
                }

                if (i + 1 < endBoard && j + 1 < endBoard) {
                    if (gameValues[j][i] == jugador && gameValues[j + 1][i + 1] == jugador) {
                        if (value < 300) {
                            value = 300 + deph;
                        }
                    }
                }
                if (i - 1 > -1 && j + 1 < endBoard) {
                    if (gameValues[j][i] == jugador && gameValues[j + 1][i - 1] == jugador) {
                        if (value < 300) {
                            value = 300 + deph;
                        }
                    }
                }
                if (i + 1 < endBoard && j - 1 > -1) {
                    if (gameValues[j][i] == jugador && gameValues[j - 1][i + 1] == jugador) {
                        if (value < 300) {
                            value = 300 + deph;
                        }
                    }
                }

            }
        }

        return value;

    }
}
