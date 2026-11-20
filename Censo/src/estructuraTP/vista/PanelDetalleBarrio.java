package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.Conexion;
import estructuraTP.dao.DaoBarrio;
import estructuraTP.dao.Sentencia;
import estructuraTP.modelo.Barrio;
import estructuraTP.modelo.ComplejosHabitacionales;
import estructuraTP.modelo.TrazadoFormal;
import estructuraTP.modelo.UrbanizacionPlanificada;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class PanelDetalleBarrio extends JPanel {

	private static final long serialVersionUID = 1L;

	private JFrame marco;
	private PanelBarrioConsulta barrioConsulta;
	
	private Sentencia sentencia;
	
	private JButton botonVolver;
	
	private JScrollPane scrollPane;
	
	private JTable tabla;
	
	private DefaultTableModel modelo;
	
	private Object[] columnas;
	
	private Barrio barrioSeleccionado;
	
	private JLabel lblCriticidad;
	private JLabel labelTieneCriticidad;
	private JLabel lblDensidadPoblacional;
	private JLabel labelNumeroDensidad;
	
	public PanelDetalleBarrio(JFrame marco, PanelBarrioConsulta barrioConsulta,Barrio barrioSeleccionado,Sentencia sentencia) {
		this.marco = marco;
		this.barrioConsulta = barrioConsulta;
		this.barrioSeleccionado = barrioSeleccionado;
		this.sentencia = sentencia;
		asociarPersonitas();
		
		setBounds(0,0,750,500);
		setLayout(null);
		
		
		crearBotones();
		crearLabels();
		accionBotones();
		rellenarLabel();
		crearTabla(barrioSeleccionado);
	}
	
	public void asociarPersonitas() {
		String nombre = barrioSeleccionado.getNombre();
		
		barrioSeleccionado.setPersonas(sentencia.conexionPersona.conseguirPersonasAsociadasABarrio(nombre));
	}
	
	public void crearLabels() {
		lblCriticidad = new JLabel("Tiene criticidad?");
		lblCriticidad.setBounds(25, 251, 138, 14);
		add(lblCriticidad);
		
		labelTieneCriticidad = new JLabel("");
		labelTieneCriticidad.setBounds(149, 251, 46, 14);
		add(labelTieneCriticidad);
		
		lblDensidadPoblacional = new JLabel("Densidad Poblacional:");
		lblDensidadPoblacional.setBounds(343, 251, 138, 14);
		add(lblDensidadPoblacional);
		
		labelNumeroDensidad = new JLabel("");
		labelNumeroDensidad.setBounds(491, 251, 46, 14);
		add(labelNumeroDensidad);
	}
	
	public void rellenarLabel() {
		float densidad = 0f;
		boolean criticidad = false;
		
		if(barrioSeleccionado instanceof TrazadoFormal) {
			TrazadoFormal bTF = (TrazadoFormal) barrioSeleccionado;
			
			densidad = bTF.calcularDensidad();
			criticidad = bTF.tieneCriticidad();
		}
		else if(barrioSeleccionado instanceof UrbanizacionPlanificada) {
			UrbanizacionPlanificada bUP = (UrbanizacionPlanificada) barrioSeleccionado;
			
			densidad = bUP.calcularDensidad();
			criticidad = bUP.tieneCriticidad();
			
		}
		else if(barrioSeleccionado instanceof ComplejosHabitacionales) {
			ComplejosHabitacionales bCH = (ComplejosHabitacionales) barrioSeleccionado;
			
			densidad = bCH.calcularDensidad();
			criticidad = bCH.tieneCriticidad();
		}
		

		labelNumeroDensidad.setText(String.valueOf(densidad));
		labelTieneCriticidad.setText(criticidad ? "Si" : "No");
		
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
	
	public void crearTabla(Barrio barrioSeleccionado) {
		setLayout(null);
		tabla =  new JTable();
		tabla.setBounds(57, 122, 300, 112);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 730, 123);
		add(scrollPane);
		
		//Creo las columnas

		String valorDinamico1 = "";
		String valorDinamico2 = "";
		
		
		
		if(barrioSeleccionado instanceof ComplejosHabitacionales) {
			valorDinamico1 = "Cantidad de monoblocks";
			valorDinamico2 = "Tiene consorcio";
		}
		else if(barrioSeleccionado instanceof TrazadoFormal) {
			valorDinamico1 = "Cantidad de Edificios";
			valorDinamico2 = "Porcentaje de Asfalto";
			
		}
		else {
			valorDinamico1 = "Numero de Ley";
			valorDinamico2 = "Tiene conexion de agua";
		}
		
		columnas = new Object[] {"Nombre" , "Superficie",valorDinamico1,valorDinamico2};
		
		//creo el modelo y con cuantas filas ya hechas de por si
		modelo = new DefaultTableModel(columnas, 0);
		
		//pongo el modelo y el scrollpane
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);

		//cargo todos los datos que encontre en la base de datos
		actualizarTabla(barrioSeleccionado.getNombre());
	}
	
	public void actualizarTabla(String nombreBarrioActualizar) {
		modelo.setRowCount(0); 
		String nombre = "";
		float superficie = 0f;
		
		int cantMonoblock;
	    boolean tieneConsorcio;
	    
	    int cantEdificios;
	    int porcentajeAsf;
	    
	    String numLey;
	    boolean conexionAgua;
	    
		
		for(Barrio b: sentencia.conexionBarrio.conseguirTodosLosBarrios()) {
			if(b.getNombre().equals(nombreBarrioActualizar)) {
				nombre = b.getNombre();
				superficie = b.getSuperficie();
				if(barrioSeleccionado instanceof ComplejosHabitacionales) {
					ComplejosHabitacionales bCH = (ComplejosHabitacionales) b;
					
					cantMonoblock = bCH.getCantMonoblocks();
					tieneConsorcio = bCH.getTieneConsorcio();
					
					modelo.addRow(new Object[] {nombre, superficie,cantMonoblock,tieneConsorcio});
				}
				else if(barrioSeleccionado instanceof TrazadoFormal) {
					TrazadoFormal bTF = (TrazadoFormal) b;
					
					cantEdificios = bTF.getCantEdificios();
					porcentajeAsf = bTF.getPorcentajeAsfalto();
					
					modelo.addRow(new Object[] {nombre, superficie,cantEdificios,porcentajeAsf});
				}
				else {
					UrbanizacionPlanificada bUP = (UrbanizacionPlanificada) b;
					
					numLey = bUP.getNumLey();
					conexionAgua = bUP.getConexionAgua();
					
					modelo.addRow(new Object[] {nombre, superficie,numLey,conexionAgua});
					
				}
			}
			
		}

		
	}

}
