package arreglo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import clases.Alumno;
import clases.Curso;
import clases.Matricula;

public class ArregloMatricula {

	private ArrayList<Matricula> mat;
	private ArregloAlumnos listaAlumnos = new ArregloAlumnos();
	private ArregloCursos listaCursos = new ArregloCursos();

	public ArregloMatricula() {
		mat = new ArrayList<Matricula>();
		listaAlumnos.cargar();
		listaCursos.cargar();
	}

	// Operaciones p�blicas b�sicas

	public void adicionar(Matricula x) {
		mat.add(x);
	}

	public int tamano() {
		return mat.size();
	}

	public Matricula obtener(int i) {
		return mat.get(i);

	}

	public void eliminar(Matricula i) {
		mat.remove(i);
	}

	public int codigoCorrelativo() {
		if(tamano()==0){	
			return 100001;
		}
		return obtener(tamano() - 1).getNumMatricula() + 1;
	}

	public String hora() {
		return LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
	}

	public LocalDate fecha() {
		return LocalDate.now();
	}
	
	public Matricula buscarPorCodAlumno(int codigoAlumno){
		for (int i = 0; i <tamano(); i++) 
			if(obtener(i).getAlumno().getCodAlumno()==codigoAlumno){
				return obtener(i);
			}
		
		return null;	
	}
	public Matricula buscarPorNumMatricula(int numMatricula){
		for (int i = 0; i <tamano(); i++) 
			if(obtener(i).getNumMatricula()==numMatricula){
				return obtener(i);
			}
		
		return null;
	}
	
	public int buscarPorFiltro(String sCodigo, String sNombres, String sCurso){
		int index = -1;
		
		for (int i = 0; i <tamano(); i++) {
			
			Matricula oMatricula = obtener(i);
			
			if((oMatricula.getAlumno().getApellidos().toLowerCase().contains(sNombres.toLowerCase()) || 
					oMatricula.getAlumno().getNombre().toLowerCase().contains(sNombres.toLowerCase())) &&
					oMatricula.getCurso().getAsignatura().toLowerCase().contains(sCurso.toLowerCase())&&
				String.valueOf(oMatricula.getNumMatricula()).toLowerCase().contains(sCodigo.toLowerCase())){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public ArrayList<Matricula> obtenerMatriculaOrdenada(){
		
		ArrayList<Matricula> lMatricula = mat;
		Matricula aux;
		
		for (int i = 0; i < lMatricula.size() - 1; i++) {
			for (int j = 0; j < lMatricula.size() - 1; j++) {
				if (lMatricula.get(j).getCurso().getCodCurso() > lMatricula.get(j + 1).getCurso().getCodCurso()) {
					aux = lMatricula.get(j);
					lMatricula.set(j, lMatricula.get(j + 1));
					lMatricula.set(j + 1, aux);
				}
			}
		}
		
		return lMatricula;
		
	}
	
	public void cargar() {

		try {
			mat = new ArrayList<Matricula>();
			
			FileReader fr = new FileReader("Matricula.txt");
			BufferedReader br = new BufferedReader(fr);

			 int numMatricula,codALumno,codCurso;
			 Alumno oAlumno;
			 Curso oCurso;
			 String fecha,horas,linea;
			
			while( (linea = br.readLine()) != null) {
				String s[] = linea.split(";");
				
				numMatricula = Integer.parseInt(s[0].trim());
				
				codALumno = Integer.parseInt(s[1].trim());
				oAlumno = listaAlumnos.buscarPorCodAlumno(codALumno);
				
				codCurso = Integer.parseInt (s[2].trim());
				oCurso = listaCursos.buscarPorCod(codCurso);
				
				fecha = s[3].trim();
				horas= s[4].trim();

				adicionar(new Matricula(numMatricula,oAlumno,oCurso,fecha,horas));				
			}
			br.close();			
			
		}

		catch(Exception e) {
			System.out.println("ERROR: " + e);

		}
	}
	public void guardar() {

		try {
			FileWriter fw = new FileWriter("Matricula.txt");
			PrintWriter pw = new PrintWriter(fw);

			String linea;
			Matricula x;
			for(int i = 0; i< tamano(); i++) {
				x = obtener(i);
				linea = 
						x.getNumMatricula() + ";" +
						x.getAlumno().getCodAlumno() + ";" +
						x.getCurso().getCodCurso()+ ";" +
						x.getFecha()+ ";" +
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
