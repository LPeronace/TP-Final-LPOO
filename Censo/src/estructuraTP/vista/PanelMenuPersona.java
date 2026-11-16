package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PanelMenuPersona extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame marco;
	private PanelMenuPrincipal menuPrincipal;

	public PanelMenuPersona(JFrame marco, PanelMenuPrincipal menuPrincipal) {
		this.marco = marco;
		this.menuPrincipal = menuPrincipal;
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Persona");
		lblNewLabel.setBounds(342, 23, 46, 14);
		add(lblNewLabel);
	}

}
