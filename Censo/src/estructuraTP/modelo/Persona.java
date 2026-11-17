package estructuraTP.modelo;
import java.time.*;
public class Persona {
	private String dni;
	private String nombre;
	private String apellido;
	private Estado situacionLaboral;
	private LocalDate fechaNacimiento;
	
	public Persona(String dni, String nombre,String apellido, Estado situacionLaboral, LocalDate fechaNacimiento) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.situacionLaboral = situacionLaboral;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getDNI() {
		return dni;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public Estado getSituacionLaboral() {
		return situacionLaboral;
	}
	public LocalDate getFechaNacimeinto() {
		return fechaNacimiento;
	}
	
	
}
