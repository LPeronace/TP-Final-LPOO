package estructuraTP.vista;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.Sentencia;
import estructuraTP.modelo.Estado;
import estructuraTP.modelo.Persona;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelHistorial extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	private PanelPersonaConsulta consulta;
	
	private String dni;
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private Object[] columnas;
	private DefaultTableModel modelo;
	
	private Sentencia sentencia;
	
	private JLabel labelTitulo;
	private JLabel labelDNI;
	
	private JButton botonVolver;
	
	
	public PanelHistorial(JFrame marco, PanelPersonaConsulta consulta,String dni,Sentencia sentencia) {
			this.marco = marco;
			this.consulta = consulta;
			this.sentencia = sentencia;
			this.dni = dni;
			
			setBounds(0,0,750,500);
			setLayout(null);
			
			crearTablaCompleta();
			crearLabel();
			crearBotones();
			accionBotones();
	}
	
	public void crearLabel() {
		labelTitulo = new JLabel("Historial");
		labelTitulo.setBounds(345, 11, 46, 14);
		add(labelTitulo);
		
		labelDNI = new JLabel("DNI:"+dni);
		labelDNI.setBounds(572, 80, 46, 14);
		add(labelDNI);
		
	}
	
	public void crearBotones() {
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(29, 415, 89, 23);
		add(botonVolver);
	}
	
	public void accionBotones() {
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(consulta);
			}
		});
	}
	
	public void crearTablaCompleta() {
		tabla =  new JTable();
		tabla.setBounds(57, 122, 300, 112);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 518, 123);
		add(scrollPane);
		
		//Creo las columnas
		columnas = new Object[] {"Nombre de Barrio", "Fecha"};
		
		//creo el modelo y con cuantas filas
		modelo = new DefaultTableModel(columnas, 0);

		//pongo el modelo y el scrollpane
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
	
		
		actualizarTabla();
		//cargo todas las comunas que encontre en la base de datos
	}
	
	public void actualizarTabla() {

	    modelo.setRowCount(0);

	    int idPersona = sentencia.conexionPersona.conseguirIdPersonaConDni(dni);

	    ArrayList<Integer> idsBarrios = sentencia.conexionPersona.conseguirIDBarrioEnHistorial(idPersona);

	    ArrayList<LocalDate> fechas = sentencia.conexionPersona.conseguirFechaEnHistorial(idPersona);

	    String nombreBarrio;
	    LocalDate fechaDeMovida;

	    for (int i = 0; i < idsBarrios.size(); i++) {

	        nombreBarrio = sentencia.conexionBarrio.conseguirNombreBarrio(idsBarrios.get(i));

	        fechaDeMovida = fechas.get(i);

	        modelo.addRow(new Object[] {nombreBarrio, fechaDeMovida});
	    }
	}

}
