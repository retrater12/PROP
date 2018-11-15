/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class Restricciones {
  //  private ArrayList<Integer> conts;

    
    public Restricciones(){
        
    }
    
    /*public boolean mismaFranja(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        //Franaja es mañana o tarde
        return a1.getFranja() == a2.getFranja();
    }*/
    
    public boolean compartenTipo(capaDatos.Asignatura a1, capaDatos.Aula a2){
        return a1.getTipusClase()==a2.getTipusClase();
    }
    
    public boolean capacidadValida(capaDatos.Asignatura a1, capaDatos.Aula a2){
        return a1.getCapacidad()<=a2.getCapacidad();
    }
    
    public boolean tienenDistintoNivel(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return a1.getMat().getNivel() != a2.getMat().getNivel() || a1.getMat()== a2.getMat();
    }
    
    public boolean esCorequisito(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        boolean esCoreq = false;
        ArrayList<capaDatos.Requisito> auxReq = a1.getMat().getReqs();
        ArrayList<capaDatos.Materia> auxMat = null;
        for(int i=0;i<auxReq.size(); i++)
            //No se si esto esta bien, comprobar!!
            if (auxReq.get(i).getTipoR()==capaDatos.Requisito.TipoR.co) auxMat = auxReq.get(i).getMats();
        if(auxMat == null) return false;
        for(int i=0; i<auxMat.size() && !esCoreq; i++){
            if (auxMat.get(i).equals(a2.getMat())) esCoreq = true;
        }
        return esCoreq;
    }
    
    public boolean pertMismoGrupo(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return a1.getMat().getSiglas() == a2.getMat().getSiglas() && 
                (((int)a1.getGrupo()/10 == (int)a2.getGrupo()/10) ||  (a1.getGrupo()%10==0 && a2.getGrupo()%10==0) )&&
                (a1.getGrupo()%10==a2.getGrupo()%10 || 
                a1.getGrupo()%10!=a2.getGrupo()%10 &&(a1.getGrupo()%10==0 || a2.getGrupo()%10==0));
    }
    
        // En CjtAsignaciones estan las asignaciones hechas hasta el momento para poder comprobar restricciones.
        // En *aula hay que devolver la aula en la que se a puesto la asignatura en la franja horaria.
        // Y hay que retornar un boolean que diga si se a podido colocar o no.
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
