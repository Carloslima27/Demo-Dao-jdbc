package modelo.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import modelo.entidades.Departamento;

public class DepartamentoDaoJdbc implements DepartamentoDao{
	private Connection conn;
	public DepartamentoDaoJdbc(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void inserir(Departamento d) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO Department"+" (Name)"+"VALUES"+"(?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, d.getNome());
			int inseridos = ps.executeUpdate();
			if(inseridos > 0) {
				throw new DbException("Departamento Inserido!");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.fecharStatement(ps);
		}
		
	}

	@Override
	public void atualizar(Departamento d) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE Department "+"SET Name = ? "+"Where Id = ? ");
			ps.setString(1, d.getNome());
			ps.setInt(2, d.getId());
			int atualizados = ps.executeUpdate();
			if(atualizados > 0) {
				throw new DbException("Atualizado!");
			}else {
				throw new DbException("Id Não Existe!");
			}
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
			ps = conn.prepareStatement("DELETE FROM Department "+"WHERE Id = ? ");
			ps.setInt(1, id);
			int deletados = ps.executeUpdate();
			if(deletados > 0) {
				throw new DbException("Deletado!");
			}else {
				throw new DbException("Id não existe!");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.fecharStatement(ps);
		}
		
	}

	@Override
	public Departamento encaontraPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet r = null;
		try {
			ps = conn.prepareStatement("SELECT * from Department "+"WHERE Id = ? ");
			ps.setInt(1, id);
			r = ps.executeQuery();
			Departamento dep = new Departamento();
			while(r.next()) {
				dep.setId(r.getInt("Id"));
				dep.setNome(r.getString("Name"));
			}
			return dep;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.fecharStatement(ps);
			DB.fecharResultSet(r);
		}
	}

	@Override
	public List<Departamento> encontraTodos() {
	PreparedStatement ps = null;
	ResultSet r = null;
	try {
		ps = conn.prepareStatement("SELECT * FROM Department");
		r = ps.executeQuery();
		List<Departamento> lista = new ArrayList<>();
		
		while(r.next()) {
			Departamento dep = new Departamento();
			dep.setId(r.getInt("Id"));
			dep.setNome(r.getString("Name"));
			lista.add(dep);
		}
		return lista;
	}catch(SQLException e) {
		throw new DbException(e.getMessage());
	}finally {
		DB.fecharStatement(ps);
		DB.fecharResultSet(r);
	}
		
	}
	public Connection getConn() {
		return conn;
	}

}
