package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import estructuraTP.dao.Sentencia;
import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.Estado;
import estructuraTP.modelo.Meses;
import estructuraTP.modelo.Persona;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JPanel panelActual;
	
	private JLabel labelTitulo;
	private JLabel labelNombre;
	private JLabel labelDNI;
	private JLabel labelApellido;
	private JLabel labelNacimiento;
	private JLabel labelSituacionLaboral;
	private JLabel labelAsociarBarrio;
	
	private Sentencia sentencia = new Sentencia();
	
	private JButton botonVolver;
	private JButton botonCargar;
	
	private JComboBox comboBoxMes;
	private JComboBox comboBoxEstado;
	private JComboBox comboBoxBarrioAsociado;
	
	private Estado e;
	private Meses m;
	
	private JSpinner spinnerDia;
	private JSpinner spinnerAño;
	
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	
	private boolean modoModificar;
	
	public PanelAgregarPersona(JFrame marco, PanelMenuPersona menuPersona) {
		this.marco = marco;
		this.menuPersona = menuPersona;
		
		modoModificar = false;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearBotones();
		crearSpinner();
		crearComboBox();
		crearTextfields();
		eventos();
		
	}
	
	public void crearPaneles() {
		panelActual = this;
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
		labelSituacionLaboral.setBounds(22, 352, 138, 42);
		add(labelSituacionLaboral);
		
		labelAsociarBarrio = new JLabel("Barrio en el que reside");
		labelAsociarBarrio.setBounds(381, 81, 144, 14);
		add(labelAsociarBarrio);
	}
	
	public void crearTextfields() {
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(22, 106, 86, 20);
		add(textFieldDNI);
		textFieldDNI.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(22, 166, 86, 20);
		add(textFieldNombre);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(22, 225, 86, 20);
		add(textFieldApellido);

		
	}
	
	public void crearBotones() {
		botonVolver = new JButton("volvo");
		botonVolver.setBounds(612, 398, 89, 23);
		add(botonVolver);
		
		botonCargar = new JButton("Cargar");
		botonCargar.setBounds(312, 398, 115, 23);
		add(botonCargar);
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
		
		botonCargar.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				Persona personaCargada = cargar();
				
				String nombreBarrioAsociado = (String) comboBoxBarrioAsociado.getSelectedItem();
				
				String dniParaVerSiExiste = personaCargada.getDNI();
				
				String dniAntiguo = "";
				
				if(!sentencia.conexionPersona.verSiExisteDni(dniParaVerSiExiste) && !personaCargada.getDNI().equals(dniAntiguo) ) {
					if(modoModificar) {
						
						sentencia.conexionPersona.modificarPersona(personaCargada,nombreBarrioAsociado,dniAntiguo);
						
						
						marco.setContentPane(menuPersona);
						
					}else {
						JOptionPane.showMessageDialog(panelActual,"Cargado/a con exito!");
						sentencia.conexionPersona.crearPersona(personaCargada, nombreBarrioAsociado);
						reiniciarCarga();
				}
					
					
				}else {
					JOptionPane.showMessageDialog(panelActual, "Ese dni ya esta cargado, intentelo devuelta con otro dni");
				}
				
			}
		});
	}
	
	public void reiniciarCarga() {
		textFieldDNI.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		
		comboBoxEstado.setSelectedIndex(0);
		
		
		spinnerDia.setValue(0);
		comboBoxMes.setSelectedIndex(0);
		spinnerAño.setValue(0);
		
		comboBoxBarrioAsociado.setSelectedIndex(0);
	}
	
	public void crearComboBox() {
		comboBoxMes = new JComboBox();
		comboBoxMes.setBounds(86, 310, 74, 20);
		add(comboBoxMes);
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(22, 396, 126, 26);
		add(comboBoxEstado);
		
		comboBoxBarrioAsociado = new JComboBox();
		comboBoxBarrioAsociado.setBounds(381, 105, 144, 22);
		add(comboBoxBarrioAsociado);
		
		
		for(Meses m:m.values()) {
			comboBoxMes.addItem(m);
		}
		for(Estado e:e.values()){
			comboBoxEstado.addItem(e);
		}
			
		for(Barrio b: sentencia.conexionBarrio.conseguirTodosLosBarrios()) {
			comboBoxBarrioAsociado.addItem(b.getNombre());
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
	
	
	
	//Constructor = String dni, String nombre,String apellido, Estado situacionLaboral, LocalDate fechaNacimiento
	public Persona cargar() {
		Persona p = null;
		
		String dni = textFieldDNI.getText();
		String nombre = textFieldNombre.getText();
		String apellido = textFieldApellido.getText();
		
		Estado situacionLaboral = (Estado) comboBoxEstado.getSelectedItem();
		
		
		int dia =(int) spinnerDia.getValue();
		int mes = (int) comboBoxMes.getSelectedIndex() + 1;
		int año = (int) spinnerAño.getValue();
		
		LocalDate fechaCompleta = LocalDate.of(año, mes, dia);
		
		
		p = new Persona(dni, nombre, apellido, situacionLaboral, fechaCompleta);
		
		return p;
	}
}