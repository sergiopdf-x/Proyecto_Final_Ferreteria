package com.example.ferreteria.controller;

import com.example.ferreteria.dao.ProductoDao;
import com.example.ferreteria.dao.VentaDao;
import com.example.ferreteria.model.Producto;
import com.example.ferreteria.model.Venta;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ReportesController {

    @FXML
    private Label lblTotalVendido;
    @FXML private TableView<Venta> tablaReporte;
    @FXML private TableColumn<Venta, Integer> colId;
    @FXML private TableColumn<Venta, String> colProducto;
    @FXML private TableColumn<Venta, Integer> colCantidad;
    @FXML private TableColumn<Venta, Double> colTotal;
    @FXML private TableColumn<Venta, String> colFecha;

    private final VentaDao ventaDao = new VentaDao();
    private final ProductoDao productoDao = new ProductoDao();
    private final Map<Integer, Producto> productosPorId = new HashMap<>();

    @FXML
    public void initialize() {
        try {
            productoDao.listarTodo().forEach(p -> productosPorId.put(p.getId(), p));
        } catch (Exception ignored) { }

        colId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());
        colProducto.setCellValueFactory(d -> {
            Producto p = productosPorId.get(d.getValue().getProductoId());
            return new SimpleStringProperty(p != null ? p.getNombre() : "N/D");
        });
        colCantidad.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getCantidad()).asObject());
        colTotal.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getTotal()).asObject());
        colFecha.setCellValueFactory(d -> new SimpleStringProperty(
                d.getValue().getFechaVenta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));

        try {
            tablaReporte.setItems(FXCollections.observableArrayList(ventaDao.listarTodo()));
            lblTotalVendido.setText(String.format("Total vendido: $%.2f", ventaDao.totalVendido()));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "No se pudo cargar el reporte: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void volverDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ferreteria/dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tablaReporte.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "No se pudo volver: " + e.getMessage()).showAndWait();
        }
    }
}
