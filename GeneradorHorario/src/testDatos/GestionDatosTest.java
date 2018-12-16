/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDatos;


import capaDatos.GestionDatos;
import java.util.Scanner;

/**
 *
 * @author casa1
 */
public class GestionDatosTest {
    GestionDatos gd = new GestionDatos();
    static Scanner sc = new Scanner(System.in);
    
    public void testchar_to_tipusClase(){
        System.out.println("Ingresa el tipo de clase(T,L o P)");
        char tipo = sc.next().charAt(0);
        //gd.stri
        
    }
    
    public void teststring_to_especialitat(){
        
    }
    
    public void testint_to_tipoR(){
        
    }
    /*
    public static void main (String [] args){
        GestionDatosTest r1 = new GestionDatosTest();
        boolean aux = true;
        while(aux){
            System.out.println("\n");
            System.out.println("Test GestionDatos:\n¿Que función desea probar?(1..8)");
            System.out.println("1.Materia()\n2.getEspecialidad()\n3.getNom()\n"
                    + "4.setEspecialidad()\n5.setRequisito()\n6.getSiglas()\n7.getNivel()\n"
                    + "8.getRequisito()\n9.Salir");
            System.out.println("Importante: Es recomendable utilizar el test del constructor o los set antes que los get, en caso contrario, devolveran nulo");
            int res = sc.nextInt();
            sc.nextLine();//Para limpiar el salto de linea del buffer
            switch(res){
                case 1:
                        r1.testMateria();
                        break;
                case 2:
                        r1.testgetEspecialidad();
                        break;
                case 3:
                        r1.testgetNom();
                        break;
                case 4:
                        r1.testsetEspecialidad();
                        break;
                case 5:
                        r1.testsetRequisito();
                        break;
                case 6:
                        r1.testgetSiglas();
                        break;
                case 7:
                        r1.testgetNivel();
                        break;
                case 8: r1.testgetRequisito();
                        break;
                case 9: aux = false;
                        break;
                default:System.out.println("Opcion no valida");
                        break;
            }
        }
    }*/
}
