package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;

import Cod_Mensajes.Mensaje;
import TP7.progredes.et37.HiloCliente;
import sockets.progredes.et37.SocketClienteTCP;
import validaciones.progredes.et37.Validacion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaCliente {

	private String Nombreventana = "Ventana del cliente";
	private String username = "Cliente";
	private Validacion validar = new Validacion();

	private JFrame Frame = new JFrame(Nombreventana);
	private JButton BtnEnviarMensaje;
	private JTextField TextfieldMensaje;
	private JTextArea chatBox;

	SocketClienteTCP Socketcl;
	private JPanel panel;
	private JLabel lblPuerto;
	private JLabel lblIpServer;
	private JTextField textFieldIP;
	private JTextField textFieldPuerto;
	private JButton btnConectar;
	private JButton btnDesconectar;
	Mensaje datos = new Mensaje();
	// nick =new JTextField(5);


	private JLabel lblNick;
	private JTextField textField_Nick;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					VentanaCliente ventanacliente = new VentanaCliente();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public VentanaCliente() {


		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel PaneldeAbajo = new JPanel();
		PaneldeAbajo.setBackground(Color.WHITE);
		GridBagLayout gbl_PaneldeAbajo = new GridBagLayout();
		gbl_PaneldeAbajo.columnWidths = new int[] { 0, 69 };
		PaneldeAbajo.setLayout(gbl_PaneldeAbajo);

		TextfieldMensaje = new JTextField(30);
		TextfieldMensaje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				ent();
			}}
		});
		
		TextfieldMensaje.requestFocusInWindow();

		BtnEnviarMensaje = new JButton("Enviar Mensaje");
		BtnEnviarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			ent();
				
			}
		});
		
				
				
		
		chatBox = new JTextArea();
		chatBox.setEditable(false);
		chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
		chatBox.setLineWrap(true);

	

		JScrollPane scrollPane = new JScrollPane(chatBox);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 87, 55, 74, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 30, 32, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblIpServer = new JLabel("Dirreción IP:");
		GridBagConstraints gbc_lblIpServer = new GridBagConstraints();
		gbc_lblIpServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpServer.gridx = 1;
		gbc_lblIpServer.gridy = 0;
		panel.add(lblIpServer, gbc_lblIpServer);

		textFieldIP = new JTextField();
		textFieldIP.setText("127.0.0.1");
		GridBagConstraints gbc_textFieldIP = new GridBagConstraints();
		gbc_textFieldIP.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIP.gridx = 2;
		gbc_textFieldIP.gridy = 0;
		panel.add(textFieldIP, gbc_textFieldIP);
		textFieldIP.setColumns(10);

		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Mensaje mensaje = new Mensaje();
					mensaje.setNick(textField_Nick.getText());
					mensaje.setMensaje("Se desconectó");
					mensaje.setDesconexion(true);
					Socketcl.EnviarObjeto(mensaje);

					Socketcl.CerrarSerializado2();

					btnDesconectar.setEnabled(false);
					BtnEnviarMensaje.setEnabled(false);
					btnConectar.setEnabled(true);

				} 
				catch (IOException e1) {
					//
					e1.printStackTrace();
				}

			}
		});

		//

		//
		GridBagConstraints gbc_btnDesconectar = new GridBagConstraints();
		gbc_btnDesconectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnDesconectar.gridx = 4;
		gbc_btnDesconectar.gridy = 0;
		panel.add(btnDesconectar, gbc_btnDesconectar);

		lblPuerto = new JLabel("Puerto:");
		GridBagConstraints gbc_lblPuerto = new GridBagConstraints();
		gbc_lblPuerto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPuerto.gridx = 1;
		gbc_lblPuerto.gridy = 1;
		panel.add(lblPuerto, gbc_lblPuerto);

		textFieldPuerto = new JTextField();
		textFieldPuerto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		GridBagConstraints gbc_textFieldPuerto = new GridBagConstraints();
		gbc_textFieldPuerto.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPuerto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPuerto.gridx = 2;
		gbc_textFieldPuerto.gridy = 1;
		panel.add(textFieldPuerto, gbc_textFieldPuerto);

		textFieldPuerto.setColumns(10);

		lblNick = new JLabel("Nick:");
		GridBagConstraints gbc_lblNick = new GridBagConstraints();
		gbc_lblNick.insets = new Insets(0, 0, 0, 5);
		gbc_lblNick.gridx = 1;
		gbc_lblNick.gridy = 2;
		panel.add(lblNick, gbc_lblNick);

		textField_Nick = new JTextField();
		textField_Nick.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		textField_Nick.setColumns(10);
		GridBagConstraints gbc_textField_Nick = new GridBagConstraints();
		gbc_textField_Nick.insets = new Insets(0, 0, 0, 5);
		gbc_textField_Nick.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Nick.gridx = 2;
		gbc_textField_Nick.gridy = 2;
		panel.add(textField_Nick, gbc_textField_Nick);

		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socketcl = new SocketClienteTCP();
				String IP = textFieldIP.getText();
				chatBox.setText("");
				try {
					int puerto = Integer.parseInt(textFieldPuerto.getText());

				} catch (NumberFormatException g) {

				}

				if (validar.validarIP(IP) == false) {
					JOptionPane.showMessageDialog(Frame, "Por favor ingrese una IP valida", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					textFieldIP.setText("");
				}
				if (validar.validarNick(textField_Nick.getText())) {
					JOptionPane.showMessageDialog(Frame, "Por favor ingrese un nombre valido", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					textField_Nick.setText("");
				}
				if (validar.validarPuerto(textFieldPuerto.getText()) == false) {
					JOptionPane.showMessageDialog(Frame, "Por favor ingrese un PUERTO valido.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					textFieldPuerto.setText("");
				}

				if (validar.validarNick(textField_Nick.getText()) == false && validar.validarIP(IP)
						&& validar.validarPuerto(textFieldPuerto.getText())) {

					try {

						String nick = textField_Nick.getText();

						Socketcl.Conectar(textFieldIP.getText(), Integer.parseInt(textFieldPuerto.getText()));
						
						btnConectar.setEnabled(false);
						JOptionPane.showMessageDialog(Frame, "Conectado al servidor", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
						System.out.println("[CONEXION ESTABLECIDA] Se conectó al servidor de dirección IP: "
								+ Socketcl.IpRemota());
						System.out.println(
								"[CONEXION ESTABLECIDA] Se conectó al servidor de Puerto: " + Socketcl.puertoRemoto());
						System.out.println("[CONEXION ESTABLECIDA] Se conectó al servidor de Puerto Local: "
								+ Socketcl.Puertolocal());
						BtnEnviarMensaje.setEnabled(true);
						btnDesconectar.setEnabled(true);
						Mensaje user = new Mensaje();
						user.setMensaje("Se conectó");  //Importante para el tema de los nomrbres 
						user.setNick(nick);
						Socketcl.EnviarObjeto(user);
						HiloCliente nuevoHilo = new HiloCliente(chatBox, Socketcl, btnDesconectar, BtnEnviarMensaje,
								btnConectar, textField_Nick);
						new Thread(nuevoHilo).start();

					} catch (UnknownHostException e1) {

						e1.printStackTrace();
					} catch (ConnectException ez) {
						System.out.println("No se pudo conectar intente de nuevo\n");
						JOptionPane.showMessageDialog(Frame, "Imposible Conectar", "ERROR", JOptionPane.ERROR_MESSAGE);
					}

					catch (IOException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.insets = new Insets(0, 0, 0, 5);
		gbc_btnConectar.gridx = 4;
		gbc_btnConectar.gridy = 2;
		panel.add(btnConectar, gbc_btnConectar);

		GridBagConstraints gbc_TextfieldMensaje = new GridBagConstraints();
		gbc_TextfieldMensaje.anchor = GridBagConstraints.LINE_START;
		gbc_TextfieldMensaje.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextfieldMensaje.weightx = 512.0D;
		gbc_TextfieldMensaje.weighty = 1.0D;

		GridBagConstraints gbc_BtnEnviarMensaje = new GridBagConstraints();
		gbc_BtnEnviarMensaje.insets = new Insets(0, 10, 0, 0);
		gbc_BtnEnviarMensaje.anchor = GridBagConstraints.LINE_END;
		gbc_BtnEnviarMensaje.fill = GridBagConstraints.NONE;
		gbc_BtnEnviarMensaje.weightx = 1.0D;
		gbc_BtnEnviarMensaje.weighty = 1.0D;

		PaneldeAbajo.add(TextfieldMensaje, gbc_TextfieldMensaje);
		PaneldeAbajo.add(BtnEnviarMensaje, gbc_BtnEnviarMensaje);

		mainPanel.add(BorderLayout.SOUTH, PaneldeAbajo);

		Frame.getContentPane().add(mainPanel);

		/**
		 * WindowListener exitListener = new WindowAdapter() {
		 * 
		 * @Override public void windowClosing(WindowEvent e) { try {
		 *           Socketcl.Enviar("se desconecto el cliente");
		 *           Socketcl.Cerrar(); } catch (IOException e1) { // TODO
		 *           Auto-generated catch block e1.printStackTrace(); } } };
		 * 
		 *           newFrame.addWindowListener(exitListener);
		 */
		Frame.setSize(400, 500);
		Frame.setVisible(true);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BtnEnviarMensaje.setEnabled(false);
		btnDesconectar.setEnabled(false);

	}

public void ent() {
	try {

		datos.setNick(textField_Nick.getText());
		datos.setMensaje(TextfieldMensaje.getText());
		if (validar.validarMensaje(TextfieldMensaje.getText()) == false) {
			JOptionPane.showMessageDialog(Frame, "Por favor no ingrese un mensaje vacio.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

		else {
			// Socketcl.Enviar(TextfieldMensaje.getText());
			Socketcl.EnviarObjeto(datos);
		}
	} catch (SocketException i) {
		System.out.println("\n[ADVERTENCIA] Se ha detectado desconexion del SV\n");
		JOptionPane.showMessageDialog(Frame, "Se ha detectado desconexion del SV", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
		btnDesconectar.setEnabled(false);
		BtnEnviarMensaje.setEnabled(false);
		btnConectar.setEnabled(true);

	}

	catch (IOException eyr) {

		eyr.printStackTrace();
	}

	if (TextfieldMensaje.getText().length() < 1) {
		// do nothing
	} else if (TextfieldMensaje.getText().equals(".clear")) {
		chatBox.setText("Los mensajes fueron borrados\n");
		TextfieldMensaje.setText("");
	} else {
		chatBox.append("<" + textField_Nick.getText() + ">:  " + TextfieldMensaje.getText() + "\n");
		TextfieldMensaje.setText("");
	}
}

	// class EnviarMensajeButtonListener implements ActionListener {//Cuando
	// hacemos click en el boton enviar
	// public void actionPerformed(ActionEvent event) {
	// agregar aca la wea de tcp
	// try {
	// Socketcl.Enviar(TextfieldMensaje.getText());
	// } catch (IOException e) {
	// TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// if (TextfieldMensaje.getText().length() < 1) {
	// do nothing
	// } else if (TextfieldMensaje.getText().equals(".clear")) {
	// chatBox.setText("Cleared all messages\n");
	// TextfieldMensaje.setText("");
	// } else {
	// chatBox.append("<" + username + ">: " + TextfieldMensaje.getText()
	// + "\n");
	// TextfieldMensaje.setText("");
	// }
	// TextfieldMensaje.requestFocusInWindow();
	// }
	// }

}