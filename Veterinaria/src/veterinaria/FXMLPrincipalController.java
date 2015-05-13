/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Carlos & Raul
 */
public class FXMLPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private MainApp ProgramaPrincipal;

    @FXML
    private Button btnCita, btnMascota, btnPropietario;
    @FXML
    private RadioButton rdbPropietario, rdbMascota;
    @FXML
    private TextField txtBusquedaDirecta;

    @FXML
    private ImageView imgCerrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnCita.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                accionCita();
            }
        });
        imgCerrar.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ProgramaPrincipal.cerrarVentana();
            }
        });
    }

    private void accionCita() {
        ProgramaPrincipal.esconderVentana();
        ProgramaPrincipal.pantallaCita();
    }

    public void setProgramaPrincipal(MainApp aThis) {
        ProgramaPrincipal = aThis;
    }

    public void cerrar() {
        ProgramaPrincipal.cerrarVentana();
    }

    public void esconder() {
        ProgramaPrincipal.esconderVentana();
    }
}
