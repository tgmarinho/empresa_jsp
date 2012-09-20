package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojos.Funcionario;
import pojos.Setor;

public class GenericDao {

	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private String url;
	private String user;
	private String password;
	private String driver;
	private String sql;
	private ResultSet rs;

	public GenericDao() {

		try {
			this.driver = "org.postgresql.Driver";
			this.url = "jdbc:postgres:empresa";
			this.user = "postgres";
			this.password = "postgres";
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserirFuncionario(Funcionario funcionario) {
		try {
			sql = "INSERT INTO funcionario VALUES (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, funcionario.getId());
			ps.setString(2, funcionario.getNome());
			ps.setInt(3, funcionario.getSetor().getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserirSetor(Setor setor) {
		try {
			sql = "INSERT INTO setor VALUES (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, setor.getId());
			ps.setString(2, setor.getNome());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletarFuncionario(Funcionario funcionario) {
		try {
			sql = "DELETE FROM funcionario WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, funcionario.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarSetor(Setor setor) {
		try {
			sql = "DELETE FROM setor WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, setor.getId());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Funcionario> buscarTodosFuncionarios() {

		try {
			st = conn.createStatement();
			sql = "SELECT * FROM funcionario";
			rs = st.executeQuery(sql);

			List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setSetor(new Setor());
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.getSetor().setId(rs.getInt("id_setor"));
				listaFuncionarios.add(funcionario);
			}
			return listaFuncionarios;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Setor> buscarTodosSetores() {

		try {
			st = conn.createStatement();
			sql = "SELECT * FROM setor";
			rs = st.executeQuery(sql);

			List<Setor> setores = new ArrayList<Setor>();

			while (rs.next()) {
				Setor setor = new Setor();
				setor.setId(rs.getInt("id"));
				setor.setNome(rs.getString("nome"));
				setores.add(setor);
			}
			return setores;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
