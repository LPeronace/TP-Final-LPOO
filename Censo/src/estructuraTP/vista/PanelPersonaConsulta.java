package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPersonaConsulta extends JPanel {

	private static final long serialVersionUID = 1L;

	private JFrame marco;
	private PanelMenuPersona menuPersona;
	
	private JButton botonVolver;
	
	private JLabel labelTitulo;
	
	public PanelPersonaConsulta(JFrame marco, PanelMenuPersona menuPersona) {
		this.marco = marco;
		this.menuPersona = menuPersona;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearBotones();
		accionBotones();
	}

	public void crearLabels() {
		labelTitulo = new JLabel("Consulta de Personas");
		labelTitulo.setBounds(283, 11, 211, 14);
		add(labelTitulo);
		
	}
	
	public void crearBotones() {
		botonVolver = new JButton("volkswagen");
		botonVolver.setBounds(30, 433, 125, 23);
		add(botonVolver);
	}
	
	public void accionBotones() {
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuPersona);
			}
		});
	}
}
