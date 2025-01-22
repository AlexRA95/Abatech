package es.abatech.DAO;

import es.abatech.beans.LineaPedido;

import java.sql.Connection;

public interface ILineaPedidosDAO {

    public void addLineaPedido(LineaPedido lineaPedido, short idPedido, Connection conexion);

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
