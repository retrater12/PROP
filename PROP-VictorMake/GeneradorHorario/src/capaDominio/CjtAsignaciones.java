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
/**
 * Arraylist on es guarda totes les asignacions realitzades. Creades per la clase controladorDominio, per tal de construir l'horari.
 */
    private ArrayList<Asignacion> cjt_asignaciones; 
/**
 * Constructora sense parametres de la clase CjtAsignacion.
 */
    public CjtAsignaciones(){
        ArrayList<Asignacion> asig = new ArrayList<>();
        cjt_asignaciones = asig;
    }
    /**
     * Constructora amb parametres de la clase CjtAsignacion. Creara una clase CjtAsignacion a la cual se li asignara l'arraylist asig.
     * @param asig Arraylist que se li asignara a la clase.
     */
    public CjtAsignaciones(ArrayList<Asignacion> asig){
        cjt_asignaciones = asig;
    }
    
    /**
     * Obte l'arraylist d'asignacions de la clase CjtAsignacions.
     * @return Retorna l'arraylist d'asignacions.
     */
    public ArrayList<Asignacion> getCjtA(){
        return cjt_asignaciones;
    }
    /**
     * Afegeix un element, en aquest cas una asignacio, a l'arraylist de la clase CjtAsignacions.
     * @param A Asignacion que sera afegida al arraylist d'asignacions.
     */
    public void addelement(Asignacion A){
        
            cjt_asignaciones.add(A);

    }
    /**
     * Elimina l'ultim element de l'arraylist d'asignacions de la clase CjtAsignacions.
     */
    public void delelement(){
        cjt_asignaciones.remove(cjt_asignaciones.size()-1);
    }
    /**
     * Crea un map en que s'ordenen totes les asignacions que pertanyen a l'arraylist de la clase
     * CjtAsignaciones segons el dia i l'hora de la franja horaria de cada una.
     * @return Retorna un Map amb les asignacions ordenades segons hora i dia.
     */
     public Map<Integer, ArrayList<Asignacion>> get_asignaciones_ordenadas (){
        Map<Integer, ArrayList<Asignacion>> m = new HashMap<Integer, ArrayList<Asignacion>>();  
        for (int i = 0; i < cjt_asignaciones.size(); i++){
            int val = cjt_asignaciones.get(i).getFranjaHoraria().unificar_values();
            for (int j = 0; j < cjt_asignaciones.get(i).getAsignatura().getHoraClase(); j++){
                if (!m.containsKey(val+j))
                    m.put(val+j, new ArrayList<>());
                m.get(val+j).add(cjt_asignaciones.get(i));      
            }
        }           
        return (HashMap<Integer, ArrayList<Asignacion>>) m;
    }     
}
