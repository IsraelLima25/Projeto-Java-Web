package Dto;

import java.util.Date;

public class DtoCentrodeCusto {

    private int idcentrodecusto;
    private String descricao;
    private Date data;

    public DtoCentrodeCusto() {
    }

    public DtoCentrodeCusto(int idcentrodecusto, String descricao, Date data) {
        this.idcentrodecusto = idcentrodecusto;
        this.descricao = descricao;
        this.data = data;
    }

    public DtoCentrodeCusto(int idcentrodecusto) {
        this.idcentrodecusto = idcentrodecusto;
    }

    public int getIdcentrodecusto() {
        return idcentrodecusto;
    }

    public void setIdcentrodecusto(int idcentrodecusto) {
        this.idcentrodecusto = idcentrodecusto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    

}
