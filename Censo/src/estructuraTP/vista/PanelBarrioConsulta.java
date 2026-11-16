package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.Conexion;
import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.ComplejosHabitacionales;
import estructuraTP.modelo.Comuna;
import estructuraTP.modelo.TrazadoFormal;
import estructuraTP.modelo.UrbanizacionPlanificada;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelBarrioConsulta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame marco;
	private PanelMenuBarrio menuBarrio;
	private PanelDetalleBarrio detalleBarrio;
	
	private JButton botonVolver;
	
	private JScrollPane scrollPane;
	
	private JTable tabla;
	
	private DefaultTableModel modelo;
	
	private Object[] columnas;
	
	private Conexion c = new Conexion();
	
	private JButton botonEliminar;
	private JButton botonModificar;
	private JButton botonVerDetalle;
	private JButton botonActualizar;

	public PanelBarrioConsulta(JFrame marco, PanelMenuBarrio menuBarrio) {
		this.marco = marco;
		this.menuBarrio = menuBarrio;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearBotones();
		accionBotones();
		crearTabla();
		crearPaneles();
	
	}

	public void crearPaneles() {
		detalleBarrio = new PanelDetalleBarrio(marco,this);
	}
	
	public void crearBotones() {
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(27, 426, 89, 23);
		add(botonVolver);
		
		botonEliminar = new JButton("Eliminar");
		botonEliminar.setBounds(27, 244, 119, 23);
		add(botonEliminar);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setBounds(193, 244, 128, 23);
		add(botonModificar);
		
		botonVerDetalle = new JButton("Ver Detalle");
		botonVerDetalle.setBounds(385, 88, 119, 23);
		add(botonVerDetalle);
		
		botonActualizar = new JButton("Actualizar");
		botonActualizar.setBounds(385, 244, 119, 23);
		add(botonActualizar);
	}
	
	public void accionBotones() {
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuBarrio);
			}
		});
		
		botonModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		botonVerDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(detalleBarrio);
			}
		});
		
		botonEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		botonActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizarTabla();
			}
		});
	}
	
	public void crearTabla() {
		setLayout(null);
		tabla =  new JTable();
		tabla.setBounds(57, 122, 300, 112);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 338, 123);
		add(scrollPane);
		
		//Creo las columnas
		columnas = new Object[] {"Nombre" , "Superficie"};
		
		//creo el modelo y con cuantas filas ya hechas de por si
		modelo = new DefaultTableModel(columnas, 0);

		//pongo el modelo y el scrollpane
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
		
		//cargo todos los datos que encontre en la base de datos
		actualizarTabla();
	}
	
	public void actualizarTabla() {
		modelo.setRowCount(0); 
		String nombre;
		float superficie;
		
		for(Barrio b: c.conseguirTodosLosBarrios()) {
			nombre = b.getNombre();
			superficie = b.getSuperficie();
			
			modelo.addRow(new Object[] {nombre, superficie});
			
		}
		
	}
}
