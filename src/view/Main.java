package view;

import java.util.Scanner;

import controller.ejercicios.MonedaController;

import model.Moneda;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Moneda moneda_entrada, moneda_salida;
        double valor_entrada, valor_salida;
        MonedaController.mostrarMonedas();
        System.out.print("\nIngrese la moneda de entrada (entero del 1 al 50): ");
        moneda_entrada = MonedaController.getMoneda(sc.nextInt());
        System.out.print("Ingrese la cantidad de dinero que desea convertir: ");
        valor_entrada = sc.nextDouble();
        System.out.println();
        MonedaController.mostrarMonedas();
        System.out.print("\nIngrese la moneda de salida (entero del 1 al 50): ");
        moneda_salida = MonedaController.getMoneda(sc.nextInt());
        valor_salida = valor_entrada / moneda_entrada.valor_de_conversion * moneda_salida.valor_de_conversion;
        System.out.println("\nEl valor de "+valor_entrada+""+moneda_entrada.simbolo+" ["+moneda_entrada.nombre+" ("+moneda_entrada.codigo_iso+")] convertido a "+moneda_salida.nombre+" ("+moneda_salida.codigo_iso+") es: ");
        System.out.println(valor_salida+" "+moneda_salida.simbolo);
    }
}
