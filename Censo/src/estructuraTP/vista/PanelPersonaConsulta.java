package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelPersonaConsulta extends JPanel {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel modelo;
	private JFrame marco;
	private PanelMenuPersona menuPersona;
	
	private JButton botonVolver;
	private JButton botonEliminar;
	private JButton botonMover;
	private JButton botonModificar;
	private JButton botonHistorial;
	
	private JScrollPane scrollPane;
	private JLabel labelTitulo;
	private JTable tabla;
	private Object[] columnas;
	
	public PanelPersonaConsulta(JFrame marco, PanelMenuPersona menuPersona) {
		this.marco = marco;
		this.menuPersona = menuPersona;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearBotones();
		accionBotones();
		crearTablaCompleta();
	}

	public void crearLabels() {
		labelTitulo = new JLabel("Consulta de Personas");
		labelTitulo.setBounds(283, 11, 211, 14);
		add(labelTitulo);
		
	}
	
	public void crearBotones() {
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(10, 433, 125, 23);
		add(botonVolver);
		
		botonHistorial = new JButton("Historial");
		botonHistorial.setBounds(613, 433, 125, 23);
		add(botonHistorial);
		
		botonEliminar = new JButton("Eliminar");
		botonEliminar.setBounds(133, 222, 125, 23);
		add(botonEliminar);
		
		botonMover = new JButton("Mover");
		botonMover.setBounds(293, 222, 125, 23);
		add(botonMover);
		
		botonModificar= new JButton("Modificar");
		botonModificar.setBounds(443, 222, 125, 23);
		add(botonModificar);
	}
	
	public void crearTablaCompleta() {
		tabla =  new JTable();
		tabla.setBounds(57, 122, 300, 112);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 728, 123);
		add(scrollPane);
		
		//Creo las columnas
		columnas = new Object[] {"DNI" , "Nombre", "Apellido", "barrio", "Siuacion", "FechaNacimiento"};
		
		//creo el modelo y con cuantas filas
		modelo = new DefaultTableModel(columnas, 0);

		//pongo el modelo y el scrollpane
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
		
		//cargo todas las comunas que encontre en la base de datos
	}
	
	public void accionBotones() {
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuPersona);
			}
		});
	}
}
