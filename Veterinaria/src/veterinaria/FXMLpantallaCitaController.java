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
public class FXMLpantallaCitaController implements Initializable {

    private MainApp ProgramaPrincipal;

    @FXML
    private ImageView imgCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgCerrar.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ProgramaPrincipal.cerrarVentana();
                ProgramaPrincipal.mostrarVentanaPrincipal();
            }
        });
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
