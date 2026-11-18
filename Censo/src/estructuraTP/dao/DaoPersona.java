package estructuraTP.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import estructuraTP.modelo.Estado;
import estructuraTP.modelo.Persona;

public class DaoPersona {
	private Conexion conex;
	private DaoBarrio conexionBarrio;
	
	public DaoPersona(Conexion conex, DaoBarrio conexionBarrio) {
		this.conex = conex;
		this.conexionBarrio = conexionBarrio;
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
		
		Date fechaNacimiento = Date.valueOf(persona.getFechaNacimeinto());
		
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
		+"WHERE p.Nombre Like '?%';";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			stmt.setString(1, textoABuscar);
			
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
					
				case "Descoupado":
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
					
				case "Descoupado":
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
		String sql = "UPDATE Persona INNER JOIN situacion_laboral sl  ON p.Situacion_laboral_ID_Situacion_laboral = sl.ID_Situacion_laboral"
	    		+ "SET p.dni = ?,p.nombre = ?, p.Apellido = ?,p.Fecha_nacimiento = ?, p.Situacion_laboral_ID_Situacion_laboral = ?, p.Barrio_ID_barrio = ?"
	    		+ "WHERE dni = ?;";
		
		String nombre = persona.getNombre();
		String DNI = persona.getDNI();
		String apellido = persona.getApellido();
		
		Date fechaNacimiento = Date.valueOf(persona.getFechaNacimeinto());
		
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
			
			stmt.executeUpdate();	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
