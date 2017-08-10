package Model.entity;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "centro_de_custo")
public class Centro implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centro_de_custo")
    private int id;
    @Column(name = "descricao", nullable = false)
    private String descri;
    @Column(name = "data_de_cadastro", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar datacad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="fk_adm",insertable = true, updatable = true)    
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Administrador administradorcentro;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="fk_aprovador",insertable = true, updatable = true)    
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Aprovador aprovadorcentro;
    
    @OneToMany(mappedBy = "centrosolicitado", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Collection <Solicitacao> solicitacao;
    
     /////******************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%############//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Calendar getDatacad() {
        return datacad;
    }

    public void setDatacad(Calendar datacad) {
        this.datacad = datacad;
    }

    public Administrador getAdministradorcentro() {
        return administradorcentro;
    }

    public void setAdministradorcentro(Administrador administradorcentro) {
        this.administradorcentro = administradorcentro;
    }

    public Aprovador getAprovadorcentro() {
        return aprovadorcentro;
    }

    public void setAprovadorcentro(Aprovador aprovadorcentro) {
        this.aprovadorcentro = aprovadorcentro;
    }

    public Collection<Solicitacao> getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Collection<Solicitacao> solicitacao) {
        this.solicitacao = solicitacao;
    }
    
    
    
    

}
