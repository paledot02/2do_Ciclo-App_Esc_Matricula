package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import arreglo.ArregloCursos;
import arreglo.ArregloMatricula;
import clases.Alumno;
import clases.ComboItem;
import clases.Curso;
import clases.Matricula;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class proyecto_03 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumMatricula;
	//private JTextField txtFecha;
	//private JTextField txtHora;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private JButton btnAdicionar;
	private JComboBox cboCurso;
	private JComboBox cboAlumno;

	static ArregloMatricula arrayMatriculas = new ArregloMatricula();
	static ArregloAlumnos tempAlumnos = new ArregloAlumnos();
	static ArregloCursos tempCurso = new ArregloCursos();
	private JTextField txt_filtro_codigo;
	private JTextField txt_filtro_nombres;
	private JTextField txt_filtro_curso;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			proyecto_03 dialog = new proyecto_03();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public proyecto_03() {
		//Cargar lista de alumnos
		tempCurso.cargar();
		tempAlumnos.cargar();
		arrayMatriculas.cargar();
		
		setTitle("Registro/Matricula");
		setBounds(100, 100, 663, 443);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 484, 210);
		contentPanel.add(scrollPane);

		tblTabla = new JTable();
		tblTabla.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				int pos = tblTabla.rowAtPoint(arg0.getPoint());

					
					if (pos != -1) {
						
						Matricula oMatricula = arrayMatriculas.buscarPorNumMatricula(Integer.valueOf(String.valueOf(tblTabla.getValueAt(pos, 0))));
						
						int indexCurso = tempCurso.getItemCurso(oMatricula.getCurso().getCodCurso());
						int indexAlumno = tempAlumnos.getItemAlumno(oMatricula.getAlumno().getCodAlumno());
						cboAlumno.setSelectedIndex(indexAlumno);
						cboCurso.setSelectedIndex(indexCurso);
					}

			
				
			}
		});
		scrollPane.setViewportView(tblTabla);
		tblTabla.setFillsViewportHeight(true);

		JLabel lblNewLabel = new JLabel("Num. matricula");
		lblNewLabel.setBounds(10, 11, 86, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblAsignatura = new JLabel("Cod. Alumno");
		lblAsignatura.setBounds(212, 11, 85, 14);
		contentPanel.add(lblAsignatura);

		JLabel lblCiclo = new JLabel("Cod. curso");
		lblCiclo.setBounds(420, 11, 74, 14);
		contentPanel.add(lblCiclo);

		txtNumMatricula = new JTextField();
		txtNumMatricula.setEnabled(false);
		txtNumMatricula.setBounds(106, 8, 74, 20);
		contentPanel.add(txtNumMatricula);
		txtNumMatricula.setText(String.valueOf(arrayMatriculas.codigoCorrelativo()));
		txtNumMatricula.setColumns(10);

		/*txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(106, 77, 74, 20);
		txtFecha.setText(String.valueOf(arrayMatriculas.fecha()));
		contentPanel.add(txtFecha);*/

		/*txtHora = new JTextField();
		txtHora.setEnabled(false);
		txtHora.setForeground(Color.WHITE);
		txtHora.setColumns(10);
		txtHora.setBounds(286, 77, 74, 20);
		txtHora.setText(String.valueOf(arrayMatriculas.hora()));
		contentPanel.add(txtHora);*/

		
		cboCurso = new JComboBox(tempCurso.comboCurso());
		cboCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox comboBox = (JComboBox)arg0.getSource();
		        ComboItem item = (ComboItem)comboBox.getSelectedItem();
			}
		});
		cboCurso.setEnabled(true);
		cboCurso.setBounds(484, 8, 107, 20);
		contentPanel.add(cboCurso);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (arrayMatriculas.buscarPorCodAlumno(leerCodAlumno()) != null) {
					JOptionPane.showMessageDialog(null, "Alumno ya matriculado");
				} else  {
					Alumno alumMatriculado = tempAlumnos.buscarPorCodAlumno(leerCodAlumno());
					alumMatriculado.setEstado(1);
					tempAlumnos.guardar();
					
					Curso arregloCurso = tempCurso.buscarPorCod(leerCodCurso());
					
					Matricula nuevo = new Matricula(leerNumMatricula(), alumMatriculado, arregloCurso,leerFecha(),leerhora());
					
					arrayMatriculas.adicionar(nuevo);
					arrayMatriculas.guardar();
					listar();
					limpieza();
					//txtHora.setText(String.valueOf(arrayMatriculas.hora()));
					txtNumMatricula.setText(String.valueOf(arrayMatriculas.codigoCorrelativo()));
					//txtFecha.setText(String.valueOf(arrayMatriculas.fecha()));
				}
			
			}
		});
		btnAdicionar.setBounds(523, 125, 89, 23);
		contentPanel.add(btnAdicionar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int mod = tblTabla.getSelectedRow();
				if (mod != -1) {
					
					Alumno alumMatriculado = tempAlumnos.buscarPorCodAlumno(leerCodAlumno());
					Curso arregloCurso = tempCurso.buscarPorCod(leerCodCurso());
					
						tblTabla.setValueAt(leerCodAlumno(), mod, 2);
						
						tblTabla.setValueAt(leerCodCurso(), mod, 3);

						Matricula matricula = arrayMatriculas.obtener(mod);
						matricula.setAlumno(alumMatriculado);
						matricula.setCurso(arregloCurso);
						arrayMatriculas.guardar();
						listar();
						limpieza();
					}

				}
		
			
		});
		btnModificar.setBounds(523, 159, 89, 23);
		contentPanel.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int i = tblTabla.getSelectedRow();
				if (i == -1) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar el registro que desea eliminar",
							"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				} else {
					int seleccion;
					seleccion = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar este registro?",
							"Eliminar", JOptionPane.YES_NO_OPTION);
					
					
					Matricula tempMatricula = arrayMatriculas.obtener(i);
					
					Alumno alumMatriculado = tempAlumnos.buscarPorCodAlumno(tempMatricula.getAlumno().getCodAlumno());
					
					
					if (seleccion == JOptionPane.YES_OPTION && alumMatriculado.getEstado() != 2) {
						
						alumMatriculado.setEstado(0);
						tempAlumnos.guardar();
						
						modelo.removeRow(i);
						arrayMatriculas.eliminar(tempMatricula);
						arrayMatriculas.guardar();
						
					} else {
						JOptionPane.showMessageDialog(null, "No puedes eliminar un alumno registrado/retirado ");
					}

				}
			}

		});
		btnEliminar.setBounds(523, 193, 89, 23);
		contentPanel.add(btnEliminar);

		modelo = new DefaultTableModel();
		modelo.addColumn("Num.Matricula");
		modelo.addColumn("Alumno");
		modelo.addColumn("Curso");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		tblTabla.setModel(modelo);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cboAlumno.setSelectedIndex(0);
				cboCurso.setSelectedIndex(0);
				//txtCodCurso.setText("");
				//txtS.setText("");
				txtNumMatricula.setText(String.valueOf(arrayMatriculas.codigoCorrelativo()));
				
			}
			
		});
		btnNuevo.setBounds(523, 91, 89, 23);
		contentPanel.add(btnNuevo);
		
		cboAlumno = new JComboBox(tempAlumnos.comboAlumno());
		cboAlumno.setBounds(286, 8, 124, 20);
		contentPanel.add(cboAlumno);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 299, 624, 88);
		contentPanel.add(panel);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setBounds(10, 11, 113, 14);
		panel.add(label);
		
		JLabel lblAlumno = new JLabel("Alumno:");
		lblAlumno.setBounds(10, 36, 113, 14);
		panel.add(lblAlumno);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(10, 61, 113, 14);
		panel.add(lblCurso);
		
		txt_filtro_codigo = new JTextField();
		txt_filtro_codigo.setColumns(10);
		txt_filtro_codigo.setBounds(133, 11, 263, 20);
		panel.add(txt_filtro_codigo);
		
		txt_filtro_nombres = new JTextField();
		txt_filtro_nombres.setColumns(10);
		txt_filtro_nombres.setBounds(133, 36, 263, 20);
		panel.add(txt_filtro_nombres);
		
		txt_filtro_curso = new JTextField();
		txt_filtro_curso.setColumns(10);
		txt_filtro_curso.setBounds(133, 61, 263, 20);
		panel.add(txt_filtro_curso);
		
				JButton btnConsultar = new JButton("Consultar");
				btnConsultar.setBounds(418, 11, 196, 70);
				panel.add(btnConsultar);
				btnConsultar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						String fCodigo = txt_filtro_codigo.getText();
						String fNombres = txt_filtro_nombres.getText();
						String fCurso = txt_filtro_curso.getText();
						
						if(fCodigo.trim().equals("") && fNombres.trim().equals("") && fCurso.trim().equals("")){
							JOptionPane.showMessageDialog(null, "Debe ingresar por lo menos un filtro.",
									"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						int busquedaIndex = arrayMatriculas.buscarPorFiltro(fCodigo, fNombres, fCurso);
						if(busquedaIndex >= 0){
							tblTabla.setRowSelectionInterval(busquedaIndex, busquedaIndex);
						}else{
							JOptionPane.showMessageDialog(null, "No se encontraron resultados.",
									"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
						}
						
						/*for (int i = 0; i < tblTabla.getRowCount(); i++) {
							if (tblTabla.getValueAt(i, 1).equals(leerCodAlumno())) {

								
								int indexCurso = tempCurso.getItemCurso(Integer.valueOf(String.valueOf(tblTabla.getValueAt(i, 2))));
								int indexAlumno = tempAlumnos.getItemAlumno(Integer.valueOf(String.valueOf(tblTabla.getValueAt(i, 1))));
								cboAlumno.setSelectedIndex(indexAlumno);
								cboCurso.setSelectedIndex(indexCurso);
								
								txtNumMatricula.setText(String.valueOf(tblTabla.getValueAt(i, 0)));
								//cboAlumno.setSelectedItem(String.valueOf(tblTabla.getValueAt(i, 1)));
								//txtCodAlumno.setText(String.valueOf(tblTabla.getValueAt(i, 1)));
								//cboCurso.setSelectedItem(String.valueOf(tblTabla.getValueAt(i, 2)));
								//txtCodCurso.setText(String.valueOf(tblTabla.getValueAt(i, 2)));
								//txtFecha.setText(String.valueOf(tblTabla.getValueAt(i, 3)));
								//txtHora.setText(String.valueOf(tblTabla.getValueAt(i, 4)));

							}

						}*/
					}
				});
		
		listar();
	}

	void listar() {
		try{
			modelo.setRowCount(0);
			for (int i = 0; i < arrayMatriculas.tamano(); i++) {
				
				Matricula om = arrayMatriculas.obtener(i);
				
				Object[] fila = { 	om.getNumMatricula(), 
						om.getAlumno().getNombre() + " " + om.getAlumno().getApellidos(),
						om.getCurso().getAsignatura(),
						om.getFecha(),
						om.getHoras() };

				modelo.addRow(fila);
			}
		}catch(Exception ex){
			
			JOptionPane.showMessageDialog(null, ex);
		}
		
	}

	void limpieza() {
		txtNumMatricula.setText("");
		cboAlumno.setSelectedIndex(0);
		cboCurso.setSelectedIndex(0);
		//txtFecha.setText("");
		//txtHora.setText("");
		cboAlumno.requestFocus();
	}

	void imprimir(String s) {
		//txtS.append(s + "\n");
	}

	// M�todos que retornan valor (sin par�metros)

	int leerNumMatricula() {
		return Integer.parseInt(txtNumMatricula.getText().trim());
	}

	int leerCodAlumno() {
		ComboItem item = (ComboItem)cboAlumno.getSelectedItem();
		return Integer.parseInt(item.getValue());
	}

	int leerCodCurso() {
		ComboItem item = (ComboItem)cboCurso.getSelectedItem();
		return Integer.parseInt(item.getValue());
	}
	String leerFecha() {
		return arrayMatriculas.fecha().toString();//txtFecha.getText().trim();
	}

	String leerhora() {
		return arrayMatriculas.hora().toString();//txtHora.getText().trim();
	}
}