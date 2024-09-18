package Vistas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import TP7.progredes.et37.Binary_file;
import TP7.progredes.et37.Configuracion;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Ventana_Configuración extends JPanel {
	private JTextField textField_puerto;
	private JTextField textField_sv;

	/**
	 * Create the panel.
	 */
	
	public Ventana_Configuración() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{57, 95, 1, 86, 88, 69, 0};
		gridBagLayout.rowHeights = new int[]{66, 28, 32, 24, 64, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblServidor = new JLabel("Servidor:");
		lblServidor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblServidor = new GridBagConstraints();
		gbc_lblServidor.anchor = GridBagConstraints.EAST;
		gbc_lblServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblServidor.gridx = 2;
		gbc_lblServidor.gridy = 1;
		add(lblServidor, gbc_lblServidor);
		
		textField_sv = new JTextField();
		GridBagConstraints gbc_textField_sv = new GridBagConstraints();
		gbc_textField_sv.insets = new Insets(0, 0, 5, 5);
		gbc_textField_sv.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_sv.gridx = 3;
		gbc_textField_sv.gridy = 1;
		add(textField_sv, gbc_textField_sv);
		textField_sv.setColumns(10);
		
		JButton btnGuardar = new JButton("guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Binary_file f=new Binary_file("config",false);
				Configuracion conf=new Configuracion(textField_sv.getText(), Integer.parseInt(textField_puerto.getText()));
				try {
					f.Escribir(conf);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardar.gridx = 4;
		gbc_btnGuardar.gridy = 2;
		add(btnGuardar, gbc_btnGuardar);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblPuerto = new GridBagConstraints();
		gbc_lblPuerto.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblPuerto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPuerto.gridx = 2;
		gbc_lblPuerto.gridy = 3;
		add(lblPuerto, gbc_lblPuerto);
		
		textField_puerto = new JTextField();
		GridBagConstraints gbc_textField_puerto = new GridBagConstraints();
		gbc_textField_puerto.insets = new Insets(0, 0, 5, 5);
		gbc_textField_puerto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_puerto.gridx = 3;
		gbc_textField_puerto.gridy = 3;
		add(textField_puerto, gbc_textField_puerto);
		textField_puerto.setColumns(10);

	}
	public static void main(String[] args) {
		JFrame marco = new JFrame();
		marco.setTitle("conf");
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setBounds(100, 100, 500, 300);
		marco.setVisible(true);
		marco.setContentPane(new Ventana_Configuración());
      
		JPanel mainPanel = new JPanel();

		marco.getContentPane().add(mainPanel);
		
		marco.validate();
	}

}
