package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelDetalleBarrio extends JPanel {

	private static final long serialVersionUID = 1L;

	private JFrame marco;
	private PanelBarrioConsulta barrioConsulta;
	
	private JButton botonVolver;
	
	public PanelDetalleBarrio(JFrame marco, PanelBarrioConsulta barrioConsulta) {
		this.marco = marco;
		this.barrioConsulta = barrioConsulta;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearBotones();
		accionBotones();
	}
	
	public void crearBotones() {
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(25, 428, 89, 23);
		add(botonVolver);
		
	}
	
	public void accionBotones() {
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(barrioConsulta);
			}
		});
	}

}
