package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.modelo.Comuna;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class PanelComunaConsulta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelTitulo;
	private JLabel lblBusqueda;
	private JLabel lblComboBoxBarrio;
	
	private JScrollPane scrollPane;
	
	private JTable tabla;
	
	private DefaultTableModel modelo;
	
	private JButton botonVolver;
	private JButton botonAsignar;
	private JButton botonVerDetalle;
	private JButton botonEliminar;
	
	
	private Comuna c = new Comuna();
	
	private JTextField textFieldBusqueda;
	
	private JComboBox comboBoxBarrios;
	
	public PanelComunaConsulta() {
		setBounds(0, 0, 750, 500);
		setLayout(null);
		
		crearLabels();
		crearTablaCompleta();
		crearBotones();
		crearTextFields();
		crearComboBox();
		accionBotones();
		
	}
	
	public void crearLabels() {
		labelTitulo = new JLabel("Consultas Comuna");
		labelTitulo.setBounds(300, 25, 102, 14);
		add(labelTitulo);
		
		
		lblBusqueda = new JLabel("Busqueda por nombre:");
		lblBusqueda.setBounds(396, 65, 139, 33);
		add(lblBusqueda);
		
		lblComboBoxBarrio= new JLabel("Barrios para Reasignar");
		lblComboBoxBarrio.setBounds(29, 242, 139, 33);
		add(lblComboBoxBarrio);
		
	
	}
	public void crearComboBox() {
		comboBoxBarrios = new JComboBox();
		comboBoxBarrios.setBounds(152, 247, 89, 23);
		add(comboBoxBarrios);
		
		comboBoxBarrios.
		
	}
	
	public void crearBotones() {
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(39, 431, 89, 23);
		add(botonVolver);
		
		botonVerDetalle = new JButton("Ver Detalle");
		botonVerDetalle.setBounds(359, 247, 112, 23);
		add(botonVerDetalle);
		
		botonAsignar = new JButton("Reasignar");
		botonAsignar.setBounds(39, 286, 89, 23);
		add(botonAsignar);
		
		botonEliminar = new JButton("Eliminar Seleccionado");
		botonEliminar.setBounds(358, 164, 133, 23);
		add(botonEliminar);
	}
	
	public void accionBotones() {
		
	}
	
	public void crearTextFields() {
		textFieldBusqueda = new JTextField();
		textFieldBusqueda.setBounds(545, 71, 86, 20);
		add(textFieldBusqueda);
		textFieldBusqueda.setColumns(10);
		
	}
	
	public void crearTablaCompleta() {
		tabla =  new JTable();
		tabla.setBounds(57, 122, 300, 112);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 64, 309, 123);
		add(scrollPane);
		
		//Creo las columnas
		Object[] columnas = new Object[] {"Nombre" , "Numero", "Cantidad Barrios"};
		
		//creo el modelo y con cuantas filas
		modelo = new DefaultTableModel(columnas, 0);

		//pongo el modelo y el scrollpane
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
		
		
		cargarTabla();
		
		/*Esto es para añadir filas como ejemplo
		for(int i = 0; i<15;i++) {
			modelo.addRow(new Object[] {"Raul", "12","23"});
		}Y comprobar que funcione el scrollpane
		*/
	}
	public void cargarTabla() {
		
	}
}
