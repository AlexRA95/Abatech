package es.abatech.controllers;
import es.abatech.DAO.IProductosDAO;
import es.abatech.DAOFactory.DAOFactory;
import es.abatech.beans.Filtro;
import es.abatech.beans.Producto;
import es.abatech.models.ListConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FiltrarProds", value = "/FiltrarProds")
public class FiltrarProds extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IProductosDAO pdao = daof.getProductosDAO();
        List<Producto> productos = new ArrayList<>();
        Filtro filtro = new Filtro();
        Map<String, String[]> parametros = request.getParameterMap();
        ConvertUtils.register(new ListConverter(), List.class);
        try {
            BeanUtils.populate(filtro, parametros);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        productos = pdao.getProductosByFiltro(filtro);
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}