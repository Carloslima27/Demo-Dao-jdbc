package aplicacao;

import model.dao.DepartamentoDao;
import model.dao.FabricaDao;
import modelo.entidades.Departamento;

public class Programa2 {

	public static void main(String[] args) {
		
		DepartamentoDao dd = FabricaDao.criarDepartamento();
		System.out.println("==== Teste 1: Inserir Departamento ==== ");
		Departamento departamento = new Departamento();
		departamento.setNome("Celular");
		//dd.inserir(departamento);
		System.out.println("==== Teste 1: Atualizar Departamento ==== ");
		departamento = new Departamento(7, "Televisão");
		dd.atualizar(departamento);
	}

}
