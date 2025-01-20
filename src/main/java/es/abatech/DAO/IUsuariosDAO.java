package es.abatech.DAO;

import es.abatech.beans.Usuario;

import java.sql.Timestamp;

public interface IUsuariosDAO {

    public void createUsuario(Usuario usuario);

    public Usuario getUsusarioByEmailPassword(String email, String password);

    public void updateUltimaConex(Timestamp ultimaConexion, Short idUsuario);

    public void updateUsuarioGen(Usuario usuario);

    public void closeConnection();
}
