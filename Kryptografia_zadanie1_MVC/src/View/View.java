/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionListener;

/**
 *
 * @author edodomi
 */
public class View extends javax.swing.JFrame {

    /**
     * Creates new form View
     */
    public View() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encrypt = new javax.swing.JButton();
        decrypt = new javax.swing.JButton();
        decryptFile = new javax.swing.JButton();
        encryptFile = new javax.swing.JButton();
        JscrollPanel = new javax.swing.JScrollPane();
        plainText = new javax.swing.JTextArea();
        JscrollPanel2 = new javax.swing.JScrollPane();
        encodedText = new javax.swing.JTextArea();
        JscrollPanel3 = new javax.swing.JScrollPane();
        plainText2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        encrypt.setText("Encrypt");
        encrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptActionPerformed(evt);
            }
        });

        decrypt.setText("Decrypt");

        decryptFile.setText("Decrypt file");

        encryptFile.setText("Encrypt file");
        encryptFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptFileActionPerformed(evt);
            }
        });

        plainText.setColumns(20);
        plainText.setRows(5);
        JscrollPanel.setViewportView(plainText);

        encodedText.setColumns(20);
        encodedText.setRows(5);
        JscrollPanel2.setViewportView(encodedText);

        plainText2.setColumns(20);
        plainText2.setRows(5);
        JscrollPanel3.setViewportView(plainText2);

        jLabel1.setText("Text to encrypt");

        jLabel2.setText("Text encrypted");

        jLabel3.setText("Text decrypted");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(encryptFile))
                    .addComponent(JscrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JscrollPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JscrollPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(decryptFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(encrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {decryptFile, encryptFile});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {JscrollPanel, JscrollPanel2, JscrollPanel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JscrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(encrypt)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JscrollPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(decrypt)))
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JscrollPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decryptFile)
                    .addComponent(encryptFile))
                .addGap(22, 22, 22))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {JscrollPanel, JscrollPanel2, JscrollPanel3});

        setSize(new java.awt.Dimension(759, 555));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void encryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_encryptActionPerformed

    private void encryptFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_encryptFileActionPerformed

    public void addEncryptedListener(ActionListener listener){
        encrypt.addActionListener(listener);
    }

    public void addEncryptedFileListener(ActionListener listener){
        encryptFile.addActionListener(listener);
    }

    public void addDecryptedFileListener(ActionListener listener){
        decryptFile.addActionListener(listener);
    }

    public void addDecryptedListener(ActionListener listener){
        decrypt.addActionListener(listener);
    }
    
    public String getPlainText(){
        
        return plainText.getText();
    }
    
    public String getEncodedText(){
        return encodedText.getText();
    }
    
    public void setEncodedText(String text){
        encodedText.setText(text);
    }
    
    public String getPlainText2(){
        return plainText2.getText();
    }
    
    public void setPlainText2(String text){
        plainText2.setText(text);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JscrollPanel;
    private javax.swing.JScrollPane JscrollPanel2;
    private javax.swing.JScrollPane JscrollPanel3;
    private javax.swing.JButton decrypt;
    private javax.swing.JButton decryptFile;
    private javax.swing.JTextArea encodedText;
    private javax.swing.JButton encrypt;
    private javax.swing.JButton encryptFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextArea plainText;
    private javax.swing.JTextArea plainText2;
    // End of variables declaration//GEN-END:variables
}
