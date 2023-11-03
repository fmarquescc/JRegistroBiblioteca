package dev.biblioteca.dialog;
import dev.biblioteca.LanguageManager;
import dev.biblioteca.Leitor;
import dev.biblioteca.bd.LigaBD;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JOptionPane;
public class SignUpLeitor extends javax.swing.JDialog {
    private static final Random RANDOM = new Random();

    /**
     * Creates new form LeitorSignUp
     */
    public SignUpLeitor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.updateTranslations();
    }
    
    private void updateTranslations() {
        this.setTitle(LanguageManager.translate("menu.add_reader"));
        this.nomeLabel.setText(LanguageManager.translate("menu.reader.name"));
        this.emailLabel.setText(LanguageManager.translate("menu.reader.email"));
        this.telefoneLabel.setText(LanguageManager.translate("menu.reader.phonenumber"));
        this.utilizadorLabel.setText(LanguageManager.translate("menu.reader.username"));
        this.passwordLabel.setText(LanguageManager.translate("menu.password"));
        this.confirmPassLabel.setText(LanguageManager.translate("menu.confirm_password"));
        this.nomeLabel.setText(LanguageManager.translate("menu.reader.name"));
        this.signUpButton.setText(LanguageManager.translate("menu.register"));
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

        nomeField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        nomeLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        telefoneField = new javax.swing.JTextField();
        telefoneLabel = new javax.swing.JLabel();
        loginField = new javax.swing.JTextField();
        utilizadorLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        confirmPassLabel = new javax.swing.JLabel();
        confirmarPasswordField = new javax.swing.JPasswordField();
        passwordField = new javax.swing.JPasswordField();
        cancelButton = new javax.swing.JButton();
        signUpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar - Leitor");
        setResizable(false);

        nomeLabel.setText("Nome");

        emailLabel.setText("Email");

        telefoneLabel.setText("Telefone");

        utilizadorLabel.setText("Nome de Utilizador");

        passwordLabel.setText("Palavra-Passe");
        passwordLabel.setToolTipText("Mais de 8 caracteres");

        confirmPassLabel.setText("Confirmar Palavra-Passe");

        confirmarPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarPasswordFieldActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        signUpButton.setText("Registar");
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(confirmPassLabel)
                    .addComponent(passwordLabel)
                    .addComponent(utilizadorLabel)
                    .addComponent(telefoneLabel)
                    .addComponent(emailLabel)
                    .addComponent(nomeLabel)
                    .addComponent(confirmarPasswordField)
                    .addComponent(loginField)
                    .addComponent(telefoneField)
                    .addComponent(emailField)
                    .addComponent(nomeField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(signUpButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(nomeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(telefoneLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(utilizadorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(confirmPassLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmarPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(signUpButton))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void mostraMensagem(String campo, String mensagem) {
        JOptionPane.showMessageDialog(this, campo + ": " + mensagem, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
    }
    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        String nome = nomeField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();
        String login = loginField.getText();
        char[] senha = passwordField.getPassword();
        char[] senhaRepetida = confirmarPasswordField.getPassword();

        boolean nomeValido = false, emailValido = false, telefoneValido = false, loginValido = false, 
           passValida = false;

        int conta = 0, contaEspacos = 0;


        // Validação do nome
         if (nome.isEmpty()) {
            mostraMensagem("Nome", "Preencha o campo nome");
        } else if (nome.length() < 2) {
            mostraMensagem("Nome", "Preencha um nome válido");
        } else {
            for (int x = 0; x < nome.length(); x++) {
                if ((nome.charAt(x) >= 65 && nome.charAt(x) <= 90) || (nome.charAt(x) >= 97 && nome.charAt(x) <= 122)) {
                    conta++;
                } else if (nome.charAt(x) == ' ') {
                    contaEspacos++;
                }
            }
            if (conta < 2 || (conta + contaEspacos) < nome.length()) {
                mostraMensagem("Nome", "Preencha um nome válido");
            } else {
                nomeValido = true;
            }
        }
     
        // Validação do email
        if (nomeValido) {
            if (email.isEmpty()) {
                mostraMensagem("Email", "Preencha o campo email");
            } else if (email.indexOf('@') == -1) {
                mostraMensagem("Email", "Preencha um email válido");
            } else {
                int pos_arroba = email.indexOf('@');
                int pos_ponto = email.indexOf('.', pos_arroba + 2);
                if (pos_ponto != -1 && pos_ponto < email.length() - 1) {
                    emailValido = true;
                } else {
                    mostraMensagem("Email", "Preencha um email válido");
                }
            }
            // Validação do telefone
            if (emailValido) {
                if (telefone.isEmpty()) {
                    mostraMensagem("Telefone", "Preencha o campo telefone");
                } else {
                    if (telefone.length() < 9) {
                         mostraMensagem("Telefone", "O número de telefone deve ter pelo menos 9 dígitos");
                    } else if (!telefone.matches("[0-9]{9}")) {
                        mostraMensagem("Telefone", "O número de telefone só pode conter digitos!");
                    } else {
                        telefoneValido = true;
                    }
                }
            }
        }
       
        //Validação do login
        if (telefoneValido) {
            if (login.isEmpty()) {
                mostraMensagem("Login", "Preencha o campo login");
            } else if (login.length() < 4) {
                mostraMensagem("Login", "O login deve ter pelo menos 4 caracteres");
            } else if (login.contains(" ")) {
                mostraMensagem("Login", "O login não pode conter espaços");
            } else {
                loginValido = true;
            }
        }

        // Validação da senha
        if (loginValido) { 
            if (senha.length == 0) {
                mostraMensagem("Senha", "Preencha o campo de senha");
            } else {
                String senhaString = String.valueOf(senha);
                if (senha.length < 8) {
                    mostraMensagem("Senha", "A senha deve ter pelo menos 8 caracteres");
                } else if (!senhaString.matches(".*[a-z].*")) {
                    mostraMensagem("Senha", "A senha deve conter pelo menos uma letra minúscula");
                } else if (!senhaString.matches(".*[A-Z].*")) {
                    mostraMensagem("Senha", "A senha deve conter pelo menos uma letra maiúscula");
                } else if (!senhaString.matches(".*\\d.*")) {
                    mostraMensagem("Senha", "A senha deve conter pelo menos um dígito");
                } else if (!senhaString.matches(".*[@#$%^&+=].*")) {
                    mostraMensagem("Senha", "A senha deve conter pelo menos um caractere especial (@, #, $, %, ^, & ou +)");
                } else if (!String.valueOf(senha).equals(String.valueOf(senhaRepetida))) {
                    mostraMensagem("Senha", "As senhas não coincidem");
                } else {
                    passValida = true;
                }
            }
        }
        
        if (nomeValido && emailValido && telefoneValido && loginValido && passValida) {
            JOptionPane.showMessageDialog(this, "Campos validados", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            Leitor leitor = new Leitor(nome, RANDOM.nextInt(0, Integer.MAX_VALUE) + "", email, telefone, login, this.passwordField.getText());
            LigaBD.getBD().inserirLeitor(leitor);
            LigaBD.LIVROS_UPDATE_EVENT.invoker().run();
            LigaBD.logAsLeitor(leitor);
        }
    }//GEN-LAST:event_signUpButtonActionPerformed

    private void confirmarPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarPasswordFieldActionPerformed
        signUpButtonActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }//GEN-LAST:event_confirmarPasswordFieldActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
    
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
            java.util.logging.Logger.getLogger(SignUpLeitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpLeitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpLeitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpLeitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SignUpLeitor dialog = new SignUpLeitor(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel confirmPassLabel;
    private javax.swing.JPasswordField confirmarPasswordField;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField loginField;
    private javax.swing.JTextField nomeField;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton signUpButton;
    private javax.swing.JTextField telefoneField;
    private javax.swing.JLabel telefoneLabel;
    private javax.swing.JLabel utilizadorLabel;
    // End of variables declaration//GEN-END:variables
}
