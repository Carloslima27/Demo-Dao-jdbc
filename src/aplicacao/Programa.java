package aplicacao;


import java.util.Date;
import java.util.List;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
	
		
	VendedorDao vd = FabricaDao.criarVendedorDao();
	Vendedor v = vd.encontrarPorId(3);
	System.out.println("=== Teste 1: Encontrar por Id do vendedor ====="); 
	System.out.println(v);
	System.out.println("\n=== Teste 2: Encontrar por Departamento do vendedor ====="); 
	Departamento departamento = new Departamento(2, null);
	List<Vendedor> lista = vd.encontrarPorDepartamento(departamento);
	lista.forEach(System.out::println);
	System.out.println("\n=== Teste 3: Encontrar Todos os vendedores  ====="); 
	 lista = vd.encontraTodos();
	lista.forEach(System.out::println);
	System.out.println("\n=== Teste 4: Inserir vendedor ====="); 
	Vendedor vendedor = new Vendedor("João", "João@gmail.com", new Date(), 3000.00, new Departamento(1, null) );
	vd.inserir(vendedor);
	System.out.println(vendedor);
	
	}

}
