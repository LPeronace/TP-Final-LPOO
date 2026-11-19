package estructuraTP.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.Estado;
import estructuraTP.modelo.Persona;

public class DaoPersona {
	private Conexion conex;
	private DaoBarrio conexionBarrio;
	
	public DaoPersona(Conexion conex, DaoBarrio conexionBarrio) {
		this.conex = conex;
		this.conexionBarrio = conexionBarrio;
	}
	
	public int conseguirIdPersonaConDni(String dni) {
		int id = 0;
		
		String sql = "SELECT ID_Persona FROM persona WHERE dni = ?";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, dni);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("ID_Persona");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public int conseguirIdSituacionLaboral(Estado situacionLaboral) {
		int id = 0;
		
		String sql = "SELECT ID_Situacion_laboral FROM situacion_laboral WHERE estado = ?";
		String nombreSituacion = situacionLaboral.toString();
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, nombreSituacion);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("ID_Situacion_laboral");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return id;
	}

	public void crearPersona(Persona persona,String nombreBarrioAsociado) {
		
		String sql = "INSERT INTO persona(DNI,nombre,Apellido,Fecha_nacimiento,Situacion_laboral_ID_Situacion_laboral,Barrio_ID_Barrio) VALUES(?,?,?,?,?,?)";
		
		String nombre = persona.getNombre();
		String DNI = persona.getDNI();
		String apellido = persona.getApellido();
		
		Date fechaNacimiento = Date.valueOf(persona.getFechaNacimiento());
		
		Estado situacionLaboral = persona.getSituacionLaboral();
		
		int idSituacionLaboral = conseguirIdSituacionLaboral(situacionLaboral);
		
		int idBarrioAsociado = conexionBarrio.conseguirIdBarrioAsociado(nombreBarrioAsociado);
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, DNI);
			
			stmt.setString(2, nombre);
			
			stmt.setString(3, apellido);
			
			stmt.setDate(4, fechaNacimiento);
			
			stmt.setInt(5, idSituacionLaboral);
			
			stmt.setInt(6, idBarrioAsociado);
			
			stmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Persona> buscarPersonaAproximacion(String textoABuscar) {
		ArrayList<Persona> personas = new ArrayList<>();
		
		String dni;
		String nombre;
		String apellido;
		Estado situacionLaboral;
		LocalDate fechaNacimiento;
		String nombreDelEstado;
		
		String sql = "SELECT p.DNI,p.Nombre,p.Apellido,p.Fecha_nacimiento,sl.estado "
		+ "FROM Persona p "
		+ "INNER JOIN situacion_laboral sl "
		+ "on p.Situacion_laboral_ID_Situacion_laboral = sl.ID_Situacion_laboral "
		+"WHERE p.Nombre Like ?;";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, textoABuscar+"%");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				dni = rs.getString("DNI");
				
				nombre = rs.getString("nombre");
				
				apellido = rs.getString("Apellido");
				
				nombreDelEstado = rs.getString("estado");
				
				switch(nombreDelEstado) {
				
				case "Ocupado":
					situacionLaboral = Estado.OCUPADO;
					break;
					
				case "Desocupado":
					situacionLaboral = Estado.DESOCUPADO;
					break;
					
				case "Subocupado":
					situacionLaboral = Estado.SUBOCUPADO;
					break;
					
				default:
						situacionLaboral = null;
					break;
				}
				
				fechaNacimiento = rs.getDate("Fecha_nacimiento").toLocalDate();
				
				
				Persona p = new Persona(dni,nombre,apellido,situacionLaboral,fechaNacimiento);
				
				personas.add(p);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return personas;
	}
	
	public ArrayList<Persona> conseguirTodasLasPersonas(){
		ArrayList<Persona> personas = new ArrayList<>();
		
		String dni;
		String nombre;
		String apellido;
		Estado situacionLaboral;
		LocalDate fechaNacimiento;
		
		String nombreDelEstado;
		
		
		String sql = "SELECT p.DNI,p.Nombre,p.Apellido,p.Fecha_nacimiento,sl.estado "
				+ "FROM Persona p "
				+ "INNER JOIN situacion_laboral sl "
				+ "on p.Situacion_laboral_ID_Situacion_laboral = sl.ID_Situacion_laboral";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				
				dni = rs.getString("DNI");
				
				nombre = rs.getString("nombre");
				
				apellido = rs.getString("Apellido");
				
				nombreDelEstado = rs.getString("estado");
				
				switch(nombreDelEstado) {
				
				case "Ocupado":
					situacionLaboral = Estado.OCUPADO;
					break;
					
				case "Desocupado":
					situacionLaboral = Estado.DESOCUPADO;
					break;
					
				case "Subocupado":
					situacionLaboral = Estado.SUBOCUPADO;
					break;
					
					default:
						situacionLaboral = null;
					break;
				}
				
				fechaNacimiento = rs.getDate("Fecha_nacimiento").toLocalDate();
				
				
				Persona p = new Persona(dni,nombre,apellido,situacionLaboral,fechaNacimiento);
				
				personas.add(p);
			}
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return personas;
	}
	
	public boolean verSiExisteDni(String dni) {
		boolean existe = false;
		
		String sql = "SELECT * FROM persona WHERE dni = ?";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, dni);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				existe = true;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public void modificarPersona(Persona persona,String nombreBarrioAsociado,String dniAntiguo) {
		String sql = "UPDATE Persona "
	    		+ "SET dni = ?,nombre = ?, Apellido = ?,Fecha_nacimiento = ?, Situacion_laboral_ID_Situacion_laboral = ?, Barrio_ID_barrio = ? "
	    		+ "WHERE dni = ?;";
		
		String nombre = persona.getNombre();
		String DNI = persona.getDNI();
		String apellido = persona.getApellido();
		
		Date fechaNacimiento = Date.valueOf(persona.getFechaNacimiento());
		
		Estado situacionLaboral = persona.getSituacionLaboral();
		
		int idSituacionLaboral = conseguirIdSituacionLaboral(situacionLaboral);
		
		int idBarrioAsociado = conexionBarrio.conseguirIdBarrioAsociado(nombreBarrioAsociado);
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, DNI);
			stmt.setString(2, nombre);
			stmt.setString(3, apellido);
			stmt.setDate(4, fechaNacimiento);
			stmt.setInt(5, idSituacionLaboral);
			stmt.setInt(6, idBarrioAsociado);
			stmt.setString(7, dniAntiguo);
			
			System.out.println(stmt.executeUpdate());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public boolean eliminarPersonaSeleccionada(String dni) {
		
		String sql = "DELETE FROM persona WHERE dni = ?";
		int filasAfectadas = 0;
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, dni);
			
			filasAfectadas = stmt.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return filasAfectadas > 0;
	}
	
	public Persona conseguirPersonaPorDNI(String dni) {
		Persona p = null;
		
		String nombre;
		String apellido;
		LocalDate fecha;
		Estado situacionLaboral;
		
		String nombreDelEstado;
		
		String sql = "SELECT p.DNI,p.Nombre,p.Apellido,p.Fecha_nacimiento,sl.estado "
				+ "FROM Persona p "
				+ "INNER JOIN situacion_laboral sl "
				+ "on p.Situacion_laboral_ID_Situacion_laboral = sl.ID_Situacion_laboral "
				+ "WHERE p.dni = ?";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, dni);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				nombre = rs.getString("nombre");
				
				apellido = rs.getString("Apellido");
				
				nombreDelEstado = rs.getString("estado");
				
				switch(nombreDelEstado) {
				
				case "Ocupado":
					situacionLaboral = Estado.OCUPADO;
					break;
					
				case "Desocupado":
					situacionLaboral = Estado.DESOCUPADO;
					break;
					
				case "Subocupado":
					situacionLaboral = Estado.SUBOCUPADO;
					break;
					
					default:
						situacionLaboral = null;
					break;
				}
				
				fecha = rs.getDate("Fecha_nacimiento").toLocalDate();
				
				
				p = new Persona(dni,nombre,apellido,situacionLaboral,fecha);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public void añadirHistorial(String dni, String nombreBarrio, LocalDate fechaCambio) {

	    Date fechaSQL = Date.valueOf(fechaCambio);

	    int idBarrio = conexionBarrio.conseguirIdBarrioAsociado(nombreBarrio);
	    int idPersona = conseguirIdPersonaConDni(dni);

	   
	    String sqlHistorial = "INSERT INTO historial(ID_persona, ID_barrio, Fecha_traslado) "
	            + "VALUES (?, ?, ?)";

	    String sqlPersona = "UPDATE persona "
	            + "SET Barrio_ID_barrio = ? "
	            + "WHERE dni = ?";

	    try (Connection c = conex.conectar()) {
	    	PreparedStatement stmtHistorial = c.prepareStatement(sqlHistorial);
            PreparedStatement stmtPersona = c.prepareStatement(sqlPersona);


	        stmtHistorial.setInt(1, idPersona);
	        stmtHistorial.setInt(2, idBarrio);
	        stmtHistorial.setDate(3, fechaSQL);

	        stmtHistorial.executeUpdate();

	        stmtPersona.setInt(1, idBarrio);
	        stmtPersona.setString(2, dni);

	        stmtPersona.executeUpdate();


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	/**
	 * Este método lo que hace es basicamente conseguir parte de todos los datos para cargar la tabla del PanelHistorial, aunque lo tuvimos
	 * que dividir en dos métodos, ya que no podíamos combiar un localDate con enteros, y no queríamos crear otro objeto
	 */
	public ArrayList<Integer> conseguirIDBarrioEnHistorial(int id_Persona) {
		ArrayList<Integer> ids = new ArrayList<>();
		String sql = "SELECT ID_barrio FROM historial WHERE ID_persona = ?;";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setInt(1, id_Persona);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				ids.add(rs.getInt("ID_Barrio"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ids;
	}
	/**
	 * Este método es la segunda parte del conseguirIDBarrioEnHistorial, retorna el arraylist de las fechas que estan en la tabla
	 * historial
	 */
	public ArrayList<LocalDate> conseguirFechaEnHistorial(int id_Persona) {
		ArrayList<LocalDate> fechas = new ArrayList<>();
		
		String sql = "SELECT Fecha_traslado FROM historial WHERE ID_persona = ?;";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setInt(1, id_Persona);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				fechas.add(rs.getDate("Fecha_traslado").toLocalDate());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return fechas;
	}
	

	public ArrayList<Barrio> asociarBarriosConPersonas() {

	    ArrayList<Barrio> barrios = conexionBarrio.conseguirTodosLosBarrios();

	    ArrayList<Persona> personas = conseguirTodasLasPersonas();

	    String dni;
	    String nombreBarrio;

	    String sql = "SELECT p.DNI, b.nombre "
	            + "FROM persona p "
	            + "INNER JOIN barrio b "
	            + "ON p.Barrio_ID_barrio = b.ID_barrio;";

	    try (Connection conexion = conex.conectar()) {

	        PreparedStatement stmt = conexion.prepareStatement(sql);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {

	            dni = rs.getString("DNI");
	            nombreBarrio = rs.getString("nombre");

	            for (Barrio b : barrios) {

	                if (b.getNombre().equals(nombreBarrio)) {

	                    for (Persona p : personas) {

	                        if (p.getDNI().equals(dni)) {

	                            b.getPersonas().add(p);
	                            break;
	                        }
	                    }

	                    break;
	                }
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return barrios;
	}
	
	public ArrayList<Persona> conseguirPersonasAsociadasABarrio(String nombreBarrio){
		ArrayList<Persona> personas = new ArrayList<>();

		String dni;
		String nombre;
		String apellido;
		Estado situacionLaboral;
		LocalDate fechaNacimiento;
		String nombreDelEstado;

		String sql = "SELECT p.DNI, p.Nombre, p.Apellido, p.Fecha_nacimiento, sl.estado "
				+ "FROM persona p "
				+ "INNER JOIN situacion_laboral sl "
				+ "ON p.Situacion_laboral_ID_Situacion_laboral = sl.ID_Situacion_laboral "
				+ "INNER JOIN barrio b "
				+ "ON p.Barrio_ID_barrio = b.ID_barrio "
				+ "WHERE b.nombre = ?";
	
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, nombreBarrio);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				
				dni = rs.getString("DNI");
				
				nombre = rs.getString("nombre");
				
				apellido = rs.getString("Apellido");
				
				nombreDelEstado = rs.getString("estado");
				
				switch(nombreDelEstado) {
				
				case "Ocupado":
					situacionLaboral = Estado.OCUPADO;
					break;
					
				case "Desocupado":
					situacionLaboral = Estado.DESOCUPADO;
					break;
					
				case "Subocupado":
					situacionLaboral = Estado.SUBOCUPADO;
					break;
					
					default:
						situacionLaboral = null;
					break;
				}
				
				fechaNacimiento = rs.getDate("Fecha_nacimiento").toLocalDate();
				
				
				Persona p = new Persona(dni,nombre,apellido,situacionLaboral,fechaNacimiento);
				
				personas.add(p);
			}
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		return personas;
	}
	
	public Barrio conseguirBarrioConPersonas(String nombreBarrio) {

	    Barrio barrio = conexionBarrio.buscarBarrioPorNombre(nombreBarrio);

	    barrio.setPersonas(conseguirPersonasAsociadasABarrio(nombreBarrio));


	    return barrio;
	}
}
