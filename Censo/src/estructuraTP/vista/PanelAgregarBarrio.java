package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import estructuraTP.dao.Conexion;
import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.ComplejosHabitacionales;
import estructuraTP.modelo.Comuna;
import estructuraTP.modelo.TrazadoFormal;
import estructuraTP.modelo.UrbanizacionPlanificada;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

public class PanelAgregarBarrio extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	
	private PanelMenuBarrio menuBarrio;
	
	private JLabel labelTitulo;
	private JLabel labelNombre;
	private JLabel labelSuperficie;
	private JLabel labelTipo;
	private JLabel labelDatosEspecificos;
	
	private JButton botonCargar;
	
	private JTextField textFieldNombre;
	private JTextField textFieldSuperficie;
	
	private JComboBox comboBoxTipoBarrio;
	private JComboBox comboBoxComuna;
	private JLabel labelAsignarComuna;
	
	private Conexion c = new Conexion();
	private JLabel labelEspecifico1;
	private JLabel labelEspecifico2;

	private JSpinner spinnerPorcentajeAsfalto;
	private JSpinner spinnerCantEdificios;
	
	private JCheckBox checkBoxConsorcio;
	private JSpinner spinnerMonoBlocks;
	private JCheckBox checkBoxAgua;
	private JTextField textFieldLey;
	
	public PanelAgregarBarrio(JFrame marco, PanelMenuBarrio menuBarrio) {
		this.marco = marco;
		this.menuBarrio = menuBarrio;
		
		setBounds(0, 0, 750, 500);
		setLayout(null);
		
		crearLabels();
		crearBotones();
		crearComboBox();
		crearTextFields();
		eventos();
		
		crearDiseñoTrazadoFormal();
		crearDiseñoComplejos();
		crearDiseñoUrbanizacion();
		
		cambiarDiseño();
	}
	
	public void crearLabels() {
		labelTitulo = new JLabel("Menu Cargar Barrios");
		labelTitulo.setBounds(321, 23, 145, 14);
		add(labelTitulo);
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(60, 110, 83, 14);
		add(labelNombre);
		
		labelSuperficie = new JLabel("Superficie");
		labelSuperficie.setBounds(60, 186, 93, 14);
		add(labelSuperficie);
		
		labelTipo = new JLabel("Tipo de Barrio");
		labelTipo.setBounds(60, 252, 83, 14);
		add(labelTipo);
		
		labelDatosEspecificos = new JLabel("Datos Especificos");
		labelDatosEspecificos.setBounds(481, 73, 151, 14);
		add(labelDatosEspecificos);
	
		labelAsignarComuna = new JLabel("Asignar Comuna");
		labelAsignarComuna.setBounds(60, 317, 83, 14);
		add(labelAsignarComuna);
		
		labelEspecifico1 = new JLabel("Cantidad de Edificios");
		labelEspecifico1.setBounds(348, 110, 127, 14);
		add(labelEspecifico1);
		
		labelEspecifico2 = new JLabel("Porcentaje de Asfalto");
		labelEspecifico2.setBounds(348, 186, 181, 14);
		add(labelEspecifico2);
	}
	
	
	public void crearBotones() {
		botonCargar = new JButton("Cargar");
		botonCargar.setBounds(319, 368, 107, 23);
		add(botonCargar);
		
	}
	
	public void eventos() {
		comboBoxTipoBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarDiseño();

			}
		});
	}
	
	public void cambiarTextoSpinnerEspecifico(String nombre1,String nombre2) {
		labelEspecifico1.setText(nombre1);
		labelEspecifico2.setText(nombre2);
	}
	
	
	public void crearComboBox() {
		comboBoxTipoBarrio = new JComboBox();
		comboBoxTipoBarrio.setBounds(163, 248, 145, 22);
		add(comboBoxTipoBarrio);
		
		comboBoxComuna = new JComboBox();
		comboBoxComuna.setBounds(163, 313, 145, 22);
		add(comboBoxComuna);
		
		cargarComboBox();
	}
	
	public void crearTextFields() {
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(160, 107, 148, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldSuperficie = new JTextField();
		textFieldSuperficie.setBounds(160, 183, 148, 20);
		add(textFieldSuperficie);
		textFieldSuperficie.setColumns(10);
		
	}
	
	/**
	 * Crear diseño especial de los complejos habitacionales
	 * 
	 * 
	**/
	public void crearDiseñoComplejos() {
		cambiarTextoSpinnerEspecifico("Cantidad de monoblocks","Tiene consorcio?");
		
		spinnerMonoBlocks = new JSpinner();
		spinnerMonoBlocks.setModel(new SpinnerNumberModel(0, 0, 400, 1));
		spinnerMonoBlocks.setBounds(539, 107, 53, 20);
		add(spinnerMonoBlocks);
		
		
		checkBoxConsorcio = new JCheckBox("");
		checkBoxConsorcio.setBounds(562, 186, 97, 23);
		add(checkBoxConsorcio);
	}
	
	/**
	 * Crear diseño especial de las Urbanizaciones Planificadas
	 * 
	 * 
	**/
	public void crearDiseñoUrbanizacion() {
		cambiarTextoSpinnerEspecifico("Numero de ley","Tiene conexión de agua?");
		
		textFieldLey = new JTextField();
		textFieldLey.setBounds(491, 107, 116, 20);
		add(textFieldLey);
		textFieldLey.setColumns(10);
		
		checkBoxAgua = new JCheckBox("");
		checkBoxAgua.setBounds(555, 182, 97, 23);
		add(checkBoxAgua);
		
		
		
		
	}
	
	/**
	 * Hacer el diseño especifico a dinamico
	 * 
	 * 
	**/
	public void cambiarDiseño() {

	    String tipo = (String) comboBoxTipoBarrio.getSelectedItem();

	    // ocultar todos los componentes q sean especificos del tipo

	    spinnerCantEdificios.setVisible(false);
	    spinnerPorcentajeAsfalto.setVisible(false);

	    spinnerMonoBlocks.setVisible(false);
	    checkBoxConsorcio.setVisible(false);

	    textFieldLey.setVisible(false);
	    checkBoxAgua.setVisible(false);

	    // checkeo el tipo y muestro los correspondientes solamente
	    if (tipo.equals("Trazado Formal")) {
	    	cambiarTextoSpinnerEspecifico("Cantidad de Edificios","Porcentaje de Asfalto");

	        spinnerCantEdificios.setVisible(true);
	        spinnerPorcentajeAsfalto.setVisible(true);

	    } else if (tipo.equals("Complejos Habitacionales")) {
	    	cambiarTextoSpinnerEspecifico("Cantidad de monoblocks","Tiene consorcio?");

	        spinnerMonoBlocks.setVisible(true);
	        checkBoxConsorcio.setVisible(true);

	    } else if (tipo.equals("Urbanizacion Planificada")) {
	    	cambiarTextoSpinnerEspecifico("Numero de ley","Tiene conexión de agua?");

	        textFieldLey.setVisible(true);
	        checkBoxAgua.setVisible(true);
	    }

	    revalidate();
	    repaint();
	}
	
	/**
	 * Crear diseño especial del trazado formal
	 * 
	 * 
	**/
	public void crearDiseñoTrazadoFormal() {
		cambiarTextoSpinnerEspecifico("Cantidad de Edificios","Porcentaje de Asfalto");
		
		spinnerCantEdificios = new JSpinner();
		spinnerCantEdificios.setModel(new SpinnerNumberModel(0, 0, 400, 1));
		spinnerCantEdificios.setBounds(549, 107, 55, 20);
		add(spinnerCantEdificios);
		
		spinnerPorcentajeAsfalto = new JSpinner();
		spinnerPorcentajeAsfalto.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinnerPorcentajeAsfalto.setBounds(549, 183, 55, 20);
		add(spinnerPorcentajeAsfalto);
	}
	
	public void cargarComboBox() {
		comboBoxTipoBarrio.addItem("Trazado Formal");
		comboBoxTipoBarrio.addItem("Complejos Habitacionales");
		comboBoxTipoBarrio.addItem("Urbanizacion Planificada");
		
		for(Comuna comuna: c.conseguirTodasLasComunas()) {
			comboBoxComuna.addItem(comuna.getNombre());
		}
		
	}
	
	public Barrio cargarDatos() {
		Barrio b;
		
		String nombre;
		float Superficie;

		
		
		return b;
	}
}
