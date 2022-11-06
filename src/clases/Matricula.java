package clases;

public class Matricula {
	
	private int numMatricula;//,codALumno,codCurso;
	private Alumno oAlumno;
	private Curso oCurso;
	private String fecha,horas;

	public Matricula(int numMatricula, Alumno oalumno, Curso ocurso, String fecha, String horas) {
		
		this.numMatricula = numMatricula;
		this.oAlumno = oalumno;
		this.oCurso = ocurso;
		this.fecha = fecha;
		this.horas = horas;
	}
	
	
	public int getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public Alumno getAlumno() {
		return oAlumno;
	}

	public void setAlumno(Alumno oalumno) {
		this.oAlumno = oalumno;
	}

	public Curso getCurso() {
		return oCurso;
	}

	public void setCurso(Curso ocurso) {
		this.oCurso = ocurso;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	
	
}
