package es.abatech.controllers;

import es.abatech.DAO.IPedidoDAO;
import es.abatech.DAOFactory.DAOFactory;
import es.abatech.beans.Pedido;
import es.abatech.beans.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OpcionesUser", value = "/OpcionesUser")
public class OpcionesUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = ".";
        switch (request.getParameter("opcion")){
            case "perfil":
                URL="JSP/OpcionesPerfil.jsp";
                break;
            case "pedidos":
                URL="JSP/VerFinalizados.jsp";
                //Antes de ir a esta página, cargamos en request todos los pedidos finalizados del usuario
                DAOFactory factory = DAOFactory.getDAOFactory();
                IPedidoDAO pedidoDAO = factory.getPedidoDAO();
                List<Pedido> pedidos = pedidoDAO.getPedidosFinByUser((Usuario) request.getSession().getAttribute("usuario"));
                request.setAttribute("pedidos", pedidos);
                break;
            case "carrito":
                URL="JSP/Carrito.jsp";
                break;
        }
        request.getRequestDispatcher(URL).forward(request, response);
    }
}