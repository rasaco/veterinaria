/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author clase
 */
public class FXMLpantallaMascotaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private MainApp ProgramaPrincipal;
    
    @FXML private ImageView imgCerrar2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAcciones();
      //  System.out.println(ProgramaPrincipal.getParametros());
    }    
  
    private void getAcciones(){
        imgCerrar2.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ProgramaPrincipal.cerrarVentanaS();
                ProgramaPrincipal.desocultarPrincipal();
            }
        });
    }
    
    public void setProgramaPrincipal(MainApp aThis) {
        ProgramaPrincipal = aThis;
    }

    
}
