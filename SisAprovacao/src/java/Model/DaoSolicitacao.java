package Model;

import Dto.DtoAdministrador;
import Dto.DtoAprovador;
import Dto.DtoCentrodeCusto;
import Dto.DtoSolicitacao;
import Dto.DtoSolicitante;
import Dto.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoSolicitacao extends DaoUtil {

    public DaoSolicitacao() {

        super();
    }

    public void enviarSolicitacao(DtoSolicitacao dtosolicitacao, DtoCentrodeCusto dtocentro, Usuario dtosolicitante) throws SQLException, SQLException, ClassNotFoundException, ClassNotFoundException {

        String sql = "INSERT INTO SOLICITACAO (OQUE,PORQUE,QUANTO,QUANDO,COMO,QUEM,"
                + "ONDE,ORCAMENTO_TOTAL,ORCAMENTO_DECISAO,RESULTADO,FK_CENTRO_DE_CUSTO,FK_SOLICITANTE,"
                + "DATA_DE_SOLICITACAO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setString(1, dtosolicitacao.getOque());
        pst.setString(2, dtosolicitacao.getPorque());
        pst.setString(3, dtosolicitacao.getQuanto());
        pst.setString(4, dtosolicitacao.getQuando());
        pst.setString(5, dtosolicitacao.getComo());
        pst.setString(6, dtosolicitacao.getQuem());
        pst.setString(7, dtosolicitacao.getOnde());
        pst.setFloat(8, dtosolicitacao.getOrcamentototal());
        pst.setFloat(9, dtosolicitacao.getOrcamentodecisao());
        pst.setString(10, dtosolicitacao.getResultado());
        pst.setInt(11, dtocentro.getIdcentrodecusto());
        pst.setInt(12, dtosolicitante.getId());
        pst.setDate(13, new Date(2016 - 12 - 12));
        pst.executeUpdate();
        pst.close();
        fechaTudo();
    }

    public ArrayList<DtoSolicitacao> buscarSolicitacaoPorAprovador(DtoAprovador dtoap) throws SQLException, ClassNotFoundException {
        ArrayList<DtoSolicitacao> retornoCarregado = new ArrayList<>();
        String sql = "SELECT * FROM SOLICITACAO WHERE FK_CENTRO_DE_CUSTO = "
                + "(SELECT ID_CENTRO_DE_CUSTO FROM CENTRO_DE_CUSTO WHERE FK_APROVADOR="
                + "(SELECT ID_APROVADOR FROM APROVADOR WHERE ID_APROVADOR = ?) AND RESULTADO=?)";

        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtoap.getId());
        pst.setString(2, "E");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            DtoSolicitacao aux = new DtoSolicitacao();
            aux.setComo(rs.getString("como"));
            aux.setData(rs.getDate("data_de_solicitacao"));
            aux.setId(rs.getInt("id_solicitacao"));
            aux.setOnde(rs.getString("onde"));
            aux.setOque(rs.getString("oque"));
            aux.setOrcamentodecisao(rs.getFloat("orcamento_decisao"));
            aux.setOrcamentototal(rs.getFloat("orcamento_total"));
            aux.setPorque(rs.getString("porque"));
            aux.setQuando(rs.getString("quando"));
            aux.setQuanto(rs.getString("quanto"));
            aux.setQuem(rs.getString("quem"));
            aux.setResultado(rs.getString("resultado"));
            retornoCarregado.add(aux);
        }
        return retornoCarregado;
    }

    public DtoSolicitacao retornarSolicitacaoPorId(DtoSolicitacao dtosolicitacao) throws SQLException, SQLException, ClassNotFoundException {
        DtoSolicitacao solitretorno = new DtoSolicitacao();
        String sql = "SELECT * FROM SOLICITACAO WHERE ID_SOLICITACAO=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtosolicitacao.getId());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            solitretorno.setComo(rs.getString("como"));
            solitretorno.setData(rs.getDate("data_de_solicitacao"));
            solitretorno.setId(rs.getInt("id_solicitacao"));
            solitretorno.setOnde(rs.getString("onde"));
            solitretorno.setOque(rs.getString("oque"));
            solitretorno.setOrcamentodecisao(rs.getFloat("orcamento_decisao"));
            solitretorno.setOrcamentototal(rs.getFloat("orcamento_total"));
            solitretorno.setPorque(rs.getString("porque"));
            solitretorno.setQuando(rs.getString("quando"));
            solitretorno.setQuanto(rs.getString("quanto"));
            solitretorno.setQuem(rs.getString("quem"));
            solitretorno.setResultado(rs.getString("resultado"));

        }
        return solitretorno;
    }

    public void aprovarSolicitacao(DtoSolicitacao dtosolicitacao) throws SQLException, ClassNotFoundException {
        DtoSolicitacao ap = retornarSolicitacaoPorId(dtosolicitacao);
        String sql = "UPDATE SOLICITACAO SET RESULTADO=? WHERE ID_SOLICITACAO=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setString(1, "A");
        pst.setInt(2, dtosolicitacao.getId());
        pst.executeUpdate();
    }

    public void reprovarSolicitacao(DtoSolicitacao dtosolicitacao) throws SQLException, ClassNotFoundException {
        DtoSolicitacao ap = retornarSolicitacaoPorId(dtosolicitacao);
        String sql = "UPDATE SOLICITACAO SET RESULTADO=? WHERE ID_SOLICITACAO=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setString(1, "R");
        pst.setInt(2, dtosolicitacao.getId());
        pst.executeUpdate();
    }

    public ArrayList<DtoSolicitacao> buscarSolicitacaoAprovada(Usuario dtoap) throws SQLException, ClassNotFoundException {
        ArrayList<DtoSolicitacao> retornoCarregado = new ArrayList<>();
        String sql = "SELECT * FROM SOLICITACAO WHERE FK_CENTRO_DE_CUSTO = "
                + "(SELECT ID_CENTRO_DE_CUSTO FROM CENTRO_DE_CUSTO WHERE FK_APROVADOR="
                + "(SELECT ID_APROVADOR FROM APROVADOR WHERE ID_APROVADOR = ?) AND RESULTADO=?)";

        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtoap.getId());
        pst.setString(2, "A");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            DtoSolicitacao aux = new DtoSolicitacao();
            aux.setComo(rs.getString("como"));
            aux.setData(rs.getDate("data_de_solicitacao"));
            aux.setId(rs.getInt("id_solicitacao"));
            aux.setOnde(rs.getString("onde"));
            aux.setOque(rs.getString("oque"));
            aux.setOrcamentodecisao(rs.getFloat("orcamento_decisao"));
            aux.setOrcamentototal(rs.getFloat("orcamento_total"));
            aux.setPorque(rs.getString("porque"));
            aux.setQuando(rs.getString("quando"));
            aux.setQuanto(rs.getString("quanto"));
            aux.setQuem(rs.getString("quem"));
            aux.setResultado(rs.getString("resultado"));
            retornoCarregado.add(aux);
        }
        return retornoCarregado;
    }

    public ArrayList<DtoSolicitacao> buscarSolicitacaoReprovada(Usuario dtoap) throws SQLException, ClassNotFoundException {
        ArrayList<DtoSolicitacao> retornoCarregado = new ArrayList<>();
        String sql = "SELECT * FROM SOLICITACAO WHERE FK_CENTRO_DE_CUSTO = "
                + "(SELECT ID_CENTRO_DE_CUSTO FROM CENTRO_DE_CUSTO WHERE FK_APROVADOR="
                + "(SELECT ID_APROVADOR FROM APROVADOR WHERE ID_APROVADOR = ?) AND RESULTADO=?)";

        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtoap.getId());
        pst.setString(2, "R");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            DtoSolicitacao aux = new DtoSolicitacao();
            aux.setComo(rs.getString("como"));
            aux.setData(rs.getDate("data_de_solicitacao"));
            aux.setId(rs.getInt("id_solicitacao"));
            aux.setOnde(rs.getString("onde"));
            aux.setOque(rs.getString("oque"));
            aux.setOrcamentodecisao(rs.getFloat("orcamento_decisao"));
            aux.setOrcamentototal(rs.getFloat("orcamento_total"));
            aux.setPorque(rs.getString("porque"));
            aux.setQuando(rs.getString("quando"));
            aux.setQuanto(rs.getString("quanto"));
            aux.setQuem(rs.getString("quem"));
            aux.setResultado(rs.getString("resultado"));
            retornoCarregado.add(aux);
        }
        return retornoCarregado;
    }

    //CORRIGIR
    public ArrayList<DtoSolicitacao> buscarSolicitacaoRealizada(Usuario dtoap) throws SQLException, ClassNotFoundException {
        ArrayList<DtoSolicitacao> retornoCarregado = new ArrayList<>();
        String sql = "SELECT * FROM SOLICITACAO WHERE FK_CENTRO_DE_CUSTO = "
                + "(SELECT ID_CENTRO_DE_CUSTO FROM CENTRO_DE_CUSTO WHERE FK_APROVADOR="
                + "(SELECT ID_APROVADOR FROM APROVADOR WHERE ID_APROVADOR = ?))";

        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtoap.getId());

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            DtoSolicitacao aux = new DtoSolicitacao();
            aux.setComo(rs.getString("como"));
            aux.setData(rs.getDate("data_de_solicitacao"));
            aux.setId(rs.getInt("id_solicitacao"));
            aux.setOnde(rs.getString("onde"));
            aux.setOque(rs.getString("oque"));
            aux.setOrcamentodecisao(rs.getFloat("orcamento_decisao"));
            aux.setOrcamentototal(rs.getFloat("orcamento_total"));
            aux.setPorque(rs.getString("porque"));
            aux.setQuando(rs.getString("quando"));
            aux.setQuanto(rs.getString("quanto"));
            aux.setQuem(rs.getString("quem"));
            aux.setResultado(rs.getString("resultado"));
            retornoCarregado.add(aux);
        }
        return retornoCarregado;
    }

    ///////////////////*************************//////////////////////////////////
    public ArrayList<DtoSolicitacao> listarSolicitacoesPendentesPorSoli(DtoSolicitante dtosolicitante) throws SQLException, SQLException, ClassNotFoundException {
        ArrayList<DtoSolicitacao> retornoCarregado = new ArrayList<>();
        String sql = "SELECT * FROM SOLICITACAO WHERE FK_SOLICITANTE = ? AND RESULTADO=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtosolicitante.getId());
        pst.setString(2, "E");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            DtoSolicitacao aux = new DtoSolicitacao();
            aux.setComo(rs.getString("como"));
            aux.setData(rs.getDate("data_de_solicitacao"));
            aux.setId(rs.getInt("id_solicitacao"));
            aux.setOnde(rs.getString("onde"));
            aux.setOque(rs.getString("oque"));
            aux.setOrcamentodecisao(rs.getFloat("orcamento_decisao"));
            aux.setOrcamentototal(rs.getFloat("orcamento_total"));
            aux.setPorque(rs.getString("porque"));
            aux.setQuando(rs.getString("quando"));
            aux.setQuanto(rs.getString("quanto"));
            aux.setQuem(rs.getString("quem"));
            aux.setResultado(rs.getString("resultado"));
            retornoCarregado.add(aux);

        }
        return retornoCarregado;
    }

    public ArrayList<DtoSolicitacao> listarSolicitacoesAprovadasPorSoli(DtoSolicitante dtosolicitante) throws SQLException, SQLException, ClassNotFoundException {
        ArrayList<DtoSolicitacao> retornoCarregado = new ArrayList<>();
        String sql = "SELECT * FROM SOLICITACAO WHERE FK_SOLICITANTE = ? AND RESULTADO=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtosolicitante.getId());
        pst.setString(2, "A");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            DtoSolicitacao aux = new DtoSolicitacao();
            aux.setComo(rs.getString("como"));
            aux.setData(rs.getDate("data_de_solicitacao"));
            aux.setId(rs.getInt("id_solicitacao"));
            aux.setOnde(rs.getString("onde"));
            aux.setOque(rs.getString("oque"));
            aux.setOrcamentodecisao(rs.getFloat("orcamento_decisao"));
            aux.setOrcamentototal(rs.getFloat("orcamento_total"));
            aux.setPorque(rs.getString("porque"));
            aux.setQuando(rs.getString("quando"));
            aux.setQuanto(rs.getString("quanto"));
            aux.setQuem(rs.getString("quem"));
            aux.setResultado(rs.getString("resultado"));
            retornoCarregado.add(aux);

        }
        return retornoCarregado;
    }

    public ArrayList<DtoSolicitacao> listarSolicitacoesReprovadasPorSoli(DtoSolicitante dtosolicitante) throws SQLException, SQLException, ClassNotFoundException {
        ArrayList<DtoSolicitacao> retornoCarregado = new ArrayList<>();
        String sql = "SELECT * FROM SOLICITACAO WHERE FK_SOLICITANTE = ? AND RESULTADO=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtosolicitante.getId());
        pst.setString(2, "R");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            DtoSolicitacao aux = new DtoSolicitacao();
            aux.setComo(rs.getString("como"));
            aux.setData(rs.getDate("data_de_solicitacao"));
            aux.setId(rs.getInt("id_solicitacao"));
            aux.setOnde(rs.getString("onde"));
            aux.setOque(rs.getString("oque"));
            aux.setOrcamentodecisao(rs.getFloat("orcamento_decisao"));
            aux.setOrcamentototal(rs.getFloat("orcamento_total"));
            aux.setPorque(rs.getString("porque"));
            aux.setQuando(rs.getString("quando"));
            aux.setQuanto(rs.getString("quanto"));
            aux.setQuem(rs.getString("quem"));
            aux.setResultado(rs.getString("resultado"));
            retornoCarregado.add(aux);

        }
        return retornoCarregado;
    }
}
