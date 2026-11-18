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
		
		String sql = "SELECT p.DNI,p.Nombre,p.Apellido,p.Fecha_nacimiento,sl.estado "
		+ "FROM Persona p "
		+ "INNER JOIN situacion_laboral sl "
		+ " p.Situacion_laboral_ID_Situacion_laboral = sl.ID_Situacion_laboral";
		
		try(Connection c = conex.conectar()){
			PreparedStatement stmt = c.prepareStatement(sql);
			
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return personas;
	}
	
	public ArrayList<Persona> conseguirTodasLasPersonas(){
		ArrayList<Persona> personas = new ArrayList<>();
		
		String sql = "SELECT";
		
		return personas;
	}
	
}
