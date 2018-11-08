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
}
