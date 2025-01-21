package es.abatech.controllers;

import es.abatech.DAO.IProductosDAO;
import es.abatech.DAOFactory.DAOFactory;
import es.abatech.beans.Pedido;
import es.abatech.beans.Producto;
import es.abatech.beans.Usuario;
import es.abatech.models.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

@WebServlet(name = "Carrito", value = "/Carrito")
public class Carrito extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cookie cookie = null;
        Pedido pedido = (Pedido) session.getAttribute("carrito");
        Producto producto = null;
        DAOFactory daof = DAOFactory.getDAOFactory();
        IProductosDAO pdao = daof.getProductosDAO();
        if (pedido == null) {
            pedido = new Pedido();
            pedido.setIva(21.0);
            pedido.setFecha(new Date());
            session.setAttribute("carrito", pedido);
        }
        Integer idProducto = Integer.valueOf(request.getParameter("anadir"));
        producto = pdao.getProductoById(idProducto);
        Utils.addProductoToPedido(producto, pedido);
        //Pasamos el contenido del pedido a la cookie
        cookie = new Cookie("carrito", URLEncoder.encode(Utils.pedidoToCookie(pedido), "UTF-8"));
        cookie.setMaxAge(60*60*24*2);//Hacemos que la cookie dure 2 dias
        response.addCookie(cookie);

        request.getRequestDispatcher(".").forward(request, response);
    }
}