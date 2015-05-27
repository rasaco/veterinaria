/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import veterinaria.DBConnector;
import veterinaria.MainApp;

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
    @FXML
    private ToggleGroup rgButton;

    @FXML
    private TableColumn<Propietario, String> tcNombre, tcApellidos, tcTelefono1, tcTelefono2, tcEmail;

    @FXML
    private TableView<Propietario> tbPropietario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getActions();
        inicializaTabla();
        //pruebaConexion();

    }

    private void accionMascota() {
        //Prueba de funcionamiento del RadioGroup
        //a = (RadioButton) rgButton.getSelectedToggle();
        //System.out.println("Has pulsado en " + a.getText());
        //
        ProgramaPrincipal.esconderVentana();
        ProgramaPrincipal.pantallaMascota();
    }

    private void accionCita() {
        ProgramaPrincipal.esconderVentana();
        ProgramaPrincipal.pantallaCita();
    }

    private void accionPropietario() {
        ProgramaPrincipal.esconderVentana();
        ProgramaPrincipal.pantallaPropietario();
    }

    public void setProgramaPrincipal(MainApp aThis) {
        ProgramaPrincipal = aThis;
    }

    public void esconder() {
        ProgramaPrincipal.esconderVentana();
    }

    private void cargaDesdeTxt() {
        //Caso de que esté pulsado mascota
        if (((RadioButton) rgButton.getSelectedToggle()).getText().equals(rdbMascota.getText())) {
            
            ProgramaPrincipal.parametros = rdbMascota.getText();
            ProgramaPrincipal.pantallaMascota();
        //Caso de que esté pulsado propietario
        } else if (((RadioButton) rgButton.getSelectedToggle()).getText().equals(rdbPropietario.getText())) {
            ProgramaPrincipal.parametros = rdbPropietario.getText();
            ProgramaPrincipal.pantallaPropietario();
        }
    }

    private void getActions() {

        txtBusquedaDirecta.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCharacter().equals("\r")) {
                    //System.out.println("Intro pulsado");
                    //Consigo el radiobutton pulsado
                    //Carga un Bundle con la opción elegida del radiogroup, abre la pantalla elegida y carga la ficha de propietario o mascota
                    cargaDesdeTxt();
                    event.consume();
                }

            }
        });
        btnPropietario.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                accionPropietario();
            }
        });
        btnMascota.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                accionMascota();
            }
        });
        btnCita.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                accionCita();
            }
        });
        imgCerrar.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ProgramaPrincipal.cerrarVentanaP();
            }
        });
    }

    private void pruebaConexion() {
        Connection conexion;
        try {

            conexion = DBConnector.connect();
            // Consulta
            Statement sentencia = conexion.createStatement();
            //      System.out.println(user.getText());
            //      System.out.println(password.getText());
            ResultSet res;
            res = sentencia.executeQuery("SELECT nombre from propietario");
           // sentencia.executeUpdate("INSERT INTO propietario VALUES ('"+txtBusquedaDirecta.getText()+"')");
            //Recorremos el resultada para visualizar cada fila
            while (res.next()) {
                System.out.print(res.getString(1));
            }
            res.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
        private void inicializaTabla() {
        tcNombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Propietario, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Propietario, String> cellData) {
                return cellData.getValue().nombre;
            }
        });
        tcApellidos.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Propietario, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Propietario, String> cellData) {
                return cellData.getValue().apellidos;
            }
        });

        tcTelefono1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Propietario, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Propietario, String> cellData) {
                return cellData.getValue().tel1;
            }
        });
        tcTelefono2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Propietario, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Propietario, String> cellData) {
                return cellData.getValue().tel2;
            }
        });
        tcEmail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Propietario, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Propietario, String> cellData) {
                return cellData.getValue().email;
            }
        });
    }
public class Propietario {

        private final SimpleStringProperty codProp;
        private final SimpleStringProperty nombre;
        private final SimpleStringProperty apellidos;
        private final SimpleStringProperty tel1;
        private final SimpleStringProperty tel2;
        private final SimpleStringProperty email;

        private Propietario(String codProp,String nombre,String apellidos,String tel1,String tel2,String email) throws SQLException {
            this.codProp = new SimpleStringProperty (codProp);
            this.nombre = new SimpleStringProperty (nombre);
            this.apellidos = new SimpleStringProperty (apellidos);
            this.tel1 = new SimpleStringProperty (tel1);
            this.tel2 = new SimpleStringProperty (tel2);
            this.email = new SimpleStringProperty (email);
        }

    }
}
