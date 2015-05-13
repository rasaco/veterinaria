package veterinaria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author clase
 */
public class MainApp extends Application {

    private Stage stagePrincipal;
    private AnchorPane rootPane;

    public String usuario;

    @Override
    public void start(Stage stage) throws Exception {
        this.stagePrincipal = stage;
        mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXMLPrincipal.fxml"));
            rootPane = (AnchorPane) loader.load();
            Scene scene = new Scene(rootPane);
            stagePrincipal.initStyle(StageStyle.UNDECORATED);
            stagePrincipal.setTitle("Veterinaria Main");
            stagePrincipal.setScene(scene);
            FXMLPrincipalController controller = loader.getController();
            controller.setProgramaPrincipal(this);
            //   stagePrincipal.show();

        } catch (IOException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, e);
        }
        stagePrincipal.show();

    }

    public void pantallaPropietario() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXMLpantallaPropietario.fxml"));
            AnchorPane ventanaEmpleado = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Control Horario");
            ventana.initStyle(StageStyle.UNDECORATED);
//ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaEmpleado);
            ventana.setScene(scene);
            FXMLpantallaPropietarioController controller = loader.getController();
            controller.setProgramaPrincipal(this);
            ventana.show();
            stagePrincipal = ventana;

        } catch (IOException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void pantallaMascota() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXMLpantallaMascota.fxml"));
            AnchorPane ventanaAdmin = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Control Horario");
            ventana.initStyle(StageStyle.UNDECORATED);
            //ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaAdmin);
            ventana.setScene(scene);
            FXMLpantallaMascotaController controller = loader.getController();
            controller.setProgramaPrincipal(this);
            ventana.show();
            stagePrincipal = ventana;

        } catch (IOException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void pantallaCita() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("FXMLpantallaCita.fxml"));
            AnchorPane ventanaCambioPass = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Cambiar contraseña");
            ventana.initStyle(StageStyle.UNDECORATED);
            //ventana.initModality(Modality.WINDOW_MODAL);
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaCambioPass);
            ventana.setScene(scene);
            FXMLpantallaCitaController controller = loader.getController();
            controller.setProgramaPrincipal(this);
            Platform.setImplicitExit(false);

            /* stagePrincipal.setOnCloseRequest(new EventHandler<WindowEvent>() {
             @Override
             public void handle(WindowEvent event) {
             event.consume();
             mostrarVentanaPrincipal();
             }
             });
             */
            ventana.show();
            stagePrincipal = ventana;

        } catch (IOException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void cerrarVentana() {
        stagePrincipal.close();
    }

    public void esconderVentana() {
        stagePrincipal.hide();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the application can not be launched through deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
