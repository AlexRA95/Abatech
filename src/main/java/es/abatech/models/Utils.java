package es.abatech.models;

import javax.servlet.http.Cookie;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static boolean contrasIguales(String contraActu, String contraNueva) {
        return contraActu.equals(md5(contraNueva));
    }
}
