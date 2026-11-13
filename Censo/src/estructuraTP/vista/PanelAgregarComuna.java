package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import estructuraTP.dao.Conexion;
import estructuraTP.modelo.Comuna;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelAgregarComuna extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldNumeroSede;
	private JLabel labelTitulo;
	private JLabel labelNombre;
	private JLabel labelNumeroSede;
	private JButton botonCargar;
	private Conexion c = new Conexion();
	
	public PanelAgregarComuna() {
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearBoton();
		crearTextField();
		eventoBoton();
	}
	
	public void crearLabels() {

		labelTitulo = new JLabel("Menu de Cargar Comunas");
		labelTitulo.setBounds(292, 38, 216, 14);
		add(labelTitulo);
		
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(52, 82, 46, 14);
		add(labelNombre);
		
		labelNumeroSede = new JLabel("Numero de Sede");
		labelNumeroSede.setBounds(48, 255, 86, 14);
		add(labelNumeroSede);
	}
	
	public void crearTextField() {
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(178, 79, 86, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldNumeroSede = new JTextField();
		textFieldNumeroSede.setBounds(178, 252, 86, 20);
		add(textFieldNumeroSede);
		textFieldNumeroSede.setColumns(10);
	}
	public void crearBoton() {
		botonCargar = new JButton("Cargar");
		botonCargar.setBounds(321, 369, 89, 23);
		add(botonCargar);
	}
	public void eventoBoton() {
		botonCargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.cargarComuna(conseguirDatos());
			}
		});
	}
	//Constructor comuna: String nombre y String numeroSede
	public Comuna conseguirDatos() {
		Comuna c;
		
		String nombre = textFieldNombre.getText();
		
		String numeroSede = textFieldNombre.getText();
		
		c = new Comuna(nombre, numeroSede);
		
		return c;
	}
}
