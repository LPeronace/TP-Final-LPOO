package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PanelMenuBarrio extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelMenuBarrio() {
		setBounds(0,0,750,500);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Barrio");
		lblNewLabel.setBounds(322, 11, 46, 14);
		add(lblNewLabel);
		
	}

}
