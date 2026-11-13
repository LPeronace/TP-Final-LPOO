package estructuraTP;

import javax.swing.JFrame;
import estructuraTP.vista.PanelMenuPrincipal;
public class App {

	
	public static void main(String[] args) {
		JFrame marco = new JFrame();
		
		
		marco.setVisible(true);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setBounds(0, 0, 750, 500);
		
		PanelMenuPrincipal PanelMenuPrincipal = new PanelMenuPrincipal(marco);
		
		marco.setContentPane(PanelMenuPrincipal);
		marco.validate();
		
	}

}
