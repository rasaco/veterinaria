/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author clase
 */
public class FXMLpantallaPropietarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private MainApp ProgramaPrincipal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    public void setProgramaPrincipal(MainApp aThis) {
        ProgramaPrincipal = aThis;
    }

    public void cerrar() {
        ProgramaPrincipal.cerrarVentana();
    }

    private void cierraVentana() {
        ProgramaPrincipal.cerrarVentana();
        ProgramaPrincipal.mostrarVentanaPrincipal();
    }
}
