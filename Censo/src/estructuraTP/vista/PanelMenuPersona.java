package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PanelMenuPersona extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelMenuPersona() {
		setBounds(0,0,750,500);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Persona");
		lblNewLabel.setBounds(342, 23, 46, 14);
		add(lblNewLabel);
	}

}
