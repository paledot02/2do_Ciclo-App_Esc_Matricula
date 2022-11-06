package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloAlumnos;

import clases.Alumno;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class proyecto_01 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtEdad;
	private JTextField txtNombre;
	private JTextField txtCelular;
	private JTextField txtApellido;
	private JTextField txtdni;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private JComboBox cboEstado;
	private JButton btnAdicionar;
	// DECLARACION
	ArregloAlumnos arrayAlumnos = new ArregloAlumnos();
	private JTextField txt_filtro_codigo;
	private JTextField txt_filtro_nombres;
	private JTextField txt_filtro_dni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			proyecto_01 dialog = new proyecto_01();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public proyecto_01() {

		setTitle("Mantenimiento/Alumno");
		setBounds(100, 100, 660, 573);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 96, 470, 322);
			contentPanel.add(scrollPane);
			{
				tblTabla = new JTable();
				tblTabla.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {

						int pos = tblTabla.rowAtPoint(arg0.getPoint());

						
						if (pos != -1) {
							btnAdicionar.setEnabled(false);
							txtdni.setEnabled(false);
							txtCodigo.setText(String.valueOf(tblTabla.getValueAt(pos, 0)));
							txtNombre.setText(String.valueOf(tblTabla.getValueAt(pos, 1)));
							txtApellido.setText(String.valueOf(tblTabla.getValueAt(pos, 2)));
							txtdni.setText(String.valueOf(tblTabla.getValueAt(pos, 3)));
							txtEdad.setText(String.valueOf(tblTabla.getValueAt(pos, 4)));
							txtCelular.setText(String.valueOf(tblTabla.getValueAt(pos, 5)));
						}

					}
				});
				tblTabla.setFillsViewportHeight(true);
				scrollPane.setViewportView(tblTabla);
			}
		}
		{
			JLabel lblCodPaciente = new JLabel("Cod.Alumno");
			lblCodPaciente.setBounds(10, 11, 104, 14);
			contentPanel.add(lblCodPaciente);
		}
		{
			JLabel label = new JLabel("Edad");
			label.setBounds(10, 68, 91, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Nombre");
			label.setBounds(181, 11, 68, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Celular");
			label.setBounds(184, 68, 91, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Apellidos");
			label.setBounds(330, 11, 91, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Estado");
			label.setBounds(340, 68, 91, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Dni");
			label.setBounds(497, 11, 42, 14);
			contentPanel.add(label);
		}

		{

			btnAdicionar = new JButton("Adicionar");
			btnAdicionar.setEnabled(false);
			btnAdicionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// CODIGO ADICIONAR

					

					if (!isNumeric(txtEdad.getText())) {
						JOptionPane.showMessageDialog(null, "La edad debe ser un n�mero v�lido", "ADVERTENCIA",
								JOptionPane.WARNING_MESSAGE);
					}else if (!isString(txtNombre.getText())||!isString(txtApellido.getText())) {
							JOptionPane.showMessageDialog(null, "Debe ingresar los caracteres correctos", "ADVERTENCIA",
									JOptionPane.WARNING_MESSAGE);
					}else if(leerEdad()<0){ 
						JOptionPane.showMessageDialog(null, "La edad debe ser un n�mero v�lido", "ADVERTENCIA",
								JOptionPane.WARNING_MESSAGE);
					
					} else if (!isNumeric(txtCelular.getText()) || !(txtCelular.getText().length() == 9)) {
						JOptionPane.showMessageDialog(null, "Debe ingresar un celular correcto", "ADVERTENCIA",
								JOptionPane.WARNING_MESSAGE);
	
					}
						else if (arrayAlumnos.buscar(txtdni.getText()) != null) {
						JOptionPane.showMessageDialog(null, "El dni ya se encuentra registrado");
					} else {
						
						int codigo = leerCodigo();
						String nombre = leerNombre();
						String apellido = leerApellido();
						String dni = leerDni();
						int edad = leerEdad();
						int celular = leerCelular();
						int estado = leerEstado();
						
						Alumno nuevo = new Alumno(codigo, nombre, apellido, dni, edad, celular, estado);
						arrayAlumnos.adicionar(nuevo);
						arrayAlumnos.guardar();
						listar();
						limpieza();
						txtCodigo.setText(String.valueOf(arrayAlumnos.codigoCorrelativo()));
					}
					
				}

			});
			btnAdicionar.setBounds(497, 156, 113, 23);
			contentPanel.add(btnAdicionar);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setEnabled(false);
			txtCodigo.setToolTipText("");
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(85, 8, 86, 20);
			txtCodigo.setText(String.valueOf(arrayAlumnos.codigoCorrelativo()));
			contentPanel.add(txtCodigo);
		}
		{
			txtEdad = new JTextField();
			txtEdad.addKeyListener(new KeyAdapter() {

				public void keyReleased(KeyEvent e) {
					 
					validarCampos();
				}
				
				@Override
		        public void keyTyped(KeyEvent e) {
					if(!validaNumeros(e.getKeyChar())) {
						e.consume();
			        }
		        }
			});
			txtEdad.setColumns(10);
			txtEdad.setBounds(85, 65, 86, 20);
			contentPanel.add(txtEdad);
		}
		{
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {

				public void keyReleased(KeyEvent arg0) {
					validarCampos();
				}
			});
			txtNombre.setColumns(10);
			txtNombre.setBounds(234, 8, 86, 20);
			contentPanel.add(txtNombre);
		}
		{
			txtCelular = new JTextField();
			txtCelular.addKeyListener(new KeyAdapter() {

				public void keyReleased(KeyEvent e) {
					validarCampos();
				}
				
				@Override
		        public void keyTyped(KeyEvent e) {
					if(!validaNumeros(e.getKeyChar())) {
						e.consume();
			        }
		        }
			});
			txtCelular.setColumns(10);
			txtCelular.setBounds(234, 65, 86, 20);
			contentPanel.add(txtCelular);
		}
		{
			txtApellido = new JTextField();
			txtApellido.addKeyListener(new KeyAdapter() {

				public void keyReleased(KeyEvent e) {
					validarCampos();
				}
			});
			txtApellido.setColumns(10);
			txtApellido.setBounds(394, 8, 86, 20);
			contentPanel.add(txtApellido);
		}
		{
			txtdni = new JTextField();
			txtdni.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					validarCampos();
				}
				
				@Override
		        public void keyTyped(KeyEvent e) {
					if(!validaNumeros(e.getKeyChar())) {
						e.consume();
			        }
		        }
			});
			txtdni.setColumns(10);
			txtdni.setBounds(524, 8, 86, 20);
			contentPanel.add(txtdni);
		}
		modelo = new DefaultTableModel();
		modelo.addColumn("C�digo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellidos");
		modelo.addColumn("DNI");
		modelo.addColumn("Edad");
		modelo.addColumn("Celular");
		modelo.addColumn("Estado");
		tblTabla.setModel(modelo);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int mod = tblTabla.getSelectedRow();
				if (mod != -1) {
					if (!isNumeric(txtEdad.getText())) {
						JOptionPane.showMessageDialog(null, "La edad debe ser un n�mero v�lido", "ADVERTENCIA",
								JOptionPane.WARNING_MESSAGE);
					} else if (!isNumeric(txtCelular.getText()) || !(txtCelular.getText().length() == 9)) {
						JOptionPane.showMessageDialog(null, "Debe ingresar un celular correcto", "ADVERTENCIA",
								JOptionPane.WARNING_MESSAGE);
					} else {

						tblTabla.setValueAt(txtNombre.getText(), mod, 1);
						tblTabla.setValueAt(txtApellido.getText(), mod, 2);
						txtdni.setEnabled(false);
						tblTabla.setValueAt(txtEdad.getText(), mod, 4);
						tblTabla.setValueAt(txtCelular.getText(), mod, 5);

						Alumno alumno = arrayAlumnos.obtener(mod);
						alumno.setNombre(txtNombre.getText());
						alumno.setApellidos(txtApellido.getText());
						alumno.setEdad(Integer.parseInt(txtEdad.getText()));
						alumno.setCelular(Integer.parseInt(txtCelular.getText()));
						arrayAlumnos.guardar();
						listar();
						limpieza();
					}

				}

			}
		});
		btnModificar.setBounds(497, 190, 113, 23);
		contentPanel.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = tblTabla.getSelectedRow();
				if (i == -1){
					JOptionPane.showMessageDialog(null, "Debe seleccionar el registro que desea eliminar",
							"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				} else {
					int seleccion;
					seleccion = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar este registro?",
							"Eliminar", JOptionPane.YES_NO_OPTION);
					if (seleccion == JOptionPane.YES_OPTION && arrayAlumnos.obtener(i).getEstado()==0) {
						modelo.removeRow(i);
						arrayAlumnos.guardar();
						arrayAlumnos.eliminar(i);
					}	else{
						mensaje( "No puedes eliminar un alumno registrado/retirado ");
					}
					

				}
			}
		});
		btnEliminar.setBounds(497, 224, 113, 23);
		contentPanel.add(btnEliminar);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtNombre.setText("");
				txtApellido.setText("");
				txtdni.setText("");
				txtEdad.setText("");
				txtCelular.setText("");
				// txtEstado.setText("");
				txtdni.setEnabled(true);
				txtCodigo.setText(String.valueOf(arrayAlumnos.codigoCorrelativo()));
			}
		});
		btnNuevo.setBounds(497, 122, 111, 23);
		contentPanel.add(btnNuevo);

		cboEstado = new JComboBox();
		cboEstado.setEnabled(false);
		cboEstado.setEditable(true);
		cboEstado.setModel(new DefaultComboBoxModel(new String[] { "Registrado", "Matriculado", "Retirado" }));
		cboEstado.setBounds(403, 62, 91, 20);
		contentPanel.add(cboEstado);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 429, 624, 88);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(10, 11, 113, 14);
		panel.add(lblCdigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 36, 113, 14);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("DNI:");
		lblApellido.setBounds(10, 61, 113, 14);
		panel.add(lblApellido);
		
		txt_filtro_codigo = new JTextField();
		txt_filtro_codigo.setBounds(133, 11, 263, 20);
		panel.add(txt_filtro_codigo);
		txt_filtro_codigo.setColumns(10);
		
		txt_filtro_nombres = new JTextField();
		txt_filtro_nombres.setBounds(133, 36, 263, 20);
		panel.add(txt_filtro_nombres);
		txt_filtro_nombres.setColumns(10);
		
		txt_filtro_dni = new JTextField();
		txt_filtro_dni.setBounds(133, 61, 263, 20);
		panel.add(txt_filtro_dni);
		txt_filtro_dni.setColumns(10);
		{
			JButton btnConstultar = new JButton("Constultar");
			btnConstultar.setBounds(406, 11, 208, 70);
			panel.add(btnConstultar);
			btnConstultar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String fCodigo = txt_filtro_codigo.getText();
					String fNombres = txt_filtro_nombres.getText();
					String fDni = txt_filtro_dni.getText();
					
					if(fCodigo.trim().equals("") && fNombres.trim().equals("") && fDni.trim().equals("")){
						JOptionPane.showMessageDialog(null, "Debe ingresar por lo menos un filtro.",
								"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					int busquedaIndex = arrayAlumnos.buscarPorFiltro(fCodigo, fNombres, fDni);
					if(busquedaIndex >= 0){
						tblTabla.setRowSelectionInterval(busquedaIndex, busquedaIndex);
					}else{
						JOptionPane.showMessageDialog(null, "No se encontraron resultados.",
								"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
					}
					/*int pos = tblTabla.getSelectedRow();
					txtCodigo.setText(String.valueOf(tblTabla.getValueAt(pos, 0)));
					txtNombre.setText(String.valueOf(tblTabla.getValueAt(pos, 1)));
					txtApellido.setText(String.valueOf(tblTabla.getValueAt(pos, 2)));
					txtdni.setText(String.valueOf(tblTabla.getValueAt(pos, 3)));
					txtEdad.setText(String.valueOf(tblTabla.getValueAt(pos, 4)));
					txtCelular.setText(String.valueOf(tblTabla.getValueAt(pos, 5)));
					txtS.setText("");

					imprimir("CODIGO: " + leerCodigo());
					imprimir("NOMBRE: " + leerNombre());
					imprimir("APELLIDO: " + leerApellido());
					imprimir("DNI: " + leerDni());
					imprimir("EDAD: " + leerEdad());
					imprimir("CELULAR: " + leerCelular());
					imprimir("ESTADO: " + leerEstado());*/

				}

			});
		}
		
		arrayAlumnos.cargar();
		listar();
	}

	public boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
		
	}
	public boolean isString(String str) {
		try {
			Double.parseDouble(str);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
		
	}
	/*public boolean isString(String str){
		return str.matches("[a-zA-Z]*");
	}
	*/

	public void validarCampos() {
		btnAdicionar.setEnabled(!txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty()
				&& !txtdni.getText().isEmpty() && !txtEdad.getText().isEmpty() && !txtCelular.getText().isEmpty());

	}

	void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < arrayAlumnos.tamano(); i++) {
			String estado = "";

			switch (arrayAlumnos.obtener(i).getEstado()) {
			case 0:
				estado = "Registrado";
				break;
			case 1:
				estado = "Matriculado";
				break;
			case 2:
				estado = "Retirado";
				break;
			}

			Object[] fila = { arrayAlumnos.obtener(i).getCodAlumno(), arrayAlumnos.obtener(i).getNombre(),
					arrayAlumnos.obtener(i).getApellidos(), arrayAlumnos.obtener(i).getDni(),
					arrayAlumnos.obtener(i).getEdad(), arrayAlumnos.obtener(i).getCelular(), estado, };

			modelo.addRow(fila);

		}
	}

	// M�todos tipo void (sin par�metros)
	void imprimir() {
		imprimir("");
	}

	void limpieza() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtdni.setText("");
		txtEdad.setText("");
		txtCelular.setText("");
		txtNombre.requestFocus();
	}

	void imprimir(String s) {
		//txtS.append(s + "\n");
	}

	// M�todos que retornan valor (sin par�metros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(null, s);
	}

	int leerCodigo() {

		return arrayAlumnos.codigoCorrelativo();
	}

	String leerNombre() {
		return txtNombre.getText().trim();
	}

	String leerApellido() {
		return txtApellido.getText().trim();
	}

	String leerDni() {
		return txtdni.getText().trim();
	}

	int leerEdad() {
		return Integer.parseInt(txtEdad.getText().trim());
	}

	int leerCelular() {
		return Integer.parseInt(txtCelular.getText().trim());
	}

	int leerEstado() {
		return cboEstado.getSelectedIndex();
	}
	
	boolean validaNumeros(char _entrada){
		StringBuilder sb = new StringBuilder();
		sb.append(_entrada);
		return sb.toString().matches("[0-9]*");
	}
}
