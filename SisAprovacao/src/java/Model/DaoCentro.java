package Model;

import Dto.DtoAdministrador;
import Dto.DtoAprovador;
import Dto.DtoCentrodeCusto;
import Dto.DtoSolicitante;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCentro extends DaoUtil {

    public DaoCentro() {
        super();
    }

    public void cadastrarCentro(DtoCentrodeCusto centro, DtoAdministrador dtoadm, DtoAprovador dtoap) throws SQLException, SQLException, ClassNotFoundException, ClassNotFoundException {

        String sql = "INSERT INTO CENTRO_DE_CUSTO (DESCRICAO,FK_APROVADOR,FK_ADM,DATA_DE_"
                + "CADASTRO)  VALUES (?,?,?,?)";

        PreparedStatement pst = super.getPreparedStatement(sql);

        pst.setString(1, centro.getDescricao());
        pst.setInt(2, dtoap.getId());
        pst.setInt(3, dtoadm.getId());
        pst.setDate(4, new Date(2016 - 12 - 20));
        pst.executeUpdate();
        pst.close();
        fechaTudo();

    }

    public ArrayList<DtoCentrodeCusto> listarCentro() throws SQLException, ClassNotFoundException {

        ArrayList<DtoCentrodeCusto> sol = new ArrayList<>();

        String sql = "SELECT ID_CENTRO_DE_CUSTO,DESCRICAO,DATA_DE_CADASTRO FROM CENTRO_DE_CUSTO";
        ResultSet rs = super.getStatment().executeQuery(sql);

        while (rs.next()) {
            DtoCentrodeCusto retorno = new DtoCentrodeCusto();
            retorno.setIdcentrodecusto(rs.getInt("ID_CENTRO_DE_CUSTO"));
            retorno.setDescricao(rs.getString("DESCRICAO"));
            retorno.setData(rs.getDate("DATA_DE_CADASTRO"));
            sol.add(retorno);
        }
        rs.close();
        fechaTudo();
        return sol;
    }

    public DtoCentrodeCusto buscarCentroPorId(DtoCentrodeCusto dtocen) throws SQLException, ClassNotFoundException {

        ArrayList<DtoCentrodeCusto> teste = new ArrayList<>();
        DtoCentrodeCusto retorno = new DtoCentrodeCusto();
        String sql = "SELECT ID_CENTRO_DE_CUSTO,DESCRICAO,DATA_DE_CADASTRO FROM CENTRO_DE_CUSTO "
                + "WHERE ID_CENTRO_DE_CUSTO=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtocen.getIdcentrodecusto());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            DtoCentrodeCusto aux = new DtoCentrodeCusto();
            aux.setIdcentrodecusto(rs.getInt("ID_CENTRO_DE_CUSTO"));
            aux.setDescricao(rs.getString("DESCRICAO"));
            aux.setData(rs.getDate("DATA_DE_CADASTRO"));
            teste.add(aux);
        }

        for (DtoCentrodeCusto dto : teste) {
            if (dto.getIdcentrodecusto() == dtocen.getIdcentrodecusto()) {
                retorno.setIdcentrodecusto(dto.getIdcentrodecusto());
                retorno.setDescricao(dto.getDescricao());
                retorno.setData(dto.getData());
            }
        }
        return retorno;
    }

    public DtoCentrodeCusto retornarCentroPorAprovador(DtoAprovador dtoaprovador) throws SQLException, SQLException, ClassNotFoundException {
        DtoCentrodeCusto dtocentro = new DtoCentrodeCusto();

        String sql = "SELECT * FROM centro_de_custo WHERE FK_APROVADOR = ?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtoaprovador.getId());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            dtocentro.setIdcentrodecusto(rs.getInt("id_centro_de_custo"));
            dtocentro.setDescricao(rs.getString("descricao"));
            dtocentro.setData(rs.getDate("data_de_cadastro"));
        }
        return dtocentro;

    }

}
