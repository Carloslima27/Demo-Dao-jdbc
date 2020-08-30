package aplicacao;

import java.util.Date;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
	VendedorDao vd = FabricaDao.criarVendedorDao();
	Vendedor v = vd.encontrarPorId(3);
	System.out.println("=== Teste1: Encontrar por Id ====="); 
	System.out.println(v);
	}

}
