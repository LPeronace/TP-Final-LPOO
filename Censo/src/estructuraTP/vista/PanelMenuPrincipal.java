package estructuraTP.vista;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructuraTP.dao.Sentencia;

public class PanelMenuPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton botonComuna;
	private JButton botonPersona;
	private JButton botonBarrio;
	
	private JLabel labelTitulo;
	
	private Sentencia sentencia;
	
	private JFrame marco;
	
	private JPanel PanelMenuPersona;
	private JPanel PanelMenuBarrio; 
	private JPanel PanelMenuComuna;
	private PanelMenuPrincipal panelActual = this;
	
	public PanelMenuPrincipal(JFrame marco) {
		this.marco = marco;
		setBounds(0, 0, 750, 500);
		setLayout(null);
		crearLabel();
		crearBotones();
		eventoBotones();
		
	}
	

	public void crearLabel() {
		labelTitulo = new JLabel("Bienvenido Al MENU");
		labelTitulo.setBounds(318, 24, 126, 14);
		add(labelTitulo);		
	}
	
	public void crearBotones() {

		botonComuna = new JButton("Comuna");
		botonComuna.setBounds(318, 263, 111, 23);
		add(botonComuna);
		
		botonBarrio = new JButton("Barrio");
		botonBarrio.setBounds(318, 195, 127, 23);
		add(botonBarrio);
		

		botonPersona = new JButton("Persona");
		botonPersona.setBounds(318, 122, 111, 23);
		add(botonPersona);
		
	}
	
	public void eventoBotones() {
		botonPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelMenuPersona = new PanelMenuPersona(marco,panelActual);
				marco.setContentPane(PanelMenuPersona);
			}
		});
		
		botonBarrio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelMenuBarrio = new PanelMenuBarrio(marco, panelActual);
				marco.setContentPane(PanelMenuBarrio);
			}
		});
		
		botonComuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelMenuComuna = new PanelMenuComuna(marco, panelActual);
				marco.setContentPane(PanelMenuComuna);
			}
		});
	}
}