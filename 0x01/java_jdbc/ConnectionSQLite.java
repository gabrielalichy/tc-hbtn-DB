import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionSQLite {

    private static Connection connection;
    private static String NOME_DB = "sqlite_database_2022.db";

    public static void main(String[] args) {
        initConnection();
    }

    private static void initConnection(){
        try {
            Class.forName("org.sql.JDBC");
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", NOME_DB));
        } catch (ClassNotFoundException e) {
            System.out.println("Driver expecificado não encontrado.");
            Logger.getLogger(ConnectionSQLite.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Bando de Dados.");
            Logger.getLogger(ConnectionSQLite.class.getName()).log(Level.SEVERE, null, e);
         }
        }

        public void closeConnection(Connection connection){
        try {
            connection.close();
        }catch (SQLException ex){
            Logger.getLogger((ConnectionSQLite.class.getName())).log(Level.SEVERE, null, ex);
        }
    }

}
