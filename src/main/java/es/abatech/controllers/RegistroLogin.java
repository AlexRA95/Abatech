package es.abatech.controllers;

import es.abatech.DAO.IUsuariosDAO;
import es.abatech.DAOFactory.DAOFactory;
import es.abatech.beans.Usuario;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Map;

@WebServlet(name = "RegistroLogin", value = "/RegistroLogin")
public class RegistroLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuariosDAO udao = daof.getUsuariosDAO();
        String URL = ".";
        Usuario usuario = new Usuario();
        HttpSession sesion = request.getSession();
        Map<String, String[]> parametros = request.getParameterMap();

        switch (request.getParameter("opcion")){
            case "Registrar":
                try {
                    BeanUtils.populate(usuario, parametros);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                if(request.getParameter("avatar") == null){
                    usuario.setAvatar("default.png");
                }
                usuario.setUltimoAcceso(new Timestamp(System.currentTimeMillis()));
                udao.createUsuario(usuario);
                //Volvemos a darle datos al usuario para que este tenga su idUsuario de la base de datos
                usuario = udao.getUsusarioByEmailPassword(usuario.getEmail(), usuario.getPassword());
                sesion.setAttribute("usuario", usuario);
                break;
            case "Iniciar":
                usuario = udao.getUsusarioByEmailPassword(request.getParameter("email"), request.getParameter("password"));
                //Si el usuario existe, actualizamos su último acceso
                //Si no existe, se queda en null y se manda un mensaje de error
                if(usuario.getIdUsuario() != null){
                    udao.updateUltimaConex(new Timestamp(System.currentTimeMillis()), usuario.getIdUsuario());
                    sesion.setAttribute("usuario", usuario);
                }else{
                    request.setAttribute("error", "Usuario o contraseña incorrectos");
                }
                break;
            case "salir":
                sesion.removeAttribute("usuario");
                break;
        }

        request.getRequestDispatcher(URL).forward(request, response);
    }
}