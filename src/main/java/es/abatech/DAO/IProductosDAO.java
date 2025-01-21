package es.abatech.DAO;

import es.abatech.beans.Filtro;
import es.abatech.beans.Producto;

import java.util.List;

public interface IProductosDAO {
    public List<Producto> get8productosRand();
    public List<Producto> getProductosByDescripcion(String descripcion);
    public List<Producto> getProductosByFiltro(Filtro filtro);
    public List<String> getMarcas();
    public Producto getProductoById(int idProducto);
    public void closeConnection();
}
