package dev.biblioteca;


import com.formdev.flatlaf.FlatLightLaf;
import dev.biblioteca.bd.LigaBD;
import dev.biblioteca.dialog.AdicionarLivro;
import dev.biblioteca.dialog.ChangePasswordLeitor;
import dev.biblioteca.dialog.LoginFuncionario;
import dev.biblioteca.dialog.LoginLeitor;
import dev.biblioteca.dialog.RegistoLeitores;
import dev.biblioteca.dialog.RegistoLivros;
import dev.biblioteca.dialog.SignUpLeitor;
import java.awt.Color;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author fmarques
 */
public class Biblioteca extends javax.swing.JFrame {
    /** Creates new form JRegistroBiblioteca */
    public Biblioteca() {
         try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
        }
        initComponents();
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LigaBD.ACTION_MESSAGE_EVENT.register(message -> {
            this.activityTextArea.setText(message + '\n' + this.activityTextArea.getText());
        });
        LigaBD.LOGIN_STATUS_CHANGE_EVENT.register(() -> {
            this.signupLeitorMenuItem.setVisible(LigaBD.LOGGED_LEITOR == null && !LigaBD.FUNCIONARIO_LOGGED);
            this.loginLeitorMenuItem.setVisible(LigaBD.LOGGED_LEITOR == null && !LigaBD.FUNCIONARIO_LOGGED);
            this.loginFuncMenuItem.setVisible(LigaBD.LOGGED_LEITOR == null && !LigaBD.FUNCIONARIO_LOGGED);
            this.logoutMenuItem.setVisible(LigaBD.LOGGED_LEITOR != null || LigaBD.FUNCIONARIO_LOGGED);
            this.changePassMenuItem.setVisible(LigaBD.LOGGED_LEITOR != null);
            
            if (LigaBD.FUNCIONARIO_LOGGED) {
                this.accountStatusLabel.setText("Conta: Funcionario");
                this.adicionarLivroMenuItem.setVisible(true);
            } else if (LigaBD.LOGGED_LEITOR != null) {
                this.accountStatusLabel.setText("Conta: " + LigaBD.LOGGED_LEITOR.getNome());
                this.adicionarLivroMenuItem.setVisible(false);
            } else {
                this.accountStatusLabel.setText("Sem Conta");
                this.adicionarLivroMenuItem.setVisible(false);
            }
        });
        LigaBD.LOGIN_STATUS_CHANGE_EVENT.invoker().run();
        this.jScrollPane1.getViewport().setOpaque(false);
        this.jScrollPane1.setOpaque(false);
          this.activityTextArea.setBackground(new Color(0, 0, 0,50));
        this.activityTextArea.setHighlighter( null );
        this.activityTextArea.getCaret().deinstall( this.activityTextArea );
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        accountStatusLabel = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        activityTextArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        adicionarLivroMenuItem = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        signupLeitorMenuItem = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        changePassMenuItem = new javax.swing.JMenuItem();
        loginLeitorMenuItem = new javax.swing.JMenuItem();
        loginFuncMenuItem = new javax.swing.JMenuItem();
        logoutMenuItem = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenu1.setText("jMenu1");

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 600));

        accountStatusLabel.setText("Sem Conta");

        jLayeredPane1.setMinimumSize(new java.awt.Dimension(300, 200));

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/IonLibrarySharp.png"))); // NOI18N
        jLabel1.setText("Biblioteca");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel1.setPreferredSize(new java.awt.Dimension(0, 0));

        activityTextArea.setEditable(false);
        activityTextArea.setColumns(20);
        activityTextArea.setForeground(new java.awt.Color(255, 255, 255));
        activityTextArea.setRows(5);
        activityTextArea.setHighlighter(null);
        activityTextArea.setMinimumSize(new java.awt.Dimension(300, 300));
        activityTextArea.setName(""); // NOI18N
        jScrollPane1.setViewportView(activityTextArea);

        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout jLayeredPane1Layout = new org.jdesktop.layout.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 487, Short.MAX_VALUE)
            .add(jLayeredPane1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addContainerGap()))
            .add(jLayeredPane1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 358, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 246, Short.MAX_VALUE)
            .add(jLayeredPane1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addContainerGap()))
            .add(jLayeredPane1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 234, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jMenu2.setText("Livros");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FluentBook20Filled (3).png"))); // NOI18N
        jMenuItem1.setText("Registo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        adicionarLivroMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FluentBookAdd20Filled.png"))); // NOI18N
        adicionarLivroMenuItem.setText("Adicionar Livro");
        adicionarLivroMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarLivroMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(adicionarLivroMenuItem);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Leitores");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PixelarticonsContact.png"))); // NOI18N
        jMenuItem3.setText("Registo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        signupLeitorMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PixelarticonsContactPlus.png"))); // NOI18N
        signupLeitorMenuItem.setText("Adicionar Leitor");
        signupLeitorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupLeitorMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(signupLeitorMenuItem);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Conta");

        changePassMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TablerPasswordUser.png"))); // NOI18N
        changePassMenuItem.setText("Mudar Palavra-Passe");
        changePassMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(changePassMenuItem);

        loginLeitorMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MaterialSymbolsPasskeyOutline.png"))); // NOI18N
        loginLeitorMenuItem.setText("Entrar - Leitor");
        loginLeitorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginLeitorMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(loginLeitorMenuItem);

        loginFuncMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MaterialSymbolsPasskey.png"))); // NOI18N
        loginFuncMenuItem.setText("Entrar - Funcionário");
        loginFuncMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginFuncMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(loginFuncMenuItem);

        logoutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/HeroiconsOutlineLogout.png"))); // NOI18N
        logoutMenuItem.setText("Sair");
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(logoutMenuItem);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(accountStatusLabel))
                    .add(layout.createSequentialGroup()
                        .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(1, 1, 1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(1, 1, 1)
                .add(accountStatusLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new RegistoLivros(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void adicionarLivroMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarLivroMenuItemActionPerformed
        // TODO add your handling code here:
         new AdicionarLivro(this, true).setVisible(true);
    }//GEN-LAST:event_adicionarLivroMenuItemActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        new RegistoLeitores(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void signupLeitorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupLeitorMenuItemActionPerformed
        // TODO add your handling code here:
        new SignUpLeitor(this, true).setVisible(true);

    }//GEN-LAST:event_signupLeitorMenuItemActionPerformed

    private void loginLeitorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginLeitorMenuItemActionPerformed
        // TODO add your handling code here:
        new LoginLeitor(this, true).setVisible(true);
    }//GEN-LAST:event_loginLeitorMenuItemActionPerformed

    private void loginFuncMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginFuncMenuItemActionPerformed
        // TODO add your handling code here:
        new LoginFuncionario(this, true).setVisible(true);
        
    }//GEN-LAST:event_loginFuncMenuItemActionPerformed

    private void logoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMenuItemActionPerformed
        LigaBD.logOut();
    }//GEN-LAST:event_logoutMenuItemActionPerformed

    private void changePassMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassMenuItemActionPerformed
        // TODO add your handling code here:
        new ChangePasswordLeitor(this, true).setVisible(true);
    }//GEN-LAST:event_changePassMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Biblioteca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountStatusLabel;
    private javax.swing.JTextArea activityTextArea;
    private javax.swing.JMenuItem adicionarLivroMenuItem;
    private javax.swing.JMenuItem changePassMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem loginFuncMenuItem;
    private javax.swing.JMenuItem loginLeitorMenuItem;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JMenuItem signupLeitorMenuItem;
    // End of variables declaration//GEN-END:variables

}
