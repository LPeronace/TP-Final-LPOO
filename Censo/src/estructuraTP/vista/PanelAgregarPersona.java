package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import estructuraTP.modelo.Estado;
import estructuraTP.modelo.Meses;
import estructuraTP.modelo.Persona;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JTextField;

public class PanelAgregarPersona extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	private PanelMenuPersona menuPersona;
	
	private JLabel labelTitulo;
	private JLabel labelNombre;
	private JLabel labelDNI;
	private JLabel labelApellido;
	private JLabel labelNacimiento;
	private JLabel labelSituacionLaboral;
	private JButton botonVolver;
	
	private JComboBox comboBoxMes;
	private JComboBox comboBoxEstado;
	private Estado e;
	private Meses m;
	
	private JSpinner spinnerDia;
	private JSpinner spinnerAño;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	
	public PanelAgregarPersona(JFrame marco, PanelMenuPersona menuPersona) {
		this.marco = marco;
		this.menuPersona = menuPersona;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearBotones();
		crearSpinner();
		crearComboBox();
		crearTextfields();
		eventos();
		
	}
	
	public void crearLabels() {
		labelTitulo = new JLabel("Agregar Personas");
		labelTitulo.setBounds(270, 11, 195, 14);
		add(labelTitulo);
		
		labelNombre = new JLabel("Ingrese Nombre");
		labelNombre.setBounds(22, 141, 115, 14);
		add(labelNombre);
		
		labelDNI = new JLabel("Ingrese DNI");
		labelDNI.setBounds(22, 81, 74, 14);
		add(labelDNI);
		
		labelApellido = new JLabel("Ingrese Apellido");
		labelApellido.setBounds(22, 198, 115, 14);
		add(labelApellido);
		
		labelNacimiento = new JLabel("Ingrese Fecha Nacimiento");
		labelNacimiento.setBounds(22, 260, 182, 42);
		add(labelNacimiento);
		
		labelSituacionLaboral = new JLabel("Situacion Laboral");
		labelSituacionLaboral.setBounds(43, 352, 138, 42);
		add(labelSituacionLaboral);
	}
	
	public void crearTextfields() {
		
		textField = new JTextField();
		textField.setBounds(22, 106, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(22, 166, 86, 20);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(22, 225, 86, 20);
		add(textField_2);
	}
	
	public void crearBotones() {
		botonVolver = new JButton("volvo");
		botonVolver.setBounds(649, 465, 89, 23);
		add(botonVolver);
	}
	
	public void eventos() {
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuPersona);
			}
		});
		
		comboBoxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarModeloSpinner();
			}
		});
	}
	public void crearComboBox() {
		comboBoxMes = new JComboBox();
		comboBoxMes.setBounds(86, 310, 74, 20);
		add(comboBoxMes);
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(43, 396, 126, 26);
		add(comboBoxEstado);
		
		
		
		
		for(Meses m:m.values()) {
			comboBoxMes.addItem(m);
		}
		for(Estado e:Estado.values()){
			comboBoxEstado.addItem(e);
		}
			
		
	}
	
	public void crearSpinner() {
		spinnerDia = new JSpinner();
		spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinnerDia.setBounds(22, 310, 52, 20);
		add(spinnerDia);
		
		spinnerAño = new JSpinner();
		spinnerAño.setModel(new SpinnerNumberModel(1915, 1915, 2026, 1));
		spinnerAño.setBounds(172, 310, 58, 20);
		add(spinnerAño);
		
	}
	
	public void actualizarModeloSpinner() {
		Meses mes = (Meses) comboBoxMes.getSelectedItem();//agarra directamente el item seleccionado, en este caso por ejemplo es
		//Meses.FEBRRERO
		spinnerDia.setModel(new SpinnerNumberModel(1, 1, mes.getDia(), 1));
		
	}
	
	public Persona cargar() {
		Persona p = null;
		
		int dia =(int) spinnerDia.getValue();
		int mes = (int) comboBoxMes.getSelectedIndex() + 1;
		int año = (int) spinnerAño.getValue();
		LocalDate fechaCompleta = LocalDate.of(año, mes, dia);
		
		return p;
	}
}
