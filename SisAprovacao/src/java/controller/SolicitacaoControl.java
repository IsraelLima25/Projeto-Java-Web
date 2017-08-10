package controller;

import Dto.DtoAprovador;
import Dto.DtoCentrodeCusto;
import Dto.DtoSolicitacao;
import Dto.DtoSolicitante;
import Model.DaoAdministrador;
import Model.DaoAprovador;
import Model.DaoCentro;
import Model.DaoSolicitacao;
import Model.DaoSolicitante;
import Model.entity.Solicitacao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name = "solicitacaoMB")
public class SolicitacaoControl {

    DtoSolicitacao dtosolicitacao;
    DtoCentrodeCusto dtocentrodecusto;
    DtoSolicitante dtosolicitante;
    DtoAprovador dtoaprovador;

    //Contrutor da Classe para instaciar os objetos.
    public SolicitacaoControl() {
        dtosolicitacao = new DtoSolicitacao();
        dtocentrodecusto = new DtoCentrodeCusto();
        dtosolicitante = new DtoSolicitante();
        dtoaprovador = new DtoAprovador();
    }

    public void confirmarSolicitacao() throws SQLException, ClassNotFoundException {
        dtosolicitacao.setData(new Date(01 - 01 - 2017));
        dtosolicitacao.setResultado("E");
        dtosolicitante = (DtoSolicitante) SingleUserLog.retornarUsuariodaSessao();
        DaoSolicitacao daosolicitacao = new DaoSolicitacao();
        //
        daosolicitacao.enviarSolicitacao(dtosolicitacao, dtocentrodecusto, dtosolicitante);
       
    }
    
    public String voltar(){
        FacesMessage msg = new FacesMessage("Solicitacao enviada com sucesso");
        FacesContext.getCurrentInstance().addMessage("Solicitação Enviada com Sucesso", msg);
        return "conteudosolicitacaopendente";
    }

    public void confirmarSolicitacaoAp() throws SQLException, ClassNotFoundException {
        boolean c = true;
        if (c) {
            dtosolicitacao.setData(new Date(01 - 01 - 2017));
            dtosolicitacao.setResultado("E");
            dtoaprovador = (DtoAprovador) SingleUserLog.retornarUsuariodaSessao();
            DaoSolicitacao daosolicitacao = new DaoSolicitacao();
            //
            daosolicitacao.enviarSolicitacao(dtosolicitacao, dtocentrodecusto, dtoaprovador);
            FacesContext facesContext = FacesContext.getCurrentInstance();

            facesContext.addMessage("cnpj",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "CNPJ já cadastrado!",
                            "CNPJ já cadastrado!"));
        }
    }

