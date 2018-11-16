/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package generadorhorario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;





/**
 *
 * @author Sergi
 */
public class GeneradorHorario {
//Métodes d'aquesta clase han de ser static, ja que no hi ha cap instancia generada (de fet, tot amb una instancia ho podriem tractar aixi)
    
    private static String datos;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        boolean salir=false;
        capaDominio.controladorDominio control;
        while(!salir){
            Scanner sc = new Scanner(System.in);
            System.out.println("\nGENERADOR DE HORARIOS");
            System.out.println("1-Generar horario");
            System.out.println("2-Cargar horario");
            System.out.println("3-Salir");
            System.out.println("¿Que desea hacer?");
            int opcion = sc.nextInt();
            switch (opcion){
                case 1: System.out.println("Que asignaturas desea leer");
                        System.out.println("1-Ejemplo 1");
                        System.out.println("2-Ejemplo 2");
                        System.out.println("3-Ejemplo 3");
                        int asig = sc.nextInt();
                        sc.nextLine();//Limpiar buffer
                        if(asig>0 && asig<4){
                            control = new capaDominio.controladorDominio("ejemplo"+asig); datos = "ejemplo"+asig;
                            mostrar_horario_por_pantalla(control.Generar());
                            System.out.println("¿Desea guardar el horario generado?(S/N)");
                            char op = sc.next().charAt(0);
                            if(op=='S' || op=='s') guardar_horario(control.getCjtA());
                        }
                        else System.out.println("Opcion no valida");
                        break;
                case 2: 
                    File prueba = null;
                    FileReader fr = null;
                    BufferedReader br = null;
                    try {
                        prueba = new File("ArchivosExternos/Horarios.txt");
                        fr = new FileReader(prueba);
                        br = new BufferedReader(fr);
                        String linea;            
                        if ((linea=br.readLine()) != null) {
                            control = new capaDominio.controladorDominio(datos);
                            mostrar_horario_por_pantalla(control.Generar());
                        }      
                    } catch(IOException e){
                        System.out.println("Error al leer el archivo");
                        System.out.println(e.getMessage());
                     }finally{
                
                    }
                    break;
                case 3: salir =true;
                        break;
                default: System.out.println("Opcion no valida");
            }
        }
        
        
        
        
    }
    /*public static void main(String[] args){
        AsignacionTest AT = new AsignacionTest();
        AT.testgetAula();
        AT.testsetAula();
        AT.testgetAsignatura();
        AT.testsetAsignatura();
        AT.testgetFranjaHoraria();
        AT.testsetFranjaHoraria();
    }*/
    
    
    private static void mostrar_horario_por_pantalla(capaDominio.CjtAsignaciones cjt){
        Map<Integer, ArrayList<capaDominio.Asignacion>> m = cjt.get_asignaciones_ordenadas();
        System.out.println("HORAS          LUNES          MARTES          MIERCOLES          JUEVES          VIERNES");
        for (int hora = 0; hora < 12; hora++) {
            System.out.print((hora+8) + ":00"); 
            boolean acabat = false; int i = 0;
            while (!acabat){
                acabat = true;
                for (int dia = 0; dia < 5; dia++){
                    if (m.containsKey(dia*12 + hora) && m.get(dia*12 + hora).size() > i){
                        System.out.print("          ");
                        capaDominio.Asignacion a = m.get(dia*12 + hora).get(i);
                        aux_mostrar_horario_por_pantalla(a);
                        acabat = false;
                    }
                }
                System.out.println("");
                i++;
            }
        }
    }
    
    
    private static void aux_mostrar_horario_por_pantalla(capaDominio.Asignacion a){
        System.out.print(a.getAsignatura().getMat().getSiglas() + " " + a.getAsignatura().getGrupo() + " " + a.getAsignatura().getTipusClase() + " [" + a.getAula().getCodigo() + "]");
    }
    
    private static void guardar_horario(capaDominio.CjtAsignaciones cjt){
        String ruta = "ArchivosExternos/Horarios.txt";
        File archivo = new File(ruta);
        BufferedWriter out = null;
            try{
                out = new BufferedWriter(new FileWriter(ruta,archivo.exists()));
                out.write(datos);
            }catch(IOException e){
                System.out.println("Error al leer el archivo");
                System.out.println(e.getMessage());
            }finally{
                try {
                    out.close();
                } catch (IOException ex) {
                    
                }
            }
        System.out.println("Horario guardado correctamente");
    }
}
