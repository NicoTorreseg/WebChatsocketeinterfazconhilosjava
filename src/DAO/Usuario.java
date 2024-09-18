package DAO;

public class Usuario {
	
	private int id;
	private String username;
	private String contrase�a;
	private String nombre;
	
	
	public Usuario(int id, String username, String contrase�a, String nombre) {
		this.id = id;
		this.username = username;
		this.contrase�a = contrase�a;
		this.nombre = nombre;
		
	}

	public Usuario(String username, String contrase�a, String nombre) {
		this.username = username;
		this.contrase�a = contrase�a;
		this.nombre = nombre;
		
	}
	
	public Usuario(String username, String contrase�a) {
		this.username = username;
		this.contrase�a = contrase�a;
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
