package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MenuPrincipal2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnInicio;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenuItem mntmCerrarSesion;
	private JMenuItem mntmConfiguraciones;
	private JMenu mnAcerdaDe;
	private JMenuItem mntmPacientesVacunados;
	private JMenuItem mntmAltoRiesgo;
	private JLabel lblNewLabel;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal2 frame = new MenuPrincipal2();
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
	public MenuPrincipal2() {
		setBackground(Color.CYAN);
		setTitle("Matriculas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 667);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnInicio = new JMenu("Mantenimiento       ");
		mnInicio.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		menuBar.add(mnInicio);

		mntmConfiguraciones = new JMenuItem("Alumno           ");
		mntmConfiguraciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				proyecto_01 gg = new proyecto_01();
				gg.setVisible(true);
			}
		});
		mntmConfiguraciones.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		mnInicio.add(mntmConfiguraciones);

		mntmNewMenuItem = new JMenuItem("Cursos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				proyecto_02 gg = new proyecto_02();
				gg.setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		mnInicio.add(mntmNewMenuItem);

		mnNewMenu = new JMenu("Registro        ");
		mnNewMenu.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		menuBar.add(mnNewMenu);

		mntmPacientesVacunados = new JMenuItem("Matricula");
		mntmPacientesVacunados.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		mntmPacientesVacunados.addActionListener(this);
		mnNewMenu.add(mntmPacientesVacunados);

		mntmNewMenuItem_1 = new JMenuItem("Retiro");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proyecto_04 gg = new proyecto_04();
				gg.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		mnNewMenu.add(mntmNewMenuItem_1);

		mnNewMenu_1 = new JMenu("Consulta        ");
		mnNewMenu_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmAlumnosYCursos = new JMenuItem("Alumnos ,cursos, matriculas y cursos\r\n\r\n");
		mntmAlumnosYCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				proyecto_05 gg= new proyecto_05();
				gg.setVisible(true);
				
			}
		});
		mntmAlumnosYCursos.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		mnNewMenu_1.add(mntmAlumnosYCursos);

		mnAcerdaDe = new JMenu("Reporte              ");
		mnAcerdaDe.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		menuBar.add(mnAcerdaDe);

		mntmAltoRiesgo = new JMenuItem("Reportes de alumnos");
		mntmAltoRiesgo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		mntmAltoRiesgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteAlumnos gg= new ReporteAlumnos();
				gg.setVisible(true);

			}
		});
		mnAcerdaDe.add(mntmAltoRiesgo);

		mnNewMenu_2 = new JMenu("Salir   ");
		mnNewMenu_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		menuBar.add(mnNewMenu_2);

		mntmCerrarSesion = new JMenuItem("Cerrar sesion");
		mntmCerrarSesion.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		mnNewMenu_2.add(mntmCerrarSesion);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Matricula y Retiros");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal2.class.getResource("/icono1/cibertec2.jpg")));
		lblNewLabel.setBounds(10, 11, 761, 559);
		contentPane.add(lblNewLabel);

		mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mntmNewMenuItem_2.setBounds(366, 23, 137, 26);
		contentPane.add(mntmNewMenuItem_2);
	}

	public void actionPerformed(ActionEvent e) {
		proyecto_03 gg = new proyecto_03();
		gg.setVisible(true);

	}
}
