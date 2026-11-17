package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelAgregarPersona extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	private PanelMenuPersona menuPersona;
	
	private JLabel labelTitulo;
	
	private JButton botonVolver;
	
	public PanelAgregarPersona(JFrame marco, PanelMenuPersona menuPersona) {
		this.marco = marco;
		this.menuPersona = menuPersona;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearBotones();
		accionBotones();
		
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
	
	public void accionBotones() {
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(menuPersona);
			}
		});
	}
}
