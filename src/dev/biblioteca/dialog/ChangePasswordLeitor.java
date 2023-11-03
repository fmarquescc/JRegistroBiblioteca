/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package dev.biblioteca.dialog;

import dev.biblioteca.LanguageManager;
import dev.biblioteca.Utils;
import dev.biblioteca.bd.LigaBD;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author fmarques
 */
public class ChangePasswordLeitor extends javax.swing.JDialog {

    /**
     * Creates new form ChangePasswordLeitor
     */
    public ChangePasswordLeitor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.updateTranslations();
    }
    
    private void updateTranslations() {
        this.setTitle(LanguageManager.translate("menu.change_password"));
        this.newPassLabel.setText(LanguageManager.translate("menu.new_password"));
        this.confirmPassLabel.setText(LanguageManager.translate("menu.confirm_password"));
        this.confirmButton.setText(LanguageManager.translate("menu.confirm"));
        this.cancelButton.setText(LanguageManager.translate("menu.cancel"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        newPassLabel = new javax.swing.JLabel();
        confirmPassLabel = new javax.swing.JLabel();
        passField = new javax.swing.JPasswordField();
        confirmPassField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mudar Palavra-Passe");
        setIconImage(null);

        confirmButton.setText("Confirmar");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        newPassLabel.setText("Nova Palavra-Passe");

        confirmPassLabel.setText("Confirmar Palavra-Passe");

        confirmPassField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPassFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(157, Short.MAX_VALUE)
                        .addComponent(confirmButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(confirmPassField, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(confirmPassLabel)
                            .addComponent(newPassLabel)
                            .addComponent(passField))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(newPassLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmPassLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton)
                    .addComponent(cancelButton))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        String senha = this.passField.getText();
        String senhaRepetida = this.confirmPassField.getText();
        
        if (senha.length() < 8) {
            Utils.showErrorMessage("Erro de Validação", "Senha: A senha deve ter pelo menos 8 caracteres");
        } else if (!senha.matches(".*[a-z].*")) {
            Utils.showErrorMessage("Erro de Validação", "Senha: A senha deve conter pelo menos uma letra minúscula");
        } else if (!senha.matches(".*[A-Z].*")) {
            Utils.showErrorMessage("Erro de Validação", "Senha: A senha deve conter pelo menos uma letra maiúscula");
        } else if (!senha.matches(".*\\d.*")) {
            Utils.showErrorMessage("Erro de Validação", "Senha: A senha deve conter pelo menos um dígito");
        } else if (!senha.matches(".*[@#$%^&+=].*")) {
            Utils.showErrorMessage("Erro de Validação", "Senha: A senha deve conter pelo menos um caractere especial (@, #, $, %, ^, & ou +)");
        } else if (!String.valueOf(senha).equals(String.valueOf(senhaRepetida))) {
            Utils.showErrorMessage("Erro de Validação", "Senha: As senhas não coincidem");
        } else {
            LigaBD.LOGGED_LEITOR.setPass(senha);
            LigaBD.getBD().atualizarLeitor(LigaBD.LOGGED_LEITOR);
            LigaBD.LIVROS_UPDATE_EVENT.invoker().run();
            this.dispose();
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void confirmPassFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPassFieldActionPerformed
        confirmButtonActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }//GEN-LAST:event_confirmPassFieldActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChangePasswordLeitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePasswordLeitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePasswordLeitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePasswordLeitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChangePasswordLeitor dialog = new ChangePasswordLeitor(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPasswordField confirmPassField;
    private javax.swing.JLabel confirmPassLabel;
    private javax.swing.JLabel newPassLabel;
    private javax.swing.JPasswordField passField;
    // End of variables declaration//GEN-END:variables
}
