package model.dao;

import java.util.List;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public interface VendedorDao {

	void inserir(Vendedor d);
	void atualizar(Vendedor d);
	void deletarPorId(Integer id);
	Vendedor encontrarPorId(Integer id);
	List<Vendedor> encontraTodos();
	List<Vendedor> encontrarPorDepartamento(Departamento departamento);
}
