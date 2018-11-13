/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package generadorhorario;

import capaDominio.Asignacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import testDominio.AsignacionTest;




/**
 *
 * @author Sergi
 */
public class GeneradorHorario {
//Métodes d'aquesta clase han de ser static, ja que no hi ha cap instancia generada (de fet, tot amb una instancia ho podriem tractar aixi)
    
    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        
        capaDominio.controladorDominio control = new capaDominio.controladorDominio();
        
        mostrar_horario_por_pantalla(control.Generar());
        
        
        
    }*/
    public static void main(String[] args){
        AsignacionTest AT = new AsignacionTest();
        AT.testgetAula();
        AT.testsetAula();
        AT.testgetAsignatura();
        AT.testsetAsignatura();
        AT.testgetFranjaHoraria();
        AT.testsetFranjaHoraria();
    }
    
    
    private static void mostrar_horario_por_pantalla(capaDominio.CjtAsignaciones cjt){
        Map<Integer, ArrayList<capaDominio.Asignacion>> m = cjt.get_asignaciones_ordenadas();
        System.out.println("HORAS          LUNES          MARTES          MIERCOLES          JUEVES          VIERNES");
        for (int hora = 0; hora < 12; hora++) {
            System.out.print((hora+8) + ":00"); 
            boolean acabat = false; int i = 0;
            while (!acabat){
                acabat = true;
                for (int dia = 0; dia < 5; dia++){
                    if (m.containsKey(dia*12 + hora) && m.get(dia*12 + hora).size() > i){
                        System.out.print("          ");
                        Asignacion a = m.get(dia*12 + hora).get(i);
                        aux_mostrar_horario_por_pantalla(a);
                        acabat = false;
                    }
                }
                System.out.println("");
                i++;
            }
        }
    }
    
    
    private static void aux_mostrar_horario_por_pantalla(Asignacion a){
        System.out.print(a.getAsignatura().getMat().getSiglas() + " " + a.getAsignatura().getGrupo() + " " + a.getAsignatura().getTipusClase() + " [" + a.getAula().getCodigo() + "]");
    }
    
}
