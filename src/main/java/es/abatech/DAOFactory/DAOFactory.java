package es.abatech.DAOFactory;

import es.abatech.DAO.ICategoriasDAO;
import es.abatech.DAO.IProductosDAO;

public abstract class DAOFactory {

    /**
     * Una clase abstracta por cada tabla de la base de datos
     * @return Inteface de las operaciones a realizar con la tabla
     */
    public abstract ICategoriasDAO getCategoriasDAO();
    public abstract IProductosDAO getProductosDAO();

    /**
     * Fábrica abstracta
     * @return Objeto de la fábrica abstracta
     */
    public static DAOFactory getDAOFactory() {
        DAOFactory daof = null;
        daof = new MySQLDAOFactory();
        return daof;
    }

}
