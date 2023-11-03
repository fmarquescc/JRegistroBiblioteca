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
                this.adicionarLivroMenuItem.setVisible(true);
            } else if (LigaBD.LOGGED_LEITOR != null) {
                this.adicionarLivroMenuItem.setVisible(false);
            } else {
                this.adicionarLivroMenuItem.setVisible(false);
            }
            this.updateAccountStatusTranslations();
        });
        LigaBD.LOGIN_STATUS_CHANGE_EVENT.invoker().run();
        
        LigaBD.LANGUAGE_UPDATE_EVENT.register(this::updateTranslations);
        
        var directionGroup = new javax.swing.ButtonGroup();
        for (String[] lang : LanguageManager.LANGUAGES) {
            var langRB0 = new javax.swing.JRadioButtonMenuItem();
            langRB0.setText(lang[1]);
            directionGroup.add(langRB0);
            this.idiomaMenu.add(langRB0);
            
            if (lang[0] == LanguageManager.currentLanguage) {
                langRB0.setSelected(true);
            }
            
            langRB0.addActionListener((ev) -> {
                if (lang[0] != LanguageManager.currentLanguage) {
                    LanguageManager.switchLanguage(lang[0]);
                    LigaBD.LANGUAGE_UPDATE_EVENT.invoker().run();
                }
            });
        }
    }
    
    private void updateTranslations() {
        this.jLabel1.setText(LanguageManager.translate("menu.library"));
        this.livrosMenu.setText(LanguageManager.translate("menu.books"));
        this.leitoresMenu.setText(LanguageManager.translate("menu.readers"));
        this.contaMenu.setText(LanguageManager.translate("menu.account"));
        this.idiomaMenu.setText(LanguageManager.translate("menu.language"));
        this.registoLivrosMenuItem.setText(LanguageManager.translate("menu.registry"));
        this.adicionarLivroMenuItem.setText(LanguageManager.translate("menu.add_book"));
        this.registoLeitoresMenuItem.setText(LanguageManager.translate("menu.registry"));
        this.signupLeitorMenuItem.setText(LanguageManager.translate("menu.add_reader"));
        this.changePassMenuItem.setText(LanguageManager.translate("menu.change_password"));
        this.loginLeitorMenuItem.setText(LanguageManager.translate("menu.login_as_reader"));
        this.loginFuncMenuItem.setText(LanguageManager.translate("menu.login_as_staff"));
        this.logoutMenuItem.setText(LanguageManager.translate("menu.logout"));
        this.updateAccountStatusTranslations();
    }
    
    private void updateAccountStatusTranslations() {
        if (LigaBD.FUNCIONARIO_LOGGED) {
            this.accountStatusLabel.setText(LanguageManager.translate("menu.account_status.staff"));
        } else if (LigaBD.LOGGED_LEITOR != null) {
            this.accountStatusLabel.setText(LanguageManager.translate("menu.account_status.reader", LigaBD.LOGGED_LEITOR.getNome()));
        } else {
            this.accountStatusLabel.setText(LanguageManager.translate("menu.account_status.none"));
        }
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
        jMenuItem2 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        accountStatusLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        activityTextArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        livrosMenu = new javax.swing.JMenu();
        registoLivrosMenuItem = new javax.swing.JMenuItem();
        adicionarLivroMenuItem = new javax.swing.JMenuItem();
        leitoresMenu = new javax.swing.JMenu();
        registoLeitoresMenuItem = new javax.swing.JMenuItem();
        signupLeitorMenuItem = new javax.swing.JMenuItem();
        contaMenu = new javax.swing.JMenu();
        changePassMenuItem = new javax.swing.JMenuItem();
        loginLeitorMenuItem = new javax.swing.JMenuItem();
        loginFuncMenuItem = new javax.swing.JMenuItem();
        logoutMenuItem = new javax.swing.JMenuItem();
        idiomaMenu = new javax.swing.JMenu();

        jMenu3.setText("jMenu3");

        jMenu1.setText("jMenu1");

        jMenuItem5.setText("jMenuItem5");

        jMenuItem2.setText("jMenuItem2");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(600, 400));

        accountStatusLabel.setText("Sem Conta");

        jLabel1.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/IonLibrarySharp.png"))); // NOI18N
        jLabel1.setText("Biblioteca");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel1.setPreferredSize(new java.awt.Dimension(0, 0));

        activityTextArea.setEditable(false);
        activityTextArea.setColumns(20);
        activityTextArea.setForeground(new java.awt.Color(255, 255, 255));
        activityTextArea.setRows(5);
        activityTextArea.setHighlighter(null);
        activityTextArea.setMinimumSize(new java.awt.Dimension(300, 300));
        activityTextArea.setName(""); // NOI18N
        jScrollPane1.setViewportView(activityTextArea);

        livrosMenu.setText("Livros");

        registoLivrosMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FluentBook20Filled (3).png"))); // NOI18N
        registoLivrosMenuItem.setText("Registo");
        registoLivrosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registoLivrosMenuItemActionPerformed(evt);
            }
        });
        livrosMenu.add(registoLivrosMenuItem);

        adicionarLivroMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FluentBookAdd20Filled.png"))); // NOI18N
        adicionarLivroMenuItem.setText("Adicionar Livro");
        adicionarLivroMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarLivroMenuItemActionPerformed(evt);
            }
        });
        livrosMenu.add(adicionarLivroMenuItem);

        jMenuBar1.add(livrosMenu);

        leitoresMenu.setText("Leitores");

        registoLeitoresMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PixelarticonsContact.png"))); // NOI18N
        registoLeitoresMenuItem.setText("Registo");
        registoLeitoresMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registoLeitoresMenuItemActionPerformed(evt);
            }
        });
        leitoresMenu.add(registoLeitoresMenuItem);

        signupLeitorMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PixelarticonsContactPlus.png"))); // NOI18N
        signupLeitorMenuItem.setText("Adicionar Leitor");
        signupLeitorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupLeitorMenuItemActionPerformed(evt);
            }
        });
        leitoresMenu.add(signupLeitorMenuItem);

        jMenuBar1.add(leitoresMenu);

        contaMenu.setText("Conta");

        changePassMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TablerPasswordUser.png"))); // NOI18N
        changePassMenuItem.setText("Mudar Palavra-Passe");
        changePassMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassMenuItemActionPerformed(evt);
            }
        });
        contaMenu.add(changePassMenuItem);

        loginLeitorMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MaterialSymbolsPasskeyOutline.png"))); // NOI18N
        loginLeitorMenuItem.setText("Entrar - Leitor");
        loginLeitorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginLeitorMenuItemActionPerformed(evt);
            }
        });
        contaMenu.add(loginLeitorMenuItem);

        loginFuncMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MaterialSymbolsPasskey.png"))); // NOI18N
        loginFuncMenuItem.setText("Entrar - Funcionário");
        loginFuncMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginFuncMenuItemActionPerformed(evt);
            }
        });
        contaMenu.add(loginFuncMenuItem);

        logoutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/HeroiconsOutlineLogout.png"))); // NOI18N
        logoutMenuItem.setText("Sair");
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        contaMenu.add(logoutMenuItem);

        jMenuBar1.add(contaMenu);

        idiomaMenu.setText("Idioma");
        jMenuBar1.add(idiomaMenu);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(accountStatusLabel))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(15, 15, 15)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 363, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(0, 0, Short.MAX_VALUE))
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))))
                .add(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(accountStatusLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registoLivrosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registoLivrosMenuItemActionPerformed
        // TODO add your handling code here:
        new RegistoLivros(this, true).setVisible(true);
    }//GEN-LAST:event_registoLivrosMenuItemActionPerformed

    private void adicionarLivroMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarLivroMenuItemActionPerformed
        // TODO add your handling code here:
         new AdicionarLivro(this, true).setVisible(true);
    }//GEN-LAST:event_adicionarLivroMenuItemActionPerformed

    private void registoLeitoresMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registoLeitoresMenuItemActionPerformed
        // TODO add your handling code here:
        new RegistoLeitores(this, true).setVisible(true);
    }//GEN-LAST:event_registoLeitoresMenuItemActionPerformed

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
    private javax.swing.JMenu contaMenu;
    private javax.swing.JMenu idiomaMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu leitoresMenu;
    private javax.swing.JMenu livrosMenu;
    private javax.swing.JMenuItem loginFuncMenuItem;
    private javax.swing.JMenuItem loginLeitorMenuItem;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JMenuItem registoLeitoresMenuItem;
    private javax.swing.JMenuItem registoLivrosMenuItem;
    private javax.swing.JMenuItem signupLeitorMenuItem;
    // End of variables declaration//GEN-END:variables

}
