/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conecta4;

/**
 *
 * @author Matt Q
 */
public class Nodo {
    
    private int valor;
    private int col_pos;
    private int row_pos;
    private int num_jugador;
    private String node_type;
    
    public Nodo(int col, int row){
        col_pos = col;
        row_pos = row;
    }
    
    public void setJugador(int jugador){
        num_jugador = jugador;
    }
    
    public int obtenerJugador(){
        return num_jugador;
    }
    
    public int obtenerValor(){
        return valor;
    }
    
    public int obtenerColPos(){
        return col_pos;
    }
    
    public int obtenerRowPos(){
        return row_pos;
    }
    
    public String obtenerNodeType(){
        return node_type;
    }
}











