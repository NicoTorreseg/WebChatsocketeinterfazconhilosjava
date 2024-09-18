package TP7.progredes.et37;

import java.io.IOException;
import java.net.BindException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Cod_Mensajes.Mensaje;
import sockets.progredes.et37.SocketN;
import sockets.progredes.et37.SocketServidorTCP;

public class Hilo_Escuchar extends Thread{

	SocketServidorTCP Socketsv;
	int puerto;
	JButton btnDesconectar;
	JButton btnDesconectarSV;
	JButton BtnEnviarMensaje; 
	JButton btnEscuchar;
	JTextArea chatBox;
	SocketN socket;
	String usuario;
	ArrayList<SocketN>sockets;
	
	public Hilo_Escuchar(SocketServidorTCP sv, int puerto) {
		// TODO Auto-generated constructor stub
		this.Socketsv=sv;
		this.puerto=puerto;
		
	}
	public Hilo_Escuchar(SocketServidorTCP socketsv, int parseInt, JButton btnDesconectar, JButton btnEnviarMensaje,
			JButton btnEscuchar, JTextArea chat) {
		this.Socketsv=socketsv;
		this.puerto=parseInt;
		this.btnDesconectarSV=btnDesconectar;
		this.btnEscuchar=btnEscuchar;
		this.BtnEnviarMensaje=btnEnviarMensaje;
		this.chatBox=chat;
		// TODO Auto-generated constructor stub
	}

	
	public Hilo_Escuchar(SocketServidorTCP socketsv, int parseInt, JButton btnDesconectar, JButton btnEnviarMensaje,
			JButton btnEscuchar, JTextArea chat, ArrayList<SocketN>sockets) {
		this.Socketsv=socketsv;
		this.puerto=parseInt;
		this.btnDesconectar=btnDesconectar;
		this.btnEscuchar=btnEscuchar;
		this.BtnEnviarMensaje=btnEnviarMensaje;
		this.chatBox=chat;
		this.sockets=sockets;
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean IniciarHiloRecibir=true;
		try {
			System.out.println("hilo escucha seccion critica");
			System.out.println("Escuchando");
			Socketsv.Escuchar(puerto);
			JOptionPane.showMessageDialog(chatBox, "Se activo la Escucha.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		
			int index=0;
			while(true) {
				
			
			try {
				 {
					synchronized (Socketsv) {     //khe 
						usuario=new String();
						socket=new SocketN();
						 Socketsv.Aceptar();

					System.out.println("Se conectó el cliente de dirección IP: " + Socketsv.IpRemota());
					System.out.println("Se conectó el cliente de puerto: " + Socketsv.puertoRemoto());
					socket.setSocket(Socketsv.getSocket());
					
					 socket.setId(index);
					 
					 
					 try {
						Mensaje Cliente =(Mensaje) socket.RecibirObjeto(); //para validar nombres
						usuario =Cliente.getNick();
						socket.setUsuario(usuario);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					btnDesconectar.setEnabled(true);
					
					BtnEnviarMensaje.setEnabled(false);
					}
					
				}
				System.out.println("afuera del hilo escucha seccion critica");
				
			}catch (BindException e) {
				// TODO Auto-generated catch block
				IniciarHiloRecibir=false;
				JOptionPane.showMessageDialog(chatBox, "El puerto ya esta en uso", "ERROR", JOptionPane.ERROR_MESSAGE);
				btnEscuchar.setEnabled(true);
				
			} 
			catch(SocketException e){
				
				IniciarHiloRecibir=false;
				break;
			}
			
			
			
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				IniciarHiloRecibir=false;
			}
				try {
					IniciarHiloRecibir=ValidarNombre(); //????????????
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				
			
			if(IniciarHiloRecibir){
				Mensaje bienvenida=new Mensaje();
				bienvenida.setNick("Sala de chat");
				bienvenida.setMensaje(socket.getUsuario()+" ha entrado al chat");
				enviarMensajeBienvenida(bienvenida);
				
				sockets.add(socket);
				
				HiloRecibir nuevoHilo = new HiloRecibir(chatBox, socket,btnDesconectar,BtnEnviarMensaje,btnEscuchar,sockets,index);
				new Thread(nuevoHilo).start();
				
			}
			index++;
			}
		}catch (BindException e) {
			// TODO Auto-generated catch block
			IniciarHiloRecibir=false;
			JOptionPane.showMessageDialog(chatBox, "El puerto ya esta en uso", "ERROR", JOptionPane.ERROR_MESSAGE);
			btnEscuchar.setEnabled(true);
			
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	public void enviarMensajeBienvenida(Mensaje bienvenida) throws IOException{
		for(SocketN n: sockets){
			
			n.EnviarObjeto(bienvenida);
		}
	}
	
	public boolean ValidarNombre() throws Exception {
	
		for(SocketN s:sockets){
			if(socket.getUsuario().equals(s.getUsuario())){
				System.out.println("nombre en uso");
				Mensaje mensaje=new Mensaje();
				mensaje.setNick("Sala de chat");
				mensaje.setMensaje("El nick ingresado ya esta en uso");
				mensaje.setNombreEnuso(true);
				socket.EnviarObjeto(mensaje);
				socket.getSocket().close();
				return false;
			}
			}
		return true;
	}
}
