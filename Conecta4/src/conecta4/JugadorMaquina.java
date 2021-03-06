/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conecta4;

/**
 *
 * @author sistemas inteligentes
 * @author Matthew Quintana
 */
public class JugadorMaquina extends Jugador{
    //Profundidad hasta la que se va a desarrollar el Ã¡rbol de juego
    public final static int NIVEL_DEFECTO = 2;
    private Nodo root;
    
    class Nodo{
        private final Tablero n_tablero;
        private final String n_tipo;
        private final int n_jugador;
        private int n_valor;
        private int n_colPos;
        private int n_rowPos;
        private final int n_nivel;

        public Nodo(Tablero tablero, String tipo, int jugador, int nivel){
            n_tablero = tablero;
            n_tipo = tipo;
            n_jugador = jugador;
            n_nivel = nivel;

        }
        
        private void fijarCol(int colPos){
            n_colPos = colPos;
        }
        
        private void fijarFila(int rowPos){
            n_rowPos = rowPos;
        }
        
        public int obtenerCol(){
            return n_colPos;
        }
        
        public int obtenerFila(){
            return n_rowPos;
        }

        public int obtenerNivel(){
            return n_nivel;
        }

        public int obtenerJugador(){
            return n_jugador;
        }

        public int obtenerValor(){
            return n_valor;
        }

        public String obtenerNodoTipo(){
            return n_tipo;
        }
        
        public Tablero obtenerTablero(){
            return n_tablero;
        }
        
        /**
         * Función de evaluación 
         * 
         * La función devuelve un valor sobre el estado del juego con la 
         * información dentro del nodo. 
         * 
         * La función compraba las casillas cerca de una posición para determinar
         * el valor de poner una ficha allí. Compraba las filas y las columnas para 
         * ver si hay tres en una raya y también para contar el número de
         * fichas propias y oponentes. 
         */
        public int valorarNodo(){       
            
            int valorNodoMin = 0;
            int valorNodoMax = 0;
            
            int numOpponent = 0;
            int numSelf = 0;
            int rowOffset;
            int colOffset;
            int diagonalOffset;
            int valorCasilla;
            
            // Si he ganado, devuelve un valor 
            if(n_tablero.cuatroEnRaya() == n_jugador){
                m_columna = n_colPos;
                return 1000;
            }
            // Si el oponente ha ganado por una manera, evita el jugada que 
            // hizo este estado. 
            else if (n_tablero.cuatroEnRaya() != 0){
                return -300;
            }
                       
            // Check right horizontal
            // Comprobar los tres espacios a la derecha
            for (colOffset = 1; colOffset < 4 && n_colPos + colOffset < n_tablero.numColumnas(); colOffset++){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos, n_colPos + colOffset);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                
                if (numOpponent >= 3){
                    return -10;
                }
                if (numSelf >= 3){
                    return 10;
                }
            }
            
            numOpponent = 0;
            numSelf = 0;
            
            // Check left horizontal
            // Comproba los tres espacios a la izquierda 
            for (colOffset = -1; colOffset > -4 && (n_colPos + colOffset) > -1; colOffset--){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos, n_colPos + colOffset);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                
                if (numSelf >= 3){
                    return 10;
                }
                if (numOpponent >= 3){
                    return -10;
                }
                
            }
            
            numOpponent = 0;
            numSelf = 0;
            
