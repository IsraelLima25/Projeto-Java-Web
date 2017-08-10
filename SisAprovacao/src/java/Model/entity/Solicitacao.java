package Model.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private int id;
    @Column(name = "oque", nullable = false)
    private String oque;
    @Column(name = "porque", nullable = false)
    private String porque;
    @Column(name = "quanto", nullable = false)
    private String quanto;
    @Column(name = "quando", nullable = false)
    private String quando;
    @Column(name = "como", nullable = false)
    private String como;
    @Column(name = "quem", nullable = false)
    private String quem;
    @Column(name = "onde", nullable = false)
    private String onde;
    @Column(name = "orcamento_total", nullable = false)
    private float orcatotal;
    @Column(name = "orcamento_decisao", nullable = false)
    private float orcadec;
    @Column(name = "resultado", nullable = false)
    private String resultado;
    @Column(name = "data_de_solicitacao", nullable = false)
    private Calendar datasolit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_aprovador", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Aprovador aprovadorsolicitacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_centro_de_custo", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Centro centrosolicitado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitante", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Solicitante solicitante;

    /////******************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%############//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOque() {
        return oque;
    }

    public void setOque(String oque) {
        this.oque = oque;
    }

    public String getPorque() {
        return porque;
    }

    public void setPorque(String porque) {
        this.porque = porque;
    }

    public String getQuanto() {
        return quanto;
    }

    public void setQuanto(String quanto) {
        this.quanto = quanto;
    }

    public String getQuando() {
        return quando;
    }

    public void setQuando(String quando) {
        this.quando = quando;
    }

    public String getComo() {
        return como;
    }

    public void setComo(String como) {
        this.como = como;
    }

    public String getQuem() {
        return quem;
    }

    public void setQuem(String quem) {
        this.quem = quem;
    }

    public String getOnde() {
        return onde;
    }

    public void setOnde(String onde) {
        this.onde = onde;
    }

    public float getOrcatotal() {
        return orcatotal;
    }

    public void setOrcatotal(float orcatotal) {
        this.orcatotal = orcatotal;
    }

    public float getOrcadec() {
        return orcadec;
    }

    public void setOrcadec(float orcadec) {
        this.orcadec = orcadec;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Calendar getDatasolit() {
        return datasolit;
    }

    public void setDatasolit(Calendar datasolit) {
        this.datasolit = datasolit;
    }

    public Aprovador getAprovadorsolicitacao() {
        return aprovadorsolicitacao;
    }

    public void setAprovadorsolicitacao(Aprovador aprovadorsolicitacao) {
        this.aprovadorsolicitacao = aprovadorsolicitacao;
    }

    public Centro getCentrosolicitado() {
        return centrosolicitado;
    }

    public void setCentrosolicitado(Centro centrosolicitado) {
        this.centrosolicitado = centrosolicitado;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }
    
    

}
