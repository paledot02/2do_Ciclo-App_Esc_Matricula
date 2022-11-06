package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/* IMPORTAR ARREGLOS Y CLASES*/

import arreglo.ArregloAlumnos;
import arreglo.ArregloCursos;
import arreglo.ArregloMatricula;
import arreglo.ArregloRetiro;
import clases.Alumno;
import clases.Curso;
import clases.Matricula;
import clases.Retiro;

/////////////////////////////
public class proyecto_05 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCurso;
	private JTextField txtAlumno;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			proyecto_05 dialog = new proyecto_05();
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
	ArregloRetiro rr = new ArregloRetiro();
	
	//////////////////////////////////////////////
	private JButton btnAlumno;
	private JButton btnCurso;
	private JTextField txtMatricula;
	private JTextField txtRetiro;
	private JButton btnMatricula;
	private JButton btnRetiro;
	
	
	
	/////////////////////////////////////
	
	/**
	 * Create the dialog.
	 */
	public proyecto_05() {
		setTitle("Consulta Datos");
		setBounds(100, 100, 496, 599);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.WHITE, Color.LIGHT_GRAY));
		panel.setBounds(10, 11, 460, 538);
		contentPanel.add(panel);
		panel.setLayout(null);
		// cargar datos//
		
		aa.cargar();
		mm.cargar();
		cc.cargar();
		rr.cargar();
		
		
		JLabel lblConsultaAlumnoY = new JLabel("Consulta");
		lblConsultaAlumnoY.setFont(new Font("Cambria Math", Font.BOLD, 14));
		lblConsultaAlumnoY.setBounds(199, 11, 61, 18);
		panel.add(lblConsultaAlumnoY);
		{
			btnAlumno = new JButton("Consultar");
			btnAlumno.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					/*BOTON CONSULTAR POR CODIGO ALUMNO*/
					
					try{
						int codAlumno = leerCodigoAlumno();
						Alumno x = aa.buscarPorCodAlumno(codAlumno);
					
						if (x != null){
						
							Matricula m = mm.buscarPorCodAlumno(codAlumno);
							if (m != null){
								Curso b = m.getCurso();
						
								txtS.setText("");
								imprimir();
								imprimir("     ALUMNO ENCONTRADO");
								imprimir();
								imprimir("  Codigo Alumno:  " + x.getCodAlumno());
								imprimir("  Nombre:         " + x.getNombre());
								imprimir("  Apellido:       " + x.getApellidos());
								imprimir("  Dni:            " + x.getDni());
								imprimir("  Edad:           " + x.getEdad());
								imprimir("  Celular:        " + x.getCelular());
								imprimir("  Estado:         " + x.getEstado());
						
								if(x.getEstado()==1){
							
									imprimir();
									imprimir("     ALUMNO MATRICULADO");
									imprimir();	
									imprimir("  Codigo Curso:   " + b.getCodCurso());
									imprimir("  Asignatura:     " + b.getAsignatura());
									imprimir("  Ciclo:          " + b.getCiclo());
									imprimir("  Creditos:       " + b.getCreditos());
									imprimir("  Horas:          " + b.getHoras());
								}
						
							}
							else {
						
								txtS.setText("");
								imprimir();
								imprimir("     ALUMNO ENCONTRADO");
								imprimir();
								imprimir("  Codigo Alumno:  " + x.getCodAlumno());
								imprimir("  Nombre:         " + x.getNombre());
								imprimir("  Apellido:       " + x.getApellidos());
								imprimir("  Dni:            " + x.getDni());
								imprimir("  Edad:           " + x.getEdad());
								imprimir("  Celular:        " + x.getCelular());
								imprimir("  Estado:         " + x.getEstado());
								imprimir();
								imprimir("  No Matriculado");
							}
						
						}
						else{
							mensaje("No se encontro al Alumno");
						}
					}
					catch(Exception e){
						mensaje("Codigo Incorrecto");
					}
					
				}
			});
			btnAlumno.setBounds(271, 50, 89, 23);
			panel.add(btnAlumno);
			btnAlumno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 196, 440, 331);
		panel.add(scrollPane);
		
		txtS = new JTextArea();
		txtS.setEditable(false);
		txtS.setFont(new Font("Courier New", Font.PLAIN, 14));
		scrollPane.setViewportView(txtS);
		
		txtAlumno = new JTextField();
		txtAlumno.setBounds(128, 52, 105, 20);
		panel.add(txtAlumno);
		txtAlumno.setColumns(10);
		
		txtCurso = new JTextField();
		txtCurso.setBounds(128, 87, 105, 20);
		panel.add(txtCurso);
		txtCurso.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Codigo Alumno");
		lblNewLabel.setBounds(22, 55, 96, 14);
		panel.add(lblNewLabel);
		
		JLabel lblCodigoCurso = new JLabel("Codigo Curso");
		lblCodigoCurso.setBounds(22, 90, 96, 14);
		panel.add(lblCodigoCurso);
		
		btnCurso = new JButton("Consultar");
		btnCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/* BOTON CONSULTAR POR CODIGO DE CURSO */
				try{
					int codCurso = leerCodigoCurso();
				
					Curso c = cc.buscarPorCod(leerCodigoCurso());
				
					if(c != null){
					
						txtS.setText("");
						imprimir();
						imprimir("     CURSO ENCONTRADO");
						imprimir();
						imprimir("  Nombre:         " + c.getAsignatura());
						imprimir("  Codigo:         " + c.getCodCurso());
						imprimir("  Ciclo:          " + c.getCiclo());
						imprimir("  Creditos:       " + c.getCreditos());
						imprimir("  Horas:          " + c.getHoras());
					}
					else{
						mensaje("No se encontro el Curso");
					}
				}
				catch(Exception e){
					mensaje("Codigo Incorrecto");
				}
				
			}
		});
		btnCurso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCurso.setBounds(271, 84, 89, 23);
		panel.add(btnCurso);
		
		JLabel lblNMatricula = new JLabel("n\u00B0 Matricula");
		lblNMatricula.setBounds(22, 125, 96, 14);
		panel.add(lblNMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(128, 122, 105, 20);
		panel.add(txtMatricula);
		
		btnMatricula = new JButton("Consultar");
		btnMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* BOTON CONSULTAR POR NUMERO DE MATRICULA */
				
				try{
					int numMatricula = leerNumeroMatricula();
					Matricula m = mm.buscarPorNumMatricula(numMatricula);
				
					if(m != null){
					
						Alumno x = m.getAlumno();
					
						txtS.setText("");
						imprimir();
						imprimir("     MATRICULA ENCONTRADO");
						imprimir();
						imprimir("  Codigo Alumno:  " + x.getCodAlumno());
						imprimir("  Nombre:         " + x.getNombre());
						imprimir("  Apellido:       " + x.getApellidos());
						imprimir("  Dni:            " + x.getDni());
						imprimir("  Edad:           " + x.getEdad());
						imprimir("  Celular:        " + x.getCelular());
						imprimir("  Estado:         " + x.getEstado());
						imprimir();
						imprimir("  Numero Matricula: " + m.getNumMatricula());
						imprimir("  Codigo Curso:     " + m.getCurso());
						imprimir("  Fecha Matricula:  " + m.getFecha());
						imprimir("  Hora Matricula:   " + m.getHoras());
					
					}
					else{
					mensaje("No se econtro la Matricula");
					}
				}
				catch(Exception a){
					mensaje("Numero Incorrecto");
				}
			}
		});
		btnMatricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMatricula.setBounds(271, 119, 89, 23);
		panel.add(btnMatricula);
		
		JLabel lblNRetiro = new JLabel("n\u00B0 Retiro");
		lblNRetiro.setBounds(22, 160, 91, 14);
		panel.add(lblNRetiro);
		
		txtRetiro = new JTextField();
		txtRetiro.setColumns(10);
		txtRetiro.setBounds(128, 157, 105, 20);
		panel.add(txtRetiro);
		
		btnRetiro = new JButton("Consultar");
		btnRetiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* BOTON CONSULTAR POR NUMERO DE RETIRO */
				
				try{
					int numRetiro = leerNumeroRetiro();
					Retiro r = rr.buscarPorNumeroRetiro(numRetiro);//objeto retiro
				
					if( r != null){
						
						Matricula m = r.getMatricula();
						Alumno a = m.getAlumno();
						Curso c = m.getCurso();
						
						txtS.setText("");
						imprimir();
						imprimir("     RETIRO ENCONTRADO");
						imprimir();
						imprimir("  Codigo Alumno:  " + a.getCodAlumno());
						imprimir("  Nombre:         " + a.getNombre());
						imprimir("  Apellido:       " + a.getApellidos());
						imprimir("  Dni:            " + a.getDni());
						imprimir("  Edad:           " + a.getEdad());
						imprimir("  Celular:        " + a.getCelular());
						imprimir("  Estado:         " + a.getEstado());
						imprimir();
						imprimir("  Codigo Curso:   " + c.getCodCurso());
						imprimir("  Asignatura:     " + c.getAsignatura());
						imprimir("  Ciclo:          " + c.getCiclo());
						imprimir("  Creditos:       " + c.getCreditos());
						imprimir("  Horas:          " + c.getHoras());
					}
					else{
						mensaje("No se encontro el Retiro");
					}
				}
				catch(Exception a){
					mensaje("Numero Incorrecto");
				}
			}
		});
		btnRetiro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRetiro.setBounds(271, 155, 89, 23);
		panel.add(btnRetiro);
		
	}
	/* METODOS NECESARIOS*/
	
	void imprimir() {
		imprimir("");
	}
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	int leerCodigoAlumno(){
		return Integer.parseInt(txtAlumno.getText().trim());
	}
	int leerCodigoCurso(){
		return Integer.parseInt(txtCurso.getText().trim());
	}
	int leerNumeroMatricula(){
		return Integer.parseInt(txtMatricula.getText().trim());
	}
	int leerNumeroRetiro(){
		return Integer.parseInt(txtRetiro.getText().trim());
	}
	void mensaje(String e){
		JOptionPane.showMessageDialog(this, e);
	}
}

















