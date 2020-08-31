package aplicacao;

import java.util.List;

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
		System.out.println("==== Teste 2: Atualizar Departamento ==== ");
		//departamento = new Departamento(7, "Televisão");
		//dd.atualizar(departamento);
		System.out.println("==== Teste 3: Deletar Departamento ==== ");
		//dd.deletarPorId(6);
		System.out.println("==== Teste 4: Encontrar por Id Departamento ==== ");
		//departamento = dd.encaontraPorId(2);
		//System.out.println(departamento);
		System.out.println("==== Teste 5: encontrar todos os Departamento ==== ");
		List<Departamento> lista = dd.encontraTodos();
		lista.forEach(System.out::println);
	}

}
