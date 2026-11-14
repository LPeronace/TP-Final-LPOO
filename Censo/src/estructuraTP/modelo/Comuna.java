package estructuraTP.modelo;

import java.util.ArrayList;

public class Comuna {
	private String nombre;
	private String numeroSede;
	private ArrayList<Barrio> barrios = new ArrayList<>();
	private int cantidadBarrio;
	
	public Comuna(String nombre, String numeroSede) {
		this.nombre = nombre;
		this.numeroSede = numeroSede;
	}
	public Comuna() {
		
	}
	
	public boolean esPrioritaria() {	
		int contadorPrioritario = 0;
		
		for(Barrio b: barrios) {
			if(b.tieneCriticidad()) {
				contadorPrioritario++;
			}
		}
		
		
		return (cantidadBarrio*0.5) <= contadorPrioritario;
	}
	
	public ArrayList<Barrio> getBarrios(){
		return barrios;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getNumeroSede() {
		return numeroSede;
	}
	
	public int getCantidadBarrio() {
		return barrios.size();
	}
}
