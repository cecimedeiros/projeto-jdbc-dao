package Model.DAO.Impl; //esse Impl é de implements

import DB.Conector;
import DB.DbException;
import Model.DAO.VendedorDAO;
import Model.Entities.Departamento;
import Model.Entities.Vendedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDAO_JDBC implements VendedorDAO {

    public Conector c = new Conector();

    public VendedorDAO_JDBC(){
        c.conectar();
    }

    @Override
    public void insert(Vendedor obj) throws DbException {

        PreparedStatement st = null;

        try{
            st = c.connection.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getDataDeNascimento().getTime()));
            st.setDouble(4, obj.getSalario());
            st.setInt(5, obj.getDepartamento().getId());

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
    public void update(Vendedor obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Vendedor findById(Integer id) throws DbException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = c.connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){ //esse if é pq SE tiver 1 printar ele

                Departamento dp = instantiateDepartamento(rs);
                Vendedor vd = instantiateVendedor(rs, dp);
                return vd;

            }
            return null;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            c.fecharResultSet(rs);
            c.fecharStatement(st);
        }
    }

    //isso aqui é como se transforma uma linha do banco de dados em obj Java conforme meu banco de dados teste
    private Departamento instantiateDepartamento(ResultSet rs) throws SQLException{
        Departamento dp = new Departamento();
        dp.setId(rs.getInt("DepartmentId"));
        dp.setNome(rs.getString("DepName"));
        return dp;
    }

    private Vendedor instantiateVendedor(ResultSet rs, Departamento dp) throws SQLException{
        Vendedor vd = new Vendedor();
        vd.setId(rs.getInt("Id"));
        vd.setName(rs.getString("Name"));
        vd.setEmail(rs.getString("Email"));
        vd.setSalario(rs.getDouble("BaseSalary"));
        vd.setDataDeNascimento(rs.getDate("BirthDate"));
        vd.setDepartamento(dp);
        return vd;
    }

    @Override
    public List<Vendedor> findAll() throws DbException {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = c.connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "ORDER BY Name");

            rs = st.executeQuery();

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()){ //esse while no lugar do if é pelo fato de poder ter + de 1 obk

                Departamento dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instantiateDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Vendedor vd = instantiateVendedor(rs, dep);
                lista.add(vd);
            }
            return lista;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            c.fecharResultSet(rs);
            c.fecharStatement(st);
        }
    }

    @Override
    public List<Vendedor> findByDepartamento(Departamento dp) throws DbException {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = c.connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE DepartmentId = ? "
                            + "ORDER BY Name");

            st.setInt(1, dp.getId());
            rs = st.executeQuery();

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()){ //esse while no lugar do if é pelo fato de poder ter + de 1 obk

                Departamento dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instantiateDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Vendedor vd = instantiateVendedor(rs, dep);
                lista.add(vd);
            }
            return lista;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            c.fecharResultSet(rs);
            c.fecharStatement(st);
        }

    }
}
