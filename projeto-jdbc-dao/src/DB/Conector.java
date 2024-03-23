package DB;
import java.sql.*;

public class Conector {
    public static Connection connection = null;

    public void conectar(){
        if (connection == null) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "0000");
                //ali onde tá escrito 'teste' é o nome do schema, se estiver em algo novo lembrar de mudar
                connection = con;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void fecharConexao(){
        try{
            connection.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void fecharStatement(Statement st){
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void fecharResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}