package Model.DAO;

import DB.DbException;
import Model.Entities.Departamento;

import java.util.List;

public interface DepartamentoDAO {

    void insert(Departamento obj) throws DbException;
    void update(Departamento obj) throws DbException;
    void deleteById(Integer id) throws DbException;
    Departamento findById(Integer id) throws DbException;
    List<Departamento> findAll() throws DbException;

}
