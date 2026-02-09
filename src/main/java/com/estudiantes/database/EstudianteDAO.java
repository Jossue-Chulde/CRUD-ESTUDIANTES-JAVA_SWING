package com.estudiantes.database;

import com.estudiantes.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    // Método para guardar un estudiante
    public boolean guardar(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiantes (nombres, apellidos, edad, carrera, semestre, correo) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            conexion = ConexionDB.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, estudiante.getNombres());
            ps.setString(2, estudiante.getApellidos());
            ps.setInt(3, estudiante.getEdad());
            ps.setString(4, estudiante.getCarrera());
            ps.setInt(5, estudiante.getSemestre());
            ps.setString(6, estudiante.getCorreo());

            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al guardar estudiante");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) ConexionDB.cerrarConexion(conexion);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para listar todos los estudiantes
    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM Estudiantes";
        Connection conexion = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conexion = ConexionDB.getConexion();
            st = conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setEdad(rs.getInt("edad"));
                estudiante.setCarrera(rs.getString("carrera"));
                estudiante.setSemestre(rs.getInt("semestre"));
                estudiante.setCorreo(rs.getString("correo"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar estudiantes");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conexion != null) ConexionDB.cerrarConexion(conexion);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return estudiantes;
    }

    // Método para actualizar un estudiante
    public boolean actualizar(Estudiante estudiante) {
        String sql = "UPDATE Estudiantes SET nombres=?, apellidos=?, edad=?, carrera=?, semestre=?, correo=? WHERE id=?";
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            conexion = ConexionDB.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, estudiante.getNombres());
            ps.setString(2, estudiante.getApellidos());
            ps.setInt(3, estudiante.getEdad());
            ps.setString(4, estudiante.getCarrera());
            ps.setInt(5, estudiante.getSemestre());
            ps.setString(6, estudiante.getCorreo());
            ps.setInt(7, estudiante.getId());

            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar estudiante");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) ConexionDB.cerrarConexion(conexion);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para eliminar un estudiante
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Estudiantes WHERE id=?";
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            conexion = ConexionDB.getConexion();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);

            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar estudiante");
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) ConexionDB.cerrarConexion(conexion);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
