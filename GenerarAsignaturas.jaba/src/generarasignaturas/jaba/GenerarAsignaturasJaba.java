/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generarasignaturas.jaba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GenerarAsignaturasJaba{
    
    public static void main(String[] argv) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Escribir Asignarutas:");
        ArrayList<String> As = new ArrayList<>();
        
        String asig = buff.readLine(); 
        while (!"-".equals(asig)){
            As.add(asig);
            asig = buff.readLine(); 
        }
        System.out.println("Escribir numero de grupos:");
        String numG = buff.readLine();
        System.out.println("Escribir numero de subgrupos:");
        String numSG = buff.readLine();
        
        for (int i = 0; i < As.size(); i++){
            for (int grupo = 1; grupo <= Integer.parseInt(numG);++grupo){
                for (int Sgrupo = 0; Sgrupo <= Integer.parseInt(numSG); ++Sgrupo){
                    if (Sgrupo == 0) System.out.println(As.get(i)+ "-" + grupo+Sgrupo + "-40-T-2");
                    else System.out.println(As.get(i)+ "-" + grupo+Sgrupo + "-20-L-2");
                } 
            } 
        } 
    }
    
}