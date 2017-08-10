package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DaoUtil {

    private Connection con = null;

    private Connection getConnection() throws SQLException, ClassNotFoundException {

        if (con == null) {
            String url = "jdbc:mysql://localhost:3306/sistema_aprovacao?zeroDateTimeBehavior=convertToNull";
            String usuario = "root";
            String senha = "2016";
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(url, usuario, senha);
        }

        return con;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException {

        return this.getConnection().prepareStatement(sql);

    }

    public Statement getStatment() throws SQLException, ClassNotFoundException {

        return this.getConnection().createStatement();
    }

    public void fechaTudo() throws SQLException {
        if (con != null) {
            this.con.close();
            this.con = null;

        }

    }
}
