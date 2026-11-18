package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import estructuraTP.dao.Conexion;
import estructuraTP.dao.DaoComuna;
import estructuraTP.dao.Sentencia;
import estructuraTP.modelo.Comuna;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelAgregarComuna extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldNumeroSede;
	
	private Sentencia sentencia = new Sentencia();

	
	private JLabel labelTitulo;
	private JLabel labelNombre;
	private JLabel labelNumeroSede;
	
	private JButton botonCargar;
	private JButton botonVolver;
	
	private PanelMenuComuna panelMenuComuna;
	private JPanel panelActual = this;
	private JFrame marco;
	
	private String numeroSedeAntiguo;
	private boolean modoModificar;

	
	public PanelAgregarComuna(JFrame marco, PanelMenuComuna panelMenuComuna,String numeroSedeAntiguo) {
		this.marco = marco;
		this.panelMenuComuna = panelMenuComuna;
		this.numeroSedeAntiguo = numeroSedeAntiguo;
		modoModificar = true;
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearBoton();
		crearTextField();
		eventoBoton();
		rellenarCampos();
	}
	
	public PanelAgregarComuna(JFrame marco, PanelMenuComuna panelMenuComuna) {
		this.marco = marco;
		this.panelMenuComuna = panelMenuComuna;
		modoModificar = false;
		setBounds(0,0,750,500);
		setLayout(null);
		
		crearLabels();
		crearBoton();
		crearTextField();
		eventoBoton();
	}
	
	public void crearLabels() {

		labelTitulo = new JLabel("Menu de Cargar Comunas");
		labelTitulo.setBounds(292, 38, 216, 14);
		add(labelTitulo);
		
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(206, 82, 46, 14);
		add(labelNombre);
		
		labelNumeroSede = new JLabel("Numero de Sede");
		labelNumeroSede.setBounds(474, 82, 86, 14);
		add(labelNumeroSede);
	}
	
	public void crearTextField() {
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(102, 156, 162, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldNumeroSede = new JTextField();
		textFieldNumeroSede.setBounds(474, 156, 141, 20);
		add(textFieldNumeroSede);
		textFieldNumeroSede.setColumns(10);
		
		
	}
	public void crearBoton() {
		botonCargar = new JButton("Cargar");
		botonCargar.setBounds(323, 228, 89, 23);
		add(botonCargar);
		
		botonVolver = new JButton("VOLVER");
		botonVolver.setBounds(53, 422, 89, 23);
		add(botonVolver);
		
	}
	
	public void eventoBoton() {
		botonCargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Comuna comuna = conseguirDatos();
				
				String numeroSede = comuna.getNumeroSede();
				
				if(!sentencia.conexionComuna.existeComunaConEsteNumero(numeroSede)) {
					if(modoModificar) {
						
						JOptionPane.showMessageDialog(panelActual, "Modificado con exito!!!");
						
						sentencia.conexionComuna.modificarComuna(comuna,numeroSedeAntiguo);
						marco.setContentPane(panelMenuComuna);
					}
					else {
						JOptionPane.showMessageDialog(panelActual, "Cargado con exito!!!");
						
						sentencia.conexionComuna.cargarComuna(comuna);
					}
				}
				else {
					JOptionPane.showMessageDialog(panelActual, "Ya existe un panel con ese número de sede, intentelo devuelta");
					textFieldNumeroSede.setText("");
				}
			}
		});
		
		botonVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				marco.setContentPane(panelMenuComuna);
			}
		});
	}
	
	public void rellenarCampos() {
		Comuna comuna = sentencia.conexionComuna.buscarComunaEspecifica(numeroSedeAntiguo);
		
		textFieldNombre.setText(comuna.getNombre());
		
		textFieldNumeroSede.setText(comuna.getNumeroSede());
	}
	
	
	
	//Constructor comuna: String nombre y String numeroSede
	public Comuna conseguirDatos() {
		Comuna c;
		
		String nombre = textFieldNombre.getText();
		
		String numeroSede = textFieldNumeroSede.getText();
		
		c = new Comuna(nombre, numeroSede);
		
		return c;
	}
}
