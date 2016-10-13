/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conecta4;

/**
 *
 * @author sistemas inteligentes
 */
public class JugadorMaquina extends Jugador{

    //Profundidad hasta la que se va a desarrollar el Ã¡rbol de juego
    public final static int NIVEL_DEFECTO = 2;
    public Nodo root;
    
    class Nodo{
        private Tablero n_tablero;
        private String n_tipo;
        private int n_jugador;
        private int n_valor;
        private int n_colPos;
        private int n_rowPos;
        private int n_nivel;

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

        //Put the evaluate node function inside the node so that it can use 
        /* its own private table to do the evaluation. */        
        public int valorarNodo(){
            
            //int valorNodo= 0;
            
            int valorNodoMin = 0;
            int valorNodoMax = 0;
            
            int numOpponent = 0;
            int numSelf = 0;
            int rowOffset;
            int colOffset;
            int diagonalOffset;
            int valorCasilla;
            
            // Check right horizontal
            for (colOffset = 1; colOffset < 4 && n_colPos + colOffset < n_tablero.numColumnas(); colOffset++){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos, n_colPos + colOffset);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                else{
                    //do nothing;
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
            for (colOffset = -1; colOffset > -4 && (n_colPos + colOffset) > -1; colOffset--){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos, n_colPos + colOffset);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                else{
                    //do nothing;
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
            
            // Check for lower vertical
            for (rowOffset = 1; rowOffset < 4 && (n_rowPos + rowOffset) < n_tablero.numFilas(); rowOffset++){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos + rowOffset, n_colPos);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                else{
                    //do nothing;
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
            // Check for higher vertical
            for (rowOffset = -1; rowOffset > -4 && (n_rowPos + rowOffset) > -1; rowOffset--){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos + rowOffset, n_colPos);
                if (valorCasilla != n_jugador && valorCasilla != 0){
                    numOpponent += 1;
                }
                else if (valorCasilla == n_jugador){
                    numSelf += 1;
                }
                else{
                    //do nothing;
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
            
            // Check upper left diagonal
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
                else{
                    //do nothing;
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
            
            // Check lower right diagonal
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
                else{
                    //do nothing;
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
            
            // Upper right diagonal
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
                else{
                    //do nothing;
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
            
            // Lower left diagonal
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
                else{
                    //do nothing;
                }
                
                if (numOpponent >= 3){
                    return -10;
                }
                if (numSelf >= 3){
                    return 10;
                }                
            }
            
            // Check row above the piece
            if (n_rowPos - 1 > -1){
                
                // Space above the piece
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos - 1, n_colPos);
                if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                    valorNodoMin -= 1;
                } 
                else if (valorCasilla == n_jugador){
                    valorNodoMax += 1;
                }
                
                // Upper Left
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
            if (n_rowPos + 1 < n_tablero.numFilas()){
                
                // Check space below
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos + 1, n_colPos);
                if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                    valorNodoMin -= 1;
                } 
                else if (valorCasilla == n_jugador){
                    valorNodoMax += 1;
                }
                
                // Check the lower left
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
            if (n_colPos + 1 < n_tablero.numColumnas()){
                valorCasilla = n_tablero.obtenerCasilla(n_rowPos, n_colPos + 1);
                if ((valorCasilla != n_jugador) && (valorCasilla != 0)){
                    valorNodoMin -= 1;
                } 
                else if (valorCasilla == n_jugador){
                    valorNodoMax += 1;
                }
            }
            
            if ((valorNodoMin * -1) > valorNodoMax){
                return valorNodoMin;
            }
            else{
                return valorNodoMax;
            }
            
            
            //return valorNodo;
        }
    }
    

    //Constructor
    public JugadorMaquina(int jugador)
    {
        super(jugador);
        
    }

    // FunciÃ³n que se ejecuta en el thread
    public void run()
    {
        root = new Nodo(m_tablero, "MAX", m_jugador, NIVEL_DEFECTO);
        //Llama a la funciÃ³n Minimax que implementa el algoritmo para calcular la jugada
        //minimax();
        
        minimax(root);
        // Call this with the nivel por defecto and then decrement in each call. 
        
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

        //El siguiente cÃ³digo genera una jugada aleatoria
        //SE DEBE SUSTITUIR ESTE CÃ“DIGO POR EL DEL ALGORITMO Minimax
        boolean buenaTirada = false;
        int columna = -1;

        columna = (int) (Math.random()*m_tablero.numColumnas());

        while(!buenaTirada)
        {
            if(m_tablero.comprobarColumna(columna)!=-1)
            {
                buenaTirada = true;
                m_columna = columna;
            }
            else
                columna = (int) (Math.random()*m_tablero.numColumnas());
    
        }
     }
    
    public int minimax(Nodo n){
        // Create a vector of nodes for all of the possible moves
        Nodo listaNodo[] = new Nodo[n.obtenerTablero().numColumnas()];
        // Create a vector to contain all of the returned values
        int listaValor[] = new int[n.obtenerTablero().numColumnas()];
        // Create a copy of the table to be used in the recursion
        // Make sure to grab a copy of the table from the node passed in.
        Tablero nueva_copia = new Tablero(n.obtenerTablero());
        
        
        // If the table is full, then we have reached an end state
        // Similarly if we have reached the desired depth level. 
        if (nueva_copia.tableroLleno() == true || n.obtenerNivel() <= 0){
            // evaluate the final node and return its value
            return n.valorarNodo();
        }
        else{
            // Otherwise for every column of the table 
            // Generate all of the children
            for (int i = 0; i < nueva_copia.numColumnas(); i++){
                // Check each column for an empty row
                int fila = n.obtenerTablero().comprobarColumna(i);
                // If the column is full
                if (fila == -1){
                    return 0;
                    //Tablero hijo_tablero = new Tablero(nueva_copia);
                    // don't know yet
                }
                // If not, create a new node
                else{
                    // The next level has to be opposite the parent level
                    // I.E. If the parent is max, the children must be min and vice versa
                    Tablero hijo_tablero = new Tablero(nueva_copia);
                    if (n.obtenerNodoTipo().matches("MAX")){
                        // Create the node
                        
                        listaNodo[i] = new Nodo(hijo_tablero, "MIN", n.obtenerJugador(), n.obtenerNivel() - 1);
                        listaNodo[i].fijarCol(i);
                        listaNodo[i].fijarFila(fila);
                        listaNodo[i].obtenerTablero().ponerFicha(listaNodo[i].obtenerCol(), listaNodo[i].obtenerJugador());
                        // Set the column position and row position
                        // Place the piece in the table
                        
                    }
                    else if (n.obtenerNodoTipo().matches("MIN")){
                        listaNodo[i] = new Nodo(hijo_tablero, "MAX", n.obtenerJugador(), n.obtenerNivel() - 1);
                        listaNodo[i].fijarCol(i);
                        listaNodo[i].fijarFila(fila);
                        listaNodo[i].obtenerTablero().ponerFicha(listaNodo[i].obtenerCol(), listaNodo[i].obtenerJugador());
                    }
                }// fin sino
            }//fin para
            
            if (n.obtenerNodoTipo().matches("MAX")){
                // Find the max value from the nodes in the list. 
                // Busca el valor maximo de los nodos en la lista
                // Initializar los variables
                int max = 0;
                int max_pos = 0;
                int valorActual = 0;
                
                for (int i = 0; i < listaNodo.length; i++){
                    // Make the recursive call to evaluate the nodes. 
                    // Hacer la recursion 
                    valorActual = minimax(listaNodo[i]);
                    
                    // Set the maximum value higher if one is found
                    // Cambia el valor de max si encuentras un mayor
                    if (valorActual > max){
                        max = valorActual;
                        max_pos = i;
                        m_columna = max_pos;
                        
                    }
                }
                
                return max;
            }// Fin Si
            else{
                // Find the minimum value from the nodes in the list
                // Initializar los variables
                int min = 0;
                int min_pos = 0;
                int valorActual = 0;
                
                for (int i = 0; i < listaNodo.length; i++){
                    // Make the recursive call to get the value
                    valorActual = minimax(listaNodo[i]);
                    
                    // Set the minimum value lower if one is found
                    // Cambiar el valor de min si encontras un menor
                    if (valorActual < min){
                        min = valorActual;
                        min_pos = i;
                        m_columna = min_pos;
                    }
                }
                
                return min;
            }// fin Else
        }
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
}

