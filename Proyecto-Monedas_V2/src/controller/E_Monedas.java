/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author wilson
 */
public class E_Monedas {
    public String[][] monedas;
    public String[][] sesiones;
    private Integer props = 4;
    
    
    public String[][] getMonedas() {
        return monedas;
    }

    public void crear(Integer nro) {
        this.monedas = new String[nro][props];
    }

    public Boolean generar_files(Object[][] data, String url) {
        if (data != null) {
            String pathDatas = url;
            try {
                FileWriter file_data = new FileWriter(pathDatas);
                for (int i = 0; i < data.length; i++) {
                    String datos = "";
                    for (int j = 0; j < data[0].length; j++) {
                        datos += data[i][j] + "\t";
                    }

                    file_data.write(datos.toString() + "\n");
                    file_data.flush();
                }
                file_data.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public String[][] cargarArchivo(String url) {
        String pathDatas = url;

        try {
            FileReader frn = new FileReader(pathDatas);
            BufferedReader file_data = new BufferedReader(frn);
            FileReader fra1 = new FileReader(pathDatas);
            BufferedReader size = new BufferedReader(fra1);
            //crear

            String linea;
            linea = file_data.readLine();

            int cont = 0;
            //this.matriz = new String[nrof][nroc];
            String[][] matriz = new String[Integer.parseInt(String.valueOf(size.lines().count()))][linea.split("\t").length];
            do {
                //data[cont][1] = Float.parseFloat(linea);
                String[] aux = linea.split("\t");
                if (aux[0].equalsIgnoreCase("null")) {
                    for (int i = 0; i < matriz[cont].length; i++) {
                        matriz[cont][i] = null;
                    }
                } else {
                    for (int i = 0; i < matriz[cont].length; i++) {
                        matriz[cont][i] = (aux[i]);
                    }
                }

                cont++;
            } while ((linea = file_data.readLine()) != null);
            frn.close();
            file_data.close();

            fra1.close();
            size.close();

            return matriz;
        } catch (Exception e) {
            return null;
        }
    }
}
