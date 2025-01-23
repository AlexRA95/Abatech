package es.abatech.DAO;

import es.abatech.beans.Pedido;
import es.abatech.beans.Usuario;

public interface IPedidoDAO {

    public Pedido getPedidoByUser(Usuario usuario);

    public void addPedido(Pedido pedido);

    public void deletePedido(Pedido pedido);

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
