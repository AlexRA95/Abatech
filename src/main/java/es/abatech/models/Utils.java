package es.abatech.models;

import es.abatech.DAO.IProductosDAO;
import es.abatech.DAOFactory.DAOFactory;
import es.abatech.beans.LineaPedido;
import es.abatech.beans.Pedido;
import es.abatech.beans.Producto;

import javax.servlet.http.Cookie;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase de utilidades que proporciona varios métodos auxiliares para la aplicación.
 */
public class Utils {

    /**
     * Convierte una cadena de entrada a su hash MD5.
     *
     * @param input la cadena de entrada a convertir
     * @return el hash MD5 de la cadena de entrada
     */
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Busca una cookie por nombre en el array de cookies proporcionado.
     *
     * @param nombre el nombre de la cookie a buscar
     * @param cookies el array de cookies en el que buscar
     * @return la cookie si se encuentra, de lo contrario null
     */
    public static Cookie buscarCookie(String nombre, Cookie[] cookies) {
        Cookie c = null;
        if (cookies != null) {
            for (Cookie value : cookies) {
                if (value.getName().equals(nombre)) {
                    c = value;
                    break;
                }
            }
        }
        return c;
    }

    /**
     * Añade un producto a un pedido dado. Si el producto ya existe en el pedido, su cantidad se incrementa.
     *
     * @param producto el producto a añadir
     * @param pedido el pedido al que se añade el producto
     */
    public static Boolean addProductoToPedido(Producto producto, Pedido pedido) {
        List<LineaPedido> lineasPedido = pedido.getLineasPedido();
        if (lineasPedido == null) {
            lineasPedido = new ArrayList<>();
        }
        Boolean productoEncontrado = false;
        for (LineaPedido lineaPedido : lineasPedido) {
            if (lineaPedido.getProducto().getIdProducto().equals(producto.getIdProducto()) && !productoEncontrado) {
                lineaPedido.setCantidad((byte) (lineaPedido.getCantidad() + 1));
                productoEncontrado = true;
            }
        }
        if (!productoEncontrado) {
            LineaPedido nuevaLineaPedido = new LineaPedido();
            nuevaLineaPedido.setProducto(producto);
            nuevaLineaPedido.setCantidad((byte) 1);
            nuevaLineaPedido.setPedido(pedido);
            lineasPedido.add(nuevaLineaPedido);
        }
        pedido.setLineasPedido(lineasPedido);
        updateImportePedido(pedido);
        return productoEncontrado;
    }

    /**
     * Actualiza el importe total de un pedido dado basado en sus líneas de pedido y sus cantidades.
     *
     * @param pedido el pedido a actualizar
     */
    public static void updateImportePedido(Pedido pedido) {
        double importe = 0;
        for (LineaPedido lineaPedido : pedido.getLineasPedido()) {
            importe += lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad();
        }
        pedido.setIva(1.21);
        importe *= pedido.getIva();
        pedido.setImporte(importe);
    }

    /**
     * Convierte un pedido a una representación en cadena adecuada para almacenar en una cookie.
     *
     * @param pedido el pedido a convertir
     * @return la representación en cadena del pedido
     */
    public static String pedidoToCookie(Pedido pedido) {
        StringBuilder mensaje = new StringBuilder();
        for (LineaPedido lp : pedido.getLineasPedido()) {
            mensaje.append(lp.getProducto().getIdProducto()).append("<*>");
            mensaje.append(lp.getCantidad()).append("[+]");
        }
        return mensaje.toString();
    }

    /**
     * Convierte una representación en cadena de una cookie de vuelta a un pedido.
     *
     * @param cookie la cadena de la cookie a convertir
     * @param pedido el pedido a poblar
     * @return el pedido poblado
     */
    public static Pedido cookieToPedido(String cookie, Pedido pedido) {
        String[] lineas = cookie.split("\\[\\+]");
        DAOFactory daof = DAOFactory.getDAOFactory();
        IProductosDAO pdao = daof.getProductosDAO();
        List<LineaPedido> lineasPedido = new ArrayList<>();
        for (String linea : lineas) {
            String[] atributos = linea.split("<\\*>");
            short idProducto = Short.parseShort(atributos[0]);
            byte cantidad = Byte.parseByte(atributos[1]);

            Producto producto = pdao.getProductoById(idProducto);

            LineaPedido lp = new LineaPedido();
            lp.setProducto(producto);
            lp.setCantidad(cantidad);
            lp.setPedido(pedido);
            lineasPedido.add(lp);
        }
        pedido.setLineasPedido(lineasPedido);
        updateImportePedido(pedido);
        pedido.setEstado(Pedido.Estado.c);
        pedido.setIva(0.21);
        pedido.setFecha(new Date());
        return pedido;
    }

    /**
     * Verifica si dos contraseñas son iguales después de hashear la nueva contraseña con MD5.
     *
     * @param contraActu la contraseña actual
     * @param contraNueva la nueva contraseña a comparar
     * @return true si las contraseñas son iguales, false de lo contrario
     */
    public static boolean contrasIguales(String contraActu, String contraNueva) {
        return contraActu.equals(md5(contraNueva));
    }

    public static void restarCantidadProducto(Short idProducto, Pedido pedido){
        List<LineaPedido> lineasPedido = pedido.getLineasPedido();
        for (LineaPedido lineaPedido : lineasPedido) {
            if (lineaPedido.getProducto().getIdProducto().equals(idProducto)) {
                if (lineaPedido.getCantidad() > 1) {
                    lineaPedido.setCantidad((byte) (lineaPedido.getCantidad() - 1));
                } else {
                    lineasPedido.remove(lineaPedido);
                }
                break;
            }
        }
        pedido.setLineasPedido(lineasPedido);
        updateImportePedido(pedido);
    }

    public static void aumentarCantidadProducto(Short idProducto, Pedido pedido){
        List<LineaPedido> lineasPedido = pedido.getLineasPedido();
        for (LineaPedido lineaPedido : lineasPedido) {
            if (lineaPedido.getProducto().getIdProducto().equals(idProducto)) {
                lineaPedido.setCantidad((byte) (lineaPedido.getCantidad() + 1));
                break;
            }
        }
        pedido.setLineasPedido(lineasPedido);
        updateImportePedido(pedido);
    }

    public static void borrarLineaPedido(short idProducto, Pedido pedido) {
        List<LineaPedido> lineasPedido = pedido.getLineasPedido();
        for (LineaPedido lineaPedido : lineasPedido) {
            if (lineaPedido.getProducto().getIdProducto().equals(idProducto)) {
                lineasPedido.remove(lineaPedido);
                break;
            }
        }
        pedido.setLineasPedido(lineasPedido);
        updateImportePedido(pedido);
    }
}