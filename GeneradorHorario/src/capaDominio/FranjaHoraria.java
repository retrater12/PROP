/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

/**
 *
 * @author Alberto Camacho
 */
    
    public class FranjaHoraria {
        /**
         * Enumeracio amb els dies de la semana, LUNES, MARTES, MIERCOLES, JUEVES i VIERNES.
         */
        public enum Dia {
            LUNES, MARTES, MIERCOLES, JUEVES,VIERNES 
        }
        /**
         * Hora d'inici de la franja horaria.
         */
        private int HoraIni;
        /**
         * Hora final de la franja horaria.
         */
        private int HoraFi;
        /**
         * Dia de la franja horaria.
         */
        private Dia dia;   
    /**
     * Comproba si dos franjes horaries(FranjaHoraria) son iguals.
     * @param FH FranjaHoraria que es comparara amb la clase acutal de FranjaHoraria.
     * @return Retorna true si les dues FranjasHoraria son iguals i false si no ho son.
     */
        public boolean equals(FranjaHoraria FH){
            return FH.getDia() == dia && FH.getHoraFi() == HoraFi
                    && FH.getHoraIni() == HoraIni;
        } 
        /**
         * Constructora de la clase FranjaHoraria amb parametres d'entrada.
         * @param horaIni Hora d'inici que s'asignara a la hora d'inici de la clase.
         * @param horaFi Hora finar que s'asignara a la hora final de la clase.
         * @param dia Dia que s'asignara al Dia de la clase.
         */
        public FranjaHoraria(Integer horaIni, Integer horaFi, Dia dia) {
            this.HoraIni = horaIni;
            this.HoraFi = horaFi;
            this.dia = dia;
        }
        /**
         * Obte la hora d'inici de la clase FranjaHoraria.
         * @return Retorna la HoraIni.
         */
        public int getHoraIni(){
            return HoraIni;
        }
        /**
         * Obte la hora final de la clase FranjaHoraria.
         * @return Retorn la HoraFi
         */
        public int getHoraFi(){
            return HoraFi;
        }
        /**
         * Obte el dia de la clase FranjaHoraria.
         * @return Retorna el Dia.
         */
        public Dia getDia(){
            return dia;
        }
        /**
         * Calcula l'hora "global" de la franja horaria, per tal de tenir un seguiment de franjes. 
         * @return Retonra un valor per saber l'hora inicial i el dia.
         */
        public int unificar_values(){
            return (dia.ordinal() * 12 + (HoraIni - 8));
        }
         /**
          * Calcula la seguent hora per la FranjaHoraria actual.
          * @return Retorna true si es pot augmentar la franja horaria, es a dir que no son les 8 de la tarda de divendres, i fals altrament.
          */
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
            /**
             * Cambia el Dia pel seguent i augmenta les hores, HoraIni i HoraFi.
             */
        private void seguentDia(){
            if (dia == Dia.LUNES) dia = Dia.MARTES;
            else if (dia == Dia.MARTES) dia = Dia.MIERCOLES;
            else if (dia == Dia.MIERCOLES) dia = Dia.JUEVES;
            else if (dia == Dia.JUEVES) dia = Dia.VIERNES;            
            HoraFi = 8 + (HoraFi-HoraIni);
            HoraIni = 8;
        }
    }
