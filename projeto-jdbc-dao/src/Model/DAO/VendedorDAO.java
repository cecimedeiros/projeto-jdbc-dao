package Model.DAO;

import Model.Entities.Departamento;
import Model.Entities.Vendedor;

import java.util.List;

public interface VendedorDAO {

    void insert(Vendedor obj);
    void update(Vendedor obj);
    void deleteById(Integer id);
    Vendedor findById(Integer id);
    List<Vendedor> findAll();

}
