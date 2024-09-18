package TP7.progredes.et37;

import java.io.Serializable;

public class Configuracion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8689733836794697178L;
	public Configuracion(String ip,int puerto){
		this.ip=ip;
		
		this.puerto=puerto;
	}

	 private String  ip;
	 private int puerto;
	public String getip() {
		return ip;
	}
	public void setip(String nombre) {
		this.ip = nombre;
	}
	public int getPuerto() {
		return puerto;
	}
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	 
}
