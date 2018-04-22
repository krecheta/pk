/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.Controller;
import Model.BigInt;
import View.View;
import Model.Model;
import java.io.UnsupportedEncodingException;


/**
 *
 * @author edodomi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        // TODO code application logic here
        BigInt number_of_chars = new BigInt("1");
        int accuracyMilerTest =20;
     
        Model model = new Model(number_of_chars, accuracyMilerTest);
       View view = new View();
        Controller controller = new Controller(view, model);
        view.setVisible(true);
     /* ///  model.choose_P_AND_Q();
        //model.encode("a".getBytes());
      
        model.choose_P_AND_Q();
              System.out.println("zaczynamy 1\n ");

       model.extendedAlghoritmEuklidesa(model.getP(), model.getQ());
      // model.decode(model.getEncodedText().toString());
      */  
    


    }
    
}
