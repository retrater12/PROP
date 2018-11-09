/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;


/**
 *
 * @author Victor
 */

public class Asignacion {
    
    
    private capaDatos.Aula aula;
    private capaDatos.Asignatura asignatura;
    private FranjaHoraria franjaHoraria;
    
    public Asignacion(capaDatos.Aula aula, capaDatos.Asignatura asignatura, FranjaHoraria franjaHoraria){
        this.aula = aula;
        this.asignatura = asignatura;
        this.franjaHoraria = franjaHoraria;
    }
    
    public FranjaHoraria get_franjahoraria(){
        return franjaHoraria;
    }
    
    public capaDatos.Aula get_Aula(){
        return aula;
    }
    
    public capaDatos.Asignatura get_Asignatura() {
        return asignatura;
    }
    
}
