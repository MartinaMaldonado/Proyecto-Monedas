package controller.util;

import java.util.Random;

public class Utilidades {
    public static float transformStringFloat(String num){
        float resp = 0;
        return Float.parseFloat(num);
    }
    public static int generaNumeroRango(int minimo, int maximo){
        Random random = new Random();
        return minimo + random.nextInt((maximo + 1) -minimo);
    }
}
