package es.abatech.DAO;

import es.abatech.beans.Pedido;
import es.abatech.beans.Usuario;

import java.util.List;

public interface IPedidoDAO {

    public Pedido getPedidoByUser(Usuario usuario);

    public void addPedido(Pedido pedido);

    public void deletePedido(Pedido pedido);

    public void updatePedido(Pedido pedido);

    public List<Pedido> getPedidosFinByUser(Usuario usuario);

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
