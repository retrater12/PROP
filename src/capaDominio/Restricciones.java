/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class Restricciones {
    private ArrayList<ArrayList<capaDatos.Asignatura>> A;
  //  private ArrayList<Integer> conts;

    
    public Restricciones(){
        
        
    }
    
    public void montarConjuntos(ArrayList <capaDatos.Asignatura> asigs){
        
    }
    
    public boolean comprueba_Restricciones(capaDatos.Asignatura a, FranjaHoraria fh, CjtAsignaciones ca, capaDatos.Aula A){
        // En CjtAsignaciones estan las asignaciones hechas hasta el momento para poder comprobar restricciones.
        // En *aula hay que devolver la aula en la que se a puesto la asignatura en la franja horaria.
        // Y hay que retornar un boolean que diga si se a podido colocar o no.
        return true;
    }
}
