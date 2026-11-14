package estructuraTP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import estructuraTP.modelo.Comuna;

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
		
	}
	
	public void eliminarComunaSeleccionada(int indiceBorrado) {
		
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
	
	
	
}
