package es.abatech.DAO;

import es.abatech.beans.Producto;

import java.util.List;

public interface IProductosDAO {
    public List<Producto> get8productosRand();
    public List<Producto> getProductosByNombre(String nombre);
    public void closeConnection();
}
