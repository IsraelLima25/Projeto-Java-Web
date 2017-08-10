package Model;

import Dto.DtoAdministrador;
import Dto.DtoAprovador;
import Dto.DtoSolicitante;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoAprovador extends DaoUtil {

    public DaoAprovador() {
        super();
    }

    public void cadastrarAprovador(DtoAprovador dtoap, DtoAdministrador dtoadm) throws SQLException, SQLException, ClassNotFoundException, ClassNotFoundException {

        String sql = "INSERT INTO APROVADOR (NOME,EMAIL,NIVEL_PERMISSAO,SENHA_DE_ACESSO,DATA_DE_"
                + "CADASTRO,FK_ADM1)  VALUES (?,?,?,?,?,?)";

        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setString(1, dtoap.getNome());
        pst.setString(2, dtoap.getEmail());
        pst.setString(3, dtoap.getPermissao());
        pst.setString(4, dtoap.getSenha());
        pst.setDate(5, new Date(2016 - 12 - 20));
        pst.setInt(6, dtoadm.getId());
        pst.executeUpdate();
        pst.close();
        fechaTudo();

    }

    public void alterarAprovador(DtoAprovador dtoap) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE APROVADOR SET NOME=?, EMAIL=?, SENHA_DE_ACESSO =? WHERE ID_APROVADOR =?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setString(1, dtoap.getNome());
        pst.setString(2, dtoap.getEmail());
        pst.setString(3, dtoap.getSenha());
        pst.setInt(4, dtoap.getId());
        pst.executeUpdate();
        pst.close();
        fechaTudo();

    }

    public ArrayList<DtoAprovador> listarAp() throws SQLException, ClassNotFoundException {

        ArrayList<DtoAprovador> sol = new ArrayList<>();

        String sql = "SELECT ID_APROVADOR,NOME,EMAIL,NIVEL_PERMISSAO,SENHA_DE_ACESSO,DATA_DE_CADASTRO FROM APROVADOR";
        ResultSet rs = super.getStatment().executeQuery(sql);

        while (rs.next()) {
            DtoAprovador retorno = new DtoAprovador();
            retorno.setId(rs.getInt("ID_APROVADOR"));
            retorno.setNome(rs.getString("NOME"));
            retorno.setEmail(rs.getString("EMAIL"));
            retorno.setPermissao(rs.getString("NIVEL_PERMISSAO"));
            retorno.setSenha(rs.getString("SENHA_DE_ACESSO"));
            retorno.setData(rs.getDate("DATA_DE_CADASTRO"));

            sol.add(retorno);
        }
        rs.close();
        fechaTudo();
        return sol;
    }

    public DtoAprovador buscarApPorId(DtoAprovador dtoap) throws SQLException, ClassNotFoundException {

        ArrayList<DtoAprovador> teste = new ArrayList<>();
        DtoAprovador retorno = new DtoAprovador();
        String sql = "SELECT ID_APROVADOR,NOME,EMAIL,NIVEL_PERMISSAO,SENHA_DE_ACESSO,DATA_DE_CADASTRO FROM APROVADOR "
                + "WHERE ID_APROVADOR=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtoap.getId());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            DtoAprovador aux = new DtoAprovador();
            retorno.setId(rs.getInt("ID_APROVADOR"));
            retorno.setNome(rs.getString("NOME"));
            retorno.setEmail(rs.getString("EMAIL"));
            retorno.setPermissao(rs.getString("NIVEL_PERMISSAO"));
            retorno.setSenha(rs.getString("SENHA_DE_ACESSO"));
            retorno.setData(rs.getDate("DATA_DE_CADASTRO"));
            teste.add(aux);
        }

        for (DtoAprovador dto : teste) {
            if (dto.getId() == dtoap.getId()) {
                retorno.setId(dto.getId());
                retorno.setData(dto.getData());
                retorno.setEmail(dto.getEmail());
                retorno.setNome(dto.getNome());
                retorno.setSenha(dto.getSenha());
            }
        }
        return retorno;
    }

}
