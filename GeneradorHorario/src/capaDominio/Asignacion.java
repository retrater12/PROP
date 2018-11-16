/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDatos.Asignatura;
import capaDatos.Aula;


/**
 *
 * @author Victor Rojas
 */

public class Asignacion {
    
    /**
     * Aula corresponent a l'asignacion. Aula que estara asignada per una asignatura a una hora
     */
    private capaDatos.Aula aula;
    /**
     * Asignatura corresponent a l'asignacion. Asignatura que estara asignada per una aula a una hora
     */
    private capaDatos.Asignatura asignatura;
    /**
     * Franja horaria corresponent a l'asignacion. Franja horaria a la que s'asignara una aula i una asignatura. 
     */
    private FranjaHoraria franjaHoraria;
    
    /**
     * Compara dos Asignacions i diu si son iguals o no.
     * @param A Asignacio que es vol comparar amb la de la clase.
     * @return Retorna true si aquestes dos asignacionsson iguals, i fals altrament.
     */
    public boolean equals(Asignacion A){
        return A.getAsignatura().equals(asignatura) && A.getAula().equals(aula) && A.getFranjaHoraria().equals(franjaHoraria);
    }
    /**
     * Constructora de Asignacion amb parametres d'entrada.
     * @param aula Aula que s'asignara a l'asignacion.
     * @param asignatura Asignatura que s'asignara a la clase.
     * @param franjaHoraria  Franja horaria que s'asignara.
     */
    public Asignacion(capaDatos.Aula aula, capaDatos.Asignatura asignatura, FranjaHoraria franjaHoraria){
        this.aula = aula;
        this.asignatura = asignatura;
        this.franjaHoraria = franjaHoraria;
    }
/**
 * Obte l'aula de la clase asignacion.
 * @return Retorna l'aula de l'asignacion.
 */
    public Aula getAula() {
        return aula;
    }
/**
 * Fixa l'aula de la clase asignacion l'aula que li entra com a parametre.
 * @param aula Aula que sera asignada per aquesta clase.
 */
    public void setAula(Aula aula) {
        this.aula = aula;
    }
/**
 * Obte l'asignatura de la clase asignacion.
 * @return Retorna l'asignatura de l'asignacion.
 */
    public Asignatura getAsignatura() {
        return asignatura;
    }
/**
 * Fixa l'asignatura de la clase asignacion l'asignatura que entra com a parametre.
 * @param asignatura Asignatura que sera asignada per aquesta clase.
 */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
/**
 * Obte la franja horaria de la  clase asignacion.
 * @return Retorna la franja horaria de l'asignacion.
 */
    public FranjaHoraria getFranjaHoraria() {
        return franjaHoraria;
    }
/**
 * Fixa la franja horaria de la clase asignacion la franja horaria que entra com a parametre.
 * @param franjaHoraria Franja horaria que sera asignada per aquesta clase.
 */
    public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
    
    
}
