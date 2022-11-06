package clases;

public class Retiro {
	private int numRetiro;//, numMatricula;
	private Matricula oMatricula;
	private String fecha,hora;
	
	public Retiro(int numRetiro, Matricula omatricula, String fecha, String hora) {
		
		this.numRetiro = numRetiro;
		this.oMatricula = omatricula;
		this.fecha = fecha;
		this.hora = hora;
	}
	public int getNumRetiro() {
		return numRetiro;
	}

	public void setNumRetiro(int numRetiro) {
		this.numRetiro = numRetiro;
	}

	public Matricula getMatricula() {
		return oMatricula;
	}

	public void setMatricula(Matricula omatricula) {
		this.oMatricula = omatricula;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}
