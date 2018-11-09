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
    public class FHaula{
        private FranjaHoraria FH;
        private capaDatos.Aula aula;
        
        public FHaula(FranjaHoraria FH, capaDatos.Aula aula){
            this.FH = FH;
            this.aula = aula;
        }
        public FranjaHoraria getFH(){
            return FH;
        }
        public capaDatos.Aula getAula(){
            return aula;
        }
    };
    
    public controladorDominio() {
        System.out.println("CARGADO CONTROLADOR DOMINIO");
        gestionDatos2 = new capaDatos.GestionDatos();
        CJTA = new capaDominio.CjtAsignaciones();
    }
    
    private FranjaHoraria.Dia Dia_semana(int dia){
        
        if (dia == 0) return FranjaHoraria.Dia.LUNES;
        else if (dia == 1) return FranjaHoraria.Dia.MARTES;
        else if (dia == 2) return FranjaHoraria.Dia.MIERCOLES;
        else if (dia == 3) return FranjaHoraria.Dia.JUEVES;
        else return FranjaHoraria.Dia.VIERNES;
    }
    private ArrayList<FHaula>  PosiblesFH(capaDatos.Asignatura A){
        //Devuelve el conjunto de Franja horarias junto con las clases a las que podriamos meter la Asignatura A.
        ArrayList<FHaula> ArrayFHaula = new ArrayList<>();
        int HorasC = A.gethoraClase(); 
        for (int diai = 0; diai < 5;++diai){
            for (int horai = 8; (horai+HorasC) <= 20;){
                FranjaHoraria FH = new FranjaHoraria(horai,horai+HorasC, Dia_semana(diai));
                capaDatos.Aula aula = new capaDatos.Aula();
                Restricciones compRes = new Restricciones();
                aula = compRes.Comprueba_Restricciones(A, FH, CJTA, gestionDatos2.getcjt_aules());
                if (aula != null){//Funcion que dice si podria añadir la asignatura A en la FH.  
                    FHaula fhaula = new FHaula(FH, aula);
                    ArrayFHaula.add(fhaula);
                    // Hay que añadir a que clase ira a parte de la FH. Supongo que creando un struck con FranjaHoraria y Aula ya estara.
                }
                if (HorasC%2 == 0) horai += 2;// Si la hora dura dos horas que busque FH pares
                else ++horai;
            }
        }        
        return ArrayFHaula;          
    }
  
            
    private boolean Generar_r(ArrayList<capaDatos.Asignatura> A, int i){
        // i = numero de asignatura que estoy visitando en el array A.
        if (i >= A.size()){
            return true; // Todas las asignaturas han sido asignadas.
        }
        else{
            boolean b = false;
            capaDatos.Asignatura Aux = A.get(i); // Cojo una asignatura. 
            ArrayList<FHaula> ArrayFHaula = new ArrayList<>();
            ArrayFHaula = PosiblesFH(Aux);// Para cada Asignatura me devuelve un array con la lista de franjas horarias donde la podria colocar junto con el Aula a la que iria..
            for (int j = 0; j < ArrayFHaula.size() && !b; ++j){
                Asignacion Asig = new Asignacion(ArrayFHaula.get(j).getAula(), Aux, ArrayFHaula.get(j).getFH()); // Primero crear asignacion 
                CJTA.addelement(Asig);
                b = Generar_r(A, i++);// hacer recursividad
                if (!b) CJTA.delelement();    // Eliminar Asignacion (ya que significara que por esta rama no ha acabado )
                // Hay que controlar que no cree todas las ramas, por ejemplo haciendo que la funcion devuelva un bool, true cuando llegue al final y false mientras no.  
            }
             return b;   
        }
    }
    private capaDominio.CjtAsignaciones Generar(){
        ArrayList<capaDatos.Asignatura> A = gestionDatos2.getcjt_asignatures();
        Generar_r(A, 0);
        return CJTA;
    }
    
    
}
