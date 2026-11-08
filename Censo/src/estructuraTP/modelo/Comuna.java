package estructuraTP.modelo;

import java.util.ArrayList;

public class Comuna {
	private String nombre;
	private String juntaComunal;
	private ArrayList<Barrio> barrios = new ArrayList<>();
	
	
	
	
	public boolean esPrioritaria() {	
		int tamañoTotal = barrios.size();
		int contadorPrioritario = 0;
		
		for(Barrio b: barrios) {
			if(b.tieneCriticidad()) {
				contadorPrioritario++;
			}
		}
		
		
		return (tamañoTotal*0.5) <= contadorPrioritario;
	}
	
	public void mostrarBarrios() {
		
	}
}
