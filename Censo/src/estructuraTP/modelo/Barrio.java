package estructuraTP.modelo;

public abstract class Barrio {
	
	private String nombre;
	private float superficie;
	
	public abstract float calcularDensidad();
	
	public abstract boolean tieneCriticidad();
	
}
