package com.example.actividad3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


class FormularioController implements Initializable {

    private TextField txtCodigo;
    @FXML
    private TextField txtProducto;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private VBox principalVBox;


    private ProductRepository productRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.productRepository = ProductRepository.getInstance();
    }

    @FXML
    void onGuardar(ActionEvent event) {
        if (txtCodigo.getText().trim().isEmpty() || txtProducto.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty()) {
            mostrarAlerta("Error de Validación", "Los campos Código, Producto y Precio son obligatorios.", Alert.AlertType.ERROR);
            return;
        }

        try {
            String codigo = txtCodigo.getText().trim();
            String producto = txtProducto.getText().trim();
            String descripcion = txtDescripcion.getText().trim();


            double precio = Double.parseDouble(txtPrecio.getText().trim());


            int cantidad = txtCantidad.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCantidad.getText().trim());


            if (productRepository == null) {
                mostrarAlerta("Error", "El repositorio de productos no está disponible.", Alert.AlertType.ERROR);
                return;
            }


            Elemento nuevoElemento = new Elemento(codigo, producto, descripcion, precio, cantidad);

            productRepository.agregarProducto(nuevoElemento);
            mostrarAlerta("Éxito", "Producto agregado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Asegúrese de que **Precio** y **Cantidad** sean números válidos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onCancelar(ActionEvent event) {
        volverAlMenu();
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtProducto.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void volverAlMenu() {
        try {
            // Carga de la vista usando la ruta relativa al controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent menuView = loader.load();

            // Navegación correcta (usando principalVBox)
            principalVBox.getChildren().clear();
            principalVBox.getChildren().add(menuView);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo volver al Menú Principal (Menu.fxml).", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}