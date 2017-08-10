package controller;

import Model.entity.Administrador;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleadm")
public class AdministradorControl {

    private int id;
    private String nome;
    private String email;
    private String permi;
    private String senha;

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Administrador) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msgs = new FacesMessage("Car Edited", ((Administrador) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msgs);
    }

    public String deslogar() {
        SingleUserLog.encerrarSessao();
        return "Login";
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

    public String getPermi() {
        return permi;
    }

    public void setPermi(String permi) {
        this.permi = permi;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}



