package com.example.actividad3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;


public class MenuController {


    @FXML
    private VBox principalVBox;

    @FXML
    void onDashboard(ActionEvent event) {

        cargarVista("dashboard.fxml");
    }

    @FXML
    void onFormulario(ActionEvent event) {

        cargarVista("formulario.fxml");
    }

    private void cargarVista(String fxml) {
        try {

            URL fxmlLocation = getClass().getResource(fxml);


            if (fxmlLocation == null) {
                mostrarAlerta("Error de Archivo",
                        "El recurso '" + fxml + "' no se encuentra. Verifique la ubicación en 'src/main/resources/com/example/actividad3/'.",
                        Alert.AlertType.ERROR);
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent view = loader.load();

            if (principalVBox != null) {
                principalVBox.getChildren().clear();
                principalVBox.getChildren().add(view);
            } else {
                mostrarAlerta("Error de Inyección",
                        "El VBox principal (fx:id=\"principalVBox\") no está inyectado. Revise Menu.fxml.",
                        Alert.AlertType.ERROR);
            }

        } catch (IOException e) {
            mostrarAlerta("Error de Carga",
                    "Error al cargar la vista " + fxml + ": " + e.getMessage(),
                    Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}