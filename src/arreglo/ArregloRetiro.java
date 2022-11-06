package arreglo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import clases.Matricula;
import clases.Retiro;



public class ArregloRetiro {


		private ArrayList<Retiro> ret;
		ArregloMatricula arrayMatriculas = new ArregloMatricula();
		
		public ArregloRetiro() {
			ret = new ArrayList<Retiro>();
			arrayMatriculas.cargar();
		}

		// Operaciones p�blicas b�sicas

		public void adicionar(Retiro x) {
			ret.add(x);
		}

		public int tamano() {
			return ret.size();
		}

		public Retiro obtener(int i) {
			return ret.get(i);

		}

		public void eliminar(int i) {
			ret.remove(i);
		}
		
		public int buscarPorFiltro(String sAlumno, String sCurso, String sMatricula){
			int index = -1;
			
			for (int i = 0; i <tamano(); i++) {
				
				Retiro oRetiro = obtener(i);
				
				if((oRetiro.getMatricula().getAlumno().getApellidos().toLowerCase().contains(sAlumno.toLowerCase()) || 
						oRetiro.getMatricula().getAlumno().getNombre().toLowerCase().contains(sAlumno.toLowerCase())) &&
						oRetiro.getMatricula().getCurso().getAsignatura().toLowerCase().contains(sCurso.toLowerCase())&&
					String.valueOf(oRetiro.getMatricula().getNumMatricula()).toLowerCase().contains(sMatricula.toLowerCase())){
					index = i;
					break;
				}
			}
			return index;
		}
		
		public int codigoCorrelativo() {
			if(tamano()==0){	
				return 200001;
			}
			return obtener(tamano()-1).getNumRetiro()+1;
		}
		public String hora() {
			return LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond();
		}

		public LocalDate fecha() {
			return LocalDate.now();
		}
		
		/*public Retiro buscarPorMatricula(int numMatricula){
			for (int i = 0; i <tama�o(); i++) 
				if(obtener(i).getMatricula().getNumMatricula()==numMatricula){
					return obtener(i);
				}
		return null;
	}*/
		public Retiro buscarPorNumeroRetiro(int numero){
			for (int i = 0; i <tamano(); i++) 
				if(obtener(i).getNumRetiro()==numero){
					return obtener(i);
				}
			return null;
		}
		

		public void cargar() {

			try {
				FileReader fr = new FileReader("Retiro.txt");
				BufferedReader br = new BufferedReader(fr);

				int numRetiro,numMatricula;
				Matricula oMatricula;
				String fecha,hora,linea;
				
				while( (linea = br.readLine()) != null) {
					String s[] = linea.split(";");
					
					numRetiro	= Integer.parseInt(s[0].trim());
					
					numMatricula = Integer.parseInt(s[1].trim());
					oMatricula = arrayMatriculas.buscarPorNumMatricula(numMatricula);
					fecha = s[2].trim();
					hora= s[3].trim();

					adicionar(new Retiro(numRetiro,oMatricula,fecha,hora));				
				}
				br.close();			
				
			}

			catch(Exception e) {
				System.out.println("ERROR: " + e);

			}
		}
		public void guardar() {

			try {
				FileWriter fw = new FileWriter("Retiro.txt");
				PrintWriter pw = new PrintWriter(fw);

				String linea;
				Retiro x;
				for(int i = 0; i< tamano(); i++) {
					x = obtener(i);
					linea = x.getNumRetiro()+";"+
							x.getMatricula().getNumMatricula() + ";" +
							x.getFecha()+ ";" +
							x.getHora();

					pw.println(linea);
				}
				pw.close();
			}
			catch(Exception e) {
				System.out.println("ERROR: " + e);
			}
		}
		
}
	

