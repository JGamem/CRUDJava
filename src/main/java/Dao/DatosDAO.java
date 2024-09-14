package Dao;

import Model.Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatosDAO {

    private Connection conexion;

    public DatosDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Datos> listar() throws SQLException {
        List<Datos> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_datos";
        Statement statement = conexion.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Datos datos = new Datos(
                    resultSet.getInt("codigo"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("departamento"),
                    resultSet.getDate("fecha_nacimiento")
            );
            lista.add(datos);
        }
        return lista;
    }

    public void agregar(Datos datos) throws SQLException {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, datos.getNombre());
        preparedStatement.setString(2, datos.getApellido());
        preparedStatement.setString(3, datos.getDepartamento());
        preparedStatement.setDate(4, datos.getFechaNacimiento());
        preparedStatement.executeUpdate();
    }

    public void actualizar(Datos datos) throws SQLException {
        String sql = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, datos.getNombre());
        preparedStatement.setString(2, datos.getApellido());
        preparedStatement.setString(3, datos.getDepartamento());
        preparedStatement.setDate(4, datos.getFechaNacimiento());
        preparedStatement.setInt(5, datos.getCodigo());
        preparedStatement.executeUpdate();
    }

    public void eliminar(int codigo) throws SQLException {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setInt(1, codigo);
        preparedStatement.executeUpdate();
    }
}
