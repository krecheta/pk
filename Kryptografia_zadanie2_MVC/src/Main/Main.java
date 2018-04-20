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


/**
 *
 * @author edodomi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
       // Model model = new Model();
      //  View view = new View();
      //  Controller controller = new Controller(view, model);
      //  view.setVisible(true);
      
       BigInt number_of_chars = new BigInt("1");
         BigInt blockSize = new BigInt("4");
         int accuracyMilerTest =20;
         Model model = new Model(number_of_chars, accuracyMilerTest);
         System.out.println("p lewostronny zakres" + model.getleftPRange().toString());
         System.out.println("q lewostronny zakres" + model.getleftQRange().toString());
             model.choose_P_AND_Q();
         
         System.out.println("p " + model.getP().toString());
         System.out.println("q " + model.getQ().toString());
        


    }
    
}
