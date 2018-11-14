/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDatos.Asignatura;
import java.io.IOException;
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
    
    public controladorDominio() throws IOException {
        System.out.println("CARGADO CONTROLADOR DOMINIO");
        gestionDatos2 = new capaDatos.GestionDatos();
        CJTA = new capaDominio.CjtAsignaciones();
    }
    
    public FranjaHoraria.Dia Dia_semana(int dia){
        
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
        int HorasC = A.gethoraClase(); 
        for (int diai = 0; diai < 5;++diai){
            for (int horai = 8; (horai+HorasC)-1 <= 19;){
                FranjaHoraria FH = new FranjaHoraria(horai,horai+HorasC-1, Dia_semana(diai));
                Restricciones compRes = new Restricciones();
                aula = compRes.Comprueba_Restricciones(A, FH, CJTA, gestionDatos2.getcjt_aules());
                if (aula != null){//Funcion que dice si podria añadir la asignatura A en la FH.  
                    FHaula fhaula = new FHaula(FH, aula);
                    ArrayFHaula.add(fhaula);
                    // Hay que añadir a que clase ira a parte de la FH. Supongo que creando un struck con FranjaHoraria y Aula ya estara.
                }
                //horai;
                horai+=HorasC;
            }
        }
        return ArrayFHaula;          
    }
  
            
    private boolean Generar_r(ArrayList<capaDatos.Asignatura> A, int i){
        // i = numero de asignatura que estoy visitando en el array A.
        //System.out.println(i);
        if (i >= A.size()){
            return true; // Todas las asignaturas han sido asignadas.
        }
        else{
            boolean b = false;
            capaDatos.Asignatura Aux = A.get(i++); // Cojo una asignatura. 
            ArrayList<FHaula> ArrayFHaula = new ArrayList<>();
            ArrayFHaula = PosiblesFH(Aux);// Para cada Asignatura me devuelve un array con la lista de franjas horarias donde la podria colocar junto con el Aula a la que iria..
            int tam = 0;
            if (ArrayFHaula != null) tam = ArrayFHaula.size();
            //else System.out.println("Array vacio");
            for (int j = 0; j < tam && !b; ++j){
                Asignacion Asig = new Asignacion(ArrayFHaula.get(j).getAula(), Aux, ArrayFHaula.get(j).getFH()); // Primero crear asignacion 
                                
                CJTA.addelement(Asig);
                b = Generar_r(A, i);// hacer recursividad
                if (!b){
                    CJTA.delelement();
                }    // Eliminar Asignacion (ya que significara que por esta rama no ha acabado )
                // Hay que controlar que no cree todas las ramas, por ejemplo haciendo que la funcion devuelva un bool, true cuando llegue al final y false mientras no.  
                //if(!b && j==tam-1) System.out.println("No hay horario");
            }
             return b;   
        }
    }
    public capaDominio.CjtAsignaciones Generar(){
        ArrayList<capaDatos.Asignatura> A = gestionDatos2.getcjt_asignatures();
        if(!Generar_r(A, 0)) System.out.println("no hay horario");
        return CJTA;
    }
    
    
}
