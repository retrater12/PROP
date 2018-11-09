/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;

import java.util.ArrayList;

/**
 *
 * @author Alberto
 */
public class Materia {

    public enum Especialitat {
        Computació, EnginyeriaComputadors, EnginyeriaSoftware, SistemesInformació, TecnologiesInformació
    }
    
     private String nom;
     private String siglas;
     private int nivel;
     private Especialitat especialitat; //if nivel < 3, especialitat = null
     private ArrayList<Requisito> reqs;

     
     
     
    public Materia(String nom, String siglas, int nivel) {
        this.nom = nom;
        this.siglas = siglas;
        this.nivel = nivel;
        reqs = new ArrayList<Requisito>();
    }    
    
    
    /* SETS */
    public void setEspecialitat(Especialitat especialitat){
        this.especialitat = especialitat;
    }
    //pre: nivel > 2
    //post: se asigna una especialidad
    
    public void setRequisito(Requisito req){
        reqs.add(req);
    }
    //pre: cert
    //post: añade el requisito req en la lista reqs
     
    
    /* GETS */
    public String getSiglas(){
        return siglas;
    }
    //Pre: cert
    //post: devuelve siglas
    
    public int getNivel(){
        return nivel;
    }
    
    public ArrayList<Requisito> getReqs(){
        return reqs;
    }
     
}
