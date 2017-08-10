package Model;

import Dto.DtoAdministrador;
import Dto.DtoSolicitante;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class DaoSolicitante extends DaoUtil {
    
    public DaoSolicitante() {
        super();
    }
    
    public void cadastrarSolicitante(DtoSolicitante dtosol, DtoAdministrador dtoadm) throws SQLException, SQLException, ClassNotFoundException, ClassNotFoundException {
        
        String sql = "INSERT INTO SOLICITANTE (NOME,EMAIL,NIVEL_PERMISSAO,SENHA_DE_ACESSO,DATA_DE_"
                + "CADASTRO,FK_ADM0)  VALUES (?,?,?,?,?,?)";
        
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setString(1, dtosol.getNome());
        pst.setString(2, dtosol.getEmail());
        pst.setString(3, dtosol.getPermissao());
        pst.setString(4, dtosol.getSenha());
        pst.setDate(5, new Date(2016 - 12 - 20));
        pst.setInt(6, dtoadm.getId());
        pst.executeUpdate();
        pst.close();
        fechaTudo();
        
    }
    
    public void alterarSolicitante(DtoSolicitante dtosol) throws SQLException, ClassNotFoundException {
        
        String sql = "UPDATE SOLICITANTE SET NOME=?, EMAIL=?, SENHA_DE_ACESSO =? WHERE ID_SOLICITANTE =?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setString(1, dtosol.getNome());
        pst.setString(2, dtosol.getEmail());
        pst.setString(3, dtosol.getSenha());
        pst.setInt(4, dtosol.getId());
        pst.executeUpdate();
        pst.close();
        fechaTudo();
        
    }
    
    public ArrayList<DtoSolicitante> listarSol() throws SQLException, ClassNotFoundException {
        
        ArrayList<DtoSolicitante> sol = new ArrayList<>();
        
        String sql = "SELECT ID_SOLICITANTE,NOME,EMAIL,NIVEL_PERMISSAO,SENHA_DE_ACESSO,DATA_DE_CADASTRO FROM SOLICITANTE";
        ResultSet rs = super.getStatment().executeQuery(sql);
        
        while (rs.next()) {
            DtoSolicitante retorno = new DtoSolicitante();
            retorno.setId(rs.getInt("ID_SOLICITANTE"));
            retorno.setNome(rs.getString("NOME"));
            retorno.setSenha(rs.getString("SENHA_DE_ACESSO"));
            retorno.setEmail(rs.getString("EMAIL"));
            retorno.setPermissao(rs.getString("NIVEL_PERMISSAO"));
            retorno.setData(rs.getDate("DATA_DE_CADASTRO"));
            
            sol.add(retorno);
        }
        rs.close();
        fechaTudo();
        return sol;
    }
    
    public DtoSolicitante buscarSolPorId(DtoSolicitante dtosol) throws SQLException, ClassNotFoundException {
        
        ArrayList<DtoSolicitante> teste = new ArrayList<>();
        DtoSolicitante retorno = new DtoSolicitante();
        String sql = "SELECT ID_SOLICITANTE,NOME,EMAIL,NIVEL_PERMISSAO,SENHA_DE_ACESSO,DATA_DE_CADASTRO FROM SOLICITANTE "
                + "WHERE ID_SOLICITANTE=?";
        PreparedStatement pst = super.getPreparedStatement(sql);
        pst.setInt(1, dtosol.getId());
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            DtoSolicitante aux = new DtoSolicitante();
            retorno.setId(rs.getInt("ID_SOLICITANTE"));
            retorno.setNome(rs.getString("NOME"));
            retorno.setEmail(rs.getString("EMAIL"));
            retorno.setPermissao(rs.getString("NIVEL_PERMISSAO"));
            retorno.setSenha(rs.getString("SENHA_DE_ACESSO"));
            retorno.setData(rs.getDate("DATA_DE_CADASTRO"));
            teste.add(aux);
        }
        
        for (DtoSolicitante dto : teste) {
            if (dto.getId() == dtosol.getId()) {
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
