/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.abatech.events;

import es.abatech.DAO.ICategoriasDAO;
import es.abatech.DAOFactory.DAOFactory;
import es.abatech.beans.Categoria;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Rdoriguez Alvarez
 */
@WebListener
public class ApplicationStart implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();
        DAOFactory daof = DAOFactory.getDAOFactory();
        ICategoriasDAO cdao = daof.getCategoriasDAO();
        List<Categoria> categorias = new ArrayList<>();
        categorias = cdao.getCategorias();
        sce.getServletContext().setAttribute("categorias", categorias);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();
        sce.getServletContext().removeAttribute("categorias");
    }
    
}
