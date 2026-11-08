package estructuraTP.modelo;


public final class TrazadoFormal extends Barrio{
	private int cantEdificios;
	private int porcentajeAsfalto;
	
	
	public TrazadoFormal(String nombre, float superficie,int cantEdificios, int porcentajeAsfalto) {
		super(nombre, superficie);
		this.cantEdificios = cantEdificios;
		this.porcentajeAsfalto = porcentajeAsfalto;
	}


	@Override
	public float calcularDensidad() {
		float densidad = getPersonas().size() / cantEdificios;
		
		
		
		return densidad;
	}

	@Override
	public boolean tieneCriticidad() {
		return porcentajeAsfalto > 95;
	}	
}
