package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JTextField txtusuario;
	private JPasswordField jpassword;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setForeground(Color.CYAN);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(49, 44, 46, 14);
		contentPane.add(lblUsuario);
		
		lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(49, 112, 92, 14);
		contentPane.add(lblContrasea);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(150, 41, 86, 20);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		jpassword = new JPasswordField();
		jpassword.setBounds(151, 109, 85, 20);
		contentPane.add(jpassword);
		
		btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(49, 201, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(175, 201, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		
		
		String usuario,contrasena;
		
		usuario= txtusuario.getText();
		contrasena= String.valueOf(jpassword.getPassword());
		
		if(usuario.equals("zlide")&& contrasena.equals("12345")){
			this.dispose();
		    JOptionPane.showMessageDialog(this," Bienvenido a Cibertec");
		    
		    MenuPrincipal2 gg = new MenuPrincipal2();
		    gg.setLocationRelativeTo(this);
		    gg.setVisible(true);
			
		}else{
		    	JOptionPane.showMessageDialog(this," Usuario y/o contrase�a incorrecta ");
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
