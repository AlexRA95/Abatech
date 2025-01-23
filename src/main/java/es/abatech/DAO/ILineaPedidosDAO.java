package es.abatech.DAO;

import es.abatech.beans.LineaPedido;
import es.abatech.beans.Pedido;

import java.sql.Connection;

public interface ILineaPedidosDAO {

    public void addLineaPedido(LineaPedido lineaPedido, short idPedido, Connection conexion);

    public void addLineaPedido(LineaPedido lineaPedido, short idPedido);

    public void deleteLineaPedido(Pedido pedido, short idProducto);

    public void reduceLineaPedido(Pedido pedido, short idProducto);

    public void aumentarLineaPedido(Pedido pedido, Integer idProducto);

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
