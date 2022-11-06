package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/* IMPORTAR CLASES Y ARREGLO */
import clases.Alumno;
import clases.Matricula;
import clases.Curso;
import arreglo.ArregloAlumnos;
import arreglo.ArregloMatricula;
import arreglo.ArregloCursos;
/////////////////////////////////
public class ReporteAlumnos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea txtS;
	private JButton btnMatriculaPendiente;
	private JButton btnMatriculaVigente;
	private JButton btnMatriculadosPorCurso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReporteAlumnos dialog = new ReporteAlumnos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* DECLARACION GLOBAL */
	ArregloAlumnos aa = new ArregloAlumnos();
	ArregloCursos cc = new ArregloCursos();
	ArregloMatricula mm = new ArregloMatricula();
	
	//////////////////////////////////////////////
	/**
	 * Create the dialog.
	 */
	public ReporteAlumnos() {
		setTitle("Reporte Alumnos");
		setBounds(100, 100, 675, 610);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//cargar datos
		
		aa.cargar();
		mm.cargar();
		cc.cargar();
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY));
		panel.setBounds(10, 11, 639, 549);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblReporteDeAlumnos = new JLabel("Reporte de Alumnos");
		lblReporteDeAlumnos.setFont(new Font("Cambria Math", Font.BOLD, 14));
		lblReporteDeAlumnos.setBounds(248, 11, 142, 18);
		panel.add(lblReporteDeAlumnos);
		
		btnMatriculaPendiente = new JButton("Matricula Pendiente");
		btnMatriculaPendiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* ALUMNOS CON MATRICULA PENDIENTE */
				
				Alumno a;
				txtS.setText("");
				for(int i=0; i<aa.tamano(); i++){
					a = aa.obtener(i);
					if(a.getEstado()==0){
						imprimir();
						imprimir("  C�digo del alumno :  " + a.getCodAlumno());
						imprimir("  Nombres           :  " + a.getNombre());
						imprimir("  Apellidos         :  " + a.getApellidos());
						imprimir("  DNI               :  " + a.getDni());
						imprimir("  Edad              :  " + a.getEdad());
						imprimir("  Celular           :  " + a.getCelular());
						imprimir();
					}
				}
				
			}
		});
		btnMatriculaPendiente.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMatriculaPendiente.setBounds(26, 59, 178, 27);
		panel.add(btnMatriculaPendiente);
		
		btnMatriculaVigente = new JButton("Matricula Vigente");
		btnMatriculaVigente.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMatriculaVigente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/* ALUMNOS CON MATRICULA VIGENTE */
				
				Alumno a;
				txtS.setText("");
				for(int i=0; i<aa.tamano(); i++){
					a = aa.obtener(i);
					if(a.getEstado()==1){
						imprimir();
						imprimir("  C�digo del alumno :  " + a.getCodAlumno());
						imprimir("  Nombres           :  " + a.getNombre());
						imprimir("  Apellidos         :  " + a.getApellidos());
						imprimir("  DNI               :  " + a.getDni());
						imprimir("  Edad              :  " + a.getEdad());
						imprimir("  Celular           :  " + a.getCelular());
						imprimir();
					}
				}
			}
			
			
		});
		btnMatriculaVigente.setBounds(230, 59, 178, 27);
		panel.add(btnMatriculaVigente);
		
		btnMatriculadosPorCurso = new JButton("Matriculados por Curso");
		btnMatriculadosPorCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* MATRICULADOS POR CURSO */
				
				txtS.setText("");
				
				ArrayList<Matricula> reporteMatri = mm.obtenerMatriculaOrdenada();
				boolean nuevocurso = true;
				String nombreCurso = "";
				
				for(int i=0; i<reporteMatri.size(); i++){
					Matricula m = reporteMatri.get(i);
					
					if(nombreCurso != m.getCurso().getAsignatura()){
						nuevocurso = true;
						nombreCurso = m.getCurso().getAsignatura();
					}
					
					if(nuevocurso){
						imprimir("  -------------------------------------------------");
						imprimir();
						imprimir("                       " + nombreCurso);
						imprimir();
						nuevocurso = false;
					}
					Alumno a = m.getAlumno();
					imprimir("  " + a.getCodAlumno()+ " "+ a.getNombre() +" " + a.getApellidos());
				}

			}
		});
		btnMatriculadosPorCurso.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMatriculadosPorCurso.setBounds(434, 59, 178, 27);
		panel.add(btnMatriculadosPorCurso);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 111, 597, 427);
		panel.add(scrollPane);
		
		txtS = new JTextArea();
		txtS.setFont(new Font("Courier New", Font.PLAIN, 13));
		scrollPane.setViewportView(txtS);
		
		
		
	}
	/* METODOS NECESARIOS */
	
	void imprimir() {
		imprimir("");
	}
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	
	
	
	
}
















