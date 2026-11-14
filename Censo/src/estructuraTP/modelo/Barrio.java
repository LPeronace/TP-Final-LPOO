package estructuraTP.modelo;
import java.util.ArrayList;
public abstract class Barrio {
	
	private String nombre;
	private float superficie;
	private ArrayList<Persona> personas = new ArrayList<>();
	
	
	
	public Barrio(String nombre, float superficie) {
		this.nombre = nombre;
		this.superficie = superficie;
	}
	public void agregarPersonas(Persona p) {
		personas.add(p);
	}
	
	public ArrayList<Persona> getPersonas() {
		return personas;
	}
	public float getSuperficie() {
		return superficie;
	}
	
	public String getNombre() {
		return nombre;
	}
	public abstract float calcularDensidad();
	
	public abstract boolean tieneCriticidad();
	
}
