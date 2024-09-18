package DAO;

public class Usuario {
	
	private int id;
	private String username;
	private String contraseña;
	private String nombre;
	
	
	public Usuario(int id, String username, String contraseña, String nombre) {
		this.id = id;
		this.username = username;
		this.contraseña = contraseña;
		this.nombre = nombre;
		
	}

	public Usuario(String username, String contraseña, String nombre) {
		this.username = username;
		this.contraseña = contraseña;
		this.nombre = nombre;
		
	}
	
	public Usuario(String username, String contraseña) {
		this.username = username;
		this.contraseña = contraseña;
	}
	
	public Usuario() {

	}

	public int getIdentificador() {
		return id;
	}

	public void setIdentificador(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
