package es.abatech.DAO;

import es.abatech.beans.Usuario;
import es.abatech.models.Utils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuariosDAO implements IUsuariosDAO {

    @Override
    public void updateUsuarioContra(Usuario usuario) {
        Connection conexion = null;
        PreparedStatement preparada = null;
        String sql = "UPDATE usuarios SET Password = ? WHERE IdUsuario = ?";
        try {
            conexion = ConnectionFactory.getConnection();
            preparada = conexion.prepareStatement(sql);
            preparada.setString(1, usuario.getPassword());
            preparada.setInt(2, usuario.getIdUsuario());
            preparada.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void updateUsuarioGen(Usuario usuario) {
        Connection conexion = null;
        PreparedStatement preparada = null;
        String sql = "UPDATE usuarios SET Nombre = ?, Apellidos = ?, NIF = ?, Telefono = ?, Direccion = ?, CodigoPostal = ?, Localidad = ?, Provincia = ? WHERE IdUsuario = ?";
        try {
            conexion = ConnectionFactory.getConnection();
            preparada = conexion.prepareStatement(sql);
            preparada.setString(1, usuario.getNombre());
            preparada.setString(2, usuario.getApellidos());
            preparada.setString(3, usuario.getNif());
            preparada.setInt(4, usuario.getTelefono());
            preparada.setString(5, usuario.getDireccion());
            preparada.setShort(6, usuario.getCodigoPostal());
            preparada.setString(7, usuario.getLocalidad());
            preparada.setString(8, usuario.getProvincia());
            preparada.setShort(9, usuario.getIdUsuario());
            preparada.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void createUsuario(Usuario usuario) {
        Connection connection = null;
        PreparedStatement preparada = null;
        String sql = "INSERT INTO usuarios (Email,Password,Nombre,Apellidos,NIF,Telefono,Direccion,CodigoPostal,Localidad,Provincia,UltimoAcceso) "
                + "VALUES (?,md5(?),?,?,?,?,?,?,?,?,?)";
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            preparada = connection.prepareStatement(sql);
            preparada.setString(1, usuario.getEmail());
            preparada.setString(2, usuario.getPassword());
            preparada.setString(3, usuario.getNombre());
            preparada.setString(4, usuario.getApellidos());
            preparada.setString(5, usuario.getNif());
            preparada.setString(6, usuario.getTelefono().toString());
            preparada.setString(7, usuario.getDireccion());
            preparada.setString(8, usuario.getCodigoPostal().toString());
            preparada.setString(9, usuario.getLocalidad());
            preparada.setString(10, usuario.getProvincia());
            preparada.setString(11, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(usuario.getUltimoAcceso()));

            preparada.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void updateUltimaConex(Timestamp ultimaConexion, Short idUsuario) {
        Connection conexion = null;
        PreparedStatement preparada = null;
        String sql = "UPDATE usuarios SET UltimoAcceso = ? WHERE IdUsuario = ?";
        try {
            conexion = ConnectionFactory.getConnection();
            preparada = conexion.prepareStatement(sql);
            preparada.setTimestamp(1, ultimaConexion);
            preparada.setInt(2, idUsuario);
            preparada.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public Usuario getUsusarioByEmailPassword(String email, String password) {
        Connection conexion = null;
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        String sql = null;
        Usuario usuario = null;

        try {
            conexion = ConnectionFactory.getConnection();
            sql = "SELECT * FROM usuarios WHERE Email = ? AND Password = md5(?)";
            preparada = conexion.prepareStatement(sql);
            preparada.setString(1, email);
            preparada.setString(2, password);
            resultado = preparada.executeQuery();
            usuario = new Usuario();
            while (resultado.next()) {
                usuario.setIdUsuario((short) resultado.getInt("IdUsuario"));
                usuario.setEmail(resultado.getString("Email"));
                usuario.setPassword(resultado.getString("Password"));
                usuario.setNombre(resultado.getString("Nombre"));
                usuario.setApellidos(resultado.getString("Apellidos"));
                usuario.setNif(resultado.getString("NIF"));
                usuario.setTelefono(resultado.getInt("Telefono"));
                usuario.setDireccion(resultado.getString("Direccion"));
                usuario.setCodigoPostal(Short.valueOf(resultado.getString("CodigoPostal")));
                usuario.setLocalidad(resultado.getString("Localidad"));
                usuario.setProvincia(resultado.getString("Provincia"));
                usuario.setUltimoAcceso(resultado.getTimestamp("UltimoAcceso"));
                usuario.setAvatar(resultado.getString("Avatar"));
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return usuario;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConexion();
    }
}
