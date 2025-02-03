/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.JComboBox;

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
    
    
}
