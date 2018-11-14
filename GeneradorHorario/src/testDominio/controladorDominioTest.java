/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
package testDominio;
import capaDominio.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class controladorDominioTest {
    
    // Constructora:
    @Before
    public void controladorDominioTest(){
        
    }
    // 
    @Test
    public void Dia_semana() throws IOException{
        controladorDominio CD = new controladorDominio();
        System.out.println("Test Dia_semana: (elejir dia de la semana (0 a 4))");
        System.out.println("0 = LUNES");
        System.out.println("1 = MARTES");
        System.out.println("2 = MIERCOLES");
        System.out.println("3 = JUEVES");
        System.out.println("4 = VIERNES");
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        String Dia = buff.readLine();
        int dia = Integer.parseInt(Dia);
        while (dia < 0 || dia > 4){
            Dia = buff.readLine();
            dia = Integer.parseInt(Dia);
        } 
        FranjaHoraria.Dia D = null;
        if(dia == 0) D = FranjaHoraria.Dia.LUNES;
        else if(dia == 1) D = FranjaHoraria.Dia.MARTES;
        else if(dia == 2) D = FranjaHoraria.Dia.MIERCOLES;
        else if(dia == 3) D = FranjaHoraria.Dia.JUEVES;
        else if(dia == 4) D = FranjaHoraria.Dia.VIERNES;
                       
        assertEquals("", D, CD.Dia_semana(dia));
    }
    
    
    @Test
    public void PosiblesFH(){
        
    }
    
    @Test
    public void Generar_r(){
        
    }
    @Test
    public void Generar(){
        
    }
    
    
    
}
*/