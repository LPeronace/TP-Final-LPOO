package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelAgregarBarrio extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	
	private PanelMenuBarrio menuBarrio;
	
	private JLabel labelTitulo;
	private JLabel labelNombre;
	private JLabel labelSuperficie;
	private JLabel labelTipo;
	private JLabel labelDatosEspecificos;
	private JLabel labelAsignarComuna;
	private JLabel labelEspecifico1;
	private JLabel labelEspecifico2;
	private JLabel labelNombreComuna;
	private JLabel labelTextoNombreComuna;
	
	private JButton botonCargar;
	private JButton botonVolver;
	
	private JTextField textFieldNombre;
	private JTextField textFieldLey;

	
	private JComboBox comboBoxTipoBarrio;
	private JComboBox comboBoxComuna;
	
	private Conexion c = new Conexion();

	private JPanel panelActual = this;
	
	private JSpinner spinnerPorcentajeAsfalto;
	private JSpinner spinnerCantEdificios;
	private JSpinner spinnerSuperficie;
	private JSpinner spinnerMonoBlocks;
	
	private JCheckBox checkBoxConsorcio;
	private JCheckBox checkBoxAgua;

	private String nombreBarrioAntiguo;
	private boolean modoModificar = false;
	
	public PanelAgregarBarrio(JFrame marco, PanelMenuBarrio menuBarrio, String nombreBarrioAntiguo) {
		this.marco = marco;
		this.menuBarrio = menuBarrio;
		this.nombreBarrioAntiguo = nombreBarrioAntiguo;
		modoModificar = true;
		
		setBounds(0, 0, 750, 500);
		setLayout(null);
	
		crearComboBox();
		crearLabels();
		crearBotones();
		crearSpinner();
		crearTextFields();
		eventos();
		
		crearDiseñoTrazadoFormal();
		crearDiseñoComplejos();
		crearDiseñoUrbanizacion();
		
		cambiarDiseño();
	}
	
	
	
	
	public PanelAgregarBarrio(JFrame marco, PanelMenuBarrio menuBarrio) {
		this.marco = marco;
		this.menuBarrio = menuBarrio;
		modoModificar = false;
		setBounds(0, 0, 750, 500);
		setLayout(null);
	
		crearComboBox();
		crearLabels();
		crearBotones();
		crearSpinner();
		crearTextFields();
		eventos();
		
		crearDiseñoTrazadoFormal();
		crearDiseñoComplejos();
		crearDiseñoUrbanizacion();
		
		cambiarDiseño();
	}
	public void crearSpinner() {
		spinnerSuperficie = new JSpinner();
		spinnerSuperficie.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(99903), Float.valueOf(1)));
		spinnerSuperficie.setBounds(119, 184, 107, 17);
		add(spinnerSuperficie);
	}
	
	public void crearLabels() {
		labelTitulo = new JLabel("Menu Cargar Barrios");
		labelTitulo.setBounds(321, 23, 145, 14);
		add(labelTitulo);
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(10, 110, 83, 14);
		add(labelNombre);
		
		labelSuperficie = new JLabel("Superficie");
		labelSuperficie.setBounds(10, 186, 93, 14);
		add(labelSuperficie);
		
		labelTipo = new JLabel("Tipo de Barrio");
		labelTipo.setBounds(10, 252, 83, 14);
		add(labelTipo);
		
		labelDatosEspecificos = new JLabel("Datos Especificos");
		labelDatosEspecificos.setBounds(481, 73, 151, 14);
		add(labelDatosEspecificos);
	
		labelAsignarComuna = new JLabel("Asignar Comuna");
		labelAsignarComuna.setBounds(10, 317, 83, 14);
		add(labelAsignarComuna);
		
		labelEspecifico1 = new JLabel("Cantidad de Edificios");
		labelEspecifico1.setBounds(348, 110, 127, 14);
		add(labelEspecifico1);
		
		labelEspecifico2 = new JLabel("Porcentaje de Asfalto");
		labelEspecifico2.setBounds(348, 186, 181, 14);
		add(labelEspecifico2);
		
		labelNombreComuna = new JLabel("Nombre Comuna");
		labelNombreComuna.setBounds(10, 356, 107, 14);
		add(labelNombreComuna);

		labelTextoNombreComuna = new JLabel(conseguirNombreComunaComboBox());
		labelTextoNombreComuna.setBounds(119, 356, 146, 14);
		add(labelTextoNombreComuna);
	}
	
	
	public void crearBotones() {
		botonCargar = new JButton("Cargar");
		botonCargar.setBounds(319, 368, 107, 23);
		add(botonCargar);
		
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(10, 425, 89, 23);
		add(botonVolver);
		
	}
	
	public void eventos() {
		comboBoxTipoBarrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarDiseño();
			}
		});
		
		botonCargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Barrio b = cargarBarrio();
				
				String numeroSedeComuna = (String) comboBoxComuna.getSelectedItem(); 
				
				
				if(modoModificar) {
					c.modificarBarrioElegido(b,nombreBarrioAntiguo,numeroSedeComuna);
				}
				else {
					c.cargarBarrio(b, numeroSedeComuna);
				}
				
				JOptionPane.showMessageDialog(panelActual, "Cargado con exito!!!");
			}
		});
		
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuBarrio);
			}
		});
		
		comboBoxComuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreComuna = conseguirNombreComunaComboBox();
				
				labelTextoNombreComuna.setText(nombreComuna);
			}
		});
	}
	
	public String conseguirNombreComunaComboBox() {
		String numeroSedeComuna = (String) comboBoxComuna.getSelectedItem(); 
		
		Comuna comunaEspecifica = c.buscarComunaEspecifica(numeroSedeComuna);
		
		String nombreComuna = comunaEspecifica.getNombre();
		
		
		return nombreComuna;
	}
	
	
	public void cambiarTextoSpinnerEspecifico(String nombre1,String nombre2) {
		labelEspecifico1.setText(nombre1);
		labelEspecifico2.setText(nombre2);
	}
	
	
	public void crearComboBox() {
		comboBoxTipoBarrio = new JComboBox();
		comboBoxTipoBarrio.setBounds(119, 248, 145, 22);
		add(comboBoxTipoBarrio);
		
		comboBoxComuna = new JComboBox();
		comboBoxComuna.setBounds(119, 313, 145, 22);
		add(comboBoxComuna);
		
		cargarComboBox();
	}
	
	public void crearTextFields() {
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(119, 107, 148, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
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
			comboBoxComuna.addItem(comuna.getNumeroSede());
		}
		
	}
	
	public Barrio cargarBarrio() {
		Barrio b = null;
		
		String nombre = textFieldNombre.getText();
		float superficie = (float) spinnerSuperficie.getValue();
		
		int cantMonoblocks;
		boolean tieneConsorcio;
		
		int cantEdificios;
		int porcentajeAsfalto;
		
		String numLey; 
		boolean conexionAgua;
		
		String tipo = (String) comboBoxTipoBarrio.getSelectedItem();
		
		switch(tipo) {
		case "Trazado Formal":
			
			cantEdificios = (int) spinnerCantEdificios.getValue();
			porcentajeAsfalto = (int) spinnerPorcentajeAsfalto.getValue();
			
			b = (Barrio) new TrazadoFormal(nombre,superficie,cantEdificios,porcentajeAsfalto);
			
			break;
		case "Complejos Habitacionales":
			
			cantMonoblocks = (int) spinnerMonoBlocks.getValue();
			tieneConsorcio = checkBoxConsorcio.isSelected();
			
			b = (Barrio) new ComplejosHabitacionales(nombre,superficie,cantMonoblocks,tieneConsorcio);
			break;
		case "Urbanizacion Planificada":
			
			numLey = textFieldLey.getText();
			conexionAgua = checkBoxAgua.isSelected();
			
			
			b = (Barrio) new UrbanizacionPlanificada(nombre,superficie,numLey,conexionAgua);
			break;
		}
		
		return b;
	}
}
