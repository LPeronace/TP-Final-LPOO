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
		
	public Conexion() {
		
	}
	
	
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

		
}
