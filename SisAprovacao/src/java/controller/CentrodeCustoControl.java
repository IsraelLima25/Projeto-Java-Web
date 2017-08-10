package controller;

import Dto.DtoAdministrador;
import Dto.DtoAprovador;
import Dto.DtoCentrodeCusto;
import Dto.DtoSolicitacao;
import Model.DaoAprovador;
import Model.DaoCentro;
import Model.DaoSolicitacao;
import Validar.FacesUtil;
import Validar.Validador;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "centroMB")
@ViewScoped
public class CentrodeCustoControl {

    private DtoCentrodeCusto centrodecusto;

    public CentrodeCustoControl() {
        centrodecusto = new DtoCentrodeCusto();
    }

    public List<SelectItem> carregarListadeAprovadores() throws SQLException, SQLException, ClassNotFoundException {
        DaoAprovador dao = new DaoAprovador();
        ArrayList<DtoAprovador> dto = dao.listarAp();
        List<SelectItem> itens = new ArrayList(dto.size());

        for (DtoAprovador c : dto) {
            itens.add(new SelectItem(c.getId(), c.getNome()));
        }
        return itens;
    }

    // VALIDAÇÃO DOS CAMPOS E INSERÇÃO DOS DADOS NA BASE DE DADOS.
    public void salvar() throws SQLException, ClassNotFoundException {

        if (Validador.validarCentro(this.centrodecusto.getDescricao()) == false) {
            FacesUtil.addMsgErro("Os caracteres inseridos no campo"
                    + " descrição são inválidos.");
        } else {
            DaoCentro daocentro = new DaoCentro();
            DtoCentrodeCusto dtocentro = new DtoCentrodeCusto();
            dtocentro.setDescricao(this.centrodecusto.getDescricao());
            DtoAprovador dtoap = new DtoAprovador(centrodecusto.getIdcentrodecusto());
            DtoAdministrador dtoadm = (DtoAdministrador) SingleUserLog.retornarUsuariodaSessao();
            daocentro.cadastrarCentro(dtocentro, dtoadm, dtoap);
            FacesUtil.addMsgInfo("Centro de Custo Cadastrado com Sucesso.");
        }
    }

    public DtoCentrodeCusto getCentrodecusto() {
        return centrodecusto;
    }

    public void setCentrodecusto(DtoCentrodeCusto centrodecusto) {
        this.centrodecusto = centrodecusto;
    }

}
