/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import java.util.ArrayList;

/**
 *
 * @author Victor Rojas
 */
public class Restricciones {
  //  private ArrayList<Integer> conts;

    /**
     * Constructora de la clase Restricciones sense parametres.
     */
    public Restricciones(){
        
    }
    
    /*public boolean mismaFranja(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        //Franaja es mañana o tarde
        return a1.getFranja() == a2.getFranja();
    }*/
    /**
     * Comproba si l'asignatura a1 i l'aula a2 tenen el mateix tipus de clase(T, L, P).
     * @param a1 Asignatura que es vol comparar el tipus de clase.
     * @param a2 Aula que es vol comparar el tipus de clase.
     * @return Retorna true si tant a1 com a2 tenen el mateix tipus de clase, i fals altrament.
     */
    public boolean compartenTipo(capaDatos.Asignatura a1, capaDatos.Aula a2){
        return a1.getTipusClase()==a2.getTipusClase();
    }
    /**
     * Comprova que l'asignatura a1 es pugui realitzar a l'aula a2 degut a les capacitats de cadascuna de les clases.
     * @param a1 Asignatura que es vol comparar la seva capacitat.
     * @param a2 Aula que es vol comparar la seva capacitat.
     * @return Retorna true si es pot fer l'asignatura a1 a l'aula a2 comprovant les capacitats, fals altrament.
     */
    public boolean capacidadValida(capaDatos.Asignatura a1, capaDatos.Aula a2){
        return a1.getCapacidad()<=a2.getCapacidad();
    }
    /**
     * Comprova si l'asingatura a1 te nivell diferent a l'asignatura a2.
     * @param a1 Asignatura que sera comparada.
     * @param a2 Segona Asignatura que sera comparada.
     * @return Retorna true si a1 i a2 no son del mateix nivell i fals altrament.
     */
    public boolean tienenDistintoNivel(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return a1.getMat().getNivel() != a2.getMat().getNivel() || a1.getMat()== a2.getMat();
    }
    /**
     * Comprova si l'asignatura a1 i l'asignatura a2 tenen confliques per els correquisits.
     * @param a1 Primera Asignatura que es comparara.
     * @param a2 Segona Asignatura que es comparara amb la primera.
     * @return Retorna false si no son correquisit i fals altrament.
     */
    public boolean esCorequisito(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        boolean esCoreq = false;
        ArrayList<capaDatos.Requisito> auxReq = a1.getMat().getReqs();
        ArrayList<capaDatos.Materia> auxMat = null;
        for(int i=0;i<auxReq.size(); i++)
            if (auxReq.get(i).getTipoR()==capaDatos.Requisito.TipoR.co) auxMat = auxReq.get(i).getMats();
        if(auxMat == null) return false;
        for(int i=0; i<auxMat.size() && !esCoreq; i++){
            if (auxMat.get(i).equals(a2.getMat())) esCoreq = true;
        }
        return esCoreq;
    }
    /**
     * Comprova que dos asignatures, a1 i a2 no pertanyin al mateix grup
     * @param a1 Primera asignatura que sera comparada.
     * @param a2 Segona asignatura que sera comparada amb la primera.
     * @return Retorna true si son del mateix nivell i fals altrament.
     */
    public boolean pertMismoGrupo(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return a1.getMat().getSiglas() == a2.getMat().getSiglas() && 
                (((int)a1.getGrupo()/10 == (int)a2.getGrupo()/10) ||  (a1.getGrupo()%10==0 && a2.getGrupo()%10==0) )&&
                (a1.getGrupo()%10==a2.getGrupo()%10 || 
                a1.getGrupo()%10!=a2.getGrupo()%10 &&(a1.getGrupo()%10==0 || a2.getGrupo()%10==0));
    }
    
     
    /**
     * Obte una posible aula a la que podria anar l'asignatura a la franja horaria tenint en compte les asignatures que ja es fan a aquella franja horaria i les aules disponibles.
     * @param asig Asignatura a la que es vol asignar una aula.
     * @param franH Franja horaria a la que es vol fer l'asignacio.
     * @param cjtA CjtAsignaicons fetes fins el moment, per comprobar restriccions entre asignatures que es facin a l'hora.
     * @param aulas Aules disponibles que hi hauran per la franja horaria franH.
     * @return Retorna una aula a la que podria realitzarse l'asignatura asig, i en cas de no haver-hi cap disponible retornar null.
     */
    public capaDatos.Aula Comprueba_Restricciones(capaDatos.Asignatura asig, FranjaHoraria franH, CjtAsignaciones cjtA, ArrayList<capaDatos.Aula> aulas){
           ArrayList<Asignacion> cjtAsig = cjtA.getCjtA();
           ArrayList<capaDatos.Aula> aulaAux1 = (ArrayList<capaDatos.Aula>)aulas.clone();
           /*
           ArrayList<capaDatos.Aula> aulaAux1 = new ArrayList<capaDatos.Aula>();
           ArrayList<capaDatos.Aula> aulaAux2 = new ArrayList<capaDatos.Aula>();*/
           
            //System.out.println("Aqui1"); 
            int tam = 0;
            if (cjtAsig != null) tam = cjtAsig.size();
        for(int i=0; i < tam;i++){
            //System.out.println("Aqui");
            if (cjtAsig.get(i).getFranjaHoraria().getDia() == franH.getDia() &&
                        ((franH.getHoraIni()>=cjtAsig.get(i).getFranjaHoraria().getHoraIni() && 
                        franH.getHoraIni()<=cjtAsig.get(i).getFranjaHoraria().getHoraFi()) ||
                        (franH.getHoraFi()>=cjtAsig.get(i).getFranjaHoraria().getHoraIni() &&
                        franH.getHoraFi()<=cjtAsig.get(i).getFranjaHoraria().getHoraFi()))){
                 //System.out.println("Aqui");
                if(tienenDistintoNivel(asig, cjtAsig.get(i).getAsignatura()) &&
                        !esCorequisito(asig, cjtAsig.get(i).getAsignatura()) && 
                        !esCorequisito(cjtAsig.get(i).getAsignatura(), asig) &&
                        !pertMismoGrupo(asig, cjtAsig.get(i).getAsignatura())){
                        //aulaAux1.add(cjtAsig.get(i).getAula());
                        aulaAux1.remove(cjtAsig.get(i).getAula());
                }else{
                      return null;  
                }
            }
        }
        /*if(aulaAux1.isEmpty()) aulaAux2=aulas;
        else{
            for(int i=0; i<aulas.size(); i++){
                if(!aulaAux1.contains(aulas.get(i))) aulaAux2.add(aulas.get(i));
            }
        }
        for(int i=0; i<aulaAux2.size();i++){
            if(compartenTipo(asig, aulaAux2.get(i)) && capacidadValida(asig, aulaAux2.get(i))){
                return aulaAux2.get(i);
            }
        }*/
        for(int i=0; i<aulaAux1.size();i++){
            if(compartenTipo(asig, aulaAux1.get(i)) && capacidadValida(asig, aulaAux1.get(i))){
                return aulaAux1.get(i);
            }
        }
        return null;
    }
}
