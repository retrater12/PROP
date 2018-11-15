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
    
     public controladorDominio(String datos) {
        gestionDatos2 = new capaDatos.GestionDatos(datos);
        CJTA = new capaDominio.CjtAsignaciones();
        rest = new capaDominio.Restricciones();
    }
     
     /* FUERA
    
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
        capaDatos.Aula aula = new capaDatos.Aula();
        int HorasC = A.getHoraClase(); 
        for (int diai = 0; diai < 5;++diai){
            for (int horai = 8; (horai+HorasC) <= 20;){
                FranjaHoraria FH = new FranjaHoraria(horai,horai+HorasC-1, Dia_semana(diai));
                aula = rest.Comprueba_Restricciones(A, FH, CJTA, gestionDatos2.getcjt_aules());
                if (aula != null){//Funcion que dice si podria añadir la asignatura A en la FH.  
                    FHaula fhaula = new FHaula(FH, aula);
                    ArrayFHaula.add(fhaula);
                    // Hay que añadir a que clase ira a parte de la FH. Supongo que creando un struck con FranjaHoraria y Aula ya estara.
                }
                //horai;
            //    horai+=HorasC;
            horai++;
            
            }
        }
        return ArrayFHaula;          
    }
  */
            
    private boolean Generar_r(ArrayList<capaDatos.Asignatura> A, int i){
        if (i >= A.size()){
            return true; // Todas las asignaturas han sido asignadas.
        }
        else {
            boolean b = false; boolean more_hours = true;
            capaDatos.Asignatura Aux = A.get(i++); // Cojo una asignatura. 
            FranjaHoraria fh_aux = new FranjaHoraria(8, 8+Aux.getHoraClase()-1, FranjaHoraria.Dia.LUNES);
        /* FUERA
            //ArrayList<FHaula> ArrayFHaula = new ArrayList<>();
            //ArrayFHaula = PosiblesFH(Aux);// Para cada Asignatura me devuelve un array con la lista de franjas horarias donde la podria colocar junto con el Aula a la que iria..
            //int tam = 0;
            //if (ArrayFHaula != null) tam = ArrayFHaula.size();
            //else System.out.println("Array vacio"); 
            */
            do {
                capaDatos.Aula aux_aula = null;
                while (aux_aula == null && more_hours){
                     aux_aula = rest.Comprueba_Restricciones(Aux, fh_aux, CJTA, gestionDatos2.getcjt_aules());
                     if (aux_aula == null) more_hours = fh_aux.seguentHora();
                }    
                if (aux_aula != null) {
                    Asignacion Asig = new Asignacion(aux_aula, Aux, fh_aux); // Primero crear asignacion 
                    CJTA.addelement(Asig);
                    b = Generar_r(A, i);// hacer recursividad    
                    if (!b){
                        CJTA.delelement();
                        more_hours = fh_aux.seguentHora();
                    }  
                }                     
            } while (!b && more_hours);
             return b;   
        }
    }
    
    public capaDominio.CjtAsignaciones Generar(){
        ArrayList<capaDatos.Asignatura> A = gestionDatos2.getcjt_asignatures();
        if(!Generar_r(A, 0)) System.out.println("no hay horario");
        return CJTA;
    }
    
    
}
