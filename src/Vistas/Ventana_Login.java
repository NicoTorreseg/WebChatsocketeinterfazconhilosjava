package Vistas;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DAO.UsuarioDAO;

import DAO.Usuario;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Ventana_Login extends JPanel {
	private JTextField nombreusuario;
	private JPasswordField pasword;

	/**
	 * Create the panel.
	 */
	
	public Ventana_Login() {
		setLayout(null);
		
		nombreusuario = new JTextField();
		nombreusuario.setBounds(244, 57, 86, 20);
		add(nombreusuario);
		nombreusuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(139, 58, 75, 14);
		add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasea.setBounds(114, 124, 86, 14);
		add(lblContrasea);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = nombreusuario.getText();
				String contraseña = String.valueOf(pasword.getPassword());
				Usuario user = new Usuario(username, contraseña);
				UsuarioDAO userDAO = new UsuarioDAO();
				boolean validacion;
				try{
					validacion = userDAO.validarUsuario(user);
				}catch(com.mysql.jdbc.exceptions.jdbc4.CommunicationsException f){
					f.printStackTrace();
					validacion=false;
				}
			
				if (validacion == true) {
				
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					marco.setContentPane(new Ventana_MisGrupos());
					marco.validate();
				} else {
		            JOptionPane.showMessageDialog(null, "Inicio de sesión incorrecto, por favor intentelo nuevamente.\nSi olvidó su contraseña pida su recuperación a un administrador.", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnIngresar.setBounds(179, 188, 121, 23);
		add(btnIngresar);
		
		JButton btnConfiguracin = new JButton("Configuraci\u00F3n");
		btnConfiguracin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) arg0.getSource());
				marco.setContentPane(new Ventana_Configuración());
				marco.validate();
				
			}
		});
		btnConfiguracin.setBounds(33, 238, 167, 23);
		add(btnConfiguracin);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new Ventana_Registrarse());
				marco.validate();
			}
		});
		btnRegistrarse.setBounds(270, 238, 129, 23);
		add(btnRegistrarse);
		
		pasword = new JPasswordField();
		pasword.setBounds(244, 123, 86, 20);
		add(pasword);

	}
	public static void main(String[] args) {
		JFrame marco = new JFrame();
		marco.setTitle("Registrarse");
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setBounds(100, 100, 650, 476);
		marco.setVisible(true);
		marco.setContentPane(new Ventana_Login());
		JDesktopPane desktopPane = new JDesktopPane();
		JInternalFrame internalFrame = new JInternalFrame();        
		JPanel mainPanel = new JPanel();
		mainPanel.add(desktopPane);
		mainPanel.add(internalFrame);
		marco.getContentPane().add(mainPanel);
		
		marco.validate();
	}
	
	
}
