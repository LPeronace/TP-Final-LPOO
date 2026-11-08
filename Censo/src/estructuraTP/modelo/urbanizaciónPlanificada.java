package estructuraTP.modelo;

public final class urbanizaciónPlanificada extends Barrio{
	
	private String numLey;
	private boolean conexionAgua;
	
	
	public urbanizaciónPlanificada(String nombre, float superficie,String numLey, boolean conexionAgua) {
		super(nombre,superficie);
		this.numLey = numLey;
		this.conexionAgua = conexionAgua;
		
	}
	
	
	
	@Override
	public float calcularDensidad() {
		float densidad = getPersonas().size() / getSuperficie();
		
		
		
		return densidad;
	}

	@Override
	public boolean tieneCriticidad() {
		return conexionAgua;
	}	
	
}
