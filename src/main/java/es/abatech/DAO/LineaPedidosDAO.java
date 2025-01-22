package es.abatech.DAO;

import es.abatech.beans.LineaPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LineaPedidosDAO implements ILineaPedidosDAO {

    @Override
    public void addLineaPedido(LineaPedido linea, short idPedido, Connection conexion) {
        PreparedStatement preparada = null;
        String sql = "INSERT INTO lineaspedidos (idPedido, idProducto, cantidad) VALUES (?, ?, ?)";

        try {
            preparada = conexion.prepareStatement(sql);
            preparada.setShort(1, idPedido);
            preparada.setShort(2, linea.getProducto().getIdProducto());
            preparada.setByte(3, linea.getCantidad());
            preparada.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(LineaPedidosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (preparada != null) {
                try {
                    preparada.close();
                } catch (SQLException e) {
                    Logger.getLogger(LineaPedidosDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }




    @Override
    public void closeConnection() {
        ConnectionFactory.closeConexion();
    }

}
