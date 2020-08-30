package model.dao;

import db.DB;
import modelo.dao.imple.VendedorDaoJdbc;

public class FabricaDao {
	
	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoJdbc(DB.getConnection());
	}
}
