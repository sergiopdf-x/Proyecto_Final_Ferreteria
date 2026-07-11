package com.example.ferreteria.controller;

import com.example.ferreteria.dao.ProductoDao;
import com.example.ferreteria.model.Producto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ProductoController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtMarca;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;

    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Integer> colId;
    @FXML private TableColumn<Producto, String> colCodigo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, String> colMarca;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;

    private final ProductoDao productoDao = new ProductoDao();
    private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    private Producto productoSeleccionado;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());
        colCodigo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCodigoBarra()));
        colNombre.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colMarca.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMarca()));
        colPrecio.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getPrecio()).asObject());
        colStock.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getStock()).asObject());

        tablaProductos.setItems(listaProductos);
        tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, viejo, nuevo) -> {
            if (nuevo != null) cargarEnFormulario(nuevo);
        });

        cargarProductos();
    }

    private void cargarProductos() {
        try {
            listaProductos.setAll(productoDao.listarTodo());
        } catch (Exception e) {
            alerta("Error", "No se pudo cargar el inventario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cargarEnFormulario(Producto p) {
        productoSeleccionado = p;
        txtCodigo.setText(p.getCodigoBarra());
        txtNombre.setText(p.getNombre());
        txtMarca.setText(p.getMarca());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
    }

    private boolean validarCampos() {
        if (txtCodigo.getText().isBlank() || txtNombre.getText().isBlank()) {
            alerta("Campos vacios", "Codigo y nombre son obligatorios", Alert.AlertType.WARNING);
            return false;
        }
        try {
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());
            if (precio <= 0) {
                alerta("Dato invalido", "El precio debe ser mayor a 0", Alert.AlertType.WARNING);
                return false;
            }
            if (stock < 0) {
                alerta("Dato invalido", "El stock no puede ser negativo", Alert.AlertType.WARNING);
                return false;
            }
        } catch (NumberFormatException e) {
            alerta("Dato invalido", "Precio y stock deben ser numericos", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    @FXML
    private void registrarProducto() {
        if (!validarCampos()) return;
        try {
            Producto p = new Producto(0, txtCodigo.getText().trim(), txtNombre.getText().trim(),
                    txtMarca.getText().trim(), Double.parseDouble(txtPrecio.getText().trim()),
                    Integer.parseInt(txtStock.getText().trim()));
            if (productoDao.registrar(p)) {
                alerta("Exito", "Producto registrado", Alert.AlertType.INFORMATION);
                limpiarCampos();
                cargarProductos();
            }
        } catch (Exception e) {
            alerta("Error", "No se pudo registrar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void modificarProducto() {
        if (productoSeleccionado == null) {
            alerta("Sin seleccion", "Seleccione un producto de la tabla", Alert.AlertType.WARNING);
            return;
        }
        if (!validarCampos()) return;
        try {
            productoSeleccionado.setCodigoBarra(txtCodigo.getText().trim());
            productoSeleccionado.setNombre(txtNombre.getText().trim());
            productoSeleccionado.setMarca(txtMarca.getText().trim());
            productoSeleccionado.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
            productoSeleccionado.setStock(Integer.parseInt(txtStock.getText().trim()));
            if (productoDao.modificar(productoSeleccionado)) {
                alerta("Exito", "Producto actualizado", Alert.AlertType.INFORMATION);
                limpiarCampos();
                cargarProductos();
            }
        } catch (Exception e) {
            alerta("Error", "No se pudo modificar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarProducto() {
        if (productoSeleccionado == null) {
            alerta("Sin seleccion", "Seleccione un producto de la tabla", Alert.AlertType.WARNING);
            return;
        }
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Desea eliminar el producto " + productoSeleccionado.getNombre() + "?");
        confirmacion.showAndWait().ifPresent(resp -> {
            if (resp == ButtonType.OK) {
                try {
                    productoDao.eliminar(productoSeleccionado);
                    limpiarCampos();
                    cargarProductos();
                } catch (java.sql.SQLException e) {
                    if ("23503".equals(e.getSQLState())) {
                        alerta("No se puede eliminar",
                                "Este producto ya tiene ventas registradas y no puede eliminarse, "
                                        + "porque se perdería el historial de esas ventas.\n\n"
                                        + "Si ya no se vende, puedes dejarlo con stock en 0 en lugar de borrarlo.",
                                Alert.AlertType.WARNING);
                    } else {
                        alerta("Error", "No se pudo eliminar: " + e.getMessage(), Alert.AlertType.ERROR);
                    }
                } catch (Exception e) {
                    alerta("Error", "No se pudo eliminar: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });
    }

    @FXML
    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtMarca.clear();
        txtPrecio.clear();
        txtStock.clear();
        productoSeleccionado = null;
        tablaProductos.getSelectionModel().clearSelection();
    }

    @FXML
    private void volverDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ferreteria/dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tablaProductos.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            alerta("Error", "No se pudo volver: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void alerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert a = new Alert(tipo);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }
}
