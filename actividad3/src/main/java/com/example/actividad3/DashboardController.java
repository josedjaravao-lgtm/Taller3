package com.example.actividad3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    // Nombres de campos FXML correctos (camelCase)
    @FXML
    private VBox principalVBox;
    @FXML
    private TableColumn<Elemento, String> codigoCol;
    @FXML
    private TableColumn<Elemento, String> productoCol;
    @FXML
    private TableColumn<Elemento, String> descripcionCol;
    @FXML
    private TableColumn<Elemento, Double> precioCol;
    @FXML
    private TableColumn<Elemento, Integer> cantidadCol;
    @FXML
    private TableView<Elemento> datosTableView; // Corregido el tipo de dato de Elementos a Elemento

    private ObservableList<Elemento> elementos = FXCollections.observableArrayList();

    // Uso de Singleton para el repositorio
    private ProductRepository productRepository;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicialización del Singleton del repositorio
        this.productRepository = ProductRepository.getInstance();

        // Configuración de las columnas (propiedad en Elemento debe ser 'codigo', 'producto', etc.)
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        codigoCol.setStyle("-fx-alignment: CENTER-LEFT");

        productoCol.setCellValueFactory(new PropertyValueFactory<>("producto"));
        productoCol.setStyle("-fx-alignment: CENTER-LEFT");

        descripcionCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        descripcionCol.setStyle("-fx-alignment: CENTER-LEFT");

        precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        precioCol.setStyle("-fx-alignment: CENTER-LEFT");

        cantidadCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cantidadCol.setStyle("-fx-alignment: CENTER-LEFT");

        // Formato de celda para el precio
        precioCol.setCellFactory(column -> new TableCell<Elemento, Double>() {
            @Override
            protected void updateItem(Double precio, boolean empty) {
                super.updateItem(precio, empty);
                if (empty || precio == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", precio));
                }
            }
        });

        // Carga inicial de datos
        cargarProductos();
    }

    public void cargarProductos() {
        elementos.clear();

        // Verifica que el repositorio exista antes de intentar usarlo
        if (productRepository != null) {
            elementos.addAll(productRepository.getProductos());
            datosTableView.setItems(elementos);
        }
    }

    @FXML
    private void onEliminarProducto() {
        Elemento productoSeleccionado = datosTableView.getSelectionModel().getSelectedItem();

        if (productoSeleccionado == null) {
            mostrarAlerta("Advertencia", "Por favor, seleccione un producto para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar el producto?");
        confirmacion.setContentText("Producto: " + productoSeleccionado.getProducto());

        confirmacion.showAndWait().ifPresent(response -> {
            // Asegura que el repositorio no sea nulo antes de la operación
            if (response == ButtonType.OK && productRepository != null) {
                // Llama al método de instancia
                productRepository.eliminarProducto(productoSeleccionado);
                cargarProductos();
                mostrarAlerta("Éxito", "Producto eliminado correctamente.", Alert.AlertType.INFORMATION);
            }
        });
    }

    // Método auxiliar genérico para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    public void onCancelar(ActionEvent actionEvent) {
        volverAlMenu();
    }

    private void volverAlMenu() {
        try {
            // Navegación FXML correcta
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent menuView = loader.load();

            principalVBox.getChildren().clear();
            principalVBox.getChildren().add(menuView);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo volver al Menú Principal (Menu.fxml).", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public VBox getPrincipalVBox() {
        return principalVBox;
    }
}