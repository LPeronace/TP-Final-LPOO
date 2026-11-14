package estructuraTP.modelo;

public final class ComplejosHabitacionales extends Barrio{
	private int cantMonoblocks;
	private boolean tieneConsorcio;
	
	public ComplejosHabitacionales(String nombre, float superficie,int cantMonoblocks, boolean tieneConsorcio) {
		super(nombre, superficie);
		this.cantMonoblocks = cantMonoblocks;
		this.tieneConsorcio = tieneConsorcio;
	}
	
	
	@Override
	public float calcularDensidad() {
		float densidad = getPersonas().size() /  cantMonoblocks;
		
		
		
		return densidad;
	}

	@Override
	public boolean tieneCriticidad() {
		return tieneConsorcio;
	}	
	
	
}
