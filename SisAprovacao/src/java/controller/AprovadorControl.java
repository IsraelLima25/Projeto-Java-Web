package controller;

import Dto.DtoAdministrador;
import Dto.DtoAprovador;
import Model.DaoAdministrador;
import Model.DaoAprovador;
import Validar.FacesUtil;
import Validar.Validador;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "aprovadorMB")
public class AprovadorControl {
    private int id;
    private String nome;
    private String email;
    private String senhaacesso;
    private String repsenhaacesso;

    // METODO PARA VALIDAÇÃO DOS CAMPOS E INSERÇÃO DOS DADOS NA BASE DE DADOS.
    public void salvar() throws SQLException, ClassNotFoundException {

        if (Validador.validarNome(this.nome) == false) {
            FacesUtil.addMsgErro("Os caracteres inseridos no campo"
                    + " nome são inválidos");
        } else if (Validador.validarEmail(this.email) == false) {
            FacesUtil.addMsgErro("Os caracteres inseridos no campo"
                    + " email são inválidos");
        } else if (Validador.validarSenha(this.senhaacesso) == false) {
            FacesUtil.addMsgErro("Os caracteres inseridos no campo"
                    + " senha são inválidos, neste campo são permitidos"
                    + " apenas 6 caracteres alfanumericos.");

        } else if (!validarRepeticao()) {

            FacesUtil.addMsgErro(" Atenção!! A repetição da senha não confere"
                    + " com a inserção principal.");
        } else {

            // SEGUE IMPLEMENTAÇÃO DO CADASTRO NA BASE DE DADOS
            DaoAprovador daoap = new DaoAprovador();
            DtoAprovador dtoap = new DtoAprovador();
            dtoap.setNome(this.nome);
            dtoap.setEmail(this.email);
            dtoap.setSenha(this.senhaacesso);
            dtoap.setPermissao("A");
            daoap.cadastrarAprovador(dtoap, (DtoAdministrador) SingleUserLog.retornarUsuariodaSessao());
            FacesUtil.addMsgInfo("Usuario cadastrado com Sucesso.");

        }
    }

// METODO PARA VALIDAR A REPETIÇÃO DAS SENHAS DO FORMULARIO.
    private boolean validarRepeticao() {

        if (this.senhaacesso.equals(this.repsenhaacesso)) {
            return true;
        } else {
            return false;
        }
    }

    public String deslogar() {
        SingleUserLog.encerrarSessao();
        return "Login";
    }

    public String retornarNomeAprovadorLogado() {
        DtoAprovador dtonome = (DtoAprovador) SingleUserLog.getUser();
        return "Usuario Logado : " + dtonome.getNome();
    }

    //CONSTRUTOR OVERRIDE.
    public AprovadorControl() {
    }

    // METODOS DE ENCAPSULAMENTO
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaacesso() {
        return senhaacesso;
    }

    public void setSenhaacesso(String senhaacesso) {
        this.senhaacesso = senhaacesso;
    }

    public String getRepsenhaacesso() {
        return repsenhaacesso;
    }

    public void setRepsenhaacesso(String repsenhaacesso) {
        this.repsenhaacesso = repsenhaacesso;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

}
