package estructuraTP.modelo;
import java.time.*;
public class Persona {
	private String dni;
	private String nombre;
	private Estado situacionLaboral;
	private LocalDate fechaNacimiento;
	
	public Persona(String dni, String nombre, Estado situacionLaboral, LocalDate fechaNacimiento) {
		this.dni = dni;
		this.nombre = nombre;
		this.situacionLaboral = situacionLaboral;
		this.fechaNacimiento = fechaNacimiento;
	}
	
}
