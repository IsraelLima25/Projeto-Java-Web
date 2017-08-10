package Model.entity;

import java.util.Calendar;
import java.util.Collection;
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
@Table(name = "solicitante")
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitante")
    private int id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "nivel_permissao", nullable = false)
    private String permissao;
    @Column(name = "senha_de_acesso", nullable = false)
    private String senha;
    @Column(name = "data_de_cadastro", nullable = false)
    private Calendar datacad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adm", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Administrador administradorsolicitante;
    
    @OneToMany(mappedBy = "solicitante", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection <Solicitacao> solicitacoes;
    
    
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

    public Calendar getDatacad() {
        return datacad;
    }

    public void setDatacad(Calendar datacad) {
        this.datacad = datacad;
    }

    public Administrador getAdministradorsolicitante() {
        return administradorsolicitante;
    }

    public void setAdministradorsolicitante(Administrador administradorsolicitante) {
        this.administradorsolicitante = administradorsolicitante;
    }

    public Collection<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(Collection<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }
    
    

}
