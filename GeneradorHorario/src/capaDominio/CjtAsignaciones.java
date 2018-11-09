/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDominio.Asignacion;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class CjtAsignaciones {

    private ArrayList<Asignacion> cjt_asignaciones; 

    public CjtAsignaciones(){
        
    }
    public ArrayList<Asignacion> getCjtA(){
        return cjt_asignaciones;
    }
    public void addelement(Asignacion A){
        cjt_asignaciones.add(A);
    }
    public void delelement(){
        ArrayList<Asignacion> Aux = new ArrayList<>();
        for (int i = 0; i < cjt_asignaciones.size()-1; ++i){
            Aux.add(cjt_asignaciones.get(i));
        }
        cjt_asignaciones = Aux;
    }
}
