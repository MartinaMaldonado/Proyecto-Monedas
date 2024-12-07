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
