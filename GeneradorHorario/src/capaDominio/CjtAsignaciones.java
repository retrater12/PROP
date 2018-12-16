/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDatos.Asignatura;
import static capaDatos.Asignatura.StringtoTipusclase;
import static capaDatos.Asignatura.TipusClasetoString;
import capaDatos.Aula;
import capaDominio.Asignacion;
import capaDominio.FranjaHoraria.*;
import static capaDominio.FranjaHoraria.StringtoDia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

 

/**
 *
 * @author Sergi Aragall
 */
public class CjtAsignaciones {
/**
 * Arraylist on es guarda totes les asignacions realitzades. Creades per la clase controladorDominio, per tal de construir l'horari.
 */
    private ArrayList<Asignacion> cjt_asignaciones; 
/**
 * Constructora sense parametres de la clase CjtAsignacion.
 */
    public CjtAsignaciones(){
        ArrayList<Asignacion> asig = new ArrayList<>();
        cjt_asignaciones = asig;
    }
    /**
     * Constructora amb parametres de la clase CjtAsignacion. Creara una clase CjtAsignacion a la cual se li asignara l'arraylist asig.
     * @param asig Arraylist que se li asignara a la clase.
     */
    public CjtAsignaciones(ArrayList<Asignacion> asig){
        cjt_asignaciones = asig;
    }
    
    /**
     * Obte l'arraylist d'asignacions de la clase CjtAsignacions.
     * @return Retorna l'arraylist d'asignacions.
     */
    public ArrayList<Asignacion> getCjtA(){
        return cjt_asignaciones;
    }
    /**
     * Afegeix un element, en aquest cas una asignacio, a l'arraylist de la clase CjtAsignacions.
     * @param A Asignacion que sera afegida al arraylist d'asignacions.
     */
    public void addelement(Asignacion A){
            cjt_asignaciones.add(A);
    }
    /**
     * Elimina l'ultim element de l'arraylist d'asignacions de la clase CjtAsignacions.
     */
    public void delelement(){
        cjt_asignaciones.remove(cjt_asignaciones.size()-1);
    }
    /**
     * Crea un map en que s'ordenen totes les asignacions que pertanyen a l'arraylist de la clase
     * CjtAsignaciones segons el dia i l'hora de la franja horaria de cada una.
     * @return Retorna un Map amb les asignacions ordenades segons hora i dia.
     */
     public Map<Integer, ArrayList<Asignacion>> get_asignaciones_ordenadas (){
        Map<Integer, ArrayList<Asignacion>> m = new HashMap<Integer, ArrayList<Asignacion>>();  
        for (int i = 0; i < cjt_asignaciones.size(); i++){
            int val = cjt_asignaciones.get(i).getFranjaHoraria().unificar_values();
            for (int j = 0; j < cjt_asignaciones.get(i).getAsignatura().getHoraClase(); j++){
                if (!m.containsKey(val+j))
                    m.put(val+j, new ArrayList<>());
                m.get(val+j).add(cjt_asignaciones.get(i));      
            }
        }           
        return (HashMap<Integer, ArrayList<Asignacion>>) m;
    }
     
    /**
     *
     * @param name
     * @return
     * @throws IOException
     */     
     public boolean guardar_horario(String name) throws IOException{
         String ruta = "ArchivosExternos/horarios_guardados/Asignaciones/" + name + ".txt";
         File file = new File(ruta);
         BufferedWriter bw = null;
         
         if (file.exists())
         {
             return false; 
         }
         else
         {
            bw = new BufferedWriter(new FileWriter(ruta,false));
             bw.write(name); // nombre del archivo
             bw.newLine();
             String siglas, grupo, tipoclase, codigo, dia, horaini, horafi;
             
             for (Asignacion asig : cjt_asignaciones){
                 siglas = asig.getAsignatura().getMat().getSiglas();
                 grupo = Integer.toString(asig.getAsignatura().getGrupo());
                 tipoclase = TipusClasetoString(asig.getAsignatura().getTipusClase());
                 codigo = asig.getAula().getCodigo();
                 dia = asig.getFranjaHoraria().getDiaString();
                 horaini = Integer.toString(asig.getFranjaHoraria().getHoraIni());
                 horafi = Integer.toString(asig.getFranjaHoraria().getHoraFi());
                 
                 bw.write(siglas);bw.newLine();                         
                 bw.write(grupo);bw.newLine();
                 bw.write(tipoclase);bw.newLine();
                 bw.write(codigo);bw.newLine();
                 bw.write(dia);bw.newLine();
                 bw.write(horaini);bw.newLine();
                 bw.write(horafi);bw.newLine();
               //  bw.write("!"+asig);bw.newLine();
             }
             
             bw.close();
             return true;
         }
     }
     
     
     
     public Asignacion crear(String siglas, String grupo, String tipoclase, String codigo, String dia, String horaini, String horafi, ArrayList<Asignatura> asignaturas, ArrayList<Aula> aulas){
         Asignacion aux = new Asignacion();
         boolean found = false;
         for(Asignatura a : asignaturas){
             if (a.getMat().getSiglas().equals(siglas) && Integer.toString(a.getGrupo()).equals(grupo) && a.getTipusClase() == StringtoTipusclase(tipoclase)){
                 aux.setAsignatura(a);
             }
         }
         for(Aula a : aulas){
             if (a.getCodigo().equals(codigo)){
                 aux.setAula(a);
             }
         }
         FranjaHoraria fh = new FranjaHoraria(Integer.parseInt(horaini) , Integer.parseInt(horafi) , StringtoDia(dia));
         aux.setFranjaHoraria(fh);
         return aux;
     }
     
     public boolean cargar_horario(String name, ArrayList<Asignatura> asignaturas, ArrayList<Aula> aulas) throws IOException{
         String ruta = "ArchivosExternos/horarios_guardados/Asignaciones/" + name + ".txt";
         File file = new File(ruta);         
         if (!file.exists()) return false;
         ArrayList<Asignacion> aux = new ArrayList<>();
         BufferedReader br = new BufferedReader(new FileReader(ruta));
         
         String linia = br.readLine();
         String siglas, grupo, codigo, dia, horaini, horafi, tipoclase;
         while ((linia = br.readLine()) != null){
             System.out.println(linia);
             siglas = linia;    linia = br.readLine();System.out.println(linia);
             grupo = linia;     linia = br.readLine();System.out.println(linia);
             tipoclase = linia; linia = br.readLine();System.out.println(linia);
             codigo = linia;    linia = br.readLine();System.out.println(linia);
             dia = linia;       linia = br.readLine();System.out.println(linia);
             horaini = linia;   linia = br.readLine();System.out.println(linia);
             horafi = linia;
             aux.add(crear(siglas, grupo, tipoclase, codigo, dia, horaini, horafi, asignaturas, aulas));
             
         }
         cjt_asignaciones = aux;
         return true;
     }
}

