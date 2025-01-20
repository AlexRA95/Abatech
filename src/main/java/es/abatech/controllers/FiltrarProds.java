package es.abatech.controllers;
import es.abatech.beans.Filtro;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "FiltrarProds", value = "/FiltrarProds")
public class FiltrarProds extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Filtro filtro = new Filtro();
        Map<String, String[]> parametros = request.getParameterMap();
        try {
            BeanUtils.populate(filtro, parametros);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}