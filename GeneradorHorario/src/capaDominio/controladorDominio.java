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
 * @author Alberto Camacho
 */
public class controladorDominio {
/**
 * GestionDatos amb totes les asignatures i les aules carregades.
 */
    private capaDatos.GestionDatos gestionDatos2;
    /*
    Clase Restricciones que comprobara les restriccions a l'hora d'asignar aules a asignatures.
    */
    private Restricciones rest;
    /*
    * Conjunt d'asignacions on es guardaran totes les asignacions que es van creant durant l'execucio per tal de guardar l'horari.
    */
    private capaDominio.CjtAsignaciones CJTA;
    
    /**
     * Constructora amb un parametre d'entrada.
     * @param datos Correspon al nom del arxiu que es vol carregar.
     */
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
       /**
        * Funcio que genera recursivament una posible combinacio d'asignacions entre aules i asignatures. I les va guradan al CjtAsignaciones de la clase controladorDominio.
        * @param A Arraylist que conte totes les asignatues que s'han d'asignar.
        * @param i Variable per indicar a traves dels nivells de recursivitat quina de les asignatures estem tractan.
        * @return Retorna true si hi existeix una posible combinacio i fals altrament.
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
    /**
     * Funcio encarregada de cridar a la funcio recursiva de creacio de l'horari.
     * @return Retorna un conjunt d'asignacions no vuit a no ser que no hi hagi combinacio posible.
     */
    public capaDominio.CjtAsignaciones Generar(){
        ArrayList<capaDatos.Asignatura> A = gestionDatos2.getcjt_asignatures();
        if(!Generar_r(A, 0)) System.out.println("no hay horario");
        return CJTA;
    }
    /**
     * Obte el Conjunt d'asignacions de la clase controladorDominio.
     * @return Retorna CJTA, el conjunt d'asignacions de la clase.
     */
    public capaDominio.CjtAsignaciones getCjtA(){
        return CJTA;
    }
    
    
}
