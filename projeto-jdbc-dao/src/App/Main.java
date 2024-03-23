package App;

import DB.DbException;
import Model.DAO.DAOFactory;
import Model.DAO.VendedorDAO;
import Model.Entities.Vendedor;

import java.util.List;

public class Main {
    public static void main(String[] args) throws DbException {

        VendedorDAO vDAO = DAOFactory.createVendedorDAO();
        Vendedor v = vDAO.findById(3);

        System.out.println(v);

        List<Vendedor> lista = vDAO.findAll();

        for (Vendedor vd : lista) {
            System.out.println(vd);
        }

    }
}