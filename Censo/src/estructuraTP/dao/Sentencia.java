package estructuraTP.dao;

public class Sentencia {
	private Conexion c = new Conexion();
	public DaoComuna conexionComuna = new DaoComuna(c);
	public DaoBarrio conexionBarrio = new DaoBarrio(c);
	
	
	public Sentencia() {
		
	}
}
