/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Utiles;

import java.util.Random;

/**
 *
 * @author wilson
 */
public class Utiles {

    public static boolean validate(String num) {
        boolean band = true;
        if (Character.toString(num.charAt(0)) == "-") {
            num = num.substring(1);
        }
        int cont_p = 0;
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i)) && !Character.toString(num.charAt(i)).startsWith(".")) {
                band = false;
                cont_p++;
                break;
            } else if (Character.toString(num.charAt(i)).startsWith(".")) {
                cont_p++;
            }
        }
        if (cont_p > 1) {
            band = false;
        }
        return band;
    }

    public static int transformStringInt(String num) {
        int resp = 0;
        if (Utiles.validate(num)) {
            resp = (int) Utiles.tranformStringDouble(num);
        }
        return resp;
    }

    public static double tranformStringDouble(String num) {
        double resp = 0;
        if (Utiles.validate(num)) {
            resp = Double.parseDouble(num);
        }
        return resp;
    }

    public static float transformStringFloat(String num) {
        float resp = 0;
        if (Utiles.validate(num)) {
            resp = Float.parseFloat(num);
        }
        return resp;
    }

    public static int generaNumeroRango(int minimo, int maximo) {
        Random random = new Random();
        //2-10
        //2 + 11 - 2 ------- 2 + (9)
        return minimo + random.nextInt((maximo + 1) - minimo);
    }

    public static float redondear(float num) {
        float aux = num * 100.00f;
        float aux1 = (float) ((int) aux);
        return (aux1 / 100.00f);        //return (float) (Math.round(num*100.00)/100.00);
    }

    public static void print(Object[] arreglo) {
        System.out.println("ARREGLO");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i].toString() + ", ");
        }
        System.out.println();
    }
    
    public static Float[] copy(Float[] arreglo) {
        Float[] result = new Float[arreglo.length];
        for (int i = 0; i < arreglo.length; i++) {
            result[i] = arreglo[i];
        }
        return result;
    }

    public static void print_matriz(Object[][] m) {
        if(m==null){System.out.println("\t********Matriz nula********\n");return;}
        System.out.println("Print Matrix");
            for (int i = 0; i < m.length; i++) {
                if(m[i]==null){
                    System.out.print("----" + "\n");
                    continue;
                }
                for (int j = 0; j < m[i].length; j++) {
                    if (m[i][j] != null) {
                        System.out.print(m[i][j] + "\t");
                    } else {
                        System.out.print("--" + "\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("");
    }

    public static void print_matriz1D(Object[] m) {
        System.out.println("Print Matrix ********** 1D ********");
        for (int i = 0; i < m.length; i++) {
            if (m[i] != null) {
                System.out.print(m[i] + "\t");
            } else {
                System.out.print("--" + "\t");
            }

        }
        System.out.println("");
    }
}
