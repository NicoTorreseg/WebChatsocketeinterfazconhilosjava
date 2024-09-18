package TP7.progredes.et37;




import java.io.Serializable;

public class Mensaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 781629716005335703L;
	
	private String nick;
	private String mensaje;//max 100%
	private boolean desconexion=false;
	private boolean nombreEnuso=false;
	
	
	
	public String getNick() {
		return nick;
	}




	public void setNick(String nick) {
		this.nick = nick;
	}




	public String getMensaje() {
		return mensaje;
	}




	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}




	public Mensaje(String _nick, String _mensaje){
		nick=_nick;
		mensaje=_mensaje;
	
		
	}




	public Mensaje() {
		// TODO Auto-generated constructor stub
	}




	public boolean getDesconexion() {
		return desconexion;
	}




	public void setDesconexion(boolean desconexion) {
		this.desconexion = desconexion;
	}




	public boolean getNombreEnuso() {
		return nombreEnuso;
	}




	public void setNombreEnuso(boolean nombreEnuso) {
		this.nombreEnuso = nombreEnuso;
	}
}
