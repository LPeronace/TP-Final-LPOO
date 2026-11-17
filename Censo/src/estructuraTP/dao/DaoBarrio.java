package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.ComplejosHabitacionales;
import estructuraTP.modelo.TrazadoFormal;
import estructuraTP.modelo.UrbanizacionPlanificada;

public class DaoBarrio {
	private Conexion conex;
	private DaoComuna conexionComuna;
	
	public DaoBarrio(Conexion conex, DaoComuna conexionComuna) {
		this.conex = conex;
		this.conexionComuna = conexionComuna;
	}
	
	
	//constructor Barrio = String nombre, float superficie
		//complejo = String nombre, float superficie,String cantMonoblocks, boolean tieneConsorcio
		//trazado = String nombre, float superficie,int cantEdificios, int porcentajeAsfalto
		//urbanizacion = String nombre, float superficie,String numLey, boolean conexionAgua
	public ArrayList<Barrio> conseguirTodosLosBarrios(){
		ArrayList<Barrio> barrios = new ArrayList<>();
		Connection c = conex.conectar();
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
			
		 while (rs.next()) 
		 {
			   	 	
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
		        	
		        	
		   if(esComplejo) 
		   {
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
		
	public void cargarBarrio(Barrio b, String numeroSedeComuna) {
		Connection conexion = conex.conectar();
		
		
		
		String sql = "INSERT INTO barrio(Superficie_H,nombre,Comuna_ID_comuna,Cant_monoblock,Macro_activo,"
				+ "Cant_edificios,Porcentaje_asf,Nro_ley,Conexion_agua) "
				+ "VALUES(?,?,?,?,?,?,?,?,?);";
		
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			
			stmt.setFloat(1, b.getSuperficie());
			stmt.setString(2, b.getNombre());
			stmt.setInt(3, conexionComuna.conseguirIdComuna(numeroSedeComuna));
			
			for(int i = 4;i<10;i++) {
				stmt.setObject(i, null);
			}
			
			
			if(b instanceof ComplejosHabitacionales) {
				ComplejosHabitacionales barrioCH = (ComplejosHabitacionales) b; 
				
				stmt.setInt(4, barrioCH.getCantMonoblocks());
				stmt.setBoolean(5,barrioCH.getTieneConsorcio());
				
			}
			else if(b instanceof TrazadoFormal) {
				
				TrazadoFormal barrioTF = (TrazadoFormal) b;
				
				stmt.setInt(6, barrioTF.getCantEdificios());
				stmt.setInt(7, barrioTF.getPorcentajeAsfalto());
			}
			else if(b instanceof UrbanizacionPlanificada) {
				UrbanizacionPlanificada barrioUP = (UrbanizacionPlanificada) b;
				
				stmt.setString(8, barrioUP.getNumLey());
				stmt.setBoolean(9, barrioUP.getConexionAgua());
				
			}
			
		
			
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
	
	public boolean eliminarBarrioSeleccionado(String nombreBarrio) {
		Connection conexion = conex.conectar();
		
		String sql = "DELETE FROM barrio WHERE nombre = ?";
		int filasAfectadas = 0;
		try {
		
			PreparedStatement stmt = conexion.prepareStatement(sql);

	        stmt.setString(1, nombreBarrio);
	        
	        filasAfectadas = stmt.executeUpdate();

		
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally {
			try {
				conexion.close();

			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		
		return filasAfectadas > 0;
	}
	
	public Barrio buscarBarrioPorNombre(String nombreBarrioAntiguo){
		Connection c = conex.conectar();
		Barrio b = null;
		
		String nombre;
	    float superficie;
	    
	    int cantMonoblock;
	    boolean tieneConsorcio;
	    
	    int cantEdificios;
	    int porcentajeAsf;
	    
	    String numLey;
	    boolean conexionAgua;
	    
	    boolean esComplejo;
	    boolean esTrazadoFormal;
	    String sql = "SELECT * FROM barrio WHERE nombre = ?";

	    try {
	        PreparedStatement stmt = c.prepareStatement(sql);
	        
	        stmt.setString(1, nombreBarrioAntiguo);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        
	        while(rs.next()) {
	       	 	
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
	 		        	
	 		        	
	 		   if(esComplejo) 
	 		   {
	 			   b = (Barrio) new ComplejosHabitacionales(nombre,superficie, cantMonoblock,tieneConsorcio);
	 		   }
	 		   else if(esTrazadoFormal) {
	 			   b = (Barrio) new TrazadoFormal(nombre,superficie,cantEdificios, porcentajeAsf);
	 		   }
	 		   else {
	 			   b = (Barrio) new UrbanizacionPlanificada(nombre,superficie,numLey,conexionAgua);
	 		   }

	            
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    }		
	    
		return b;
	}
	
	
	public void modificarBarrioElegido(Barrio b, String nombreBarrioAntiguo, String numeroSedeComuna) {
		Connection c = conex.conectar();

	    String nombre = b.getNombre();
	    float superficie = b.getSuperficie();
	    
	    String sql = "UPDATE barrio SET Superficie_H = ?,nombre = ?,Comuna_ID_comuna = ?,"
	    		+"Cant_monoblock = ?,Macro_activo = ?,Cant_edificios = ?,Porcentaje_asf = ?,Nro_ley = ?, Conexion_agua = ? WHERE nombre = ?;";

	    try {
	        PreparedStatement stmt = c.prepareStatement(sql);
	        
	        stmt.setFloat(1,superficie);
	        stmt.setString(2,nombre);
	        stmt.setInt(3,conexionComuna.conseguirIdComuna(numeroSedeComuna));
	        stmt.setString(10,nombreBarrioAntiguo);
	        
	        for(int i = 4;i<10;i++) {
				stmt.setObject(i, null);
			}
			
			
			if(b instanceof ComplejosHabitacionales) {
				ComplejosHabitacionales barrioCH = (ComplejosHabitacionales) b; 
				
				stmt.setInt(4, barrioCH.getCantMonoblocks());
				stmt.setBoolean(5,barrioCH.getTieneConsorcio());
				
			}
			else if(b instanceof TrazadoFormal) {
				
				TrazadoFormal barrioTF = (TrazadoFormal) b;
				
				stmt.setInt(6, barrioTF.getCantEdificios());
				stmt.setInt(7, barrioTF.getPorcentajeAsfalto());
			}
			else if(b instanceof UrbanizacionPlanificada) {
				UrbanizacionPlanificada barrioUP = (UrbanizacionPlanificada) b;
				
				stmt.setString(8, barrioUP.getNumLey());
				stmt.setBoolean(9, barrioUP.getConexionAgua());
				
			}

	        stmt.executeUpdate();
	        

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
