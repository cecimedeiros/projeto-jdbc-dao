package Model.DAO.Impl;

import DB.Conector;
import DB.DbException;
import Model.DAO.DepartamentoDAO;
import Model.Entities.Departamento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO_JDBC implements DepartamentoDAO {


    public Conector c = new Conector();

    public DepartamentoDAO_JDBC(){
        c.conectar();
    }

    @Override
    public void insert(Departamento obj) throws DbException {

        PreparedStatement st = null;

        try{
            st = c.connection.prepareStatement(
                    "INSERT INTO department "
                            + "(Name) "
                            + "VALUES "
                            + "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            //abaixo está como se faz id

            int rows = st.executeUpdate();

            if (rows > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1); //pq 1? pq o id fica na 1ª coluna das chaves(generatedkeys)
                    obj.setId(id); //para que o objeto fique populado com o novo id dele
                }
                c.fecharResultSet(rs);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            c.fecharStatement(st);
        }

    }

    @Override
    public void update(Departamento obj) throws DbException {

        PreparedStatement st = null;

        try{
            st = c.connection.prepareStatement(
                    "UPDATE department "
                            + "SET Name = ? "
                            + "WHERE Id = ?"
            );

            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            c.fecharStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) throws DbException {

        PreparedStatement st = null;

        try{

            st = c.connection.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();

            //e se eu colocar um id que não existe? simplesmente não dá em nada.
            //mas se quiser impedir que isso ocorra é só fazer aquele esquema com o row
            //if row == 0 e colocar uma exceção no escopo, prontin

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            c.fecharStatement(st);
        }

    }

    @Override
    public Departamento findById(Integer id) throws DbException {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = c.connection.prepareStatement("SELECT * FROM department WHERE Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){ //esse if é pq SE tiver 1 printar ele

                Departamento dp = new Departamento();
                dp.setId(rs.getInt("Id"));
                dp.setNome(rs.getString("Name"));
                return dp;

            }
            return null;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            c.fecharResultSet(rs);
            c.fecharStatement(st);
        }
    }

    @Override
    public List<Departamento> findAll() throws DbException {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = c.connection.prepareStatement(
                    "SELECT * FROM department "
                            + "ORDER BY Name");

            rs = st.executeQuery();

            List<Departamento> lista = new ArrayList<>();

            while (rs.next()){ //esse while no lugar do if é pelo fato de poder ter + de 1 obj

                Departamento dp = new Departamento();
                dp.setId(rs.getInt("Id"));
                dp.setNome(rs.getString("Name"));
                lista.add(dp);

            }
            return lista;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            c.fecharResultSet(rs);
            c.fecharStatement(st);
        }

    }

    public void closeConnection(){
        c.fecharConexao();
    }

}
