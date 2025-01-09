package es.abatech.DAOFactory;


import es.abatech.DAO.CategoriasDAO;
import es.abatech.DAO.ICategoriasDAO;
import es.abatech.DAO.IProductosDAO;
import es.abatech.DAO.ProductosDAO;

/**
 * Fábrica concreta para la fuente de datos MySQL
 * @author jesus
 */
public class MySQLDAOFactory extends DAOFactory{

    @Override
    public ICategoriasDAO getCategoriasDAO() {
        return new CategoriasDAO();
    }

    public IProductosDAO getProductosDAO() {
        return new ProductosDAO();
    }
   
}
