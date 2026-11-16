package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelBarrioConsulta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame marco;
	private PanelMenuBarrio menuBarrio;
	
	

	public PanelBarrioConsulta(JFrame marco, PanelMenuBarrio menuBarrio) {
		this.marco = marco;
		this.menuBarrio = menuBarrio;
		
		setBounds(0,0,750,500);
	}

}
