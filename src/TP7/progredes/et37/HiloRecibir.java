package TP7.progredes.et37;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Cod_Mensajes.Mensaje;
import sockets.progredes.et37.SocketN;
import sockets.progredes.et37.SocketServidorTCP;

public class HiloRecibir extends Thread {
	ArrayList<SocketN> socketss;
	SocketN SocketUser;
	SocketServidorTCP server;
	JTextArea chatBox;
	Mensaje psv;
	JButton btnDesconectar;
	JButton BtnEnviarMensaje;
	JButton btnEscuchar;
	int index;

	HiloRecibir(JTextArea _areaChat, SocketServidorTCP _server, JButton _btnDesconectar, JButton _BtnEnviarMensaje,
			JButton _btnEscuchar) {
		server = _server;
		chatBox = _areaChat;
		btnDesconectar = _btnDesconectar;
		btnEscuchar = _btnEscuchar;
		BtnEnviarMensaje = _BtnEnviarMensaje;
	}

	HiloRecibir(JTextArea _areaChat, SocketN _server, JButton _btnDesconectar, JButton _BtnEnviarMensaje,
			JButton _btnEscuchar, ArrayList<SocketN> sockets, int index) {
		SocketUser = _server;
		chatBox = _areaChat;
		btnDesconectar = _btnDesconectar;
		btnEscuchar = _btnEscuchar;
		BtnEnviarMensaje = _BtnEnviarMensaje;
		socketss = sockets;
		this.index = index;
	}



	public void run() {

		// socketservers.AñadirSockets(server);
		while (true) {
			try {
				try {
					psv = (Mensaje) SocketUser.RecibirObjeto();
					if (psv.getDesconexion()) {
						SocketUser.getSocket().close();
					} else {
						synchronized (socketss) {
							if (socketss.size() > 1) {
								for (SocketN a : socketss) {
									System.out.println("Socket Nº: " + a.getId() + " Usuario :" + a.getUsuario());
									if (SocketUser.getSocket() != a.getSocket()) { //aca evita enviarselo asi mismo
										a.EnviarObjeto(psv);
									}
								}
							}
						}
					}
					// socketservers.RecibirMsjdelosclientyenviarselos(psv);

				} catch (NullPointerException f) {
					f.printStackTrace();
				}

				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						chatBox.append("<" + psv.getNick() + ">:  " + psv.getMensaje() + "\n"); //muestra los mensajes recibidos en el jchat del server
					}
				});

			} catch (SocketException e) {
				// TODO Auto-generated catch block
				// JOptionPane.showMessageDialog(null, "-HILO sv Se ha detectado
				// desconexion del cliente", "Aviso",
				// JOptionPane.INFORMATION_MESSAGE);
				System.out.println(
						"Se desconecto el cliente: " + SocketUser.getUsuario() + "de socket id:" + SocketUser.getId());
				try {
					synchronized (socketss) {
						socketss.remove(SocketUser);

					}

					if (socketss.size() >= 1) {
						Mensaje desconexion = new Mensaje();
						String aux = SocketUser.getUsuario();
						desconexion.setNick("Sala de chat");
						desconexion.setMensaje(aux+" ha salido del chat ");
						for (SocketN a : socketss) {
							a.EnviarObjeto(desconexion);
						}

					}

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							btnDesconectar.setEnabled(false); ///////////////
							BtnEnviarMensaje.setEnabled(false);
							btnEscuchar.setEnabled(false);
						}
					});
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
	

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}
		}

	}

}
