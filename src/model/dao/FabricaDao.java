package model.dao;

import modelo.dao.imple.VendedorDaoJdbc;

public class FabricaDao {
	
	public VendedorDao criarVendorDao() {
		return new VendedorDaoJdbc();
	}
}