//Metodo para Carregar todos os  Centros de Custo Cadastrados para os solicitantes no componente Select One Menu da pagina.
    public List<SelectItem> enviarCentrosCadastrados() throws SQLException, SQLException, ClassNotFoundException {
        DaoCentro dao = new DaoCentro();
        ArrayList<DtoCentrodeCusto> centro = dao.listarCentro();

        List<SelectItem> itens = new ArrayList(centro.size());

        for (DtoCentrodeCusto c : centro) {
            itens.add(new SelectItem(c.getIdcentrodecusto(), c.getDescricao()));
        }
        return itens;
    }

    // Metodo para carregar os centros de custo disponiveis apenas para o aprovador solicitar
    public List<SelectItem> enviarCentrosFiltrados() throws SQLException, SQLException, ClassNotFoundException {
        DtoAprovador dtoap = (DtoAprovador) SingleUserLog.getUser();
        DaoCentro dao = new DaoCentro();
        DtoCentrodeCusto centro = dao.retornarCentroPorAprovador(dtoap);
        ArrayList<DtoCentrodeCusto> listadeCentros = dao.listarCentro();
        List<SelectItem> itens = new ArrayList();
        for (int i = 0; i < listadeCentros.size(); i++) {
            if (listadeCentros.get(i).getIdcentrodecusto() > centro.getIdcentrodecusto()) {
                itens.add(new SelectItem(listadeCentros.get(i).getIdcentrodecusto(),
                        listadeCentros.get(i).getDescricao()));
            }
        }
        return itens;
    }

    // METODO PARA CARREGAR O SELECT COM O ID DAS SOLICITAÇÕES A SEREM AVALIADAS PELOS APROVADORES.
    public List<SelectItem> carregarListadeSolicitacoes() throws SQLException, SQLException, ClassNotFoundException {
        DtoAprovador ap = (DtoAprovador) SingleUserLog.retornarUsuariodaSessao();
        DaoSolicitacao dao = new DaoSolicitacao();
        ArrayList<DtoSolicitacao> centro = dao.buscarSolicitacaoPorAprovador(ap);

        List<SelectItem> itens = new ArrayList(centro.size());

        for (DtoSolicitacao c : centro) {
            itens.add(new SelectItem(c.getId()));
        }
        return itens;
    }

    // METODO PARA RETORNAR TODAS AS SOLICITACAOES POR APROVADOR LOGADO
    public ArrayList<DtoSolicitacao> listadeSolicitacaoes() throws SQLException, SQLException, ClassNotFoundException {

        DtoAprovador ap = (DtoAprovador) SingleUserLog.retornarUsuariodaSessao();
        DaoSolicitacao dao = new DaoSolicitacao();
        return dao.buscarSolicitacaoPorAprovador(ap);
    }

    //METODO CONTROLADOR PARA APROVAR SOLICITACAO
    public String aprovarSolicitacao() throws SQLException, SQLException, ClassNotFoundException {
        DaoSolicitacao dao = new DaoSolicitacao();
        dao.aprovarSolicitacao(dtosolicitacao);
        return "conteudoaprovarsolicitacao";

    }

    // METODO CONTROLADOR PARA APROVAR SOLICITACAO
    public String reprovarSolicitacao() throws SQLException, SQLException, ClassNotFoundException {
        DaoSolicitacao dao = new DaoSolicitacao();
        dao.reprovarSolicitacao(dtosolicitacao);
        return "conteudoaprovarsolicitacao";

    }
    public String avsaprovar(){
        FacesMessage msg = new FacesMessage("Solicitacao Aprovada com Sucesso");
        FacesContext.getCurrentInstance().addMessage("Solicitação Aprovada com Sucesso", msg);
        return "conteudosolicitacoesaprovadas";
    }
    public String avsreprovar(){
        FacesMessage msg = new FacesMessage("Solicitacao Reprovada com Sucesso");
        FacesContext.getCurrentInstance().addMessage("Solicitação Reprovada com Sucesso", msg);
        return "conteudosolicitacoesreprovadas";
    }



    //Metodo para retornar lista de solicitacções aprovadas pelo aprovador(usuario) Logado no sistema
    public ArrayList<DtoSolicitacao> retornarSolicitacoesAprovadas() throws SQLException, SQLException, ClassNotFoundException {

        DtoAprovador ap = (DtoAprovador) SingleUserLog.retornarUsuariodaSessao();
        DaoSolicitacao dao = new DaoSolicitacao();
        return dao.buscarSolicitacaoAprovada(ap);

    }

    //Metodo para retornar lista de solicitacções aprovadas pelo aprovador(usuario) Logado no sistema
    public ArrayList<DtoSolicitacao> retornarSolicitacoesReprovadas() throws SQLException, SQLException, ClassNotFoundException {

        DtoAprovador ap = (DtoAprovador) SingleUserLog.retornarUsuariodaSessao();
        DaoSolicitacao dao = new DaoSolicitacao();
        return dao.buscarSolicitacaoReprovada(ap);

    }

    //Metodo para retornar lista de solicitacções aprovadas pelo aprovador(usuario) Logado no sistema
    public ArrayList<DtoSolicitacao> retornarSolicitacoesEspera() throws SQLException, SQLException, ClassNotFoundException {

        DtoAprovador ap = (DtoAprovador) SingleUserLog.retornarUsuariodaSessao();
        DaoSolicitacao dao = new DaoSolicitacao();
        return dao.buscarSolicitacaoRealizada(ap);

    }

    ////////////////*****************************///////////////////////////////////////////////****
    public ArrayList<DtoSolicitacao> retornarSolicitacoesPendendesPorSoli() throws SQLException, ClassNotFoundException {
        DtoSolicitante sol = (DtoSolicitante) SingleUserLog.retornarUsuariodaSessao();
        DaoSolicitacao dao = new DaoSolicitacao();
        return dao.listarSolicitacoesPendentesPorSoli(sol);

    }

    public ArrayList<DtoSolicitacao> retornarSolicitacoesAprovadasPorSoli() throws SQLException, ClassNotFoundException {
        DtoSolicitante sol = (DtoSolicitante) SingleUserLog.retornarUsuariodaSessao();
        DaoSolicitacao dao = new DaoSolicitacao();
        return dao.listarSolicitacoesAprovadasPorSoli(sol);

    }

    public ArrayList<DtoSolicitacao> retornarSolicitacoesReprovadasPorSoli() throws SQLException, ClassNotFoundException {
        DtoSolicitante sol = (DtoSolicitante) SingleUserLog.retornarUsuariodaSessao();
        DaoSolicitacao dao = new DaoSolicitacao();
        return dao.listarSolicitacoesReprovadasPorSoli(sol);

    }

    public DtoSolicitacao getDtosolicitacao() {
        return dtosolicitacao;
    }

    public void setDtosolicitacao(DtoSolicitacao dtosolicitacao) {
        this.dtosolicitacao = dtosolicitacao;
    }

    public DtoCentrodeCusto getDtocentrodecusto() {
        return dtocentrodecusto;
    }

    public void setDtocentrodecusto(DtoCentrodeCusto dtocentrodecusto) {
        this.dtocentrodecusto = dtocentrodecusto;
    }

    public DtoSolicitante getDtosolicitante() {
        return dtosolicitante;
    }

    public void setDtosolicitante(DtoSolicitante dtosolicitante) {
        this.dtosolicitante = dtosolicitante;
    }

}
