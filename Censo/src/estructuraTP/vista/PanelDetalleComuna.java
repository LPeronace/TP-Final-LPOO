package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.Conexion;
import estructuraTP.modelo.Comuna;

public class PanelDetalleComuna extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	
	private JScrollPane scrollPane;
	
	private JTable tabla;
	
	private DefaultTableModel modelo;
	
	private Object[] columnas;
	
	private Conexion c = new Conexion();
	/**
	 * Create the panel.
	 */
	public PanelDetalleComuna(JFrame marco) {
		this.marco = marco;
		setBounds(0,0,750,500);
		
		crearTablaCompleta();
		
	}

	public void crearTablaCompleta() {
		tabla =  new JTable();
		tabla.setBounds(57, 122, 300, 112);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 338, 123);
		add(scrollPane);
		
		//Creo las columnas
		columnas = new Object[] {"Nombre" , "Tipo "};
		
		//creo el modelo y con cuantas filas ya hechas de por si
		modelo = new DefaultTableModel(columnas, 0);

		//pongo el modelo y el scrollpane
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
		
		//cargo todas las comunas que encontre en la base de datos
		actualizarTabla();	
	}
	
	public void actualizarTabla() {
		modelo.setRowCount(0); 
		for() {
			
			modelo.addRow(new Object[] {});
		
		}
	}
	
}
