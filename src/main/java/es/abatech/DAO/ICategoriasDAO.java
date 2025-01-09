package es.abatech.DAO;

import es.abatech.beans.Categoria;

import java.util.List;

public interface ICategoriasDAO {

    /**
     * Recuperamos todas las categorias con {@link es.abatech.beans.Producto} asociados.
     *
     * @return Una lista de {@link Categoria} que tienen {@link es.abatech.beans.Producto} asociados.
     */
    public List<Categoria> getCategorias();

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection();
}
