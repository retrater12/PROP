/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

/**
 *
 * @author alumne
 */
    
    public class FranjaHoraria {
        public enum Dia {
            LUNES, MARTES, MIERCOLES, JUEVES,VIERNES 
        }
        private int HoraIni;
        private int HoraFi;
        private Dia dia;   
    
        public FranjaHoraria(Integer horaIni, Integer horaFi, Dia dia) {
            this.HoraIni = horaIni;
            this.HoraFi = horaFi;
            this.dia = dia;
        }
        
        public int getHoraIni(){
            return HoraIni;
        }
        
        public int getHoraFi(){
            return HoraFi;
        }
        
        public Dia getDia(){
            return dia;
        }
        
         public int unificar_values(){
            return (dia.ordinal() * 12 + (HoraIni - 8));
        }
        
    }
