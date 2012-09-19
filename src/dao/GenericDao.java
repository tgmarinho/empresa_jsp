package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	public void inserirFuncionario() {

	}

	public void inserirSetor() {

	}

	public void deletarFuncionario(Funcionario funcionario) {

	}

	public void deletarSetor(Setor setor) {

	}

	public List<Funcionario> buscarTodosFuncionarios() {

		return new ArrayList<Funcionario>();
	}

	public List<Setor> buscarTodosSetores() {

		return new ArrayList<Setor>();
	}

}
