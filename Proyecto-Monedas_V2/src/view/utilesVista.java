/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author wilson
 */
public class utilesVista {
    public static void cargarNroDatos(JComboBox cbx, Integer cant){
        cbx.removeAllItems();
        for (int i = 1; i <= cant; i++) {
            cbx.addItem(i);
        }
    }
    
    public static void ajustarColumnas(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int col = 0; col < table.getColumnCount(); col++) {
            int anchoMaximo = 10; // Ancho mínimo inicial
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, col);
                Component comp = table.prepareRenderer(renderer, row, col);
                anchoMaximo = Math.max(comp.getPreferredSize().width, anchoMaximo);
            }
            columnModel.getColumn(col).setPreferredWidth(anchoMaximo + 4); // Añade margen extra
        }
    }
}
