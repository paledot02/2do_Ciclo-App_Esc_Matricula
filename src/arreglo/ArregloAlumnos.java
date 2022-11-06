package arreglo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Alumno;
import clases.ComboItem;
import clases.Matricula;
import clases.Retiro;
import gui.proyecto_01;

//Alumno->ArregloAlumno->Gui

public class ArregloAlumnos {
	// Atributo privado
	private ArrayList<Alumno> pac;

	// Constructor
	public ArregloAlumnos() {

		pac = new ArrayList<Alumno>();

	}

	// Operaciones p�blicas b�sicas
	public void adicionar(Alumno x) {
		pac.add(x);
	}

	public int tamano() {
		return pac.size();
	}

	public Alumno obtener(int i) {
		return pac.get(i);

	}

	public void eliminar(int i) {
		pac.remove(i);
	}

	public int codigoCorrelativo() {
		if(tamano() == 0){
			return 202010001;
		}
		return obtener(tamano() - 1).getCodAlumno() + 1;
	}

	public int buscarPorFiltro(String sCodigo, String sNombres, String sDni){
		int index = -1;
		
		for (int i = 0; i <tamano(); i++) {
			
			Alumno oAlumno = obtener(i);
			
			if((oAlumno.getApellidos().toLowerCase().contains(sNombres.toLowerCase()) || 
					oAlumno.getNombre().toLowerCase().contains(sNombres.toLowerCase())) &&
					oAlumno.getDni().toLowerCase().contains(sDni.toLowerCase())&&
				String.valueOf(oAlumno.getCodAlumno()).toLowerCase().contains(sCodigo.toLowerCase())){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public Alumno buscar(String dni) {
		for (int i = 0; i < tamano(); i++) {
			if (obtener(i).getDni().equals(dni)) {
				
				return obtener(i);
			}
		}
		return null;
	}
	public void cargar() {

		try {
			pac = new ArrayList<Alumno>();
			FileReader fr = new FileReader("alumnos.txt");
			BufferedReader br = new BufferedReader(fr);

			int codAlumno;
			String nombre,apellidos,dni,linea;
			
			int edad, celular, estado;
			
			while( (linea = br.readLine()) != null) {
				String s[] = linea.split(";");
				
				codAlumno = Integer.parseInt(s[0].trim());
				nombre = s[1].trim();
				apellidos = s[2].trim();
				dni = s[3].trim();
				edad= Integer.parseInt (s[4].trim());
				celular=Integer.parseInt(s[5].trim());
				estado=Integer.parseInt(s[6].trim());

				adicionar(new Alumno(codAlumno, nombre, apellidos, dni,edad,celular,estado));				
			}
			br.close();			
			
		}

		catch(Exception e) {
			System.out.println("ERROR: " + e);

		}
	}
	
	public Vector comboAlumno(){
		
		Vector model = new Vector();
        
        
		ArrayList<ComboItem> lComboItem = new ArrayList<ComboItem>();
		
		model.addElement( new ComboItem("0", "-Seleccionar-") );
		for (int i = 0; i < tamano(); i++) {
			ComboItem oComboItem = new ComboItem(String.valueOf(obtener(i).getCodAlumno()), obtener(i).getNombre()+" "+obtener(i).getApellidos());
			
			model.addElement( oComboItem );
			
		}
		
		return model;
	}
	
	public int getItemAlumno(int sCodigo){
		
		int pos = 0;
		for (int i = 0; i < tamano(); i++) {
			
			if(obtener(i).getCodAlumno() == sCodigo){
				pos = i+1;
				break;	
			}
		}
		return pos;
	}

	public void guardar() {

		try {
			FileWriter fw = new FileWriter("alumnos.txt");
			PrintWriter pw = new PrintWriter(fw);

			String linea;
			Alumno x;
			for(int i = 0; i< tamano(); i++) {
				x = obtener(i);
				linea = x.getCodAlumno() + ";" +
						x.getNombre() + ";" +
						x.getApellidos() + ";" +
						x.getDni()+ ";" +
						x.getEdad()+ ";" +
						x.getCelular()+ ";" +
						x.getEstado();

				pw.println(linea);
			}
			pw.close();
		}
		catch(Exception e) {
			System.out.println("ERROR: " + e);
		}
	}
	
	public Alumno buscarPorCodAlumno(int codigoAlumno){
		for (int i = 0; i <tamano(); i++) 
			if(obtener(i).getCodAlumno()==codigoAlumno){
				return obtener(i);
			}
		
		return null;	
	}
	
}
