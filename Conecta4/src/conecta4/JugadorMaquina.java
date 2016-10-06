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
    private Nodo root;
    
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
            
            int valorNodo = 0; 
            int rowOffset = 1;
            int colOffset = 1;
            
            while (rowOffset < 4 && n_colPos + colOffset < n_tablero.numColumnas()){
                int valorCasilla = n_tablero.obtenerCasilla(n_rowPos, n_colPos + colOffset);
                
                if (valorCasilla == n_jugador){
                    valorNodo += 1;
                }
                else if (valorCasilla == 0){
                    valorNodo += 0;
                }
                else{
                    valorNodo -= 1;
                }
                
            }
            
            return 0;
        }

    }
    

    //Constructor
    public JugadorMaquina(int jugador)
    {
        super(jugador);
        root = new Nodo(m_tablero, "MAX", jugador, NIVEL_DEFECTO);
    }

    // FunciÃ³n que se ejecuta en el thread
    public void run()
    {
        //Llama a la funciÃ³n Minimax que implementa el algoritmo para calcular la jugada
        minimax();
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
    
    public int minimax_2(Nodo n){
        // Create a vector of nodes for all of the possible moves
        Nodo listaNodo[] = new Nodo[m_tablero.numColumnas()];
        // Create a vector to contain all of the returned values
        int listaValor[] = new int[m_tablero.numColumnas()];
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
                    // don't know yet
                }
                // If not, create a new node
                else{
                    // The next level has to be opposite the parent level
                    // I.E. If the parent is max, the children must be min and vice versa
                    if (n.obtenerNodoTipo().matches("MAX")){
                        // Create the node
                        listaNodo[i] = new Nodo(nueva_copia, "MIN", n.obtenerJugador(), n.obtenerNivel() - 1);
                        listaNodo[i].fijarCol(i);
                        listaNodo[i].fijarFila(fila);
                        listaNodo[i].obtenerTablero().ponerFicha(listaNodo[i].obtenerCol(), listaNodo[i].obtenerJugador());
                        // Set the column position and row position
                        // Place the piece in the table
                        
                    }
                    else if (n.obtenerNodoTipo().matches("MIN")){
                        listaNodo[i] = new Nodo(nueva_copia, "MAX", n.obtenerJugador(), n.obtenerNivel() - 1);
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
                    valorActual = minimax_2(listaNodo[i]);
                    
                    // Set the maximum value higher if one is found
                    // Cambia el valor de max si encuentras un mayor
                    if (valorActual > max){
                        max = valorActual;
                        max_pos = i;
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
                    valorActual = minimax_2(listaNodo[i]);
                    
                    // Set the minimum value lower if one is found
                    // Cambiar el valor de min si encontras un menor
                    if (valorActual < min){
                        min = valorActual;
                        min_pos = i;
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

