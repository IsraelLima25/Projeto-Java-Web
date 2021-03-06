package Dto;

import java.util.Calendar;
import java.util.Date;

public class DtoSolicitante extends Usuario {

    private int id;
    private String nome;
    private String email;
    private String permissao;
    private String senha;
    private Date data;
    private DtoAdministrador adm;

    public DtoSolicitante() {
    }

    public DtoSolicitante(int id) {
        this.id = id;
    }

    public DtoSolicitante(int id, String nome, String email, String permissao, String senha, Date data) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.permissao = permissao;
        this.senha = senha;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public DtoAdministrador getAdm() {
        return adm;
    }

    public void setAdm(DtoAdministrador adm) {
        this.adm = adm;
    }
    

}
