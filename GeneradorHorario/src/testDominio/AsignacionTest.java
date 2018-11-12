/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDominio;

import capaDatos.Asignatura;
import capaDominio.Asignacion;
import capaDominio.FranjaHoraria;

/**
 *
 * @author casa1
 */
public class AsignacionTest {
    
    Asignacion Asig;
    /*public class Aula{
        
        private String codigo = "A5102";
        private int capacidad = 40;
        private capaDatos.Asignatura.TipusClase tipusClase = capaDatos.Asignatura.TipusClase.T;  
    }*/
    
    
    public AsignacionTest(){
        capaDatos.Aula aula = new capaDatos.Aula("A5102", 40, capaDatos.Asignatura.TipusClase.T);
        capaDominio.FranjaHoraria franjahoraria = new capaDominio.FranjaHoraria(8, 10, FranjaHoraria.Dia.LUNES);
        capaDatos.Materia materia = new capaDatos.Materia("Fisica", "F", 1);
        capaDatos.Asignatura asignatura = new capaDatos.Asignatura(materia, 20, Asignatura.TipusClase.T, 40, 2);
        
        Asig = new capaDominio.Asignacion(aula, asignatura , franjahoraria);
       /* if (Asig == new capaDominio.Asignacion(aula, asignatura , franjahoraria)){
            System.out.println("Correcto");
        }
        else System.out.println("Error");*/
    }
    
    public void testgetAula(){
        if (Asig.getAula() == new capaDatos.Aula("A5102", 40, capaDatos.Asignatura.TipusClase.T)) {
            System.out.println("Correcto");
        }
        else System.out.println("Error");
    }
    
    public void testsetAula(){
        Asig.setAula(new capaDatos.Aula("A5202", 60, capaDatos.Asignatura.TipusClase.T));
       if (Asig.getAula() == new capaDatos.Aula("A5202", 60, capaDatos.Asignatura.TipusClase.T)){
           System.out.println("Correcto");
       }    
       else System.out.println("Error");
    }
    public void testgetAsignatura(){
                capaDatos.Materia materia = new capaDatos.Materia("Fisica", "F", 1);
        if (Asig.getAsignatura() == new capaDatos.Asignatura(materia, 20, Asignatura.TipusClase.T, 40, 2)){
            System.out.println("Correcto");     
        }
        else{
            System.out.println("Error");
        }
    }
    
    public void testsetAsignatura(){
        capaDatos.Materia materia = new capaDatos.Materia("PARALELISME", "PAR", 2);
        Asig.setAsignatura(new capaDatos.Asignatura(materia, 30, Asignatura.TipusClase.T, 40, 3));
        if (Asig.getAsignatura() == new capaDatos.Asignatura(materia, 30, Asignatura.TipusClase.T, 40, 3)){
            System.out.println("Correcto");
        }
        else System.out.println("Error");
    }
    
    public void testgetFranjaHoraria(){
        if (Asig.getFranjaHoraria() == new capaDominio.FranjaHoraria(8, 10, FranjaHoraria.Dia.LUNES)){
            System.out.println("Correcto");
        }
        else System.out.println("Error");
    }
    
    public void testsetFranjaHoraria(){
        Asig.setFranjaHoraria(new capaDominio.FranjaHoraria(10, 12, FranjaHoraria.Dia.LUNES));
        if (Asig.getFranjaHoraria() == new capaDominio.FranjaHoraria(10, 12, FranjaHoraria.Dia.LUNES)){
            System.out.println("Correcto");
        }
        else System.out.println("Error");
    }
    
   /* public static void main(String[] args){
        AsignacionTest AT = new AsignacionTest();
        AT.testgetAsignatura();
        AT.testsetAsignatura();
        AT.testgetAula();
        AT.testsetAula();
        AT.testgetFranjaHoraria();
        AT.testsetFranjaHoraria();
    }*/
}


