package modelo.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class VendedorDaoJdbc implements VendedorDao{
	private Connection conn;
	
	public VendedorDaoJdbc(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConn() {
		return conn;
	}

	@Override
	public void inserir(Vendedor v) {
		PreparedStatement ps = null;
		 
		try {
			ps = conn.prepareStatement("INSERT INTO seller "
					+"(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+"VALUES "
					+"(?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, v.getNome());
			ps.setString(2, v.getEmail());
			ps.setDate(3, new java.sql.Date((v.getDataNasc().getTime())));
			ps.setDouble(4, v.getSalarioBase());
			ps.setInt(5, v.getDepartamento().getId());
			int adicionados = ps.executeUpdate();

			if(adicionados > 0) {
			ResultSet r = ps.getGeneratedKeys();
				while(r.next()) {
					v.setId(r.getInt(1));
				}
				DB.fecharResultSet(r);
			}else {
				throw new DbException("Erro inesperado! nenhum vendedor adicionado");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			
			DB.fecharStatement(ps);
		}
		
	}

	@Override
	public void atualizar(Vendedor v) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE seller " 
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ? ");
			ps.setString(1, v.getNome());
			ps.setString(2, v.getEmail());
			ps.setDate(3, new java.sql.Date(v.getDataNasc().getTime()));
			ps.setDouble(4, v.getSalarioBase());
			ps.setInt(5, v.getDepartamento().getId());
			ps.setInt(6, v.getId());
			ps.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage()); 
		}finally {
			DB.fecharStatement(ps);
		}
		
	}

	@Override
	public void deletarPorId(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM seller " + 
					"WHERE Id = ? "   );
			ps.setInt(1, id);
			int deletadas = ps.executeUpdate();
			if(deletadas == 0) {
				throw new DbException("Id não existe!");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.fecharStatement(ps);
		}
		
	}

	@Override
	public Vendedor encontrarPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet r = null;
		try {
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE seller.Id = ? ");
			ps.setInt(1, id);
			r = ps.executeQuery();
			while(r.next()) {
				Departamento dep = instanciarDepartamento(r);
				Vendedor v = instanciarVendedor(r, dep);
				return v;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.fecharStatement(ps);
			DB.fecharResultSet(r);
			
		}
		
		
	}

	private Vendedor instanciarVendedor(ResultSet r, Departamento dep) throws SQLException {
		Vendedor v = new Vendedor();
		v.setId(r.getInt("Id"));
		v.setNome(r.getString("Name"));
		v.setEmail(r.getString("Email"));
		v.setDataNasc(r.getDate("BirthDate"));
		v.setSalarioBase(r.getDouble("BaseSalary"));
		v.setDepartamento(dep);
		return v;
	}

	private Departamento instanciarDepartamento(ResultSet r) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(r.getInt("DepartmentId"));
		dep.setNome(r.getString("DepName"));
		return dep;
	}

	@Override
	public List<Vendedor> encontraTodos() {
		PreparedStatement ps = null;
		ResultSet r = null;
		try {
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "+
					"FROM seller INNER JOIN department "+
					"ON seller.DepartmentId = department.Id "+
					"ORDER BY Id ");
		r = ps.executeQuery();
		List<Vendedor> lista = new ArrayList<>();
		Map<Integer, Departamento> map = new HashMap<>();
		while(r.next()) {
			Departamento dep = map.get(r.getInt("DepartmentId"));
			if(dep == null) {
				dep = instanciarDepartamento(r);
				map.put(r.getInt("DepartmentId"), dep);
			}
			Vendedor obj = instanciarVendedor(r, dep);
			lista.add(obj);
		}
		return lista;
		

		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.fecharResultSet(r);
			DB.fecharStatement(ps);
		}
	}

	@Override
	public List<Vendedor> encontrarPorDepartamento(Departamento departamento) {
		
		PreparedStatement ps = null;
		ResultSet r = null;
		try {
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "+
					"FROM seller INNER JOIN department "+
					"ON seller.DepartmentId = department.Id "+
					"WHERE DepartmentId = ? "+
					"ORDER BY Name ");
		ps.setInt(1, departamento.getId());
		r = ps.executeQuery();
		List<Vendedor> lista = new ArrayList<>();
		Map<Integer, Departamento> map = new HashMap<>();
		while(r.next()) {
			Departamento dep = map.get(r.getInt("DepartmentId"));
			if(dep == null) {
				dep = instanciarDepartamento(r);
				map.put(r.getInt("DepartmentId"), dep);
			}
			Vendedor obj = instanciarVendedor(r, dep);
			lista.add(obj);
		}
		return lista;
		

		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.fecharResultSet(r);
			DB.fecharStatement(ps);
		}
	}

}
