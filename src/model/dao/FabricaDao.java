package model.dao;

import db.DB;
import modelo.dao.imple.DepartamentoDaoJdbc;
import modelo.dao.imple.VendedorDaoJdbc;

public class FabricaDao {
	
	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoJdbc(DB.getConnection());
	}
	public static DepartamentoDao criarDepartamento() {
		return new DepartamentoDaoJdbc(DB.getConnection());
	}
}
