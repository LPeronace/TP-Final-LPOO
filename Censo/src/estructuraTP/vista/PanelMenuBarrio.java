package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMenuBarrio extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel labelTitulo;
	
	private JButton botonAgregar;
	private JButton botonConsultar;
	private JButton botonVolver;
	
	private JFrame marco;
	
	
	private PanelAgregarBarrio agregar;
	private PanelBarrioConsulta consultar;
	private PanelMenuPrincipal menuPrincipal;
	
	public PanelMenuBarrio(JFrame marco,PanelMenuPrincipal menuPrincipal) {
		this.marco = marco;
		this.menuPrincipal = menuPrincipal;
		
		setBounds(0,0,750,500);
		
		crearLabels();
		crearPaneles();
		crearBotones();
		accionBotones();
	}
	
	public void crearPaneles() {
		agregar = new PanelAgregarBarrio(marco, this);
		
		consultar = new PanelBarrioConsulta(marco,this);
		
		
	}
	
	public void crearLabels() {
		setLayout(null);
		labelTitulo = new JLabel("Barrio");
		labelTitulo.setBounds(325, 11, 102, 56);
		add(labelTitulo);
		
		
	}
	
	public void crearBotones() {

		botonAgregar = new JButton("Agregar");
		botonAgregar.setBounds(289, 162, 89, 23);
		add(botonAgregar);
		
		botonConsultar = new JButton("Consultar");
		botonConsultar.setBounds(289, 311, 89, 23);
		add(botonConsultar);
		
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(24,422,89,23);
		add(botonVolver);
		
	}
	
	public void accionBotones() {
		botonAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(agregar);
			}
		});
		
		botonConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(consultar);
			}
		});
		
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuPrincipal);
				
			}
		});
	}
}
