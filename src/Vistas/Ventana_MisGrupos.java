package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

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

import com.sun.xml.internal.ws.api.server.Container;

import Cod_Mensajes.Mensaje;
import TP7.progredes.et37.Hilo_Escuchar;
import sockets.progredes.et37.SocketClienteTCP;
import sockets.progredes.et37.SocketN;
import sockets.progredes.et37.SocketServidorTCP;
import validaciones.progredes.et37.Validacion;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Ventana_MisGrupos extends JFrame {
	JTextArea chatBox;

	private String Nombreventana = "Ventana del Servidor";
	private String username = "Servidor";
	private Validacion validar = new Validacion();
	private JFrame Frame = new JFrame(Nombreventana);
	private JButton btnEnviarMensaje;
	private JTextField textFieldMensaje;
	private JPanel panel;
	private JLabel lblPuerto;
	private String nick_usuario = null;
	// nick =new JTextField(5);

	private JLabel nick;

	ArrayList<SocketN> sockets; // user y id
	SocketServidorTCP Socketsv;
	private JPanel panel_1;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JButton btnEliminarGrupo;
	private JButton btnCrearGrupo;
	private JButton btnSalirDelGrupo;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Ventana_MisGrupos ventanaservidor = new Ventana_MisGrupos();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public Ventana_MisGrupos() {

		Mensaje datos = new Mensaje();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel PaneldeAbajo = new JPanel();
		PaneldeAbajo.setBackground(Color.WHITE);
		GridBagLayout gbl_PaneldeAbajo = new GridBagLayout();
		gbl_PaneldeAbajo.columnWidths = new int[] { 0, 69 };
		PaneldeAbajo.setLayout(gbl_PaneldeAbajo);

		textFieldMensaje = new JTextField(30);
		textFieldMensaje.requestFocusInWindow();

		btnEnviarMensaje = new JButton("Enviar Mensaje");
		btnEnviarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				// agregar aca la wea de tcp
				try {

					datos.setNick(nick.getText());
					datos.setMensaje(textFieldMensaje.getText());

					if (validar.validarMensaje(textFieldMensaje.getText()) == false) {
						JOptionPane.showMessageDialog(Frame, "Por favor no ingrese un mensaje vacio.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {

						Socketsv.Enviar(textFieldMensaje.getText());
						// servers.enviarMensajePropioAtodos(datos);
						Socketsv.EnviarObjeto(datos);
					}
				} catch (SocketException i) {
					System.out.println("\n[ADVERTENCIA] Se ha detectado desconexion del SV\n");
					JOptionPane.showMessageDialog(Frame, "Se ha detectado desconexion del CL", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
			
					btnEnviarMensaje.setEnabled(false);
					

				}

				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (textFieldMensaje.getText().length() < 1) {
					// do nothing
				} else if (textFieldMensaje.getText().equals(".clear")) {
					chatBox.setText("Los mensajes fueron borrados\n");
					textFieldMensaje.setText("");
				} else {// esto agrega lineas al chat de los mensajes
					chatBox.append("<" + nick.getText() + ">:  " + textFieldMensaje.getText() + "\n");
					textFieldMensaje.setText("");
				}
				// textFieldMensaje.requestFocusInWindow();
			}
		});

		nick = new JLabel();
		nick.setText(nick_usuario);
		getContentPane().add(nick);

		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 87, 55, 74, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 30, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		btnCrearGrupo = new JButton("Entrar a grupo");
		btnCrearGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new VentanaCliente());
				

			}
		});
		GridBagConstraints gbc_btnCrearGrupo = new GridBagConstraints();
		gbc_btnCrearGrupo.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrearGrupo.gridx = 5;
		gbc_btnCrearGrupo.gridy = 0;
		panel.add(btnCrearGrupo, gbc_btnCrearGrupo);

		GridBagConstraints gbc_textFieldMensaje = new GridBagConstraints();
		gbc_textFieldMensaje.anchor = GridBagConstraints.LINE_START;
		gbc_textFieldMensaje.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMensaje.weightx = 512.0D;
		gbc_textFieldMensaje.weighty = 1.0D;

		GridBagConstraints gbc_btnEnviarMensaje = new GridBagConstraints();
		gbc_btnEnviarMensaje.insets = new Insets(0, 10, 0, 0);
		gbc_btnEnviarMensaje.anchor = GridBagConstraints.LINE_END;
		gbc_btnEnviarMensaje.fill = GridBagConstraints.NONE;
		gbc_btnEnviarMensaje.weightx = 1.0D;
		gbc_btnEnviarMensaje.weighty = 1.0D;

		PaneldeAbajo.add(textFieldMensaje, gbc_textFieldMensaje);
		PaneldeAbajo.add(btnEnviarMensaje, gbc_btnEnviarMensaje);

		mainPanel.add(BorderLayout.SOUTH, PaneldeAbajo);

		Frame.getContentPane().add(mainPanel);

		/**
		 * WindowListener exitListener = new WindowAdapter() {
		 * 
		 * @Override public void windowClosing(WindowEvent e) { try {
		 * 
		 *           Socketsv.Cerrar(); } catch (IOException e1) { // TODO
		 *           Auto-generated catch block e1.printStackTrace(); } } };
		 *           newFrame.addWindowListener(exitListener);
		 */
		Frame.setSize(519, 312);
		Frame.setVisible(true);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnEnviarMensaje.setEnabled(false);

		btnEliminarGrupo = new JButton("Unirse a grupo");
		btnEliminarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
				lblPuerto = new JLabel("Mis Grupos");
				GridBagConstraints gbc_lblPuerto = new GridBagConstraints();
				gbc_lblPuerto.insets = new Insets(0, 0, 5, 5);
				gbc_lblPuerto.gridx = 2;
				gbc_lblPuerto.gridy = 1;
				panel.add(lblPuerto, gbc_lblPuerto);
		GridBagConstraints gbc_btnEliminarGrupo = new GridBagConstraints();
		gbc_btnEliminarGrupo.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminarGrupo.gridx = 5;
		gbc_btnEliminarGrupo.gridy = 1;
		panel.add(btnEliminarGrupo, gbc_btnEliminarGrupo);
		
		btnSalirDelGrupo = new JButton("Salir del grupo");
		GridBagConstraints gbc_btnSalirDelGrupo = new GridBagConstraints();
		gbc_btnSalirDelGrupo.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalirDelGrupo.gridx = 5;
		gbc_btnSalirDelGrupo.gridy = 2;
		panel.add(btnSalirDelGrupo, gbc_btnSalirDelGrupo);

		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 320, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "GRUPO", "id" }));
		scrollPane_1.setViewportView(table);

	}

	// class EnviarMensajeButtonListener implements ActionListener {//Cuando
	// hacemos click en el boton
	// public void actionPerformed(ActionEvent event) {

	// agregar aca la wea de tcp

	// if (textFieldMensaje.getText().length() < 1) {
	// do nothing
	// } else if (textFieldMensaje.getText().equals(".clear")) {
	// chatBox.setText("Los mensajes fueron borrados\n");
	// textFieldMensaje.setText("");
	// } else {//esto agrega lineas al chat de los mensajes
	// chatBox.append("<" + username + ">: " + textFieldMensaje.getText()
	// + "\n");
	// textFieldMensaje.setText("");
	// }
	// textFieldMensaje.requestFocusInWindow();
	// }
	// }

}
