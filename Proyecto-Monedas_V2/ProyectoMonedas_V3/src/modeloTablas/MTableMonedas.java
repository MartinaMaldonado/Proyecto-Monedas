/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloTablas;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author wilson
 */

public class MTableMonedas extends AbstractTableModel{

    public Integer columnIcon = 4;
    public Object[][] matriz;

    public MTableMonedas(Object[][] matriz) {
        this.matriz = matriz;
    }

    
    /**
     * Método que devuelve el número de filas
     * @return int nro de filas
     */
    @Override
    public int getRowCount() {
        if(matriz==null) return 0;
        return matriz.length;
    }
    /**
     * metodo que devuelve el número de columnas
     * @return Int nro de columnas
     */
    @Override
    public int getColumnCount() {
        return 4;
        //if(matriz==null || matriz[0]==null) return 0;
        //return matriz[0].length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        
        
        if(i1==0)return i+1;
        if(i1==1)return "(" + matriz[i][1] + ") " + matriz[i][2];
        if(i1==3){
            String url = "iconos"+File.separatorChar + matriz[i][5] + ".jpg";
            ImageIcon icono = new ImageIcon(url);
            
            Image imagenRedimensionada = icono.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH);
            ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

            String textContent = "(" + (matriz[i][5]+"").toUpperCase() + ") " + matriz[i][4];
            JLabel jLabel = new JLabel(textContent, iconoRedimensionado, JLabel.LEFT);
            jLabel.setFont(new java.awt.Font("sansserif", 0, 15));
            return jLabel;
        }
        return matriz[i][i1+1];
    }

    @Override
    public String getColumnName(int i) {
        String[] aux= {"Num","Nombre", "Símbolo", "País"};
        return aux[i];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == columnIcon ? Object.class : String.class;
    }
}