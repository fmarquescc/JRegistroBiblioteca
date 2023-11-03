/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.biblioteca;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fmarques
 */
public class Utils {
    public static void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showWarnMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void clearTableContents(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int rowCount = model.getRowCount(), i = rowCount - 1; i >= 0; --i) {
            model.removeRow(i);
        }
    }
}
