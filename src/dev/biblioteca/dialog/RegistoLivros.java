package dev.biblioteca.dialog;

import dev.biblioteca.Livro;
import dev.biblioteca.bd.LigaBD;
import dev.biblioteca.bd.LigaBD.LivroEstado;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegistoLivros extends javax.swing.JDialog {
    private int selectedRow = -1;
    private Runnable updateFunc;
    
    /**
     * Creates new form RegistroLivros
     */
    public RegistoLivros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.load();
        this.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = table.getSelectedRow();
                updateButtons();
             }
            
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                LigaBD.LOGIN_STATUS_CHANGE_EVENT.unregister(RegistoLivros.this::onLoginStatusChange);
            }
            
        });
        this.updateFunc = () -> {
            this.load();
            this.updateButtons();
        };
        LigaBD.LIVROS_UPDATE_EVENT.register(this.updateFunc);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                LigaBD.LIVROS_UPDATE_EVENT.unregister(updateFunc);
            }
        });
        LigaBD.LOGIN_STATUS_CHANGE_EVENT.register(this::onLoginStatusChange);
        this.onLoginStatusChange();
        this.removeAllButton.setVisible(false);
    }
    
    private void onLoginStatusChange() {
        this.addButton.setEnabled(LigaBD.FUNCIONARIO_LOGGED);
        this.removeAllButton.setEnabled(LigaBD.FUNCIONARIO_LOGGED);
    }
    
    private void updateButtons() {
        if (selectedRow < 0) {
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String titulo = (String) model.getValueAt(selectedRow, 0);

        Livro livro = LigaBD.getBD().buscarLivroPorTitulo(titulo).get();

        if (LigaBD.FUNCIONARIO_LOGGED && livro.isDisponivel()) {
            removeLivroButton.setEnabled(true);
        } else {
            removeLivroButton.setEnabled(false);
        }

        if (LigaBD.LOGGED_LEITOR != null && livro.isDisponivel()) {
            requisitarButton.setEnabled(true);
        } else {
            requisitarButton.setEnabled(false);
        }

        if (LigaBD.LOGGED_LEITOR != null && LigaBD.LOGGED_LEITOR.hasLivro(livro) && !livro.isDisponivel()) {
            devolverButton.setEnabled(true);
        } else {
            devolverButton.setEnabled(false);
        }
    }
    
    private void load() {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        this.clearTableData(model);
        for (Livro livro : LigaBD.getBD().obterLivros()) {
            model.addRow(new Object[] { livro.getTitulo(), livro.getAutor(), livro.getEditora(), livro.getAnolancamento(), livro.isDisponivel() ? "Disponível" : "Indisponível" });
        }
        if (this.selectedRow != -1) table.setRowSelectionInterval(this.selectedRow, this.selectedRow);
    }
    
    private void clearTableData(DefaultTableModel tableModel) {
        for (int rowCount = tableModel.getRowCount(), i = rowCount - 1; i >= 0; --i) {
            tableModel.removeRow(i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        removeAllButton = new javax.swing.JButton();
        removeLivroButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        requisitarButton = new javax.swing.JButton();
        devolverButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registo de Livros");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Título", "Autor", "Editora", "Ano de Lançamento", "Disponibilidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jScrollPane1.setViewportView(table);

        removeAllButton.setText("Remover Todos");
        removeAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllButtonActionPerformed(evt);
            }
        });

        removeLivroButton.setText("Remover");
        removeLivroButton.setEnabled(false);
        removeLivroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLivroButtonActionPerformed(evt);
            }
        });

        addButton.setText("Adicionar");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        requisitarButton.setText("Requisitar");
        requisitarButton.setEnabled(false);
        requisitarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requisitarButtonActionPerformed(evt);
            }
        });

        devolverButton.setText("Devolver");
        devolverButton.setEnabled(false);
        devolverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                devolverButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(removeAllButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(requisitarButton)
                        .addGap(3, 3, 3)
                        .addComponent(devolverButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeLivroButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeAllButton)
                    .addComponent(removeLivroButton)
                    .addComponent(addButton)
                    .addComponent(requisitarButton)
                    .addComponent(devolverButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeLivroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLivroButtonActionPerformed
        String[] opcoes = {"Continuar", "Cancelar"};
        int resposta = JOptionPane.showOptionDialog(this, "Tem certeza de que deseja realizar esta ação?", "Confirmação", 
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);
        if (resposta == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            LigaBD.getBD().excluirLivro((String) model.getValueAt(this.selectedRow, 0));
            this.selectedRow = -1;
            LigaBD.ACTION_MESSAGE_EVENT.invoker().run("Removido livro '" + ((String) model.getValueAt(this.selectedRow, 0)) + "'");
            model.removeRow(this.selectedRow);
            this.removeLivroButton.setEnabled(false);
        }
    }//GEN-LAST:event_removeLivroButtonActionPerformed

    private void removeAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllButtonActionPerformed
        String[] opcoes = {"Continuar", "Cancelar"};
        int resposta = JOptionPane.showOptionDialog(this, "Tem certeza de que deseja realizar esta ação?", "Confirmação", 
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);
        if (resposta == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int rowCount = model.getRowCount(), i = rowCount - 1; i >= 0; --i) {
                LigaBD.getBD().excluirLivro((String) model.getValueAt(i, 0));
                model.removeRow(i);
            }
            LigaBD.ACTION_MESSAGE_EVENT.invoker().run("Removidos todos os livros");
        }
    }//GEN-LAST:event_removeAllButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        new AdicionarLivro(null, true).setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void requisitarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requisitarButtonActionPerformed
        if (LigaBD.LOGGED_LEITOR != null) {              
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String titulo = (String) model.getValueAt(selectedRow, 0);
            Optional<Livro> livro = LigaBD.getBD().buscarLivroPorTitulo(titulo);
            
            livro.ifPresent((vl) -> {
                vl.setDisponivel(false);
                LigaBD.LOGGED_LEITOR.addLivro(vl);
                LigaBD.getBD().atualizarEstadoLivro(vl.getTitulo(), LivroEstado.INDISPONIVEL);
            });
            
            LigaBD.LIVROS_UPDATE_EVENT.invoker().run();
            LigaBD.ACTION_MESSAGE_EVENT.invoker().run("Requesitado '" + titulo + "' por " + LigaBD.LOGGED_LEITOR.getNome());
        }
    }//GEN-LAST:event_requisitarButtonActionPerformed

    private void devolverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_devolverButtonActionPerformed
        if (LigaBD.LOGGED_LEITOR != null) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String titulo = (String) model.getValueAt(selectedRow, 0);
            Optional<Livro> livro = LigaBD.getBD().buscarLivroPorTitulo(titulo);
            
            livro.ifPresent(vl -> {
                vl.setDisponivel(true);
                LigaBD.LOGGED_LEITOR.removeLivro(vl);
                LigaBD.getBD().atualizarEstadoLivro(vl.getTitulo(), LivroEstado.DISPONIVEL);

            });
            
            LigaBD.LIVROS_UPDATE_EVENT.invoker().run();
            LigaBD.ACTION_MESSAGE_EVENT.invoker().run("Devolvido '" + titulo + "' por " + LigaBD.LOGGED_LEITOR.getNome());
        }  
    }//GEN-LAST:event_devolverButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RegistoLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistoLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistoLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistoLivros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegistoLivros dialog = new RegistoLivros(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addButton;
    private javax.swing.JButton devolverButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeAllButton;
    private javax.swing.JButton removeLivroButton;
    private javax.swing.JButton requisitarButton;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
