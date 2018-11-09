/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package generadorhorario;

import capaDominio.Asignacion;
import java.util.ArrayList;
import java.util.Map;




/**
 *
 * @author Sergi
 */
public class GeneradorHorario {
//Métodes d'aquesta clase han de ser static, ja que no hi ha cap instancia generada (de fet, tot amb una instancia ho podriem tractar aixi)
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        capaDominio.controladorDominio control = new capaDominio.controladorDominio();
        
        mostrar_horario_por_pantalla(control.generar_horario());
        
        
        
    }
    
    
    private static void mostrar_horario_por_pantalla(capaDominio.CjtAsignaciones cjt){
        Map<Integer, ArrayList<capaDominio.Asignacion>> m = cjt.get_asignaciones_ordenadas();
        for (int dia = 0; dia < 5; dia++){
            for (int hora = 0; hora < 12; hora++){
                if (m.containsKey(dia*12 + hora)){
                    ArrayList<Asignacion> a = m.get(dia*12 + hora);
                    for (int i = 0; i < a.size(); i++){
                        
                        
                        
                    }
                }
            }
        }
    }
    
}
