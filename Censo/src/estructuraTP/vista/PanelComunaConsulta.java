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
	
	private JScrollPane scrollPane;
	
	private JTable tabla;
	
	private DefaultTableModel modelo;
	
	private JButton botonVolver;
	private JButton botonVerDetalle;
	private JButton botonEliminar;
	private JButton botonActualizar;
	private JButton botonModificar;
	
	private JFrame marco;
	private PanelMenuComuna menuComuna;
	private JPanel panelActual = this;
	private PanelDetalleComuna detalleComuna;
	
	private Comuna comuna = new Comuna();
	
	private Conexion c = new Conexion();
	
	private Object[] columnas;
	
	
	/*
	 *CRITERIOS
	 *	
	 *Como no dice el tema de busqueda
	 *
	*/
	public PanelComunaConsulta(JFrame marco, PanelMenuComuna menuComuna) {
		setBounds(0, 0, 750, 500);
		setLayout(null);
		
		this.marco = marco;
		this.menuComuna = menuComuna;
		
		crearLabels();
		crearTablaCompleta();
		crearBotones();
		accionBotones();
		
	}
	
	
	public void crearLabels() {
		labelTitulo = new JLabel("Consultas Comuna");
		labelTitulo.setBounds(300, 25, 102, 14);
		add(labelTitulo);
		
	
	}
	
	
	public void crearBotones() {
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(39, 431, 89, 23);
		add(botonVolver);
		
		botonVerDetalle = new JButton("Ver Detalle");
		botonVerDetalle.setBounds(508, 118, 139, 23);
		add(botonVerDetalle);
		
		botonEliminar = new JButton("Eliminar Seleccionado");
		botonEliminar.setBounds(437, 216, 155, 23);
		add(botonEliminar);
		
		botonActualizar = new JButton("Actualizar");
		botonActualizar.setBounds(208, 284, 112, 23);
		add(botonActualizar);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setBounds(39, 284, 89, 23);
		add(botonModificar);
		
		
	}
	
	public void accionBotones() {
		botonVerDetalle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int eleccion = tabla.getSelectedRow();
				
				String numeroSede = (String) modelo.getValueAt(eleccion, 1);
				
				if(eleccion != -1) {
					detalleComuna = new PanelDetalleComuna(marco,numeroSede);
					
					marco.setContentPane(detalleComuna);
				}
				else {
					JOptionPane.showMessageDialog(panelActual, "Hubo un error :( Selecciono mal en la tabla ");
				}
				
				
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
				int eleccionBorrado = tabla.getSelectedRow();
				
				String numeroSede = (String) modelo.getValueAt(eleccionBorrado, 1);

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
		
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuComuna);
			}
		});
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
