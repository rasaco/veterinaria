/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableColumn<Propietario, String> tcNombre, tcMascota, tcTelefono1, tcTelefono2, tcEmail;

    @FXML
    private TableView<Propietario> tbPropietario;

    private final ObservableList<Propietario> data = FXCollections.observableArrayList();

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

    private void getActions() {

        txtBusquedaDirecta.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCharacter().equals("\r")) {
                    //Busca al/los  usuario/s en la BBDD y los carga en la tabla.
                    cargaTabla();
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

    //Comprueba que radioButton esta pulsado y envia a conexionTabla la sentencia propia.
    private void cargaTabla() {
        //Caso de que esté pulsado mascota
        if (((RadioButton) rgButton.getSelectedToggle()).getText().equals(rdbMascota.getText())) {
            conexionTabla("SELECT p.codprop, p.nombre,m.nombre, tel1, tel2, email FROM propietario p,mascota m WHERE p.codprop = m.codprop AND m.nombre LIKE '%"+txtBusquedaDirecta.getText()+"%';"); //Caso de que esté pulsado propietario
        } else if (((RadioButton) rgButton.getSelectedToggle()).getText().equals(rdbPropietario.getText())) {
            conexionTabla("SELECT p.codprop, p.nombre, m.nombre, tel1, tel2, email FROM propietario p,mascota m WHERE p.codprop = m.codprop AND p.nombre LIKE '%"+txtBusquedaDirecta.getText()+"%';");
        }
    }

    //Conecta con la BBDD y selecciona a todos los usuarios o animales indicados en el TextField.
    private void conexionTabla(String sentencia) {
        data.clear();
        Connection conexion;
        try {

            conexion = DBConnector.connect();
            // Consulta
            Statement s = conexion.createStatement();
            ResultSet res;
            res = s.executeQuery(sentencia);
            //Recorremos el resultado para visualizar cada fila
            while (res.next()) {
                data.add(new Propietario(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6)));
            }
            res.close();
            s.close();
            conexion.close();
            if (!data.isEmpty()) {
                tbPropietario.setItems(data);
            } else {
                tbPropietario.setItems(null);
            }
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
        tcMascota.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Propietario, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Propietario, String> cellData) {
                return cellData.getValue().mascota;
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
        private final SimpleStringProperty mascota;
        private final SimpleStringProperty tel1;
        private final SimpleStringProperty tel2;
        private final SimpleStringProperty email;

        private Propietario(String codProp, String nombre, String mascota, String tel1, String tel2, String email) throws SQLException {
            this.codProp = new SimpleStringProperty(codProp);
            this.nombre = new SimpleStringProperty(nombre);
            this.mascota = new SimpleStringProperty(mascota);
            this.tel1 = new SimpleStringProperty(tel1);
            this.tel2 = new SimpleStringProperty(tel2);
            this.email = new SimpleStringProperty(email);
        }

    }
}
