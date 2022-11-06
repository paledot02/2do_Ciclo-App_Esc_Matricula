package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloAlumnos;
import arreglo.ArregloMatricula;
import arreglo.ArregloRetiro;
import clases.Alumno;
import clases.Matricula;
import clases.Retiro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;

public class proyecto_04 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRetiro;
	private JTextField txtFecha;
	private JTextField txtMatricula;
	private JTextField txtHoras;
	private JTable tblTabla;
	private DefaultTableModel modelo;

	ArregloRetiro arrayRetiros = new ArregloRetiro();
	static ArregloAlumnos alumnosTemp = new ArregloAlumnos();
	static ArregloMatricula matriculasTemp = new ArregloMatricula();
	private JTextField txtbAlumno;
	private JTextField txtbCurso;
	private JTextField txtbMatricula;
	
	public static void main(String[] args) {
		try {
			
			
			proyecto_04 dialog = new proyecto_04();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public proyecto_04() {
		matriculasTemp.cargar();
		alumnosTemp.cargar();
		arrayRetiros.cargar();
		
		setTitle("Retiros");
		setBounds(100, 100, 679, 557);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 115, 458, 261);
		contentPanel.add(scrollPane);

		tblTabla = new JTable();
		scrollPane.setViewportView(tblTabla);
		tblTabla.setFillsViewportHeight(true);

		JLabel lblNewLabel = new JLabel("Num. retiro");
		lblNewLabel.setBounds(10, 11, 86, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblAsignatura = new JLabel("Num. matricula");
		lblAsignatura.setBounds(267, 11, 112, 14);
		contentPanel.add(lblAsignatura);

		txtRetiro = new JTextField();
		txtRetiro.setEnabled(false);
		txtRetiro.setBounds(106, 8, 73, 20);
		contentPanel.add(txtRetiro);
		txtRetiro.setText(String.valueOf(arrayRetiros.codigoCorrelativo()));
		txtRetiro.setColumns(10);

		/*txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(106, 77, 73, 20);
		txtFecha.setText(String.valueOf(arrayRetiros.fecha()));
		contentPanel.add(txtFecha);
*/
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(417, 8, 61, 20);
		contentPanel.add(txtMatricula);
/*
		txtHoras = new JTextField();
		txtHoras.setEnabled(false);
		txtHoras.setColumns(10);
		txtHoras.setBounds(417, 77, 61, 20);
		txtHoras.setText(String.valueOf(arrayRetiros.hora()));
		contentPanel.add(txtHoras);
*/
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				Matricula matricula = matriculasTemp.buscarPorNumMatricula(leerNumMatricula());
				
				if(matricula == null){
					JOptionPane.showMessageDialog(null, "El n�mero de matricula no existe.",
							"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					
					return;
				}
				
				Alumno alumnoMatriculado = alumnosTemp.buscarPorCodAlumno(matricula.getAlumno().getCodAlumno());
				alumnoMatriculado.setEstado(2);
				alumnosTemp.guardar();

				Retiro nuevo = new Retiro(leerNumRetiro(), matricula, leerFecha(), leerhora());
				arrayRetiros.adicionar(nuevo);
				arrayRetiros.guardar();
				
				listar();
				limpieza();
				//txtHoras.setText(String.valueOf(arrayRetiros.hora()));
				txtRetiro.setText(String.valueOf(arrayRetiros.codigoCorrelativo()));
				//txtFecha.setText(String.valueOf(arrayRetiros.fecha()));

			}
		});
		btnAdicionar.setBounds(527, 158, 89, 23);
		contentPanel.add(btnAdicionar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(527, 192, 89, 23);
		contentPanel.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = tblTabla.getSelectedRow();
				if (i == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar el registro que desea eliminar",
							"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				} else {
					int seleccion;
					seleccion = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar este registro?",
							"Eliminar", JOptionPane.YES_NO_OPTION);

					Retiro tempMatricula = arrayRetiros.obtener(i);
					

					ArregloAlumnos tempAlumnos = new ArregloAlumnos();
					tempAlumnos.cargar();

					Alumno alumnoMatriculado = alumnosTemp.buscarPorCodAlumno(tempMatricula.getMatricula().getAlumno().getCodAlumno());
					

					if (seleccion == JOptionPane.YES_OPTION && alumnoMatriculado.getEstado() == 2) {
						
						alumnoMatriculado.setEstado(1);
						alumnosTemp.guardar();
						
						modelo.removeRow(i);
						arrayRetiros.eliminar(i);
						arrayRetiros.guardar();
						
					} else {
						mensaje("No puedes eliminar un alumno registrado/matriculado ");
					}
				}
			}
		});
		btnEliminar.setBounds(527, 226, 89, 23);
		contentPanel.add(btnEliminar);

		modelo = new DefaultTableModel();
		modelo.addColumn("Num.Retiro");
		modelo.addColumn("Num.Matricula");
		modelo.addColumn("Alumno");
		modelo.addColumn("Curso");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		tblTabla.setModel(modelo);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(20, 403, 624, 88);
		contentPanel.add(panel);
		
		JLabel lblAlumno_1 = new JLabel("Alumno:");
		lblAlumno_1.setBounds(10, 11, 113, 14);
		panel.add(lblAlumno_1);
		
		JLabel lblCurso_1 = new JLabel("Curso:");
		lblCurso_1.setBounds(10, 36, 113, 14);
		panel.add(lblCurso_1);
		
		JLabel lblMatricula_1 = new JLabel("Matricula:");
		lblMatricula_1.setBounds(10, 61, 113, 14);
		panel.add(lblMatricula_1);
		
		txtbAlumno = new JTextField();
		txtbAlumno.setColumns(10);
		txtbAlumno.setBounds(133, 11, 263, 20);
		panel.add(txtbAlumno);
		
		txtbCurso = new JTextField();
		txtbCurso.setColumns(10);
		txtbCurso.setBounds(133, 36, 263, 20);
		panel.add(txtbCurso);
		
		txtbMatricula = new JTextField();
		txtbMatricula.setColumns(10);
		txtbMatricula.setBounds(133, 61, 263, 20);
		panel.add(txtbMatricula);
		
				JButton btnConsultar = new JButton("Consultar");
				btnConsultar.setBounds(414, 11, 200, 70);
				panel.add(btnConsultar);
				btnConsultar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int busquedaIndex = arrayRetiros.buscarPorFiltro(txtbAlumno.getText(), txtbCurso.getText(), txtbMatricula.getText());
						if(busquedaIndex >= 0){
							tblTabla.setRowSelectionInterval(busquedaIndex, busquedaIndex);
						}else{
							JOptionPane.showMessageDialog(null, "No se encontraron resultados.",
									"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
		
		listar();

	}

	void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < arrayRetiros.tamano(); i++) {

			Retiro oRetiro = arrayRetiros.obtener(i);
			Object[] fila = { oRetiro.getNumRetiro(), 
					oRetiro.getMatricula().getNumMatricula(),
					oRetiro.getMatricula().getAlumno().getNombre() + " " + oRetiro.getMatricula().getAlumno().getApellidos(),
					oRetiro.getMatricula().getCurso().getAsignatura(),
					oRetiro.getFecha(), 
					oRetiro.getHora() };

			modelo.addRow(fila);
		}
	}

	void limpieza() {
		txtRetiro.setText("");
		txtMatricula.setText("");
		//txtFecha.setText("");
		//txtHoras.setText("");
		txtMatricula.requestFocus();

	}

	void imprimir(String s) {
		//txtS.append(s + "\n");
	}

	// M�todos que retornan valor (sin par�metros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	int leerNumRetiro() {
		return Integer.parseInt(txtRetiro.getText().trim());
	}

	int leerNumMatricula() {
		return Integer.parseInt(txtMatricula.getText().trim());
	}

	String leerFecha() {
		return arrayRetiros.fecha().toString();
	}

	String leerhora() {
		return arrayRetiros.hora().toString();
	}
}