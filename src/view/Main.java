package view;

import java.util.Scanner;

import controller.ejercicios.MonedaController;
import controller.util.Utilidades;
import model.Moneda;

//Se muestra una interfaz en la consola
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Se inicializan las variables
        Moneda moneda_entrada, moneda_salida;
        double valor_entrada, valor_salida;

        //Se muestran las monedas
        MonedaController.mostrarMonedas();

        //Se solicita al usuario los datos requeridos
        System.out.print("\nIngrese la moneda de entrada (entero del 1 al 50): ");
        int auxI = Utilidades.tranformStringInt(sc.nextLine());
        moneda_entrada = MonedaController.getMoneda(auxI);
        System.out.print("Ingrese la cantidad de dinero que desea convertir: ");
        double auxD = Utilidades.tranformStringDouble(sc.nextLine());
        valor_entrada = auxD;
        System.out.println();

        //Se vuelven a mostrar las monedas
        MonedaController.mostrarMonedas();

        //Se piden los últimos datos
        System.out.print("\nIngrese la moneda de salida (entero del 1 al 50): ");
        auxI = Utilidades.tranformStringInt(sc.nextLine());
        moneda_salida = MonedaController.getMoneda(auxI);

        //Se calcula el valor de salida
        valor_salida = valor_entrada / moneda_entrada.valor_de_conversion * moneda_salida.valor_de_conversion;

        //Se muestran las respectivas salidas al usuario
        System.out.println("\nEl valor de "+valor_entrada+""+moneda_entrada.simbolo+" ["+moneda_entrada.nombre+" ("+moneda_entrada.codigo_iso+")] convertido a "+moneda_salida.nombre+" ("+moneda_salida.codigo_iso+") es: ");
        System.out.println(valor_salida+" "+moneda_salida.simbolo);
    }
}
