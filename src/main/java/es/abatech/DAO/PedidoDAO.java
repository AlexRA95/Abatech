package es.abatech.DAO;

import es.abatech.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO implements IPedidoDAO {

    @Override
    public Pedido getPedidoByUser(Usuario usuario) {
        Connection conexion = null;
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        String sql = null;
        Pedido pedido = null;
        List<LineaPedido> lineas = new ArrayList<>();
        LineaPedido linea = null;

        try {
            conexion = ConnectionFactory.getConnection();
            sql = "SELECT p.*, l.*, pr.* FROM pedidos p " +
                    "JOIN abatech.lineaspedidos l ON p.idPedido = l.idPedido " +
                    "JOIN abatech.productos pr ON l.idProducto = pr.idProducto " +
                    "WHERE p.idUsuario = ? AND p.estado = 'c'";
            preparada = conexion.prepareStatement(sql);
            preparada.setInt(1, usuario.getIdUsuario());
            resultado = preparada.executeQuery();
            while (resultado.next()) {
                if (pedido == null) {
                    // Create the main pedido object
                    pedido = new Pedido();
                    pedido.setIdPedido((short) resultado.getInt("idPedido"));
                    pedido.setFecha(resultado.getDate("fecha"));
                    pedido.setEstado(Pedido.Estado.valueOf(resultado.getString("estado")));
                    pedido.setUsuario(usuario);
                    pedido.setImporte(resultado.getDouble("importe"));
                    pedido.setIva(resultado.getDouble("iva"));
                }
                // Create a new LineaPedido object
                linea = new LineaPedido();
                linea.setIdLinea((short) resultado.getInt("idLinea"));
                linea.setCantidad((byte) resultado.getInt("cantidad"));
                // Create a new Producto object and set it to the LineaPedido
                Producto producto = new Producto();
                producto.setIdProducto((short) resultado.getInt("idProducto"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                // Set the Producto to the LineaPedido
                linea.setProducto(producto);
                // Add the LineaPedido to the list
                lineas.add(linea);
            }
            if (pedido != null) {
                pedido.setLineasPedido(lineas);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }

        return pedido;
    }

    @Override
    public void addPedido(Pedido pedido) {
        Connection conexion = null;
        PreparedStatement preparada = null;
        ResultSet generatedKeys = null;
        String sqlPedido = "INSERT INTO pedidos (fecha, estado, idUsuario, importe, iva) VALUES (?, ?, ?, ?, ?)";
        LineaPedidosDAO lineaPedidosDAO = new LineaPedidosDAO();

        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false); // Start transaction

            // Insert the pedido
            preparada = conexion.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);
            preparada.setDate(1, new java.sql.Date(pedido.getFecha().getTime()));
            preparada.setString(2, pedido.getEstado().name());
            preparada.setInt(3, pedido.getUsuario().getIdUsuario());
            preparada.setDouble(4, pedido.getImporte());
            preparada.setDouble(5, pedido.getIva());
            preparada.executeUpdate();

            // Get the generated ID for the pedido
            generatedKeys = preparada.getGeneratedKeys();
            if (generatedKeys.next()) {
                pedido.setIdPedido(generatedKeys.getShort(1));
            }

            // Insert each lineaPedido using LineaPedidosDAO
            for (LineaPedido linea : pedido.getLineasPedido()) {
                lineaPedidosDAO.addLineaPedido(linea, pedido.getIdPedido(), conexion);
            }

            conexion.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conexion != null) {
                try {
                    conexion.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (preparada != null) {
                try {
                    preparada.close();
                } catch (SQLException e) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }


    @Override
    public void closeConnection() {
        ConnectionFactory.closeConexion();
    }
}
