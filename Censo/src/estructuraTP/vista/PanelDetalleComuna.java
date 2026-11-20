package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.Conexion;
import estructuraTP.dao.Sentencia;
import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.ComplejosHabitacionales;
import estructuraTP.modelo.Comuna;
import estructuraTP.modelo.TrazadoFormal;
import estructuraTP.modelo.UrbanizacionPlanificada;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelDetalleComuna extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	
	private JScrollPane scrollPane;
	
	private JTable tabla;
	
	private DefaultTableModel modelo;
	
	private Object[] columnas;
	
	private String numeroSedeSeleccionada;
	
	private JButton botonVolver;
	
	private PanelComunaConsulta consulta;
	/**
	 * Create the panel.
	 */
	public PanelDetalleComuna(JFrame marco, String numeroSedeSeleccionada, PanelComunaConsulta consulta) {
		this.marco = marco;
		this.numeroSedeSeleccionada = numeroSedeSeleccionada;
		this.consulta = consulta;
		setBounds(0,0,750,500);
		
		crearTablaCompleta();
		crearBoton();
		accionBoton();
		actualizarTabla();
	}

	public void crearTablaCompleta() {
		setLayout(null);
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
	public void crearBoton() {
		botonVolver = new JButton("VOLVER");
		botonVolver.setBounds(10, 409, 89, 23);
		add(botonVolver);
	}
	
	public void accionBoton() {
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(consulta);
			}
		});
	}
	
	
	public void actualizarTabla() {
		modelo.setRowCount(0); 
		String nombre;
		for(Comuna com: Sentencia.conexionComuna.asociarComunasConBarrios()) {
			
			if(com.getNumeroSede().equals(numeroSedeSeleccionada)) {
				
				for(Barrio b : com.getBarrios()) {
					
					nombre = b.getNombre();
					
					if(b instanceof TrazadoFormal) {
						modelo.addRow(new Object[] {nombre, "Trazado Formal"});
					}
					if(b instanceof UrbanizacionPlanificada) {
						modelo.addRow(new Object[] {nombre, "Urbanizacion Planificada"});
					}
					if(b instanceof ComplejosHabitacionales){
						modelo.addRow(new Object[] {nombre, "Complejos Habitacionales"});
					}
					
				}
				break;
			}
			
		}
		
	}
}
	

