import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

public class ClienteDAOImpl implements ClienteDAO{
    private Connection connection;
    @Override
    public Connection connect(String urlConexao) {
        try{
            this.connection = DriverManager.getConnection(urlConexao);
            return connection;
        } catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void createTable(String urlConexao) {
        String sql = "CREATE TABLE IF NOT EXISTS cliente (id integer primary key, nome varchar(40) not null, idade integer, cpf varchar(40) not null, rg varchar(40))";
        try{
            Connection conexaco = connect(urlConexao);
            Statement stm = conexaco.createStatement();
            stm.execute(sql);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, idade, cpf, rg) VALUES(?, ?, ?, ?)";

        try{
            Connection conexaco = connect(url_conexao);
            PreparedStatement pstm = conexaco.prepareStatement(sql);
            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getIdade());
            pstm.setString(3, cliente.getCpf());
            pstm.setString(4, cliente.getRG());

            pstm.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void selectAll(String urlConexao) {
        String sql = "SELECT id, nome, idade, cpf, rg FROM cliente";

        try{
            Connection conexaco = connect(urlConexao);
            Statement stm = conexaco.createStatement();
            ResultSet resultado = stm.executeQuery(sql);

            while(resultado.next()){
                System.out.println(resultado.getInt("id") + "\t" +
                        resultado.getString("nome") + "\t" +
                        resultado.getInt("idade") + "\t" +
                        resultado.getString("cpf") + "\t" +
                        resultado.getString("rg"));
            }


        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        String sql = "UPDATE cliente set nome = ?, idade = ? where id = ?";
        try{
            Connection conexaco = connect(urlConexao);
            PreparedStatement pstm = conexaco.prepareStatement(sql);

            pstm.setString(1, name);
            pstm.setInt(2, idade);
            pstm.setInt(3, id);
            pstm.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(String urlConexao, int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
            Connection conexaco = connect(urlConexao);
            PreparedStatement pstm = conexaco.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.executeUpdate();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}