package Model.DAO;

import DB.DbException;
import Model.Entities.Departamento;
import Model.Entities.Vendedor;

import java.util.List;

public interface VendedorDAO {

    void insert(Vendedor obj) throws DbException;
    void update(Vendedor obj);
    void deleteById(Integer id);
    Vendedor findById(Integer id) throws DbException;
    List<Vendedor> findAll() throws DbException;
    List<Vendedor> findByDepartamento(Departamento dp) throws DbException;

}
