package arreglo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Alumno;
import clases.Curso;
import clases.Matricula;
import clases.Retiro;
import clases.ComboItem;

public class ArregloCursos {

	private ArrayList<Curso> cur;

	public ArregloCursos() {

		cur = new ArrayList<Curso>();
		
	}

	// Operaciones p�blicas b�sicas

	public void adicionar(Curso x) {
		cur.add(x);
	}

	public int tamano() {
		return cur.size();
	}

	public Curso obtener(int i) {
		return cur.get(i);

	}

	public void eliminar(int i) {
		cur.remove(i);
	}
	
	public Vector comboCurso(){
		
		Vector model = new Vector();
		model.addElement( new ComboItem("0", "-Seleccionar-") );
		for (int i = 0; i < tamano(); i++) {
			ComboItem oComboItem = new ComboItem(String.valueOf(obtener(i).getCodCurso()), obtener(i).getAsignatura());
			
			model.addElement( oComboItem );
			
		}
		return model;
	}
	
	public int getItemCurso(int sCodigo){
		
		int pos = 0;
		for (int i = 0; i < tamano(); i++) {
			
			if(obtener(i).getCodCurso() == sCodigo)
				pos = i+1;
			
		}
		return pos;
	}

	public boolean CodigoRepetido(JTable tabla, int codigo, DefaultTableModel modelo) {
		boolean existe = false;
		for (int i = 0; i < tabla.getRowCount(); i++)
			if (tabla.getValueAt(i, 0).equals(codigo)) {
				existe = true;
			}
		
		return existe;
	}
	
	public int buscarPorFiltro(String sCodigo, String sAsignatura){
		int index = -1;
		
		for (int i = 0; i <tamano(); i++) {
			
			Curso oCurso = obtener(i);
			
			if(oCurso.getAsignatura().toLowerCase().contains(sAsignatura.toLowerCase()) &&
				String.valueOf(oCurso.getCodCurso()).toLowerCase().contains(sCodigo.toLowerCase())){
				index = i;
				break;
			}
		}
		return index;
	}

	public void ordenarCiclo() {
		Curso aux;

		for (int i = 0; i < tamano() - 1; i++) {
			for (int j = 0; j < tamano() - 1; j++) {
				if (obtener(j).getCodCurso() > obtener(j + 1).getCodCurso()) {
					aux = obtener(j);
					cur.set(j, obtener(j + 1));
					cur.set(j + 1, aux);
				}
			}
		}

	}

	public void ConfirmarEliminar(JTable tabla, DefaultTableModel modelo) {
		int i = tabla.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar el registro que desea eliminar", "ADVERTENCIA",
					JOptionPane.WARNING_MESSAGE);

		} else {
			int seleccion;
			seleccion = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar este registro",
					"Eliminar", JOptionPane.YES_NO_OPTION);
			if (seleccion == JOptionPane.YES_OPTION) {
				modelo.removeRow(i);
				eliminar(i);
				guardar();
			}
		}
	}
	
	public Curso buscarPorCod(int codigoCurso){
		for (int i = 0; i <tamano(); i++) 
			if(obtener(i).getCodCurso()==codigoCurso){
				return obtener(i);
			}
		
		return null;	
	}
	
	public void cargar() {

		try {
			cur = new ArrayList<Curso>();
			FileReader fr = new FileReader("Cursos.txt");
			BufferedReader br = new BufferedReader(fr);

			int codCurso;
			String nombre, linea;
			
			int horas, creditos, ciclo;
			
			while( (linea = br.readLine()) != null) {
				String s[] = linea.split(";");
				
				codCurso = Integer.parseInt(s[0].trim());
				nombre = s[1].trim();
				ciclo = Integer.parseInt (s[2].trim());
				creditos = Integer.parseInt (s[3].trim());
				horas= Integer.parseInt (s[4].trim());

				adicionar(new Curso(codCurso, nombre, ciclo, creditos, horas));				
			}
			br.close();			
			
		}

		catch(Exception e) {
			System.out.println("ERROR: " + e);

		}
	}
	
	public void guardar() {

		try {
			FileWriter fw = new FileWriter("Cursos.txt");
			PrintWriter pw = new PrintWriter(fw);

			String linea;
			Curso x;
			for(int i = 0; i< tamano(); i++) {
				x = obtener(i);
				linea = x.getCodCurso() + ";" +
						x.getAsignatura() + ";" +
						x.getCiclo() + ";" +
						x.getCreditos() + ";" +
						x.getHoras();

				pw.println(linea);
			}
			pw.close();
		}
		catch(Exception e) {
			System.out.println("ERROR: " + e);
		}
	}

}
