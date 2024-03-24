package Model.DAO;

import DB.Conector;
import DB.DbException;
import Model.DAO.Impl.DepartamentoDAO_JDBC;
import Model.DAO.Impl.VendedorDAO_JDBC;

public class DAOFactory {

    public static VendedorDAO createVendedorDAO() throws DbException {
        return new VendedorDAO_JDBC();
        //retorna o tipo da interface mas instancia o tipo da implementação
    }

    public static DepartamentoDAO createDepartamentoDAO() throws DbException {
        return new DepartamentoDAO_JDBC();
        //retorna o tipo da interface mas instancia o tipo da implementação
    }

}
