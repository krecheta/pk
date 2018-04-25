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
        this.view.addGenerateKey(new GenerateKey());

    }
    
    class EncryptedListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            byte[] plaintTextInBytes = view.getPlainText().getBytes();
            model.encode(plaintTextInBytes); // szyfruje nasz tekst 
            byte[] encrypted = model.getEncodedText();
            view.setEncodedText(new String(encrypted));
        }
    }

        
    class DecryptedListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            model.decode(new String (model.getEncodedText()));
            byte[] decrypted = model.getDecodedText();
            view.setPlainText2(new String(decrypted));
            }
    }
    class GenerateKey implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            model.choose_P_AND_Q();
            view.setPrivateKey(model.getPrivateKey());
            view.setPublicKey(model.getPublicKey());
        }
    }
    
    class DecryptedFileListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e){             
            selectFileView = new SelectFileView();
             // Dodac sprawdzanie klucza
             
            selectFileView.setPrivateKey("P: " + model.getP().toString() + "\n" + "Q: " + model.getQ().toString());
            selectFileView.setPublicKey(model.getPublicKey());
            selectFileView.addChooseFile_1_Button(new ChooseReadFileListener());
            selectFileView.addChooseFile_2_Button(new ChooseSaveFileListener());
            selectFileView.addOkButton(new OkListenerfordecode(selectFileView));
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
            // dodac wyswietlenie 
            
            selectFileView.setPrivateKey("P: " + model.getP().toString() + "\n" + "Q: " + model.getQ().toString());
            selectFileView.setPublicKey(model.getPublicKey());
            selectFileView.addChooseFile_1_Button(new ChooseReadFileListener());
            selectFileView.addChooseFile_2_Button(new ChooseSaveFileListener());
            selectFileView.addOkButton(new OkListenerForEncode(selectFileView));
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
    
    class OkListenerForEncode implements ActionListener{
        private final SelectFileView selectFileView;
        
        public OkListenerForEncode(SelectFileView selectFileView ){
            this.selectFileView = selectFileView;        
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
              try{
                model.readFileAsBinary(selectFileView.getReadFileText());
               System.err.println("Wczytano dane " + model.getPlainText());
               
                model.encode(model.getPlainText()); // szyfruje nasz tekst 
                byte[] encrypted = model.getEncodedText();
                model.saveFileAsBinary(encrypted, selectFileView.getSaveFileText());
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
                System.err.println("Blad");
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
        class OkListenerfordecode implements ActionListener{
        private final SelectFileView selectFileView;
        
        public OkListenerfordecode(SelectFileView selectFileView ){
            this.selectFileView = selectFileView;        
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
              try{
                model.readFileAsBinary(selectFileView.getReadFileText());
                model.decode(new String (model.getPlainText()));
                byte[] decrypted = model.getDecodedText();
               
               
                //model.saveFileAsBinary(model.getKey(), "KOD.txt");  // zapisujemy klucz aby sprawdzic odkodowywanie 
                model.saveFileAsBinary(decrypted, selectFileView.getSaveFileText());
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
    

