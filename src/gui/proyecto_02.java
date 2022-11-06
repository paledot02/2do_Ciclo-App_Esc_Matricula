package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloCursos;
import clases.Curso;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class proyecto_02 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblTabla;
	private JTextField txtCodigo;
	private JTextField txtCredito;
	private JTextField txtAsignatura;
	private JTextField txtCiclo;
	private JTextField txtHoras;
	private DefaultTableModel modelo;
	private JButton btnAdicionar;
	private JButton btnConsultar;
	private JButton btnModificar;
	ArregloCursos gg = new ArregloCursos();
	private JPanel panel;
	private JLabel label;
	private JLabel lblAsignatura_1;
	private JTextField txt_filtro_codigo;
	private JTextField txt_filtro_asignatura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			proyecto_02 dialog = new proyecto_02();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public proyecto_02() {
		gg.cargar();
		setTitle("Mantenimiento/Curso");
		setBounds(100, 100, 610, 552);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 138, 427, 274);
		contentPanel.add(scrollPane);

		tblTabla = new JTable();
		tblTabla.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {

				int pos = tblTabla.rowAtPoint(arg0.getPoint());
				
				if(pos != -1){
				btnAdicionar.setEnabled(false);
				txtCodigo.setEnabled(false);
				
				txtCodigo.setText(String.valueOf(tblTabla.getValueAt(pos, 0)));
				txtAsignatura.setText(String.valueOf(tblTabla.getValueAt(pos, 1)));
				txtCiclo.setText(String.valueOf(tblTabla.getValueAt(pos, 2)));
				txtCredito.setText(String.valueOf(tblTabla.getValueAt(pos, 3)));
				txtHoras.setText(String.valueOf(tblTabla.getValueAt(pos, 4)));
				}
				
			}
		});
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (CodigoRepetido()){
					JOptionPane.showMessageDialog(null, "El Codigo ya se encuentra registrado");
					return;
				}
				
				Curso nuevo = new Curso(leerCodigo(), leerAsignatura(), leerCiclo(), leerCredito(), leerHoras());
				gg.adicionar(nuevo);
				gg.ordenarCiclo();
				gg.guardar();
				
				listar();
				limpieza();

			}

		});
		btnAdicionar.setBounds(483, 179, 89, 23);
		contentPanel.add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int mod = tblTabla.getSelectedRow();
				if (mod !=-1) {

					tblTabla.setValueAt(txtCodigo.getText(), mod, 0);
					tblTabla.setValueAt(txtAsignatura.getText(), mod, 1);
					tblTabla.setValueAt(txtCiclo.getText(), mod, 2);
					tblTabla.setValueAt(txtCredito.getText(), mod, 3);
					tblTabla.setValueAt(txtHoras.getText(), mod, 4);
					
					Curso oCurso = gg.obtener(mod);
					oCurso.setCodCurso(Integer.valueOf(txtCodigo.getText()));
					oCurso.setAsignatura(txtAsignatura.getText());
					oCurso.setCiclo(Integer.valueOf(txtCiclo.getText()));
					oCurso.setCreditos(Integer.valueOf(txtCredito.getText()));
					gg.guardar();
					limpieza();
					
				}
			}
		});
		btnModificar.setBounds(483, 213, 89, 23);
		contentPanel.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				gg.ConfirmarEliminar(tblTabla,modelo);				
				limpieza();
			}
		});
		btnEliminar.setBounds(483, 247, 89, 23);
		contentPanel.add(btnEliminar);

		JLabel lblNewLabel = new JLabel("Cod.Curso");
		lblNewLabel.setBounds(10, 22, 78, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblAsignatura = new JLabel("Asignatura");
		lblAsignatura.setBounds(189, 22, 69, 14);
		contentPanel.add(lblAsignatura);

		JLabel lblCiclo = new JLabel("Ciclo");
		lblCiclo.setBounds(391, 22, 46, 14);
		contentPanel.add(lblCiclo);

		JLabel lblCrditos = new JLabel("Creditos");
		lblCrditos.setBounds(10, 95, 78, 14);
		contentPanel.add(lblCrditos);

		JLabel lblHoras = new JLabel("Horas");
		lblHoras.setBounds(189, 95, 46, 14);
		contentPanel.add(lblHoras);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarCampos();
			}
			
			@Override
	        public void keyTyped(KeyEvent e) {
				if(!validaNumeros(e.getKeyChar())) {
					e.consume();
		        }
				
	            if (txtCodigo.getText().length() >= 4 )
	                e.consume();
	        }
		});
		
		txtCodigo.setBounds(94, 19, 69, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtCredito = new JTextField();
		txtCredito.setColumns(10);
		txtCredito.setBounds(94, 92, 69, 20);
		txtCredito.addKeyListener(new KeyAdapter() {
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
		contentPanel.add(txtCredito);

		txtAsignatura = new JTextField();
		txtAsignatura.setColumns(10);
		txtAsignatura.setBounds(286, 19, 69, 20);
		txtAsignatura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarCampos();
			}
		});
		contentPanel.add(txtAsignatura);

		txtCiclo = new JTextField();
		txtCiclo.setColumns(10);
		txtCiclo.setBounds(475, 19, 69, 20);
		txtCiclo.addKeyListener(new KeyAdapter() {
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
		contentPanel.add(txtCiclo);

		txtHoras = new JTextField();
		txtHoras.setColumns(10);
		txtHoras.setBounds(286, 92, 69, 20);
		txtHoras.addKeyListener(new KeyAdapter() {
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
		contentPanel.add(txtHoras);

		modelo = new DefaultTableModel();
		modelo.addColumn("Cod. Curso");
		modelo.addColumn("Asignatura");
		modelo.addColumn("Ciclo");
		modelo.addColumn("Creditos");
		modelo.addColumn("Horas");
		tblTabla.setModel(modelo);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 430, 574, 68);
		contentPanel.add(panel);
		
		label = new JLabel("C\u00F3digo:");
		label.setBounds(10, 11, 113, 14);
		panel.add(label);
		
		lblAsignatura_1 = new JLabel("Asignatura:");
		lblAsignatura_1.setBounds(10, 36, 113, 14);
		panel.add(lblAsignatura_1);
		
		txt_filtro_codigo = new JTextField();
		txt_filtro_codigo.setColumns(10);
		txt_filtro_codigo.setBounds(133, 11, 190, 20);
		panel.add(txt_filtro_codigo);
		
		txt_filtro_asignatura = new JTextField();
		txt_filtro_asignatura.setColumns(10);
		txt_filtro_asignatura.setBounds(133, 36, 190, 20);
		panel.add(txt_filtro_asignatura);
		
				btnConsultar = new JButton("Consultar");
				btnConsultar.setBounds(333, 11, 231, 45);
				panel.add(btnConsultar);
				btnConsultar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String fCodigo = txt_filtro_codigo.getText();
						String fAsignatura = txt_filtro_asignatura.getText();
						
						
						if(fCodigo.trim().equals("") && fAsignatura.trim().equals("")){
							JOptionPane.showMessageDialog(null, "Debe ingresar por lo menos un filtro.",
									"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						int busquedaIndex = gg.buscarPorFiltro(fCodigo, fAsignatura);
						if(busquedaIndex >= 0){
							tblTabla.setRowSelectionInterval(busquedaIndex, busquedaIndex);
						}else{
							JOptionPane.showMessageDialog(null, "No se encontraron resultados.",
									"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
						}
						
						/*for (int i=0; i<tblTabla.getRowCount();i++){
							if(tblTabla.getValueAt(i,0).equals(leerCodigo())){
								
								txtAsignatura.setText(String.valueOf(tblTabla.getValueAt(i, 1)));
								txtCiclo.setText(String.valueOf(tblTabla.getValueAt(i, 2)));
								txtCredito.setText(String.valueOf(tblTabla.getValueAt(i, 3)));
								txtHoras.setText(String.valueOf(tblTabla.getValueAt(i, 4)));
								
							}
							
						}	*/
					}
				});
		listar();
			
	}
	
	public void validarCampos(){
		btnAdicionar.setEnabled((!txtCodigo.getText().isEmpty()&& 
								 !txtCredito.getText().isEmpty()&&
								 !txtAsignatura.getText().isEmpty()&&
								 !txtCiclo.getText().isEmpty()&&
								 !txtHoras.getText().isEmpty())); 		
	}
	

	

	void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < gg.tamano(); i++) {
			Object[] fila = { 
							gg.obtener(i).getCodCurso(),
							gg.obtener(i).getAsignatura(),
							gg.obtener(i).getCiclo(),
							gg.obtener(i).getCreditos(),
							gg.obtener(i).getHoras() };

			modelo.addRow(fila);

		}
	}

	// VALIDACIONES
	public boolean CodigoRepetido() {
		return gg.CodigoRepetido(tblTabla, leerCodigo(), modelo);
	}
	void limpieza() {
		txtCodigo.setText("");
		txtAsignatura.setText("");
		txtCiclo.setText("");
		txtCredito.setText("");
		txtHoras.setText("");
		txtCodigo.requestFocus();
		
		btnAdicionar.setEnabled(false);
		txtCodigo.setEnabled(true);
	}
	

	void imprimir(String s) {
		//txtS.append(s + "\n");
	}

	// M�todos que retornan valor (sin par�metros)

	int leerCodigo() {

		return Integer.parseInt(txtCodigo.getText().trim());
	}

	String leerAsignatura() {
		return txtAsignatura.getText().trim();
	}

	int leerCiclo() {
		return Integer.parseInt(txtCiclo.getText().trim());
	}

	int leerCredito() {
		return Integer.parseInt(txtCredito.getText().trim());
	}

	int leerHoras() {
		return Integer.parseInt(txtHoras.getText().trim());
	}

	boolean validaNumeros(char _entrada){
		StringBuilder sb = new StringBuilder();
		sb.append(_entrada);
		return sb.toString().matches("[0-9]*");//regex
	}
}