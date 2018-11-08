/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDatos.Asignatura;
//import capaDominio.FranjaHoraria.Dia;
import java.util.ArrayList;


/**
 *
 * @author Victor
 */
public class controladorDominio {

    private capaDatos.GestionDatos gestionDatos2;
    private Restricciones rest;
    private capaDominio.CjtAsignaciones CJTA;
 //   private CjtAsignaciones cjtAssigs;
    
    public controladorDominio() {
        System.out.println("CARGADO CONTROLADOR DOMINIO");
        gestionDatos2 = new capaDatos.GestionDatos();
        CJTA = new capaDominio.CjtAsignaciones();
    }
    
    private boolean cumpleRestricciones(capaDatos.Asignatura Aux, capaDominio.Asignacion.FranjaHoraria FH){
        
        return true;   
    }
    private FranjaHoraria.Dia Dia_semana(int dia){
        
        if (dia == 0) return FranjaHoraria.Dia.LUNES;
        else if (dia == 1) return FranjaHoraria.Dia.MARTES;
        else if (dia == 2) return FranjaHoraria.Dia.MIERCOLES;
        else if (dia == 3) return FranjaHoraria.Dia.JUEVES;
        else return FranjaHoraria.Dia.VIERNES;
    }
    private ArrayList<FranjaHoraria>  PosiblesFH(capaDatos.Asignatura A){
        ArrayList<FranjaHoraria> ArrayFH = new ArrayList<>();
        int HorasC = A.gethoraClase(); 
        for (int diai = 0; diai < 5;++diai){
            for (int horai = 8; (horai+HorasC) <= 20;){
                FranjaHoraria FH = new FranjaHoraria(horai,horai+HorasC, Dia_semana(diai));
                capaDatos.Aula aula = new capaDatos.Aula();
                if (Comprueba_Restricciones(A, FH, CJTA)){//Funcion que dice si podria añadir la asignatura A en la FH.  
                    ArrayFH.add(FH);
                    // Hay que añadir a que clase ira a parte de la FH. Supongo que creando un struck con FranjaHoraria y Aula ya estara.
                }
                if (HorasC%2 == 0) horai += 2;// Si la hora dura dos horas que busque FH pares
                else ++horai;
            }
        }        
        return ArrayFH;          
    }
  
            
    private void Generar_r(ArrayList<capaDatos.Asignatura> A, int i, capaDominio.Asignacion.FranjaHoraria FH){
        // i = numero de asignatura que estoy visitando en el array A.
        if (i >= A.size()){
            
        }
        else{
            capaDatos.Asignatura Aux = A.get(i++); // Cojo una asignatura. 
            ArrayList<FranjaHoraria> ArrayFH = new ArrayList<>();
            ArrayFH = PosiblesFH(Aux);// Para cada Asignatura me devuelve un array con la lista de franjas horarias donde la podria colocar.
            for (int j = 0; j < ArrayFH.size(); ++j){
                Asignacion Asig = new Asignacion(); 
                // Primero crear asignacion 
                // hacer recursividad
                // Eliminar Asignacion (ya que significara que por esta rama no ha acabado )
                
                
            }
            
                
        }
        
        
    }
    private capaDominio.CjtAsignaciones Generar(){
        ArrayList<capaDatos.Asignatura> A = gestionDatos2.getAsignaturas();
        
        Generar_r(A, 0);
        
        
        
        return null;
    }
    
    
}
