/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;



/**
 *
 * @author Sergi
 */
public class Asignatura {

    public int gethoraClase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public enum TipusClase {
        T, L, P
    }
    
    private Materia mat;
    private int grupo;
    private TipusClase tipusClase;
    private int capacidad;
    private int horaClase; 
    
    public Asignatura(Materia mat, int grupo, TipusClase tipusClase, int capacidad, int horaClase){
        this.mat = mat;
        this.grupo = grupo;
        this.tipusClase = tipusClase;
        this.capacidad = capacidad;
        this.horaClase = horaClase;
    }
    
    
}
