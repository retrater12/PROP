/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author alumne
 */
    
    public class FranjaHoraria {
        public enum Dia {
            LUNES, MARTES, MIERCOLES, JUEVES,VIERNES 
        }
        private int HoraIni;
        private Dia dia;   
    
        public boolean equals(FranjaHoraria FH){
            return FH.getDia() == dia && FH.getHoraIni() == HoraIni;
        } 
        
        public FranjaHoraria(Integer horaIni, Dia dia) {
            this.HoraIni = horaIni;
           // this.HoraFi = horaFi;
            this.dia = dia;
        }
        
        public int getHoraIni(){
            return HoraIni;
        }

        
        public Dia getDia(){
            return dia;
        }
        
        public int unificar_values(){
            return (dia.ordinal() * 12 + (HoraIni - 8));
        }
         
        public boolean seguentHora(int horaClase){
            if (HoraIni + horaClase >= 20) {
                if (dia == Dia.VIERNES) return false;
                seguentDia();
            } else {
                HoraIni++;
            }
            return true;
        }
            
        private void seguentDia(){
            if (dia == Dia.LUNES) dia = Dia.MARTES;
            else if (dia == Dia.MARTES) dia = Dia.MIERCOLES;
            else if (dia == Dia.MIERCOLES) dia = Dia.JUEVES;
            else if (dia == Dia.JUEVES) dia = Dia.VIERNES;            
            HoraIni = 8;
        }
        
        public boolean contains(Map<FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> m){
             Iterator e = m.keySet().iterator();
             while (e.hasNext()){       
                FranjaHoraria key = (FranjaHoraria) e.next();
                 System.out.println("HORAAAA    " + key.HoraIni + "    " + key.dia );
                if (this.HoraIni == key.getHoraIni() && this.dia == key.getDia()) return true;
             }
             return false;
        }
        
         @Override
        public boolean equals(Object o){
            FranjaHoraria FH = (FranjaHoraria) o;
            return FH.getDia() == dia && FH.getHoraIni() == HoraIni;
        } 
    }
