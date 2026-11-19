package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMenuPersona extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	private PanelMenuPrincipal menuPrincipal;
	
	private JLabel labelTitulo;
	
	private JButton botonAgregar;
	private JButton botonConsulta;
	private JButton botonVolver;

	private PanelPersonaConsulta consulta;
	private PanelAgregarPersona agregar;
	
	public PanelMenuPersona(JFrame marco, PanelMenuPrincipal menuPrincipal) {
		this.marco = marco;
		this.menuPrincipal = menuPrincipal;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearPaneles();
		crearBotones();
		accionBotones();
	}
	public void crearPaneles() {
		consulta = new PanelPersonaConsulta(marco,this);
		agregar = new PanelAgregarPersona(marco,this);
	}
	
	public void crearLabels() {
		labelTitulo = new JLabel("Persona");
		labelTitulo.setBounds(342, 23, 83, 14);
		add(labelTitulo);
	}
	
	public void crearBotones() {
		botonAgregar = new JButton("Agregar");
		botonAgregar.setBounds(315, 135, 89, 23);
		add(botonAgregar);
		
		botonConsulta = new JButton("Consulta");
		botonConsulta.setBounds(315, 287, 89, 23);
		add(botonConsulta);
		
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(29, 423, 89, 23);
		add(botonVolver);
	}
	
	public void accionBotones() {
		botonConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				consulta.actualizarTabla();
				marco.setContentPane(consulta);
			}
		});
		
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuPrincipal);
			}
		});
		
		botonAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(agregar);
			}
		});
	}
}
