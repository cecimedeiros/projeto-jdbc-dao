package Negocio.Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String email;
    private Date dataDeNascimento;
    private Double salario;
    private Departamento departamento;

    public Vendedor(){}

    public Vendedor(Integer id, String name, String email, Date dataDeNascimento, Double salario, Departamento departamento) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
        this.salario = salario;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return Objects.equals(id, vendedor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                ", salario=" + salario +
                ", departamento=" + departamento +
                '}';
    }
}