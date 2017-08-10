package Dto;

import java.util.Date;

public class DtoSolicitacao {

    private int id;
    private String oque;
    private String porque;
    private String quanto;
    private String quando;
    private String como;
    private String quem;
    private String onde;
    private float orcamentototal;
    private float orcamentodecisao;
    private String resultado;
    private Date data;

    public DtoSolicitacao() {
    }

    public DtoSolicitacao(int id, String oque, String porque, String quanto, String quando, String como, String quem, String onde, float orcamentototal, float orcamentodecisao, String resultado, Date data) {
        this.id = id;
        this.oque = oque;
        this.porque = porque;
        this.quanto = quanto;
        this.quando = quando;
        this.como = como;
        this.quem = quem;
        this.onde = onde;
        this.orcamentototal = orcamentototal;
        this.orcamentodecisao = orcamentodecisao;
        this.resultado = resultado;
        this.data = data;
    }

    public DtoSolicitacao(int id) {
        this.id = id;
    }

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

    public float getOrcamentototal() {
        return orcamentototal;
    }

    public void setOrcamentototal(float orcamentototal) {
        this.orcamentototal = orcamentototal;
    }

    public float getOrcamentodecisao() {
        return orcamentodecisao;
    }

    public void setOrcamentodecisao(float orcamentodecisao) {
        this.orcamentodecisao = orcamentodecisao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
