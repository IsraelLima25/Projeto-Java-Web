package Model.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "aprovador")

public class Aprovador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aprovador")
    private int id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "nivel_permissao", nullable = false)
    private String permissao;
    @Column(name = "senha_de_acesso", nullable = false)
    private String senha;
    @Column(name = "data_de_cadastro", nullable = false)
    private Calendar datacadastro;

    //***************************************************************
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_adm1", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Administrador administradoraprovador;

    @OneToMany(mappedBy = "aprovadorsolicitacao", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<Solicitacao> solicitacao;

    @OneToMany(mappedBy = "aprovadorcentro", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)

    private List<Centro> centrocusto;

    /////******************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%############//
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

    public Calendar getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Calendar datacadastro) {
        this.datacadastro = datacadastro;
    }

    public Administrador getAdministradoraprovador() {
        return administradoraprovador;
    }

    public void setAdministradoraprovador(Administrador administradoraprovador) {
        this.administradoraprovador = administradoraprovador;
    }

    public List<Solicitacao> getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(List<Solicitacao> solicitacao) {
        this.solicitacao = solicitacao;
    }

    public List<Centro> getCentrocusto() {
        return centrocusto;
    }

    public void setCentrocusto(List<Centro> centrocusto) {
        this.centrocusto = centrocusto;
    }

}
