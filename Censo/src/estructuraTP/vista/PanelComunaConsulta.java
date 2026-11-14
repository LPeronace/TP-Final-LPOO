package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.Conexion;
import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.Comuna;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JButton botonActualizar;
	
	private JFrame marco;
	private PanelMenuComuna menuComuna;
	private JPanel panelActual = this;
	private PanelDetalleComuna detalleComuna;
	
	private Comuna comuna = new Comuna();
	
	private Conexion c = new Conexion();
	
	private JTextField textFieldBusqueda;
	
	private JComboBox comboBoxBarrios;
	
	private Object[] columnas;
	private JButton botonModificar;
	
	
	public PanelComunaConsulta(JFrame marco, PanelMenuComuna menuComuna) {
		setBounds(0, 0, 750, 500);
		setLayout(null);
		
		this.marco = marco;
		this.menuComuna = menuComuna;
		
		crearLabels();
		crearPaneles();
		crearTablaCompleta();
		crearBotones();
		crearTextFields();
		crearComboBox();
		accionBotones();
		
	}
	
	public void crearPaneles() {
		detalleComuna = new PanelDetalleComuna(marco);
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
		
		botonModificar = new JButton("Modificar");
		botonModificar.setBounds(523, 247, 89, 23);
		add(botonModificar);
		
		
		for(Barrio b: comuna.getBarrios()) {
			comboBoxBarrios.addItem(b);
		}
		
	}
	
	public void crearBotones() {
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(39, 431, 89, 23);
		add(botonVolver);
		
		botonVerDetalle = new JButton("Ver Detalle");
		botonVerDetalle.setBounds(359, 247, 139, 23);
		add(botonVerDetalle);
		
		botonAsignar = new JButton("Reasignar");
		botonAsignar.setBounds(39, 286, 89, 23);
		add(botonAsignar);
		
		botonEliminar = new JButton("Eliminar Seleccionado");
		botonEliminar.setBounds(358, 164, 155, 23);
		add(botonEliminar);
		
		botonActualizar = new JButton("Actualizar");
		botonActualizar.setBounds(542, 164, 112, 23);
		add(botonActualizar);
		
	}
	
	public void accionBotones() {
		botonVerDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(detalleComuna);
				//averiguar como pasarle que seleccion hizo en la tabla a la otra clase
			}
		});
		
		botonActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		
		botonEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
									//consigue el indice de la fila actual pero arranca por 0
				int eleccionBorrado = tabla.getSelectedRow();
				
				String numeroSede = (String) modelo.getValueAt(eleccionBorrado, 1);
				
				
				System.out.println(eleccionBorrado);
				System.out.println(numeroSede);
				//Esto porque retorna -1 si no selecciono nada o no hay algo q nosotros carguemos como seleccionado//
				if(eleccionBorrado != -1) {
					//devuelve booleano si se pudo borrar
					if(c.eliminarComunaSeleccionada(numeroSede)) {
						JOptionPane.showMessageDialog(panelActual, "Personaje eliminado con exito!!!!!");
					}
					else {
						JOptionPane.showMessageDialog(panelActual, "El panel cuenta con barrios asignados, primero modifique eso");
					}
				}
				
				else {
					JOptionPane.showMessageDialog(panelActual, "Hubo un error :( la comuna seleccionada no existe o no selecciono ninguna");
				}
			}
		});
		
		botonAsignar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int eleccionComboBox = comboBoxBarrios.getSelectedIndex() + 1;
				//barrio seleccionado
				
				int eleccionTabla = tabla.getSelectedRow() + 1;
				//comuna seleccionada
				
				if(eleccionComboBox != 1 && eleccionTabla != 1) {
					//aca sería bajarle la cantidad de barrios de la comuna anterior por -1 y subirle 1 a la comuna seleccionada
					//mas asociarla al arraylist nuevo y sacarlo del anterior
				}
				else {
					JOptionPane.showMessageDialog(panelActual, "Hubo un error :( la comuna o el barrio no se seleccionaron correctamente!");
				}
				
			}
		});
		
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuComuna);
			}
		});
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
		scrollPane.setBounds(10, 64, 338, 123);
		add(scrollPane);
		
		//Creo las columnas
		columnas = new Object[] {"Nombre" , "Numero", "Cantidad Barrios"};
		
		//creo el modelo y con cuantas filas
		modelo = new DefaultTableModel(columnas, 0);

		//pongo el modelo y el scrollpane
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
		
		//cargo todas las comunas que encontre en la base de datos
		actualizarTabla();	
	}
	public void actualizarTabla() {
		modelo.setRowCount(0); 
		for(Comuna com: c.asociarComunasConBarrios()) {
			
			modelo.addRow(new Object[] {com.getNombre(),com.getNumeroSede(), com.getCantidadBarrio()});
		
		}
	}
}
