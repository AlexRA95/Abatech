package es.abatech.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
                URL=".";
                break;
            case "carrito":
                URL="JSP/Carrito.jsp";
                break;
        }
        request.getRequestDispatcher(URL).forward(request, response);
    }
}