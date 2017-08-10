package controller;

import Dto.DtoAdministrador;
import Dto.DtoAprovador;
import Dto.DtoSolicitante;
import Dto.Usuario;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "sessao")
public class SingleUserLog {

    private static Usuario user = null;

    private SingleUserLog() {
    }

    public static void iniciarSessao(Usuario usuario) {

        if (usuario instanceof DtoAdministrador) {
            user = new DtoAdministrador();
            user.setId(usuario.getId());
            user.setEmail(usuario.getEmail());
            user.setNome(usuario.getNome());
            user.setPermissao(usuario.getPermissao());
            user.setSenha(usuario.getSenha());

        } else if (usuario instanceof DtoSolicitante) {
            user = new DtoSolicitante();
            user.setId(usuario.getId());
            user.setEmail(usuario.getEmail());
            user.setNome(usuario.getNome());
            user.setPermissao(usuario.getPermissao());
            user.setSenha(usuario.getSenha());

        } else if (usuario instanceof DtoAprovador) {

            user = new DtoAprovador();
            user.setId(usuario.getId());
            user.setEmail(usuario.getEmail());
            user.setNome(usuario.getNome());
            user.setPermissao(usuario.getPermissao());
            user.setSenha(usuario.getSenha());
        }
    }

    public static Usuario retornarUsuariodaSessao() {
        return user;
    }

    public static void encerrarSessao() {
        user = null;
    }

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario user) {
        SingleUserLog.user = user;
    }
    
    

}
