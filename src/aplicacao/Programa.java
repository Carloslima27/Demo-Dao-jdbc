package aplicacao;

import java.util.List;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
	VendedorDao vd = FabricaDao.criarVendedorDao();
	Vendedor v = vd.encontrarPorId(3);
	System.out.println("=== Teste 1: Encontrar por Id ====="); 
	System.out.println(v);
	System.out.println("\n=== Teste 2: Encontrar por Departamento ====="); 
	Departamento departamento = new Departamento(2, null);
	List<Vendedor> lista = vd.encontrarPorDepartamento(departamento);
	lista.forEach(System.out::println);
	System.out.println("\n=== Teste 3: Encontrar Todos ====="); 
	 lista = vd.encontraTodos();
	lista.forEach(System.out::println);
	}

}
