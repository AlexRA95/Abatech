package es.abatech.controllers;

import es.abatech.DAO.IUsuariosDAO;
import es.abatech.DAOFactory.DAOFactory;
import es.abatech.beans.Usuario;
import es.abatech.models.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CambiarContrasenia", value = "/CambiarContrasenia")
public class CambiarContrasenia extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = "JSP/OpcionesPerfil.jsp";
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuariosDAO udao = daof.getUsuariosDAO();
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (Utils.contrasIguales(usuario.getPassword() , request.getParameter("contraseniaActual"))){
            //Si la contraseña actual del usuario coincide con la introducida, cambiamos la contraseña
            usuario.setPassword(Utils.md5(request.getParameter("nuevaContrasenia")));
            udao.updateUsuarioContra(usuario);
            request.setAttribute("succes", "Se ha cambiado la contraseña correctamente");
        }else{
            //Si la contraseña actual del usuario no coincide con la introducida, mostramos un mensaje de error
            request.setAttribute("error", "La contraseña actual no coincide");
        }

        request.getRequestDispatcher(URL).forward(request, response);
    }
}