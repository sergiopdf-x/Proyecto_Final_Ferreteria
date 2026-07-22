package com.example.ferreteria.controller;

import com.example.ferreteria.dao.ProductoDao;
import com.example.ferreteria.dao.VentaDao;
import com.example.ferreteria.model.Producto;
import com.example.ferreteria.model.Sesion;
import com.example.ferreteria.model.Venta;
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

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class VentaController {

    @FXML private TextField txtClienteNombre;
    @FXML private TextField txtClienteCedula;
    @FXML private ComboBox<Producto> cbProducto;
    @FXML private TextField txtCantidad;
    @FXML private Label lblTotal;
    @FXML private TableView<Venta> tablaVentas;
    @FXML private TableColumn<Venta, Integer> colId;
    @FXML private TableColumn<Venta, String> colCliente;
    @FXML private TableColumn<Venta, String> colProducto;
    @FXML private TableColumn<Venta, Integer> colCantidad;
    @FXML private TableColumn<Venta, Double> colTotal;
    @FXML private TableColumn<Venta, String> colFecha;

    private final ProductoDao productoDao = new ProductoDao();
    private final VentaDao ventaDao = new VentaDao();
    private final Map<Integer, Producto> productosPorId = new HashMap<>();
    private final ObservableList<Venta> listaVentas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cargarProductos();

        colId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());
        colCliente.setCellValueFactory(d -> new SimpleStringProperty(
                d.getValue().getClienteNombre() != null ? d.getValue().getClienteNombre() : "N/D"));
        colProducto.setCellValueFactory(d -> {
            Producto p = productosPorId.get(d.getValue().getProductoId());
            return new SimpleStringProperty(p != null ? p.getNombre() : "N/D");
        });
        colCantidad.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getCantidad()).asObject());
        colTotal.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getTotal()).asObject());
        colFecha.setCellValueFactory(d -> new SimpleStringProperty(
                d.getValue().getFechaVenta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));

        tablaVentas.setItems(listaVentas);
        cargarVentas();

        txtCantidad.textProperty().addListener((obs, viejo, nuevo) -> calcularTotal());
        cbProducto.valueProperty().addListener((obs, viejo, nuevo) -> calcularTotal());
    }

    private void cargarProductos() {
        try {
            java.util.List<Producto> productos = productoDao.listarTodo();
            productosPorId.clear();
            productos.forEach(p -> productosPorId.put(p.getId(), p));
            cbProducto.setItems(FXCollections.observableArrayList(productos));
        } catch (Exception e) {
            alerta("Error", "No se pudieron cargar los productos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cargarVentas() {
        try {
            listaVentas.setAll(ventaDao.listarTodo());
        } catch (Exception e) {
            alerta("Error", "No se pudieron cargar las ventas: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void calcularTotal() {
        Producto p = cbProducto.getValue();
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (p != null && cantidad > 0) {
                lblTotal.setText(String.format("Total: $%.2f", p.getPrecio() * cantidad));
                return;
            }
        } catch (NumberFormatException ignored) { }
        lblTotal.setText("Total: $0.00");
    }

    @FXML
    private void registrarVenta() {
        String clienteNombre = txtClienteNombre.getText() == null ? "" : txtClienteNombre.getText().trim();
        String clienteCedula = txtClienteCedula.getText() == null ? "" : txtClienteCedula.getText().trim();
        if (clienteNombre.isEmpty() || clienteCedula.isEmpty()) {
            alerta("Campos vacios", "Ingrese el nombre y la cedula del cliente para la factura", Alert.AlertType.WARNING);
            return;
        }

        Producto p = cbProducto.getValue();
        if (p == null) {
            alerta("Campos vacios", "Seleccione un producto", Alert.AlertType.WARNING);
            return;
        }
        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidad.getText().trim());
        } catch (NumberFormatException e) {
            alerta("Dato invalido", "La cantidad debe ser un numero entero", Alert.AlertType.WARNING);
            return;
        }
        if (cantidad <= 0) {
            alerta("Dato invalido", "La cantidad debe ser mayor a 0", Alert.AlertType.WARNING);
            return;
        }
        if (cantidad > p.getStock()) {
            alerta("Stock insuficiente", "Solo hay " + p.getStock() + " unidades disponibles", Alert.AlertType.WARNING);
            return;
        }

        try {
            double total = p.getPrecio() * cantidad;
            Venta venta = new Venta(0, Sesion.getUsuarioActual().getId(), p.getId(), cantidad, total, null,
                    clienteNombre, clienteCedula);
            if (ventaDao.registrar(venta)) {
                // Se descuenta el stock vendido del inventario
                productoDao.actualizarStock(p.getId(), p.getStock() - cantidad);
                mostrarFactura(clienteNombre, clienteCedula, p, cantidad, total);
                txtCantidad.clear();
                txtClienteNombre.clear();
                txtClienteCedula.clear();
                cargarProductos();
                cargarVentas();
            }
        } catch (Exception e) {
            alerta("Error", "No se pudo registrar la venta: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarFactura(String clienteNombre, String clienteCedula, Producto p, int cantidad, double total) {
        String factura = "FACTURA DE VENTA\n"
                + "----------------------------------\n"
                + "Cliente: " + clienteNombre + "\n"
                + "Cedula/RUC: " + clienteCedula + "\n"
                + "Atendido por: " + Sesion.getUsuarioActual().getNombre() + "\n"
                + "----------------------------------\n"
                + "Producto: " + p.getNombre() + "\n"
                + "Cantidad: " + cantidad + "\n"
                + "Precio unitario: $" + String.format("%.2f", p.getPrecio()) + "\n"
                + "TOTAL: $" + String.format("%.2f", total) + "\n"
                + "----------------------------------\n"
                + "Gracias por su compra.";
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Venta registrada");
        a.setHeaderText("Factura generada correctamente");
        a.setContentText(factura);
        a.showAndWait();
    }

    @FXML
    private void volverDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ferreteria/dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tablaVentas.getScene().getWindow();
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
