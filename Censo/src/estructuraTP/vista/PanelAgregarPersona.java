package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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

public class PanelAgregarPersona extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	private PanelMenuPersona menuPersona;
	
	private JLabel labelTitulo;
	
	private JButton botonVolver;
	
	private JComboBox comboBoxMes;
	
	private Meses m;
	
	private JSpinner spinnerDia;
	private JSpinner spinnerAño;
	
	public PanelAgregarPersona(JFrame marco, PanelMenuPersona menuPersona) {
		this.marco = marco;
		this.menuPersona = menuPersona;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearBotones();
		crearSpinner();
		crearComboBox();
		eventos();
		
	}
	
	public void crearLabels() {
		labelTitulo = new JLabel("Agregar Personas");
		labelTitulo.setBounds(314, 11, 151, 14);
		add(labelTitulo);
	}
	
	public void crearBotones() {
		botonVolver = new JButton("volvo");
		botonVolver.setBounds(22, 422, 89, 23);
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
		comboBoxMes.setBounds(294, 135, 74, 20);
		add(comboBoxMes);
		
		for(Meses m:m.values()) {
			comboBoxMes.addItem(m);
		}
		
		
	}
	
	public void crearSpinner() {
		spinnerDia = new JSpinner();
		spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinnerDia.setBounds(209, 135, 52, 20);
		add(spinnerDia);
		
		spinnerAño = new JSpinner();
		spinnerAño.setModel(new SpinnerNumberModel(1915, 1915, 2026, 1));
		spinnerAño.setBounds(401, 135, 58, 20);
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
