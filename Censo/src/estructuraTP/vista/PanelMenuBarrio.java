package estructuraTP.vista;

import javax.swing.JPanel;

import estructuraTP.dao.Sentencia;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/*
 *Como no hay algun atributo identificador de cada barrio y no se pueden usar IDs,
 *pusimos que el nombre sea único para poder identificarlos de esa manera.
 *
 *
*/
public class PanelMenuBarrio extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel labelTitulo;
	
	private JButton botonAgregar;
	private JButton botonConsultar;
	private JButton botonVolver;
	
	private JFrame marco;
	
	private Sentencia sentencia;
	
	private PanelAgregarBarrio agregar;
	private PanelBarrioConsulta consultar;
	private PanelMenuPrincipal menuPrincipal;
	
	public PanelMenuBarrio(JFrame marco,PanelMenuPrincipal menuPrincipal, Sentencia sentencia) {
		this.marco = marco;
		this.menuPrincipal = menuPrincipal;
		this.sentencia = sentencia;
		setBounds(0,0,750,500);
		
		crearLabels();
		crearPaneles();
		crearBotones();
		accionBotones();
	}
	
	public void crearPaneles() {
		agregar = new PanelAgregarBarrio(marco, this,sentencia);
		
		consultar = new PanelBarrioConsulta(marco,this,sentencia);
		
		
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
				consultar.actualizarTabla();
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
