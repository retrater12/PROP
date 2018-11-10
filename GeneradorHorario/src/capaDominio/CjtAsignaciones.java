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

    public CjtAsignaciones(){
        ArrayList<Asignacion> asig = new ArrayList<>();
        cjt_asignaciones = asig;
    }
    public CjtAsignaciones(ArrayList<Asignacion> asig){
        cjt_asignaciones = asig;
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
    
     public Map<Integer, ArrayList<Asignacion>> get_asignaciones_ordenadas (){
        Map<Integer, ArrayList<Asignacion>> m = new HashMap<Integer, ArrayList<Asignacion>>();  
        for (int i = 0; i < cjt_asignaciones.size(); i++){
            int val = cjt_asignaciones.get(i).getFranjaHoraria().unificar_values();
            for (int j = 0; j < cjt_asignaciones.get(i).getAsignatura().gethoraClase(); j++){
                if (!m.containsKey(val+j))
                    m.put(val+j, new ArrayList<>());
                m.get(val+j).add(cjt_asignaciones.get(i));      
            }
        }           
        return (HashMap<Integer, ArrayList<Asignacion>>) m;
    }    
    //pre: el conjunt assignacions ha estat montat
    //post: es retorna un map ordenat amb les clases segons el dia i l'hora
}
