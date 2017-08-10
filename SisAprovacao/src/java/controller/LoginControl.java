package controller;

import Dto.DtoAdministrador;
import Dto.DtoAprovador;
import Dto.DtoSolicitante;
import Model.DaoAdministrador;
import Model.DaoAprovador;
import Model.DaoSolicitacao;
import Model.DaoSolicitante;
import Validar.FacesUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "login")
public class LoginControl {

    private String login;
    private String senha;

    public LoginControl() {
    }

    public String autenticarUsuario() throws SQLException, ClassNotFoundException {

        // Buscando lista com todos os usuarios que podem ser logados no sistema
        DaoAdministrador daoadm = new DaoAdministrador();
        DaoAprovador daoap = new DaoAprovador();
        DaoSolicitante daosol = new DaoSolicitante();
        ArrayList<DtoAdministrador> administradores = daoadm.listarAdm();
        ArrayList<DtoAprovador> aprovadores = daoap.listarAp();
        ArrayList<DtoSolicitante> solicitantes = daosol.listarSol();

        //Implementação da Logica para login no sistema
        for (DtoAdministrador ad : administradores) {
            if (ad.getEmail().equals(this.login) && ad.getSenha().equals(this.senha)) {
                DtoAdministrador dto = new DtoAdministrador();
                dto.setId(ad.getId());
                dto.setEmail(ad.getEmail());
                dto.setNome(ad.getNome());
                dto.setPermissao(ad.getPermissao());
                dto.setSenha(ad.getSenha());
                SingleUserLog.iniciarSessao(dto);
                FacesMessage msg = new FacesMessage(dto.getNome() + "  Logado com Sucesso");
                FacesContext.getCurrentInstance().addMessage("msg", msg);

                return "PrincipalAdm";
            }
        }

        for (DtoAprovador ad : aprovadores) {
            if (ad.getEmail().equals(this.login) && ad.getSenha().equals(this.senha)) {
                DtoAprovador dto = new DtoAprovador();
                dto.setId(ad.getId());
                dto.setEmail(ad.getEmail());
                dto.setNome(ad.getNome());
                dto.setPermissao(ad.getPermissao());
                dto.setSenha(ad.getSenha());
                SingleUserLog.iniciarSessao(dto);
                FacesMessage msg = new FacesMessage(" Usuario(a) " + dto.getNome() + "  Logado com Sucesso");
                FacesContext.getCurrentInstance().addMessage("msg", msg);
                return "PrincipalAp";
            }
        }

        for (DtoSolicitante ad : solicitantes) {
            if (ad.getEmail().equals(this.login) && ad.getSenha().equals(this.senha)) {

                DtoSolicitante dto = new DtoSolicitante();
                dto.setId(ad.getId());
                dto.setEmail(ad.getEmail());
                dto.setNome(ad.getNome());
                dto.setPermissao(ad.getPermissao());
                dto.setSenha(ad.getSenha());
                SingleUserLog.iniciarSessao(dto);
                FacesMessage msg = new FacesMessage(dto.getNome() + "  Logado com Sucesso");
                FacesContext.getCurrentInstance().addMessage("msg", msg);
                return "PrincipalSol";
            }

        }

        return null;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
