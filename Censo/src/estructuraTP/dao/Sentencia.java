package estructuraTP.dao;

public abstract class Sentencia {
	
	private static Conexion conex = new Conexion();
	
	public static final DaoComuna conexionComuna = new DaoComuna(conex);
	public static final DaoBarrio conexionBarrio = new DaoBarrio(conex,conexionComuna);
	public static final DaoPersona conexionPersona = new DaoPersona(conex,conexionBarrio);

}
