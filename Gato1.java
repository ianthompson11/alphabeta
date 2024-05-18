import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gato1 extends JFrame implements ActionListener {

    private static JButton boton1, boton2, boton3, boton4, boton5, boton6, boton7,boton8;
    private static JButton[] boton, xoo;
    private static JButton[][] botones;
    public static JButton[][] botonpc;
    private static JPanel panelSuperior, panelBotones; 
    private static JPanel panelMultijugador, panelComputador, panelGamePC, panelXO, panelBotones2; 
    private static JLabel etiqueta1, etiqueta2, etiqueta3, etiqueta4, etiqueta5 ;
    public static JLabel etiqueta6; 
    private static int r =0, cont = 0;  
    public static int nivdif = 0;
    public static String x0; /*viene de Menu */

    /*esto es traido de CruzCero */

    protected boolean team0, teamX;
    // Character X & 0
    public static String targetPC, targetHuman;
    // variable to determine first turn
    protected boolean win;
    // object of minmax
    protected MinMax minMax = new MinMax();

    protected JButton btnTemporal = new JButton();

    /*Fin del inciso */



    public Gato1() {
        setTitle("Juego del Gato");

        /*PANEL SUPERIOR */

        panelSuperior = new JPanel(); 
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBounds(-5,0, 400, 400);
        panelSuperior.setBackground(new Color(15,15,15));
        //panelSuperior.setBorder(null);
        panelSuperior.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(panelSuperior);

        panelSuperior.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta1 = new JLabel("Juego del Gato"); 
        etiqueta1.setFont(new Font("Arial", Font.BOLD, 20)); 
        etiqueta1.setForeground(Color.WHITE);
        etiqueta1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(etiqueta1); 

        panelSuperior.add(Box.createRigidArea(new Dimension(0, 30)));

        etiqueta2 = new JLabel("¿Contra quien desea jugar?"); 
        etiqueta2.setFont(new Font("Arial", Font.BOLD, 15)); 
        etiqueta2.setForeground(new Color(123,141,147));
        etiqueta2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(etiqueta2); 
        
        panelSuperior.add(Box.createRigidArea(new Dimension(0, 30)));

        boton1 = new JButton ("Computador"); 
        boton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton1.setMaximumSize(new Dimension(200, 40)); // Establecemos un tamaño máximo
        boton1.setBackground(new Color(145,71,255));
        boton1.setFont(new Font("Arial", Font.BOLD, 15)); 
        boton1.setFocusPainted(false);
        boton1.setForeground(Color.WHITE);
        boton1.addActionListener(this); 
        panelSuperior.add(boton1); 

        panelSuperior.add(Box.createRigidArea(new Dimension(0, 5)));

        boton2 = new JButton ("Otro Jugador"); 
        boton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton2.setMaximumSize(new Dimension(200, 40)); // Establecemos un tamaño máximo
        boton2.setBackground(new Color(145,71,255));
        boton2.setFocusPainted(false);
        boton2.setFont(new Font("Arial", Font.BOLD, 15));
        boton2.setForeground(Color.WHITE);
        boton2.addActionListener(this); 
        panelSuperior.add(boton2); 

        panelSuperior.add(Box.createRigidArea(new Dimension(0, 5))); 

        boton3 = new JButton ("No quiero Jugar"); 
        boton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton3.setMaximumSize(new Dimension(200, 40)); // Establecemos un tamaño máximo
        boton3.setBackground(new Color(145,71,255));
        boton3.setFocusPainted(false);
        boton3.setBorderPainted(false);
        boton3.setFont(new Font("Arial", Font.BOLD, 15));
        boton3.addActionListener(this); 
        boton3.setForeground(Color.WHITE);
        panelSuperior.add(boton3); 

        /*PANEL MULTIJUGADOR */

        panelMultijugador = new JPanel(); /*Aqui deberia estar boton[], una etiqueta nueva y el boton4 */
        panelMultijugador.setLayout(new BoxLayout(panelMultijugador, BoxLayout.Y_AXIS));
        panelMultijugador.setBounds(-5,0, 400, 400);
        panelMultijugador.setBackground(new Color(15,15,15));
        panelMultijugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelMultijugador.setVisible(false); 
        add(panelMultijugador);

        panelMultijugador.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta3 = new JLabel("Turno de O"); /*HAY QUE HACER CAMBIOS EN DONDE SE CAMBIA LA ETIQUETA 1 DENTRO DE BOTON2 */
        etiqueta3.setFont(new Font("Arial", Font.BOLD, 20)); 
        etiqueta3.setForeground(Color.WHITE);
        etiqueta3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelMultijugador.add(etiqueta3); 

        panelMultijugador.add(Box.createRigidArea(new Dimension(0, 30)));

        panelBotones = new JPanel(new GridLayout(3, 3));
        panelBotones.setMaximumSize(new Dimension(225, 225)); 
        //panelBotones.setVisible(false); 
        panelMultijugador.add(panelBotones); 

        botones = new JButton[3][3];
   
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                
                botones[i][j] = new JButton("");
                //registroBotones[i][j] = (""+numero); 
                botones[i][j].setPreferredSize(new Dimension(75, 75)); // Establecer tamaño preferido del botón
                botones[i][j].addActionListener(this);
                botones[i][j].setBackground(new Color(195, 155, 255));
                panelBotones.add(botones[i][j]);
                //botones[i][j].setBackground(new Color(145,71,255));
                botones[i][j].setEnabled(true); 
            }
        }

        panelMultijugador.add(Box.createRigidArea(new Dimension(0, 10)));

        boton4 = new JButton("Menu Principal"); 
        boton4.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton4.setMaximumSize(new Dimension(225, 40)); // Establecemos un tamaño máximo
        boton4.setBackground(new Color(69,16,147));
        boton4.setFocusPainted(false);
        boton4.setBorderPainted(false);
        boton4.setFont(new Font("Arial", Font.BOLD, 15));
        boton4.addActionListener(this);
        //boton4.setVisible(false);  
        boton4.setForeground(Color.WHITE);
        panelMultijugador.add(boton4);

        /*PANEL COMPUTADOR */

        panelComputador = new JPanel();
        panelComputador.setLayout(new BoxLayout(panelComputador, BoxLayout.Y_AXIS));
        panelComputador.setBounds(-5, 0, 400, 400);
        panelComputador.setBackground(new Color(15, 15, 15));
        panelComputador.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelComputador.setVisible(false); 
        add(panelComputador);

        panelComputador.add(Box.createRigidArea(new Dimension(0, 30))); 

        etiqueta4 = new JLabel("¿Que nivel de dificultad desea?"); 
        etiqueta4.setFont(new Font("Arial", Font.BOLD, 15)); 
        etiqueta4.setForeground(new Color(123,141,147));
        etiqueta4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelComputador.add(etiqueta4); 

        panelComputador.add(Box.createRigidArea(new Dimension(0, 30))); 


        boton = new JButton[4]; 

        for(int i =0; i<4; i++){

                
                if(i == 0){

                    boton[i] = new JButton("Facil");
                    panelComputador.add(Box.createRigidArea(new Dimension(0, 5)));
                    boton[0].setBackground(new Color(195, 155, 255));

                }else if(i==1){

                    boton[i] = new JButton("Normal");
                    panelComputador.add(Box.createRigidArea(new Dimension(0, 5))); 
                    boton[1].setBackground(new Color(145,71,255));

                }else if(i==2){

                    boton[i] = new JButton("Dificil");
                    panelComputador.add(Box.createRigidArea(new Dimension(0, 5))); 
                    boton[2].setBackground(new Color(69,16,147)); 


                }else if(i==3){

                    panelComputador.add(Box.createRigidArea(new Dimension(0, 5))); 
                    boton[i] = new JButton("Atras");
                    boton[3].setBackground(new Color(39, 31, 51));


                }

                boton[i].setAlignmentX(Component.CENTER_ALIGNMENT);
                boton[i].setMaximumSize(new Dimension(200, 40)); // Establecemos un tamaño máximo
                boton[i].setFocusPainted(false);
                boton[i].setBorderPainted(false);
                boton[i].setFont(new Font("Arial", Font.BOLD, 15));
                boton[i].addActionListener(this); 
                boton[i].setForeground(Color.WHITE);
                //boton[i].setVisible(false);
                panelComputador.add(boton[i]);

        }

        /*PANEL SELECCION DE X O O */

        panelXO = new JPanel(); /*Aqui deberia estar boton[], una etiqueta nueva y el boton4 */
        panelXO.setLayout(new BoxLayout(panelXO, BoxLayout.Y_AXIS));
        panelXO.setBounds(-5,0, 400, 400);
        panelXO.setBackground(new Color(15,15,15));
        panelXO.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelXO.setVisible(false); 
        add(panelXO);

        panelXO.add(Box.createRigidArea(new Dimension(0, 30))); 

        etiqueta5 = new JLabel("¿Desea usar 'X' o 'O'"); 
        etiqueta5.setFont(new Font("Arial", Font.BOLD, 15)); 
        etiqueta5.setForeground(new Color(123,141,147));
        etiqueta5.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelXO.add(etiqueta5); 

        panelXO.add(Box.createRigidArea(new Dimension(0, 30))); 

        boton5 = new JButton("X");
        //panelXO.add(Box.createRigidArea(new Dimension(0, 5))); 
        boton5.setBackground(new Color(69,16,147)); 
        boton5.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton5.setMaximumSize(new Dimension(200, 40)); // Establecemos un tamaño máximo
        boton5.setFocusPainted(false);
        boton5.setBorderPainted(false);
        boton5.setFont(new Font("Arial", Font.BOLD, 15));
        boton5.addActionListener(this); 
        boton5.setForeground(Color.WHITE);
        panelXO.add(boton5); 

        panelXO.add(Box.createRigidArea(new Dimension(0, 5))); 

        boton6 = new JButton("O"); 
        boton6.setBackground(new Color(69,16,147)); 
        boton6.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton6.setMaximumSize(new Dimension(200, 40)); // Establecemos un tamaño máximo
        boton6.setFocusPainted(false);
        boton6.setBorderPainted(false);
        boton6.setFont(new Font("Arial", Font.BOLD, 15));
        boton6.addActionListener(this); 
        boton6.setForeground(Color.WHITE);
        panelXO.add(boton6); 

        panelXO.add(Box.createRigidArea(new Dimension(0, 5))); 

        boton7 = new JButton("Atras");
        boton7.setBackground(new Color(69,16,147)); 
        boton7.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton7.setMaximumSize(new Dimension(200, 40)); // Establecemos un tamaño máximo
        boton7.setFocusPainted(false);
        boton7.setBorderPainted(false);
        boton7.setFont(new Font("Arial", Font.BOLD, 15));
        boton7.addActionListener(this); 
        boton7.setForeground(Color.WHITE);
        panelXO.add(boton7);

        /*PANEL JUEGO COMPUTADOR */

        panelGamePC = new JPanel(); /*Aqui deberia estar boton[], una etiqueta nueva y el boton4 */
        panelGamePC.setLayout(new BoxLayout(panelGamePC, BoxLayout.Y_AXIS));
        panelGamePC.setBounds(-5,0, 400, 400);
        panelGamePC.setBackground(new Color(15,15,15));
        panelGamePC.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelGamePC.setVisible(false); 
        add(panelGamePC);

        panelGamePC.add(Box.createRigidArea(new Dimension(0, 10)));

        etiqueta6 = new JLabel("Turno de O"); /*HAY QUE HACER CAMBIOS EN DONDE SE CAMBIA LA ETIQUETA 1 DENTRO DE BOTON2 */
        etiqueta6.setFont(new Font("Arial", Font.BOLD, 20)); 
        etiqueta6.setForeground(Color.WHITE);
        etiqueta6.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelGamePC.add(etiqueta6); 

        panelGamePC.add(Box.createRigidArea(new Dimension(0, 30)));

        panelBotones2 = new JPanel(new GridLayout(3, 3));
        panelBotones2.setMaximumSize(new Dimension(225, 225)); 
        panelBotones2.setVisible(true);  
        panelGamePC.add(panelBotones2); 

        botonpc = new JButton[3][3];
   
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                
                botonpc[i][j] = new JButton("");
                //registroBotones[i][j] = (""+numero); 
                botonpc[i][j].setPreferredSize(new Dimension(75, 75)); // Establecer tamaño preferido del botón
                botonpc[i][j].addActionListener(this);
                botonpc[i][j].setBackground(new Color(230, 190, 255));
                panelBotones2.add(botonpc[i][j]);
                botonpc[i][j].setEnabled(true); 
            }
        }

        panelGamePC.add(Box.createRigidArea(new Dimension(0, 10)));

        boton8 = new JButton("Menu Principal"); 
        boton8.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton8.setMaximumSize(new Dimension(225, 40)); // Establecemos un tamaño máximo
        boton8.setBackground(new Color(69,16,147));
        boton8.setFocusPainted(false);
        boton8.setBorderPainted(false);
        boton8.setFont(new Font("Arial", Font.BOLD, 15));
        boton8.addActionListener(this);
        //boton8.setVisible(false);  
        boton8.setForeground(Color.WHITE);
        panelGamePC.add(boton8);





        

       //JPanel panelInferior = new JPanel(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS)); 

        setLayout(null); 

        
    }

    public void actionPerformed(ActionEvent e) {

        
        /*INSTRUCIONES PARA MODO COMPUTADOR */

        if(e.getSource() == boton1){ /*Instrucciones para Boton 1, MODO COMPUTADOR */

            System.out.println("El botón 1 'Modo Computador' fue presionado");

            /*
             * 
             *             for(int i =0; i<4; i++){

               boton[i].setVisible(true); 

            }
             */




            /*boton1.setVisible(false); 
            boton2.setVisible(false); 
            boton3.setVisible(false);    
            etiqueta2.setVisible(false);*/
            panelSuperior.setVisible(false);
            panelComputador.setVisible(true); 

        }

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (e.getSource() == botonpc[i][j] && minMax.gameValues[i][j] == 0) {
                    botonpc[i][j].setFont(new Font("Arial", Font.BOLD, 30));
                    botonpc[i][j].setText(targetHuman);
                    minMax.gameValues[i][j] = 1;
                    minMax.consoleLog();
                    //botonpc[i][j].removeActionListener(this);
                    botonpc[i][j].setEnabled(false); 
                    if (!minMax.isFull() && (minMax.isWin() != 1 && minMax.isWin() != 2)) {
                        minMax.miniMax();
                    }
                    minMax.endMessage();
                    /*
                                         * esto hacia que se muestre 2 veces el mensaje que la pc gano, ya se corrigio
                                         *//*hay que revisar esta vaina */
                    if (minMax.isFull()) {
                        etiqueta6.setText("EMPATE!"); 
                    }
                }
            }
        }

        if(e.getSource() == boton[0]){

            nivdif=1;
            System.out.println("El boton[0] 'Facil' fue presionado");
            panelComputador.setVisible(false); 
            panelXO.setVisible(true); 



        }else if(e.getSource() == boton[1]){
            nivdif=5;
            System.out.println("El boton[1] 'Normal' fue presionado");
            panelComputador.setVisible(false); 
            panelXO.setVisible(true); 
        



        }else if(e.getSource() == boton[2]){

            nivdif=9;
            System.out.println("El boton[2] 'Dificil' fue presionado");
            panelComputador.setVisible(false); 
            panelXO.setVisible(true); 



        }else if(e.getSource() == boton[3]){

            System.out.println("El boton[3] 'Atras' fue presionado, del modo Computador");


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    botones[i][j].setText(""); 
                    botones[i][j].setEnabled(true); 
                }
            }

            JButton botonde = new JButton(); 
            botonde = boton[3]; 

            MenuPrincipal(botonde); 

        }

        if(e.getSource()==boton5){

            System.out.println("El boton5 'X' fue presionado, del modo Computador");
            /*de Menu */
            teamX = false;
            team0 = true;
            targetHuman = "X";
            targetPC = "0";
            x0 = "X";
            minMax.initValues();
            if (!turno()) {
                // this is the best movement to first player
                System.out.println("Llama a miniMax"); 
                minMax.miniMax();
            }
            panelXO.setVisible(false); 
            panelGamePC.setVisible(true); 

        }else if(e.getSource()==boton6){

            System.out.println("El boton6 'O' fue presionado, del modo Computador");
            /*de Menu */
            teamX = true;
            team0 = false;
            targetHuman = "0";
            targetPC = "X";
            x0 = "0";
            minMax.initValues();
            if (!turno()) {
                // this is the best movement to first player
                minMax.miniMax();
            }
            panelXO.setVisible(false); 
            panelGamePC.setVisible(true);

        }else if(e.getSource()==boton7){

            System.out.println("El boton7 'Atras' fue presionado, del panelXO");

            panelXO.setVisible(false); 
            panelComputador.setVisible(true); 
            
        }

        if(e.getSource() == boton8){

            
            MenuPrincipal(boton8); 

            System.out.println("El boton8 'MenuPrincipal' fue presionado, del panelGamePC");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    botonpc[i][j].setText(""); /*Se limpia el texto de los botones */
                    botonpc[i][j].setEnabled(true); /*Se reactivan los botones */
                }
            }
            minMax.initValues();


        }


        /*INSTRUCCIONES PARA MODO MULTIJUGADOR */

        if(e.getSource() == boton2){

            System.out.println("El botón 2 'Otro Jugador' fue presionado");

            panelMultijugador.setVisible(true); 
            panelSuperior.setVisible(false); 


        }

        if(e.getSource() == boton4){ /*Seccion para cuando se presiona el boton MENU PRINCIPAL */

            System.out.println("El boton4 'Menu Principal' fue presionado, del modo Multijugador");

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    botones[i][j].setText(""); /*Se limpia el texto de los botones */
                    botones[i][j].setEnabled(true); /*Se reactivan los botones */
                }
            }

            MenuPrincipal(boton4); 


        }

        /*INSTRUCCIONES PARA NO QUIERO JUGAR */
        if(e.getSource() == boton3){

            System.exit(0);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton botonPresionado = botones[i][j]; // Obtener el botón correspondiente
                if (e.getSource() == botonPresionado) { // Verificar si el evento proviene de este botón
                    botonPresionado.setEnabled(false); // Deshabilitar el botón
                    r++; 
                    Multijugador(botonPresionado); 
                    
                }
            }
        }

        
    }

    boolean turno() {/*metodo de cruzCero */
        double firstTurn = Math.random();
        System.out.println("La variable firstTurn tiene el siguiente valor: " +firstTurn); 
        if (firstTurn < 0.7) { // <- determined 60% of the time
            if (x0 == "0") {
                etiqueta6.setText("Inicia la COMPUTADORA con X !"); 
                //JOptionPane.showMessageDialog(mDialog, "Inicia la COMPUTADORA con X !");
                return false;
            } else {

                etiqueta6.setText("Inicia la COMPUTADORA con 0 !"); 
                //JOptionPane.showMessageDialog(mDialog, "Inicia la COMPUTADORA con 0 !");
                return false;
            }

        } else {

            etiqueta6.setText("TU INICIAS"); 
            //JOptionPane.showMessageDialog(mDialog, "El primer turno es de USTED! \n            Juegas con " + Menu.x0);
            return true; // <- other 40%
        }
    }


    private void Multijugador(JButton boton) {

        boton.setFont(new Font("Arial", Font.BOLD, 30));
        cont++; 

        if(r%2 == 0){

            boton.setText("X"); 
            etiqueta3.setText("Turno de O"); 
            //ganador(); 
            
        }else{

            boton.setText("O");
            etiqueta3.setText("Turno de X");

        }

        String ganador = ganador();
        if (ganador != null) {

            etiqueta3.setText("El jugador " + ganador + " es el ganador!");

            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    botones[i][j].setEnabled(false); // Deshabilitar el botón  
                    
                }
            }


        }else if(ganador==null && cont ==9){

            etiqueta3.setText("No hay un ganador!");


        }


        

        
    }

    public String ganador() {

        // Revisar filas
        for (int i = 0; i < 3; i++) {
            if (botones[i][0].getText().equals(botones[i][1].getText()) && botones[i][0].getText().equals(botones[i][2].getText()) && !botones[i][0].getText().equals("")) {
                return botones[i][0].getText(); // Retorna el símbolo del jugador ganador
            }
        }
        // Revisar columnas
        for (int j = 0; j < 3; j++) {
            if (botones[0][j].getText().equals(botones[1][j].getText()) && botones[0][j].getText().equals(botones[2][j].getText()) && !botones[0][j].getText().equals("")) {
                return botones[0][j].getText(); // Retorna el símbolo del jugador ganador
            }
        }
        // Revisar diagonales
        if (botones[0][0].getText().equals(botones[1][1].getText()) && botones[0][0].getText().equals(botones[2][2].getText()) && !botones[0][0].getText().equals("")) {
            return botones[0][0].getText(); // Retorna el símbolo del jugador ganador
        }
        if (botones[0][2].getText().equals(botones[1][1].getText()) && botones[0][2].getText().equals(botones[2][0].getText()) && !botones[0][2].getText().equals("")) {
            return botones[0][2].getText(); // Retorna el símbolo del jugador ganador
        }
        // Si no hay un ganador
        return null;
    }

    public void MenuPrincipal(JButton botonete){

        r=0;

        if(botonete == boton4){

            panelSuperior.setVisible(true); 
            panelMultijugador.setVisible(false); 
            etiqueta3.setText("Turno de O"); 
        }else if(botonete == boton[3]){

            panelSuperior.setVisible(true); 
            panelComputador.setVisible(false); 
            /*aqui va el otro panel */

        }else if(botonete == boton8){

            panelSuperior.setVisible(true); 
            panelGamePC.setVisible(false); 

        }
        cont =0; 

    }
    

    public static void main(String[] args) {
        Gato1 ventana = new Gato1();
        ventana.setBounds(0, 0, 400, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

