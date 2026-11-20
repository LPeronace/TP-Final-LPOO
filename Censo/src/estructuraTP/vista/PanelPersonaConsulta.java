package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.Sentencia;
import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.Estado;
import estructuraTP.modelo.Meses;
import estructuraTP.modelo.Persona;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelPersonaConsulta extends JPanel {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel modelo;
	
	private JFrame marco;
	
	private PanelMenuPersona menuPersona;
	private PanelHistorial historial;
	private PanelPersonaConsulta consulta = this;
	
	private JButton botonVolver;
	private JButton botonEliminar;
	private JButton botonMover;
	private JButton botonModificar;
	private JButton botonHistorial;
	
	private JLabel labelTitulo;
	private JLabel labelBuscar;
	
	private JScrollPane scrollPane;
	
	private JTable tabla;
	
	private Object[] columnas;
	
	private PanelAgregarPersona agregar;
	private JPanel panelActual;
	
	private JTextField textFieldBusqueda;
	
	private JComboBox comboBoxCambiarBarrio;
	
	
	private Meses mes;
	
	
	public PanelPersonaConsulta(JFrame marco, PanelMenuPersona menuPersona) {
		this.marco = marco;
		this.menuPersona = menuPersona;
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearPaneles();
		crearLabels();
		crearBotones();
		crearTextField();
		eventos();
		crearComboBox();
		crearTablaCompleta();
	}
	
	public void crearPaneles() {
		panelActual = this;
	}
	
	public void crearTextField() {
		textFieldBusqueda = new JTextField();
		textFieldBusqueda.setBounds(499, 417, 217, 20);
		add(textFieldBusqueda);
		textFieldBusqueda.setColumns(10);
	}
	
	public void crearLabels() {
		labelTitulo = new JLabel("Consulta de Personas");
		labelTitulo.setBounds(283, 11, 211, 14);
		add(labelTitulo);
		
		labelBuscar = new JLabel("Buscar por aproximacion:");
		labelBuscar.setBounds(347, 420, 161, 14);
		add(labelBuscar);
	}
	
	public void crearBotones() {
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(10, 433, 125, 23);
		add(botonVolver);
		
		botonHistorial = new JButton("Ver Historial");
		botonHistorial.setBounds(197, 222, 125, 23);
		add(botonHistorial);
		
		botonEliminar = new JButton("Eliminar");
		botonEliminar.setBounds(10, 222, 125, 23);
		add(botonEliminar);
		
		botonMover = new JButton("Cambiar de Barrio");
		botonMover.setBounds(374, 222, 161, 23);
		add(botonMover);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setBounds(10, 312, 125, 23);
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
		
		
		actualizarTabla();
		//cargo todas las comunas que encontre en la base de datos
	}
	
	
	public void crearComboBox() {
		comboBoxCambiarBarrio = new JComboBox();
		comboBoxCambiarBarrio.setBounds(545, 222, 193, 22);
		add(comboBoxCambiarBarrio);

		
		for(Barrio b: Sentencia.conexionBarrio.conseguirTodosLosBarrios()) {
			comboBoxCambiarBarrio.addItem(b.getNombre());
		}
		
	
	}
	
	public void eventos() {
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuPersona);
			}
		});
		
		botonEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int eleccion = tabla.getSelectedRow();
				
				if(eleccion != -1) {
					String dni = (String) modelo.getValueAt(eleccion, 0);
					if(Sentencia.conexionPersona.eliminarPersonaSeleccionada(dni)) {
						JOptionPane.showMessageDialog(panelActual, "Eliminado con exito!!");
					}
					else {
						JOptionPane.showMessageDialog(panelActual, "Salio un error :(((((");
					}
					actualizarTabla();
				}
				else {
					JOptionPane.showMessageDialog(panelActual, "No elegiste ninguna persona en la tabla :(");
				}

			}
		});
		
		botonMover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int eleccion = tabla.getSelectedRow();
				String nombreBarrio = (String) comboBoxCambiarBarrio.getSelectedItem();
				
				LocalDate fechaCambio = LocalDate.now();
				
				if(eleccion != -1) {
					String dni = (String) modelo.getValueAt(eleccion, 0);
					
					Sentencia.conexionPersona.añadirHistorial(dni,nombreBarrio,fechaCambio);
						
					JOptionPane.showMessageDialog(panelActual,"Se ha hecho el cambio con exito!");
					actualizarTabla();
					
				}
				else {
					JOptionPane.showMessageDialog(panelActual, "No elegiste ninguna persona en la tabla :(");
				}
				
			}
		});
		
		botonModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int eleccion = tabla.getSelectedRow();
				
				if(eleccion != -1) {
					String dniAntiguo = (String) modelo.getValueAt(eleccion, 0);
					agregar = new PanelAgregarPersona(marco,menuPersona,dniAntiguo);
					marco.setContentPane(agregar);
				}
				else {
					JOptionPane.showMessageDialog(panelActual, "No elegiste ninguna persona en la tabla :(");
				}

			}
		});
		
		botonHistorial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int eleccion = tabla.getSelectedRow();
				
				if(eleccion != -1) {
					String dni = (String) modelo.getValueAt(eleccion, 0);
					historial = new PanelHistorial(marco,consulta,dni);
					
					marco.setContentPane(historial);
				}
				else {
					JOptionPane.showMessageDialog(panelActual, "No elegiste ninguna persona en la tabla :(");
				}
				
			}
		});
		
		textFieldBusqueda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String textoParaBusqueda = textFieldBusqueda.getText();
				
				ArrayList<Persona> personas = Sentencia.conexionPersona.buscarPersonaAproximacion(textoParaBusqueda);
				
				actualizarTabla(personas);
			}
		});
	
	}
	
	public void actualizarTabla() {
		modelo.setRowCount(0);
		String dni;
		String nombre;
		String apellido;
		String nombreBarrioAsociado;
		Estado situacion;
		LocalDate fechaNacimiento;

		for(Persona p: Sentencia.conexionPersona.conseguirTodasLasPersonas()) {
			dni = p.getDNI();
			
			nombre = p.getNombre();
			
			apellido = p.getApellido();
			
			situacion = p.getSituacionLaboral();
			
			fechaNacimiento = p.getFechaNacimiento();
			
			nombreBarrioAsociado = Sentencia.conexionBarrio.conseguirNombreDelBarrioAsociado(dni);
			
			modelo.addRow(new Object[] {dni, nombre, apellido, nombreBarrioAsociado, situacion, fechaNacimiento});
		}
		
	}
	
	
	//Sobrecarga del método actualizarTabla ya que necesitabamos meterle un arraylist y para no tener q pasar por parametro
	//cada vez que la actualizabamos
	public void actualizarTabla(ArrayList<Persona> personas) {
		modelo.setRowCount(0);
		String dni;
		String nombre;
		String apellido;
		String nombreBarrioAsociado;
		Estado situacion;
		LocalDate fechaNacimiento;

		for(Persona p: personas) {
			dni = p.getDNI();
			
			nombre = p.getNombre();
			
			apellido = p.getApellido();
			
			situacion = p.getSituacionLaboral();
			
			fechaNacimiento = p.getFechaNacimiento();
			
			nombreBarrioAsociado = Sentencia.conexionBarrio.conseguirNombreDelBarrioAsociado(dni);
			
			modelo.addRow(new Object[] {dni, nombre, apellido, nombreBarrioAsociado, situacion, fechaNacimiento});
		}
		
	}
}