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

public class Utils {

    /**
     * Obtiene el valor de utilizar la función MD5 de una cadena
     * @param input Cadena pasada para convertir a MD5
     * @return Cadena tras la conversión
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

    public static Cookie buscarCookie(String nombre, Cookie[] cookies){
        Cookie c = null;
        if(cookies != null){
            for (Cookie value : cookies) {
                if (value.getName().equals(nombre)) {
                    c = value;
                    break;
                }
            }
        }
        return c;
    }

    public static void addProductoToPedido(Producto producto, Pedido pedido) {
        // Obtenemos las lineas de pedido de la sesión
        List<LineaPedido> lineasPedido = pedido.getLineasPedido();
        // Si lineas de pedido esta null, creamos una nueva lista
        if (lineasPedido == null) {
            lineasPedido = new ArrayList<>();
        }
        // Buscamos si el producto ya está en alguna línea de pedido
        boolean productoEncontrado = false;
        for (LineaPedido lineaPedido : lineasPedido) {
            if (lineaPedido.getProducto().getIdProducto().equals(producto.getIdProducto()) && !productoEncontrado) {
                // Si el producto ya está en la línea de pedido, aumentamos la cantidad en 1
                lineaPedido.setCantidad((byte) (lineaPedido.getCantidad() + 1));
                productoEncontrado = true;
            }
        }
        // Si el producto no está en ninguna línea de pedido, creamos una nueva línea de pedido
        if (!productoEncontrado) {
            LineaPedido nuevaLineaPedido = new LineaPedido();
            nuevaLineaPedido.setProducto(producto);
            nuevaLineaPedido.setCantidad((byte) 1);
            nuevaLineaPedido.setPedido(pedido);
            lineasPedido.add(nuevaLineaPedido);
        }
        // Asignamos la lista de lineas de pedido al pedido
        pedido.setLineasPedido(lineasPedido);
        // Actualizamos el importe del pedido
        updateImportePedido(pedido);
    }

    public static void updateImportePedido(Pedido pedido) {
        double importe = 0;
        for (LineaPedido lineaPedido : pedido.getLineasPedido()) {
            importe += lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad();
        }
        pedido.setImporte(importe);
    }

    public static String pedidoToCookie(Pedido pedido){
        StringBuilder mensaje = new StringBuilder();

        for (LineaPedido lp : pedido.getLineasPedido()) {
            mensaje.append(lp.getProducto().getIdProducto()).append("<*>");
            mensaje.append(lp.getCantidad()).append("[+]");
        }

        return mensaje.toString();
    }

    public static Pedido cookieToPedido(String cookie, Pedido pedido) {
        String[] lineas = cookie.split("\\[\\+]");
        DAOFactory daof = DAOFactory.getDAOFactory();
        IProductosDAO pdao = daof.getProductosDAO();
        List<LineaPedido> lineasPedido = new ArrayList<>();
        for (String linea : lineas) {
            String[] atributos = linea.split("<\\*>");
            short idProducto = Short.parseShort(atributos[0]);
            byte cantidad = Byte.parseByte(atributos[1]);

            // Realizar la consulta para obtener el producto por su ID
            Producto producto = pdao.getProductoById(idProducto);

            // Crear la línea de pedido y añadirla al pedido
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

    public static boolean contrasIguales(String contraActu, String contraNueva) {
        return contraActu.equals(md5(contraNueva));
    }
}
