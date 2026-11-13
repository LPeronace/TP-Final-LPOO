package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMenuComuna extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JFrame marco;
	private JLabel titulo;
	private JButton botonCargar;
	private JButton botonConsultar;
	private JButton botonVolver;
	private PanelAgregarComuna PanelAgregar;
	private PanelComunaConsulta PanelConsultar;
	private PanelMenuPrincipal PanelMenuPrincipal;
	
	public PanelMenuComuna(JFrame marco) {
		this.marco = marco;
		setLayout(null);
		setBounds(0,0,750,500);
		crearLabels();
		crearBotones();
		crearPaneles();
		eventoBotones();
	}
	
	public void crearLabels() {
		titulo = new JLabel("Comuna");
		titulo.setBounds(318, 28, 46, 14);
		add(titulo);
	}
	
	public void crearPaneles() {
		PanelAgregar = new PanelAgregarComuna();
		
		PanelConsultar = new PanelComunaConsulta();
		
		PanelMenuPrincipal = new PanelMenuPrincipal(marco);
	}
	
	public void crearBotones() {
		botonConsultar = new JButton("Consultar");
		botonConsultar.setBounds(297, 264, 89, 23);
		add(botonConsultar);
		
		
		botonCargar = new JButton("Cargar");
		botonCargar.setBounds(297, 140, 89, 23);
		add(botonCargar);
		
		botonVolver = new JButton("VOLVER");
		botonVolver.setBounds(60, 401, 133, 23);
		add(botonVolver);
	}
	
	public void eventoBotones() {
		botonCargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(PanelAgregar);
			}
		});
		
		botonConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(PanelConsultar);
			}
		});
	
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(PanelMenuPrincipal);
			}
		});
		
	}
}
