package Model.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "adm")

public class Administrador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ADM")
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha_de_acesso", nullable = false)
    private String senha;
    
    @OneToMany(mappedBy = "administradoraprovador", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)    
    private List <Aprovador> aprovadores;
    
    @OneToMany (mappedBy = "administradorcentro", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List <Centro> centrocusto;
    
    @OneToMany(mappedBy = "administradorsolicitante", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List <Solicitante> solicitante;
    
    
        
    /////******************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%############//


    public int getId() {
        return id;
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
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Aprovador> getAprovadores() {
        return aprovadores;
    }

   

    public List<Centro> getCentrocusto() {
        return centrocusto;
    }

    public void setCentrocusto(List<Centro> centrocusto) {
        this.centrocusto = centrocusto;
    }

    public List<Solicitante> getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(List<Solicitante> solicitante) {
        this.solicitante = solicitante;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAprovadores(List<Aprovador> aprovadores) {
        this.aprovadores = aprovadores;
    }
    
    

}
