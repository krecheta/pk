/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.Controller;
import View.View;
import Model.Model;


/**
 *
 * @author edodomi
 */
public class Kryptografia_zadanie1_MVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(view, model);
        view.setVisible(true);
    }
    
}
