package App;

import DB.DbException;
import Model.DAO.DAOFactory;
import Model.DAO.VendedorDAO;
import Model.Entities.Departamento;
import Model.Entities.Vendedor;

import java.util.Date;
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

        System.out.println("\n== TESTANDO UPDATE ==\n");
        Vendedor vendUp = vDAO.findById(8);
        vendUp.setName("Cecil");
        vDAO.update(vendUp);
        //isso msm, quando for dar um set precisa fazer update pra "refazer" os dados la na tabela
        //é assim que se usa esta porra de método
        System.out.println("Update updatado!");

    }
}