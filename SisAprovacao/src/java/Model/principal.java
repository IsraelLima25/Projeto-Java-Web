package Model;

import Dto.DtoAdministrador;
import Dto.DtoAprovador;
import Dto.DtoCentrodeCusto;
import Dto.DtoSolicitacao;
import Dto.DtoSolicitante;
import controller.CentrodeCustoControl;
import controller.SingleUserLog;
import Model.DaoCentro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class principal {

    public static void main(String[] args) throws SQLException, SQLException, ClassNotFoundException {
        DaoSolicitacao dao = new DaoSolicitacao();
        DtoSolicitante dtosoli = new DtoSolicitante(1);
        ArrayList<DtoSolicitacao> ret = dao.listarSolicitacoesPendentesPorSoli(dtosoli);
        
        System.out.println(ret.size());
        
        
        

    }
}
