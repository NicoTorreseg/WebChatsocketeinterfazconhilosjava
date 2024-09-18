package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.Usuario;

public class UsuarioDAO {

	public ArrayList<Usuario> listarUsuarios() {
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/TP7";
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "");
			String sql = "SELECT * FROM usuarios";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String contraseña = rs.getString(3);
				String nombre = rs.getString(4);
			
				Usuario user = new Usuario(id, username, contraseña, nombre);
				listaUsuarios.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaUsuarios;
	}
	
	public void cargarUsuario(Usuario u) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/TP7";
		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "");
			String sql = "INSERT INTO usuarios (username, contraseña, nombre) VALUES (?, ?, ?)";
			java.sql.PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, u.getUsername());
			pStmt.setString(2, u.getContraseña());
			pStmt.setString(3, u.getNombre());
			

			pStmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean validarUsuario(Usuario user)throws com.mysql.jdbc.exceptions.jdbc4.CommunicationsException {
		boolean respuesta = false;
		ArrayList<Usuario> listaUsuarios = listarUsuarios();
		for (Usuario uFor : listaUsuarios) {
			String username = uFor.getUsername();
			String contraseña = uFor.getContraseña();
			if (user.getUsername().equals(username) && user.getContraseña().equals(contraseña)) {
				respuesta = true;
			}
		}
		return respuesta;
	}

	
}
