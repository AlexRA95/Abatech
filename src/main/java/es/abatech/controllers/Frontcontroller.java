package es.abatech.controllers;

import es.abatech.DAO.IProductosDAO;
import es.abatech.DAO.ProductosDAO;
import es.abatech.DAOFactory.DAOFactory;
import es.abatech.beans.Producto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Frontcontroller", value = "/Frontcontroller")
public class Frontcontroller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Hacemos una consulta para obtener 8 productos aleatorios
        DAOFactory daof = DAOFactory.getDAOFactory();
        IProductosDAO pdao = daof.getProductosDAO();
        List<Producto> productos = new ArrayList<>();
        productos = pdao.get8productosRand();
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}