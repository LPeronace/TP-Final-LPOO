package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.Comuna;

public class DaoComuna {
	private Conexion conex;
	private DaoBarrio conexionBarrio;
	
	public DaoComuna(Conexion conex) {
		this.conex = conex;
		conexionBarrio = new DaoBarrio(conex,this);
	}
	
	public void cargarComuna(Comuna c) {
		
		System.out.println();
		String sql = "INSERT INTO comuna(Nombre_Sede,Numero_Comuna)VALUES(?,?)";
		
		try(Connection conexion = conex.conectar()) {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			
			stmt.setString(1, c.getNombre());
			stmt.setString(2, c.getNumeroSede());
			
			stmt.executeUpdate();
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void modificarComuna(Comuna comuna, String numeroSedeAntiguo) {
	    String nombre = comuna.getNombre();
	    String numeroSede = comuna.getNumeroSede();
	    
	    String sql = "UPDATE comuna "
	    		+ "SET Nombre_Sede = ?, "
	    		+ "    Numero_Comuna = ? "
	    		+ "WHERE Numero_Comuna = ?;";

	    try(Connection conexion = conex.conectar()){
	        PreparedStatement stmt = conexion.prepareStatement(sql);
	        
	        stmt.setString(1,nombre);
	        stmt.setString(2,numeroSede);
	        stmt.setString(3,numeroSedeAntiguo);
	        
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}
	
	public boolean eliminarComunaSeleccionada(String numeroSede) {
		String sql = "DELETE FROM comuna where Numero_Comuna = ?";
		int filasAfectadas = 0;
		try(Connection conexion = conex.conectar()){
		
			PreparedStatement stmt = conexion.prepareStatement(sql);

	        stmt.setString(1, numeroSede);
	        
	        filasAfectadas = stmt.executeUpdate();
	        

		
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return filasAfectadas > 0;
	}
	
	public ArrayList<Comuna> conseguirTodasLasComunas() {	
	    ArrayList<Comuna> comunas = new ArrayList<>();

	    String nombre;
	    String numeroComuna;
	    
	    String sql = "SELECT * FROM comuna";

	    try(Connection conexion = conex.conectar()){
	        PreparedStatement stmt = conexion.prepareStatement(sql);
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
	
	
	
	public Comuna buscarComunaEspecifica(String numeroSede) {
		Comuna comuna = null;
		
	    String nombre;
	    String numeroComuna;
	    
	    String sql = "SELECT * FROM comuna WHERE Numero_Comuna = ?";

	    try(Connection conexion = conex.conectar()){
	        PreparedStatement stmt = conexion.prepareStatement(sql);
	        
	        stmt.setString(1, numeroSede);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        
	        while(rs.next()) {
	        	
	        	nombre = rs.getString("Nombre_sede");
	        	numeroComuna = rs.getString("Numero_Comuna");
	        	
	        	comuna = new Comuna(nombre,numeroComuna);
	            
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    }		
	    
		return comuna;
	}
	public int conseguirIdComuna(String numeroSedeComuna) {
		int id = 0;
		
		String sql = "SELECT ID_Comuna FROM Comuna WHERE Numero_Comuna = ?";

		try(Connection conexion = conex.conectar()){
	        PreparedStatement stmt = conexion.prepareStatement(sql);
	        stmt.setString(1, numeroSedeComuna);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	        	id = rs.getInt("ID_comuna");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		return id;
	}
	
	public boolean existeComunaConEsteNumero(String numeroComunaACheckear) {
		boolean existe = false;
		
		String sql = "SELECT * FROM comuna WHERE Numero_Comuna = ?";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
		
			stmt.setString(1, numeroComunaACheckear);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				existe = true;
			}
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return existe;
	}
	
	
	
	public ArrayList<Comuna> asociarComunasConBarrios(){
		ArrayList<Comuna> comunas = conseguirTodasLasComunas();
		ArrayList<Barrio> barrios = conexionBarrio.conseguirTodosLosBarrios();

		String numeroSedeComuna;
	    String nombreBarrio;
	    
	    
	    String sql = "SELECT c.Numero_Comuna, b.nombre "
	    		+ "FROM comuna c "
	    		+ "INNER JOIN barrio b "
	    		+ "    ON c.ID_comuna = b.Comuna_ID_comuna;";

	    try(Connection conexion = conex.conectar()){
	        PreparedStatement stmt = conexion.prepareStatement(sql);
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
