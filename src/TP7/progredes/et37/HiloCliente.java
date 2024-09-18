package TP7.progredes.et37;

import java.io.IOException;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Cod_Mensajes.Mensaje;
import sockets.progredes.et37.SocketClienteTCP;

public class HiloCliente extends Thread {
	JTextArea chatBox;

	SocketClienteTCP socketcl;
	Mensaje pcl;
	JButton btnDesconectar;
	JButton BtnEnviarMensaje;
	JButton btnConectar;
	JTextField txtNick;

public HiloCliente(JTextArea _areaChat, SocketClienteTCP _cliente, JButton _btnDesconectar, JButton _BtnEnviarMensaje,
			JButton _btnConectar, JTextField textField_Nick) {
		socketcl = _cliente;
		chatBox = _areaChat;
		btnConectar = _btnConectar;
		btnDesconectar = _btnDesconectar;
		BtnEnviarMensaje = _BtnEnviarMensaje;
		txtNick = textField_Nick;
	}

	boolean jj = false;

	public void run() {
		jj = true;
		while (jj) {
			try {

				try {
					pcl = (Mensaje) socketcl.RecibirObjeto();

					if (pcl.getDesconexion()) {
						System.out.println("Usted ha seleccionado salir del chat");
						socketcl.CerrarSerializado2();
					}
					if (pcl.getNombreEnuso()) {
						socketcl.CerrarSerializado2();
						JOptionPane.showMessageDialog(chatBox, "El nick ya esta en uso. \nIngrese uno nuevamente", "Desconectado del server",
								JOptionPane.ERROR_MESSAGE);
						txtNick.setText("");
					}
					

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						chatBox.append("<" + pcl.getNick() + ">:  " + pcl.getMensaje() + "\n");
					}
				});

			} catch (SocketException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(chatBox, "Se desconectó el SV", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
				try {

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							btnDesconectar.setEnabled(false);
							BtnEnviarMensaje.setEnabled(false);
							btnConectar.setEnabled(true);
						}
					});

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jj = false;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				jj = false;
			}

		}
	}
}