            // Check for lower vertical
            // Comproba los tres espacios de debajo
            for (rowOffset = 1; rowOffset < 4 && (n_rowPos + rowOffset) < n_tablero.numFilas(); rowOffset++){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos + rowOffset, n_colPos);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                
                if (numSelf >= 3){
                    return 10;
                }
                if (numOpponent >= 3){
                    return -10;
                }
                
            }
            
            numOpponent = 0;
            numSelf = 0;
            // Check for higher vertical
            // Comproba los tres espacios arriba de la ficha
            for (rowOffset = -1; rowOffset > -4 && (n_rowPos + rowOffset) > -1; rowOffset--){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos + rowOffset, n_colPos);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                
                if (numSelf >= 3){
                    return 10;
                }
                if (numOpponent >= 3){
                    return -10;
                }
                                
            }
            
            numOpponent = 0;
            numSelf = 0;
            
            // Check upper left diagonal
            // Comproba la diagonal a la izquierda y arriba
            for (diagonalOffset = -1; (diagonalOffset > -4) && 
                    (n_rowPos + diagonalOffset > -1) && 
                    (n_colPos + diagonalOffset > -1); 
                    diagonalOffset--){
            
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos + diagonalOffset, n_colPos + diagonalOffset);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                
                if (numSelf >= 3){
                    return 10;
                } 
                if (numOpponent >= 3){
                    return -10;
                }
                               
            }
            
            numOpponent = 0;
            numSelf = 0;
            
            // Check lower right diagonal
            // Comproba la diagonal de debajo y de la derecha 
            for (diagonalOffset = 1; (diagonalOffset < 4) && 
                    (n_rowPos + diagonalOffset < n_tablero.numFilas()) && 
                    (n_colPos + diagonalOffset < n_tablero.numColumnas()); 
                    diagonalOffset++){
            
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos + diagonalOffset, n_colPos + diagonalOffset);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                
                if (numSelf >= 3){
                    return 10;
                } 
                if (numOpponent >= 3){
                    return -10;
                }
                               
            }
            numOpponent = 0;
            numSelf = 0;
            
            // Upper right diagonal
            // Comproba la diagonal arriba y de la derecha
            for (diagonalOffset = 1; (diagonalOffset < 4) && 
                    (n_rowPos - diagonalOffset > -1) &&
                    (n_colPos + diagonalOffset < n_tablero.numColumnas());
                    diagonalOffset++){
                
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos - diagonalOffset, n_colPos + diagonalOffset);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                
                if (numSelf >= 3){
                    return 10;
                }
                if (numOpponent >= 3){
                    return -10;
                }
                
            }
            
            numOpponent = 0;
            numSelf = 0;
            
            // Lower left diagonal
            // Comproba la diagonal de debajo y a la izquierda
            for (diagonalOffset = 1; (diagonalOffset < 4) &&
                    (n_rowPos + diagonalOffset < n_tablero.numFilas() &&
                    (n_colPos - diagonalOffset > -1));
                    diagonalOffset++){
                
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos + diagonalOffset, n_colPos - diagonalOffset);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                
                if (numSelf >= 3){
                    return 10;
                }                
                if (numOpponent >= 3){
                    return -10;
                }
            }
            
            // Check row above the piece
            // Comproba la fila arriba
            if (n_rowPos - 1 > -1){
                
                // Space above the piece
                // El espacio arriba 
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos - 1, n_colPos);
                if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                    valorNodoMin -= 1;
                } 
                else if (valorCasilla == n_jugador){
                    valorNodoMax += 1;
                }
                
                // Upper Left
                // El espacio arriba y izquierda
                if (n_colPos - 1 > -1){
                    valorCasilla = n_tablero.obtenerCasilla(n_rowPos - 1, n_colPos - 1);
                    if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                        valorNodoMin -= 1;
                    } 
                    else if (valorCasilla == n_jugador){
                        valorNodoMax += 1;
                    }
                    
                }
                
                // Upper right
                // El espacio arriba y a la derecha 
                if (n_colPos + 1 < n_tablero.numColumnas()){
                    valorCasilla = n_tablero.obtenerCasilla(n_rowPos - 1, n_colPos + 1);
                    if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                        valorNodoMin -= 1;
                    } 
                    else if (valorCasilla == n_jugador){
                        valorNodoMax += 1;
                    }
                }
            }// End row above check
           
            
            
            // Check row below
            // Comproba la fila de debajo
            if (n_rowPos + 1 < n_tablero.numFilas()){
                
                // Check space below
                // El espacio de debajo
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos + 1, n_colPos);
                if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                    valorNodoMin -= 1;
                } 
                else if (valorCasilla == n_jugador){
                    valorNodoMax += 1;
                }
                
                // Check the lower left
                // El espacio de debajo y izquierda
                if (n_colPos - 1 > -1){
                    valorCasilla = n_tablero.obtenerCasilla(n_rowPos + 1, n_colPos - 1);
                    if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                        valorNodoMin -= 1;
                    } 
                    else if (valorCasilla == n_jugador){
                        valorNodoMax += 1;
                    }
                }
                
                // Check the lower right 
                // El espacio de debajo y derecha
                if (n_colPos + 1 < n_tablero.numColumnas()){
                    valorCasilla = n_tablero.obtenerCasilla(n_rowPos + 1, n_colPos + 1);
                    if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                        valorNodoMin -= 1;
                    } 
                    else if (valorCasilla == n_jugador){
                        valorNodoMax += 1;
                    }                    
                }                
            }// End of checking row below
            
            
            
            // Check space to the left
            // Comproba el espacio a la izquierda
            if (n_colPos - 1 > -1){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos, n_colPos - 1);
                if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                    valorNodoMin -= 1;
                } 
                else if (valorCasilla == n_jugador){
                    valorNodoMax += 1;
                }               
            }
            
            // Check the space to the right
            // Comproba el espacio a la derecha 
            if (n_colPos + 1 < n_tablero.numColumnas()){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos, n_colPos + 1);
                if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                    valorNodoMin -= 1;
                } 
                else if (valorCasilla == n_jugador){
                    valorNodoMax += 1;
                }
            }
            
            // Determinar si un jugada maximo o minimo es más importante 
            if ((valorNodoMin * -1) > valorNodoMax){
                return valorNodoMin;
            }
            else{
                return valorNodoMax;
            }
        }
    }
    

    //Constructor
    public JugadorMaquina(int jugador)
    {
        super(jugador);
        
    }

    // Función que se ejecuta en el thread
    public void run()
    {       
        //Llama a la función Minimax que implementa el algoritmo para calcular la jugada
        minimax();            
        
        //No borrar esta lÃ­nea!!
        isDone(true);
    }


    /**
     * Se debe determinar la mejor jugada mediante Minimax. El tablero de juego se
     * encuentra en la variable m_tablero.
     * Al final de la funciÃ³n de la variable m_columna debe contener la tirada.
     * @return
     */
    public void minimax()
    {

        //El siguiente código genera una jugada aleatoria
        //SE DEBE SUSTITUIR ESTE CÓDIGO POR EL DEL ALGORITMO Minimax
        root = new Nodo(m_tablero, "MAX", m_jugador, NIVEL_DEFECTO);
        // Call this with the nivel por defecto and then decrement in each call. 
        minimax(root);
    }
    
    
    
    /*
    Minimax (N) 
    
    parameters: node N
    return: the value of said node
    
    if N is a terminal node, then return its value.
        ***
        A terminal node consists of reaching the desired depth 
        level for determining a move, a full table, or a win/loss in the game. 
        ***
    else
        generate all of the successors of N: N1, N2, ... , N7
        if N is a max node, then return the max value evaluated from 
            its children nodes.
        if N is a MIN node, then return the minimum value evaluated from 
            its children nodes. 
    
    */  
    public int minimax(Nodo n){
        // Crea un vector de nodos
        Nodo listaNodo[] = new Nodo[n.obtenerTablero().numColumnas()];
        // Y un vector para los valores
        int listaValor[] = new int[n.obtenerTablero().numColumnas()];
        // Haz un copia del tablero para la recursión 
        // Guardar una copia del tablero
        Tablero nueva_copia = new Tablero(n.obtenerTablero());
        

        // If the table is full, then we have reached an end state
        // Similarly if we have reached the desired depth level. 
        
        // Si el tablero está lleno, entonces habíamos alcanzado un estado terminal
        if (nueva_copia.tableroLleno() == true || 
                n.obtenerNivel() <= 0 || 
                n.obtenerTablero().cuatroEnRaya() == n.obtenerJugador() || 
                (n.obtenerTablero().cuatroEnRaya() != n.obtenerJugador() 
                    && n.obtenerTablero().cuatroEnRaya() != 0)){
            
            // Evaluate the final node and return its value
            // Valorar el nodo final y devolver su valor
            if (n.obtenerTablero().comprobarColumna(n.obtenerCol()) <= -1){
                return -1000;
            }
            return n.valorarNodo();
        }
        else{
            // Otherwise for every column of the table 
            // Generate all of the children
            for (int i = 0; i < nueva_copia.numColumnas(); i++){
                // Check each column for an empty row
                int fila = n.obtenerTablero().comprobarColumna(i);
                // If the column is full
                // Si la columna está llena
                if (fila <= -1){
                    return -1000;
                }
                // If not, create a new node
                // Si no, crea el nodo
                else{
                    // The next level has to be opposite the parent level
                    // I.E. If the parent is max, the children must be min and vice versa
                    Tablero hijo_tablero = new Tablero(nueva_copia);
                    if (n.obtenerNodoTipo().matches("MAX")){
                        // Create the node
                        
                        if (n.obtenerJugador() == 1){
                            listaNodo[i] = new Nodo(hijo_tablero, "MIN", 1, n.obtenerNivel() - 1);
                            listaNodo[i].fijarCol(i);
                            listaNodo[i].fijarFila(fila);
                            listaNodo[i].obtenerTablero().ponerFicha(listaNodo[i].obtenerCol(), listaNodo[i].obtenerJugador());
                            // Set the column position and row position
                            // Place the piece in the table
                        }
                        else if (n.obtenerJugador() == 2){
                            listaNodo[i] = new Nodo(hijo_tablero, "MIN", 2, n.obtenerNivel() - 1);
                            listaNodo[i].fijarCol(i);
                            listaNodo[i].fijarFila(fila);
                            listaNodo[i].obtenerTablero().ponerFicha(listaNodo[i].obtenerCol(), listaNodo[i].obtenerJugador());
                            // Set the column position and row position
                            // Place the piece in the table
                        }
                        
                        
                    }
                    else if (n.obtenerNodoTipo().matches("MIN")){
                        
                        if (n.obtenerJugador() == 1){
                            listaNodo[i] = new Nodo(hijo_tablero, "MAX", 2, n.obtenerNivel() - 1);
                            listaNodo[i].fijarCol(i);
                            listaNodo[i].fijarFila(fila);
                            listaNodo[i].obtenerTablero().ponerFicha(listaNodo[i].obtenerCol(), listaNodo[i].obtenerJugador());
                            // Set the column position and row position
                            // Place the piece in the table
                        }
                        else if (n.obtenerJugador() == 2){
                            listaNodo[i] = new Nodo(hijo_tablero, "MAX", 1, n.obtenerNivel() - 1);
                            // Set the column position and row position
                            // Fijar las cordinadas de la ficha. 
                            listaNodo[i].fijarCol(i);
                            listaNodo[i].fijarFila(fila);
                            // Pon la ficha en el tablero
                            // Place the piece in the table
                            listaNodo[i].obtenerTablero().ponerFicha(listaNodo[i].obtenerCol(), listaNodo[i].obtenerJugador());
                            
                            
                        }
                    }
                }// fin sino
            }//fin para
            
            if (n.obtenerNodoTipo().matches("MAX")){
                // Find the max value from the nodes in the list. 
                // Busca el valor maximo de los nodos en la lista
                // Initializar los variables
                int max = Integer.MIN_VALUE;
                int valorActual;
                
                for (int i = 0; i < listaNodo.length; i++){
                    // Make the recursive call to evaluate the nodes. 
                    // Hacer la recursion 
                    valorActual = minimax(listaNodo[i]);
                    
                    if (valorActual >= 100){
                        valorActual = max;
                        break;
                    }
                    // Si la ubicación de la ficha es afuera de el tablero, pasa
                    if (valorActual < -500){
                        continue;
                    }
                    if (valorActual < -150){
                        m_columna = i;
                        max = valorActual;
                        break;
                    }
                    // Si he ganado por una manera, fijar la posición
                    if (valorActual >= 10){
                        max = valorActual;
                        if (n.obtenerTablero().comprobarColumna(i) > -1){
                            m_columna = i;
                        }
                        break;
                    }
                    
                    // Set the maximum value higher if one is found
                    // Cambia el valor de max si encuentras un mayor
                    if (valorActual > max){
                        max = valorActual;
                        if (n.obtenerTablero().comprobarColumna(i) > -1){
                            m_columna = i;
                        }
                        
                    }
                }
                return max;
            }// Fin Si
            else{
                // Find the minimum value from the nodes in the list
                // Buscar para los valores minimos en los nodos en la lista
                // Initializar los variables
                int min = Integer.MAX_VALUE;
                int valorActual;
                
                for (int i = 0; i < listaNodo.length; i++){
                    // Make the recursive call to get the value
                    valorActual = minimax(listaNodo[i]);
                    
                    if (valorActual >= 10){
                        min = valorActual;
                        break;
                    }
                    // Si la ubicación de la ficha es afuera de el tablero, pasa
                    if (valorActual < -500){
                        continue;
                    }
                    /*
                    if (valorActual < -150){
                        m_columna = i;
                        min = valorActual;
                        break;
                    }*/
                    // Si el adversario ha ganado por una manera, necesita bloquear
                    if (valorActual <= -10){
                        min = valorActual;
                        if (n.obtenerTablero().comprobarColumna(i) > -1){
                            m_columna = i;
                        }
                        break;
                    }
                    
                    // Set the minimum value lower if one is found
                    // Cambiar el valor de min si encontras un menor
                    if (valorActual < min){
                        min = valorActual;
                        if (n.obtenerTablero().comprobarColumna(i) > -1){
                            m_columna = i;
                        }
                    }
                }
                return min;
            }// fin Else
        }
    }
}

