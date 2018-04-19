/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.SelectFileView;
import View.View;
import Model.Model;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller {

    private final View view;
    private final Model model;
    private SelectFileView selectFileView;
    
    public Controller(View view, Model model){
        this.view = view;
        this.model = model; 
        this.view.addEncryptedListener(new EncryptedListener());
        this.view.addDecryptedListener(new DecryptedListener());
        this.view.addEncryptedFileListener(new EncryptedFileListener());
        this.view.addDecryptedFileListener(new DecryptedFileListener());

    }
    
    class EncryptedListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println( "AAA " +view.getPlainText());
            byte[] plaintTextInBytes = view.getPlainText().getBytes();
            model.generateKey(plaintTextInBytes.length);
            model.xorData(plaintTextInBytes, model.getKey(), 0);
            byte[] encrypted = model.getEncodedText();
            view.setEncodedText(new String(encrypted));
        }
    }

        
    class DecryptedListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            model.xorData(model.getEncodedText(),model.getKey(), 1);
            byte[] decrypted = model.getPlainText2();
            view.setPlainText2(new String(decrypted));
            }
    }
    
    class DecryptedFileListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){             
            selectFileView = new SelectFileView();
            selectFileView.addChooseFile_1_Button(new ChooseReadFileListener());
            selectFileView.addChooseFile_2_Button(new ChooseSaveFileListener());
            selectFileView.addOkButton(new OkListener(selectFileView));
            selectFileView.addKeyRadio(new KeyRadioListener());
            selectFileView.addKeyButton(new ChooseFileWithKey());
            selectFileView.addCancelButton(new CloseWindowListener(selectFileView));
            selectFileView.setVisible(true);
        }
    }   
    
    class EncryptedFileListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){             
            selectFileView = new SelectFileView();
            selectFileView.addChooseFile_1_Button(new ChooseReadFileListener());
            selectFileView.addChooseFile_2_Button(new ChooseSaveFileListener());
            selectFileView.addOkButton(new OkListener(selectFileView));
            selectFileView.addKeyRadio(new KeyRadioListener());
            selectFileView.addKeyButton(new ChooseFileWithKey());
            selectFileView.addCancelButton(new CloseWindowListener(selectFileView));
            selectFileView.setVisible(true);
        }
    }   
 
    class ChooseReadFileListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FileChooser chooser = new FileChooser();
            String respond  = chooser.React();
            
            switch(respond){
                case "canceled": ; break;
                default:selectFileView.setReadFileText(respond);
            }      
        }
    }
    
    class ChooseFileWithKey implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            FileChooser chooser = new FileChooser();
            String respond  = chooser.React();
            
            switch(respond){
                case "canceled": ; break;
                default:selectFileView.setKeyPath(respond);
            }      
        }
    }

    class KeyRadioListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectFileView.getStateKeyRadio() == null)
                selectFileView.setKeyTextEnabled();
            else
             selectFileView.setKeyTextDisabled();
        }
   }

    class ChooseSaveFileListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FileChooser chooser = new FileChooser();
            String respond  = chooser.React();
            
            switch(respond){
                case "canceled": ; break;
                default:selectFileView.setSaveFileText(respond);
            }
        }
    }
    
    class OkListener implements ActionListener{
        private final SelectFileView selectFileView;
        
        public OkListener(SelectFileView selectFileView ){
            this.selectFileView = selectFileView;        
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.readFileAsBinary(selectFileView.getReadFileText());
                if(selectFileView.getStateKeyRadio() != null){
                    System.out.println("Klucz wygenerowano dynamicznie");
                    model.generateKey(model.getPlainText().length);
                }
                
                else{
                    model.readKeyAsBinary(selectFileView.getKeyPath());
                    if (model.getKey().length < model.getPlainText().length){
                        System.out.println("Klucz wygenerowano dynamicznie z uwagi na zbyt krótki klucz w pliku");
                        model.generateKey(model.getPlainText().length);
                    }
                }
                
                model.xorData(model.getPlainText(), model.getKey(),0);
                model.saveFileAsBinary(model.getKey(), "KOD.txt");  // zapisujemy klucz aby sprawdzic odkodowywanie 
                model.saveFileAsBinary(model.getEncodedText(),selectFileView.getSaveFileText());
                this.selectFileView.dispose();
                JOptionPane.showMessageDialog(null, "Completed successfully");
            }
            
            catch(FileNotFoundException ex){
                JOptionPane.showMessageDialog(null, "File not found " + ex.getMessage());
            }
            
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    class CloseWindowListener implements ActionListener{
       private final JFrame jframe;
       
       public CloseWindowListener(JFrame jframe){
           this.jframe = jframe;           
       }
       
       @Override
        public void actionPerformed(ActionEvent e) {
            jframe.dispose();
        }  
    }
    
    class FileChooser{
        public String React(){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setVisible(true);
            int status = fileChooser.showOpenDialog(null);  
            
            if (status == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println(selectedFile);
                System.out.println(selectedFile.getName());
                return selectedFile.toString();
            }          
         return "canceled";
       }
    }
}
    

