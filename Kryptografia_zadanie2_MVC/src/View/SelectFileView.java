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
public class SelectFileView extends javax.swing.JFrame {

    /**
     * Creates new form Information
     */
    public SelectFileView() {
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

        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        chooseFile_1 = new javax.swing.JButton();
        keyRadio = new javax.swing.JRadioButton();
        keyButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        readFileText = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        saveFileText = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        keyPath = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        choseFile_2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        privateKey = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        publicKey = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ok.setText("Ok");

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        chooseFile_1.setText("Choose File");

        keyRadio.setText("Generate key dynamically");
        keyRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyRadioActionPerformed(evt);
            }
        });

        keyButton.setText("Choose File");

        readFileText.setColumns(20);
        readFileText.setRows(5);
        jScrollPane1.setViewportView(readFileText);

        saveFileText.setColumns(20);
        saveFileText.setRows(5);
        jScrollPane2.setViewportView(saveFileText);

        keyPath.setColumns(20);
        keyPath.setRows(5);
        jScrollPane3.setViewportView(keyPath);

        jLabel4.setText("Read file");

        jLabel5.setText("Save file");

        jLabel6.setText("File with Key");

        choseFile_2.setText("Choose File");

        jLabel1.setText("Private key");

        jLabel2.setText("Public key");

        privateKey.setEditable(false);
        privateKey.setBackground(new java.awt.Color(212, 212, 212));
        privateKey.setColumns(20);
        privateKey.setRows(5);
        jScrollPane4.setViewportView(privateKey);

        publicKey.setEditable(false);
        publicKey.setBackground(new java.awt.Color(212, 212, 212));
        publicKey.setColumns(20);
        publicKey.setRows(5);
        jScrollPane5.setViewportView(publicKey);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(choseFile_2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chooseFile_1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(keyRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(ok))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(keyButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancel, keyButton, ok});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane2, jScrollPane3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane4, jScrollPane5});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(chooseFile_1)))
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(choseFile_2)
                        .addGap(9, 9, 9)))
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keyButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(ok)
                    .addComponent(keyRadio))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chooseFile_1, keyButton});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2, jScrollPane3});

        setSize(new java.awt.Dimension(646, 455));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void keyRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keyRadioActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelActionPerformed
    
     public void setPublicKey(String text){
       publicKey.setText(text);
    }
    public void setPrivateKey(String text){
        privateKey.setText(text);
    }
    public void addKeyRadio(ActionListener listener){
        keyRadio.addActionListener(listener);
    }
     
    public void addKeyButton(ActionListener listener){
        keyButton.addActionListener(listener);
    }

    public void addChooseFile_1_Button(ActionListener listener){
       chooseFile_1.addActionListener(listener);
    }
       
    public void addChooseFile_2_Button(ActionListener listener){
        choseFile_2.addActionListener(listener);
    }
       
    public void addCancelButton(ActionListener listener){
        cancel.addActionListener(listener);
    }
       
    public void addOkButton(ActionListener listener){
        ok.addActionListener(listener);
    }
      
    public String getReadFileText(){
        return readFileText.getText();
    }
      
    public String getSaveFileText(){
        return saveFileText.getText();
    }
  
    public void setSaveFileText(String text){
        saveFileText.setText(text);
    }   
  
    public void setReadFileText(String text){
        readFileText.setText(text);
    }
     
    public Object[] getStateKeyRadio(){
        return keyRadio.getSelectedObjects();
     }
     
    public void setKeyTextEnabled(){
        keyPath.setEnabled(true);
        keyButton.setEnabled(true);
     }
     
    public void setKeyTextDisabled(){
        keyPath.setEnabled(false);
        keyButton.setEnabled(false);
     }
     
    public void setKeyPath(String text){
        keyPath.setText(text);
     }
     
    public String getKeyPath(){
        return keyPath.getText();
     }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JButton chooseFile_1;
    private javax.swing.JButton choseFile_2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton keyButton;
    private javax.swing.JTextArea keyPath;
    private javax.swing.JRadioButton keyRadio;
    private javax.swing.JButton ok;
    private javax.swing.JTextArea privateKey;
    private javax.swing.JTextArea publicKey;
    private javax.swing.JTextArea readFileText;
    private javax.swing.JTextArea saveFileText;
    // End of variables declaration//GEN-END:variables
}
