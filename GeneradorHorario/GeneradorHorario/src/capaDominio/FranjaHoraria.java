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
    
        public boolean equals(FranjaHoraria FH){
            return FH.getDia() == dia && FH.getHoraFi() == HoraFi
                    && FH.getHoraIni() == HoraIni;
        } 
        
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
         
        public boolean seguentHora(){
            if (HoraFi >= 19) {
                if (dia == Dia.VIERNES) return false;
                seguentDia();
            } else {
                HoraIni++;
                HoraFi++;
            }
            return true;
        }
            
        private void seguentDia(){
            if (dia == Dia.LUNES) dia = Dia.MARTES;
            else if (dia == Dia.MARTES) dia = Dia.MIERCOLES;
            else if (dia == Dia.MIERCOLES) dia = Dia.JUEVES;
            else if (dia == Dia.JUEVES) dia = Dia.VIERNES;            
            HoraFi = 8 + (HoraFi-HoraIni);
            HoraIni = 8;
        }
    }
