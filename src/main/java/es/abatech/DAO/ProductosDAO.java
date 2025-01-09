package es.abatech.DAO;

import es.abatech.beans.Categoria;
import es.abatech.beans.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductosDAO implements IProductosDAO {
    @Override
    public List<Producto> get8productosRand() {
        Connection conexion = null;
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        String sql = null;
        Producto producto = null;
        Categoria categoria = null;
        List<Producto> productos = null;

        try {
            conexion = ConnectionFactory.getConnection();
            sql = "Select p.*, c.*\n" +
                    "from productos as p\n" +
                    "JOIN abatech.categorias c on p.idCategoria = c.idCategoria\n" +
                    "ORDER BY RAND( ) LIMIT 8;";
            preparada = conexion.prepareStatement(sql);
            resultado = preparada.executeQuery();
            productos = new ArrayList<>();
            while (resultado.next()) {
                producto = new Producto();
                producto.setIdProducto(resultado.getShort("p.idProducto"));
                producto.setNombre(resultado.getString("p.nombre"));
                producto.setDescripcion(resultado.getString("p.descripcion"));
                producto.setMarca(resultado.getString("p.marca"));
                producto.setPrecio(resultado.getDouble("p.precio"));
                producto.setImagen(resultado.getString("p.imagen"));
                categoria = new Categoria();
                categoria.setIdCategoria(resultado.getByte("c.idCategoria"));
                categoria.setNombre(resultado.getString("c.nombre"));
                categoria.setImagen(resultado.getString("c.imagen"));
                producto.setCategoria(categoria);
                productos.add(producto);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return productos;
    }

    @Override
    public List<Producto> getProductosByNombre(String nombre) {
        Connection conexion = null;
        PreparedStatement preparada = null;
        ResultSet resultado = null;
        String sql = null;
        Producto producto = null;
        Categoria categoria = null;
        List<Producto> productos = null;

        try {
            conexion = ConnectionFactory.getConnection();
            sql = "Select p.*, c.*\n" +
                    "from productos as p JOIN abatech.categorias c on p.idCategoria = c.idCategoria\n" +
                    "WHERE p.nombre LIKE ?";
            preparada = conexion.prepareStatement(sql);
            preparada.setString(1, "%" + nombre + "%");
            resultado = preparada.executeQuery();
            productos = new ArrayList<>();
            while (resultado.next()) {
                producto = new Producto();
                producto.setIdProducto(resultado.getShort("p.idProducto"));
                producto.setNombre(resultado.getString("p.nombre"));
                producto.setDescripcion(resultado.getString("p.descripcion"));
                producto.setMarca(resultado.getString("p.marca"));
                producto.setPrecio(resultado.getDouble("p.precio"));
                producto.setImagen(resultado.getString("p.imagen"));
                categoria = new Categoria();
                categoria.setIdCategoria(resultado.getByte("c.idCategoria"));
                categoria.setNombre(resultado.getString("c.nombre"));
                categoria.setImagen(resultado.getString("c.imagen"));
                producto.setCategoria(categoria);
                productos.add(producto);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return productos;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConexion();
    }
}
