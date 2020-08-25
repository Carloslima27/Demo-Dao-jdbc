package model.dao;

import java.util.List;

import modelo.entidades.Departamento;

public interface DepartamentoDao {
	
	void inserir(Departamento d);
	void atualizar(Departamento d);
	void deletarPorId(Integer id);
	Departamento encaontraPorId(Integer id);
	List<Departamento> encontraTodos();
}
