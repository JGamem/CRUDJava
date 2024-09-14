package DatosController;

import Model.Datos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class DatosController {

    @FXML
    private TableView<Datos> tableDatos;
    @FXML
    private TableColumn<Datos, Integer> colCodigo;
    @FXML
    private TableColumn<Datos, String> colNombre;
    @FXML
    private TableColumn<Datos, String> colApellido;
    @FXML
    private TableColumn<Datos, String> colDepartamento;
    @FXML
    private TableColumn<Datos, Date> colFechaNacimiento;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtDepartamento;
    @FXML
    private DatePicker datePickerFechaNacimiento;

    private DatosDAO datosDAO;
    private ObservableList<Datos> datosList;

    @FXML
    public void initialize() {
        try {
            Connection conexion = Conexion.conectar();
            datosDAO = new DatosDAO(conexion);
            listarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listarDatos() throws SQLException {
        List<Datos> datos = datosDAO.listar();
        datosList = FXCollections.observableArrayList(datos);
        tableDatos.setItems(datosList);
    }

    @FXML
    public void agregar(MouseEvent event) throws SQLException {
        Datos datos = new Datos(
                0,
                txtNombre.getText(),
                txtApellido.getText(),
                txtDepartamento.getText(),
                Date.valueOf(datePickerFechaNacimiento.getValue())
        );
        datosDAO.agregar(datos);
        listarDatos();
    }

    @FXML
    public void actualizar(MouseEvent event) throws SQLException {
        Datos datos = tableDatos.getSelectionModel().getSelectedItem();
        if (datos != null) {
            datos.setNombre(txtNombre.getText());
            datos.setApellido(txtApellido.getText());
            datos.setDepartamento(txtDepartamento.getText());
            datos.setFechaNacimiento(Date.valueOf(datePickerFechaNacimiento.getValue()));
            datosDAO.actualizar(datos);
            listarDatos();
        }
    }

    @FXML
    public void eliminar(MouseEvent event) throws SQLException {
        Datos datos = tableDatos.getSelectionModel().getSelectedItem();
        if (datos != null) {
            datosDAO.eliminar(datos.getCodigo());
            listarDatos();
        }
    }
}
