/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDominio.Asignacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Victor
 */
public class CjtAsignaciones {

    private ArrayList<Asignacion> cjt_asignaciones; 
    
    
    public CjtAsignaciones () {
        cjt_asignaciones = new ArrayList<>();
    }
    
    
    
    
    
    public void push_asignacion(Asignacion a){
        cjt_asignaciones.add(a);
    }
    //pre: asignacio es una asignacio valida
    //post: cjt_asignaciones se li afegeix l'element a
    
    
    public Map<Integer, ArrayList<Asignacion>> get_asignaciones_ordenadas (){
        Map<Integer, ArrayList<Asignacion>> m = new HashMap<Integer, ArrayList<Asignacion>>();  
        for (int i = 0; i < cjt_asignaciones.size(); i++){
            int val = cjt_asignaciones.get(i).get_franjahoraria().unificar_values();
            if (!m.containsKey(val))
               m.put(val, new ArrayList<>());
            m.get(val).add(cjt_asignaciones.get(i));      
        }           
        return (HashMap<Integer, ArrayList<Asignacion>>) m;
    }    
    //pre: el conjunt assignacions ha estat montat
    //post: es retorna un map ordenat segons el principi de les clases
}
