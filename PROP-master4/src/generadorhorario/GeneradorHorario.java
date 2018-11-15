/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package generadorhorario;

import capaDominio.FranjaHoraria;
import java.util.ArrayList;
import java.util.Iterator;
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
    public static void main(String[] args) {

        capaDominio.controladorDominio control = new capaDominio.controladorDominio(args[0]);
        
        mostrar_horario_por_pantalla(control.Generar());
        
        
        
    }
    /*public static void main(String[] args){
        AsignacionTest AT = new AsignacionTest();
        AT.testgetAula();
        AT.testsetAula();
        AT.testgetAsignatura();
        AT.testsetAsignatura();
        AT.testgetFranjaHoraria();
        AT.testsetFranjaHoraria();
    }*/
    
    
    private static void mostrar_horario_por_pantalla(capaDominio.CjtAsignaciones cjt){
        
        
        Map<capaDominio.FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> m = cjt.getCjtA();
        
        Iterator e2 = m.keySet().iterator();
        while (e2.hasNext()){
        capaDominio.FranjaHoraria key2 = (capaDominio.FranjaHoraria) e2.next();
        System.out.println(key2.getHoraIni() + "  " + key2.getDia());
        }
        
        System.out.println("HORAS          LUNES          MARTES          MIERCOLES          JUEVES          VIERNES");
        for (int hora = 0; hora < 12; hora++) {
            System.out.print((hora+8) + ":00"); 
            boolean acabat = false; int i = 0;
            while (!acabat) {
                acabat = true;
                for (int dia = 0; dia < 5; dia++){
                    capaDominio.FranjaHoraria fh_aux = new capaDominio.FranjaHoraria(hora, Dia_semana(dia));
                    if (m.containsKey(fh_aux) && m.get(fh_aux).size() > i){
                        System.out.print("          ");
                        Iterator e = m.get(fh_aux).keySet().iterator();
                        for (int j = 0; j < i && e.hasNext(); j++) e.next();
                        if (e.hasNext()){       
                            capaDatos.Aula key = (capaDatos.Aula) e.next();
                            aux_mostrar_horario_por_pantalla(key, m.get(fh_aux).get(key));
                            acabat = false;
                        }
                    }
                }
                System.out.println("");
                i++;
            }
        }
    }
    
    
    private static void aux_mostrar_horario_por_pantalla(capaDatos.Aula a, capaDatos.Asignatura as){
        System.out.print(as.getMat().getSiglas() + " " + as.getGrupo() + " " + as.getTipusClase() + " [" + a.getCodigo() + "]");
    }
    
    private static FranjaHoraria.Dia Dia_semana(int dia){
        
        if (dia == 0) return FranjaHoraria.Dia.LUNES;
        else if (dia == 1) return FranjaHoraria.Dia.MARTES;
        else if (dia == 2) return FranjaHoraria.Dia.MIERCOLES;
        else if (dia == 3) return FranjaHoraria.Dia.JUEVES;
        else return FranjaHoraria.Dia.VIERNES;
    }
    
}
