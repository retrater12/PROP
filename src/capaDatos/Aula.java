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
public class Aula {
    
    private String codigo;
    private int capacidad;
    private Asignatura.TipusClase tipusClase;

    public Aula(String codigo, int capacidad, Asignatura.TipusClase tipusClase) {
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.tipusClase = tipusClase;
    }

    public Aula() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
