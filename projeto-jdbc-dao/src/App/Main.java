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

        System.out.println("\n== TESTANDO INSERÇÃO ==\n");
        Departamento dp = new Departamento(2, null); //departamento nem precisa ter nome já que é só pra esse teste e fds
        //eu fui tentar fazer gracinha nessa linha 27 (coloquei um id 69) e descobri que só permite colocar departamento que existakkk
        Vendedor novoVendedor = new Vendedor(null, "Lori Delicia", "loridelicia@emailceta.com", new Date(), 9000.0, dp);
        //id tem que ser nulo pois o próprio insert já cria essa desgraça; ali ta new Date pq to com preguiça de criar data
        vDAO.insert(novoVendedor);
        System.out.println("Botada realizada! Novo ID: " + novoVendedor.getId());

    }
}