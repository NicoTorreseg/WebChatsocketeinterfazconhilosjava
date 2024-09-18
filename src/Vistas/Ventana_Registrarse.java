package Vistas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DAO.Usuario;
import DAO.UsuarioDAO;


import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;

public class Ventana_Registrarse extends JPanel {
	private JTextField nombrealias;
	private JTextField nombredeusuario;
	private JTextField password;
	private JTextField confirmarpassword;
	
	

	/**
	 * Create the panel.
	 */
	public Ventana_Registrarse() {
		setLayout(null);
		
		JLabel lblNombreOAlias = new JLabel("Nombre o Alias:");
		lblNombreOAlias.setBounds(29, 48, 138, 14);
		add(lblNombreOAlias);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(29, 91, 138, 14);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(29, 143, 138, 14);
		add(lblPassword);
		
		JLabel lblReingresePassword = new JLabel("Reingrese Password: ");
		lblReingresePassword.setBounds(29, 188, 156, 14);
		add(lblReingresePassword);
		
		nombrealias = new JTextField();
		nombrealias.setBounds(221, 45, 86, 20);
		add(nombrealias);
		nombrealias.setColumns(10);
		
		nombredeusuario = new JTextField();
		nombredeusuario.setBounds(221, 88, 86, 20);
		add(nombredeusuario);
		nombredeusuario.setColumns(10);
		
		password = new JTextField();
		password.setBounds(221, 140, 86, 20);
		add(password);
		password.setColumns(10);
		
		confirmarpassword = new JTextField();
		confirmarpassword.setBounds(221, 185, 86, 20);
		add(confirmarpassword);
		confirmarpassword.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirmacion;
				int id = 0;
				String username = nombredeusuario.getText();
				String contraseña = password.getText();
				String nombre = nombrealias.getText();
				
		        if (username == null || username.length() == 0 || contraseña == null || contraseña.length() == 0 || nombre == null || nombre.length() == 0) {
			        JOptionPane.showMessageDialog(null, "Por favor rellene todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(password.getText().equals(confirmarpassword.getText())) {
					confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Alerta", JOptionPane.YES_NO_OPTION);
					if (confirmacion == JOptionPane.YES_OPTION) {
						Usuario user = new Usuario(id, username, contraseña, nombre);
						UsuarioDAO userDAO = new UsuarioDAO();
						userDAO.cargarUsuario(user);
			            JOptionPane.showMessageDialog(null, "El usuario ha sido registrado exitosamente.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				   		
			       	}
				}
					else {
						 JOptionPane.showMessageDialog(null, "La contraseña no coincide, vuelva a ingresar", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRegistrar.setBounds(168, 244, 89, 23);
		add(btnRegistrar);

	}

	
	public static void main(String[] args) {
		JFrame marco = new JFrame();
		marco.setTitle("Registrarse");
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setBounds(100, 100, 650, 476);
		marco.setVisible(true);
		marco.setContentPane(new Ventana_Registrarse());
		JDesktopPane desktopPane = new JDesktopPane();
		JInternalFrame internalFrame = new JInternalFrame();        
		JPanel mainPanel = new JPanel();
		mainPanel.add(desktopPane);
		mainPanel.add(internalFrame);

		marco.getContentPane().add(mainPanel);
		
		marco.validate();
	}
}
