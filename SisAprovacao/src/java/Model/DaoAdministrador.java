package Model;

import Dto.DtoAdministrador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoAdministrador extends DaoUtil {

    public DaoAdministrador() {

        super();
    }

    public void cadastrarUsuario(Dto.DtoAdministrador dtoadm) throws SQLException, SQLException, SQLException, ClassNotFoundException {

        String sql = "INSERT INTO ADM (NOME,EMAIL,SENHA_DE_ACESSO)  VALUES (?,?,?)";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setString(1, dtoadm.getNome());
        pst.setString(2, dtoadm.getEmail());
        pst.setString(3, dtoadm.getSenha());
        pst.execute();
        pst.close();
        fechaTudo();
    }

    public void alterarUsuario(Dto.DtoAdministrador dtoadm) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE ADM SET NOME=?, EMAIL=?, SENHA_DE_ACESSO =? WHERE ID_ADM=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setString(1, dtoadm.getNome());
        pst.setString(2, dtoadm.getEmail());
        pst.setString(3, dtoadm.getSenha());
        pst.setInt(4, dtoadm.getId());
        pst.executeUpdate();
        pst.close();
        fechaTudo();

    }

    public void deletarUsuario(Dto.DtoAdministrador dtoadm) throws SQLException, SQLException, SQLException, ClassNotFoundException {

        String sql = "DELETE FROM ADM WHERE ID_ADM=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtoadm.getId());
        pst.executeUpdate();
        pst.close();
        fechaTudo();

    }

    public ArrayList<Dto.DtoAdministrador> listarAdm() throws SQLException, ClassNotFoundException {

        ArrayList<DtoAdministrador> adm = new ArrayList<>();

        String sql = "SELECT * FROM ADM";
        ResultSet rs = super.getStatment().executeQuery(sql);

        while (rs.next()) {
            DtoAdministrador retorno = new DtoAdministrador();
            retorno.setId(rs.getInt("id_adm"));
            retorno.setEmail(rs.getString("email"));
            retorno.setNome(rs.getString("nome"));
            retorno.setSenha(rs.getString("senha_de_acesso"));
            adm.add(retorno);
        }
        rs.close();
        fechaTudo();
        return adm;

    }

    public DtoAdministrador buscarAdmPorId(DtoAdministrador dtoadm) throws SQLException, ClassNotFoundException {

        ArrayList<DtoAdministrador> teste = new ArrayList<>();
        DtoAdministrador retorno = new DtoAdministrador();
        String sql = "SELECT * FROM ADM WHERE ID_ADM=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtoadm.getId());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            DtoAdministrador aux = new DtoAdministrador();
            retorno.setId(rs.getInt("id_adm"));
            retorno.setEmail(rs.getString("email"));
            retorno.setNome(rs.getString("nome"));
            retorno.setSenha(rs.getString("senha_de_acesso"));

            teste.add(aux);
        }

        for (DtoAdministrador dto : teste) {
            if (dto.getId() == dtoadm.getId()) {
                retorno.setId(dto.getId());
                retorno.setEmail(dtoadm.getEmail());
                retorno.setNome(dtoadm.getNome());
                retorno.setSenha(dto.getSenha());
            }
        }
        return retorno;
    }

}
