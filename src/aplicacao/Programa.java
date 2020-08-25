package aplicacao;

import java.util.Date;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		Departamento d = new Departamento(1, "Eletronicos");
		Vendedor v = new Vendedor(2, "Alex", "Alex@gmail.com", new Date(), 3000.00, d);
		System.out.println(v);

	}

}
