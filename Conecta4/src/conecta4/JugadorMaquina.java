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
    
    public void minimax_2(Nodo n){
        // Create a vector of nodes for all of the possible moves
        Nodo listaNodo[] = new Nodo[m_tablero.numColumnas()];
        // Create a vector to contain all of the returned values
        int listaValor[] = new int[m_tablero.numColumnas()];
        // Create a copy of the table to be used in the recursion
        // Make sure to grab a copy of the table from the node passed in.
        Tablero nueva_copia = new Tablero(n.obtenerTablero());
        
        // If the table is full, then we have reached an end state
        // Similarly if we have reached the desired depth level. 
        if (nueva_copia.tableroLleno() == true || n.obtenerNivel() == 1){
            // evaluate the final node and return its value
        }
        else{
            // Otherwise for every column of the table 
            for (int i = 0; i < nueva_copia.numColumnas(); i++){
                // Check each column for an empty row
                int fila = n.obtenerTablero().comprobarColumna(i);
                // If the column is full
                if (fila == -1){
                    // don't know yet
                }
                // If not, create a new node
                else{
                    if (n.obtenerNodoTipo() == "MAX"){
                       listaNodo[i] = new Nodo(nueva_copia, "MIN", n.obtenerJugador(), n.obtenerNivel() - 1);
                    }
                    else if (n.obtenerNodoTipo() == "MIN"){
                        listaNodo[i] = new Nodo(nueva_copia, "MAX", n.obtenerJugador(), n.obtenerNivel() - 1);
                    }
                }
            } 
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

