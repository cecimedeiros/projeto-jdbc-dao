package App;

import Negocio.Entidades.Departamento;
import Negocio.Entidades.Vendedor;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Departamento obj = new Departamento(1, "Livros");
        Vendedor v = new Vendedor(21, "bob", "bob@gmail.com", new Date(), 3000.0, obj);

        System.out.println(obj);
        System.out.println(v);

    }
}