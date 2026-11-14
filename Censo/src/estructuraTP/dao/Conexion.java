package estructuraTP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.ComplejosHabitacionales;
import estructuraTP.modelo.Comuna;
import estructuraTP.modelo.TrazadoFormal;
import estructuraTP.modelo.UrbanizacionPlanificada;

public class Conexion {
	String user = "root";
	String psw = "admin";

								//aca el puerto//aca va la bdd
	String path = "jdbc:mysql://localhost:3306/Censo";
		
		
	public Connection conectar() {
		Connection c = null;
		try {
			//establece la conexion
			c = DriverManager.getConnection(path, user, psw);
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		
		return c;
	}
	
	public void cargarComuna(Comuna c) {
		Connection conexion = conectar();
		
		String sql = "INSERT INTO comuna(Nombre_Sede,Numero_Comuna)VALUES(?,?)";
		
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			
			stmt.setString(1, c.getNombre());
			stmt.setString(2, c.getNumeroSede());
			
			stmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally {
			try {
				conexion.close();

			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void eliminarComunaSeleccionada(int indiceBorrado) {
		Connection conexion = conectar();
		String sql = "DELETE FROM comuna where ID_comuna ="+indiceBorrado;
		
		try {
		
			PreparedStatement stmt = conexion.prepareStatement(sql);
			
			stmt.executeUpdate();
		
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally {
			try {
				conexion.close();

			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public ArrayList<Comuna> conseguirTodasLasComunas() {
		Connection c = conectar();
		
	    ArrayList<Comuna> comunas = new ArrayList<>();

	    String nombre;
	    String numeroComuna;
	    
	    String sql = "SELECT * FROM comuna";

	    try {
	        PreparedStatement stmt = c.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	
	        	nombre = rs.getString("Nombre_sede");
	        	numeroComuna = rs.getString("Numero_Comuna");
	        	
	            Comuna comuna = new Comuna(nombre,numeroComuna);
	            
	            comunas.add(comuna);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return comunas;
	}
	
	
	//constructor Barrio = String nombre, float superficie
	//complejo = String nombre, float superficie,String cantMonoblocks, boolean tieneConsorcio
	//trazado = String nombre, float superficie,int cantEdificios, int porcentajeAsfalto
	//urbanizacion = String nombre, float superficie,String numLey, boolean conexionAgua
	public ArrayList<Barrio> conseguirTodosLosBarrios(){
		ArrayList<Barrio> barrios = new ArrayList<>();
		Connection c = conectar();
		Barrio b;
		
		
		//creo todas las variables que se van a repetir si o si
	    String nombre;
	    float superficie;
	    
	    //complejo
	    int cantMonoblock;
	    boolean tieneConsorcio;
	    
	    //trazado
	    int porcentajeAsf;
	    int cantEdificios;
	    
	    //urbanizacion
	    String numLey;
	    boolean conexionAgua;
	    
	    //booleanos para comprobar si es nulo o no el itpo de barrio
	    boolean esComplejo;
	    boolean esTrazadoFormal;
	    
	    String sql = "SELECT * FROM barrio";
	    
	    try {
	        PreparedStatement stmt = c.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	
	        	//consigo las variables que si o si se repiten
	        	nombre = rs.getString("nombre");
	        	superficie = rs.getFloat("Superficie_H");
	        	
	        	cantMonoblock = rs.getInt("Cant_monoblock");
	        	tieneConsorcio = rs.getBoolean("Macro_activo");
	        	
	        	//Comprueba si el último campo leido es nulo o no
	        	esComplejo = !rs.wasNull();
	        
	        
	        	porcentajeAsf = rs.getInt("Porcentaje_asf");
	        	cantEdificios = rs.getInt("Cant_edificios");
	        	
	        	//ve si es trazado formal o no
	        	esTrazadoFormal = !rs.wasNull();
	        	
	        	//directamente agarra los datos de urbanización
	        	numLey = rs.getString("Nro_ley"); 
	        	conexionAgua = rs.getBoolean("Conexion_agua");
	        	
	        	
	        	if(esComplejo) {
	        		b = (Barrio) new ComplejosHabitacionales(nombre,superficie, cantMonoblock,tieneConsorcio);
	        	}
	        	else if(esTrazadoFormal) {
	        		b = (Barrio) new TrazadoFormal(nombre,superficie,cantEdificios, porcentajeAsf);
	        	}
	        	else {
	        		b = (Barrio) new UrbanizacionPlanificada(nombre,superficie,numLey,conexionAgua);
	        	}
	        	
	        	barrios.add(b);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		
		
		return barrios;
	}
	
	public ArrayList<Comuna> asociarComunasConBarrios(){
		ArrayList<Comuna> comunas = conseguirTodasLasComunas();
		ArrayList<Barrio> barrios = conseguirTodosLosBarrios();
	
			
		Connection c = conectar();
		String numeroSedeComuna;
	    String nombreBarrio;
	    
	    
	    String sql = "SELECT c.Numero_Comuna, b.nombre "
	    		+ "FROM comuna c "
	    		+ "INNER JOIN barrio b "
	    		+ "    ON c.ID_comuna = b.Comuna_ID_comuna;";

	    try {
	        PreparedStatement stmt = c.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	numeroSedeComuna = rs.getString("Numero_Comuna");
	        	nombreBarrio = rs.getString("nombre");
	        	for (Comuna com : comunas) {
	        		//hace un ciclo dentro de las comunas, para luego ver si la fila de la base de datos, que tiene un barrio asociado,
	        		//es el mismo que el del ciclo
	                if (com.getNumeroSede().equals(numeroSedeComuna)) {
	                	//si es, hace otro ciclo de todos los barrios
	                    for (Barrio b : barrios) {
	                    		//y luego comprueba de solamente añadir el barrio el cual corresponda con la fila del inner join
	                        if (b.getNombre().equals(nombreBarrio)) {

	                            com.getBarrios().add(b);
	                            break;//corta el for de busqueda de barrios si ya lo encontró

	                        }

	                    }

	                    break;//si ya añadió el barrio, corta el for de las comunas tambien para pasar a la siguiente fila

	                }

	            }

	        }

	    }catch(SQLException e) {
	        e.printStackTrace();
	    }
		
		return comunas;
	}
}