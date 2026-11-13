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

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cl = new CardLayout(0,0);
	private JPanel PanelMenuPersona = new PanelMenuPersona();
	private JPanel PanelMenuBarrio = new PanelMenuBarrio();
	private JPanel PanelMenuComuna = new PanelMenuComuna();
	private JButton botonComuna;
	private JButton botonPersona;
	private JButton botonBarrio;
	private JLabel labelTitulo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		crearLabel();
		crearBotones();
		eventoBotones();
		getContentPane().setLayout(cl);

		getContentPane().add(PanelMenuPersona, "PanelMenuPersona");
		getContentPane().add(PanelMenuBarrio, "PanelMenuBarrio");
		getContentPane().add(PanelMenuComuna, "PanelMenuComuna");
		

	}
	

	public void crearLabel() {

		labelTitulo = new JLabel("Bienvenido Al MENU");
		labelTitulo.setBounds(294, 11, 95, 14);
		add(labelTitulo);
		
	}
	public void crearBotones() {

		botonComuna = new JButton("Comuna");
		botonComuna.setBounds(318, 263, 71, 23);
		add(botonComuna);
		
		botonBarrio = new JButton("Barrio");
		botonBarrio.setBounds(318, 195, 71, 23);
		add(botonBarrio);
		

		botonPersona = new JButton("Persona");
		botonPersona.setBounds(318, 122, 71, 23);
		add(botonPersona);
		
	}
	
	public void eventoBotones() {
		botonPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(getContentPane(),"PanelMenuPersona");
				
			}
		});
		
		botonBarrio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		botonComuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
}