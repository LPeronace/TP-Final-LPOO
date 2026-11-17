package estructuraTP.dao;

public class Sentencia {
	
	private Conexion conex = new Conexion();
	
	public DaoComuna conexionComuna = new DaoComuna(conex);
	public DaoBarrio conexionBarrio = new DaoBarrio(conex,conexionComuna);
	public DaoPersona conexionPersona = new DaoPersona(conex);

}
