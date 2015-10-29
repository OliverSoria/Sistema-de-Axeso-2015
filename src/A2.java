
/*

This file is part of "Sistema de Axeso 2015".

"Sistema de Axeso 2015" is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

"Sistema de Axeso 2015" is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
"Sistema de Axeso 2015"

*/

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
//import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;

public class A2 extends JFrame implements ActionListener {
		
	// Dimensiones para los 4 paneles
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int w1 = (screenSize.width - 700- 180) / 4;			// Medidas correctas
	//int w1 = (1366 - 700 - 180) / 4;				// Medidas provisionales e incorrectas 	(1/12)
	int x1 = (screenSize.height - 555 - 40) / 3;		// Medidas correctas
	//int x1 = (768 - 555 - 40) / 3;		// Medidas provisionales e incorrectas		(2/12)
	int x1_Baja = (screenSize.height - 620 - 40) / 3;		// Medidas correctas
	//int x1_Baja = (768 - 620 - 40) / 3;		// Medidas provisionales e incorrectas		(3/12)
	int y1 = (screenSize.width - 180) / 2;			// Medidas correctas
	//int y1 = (1366 - 180) / 2;		// Medidas provisionales e incorrectas		(4/12)
	int h2 = (screenSize.width - 350) / 2;
	//int h2 = (1366 - 350) / 2;
	int h1 = (screenSize.height - 310 - 80 - 40) / 3;
	//int h1 = (768 - 310 - 80 - 40) / 3;
	int g2 = h1 + h1 + 310;
	int w2 = w1 + w1 + w1 + 350 + 180;
	int x2 = x1 + x1 + 310;
	int x2_baja = x1_Baja + x1_Baja + 310; 
	int y2 = (screenSize.height - 40 - 80) / 2;
	//int y2 = (768 - 40 - 80) / 2;		// Medidas provisionales e incorrectas		(*/12)
		
	// Componentes para alta *Alumno*
	private JTextField tfA1;
	private JTextField tfA2;
	private JTextField tfA3;
	private JTextField tfA4;
	private JPasswordField tfA5;
	private JButton btnA1;
	private JButton btnA2;
	private JComboBox cbA1;
	private JComboBox cbA2;
	private JComboBox cbA3;
	private JFileChooser jfc; // Selector de Archivos
			
	// Componentes para alta *Padres*
	private JTextField tfB1;
	private JTextField tfB2;
	private JTextField tfB3;
	private JPasswordField tfB4;
	private JTextField tfB5;
	private JTextField tfB6;
	private JPasswordField tfB7;
	private JButton btnB2;
	
	// Componentes para alta *Profesores*
	private JTextField tfC1;
	private JTextField tfC2;
	private JTextField tfC3;
	private JPasswordField tfC4;
	private JComboBox cbC1;
	private JButton btnC2;
	
	// Componentes para alta *Administrador*
	private JTextField tfD1;
	private JTextField tfD2;
	private JTextField tfD3;
	private JPasswordField tfD4;
	private JButton btnD2;
	
	// Componentes para baja *Alumno*
	JPanel JP_bajaAlumno;
	private JComboBox cb_B1;
	private JComboBox cb_B2;
	private JButton bajaAlum;
	private JTable tb_BAlum = new JTable();
	private JLabel lbl_B1;
	private JLabel lbl_B2;
	private JLabel lbl_B3;
	private JLabel lbl_B4;
	boolean bAlumno = false;
	
	// Componenetes para baja *Tutor*
	JLabel lblBT;
	JPanel JP_bajaPadre;
	private JTextField tfBT;
	private JButton btnBT;
	TextPrompt tp7;
	boolean bPadre = false;
	
	// Componenetes para baja *Profesor*
	JLabel lblBP;
	JPanel JP_bajaProfe;
	private JTextField tfBP;
	private JButton btnBP;
	TextPrompt tp8;
	boolean bProfe = false;
	
	// Componenetes para baja *Administrador*
	JLabel lblBA;
	JPanel JP_bajaAdmin;
	private JTextField tfBA;
	private JButton btnBA;
	TextPrompt tp9;
	boolean bAdmin = false;
	
	// Botones de salida
	private JButton btn_Exit1;
	private JButton btn_Exit2;
	private JButton btn_Exit3;
	private JButton btn_Exit4;
	
	// Componentes para modificar *Alumno*
	JPanel JP_modAlumno;
	public static JComboBox cb_M1;
	public static JComboBox cb_M2;
	public static JButton modAlum;
	
	//private JTable tb_MAlum = new JTable();
	private JLabel lbl_M1;
	private JLabel lbl_M2;
	private JLabel lbl_M3;
	private JLabel lbl_M4;
	boolean mAlumno = false;
	
	// Componenetes para modificar *Tutor*
	JLabel lblMT;
	JPanel JP_modPadre;
	static JTextField tfMT;
	public static JButton btnMT;
	TextPrompt tp10;
	boolean mPadre = false;
	
	// Componenetes para modificar *Profesor*
	JLabel lblMP;
	JPanel JP_modProfe;
	static JTextField tfMP;
	static JButton btnMP;
	TextPrompt tp11;
	boolean mProfe = false;
	
	// Componenetes para modificar *Administrador*
	JLabel lblMA;
	JPanel JP_modAdmin;
	public static JTextField tfMA;
	public static JButton btnMA;
	TextPrompt tp12;
	boolean mAdmin = false;
	
	// Componentes para realizar Consulta
	JPanel jp_Consulta;
	private JComboBox cb_1C;
	private JComboBox cb_2C;
	private JButton bajaAlumC;
	private JTable tb_BAlumC = new JTable();
	private JLabel lbl_1C;
	private JLabel lbl_2C;
	private JLabel lbl_3C;
	boolean c_Alumno = false;
	
	
		
	public A2() {
		super("");
		
		// Cualidades de esta ventana como pantalla completa
		this.setTitle("Administrador");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setUndecorated(true);
		this.setVisible(true);
			
		setBounds(0,0,screenSize.width, screenSize.height); // Medidas originales y correctas (5/12)
		//setBounds(0,0,1366,768); // Medidas provicionales e incorrectas 
		getContentPane().setBackground(Color.white);
		setLayout(new BorderLayout());
		
		// Color para la pestana activa
		Color c = new Color(255, 232, 215, 255);
		UIManager.put("TabbedPane.selected",c);
		
		// Inicializacion y filtrado de archivos del Selector de Archivos
		jfc = new JFileChooser();
		jfc.setFileFilter(new JavaFileFilter());
		
		// Creacion del panel de pestanas 
		JTabbedPane tabs = new JTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		
		// Color de fondo del panel de pestanas
		tabs.setBackground(Color.WHITE);
				
		// Creacion de panel - Un panel por pestaña
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel jp5 = new JPanel();
				
		jp1.setLayout(null);
		//jp1.setOpaque(true);
		jp1.setBounds(0,40,screenSize.width, screenSize.height); // Medidas originales y correctas (6/12)
		//jp1.setBounds(0,40,1366,768);	// Medias provicionales e incorrectas
		
		jp2.setLayout(null);
		//jp2.setOpaque(true);
		jp2.setBounds(0,40,screenSize.width, screenSize.height); // Medidas originales y correctas (7/12)
		//jp2.setBounds(0,40,1366,768);	// Medias provicionales e incorrectas
		
		jp3.setLayout(null);
		//jp3.setOpaque(true);
		jp3.setBounds(0,40,screenSize.width, screenSize.height); // Medidas originales y correctas (7/12)
		//jp3.setBounds(0,40,1366,768);	// Medias provicionales e incorrectas
				
		jp4.setLayout(null);
		//jp4.setOpaque(true);
		jp4.setBounds(0,40,screenSize.width, screenSize.height); // Medidas originales y correctas (7/12)
		//jp4.setBounds(0,40,1366,768);	// Medias provicionales e incorrectas
		
		// Imagen de fondo pestaña 1
		final ImageIcon fondo = new ImageIcon(getClass().getResource("/street.jpeg"));
		JLabel jFondo = new JLabel();
		jFondo.setIcon(fondo);
		jFondo.setBounds(0,0,screenSize.width, screenSize.height); // Medidas originales y correctas (8/12)
		//jFondo.setBounds(0,0,1366, 768); // Medidas provicionales e incorrectas
		
		// Imagen de fondo pestaña 2
		final ImageIcon fondo2 = new ImageIcon(getClass().getResource("/street.jpeg"));
		JLabel jFondo2 = new JLabel();
		jFondo2.setIcon(fondo2);
		jFondo2.setBounds(0,0,screenSize.width, screenSize.height); // Medidas originales y correctas (9/12)
		//jFondo2.setBounds(0,0,1366, 768); // Medidas provicionales e incorrectas
		
		// Imagen de fondo pestaña 3
		final ImageIcon fondo3 = new ImageIcon(getClass().getResource("/street.jpeg"));
		JLabel jFondo3 = new JLabel();
		jFondo3.setIcon(fondo3);
		jFondo3.setBounds(0,0,screenSize.width, screenSize.height); // Medidas originales y correctas (10/12)
		//jFondo3.setBounds(0,0,1366, 768); // Medidas provicionales e incorrectas
		
		// Imagen de fondo pestaña 4
		final ImageIcon fondo4 = new ImageIcon(getClass().getResource("/street.jpeg"));
		JLabel jFondo4 = new JLabel();
		jFondo4.setIcon(fondo4);
		jFondo4.setBounds(0,0,screenSize.width, screenSize.height); // Medidas originales y correctas (11/12)
		//jFondo4.setBounds(0,0,1366, 768); // Medidas provicionales e incorrectas
		
		// Imagen de fondo pestaña 5
		final ImageIcon fondo5 = new ImageIcon(getClass().getResource("/street.jpeg"));
		JLabel jFondo5 = new JLabel();
		jFondo5.setIcon(fondo5);
		jFondo5.setBounds(0,0,screenSize.width, screenSize.height); // Medidas originales y correctas (12/12)
		//jFondo5.setBounds(0,0,1366, 768); // Medidas provicionales e incorrectas
				
		// Creacion del panel para alta de *Alumno*
		JPanel JP_altaAlumno = new JPanel();
		JP_altaAlumno.setLayout(null);
		JP_altaAlumno.setOpaque(true);
		JP_altaAlumno.setBounds(w1, x1, 350, 310);
		//JP_altaAlumno.setBackground(new Color(0,0,0,175));
		JP_altaAlumno.setBackground(new Color(0,188,212,150));
		jp1.add(JP_altaAlumno);
		
		// Creacion del panel para alta de *Padre*
		JPanel JP_altaPadre = new JPanel();
		JP_altaPadre.setLayout(null);
		JP_altaPadre.setOpaque(true);
		JP_altaPadre.setBounds(w2, x1, 350, 310);
		//JP_altaPadre.setBackground(new Color(0,0,0,175));
		JP_altaPadre.setBackground(new Color(139,195,74,150));
		jp1.add(JP_altaPadre);
		
		// Creacion del panel para alta de *Profesor*
		JPanel JP_altaProfe = new JPanel();
		JP_altaProfe.setLayout(null);
		JP_altaProfe.setOpaque(true);
		JP_altaProfe.setBounds(w1, x2, 350, 245);
		//JP_altaProfe.setBackground(new Color(0,0,0,175));
		JP_altaProfe.setBackground(new Color(255,152,0,150));
		jp1.add(JP_altaProfe);
		
		// Creacion del panel para alta de *Administrador*
		JPanel JP_altaAdmin = new JPanel();
		JP_altaAdmin.setLayout(null);
		JP_altaAdmin.setOpaque(true);
		JP_altaAdmin.setBounds(w2, x2, 350, 245);
		//JP_altaAdmin.setBackground(new Color(0,0,0,175));
		JP_altaAdmin.setBackground(new Color(233,30,99,150));
		jp1.add(JP_altaAdmin);
				
		// Creacion del panel de *Boton Regresar* ALTA
		JPanel JP_backButton = new JPanel();
		JP_backButton.setLayout(null);
		JP_backButton.setOpaque(true);
		JP_backButton.setBounds(y1, y2, 180, 80);
		//JP_backButton.setBackground(new Color(0,0,0,175));
		JP_backButton.setBackground(new Color(66,133,244,150));
		jp1.add(JP_backButton);
		
		// Creacion del panel para baja de *Boton Regresar* BAJA
		JPanel JP_backBaja = new JPanel();
		JP_backBaja.setLayout(null);
		JP_backBaja.setOpaque(true);
		JP_backBaja.setBounds(y1, y2, 180, 80);
		JP_backBaja.setBackground(new Color(66,133,244,150));
		jp2.add(JP_backBaja);
		
		// Creacion del panel para baja de *Boton Regresar* MODIFICAR
		JPanel JP_backMod = new JPanel();
		JP_backMod.setLayout(null);
		JP_backMod.setOpaque(true);
		JP_backMod.setBounds(y1, y2, 180, 80);
		JP_backMod.setBackground(new Color(66,133,244,150));
		jp3.add(JP_backMod);
		
		// Creacion del panel para baja de *Boton Regresar* CONSULTA
		JPanel JP_backCons = new JPanel();
		JP_backCons.setLayout(null);
		JP_backCons.setOpaque(true);
		JP_backCons.setBounds(y1, g2, 180, 80);
		JP_backCons.setBackground(new Color(139,195,74,150));
		jp4.add(JP_backCons);
		
		// Creacion del panel para baja de *Alumno*
		JP_bajaAlumno = new JPanel();
		JP_bajaAlumno.setLayout(null);
		JP_bajaAlumno.setOpaque(true);
		JP_bajaAlumno.setBounds(w1, x1_Baja, 350, 310);
		JP_bajaAlumno.setBackground(new Color(0,188,212,150));
		jp2.add(JP_bajaAlumno);
		
		// Creacion del panel para baja de *Padre*
		JP_bajaPadre = new JPanel();
		JP_bajaPadre.setLayout(null);
		JP_bajaPadre.setOpaque(true);
		JP_bajaPadre.setBounds(w2, x1_Baja, 350, 310);
		JP_bajaPadre.setBackground(new Color(139,195,74,150));
		jp2.add(JP_bajaPadre);
		
		// Creacion del panel para baja de *Profesor*
		JP_bajaProfe = new JPanel();
		JP_bajaProfe.setLayout(null);
		JP_bajaProfe.setOpaque(true);
		JP_bajaProfe.setBounds(w1, x2_baja, 350, 310);
		JP_bajaProfe.setBackground(new Color(255,152,0,150));
		jp2.add(JP_bajaProfe);
		
		// Creacion del panel para baja de *Administrador*
		JP_bajaAdmin = new JPanel();
		JP_bajaAdmin.setLayout(null);
		JP_bajaAdmin.setOpaque(true);
		JP_bajaAdmin.setBounds(w2, x2_baja, 350, 310);
		JP_bajaAdmin.setBackground(new Color(233,30,99,150));
		jp2.add(JP_bajaAdmin);
			
		// Creacion del panel para modificar de *Alumno*
		JP_modAlumno = new JPanel();
		JP_modAlumno.setLayout(null);
		JP_modAlumno.setOpaque(true);
		JP_modAlumno.setBounds(w1, x1_Baja, 350, 310);
		JP_modAlumno.setBackground(new Color(0,188,212,150));
		jp3.add(JP_modAlumno);
		
		// Creacion del panel para modificar de *Padre*
		JP_modPadre = new JPanel();
		JP_modPadre.setLayout(null);
		JP_modPadre.setOpaque(true);
		JP_modPadre.setBounds(w2, x1_Baja, 350, 310);
		JP_modPadre.setBackground(new Color(139,195,74,150));
		jp3.add(JP_modPadre);
		
		// Creacion del panel para modificar de *Profesor*
		JP_modProfe = new JPanel();
		JP_modProfe.setLayout(null);
		JP_modProfe.setOpaque(true);
		JP_modProfe.setBounds(w1, x2_baja, 350, 310);
		JP_modProfe.setBackground(new Color(255,152,0,150));
		jp3.add(JP_modProfe);
		
		// Creacion del panel para modificar de *Administrador*
		JP_modAdmin = new JPanel();
		JP_modAdmin.setLayout(null);
		JP_modAdmin.setOpaque(true);
		JP_modAdmin.setBounds(w2, x2_baja, 350, 310);
		JP_modAdmin.setBackground(new Color(233,30,99,150));
		jp3.add(JP_modAdmin);
		
		// Creacion del panel para -CONSULTA-
		jp_Consulta = new JPanel();
		jp_Consulta.setLayout(null);
		jp_Consulta.setOpaque(true);
		jp_Consulta.setBounds(h2, h1, 350, 310);
		jp_Consulta.setBackground(new Color(0,188,212,150));
		jp4.add(jp_Consulta);
		
		// Imagenes para las pestañas
		final ImageIcon i_Alta = new ImageIcon(getClass().getResource("/btn_Alta.png"));
		final ImageIcon i_Baja = new ImageIcon(getClass().getResource("/btn_Baja.png"));
		final ImageIcon i_Cons = new ImageIcon(getClass().getResource("/btn_Consul.png"));
		final ImageIcon i_Modi = new ImageIcon(getClass().getResource("/btn_Repor.png"));
		final ImageIcon i_Repo = new ImageIcon(getClass().getResource("/btn_Modif.png"));
		
		// Etiquetas para las pestañas
		JLabel lbl_Alta = new JLabel("ALTA");
		JLabel lbl_Baja = new JLabel("BAJA");
		JLabel lbl_Cons = new JLabel("CONSULTA");
		JLabel lbl_Modi = new JLabel("MODIFICAR");
		JLabel lbl_Repo = new JLabel("AJUSTES");
		
		// Alinear etiqueta sin importar el icono
		lbl_Alta.setHorizontalTextPosition(JLabel.TRAILING); 
		lbl_Baja.setHorizontalTextPosition(JLabel.TRAILING);
		lbl_Cons.setHorizontalTextPosition(JLabel.TRAILING);
		lbl_Modi.setHorizontalTextPosition(JLabel.TRAILING);
		lbl_Repo.setHorizontalTextPosition(JLabel.TRAILING);
		
		// Fuentes para los botones de las pestanas 
		lbl_Alta.setFont(new Font("Arial", Font.PLAIN, 18));
		lbl_Baja.setFont(new Font("Arial", Font.PLAIN, 18));
		lbl_Cons.setFont(new Font("Arial", Font.PLAIN, 18));
		lbl_Modi.setFont(new Font("Arial", Font.PLAIN, 18));
		lbl_Repo.setFont(new Font("Arial", Font.PLAIN, 18));
		
		// Asignar imagenes a las etiquetas
		lbl_Alta.setIcon(i_Alta);
		lbl_Baja.setIcon(i_Baja);
		lbl_Cons.setIcon(i_Cons);
		lbl_Modi.setIcon(i_Modi);
		lbl_Repo.setIcon(i_Repo);
		
		// Combo box de Grado en alta Alumno
		String[] graddSa1 = new String[6];
		graddSa1[0] = "Primero";
		graddSa1[1] = "Segundo";
		graddSa1[2] = "Tercero";
		graddSa1[3] = "Cuarto";
		graddSa1[4] = "Quinto";
		graddSa1[5] = "Sexto";
		cbA1 = new JComboBox(graddSa1);
		cbA1.addActionListener(this);
		
		// Combo box de Grupo en alta Alumno
		String[] grupA2 = new String[2];
		grupA2[0] = "A";
		grupA2[1] = "B";
		cbA2 = new JComboBox(grupA2);
		cbA1.addActionListener(this);
		
		// Combo box de Sexo en alta Alumno
		String[] sexA2 = new String[2];
		sexA2[0] = "M";
		sexA2[1] = "F";
		cbA3 = new JComboBox(sexA2);
		cbA3.addActionListener(this);
		
		// Combo box de Grado en alta Profesores
		String[] sexC2 = new String[6];
		sexC2[0] = "Primero";
		sexC2[1] = "Segundo";
		sexC2[2] = "Tercero";
		sexC2[3] = "Cuarto";
		sexC2[4] = "Quinto";
		sexC2[5] = "Sexto";
		cbC1 = new JComboBox(sexC2);
		
		// Combo box de Grado en baja Alumno
		String[] bajAlum = new String[6];
		bajAlum[0] = "Primero";
		bajAlum[1] = "Segundo";
		bajAlum[2] = "Tercero";
		bajAlum[3] = "Cuarto";
		bajAlum[4] = "Quinto";
		bajAlum[5] = "Sexto";
		cb_B1 = new JComboBox(bajAlum);
		cb_B1.addActionListener(this);
		
		// Combo box de Grupo en alta Alumno
		String[] bajAlumG = new String[2];
		bajAlumG[0] = "A";
		bajAlumG[1] = "B";
		cb_B2 = new JComboBox(bajAlumG);
		cb_B2.addActionListener(this);
		
		// Combo box de Grado en modificar Alumno
		cb_M1 = new JComboBox(bajAlum);
		cb_M1.addActionListener(this);
		
		// Combo box grupo en modificar Alumno
		cb_M2 = new JComboBox(bajAlumG);
		cb_M2.addActionListener(this);
		
		// Combo box grado en CONSULTA
		cb_1C = new JComboBox(bajAlum);
		cb_1C.addActionListener(this);
		
		// Combo box grupo en CONSULTA
		cb_2C = new JComboBox(bajAlumG);
		cb_2C.addActionListener(this);
		
		// Inicializamos el boton de salida alta 
		btn_Exit1 = new JButton("Salir");
		btn_Exit1.addActionListener(this);
		
		// Inicializamos el boton de salida baja 
		btn_Exit2 = new JButton("Salir");
		btn_Exit2.addActionListener(this);
				
		// Inicializamos el boton de salida modificar 
		btn_Exit3 = new JButton("Salir");
		btn_Exit3.addActionListener(this);
						
		// Inicializamos el boton de salida Consulta 
		btn_Exit4 = new JButton("Salir");
		btn_Exit4.addActionListener(this);
				
		// Inicializamos tablas de Baja Alumno
		tb_BAlum = new JTable();
		
		// Inicializacion de las etiquetas de alta Alumno
		JLabel lblA1 = new JLabel("ALTA DE ALUMNOS");
		JLabel lblA2 = new JLabel("Apellido paterno:", SwingConstants.RIGHT);
		JLabel lblA3 = new JLabel("Apellido materno:", SwingConstants.RIGHT);
		JLabel lblA4 = new JLabel("Nombre(s):", SwingConstants.RIGHT);
		JLabel lblA5 = new JLabel("Foto:", SwingConstants.RIGHT);
		JLabel lblA6 = new JLabel("Grado:", SwingConstants.RIGHT);
		JLabel lblA7 = new JLabel("Grupo:", SwingConstants.RIGHT);
		JLabel lblA8 = new JLabel("Sexo:", SwingConstants.RIGHT);
		JLabel lblA10 = new JLabel("Tarjeta:", SwingConstants.RIGHT);
				
		// Inicializacion de las etiquetas de alta Padre
		JLabel lblB1 = new JLabel("ALTA DE TUTORES");
		JLabel lblB2 = new JLabel("Apellido paterno:", SwingConstants.RIGHT);
		JLabel lblB3 = new JLabel("Apellido materno:", SwingConstants.RIGHT);
		JLabel lblB4 = new JLabel("Nombre(s):", SwingConstants.RIGHT);
		JLabel lblB5 = new JLabel("Tarjeta de Hijo:", SwingConstants.RIGHT);
		JLabel lblB6 = new JLabel("Parentesco:", SwingConstants.RIGHT);
		JLabel lblB7 = new JLabel("Correo electrónico:", SwingConstants.RIGHT);
		JLabel lblB8 = new JLabel("Tarjeta:", SwingConstants.RIGHT);
		
		// Inicializacion de las etiquetas de alta Profesor
		JLabel lblC1 = new JLabel("ALTA DE PROFESORES");
		JLabel lblC2 = new JLabel("Apellido paterno:", SwingConstants.RIGHT);
		JLabel lblC3 = new JLabel("Apellido materno:", SwingConstants.RIGHT);
		JLabel lblC4 = new JLabel("Nombre(s):", SwingConstants.RIGHT);
		JLabel lblC5 = new JLabel("Grado:", SwingConstants.RIGHT);
		JLabel lblC6 = new JLabel("Tarjeta:", SwingConstants.RIGHT);
				
		// Inicializacion de las etiquetas de alta Administrador
		JLabel lblD1 = new JLabel("ALTA DE ADMINISTRADOR");
		JLabel lblD2 = new JLabel("Apellido paterno:", SwingConstants.RIGHT);
		JLabel lblD3 = new JLabel("Apellido materno:", SwingConstants.RIGHT);
		JLabel lblD4 = new JLabel("Nombre(s):", SwingConstants.RIGHT);
		JLabel lblD5 = new JLabel("Tarjeta:", SwingConstants.RIGHT);
		
		// Inicializacion de las etiquetas de baja Alumno
		lbl_B1 = new JLabel("BAJA DE ALUMNOS", SwingConstants.CENTER);
		lbl_B2 = new JLabel("Grado:", SwingConstants.RIGHT);
		lbl_B3 = new JLabel("Grupo:", SwingConstants.RIGHT);
		lbl_B4 = new JLabel("Seleccione el alumno que desea eliminar");
		
		// Inicializacion de las etiquetas de baja Padre
		lblBT = new JLabel("BAJA DE TUTORES", SwingConstants.CENTER);
		
		// Inicializacion de las etiquetas de baja Profe
		lblBP = new JLabel("BAJA DE PROFESORES", SwingConstants.CENTER);
		
		// Inicializacion de las etiquetas de baja Profe
		lblBA = new JLabel("BAJA DE ADMINISTRADOR", SwingConstants.CENTER);
		
		// Inicializacion de las etiquetas de modificar Alumno
		lbl_M1 = new JLabel("MODIFICAR ALUMNOS", SwingConstants.CENTER);
		lbl_M2 = new JLabel("Grado:", SwingConstants.RIGHT);
		lbl_M3 = new JLabel("Grupo:", SwingConstants.RIGHT);
		lbl_M4 = new JLabel("Seleccione el alumno que desea modificar");
		
		// Inicializacion de las etiquetas de modificar Padre
		lblMT = new JLabel("MODIFICAR TUTORES", SwingConstants.CENTER);
		
		// Inicializacion de las etiquetas de modificar Profe
		lblMP = new JLabel("MODIFICAR PROFESORES", SwingConstants.CENTER);
		
		// Inicializacion de las etiquetas de modificar Profe
		lblMA = new JLabel("MODIFICAR ADMINISTRADOR", SwingConstants.CENTER);
		
		// Inicializacion de las etiquetas Consulta
		lbl_1C = new JLabel("CONSULTA DE ASISTENCIA", SwingConstants.CENTER);
		lbl_2C = new JLabel("Grado:", SwingConstants.RIGHT);
		lbl_3C = new JLabel("Grupo:", SwingConstants.RIGHT);
		
			
		// Cambiar el color de etiquetas de alta Alumnos
		lblA1.setFont(new Font("Arial", Font.BOLD, 20));
		lblA1.setForeground(Color.white);
		lblA2.setForeground(Color.white);
		lblA3.setForeground(Color.white);
		lblA4.setForeground(Color.white);
		lblA5.setForeground(Color.white);
		lblA6.setForeground(Color.white);
		lblA7.setForeground(Color.white);
		lblA10.setForeground(Color.white);
		lblA8.setForeground(Color.white);
				
		// Cambiar el color de etiquetas de alta Padres
		lblB1.setFont(new Font("Arial", Font.BOLD, 20));
		lblB1.setForeground(Color.white);
		lblB2.setForeground(Color.white);
		lblB3.setForeground(Color.white);
		lblB4.setForeground(Color.white);
		lblB5.setForeground(Color.white);
		lblB6.setForeground(Color.white);
		lblB7.setForeground(Color.white);
		lblB8.setForeground(Color.white);
		
		// Cambiar el color de etiquetas de alta Profesores
		lblC1.setFont(new Font("Arial", Font.BOLD, 20));
		lblC1.setForeground(Color.white);
		lblC2.setForeground(Color.white);
		lblC3.setForeground(Color.white);
		lblC4.setForeground(Color.white);
		lblC5.setForeground(Color.white);
		lblC6.setForeground(Color.white);
				
		// Cambiar el color de etiquetas de alta Administrador
		lblD1.setFont(new Font("Arial", Font.BOLD, 20));
		lblD1.setForeground(Color.white);
		lblD2.setForeground(Color.white);
		lblD3.setForeground(Color.white);
		lblD4.setForeground(Color.white);
		lblD5.setForeground(Color.white);
		
		// Cambiar el color de las etiquetas de baja Alumnos
		lbl_B1.setFont(new Font("Arial", Font.BOLD, 20));
		lbl_B1.setForeground(Color.white);
		lbl_B2.setForeground(Color.white);
		lbl_B3.setForeground(Color.white);
		
		// Cambiar el color de las etiquetas de baja Padres
		lblBT.setFont(new Font("Arial", Font.BOLD, 20));
		lblBT.setForeground(Color.white);
		
		// Cambiar el color de las etiquetas de baja Profesores
		lblBP.setFont(new Font("Arial", Font.BOLD, 20));
		lblBP.setForeground(Color.white);
		
		// Cambiar el color de las etiquetas de baja Administrador
		lblBA.setFont(new Font("Arial", Font.BOLD, 20));
		lblBA.setForeground(Color.white);
		
		// Cambiar el color de las etiquetas de modificar Alumnos
		lbl_M1.setFont(new Font("Arial", Font.BOLD, 20));
		lbl_M1.setForeground(Color.white);
		lbl_M2.setForeground(Color.white);
		lbl_M3.setForeground(Color.white);
		
		// Cambiar el color de las modificar de baja Padres
		lblMT.setFont(new Font("Arial", Font.BOLD, 20));
		lblMT.setForeground(Color.white);
				
		// Cambiar el color de las modificar de baja Profesores
		lblMP.setFont(new Font("Arial", Font.BOLD, 20));
		lblMP.setForeground(Color.white);
		
		// Cambiar el color de las etiquetas de modificar Administrador
		lblMA.setFont(new Font("Arial", Font.BOLD, 20));
		lblMA.setForeground(Color.white);
		
		// Cambiar el color de las etiquetas de Consulta
		lbl_1C.setFont(new Font("Arial", Font.BOLD, 20));
		lbl_1C.setForeground(Color.white);
		lbl_2C.setForeground(Color.white);
		lbl_3C.setForeground(Color.white);
								
		// Inicializacion de los campos de alta Alumno
		tfA1 = new JTextField();
		tfA2 = new JTextField();
		tfA3 = new JTextField();
		tfA4 = new JTextField();
		tfA5 = new JPasswordField();
		
		// Inicializacion de los campos de alta Padres
		tfB1 = new JTextField();
		tfB2 = new JTextField();
		tfB3 = new JTextField();
		tfB4 = new JPasswordField();
		tfB5 = new JTextField();
		tfB6 = new JTextField();
		tfB7 = new JPasswordField();
		
		// Inicializacion de los campos de alta Profesores
		tfC1 = new JTextField();
		tfC2 = new JTextField();
		tfC3 = new JTextField();
		tfC4 = new JPasswordField();
				
		// Inicializacion de los campos de alta Administrador
		tfD1 = new JTextField();
		tfD2 = new JTextField();
		tfD3 = new JTextField();
		tfD4 = new JPasswordField();
		
		
		
		
		// Al presionar enter se activa el boton Alta ALUMNO
		tfA5.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnA2.doClick();
						}
					}
				});
		
		// ***************************************************************
		
		// Inicializamos el campo de busqueda de Baja padre
		tfBT = new JTextField();
		tfBT.addActionListener(this);
		
		// Al presionar enter se activa el boton Buscar en Baja padre
		tfBT.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnBT.doClick();
						}
					}
				});
		
		// Texto ilustrativo del campo baja Padre
		tp7 = new TextPrompt("Nombre o matricula del tutor" , tfBT);
		tp7.setForeground(Color.gray);
		tp7.changeStyle(Font.PLAIN + Font.ITALIC);
		
		// ***************************************************************
				
		// Inicializamos el campo de busqueda de Baja Profesor
		tfBP = new JTextField();
		tfBP.addActionListener(this);
		
		// Al presionar enter se activa el boton Buscar en Baja Profesor
		tfBP.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnBP.doClick();
						}
					}
				});
						
		// Texto ilustrativo del campo baja Profesor
		tp8 = new TextPrompt("Nombre o matricula del profesor" , tfBP);
		tp8.setForeground(Color.gray);
		tp8.changeStyle(Font.PLAIN + Font.ITALIC);
		
		// ***************************************************************
		
		// Inicializamos el campo de busqueda de Baja Administrador
		tfBA = new JTextField();
		tfBA.addActionListener(this);
		
		// Al presionar enter se activa el boton Buscar en Baja Administrador
		tfBA.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnBA.doClick();
						}
					}
				});
		
		// Texto ilustrativo del campo baja Administrador
		tp9 = new TextPrompt("Nombre o matricula del administrador" , tfBA);
		tp9.setForeground(Color.gray);
		tp9.changeStyle(Font.PLAIN + Font.ITALIC);
		
		// ***************************************************************
		
		// Inicializamos el campo de busqueda de Modificar padre
		tfMT = new JTextField();
		tfMT.addActionListener(this);
		
		// Al presionar enter se activa el boton Buscar en Baja padre
		tfMT.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnMT.doClick();
						}
					}
				});
		
		// Texto ilustrativo del campo baja Padre
		tp10 = new TextPrompt("Nombre o matricula del tutor" , tfMT);
		tp10.setForeground(Color.gray);
		tp10.changeStyle(Font.PLAIN + Font.ITALIC);
		
		// ***************************************************************
		
		// Inicializamos el campo de busqueda de Modificar Profesor
		tfMP = new JTextField();
		tfMP.addActionListener(this);
		
		// Al presionar enter se activa el boton Buscar en Baja Profesor
		tfMP.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnMP.doClick();
						}
					}
				});
						
		// Texto ilustrativo del campo baja Profesor
		tp11 = new TextPrompt("Nombre o matricula del profesor" , tfMP);
		tp11.setForeground(Color.gray);
		tp11.changeStyle(Font.PLAIN + Font.ITALIC);
		
		// ***************************************************************
		
		// Inicializamos el campo de busqueda de Baja Administrador
		tfMA = new JTextField();
		tfMA.addActionListener(this);
		
		// Al presionar enter se activa el boton Buscar en Baja Administrador
		tfMA.addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnMA.doClick();
						}
					}
				});
		
		// Texto ilustrativo del campo baja Administrador
		tp12 = new TextPrompt("Nombre o matricula del administrador" , tfMA);
		tp12.setForeground(Color.gray);
		tp12.changeStyle(Font.PLAIN + Font.ITALIC);
		
		// ***************************************************************
		
		// Quitamos Bordes a Campos de alta Alumno 
		tfA1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfA2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfA3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfA4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfA5.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		// Quitamos Bordes a Campos de alta Padres
		tfB1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfB2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfB3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfB4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfB5.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfB6.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfB7.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		// Quitamos Bordes a Campos de alta Alumno
		tfC1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfC2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfC3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfC4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				
		// Quitamos Bordes a Campos de alta Alumno
		tfD1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfD2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfD3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfD4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		// Quitamos Bordes a Campos de la pesta�a BAJA
		tfBT.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfBP.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tfBA.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		// Añadimos los action listener a los campos de alta Alumnos
		/*tfA1.addActionListener(this);
		tfA2.addActionListener(this);
		tfA3.addActionListener(this);
		tfA4.addActionListener(this);
		tfA5.addActionListener(this);*/
		
		// Añadimos los action listener a los campos de alta Padre
		/*tfB1.addActionListener(this);
		tfB2.addActionListener(this);
		tfB3.addActionListener(this);
		tfB4.addActionListener(this);
		tfB5.addActionListener(this);
		tfB6.addActionListener(this);
		tfB7.addActionListener(this);*/
		
		// Inicializacion de los botones de alta Alumnos
		btnA1 = new JButton("Buscar foto");
		btnA1.addActionListener(this);
		btnA1.setActionCommand("buscar_Alumno");
		btnA2 = new JButton("Dar de Alta");
		btnA2.addActionListener(this);
		btnA2.setActionCommand("alta_Alumno");
				
		// Inicializacion de los botones de alta Padres
		btnB2 = new JButton("Dar de Alta");
		btnB2.addActionListener(this);
		btnB2.setActionCommand("alta_Padre");
		
		// Inicializacion de los botones de alta Profesores
		btnC2 = new JButton("Dar de Alta");
		btnC2.addActionListener(this);
		btnC2.setActionCommand("alta_Profe");
		
		// Inicializacion de los botones de alta Administrador
		btnD2 = new JButton("Dar de Alta");
		btnD2.addActionListener(this);
		btnD2.setActionCommand("alta_Admin");
		
		// Inicializacion de los botones de baja Alumnos
		bajaAlum = new JButton("Buscar");
		bajaAlum.addActionListener(this);
		bajaAlum.setActionCommand("buscBajAlum");
		
		// Inicializacion del boton de baja Padres
		btnBT = new JButton("Buscar");
		btnBT.addActionListener(this);
		btnBT.setActionCommand("buscarTutor");
		
		// Inicializacion del boton de baja Profesores
		btnBP = new JButton("Buscar");
		btnBP.addActionListener(this);
		btnBP.setActionCommand("buscarProfesor");
		
		// Inicializacion del boton de baja Administrador
		btnBA = new JButton("Buscar");
		btnBA.addActionListener(this);
		btnBA.setActionCommand("buscarAdmin");
		
		// Inicializacion de los botones de baja Alumnos
		modAlum = new JButton("Buscar");
		modAlum.addActionListener(this);
		modAlum.setActionCommand("buscModAlum");
		
		// Inicializacion de los botones de modificar tutor
		btnMT = new JButton("Buscar");
		btnMT.addActionListener(this);
		btnMT.setActionCommand("buscModTutor");
		
		// Inicializacion de los botones de modificar profesor
		btnMP = new JButton("Buscar");
		btnMP.addActionListener(this);
		btnMP.setActionCommand("buscModProfe");
		
		// Inicializacion de los botones de modificar administrador
		btnMA = new JButton("Buscar");
		btnMA.addActionListener(this);
		btnMA.setActionCommand("buscModAdmin");
		
		// Inicializacion de los botones de Consulta
		bajaAlumC = new JButton("Buscar");
		bajaAlumC.addActionListener(this);
		bajaAlumC.setActionCommand("Consultar");
				
		// Medias etiquetas de alta Alumnos
		lblA1.setBounds(85, 10, 195, 20);
		lblA2.setBounds(35, 42, 123, 15);
		lblA3.setBounds(32, 72, 126, 15);
		lblA4.setBounds(80, 102, 78, 15);
		lblA5.setBounds(121, 132, 37, 15);
		lblA6.setBounds(112, 191, 48, 15);
		lblA7.setBounds(112, 220, 48, 15);
		lblA8.setBounds(208, 220, 39, 15);
		lblA10.setBounds(102, 247, 56, 15);
		
		// Medidas de las etiquetas de alta Padres
		lblB1.setBounds(85, 10, 195, 20);
		lblB2.setBounds(35, 42, 123, 15);
		lblB3.setBounds(32, 72, 126, 15);
		lblB4.setBounds(80, 102, 78, 15);
		lblB5.setBounds(34, 132, 124, 15);
		lblB6.setBounds(72, 162, 86, 15);
		lblB7.setBounds(22, 192, 136, 15);
		lblB8.setBounds(102, 222, 56, 15);
		
		// Medidas de las etiquetas de alta Profesores
		lblC1.setBounds(60, 10, 227, 20);
		lblC2.setBounds(35, 42, 123, 15);
		lblC3.setBounds(32, 72, 126, 15);
		lblC4.setBounds(80, 102, 78, 15);
		lblC5.setBounds(112, 132, 48, 15);
		lblC6.setBounds(102, 162, 56, 15);
				
		// Medidas de las etiquetas de alta Administrador
		lblD1.setBounds(60, 10, 255, 20);
		lblD2.setBounds(35, 42, 123, 15);
		lblD3.setBounds(32, 72, 126, 15);
		lblD4.setBounds(80, 102, 78, 15);
		lblD5.setBounds(102, 132, 56, 15);
				
		// Medias de campos de texto alta Alumnos
		tfA1.setBounds(163, 39, 128, 20);
		tfA2.setBounds(163, 70, 128, 20);
		tfA3.setBounds(163, 100, 128, 20);
		tfA4.setBounds(163, 130, 128, 20);
		tfA5.setBounds(163, 245, 128, 20);
		
		// Medias de campos de texto alta Padres
		tfB1.setBounds(163, 40, 128, 20);
		tfB2.setBounds(163, 70, 128, 20);
		tfB3.setBounds(163, 100, 128, 20);
		tfB4.setBounds(163, 130, 128, 20);
		tfB5.setBounds(163, 160, 128, 20);
		tfB6.setBounds(163, 190, 128, 20);
		tfB7.setBounds(163, 220, 128, 20);
		
		// Medias de campos de texto alta Profesores
		tfC1.setBounds(163, 40, 128, 20);
		tfC2.setBounds(163, 70, 128, 20);
		tfC3.setBounds(163, 100, 128, 20);
		tfC4.setBounds(163, 160, 128, 20);
				
		// Medias de campos de texto alta Administrador
		tfD1.setBounds(163, 40, 128, 20);
		tfD2.setBounds(163, 70, 128, 20);
		tfD3.setBounds(163, 100, 128, 20);
		tfD4.setBounds(163, 130, 128, 20);
		
		// Medidas de los botones de alta Alumno
		btnA1.setBounds(163, 160, 128, 20);
		btnA2.setBounds(110, 275, 130, 25);
		
		// Medidas de los botones de alta Padre
		btnB2.setBounds(110, 265, 130, 25);
		
		// Medidas de los botones de alta Profesor
		btnC2.setBounds(110, 200, 130, 25);
		
		// Medidas de los botones de alta Administrador
		btnD2.setBounds(110, 200, 130, 25);
		
		// Medidas de los ComboBox alta Alumnos
		cbA1.setBounds(163, 190, 128, 20);
		cbA2.setBounds(163, 217, 39, 20);
		cbA3.setBounds(252, 217, 39, 20);
		
		// Medidas del ComboBox alta Profesores
		cbC1.setBounds(163, 130, 128, 20);
		
		// Medidas del boton de salida *Alta 
		btn_Exit1.setBounds(20, 25, 140, 30);
		
		// Medidas del boton de salida *Baja 
		btn_Exit2.setBounds(20, 25, 140, 30);
		
		// Medidas del boton de salida *Baja 
		btn_Exit3.setBounds(20, 25, 140, 30);
		
		// Medidas del boton de salida *Baja 
		btn_Exit4.setBounds(20, 25, 140, 30);
		
		// Medias de las etiquetas de baja Alumno
		lbl_B1.setBounds(0, 10, 350, 20);
		lbl_B2.setBounds(9, 42, 48, 15);
		lbl_B3.setBounds(152, 42, 48, 15);
		
		// Medidas de las etiquetas de baja Padre
		lblBT.setBounds(0, 10, 350, 20);
		
		//Medidas del boton Buscar de baja Alumno
		bajaAlum.setBounds(248, 38, 90, 25);
		
		// Medidas de los ComboBox de baja Alumno
		cb_B1.setBounds(60, 40, 88, 20);
		cb_B2.setBounds(202, 40, 39, 20);
		
		// Medidas de campos de texto baja Padres
		tfBT.setBounds(10, 41, 232, 20);
		
		// Medias del boton buscar en baja Padres
		btnBT.setBounds(248, 38, 90, 25);
		
		// Medidas de las etiquetas de baja Profesor
		lblBP.setBounds(0, 10, 350, 20);
		
		// Medidas de campos de texto baja Profesor
		tfBP.setBounds(10, 41, 232, 20);
		
		//Medidas del boton Buscar de baja Profesor
		btnBP.setBounds(248, 38, 90, 25);
			
		// Medidas de las etiquetas de baja Administrador
		lblBA.setBounds(0, 10, 350, 20);
		
		// Medidas de campos de texto baja Administrador
		tfBA.setBounds(10, 41, 232, 20);
		
		//Medidas del boton Buscar de baja Administrador
		btnBA.setBounds(248, 38, 90, 25);
		
		// Medias de las etiquetas de modificar Alumno
		lbl_M1.setBounds(0, 10, 350, 20);
		lbl_M2.setBounds(9, 42, 48, 15);
		lbl_M3.setBounds(152, 42, 48, 15);
		
		// Medidas de los ComboBox de modificar Alumno
		cb_M1.setBounds(60, 40, 88, 20);
		cb_M2.setBounds(202, 40, 39, 20);
		
		//Medidas del boton Buscar de modificar Alumno
		modAlum.setBounds(248, 38, 90, 25);
		
		// Medidas de las etiquetas de modificar Padre
		lblMT.setBounds(0, 10, 350, 20);
		
		// Medidas de las etiquetas de modificar Profesor
		lblMP.setBounds(0, 10, 350, 20);
		
		// Medidas de las etiquetas de modificar Administrador
		lblMA.setBounds(0, 10, 350, 20);
		
		// Medidas de campos de texto modificar Padres
		tfMT.setBounds(10, 41, 232, 20);
		
		// Medidas de campos de texto modificar Profesor
		tfMP.setBounds(10, 41, 232, 20);
		
		// Medidas de campos de texto modificar Administrador
		tfMA.setBounds(10, 41, 232, 20);
		
		// Medidas del boton modificar en modificar Padres
		btnMT.setBounds(248, 38, 90, 25);
		
		// Medidas del boton modificar en modificar Profesor
		btnMP.setBounds(248, 38, 90, 25);
		
		// Medidas del boton modificar en modificar Profesor
		btnMA.setBounds(248, 38, 90, 25);
		
		// Medidas de los ComboBox en CONSULTA
		cb_1C.setBounds(60, 40, 88, 20);
		cb_2C.setBounds(202, 40, 39, 20);
		
		// Medidas del boton Buscar en Consulta
		bajaAlumC.setBounds(248, 38, 90, 25);
		
		// Medias de las etiquetas de Consulta
		lbl_1C.setBounds(0, 10, 350, 20);
		lbl_2C.setBounds(9, 42, 48, 15);
		lbl_3C.setBounds(152, 42, 48, 15);
				
		// Aderimos etiquetas de alta Alumnos
		JP_altaAlumno.add(lblA1);
		JP_altaAlumno.add(lblA2);
		JP_altaAlumno.add(lblA3);
		JP_altaAlumno.add(lblA4);
		JP_altaAlumno.add(lblA5);
		JP_altaAlumno.add(lblA6);
		JP_altaAlumno.add(lblA7);
		JP_altaAlumno.add(lblA10);
		JP_altaAlumno.add(lblA8);
				
		// Aderimos etiquetas de alta Padres
		JP_altaPadre.add(lblB1);
		JP_altaPadre.add(lblB2);
		JP_altaPadre.add(lblB3);
		JP_altaPadre.add(lblB4);
		JP_altaPadre.add(lblB5);
		JP_altaPadre.add(lblB6);
		JP_altaPadre.add(lblB7);
		JP_altaPadre.add(lblB8);
		
		// Aderimos etiquetas de alta Padres
		JP_altaProfe.add(lblC1);
		JP_altaProfe.add(lblC2);
		JP_altaProfe.add(lblC3);
		JP_altaProfe.add(lblC4);
		JP_altaProfe.add(lblC5);
		JP_altaProfe.add(lblC6);
				
		// Aderimos etiquetas de alta Administrador
		JP_altaAdmin.add(lblD1);
		JP_altaAdmin.add(lblD2);
		JP_altaAdmin.add(lblD3);
		JP_altaAdmin.add(lblD4);
		JP_altaAdmin.add(lblD5);
		
		// Aderimos los comboBox al alta de Alumnos
		JP_altaAlumno.add(cbA1);
		JP_altaAlumno.add(cbA2);
		JP_altaAlumno.add(cbA3);
		
		// Aderimos el comboBox al alta de Profesores
		JP_altaProfe.add(cbC1);
		
		// Aderimos los campos de texto de alta Alumnos
		JP_altaAlumno.add(tfA1);
		JP_altaAlumno.add(tfA2);
		JP_altaAlumno.add(tfA3);
		JP_altaAlumno.add(tfA4);
		JP_altaAlumno.add(tfA5);
		
		// Aderimos los campos de texto de alta Padres
		JP_altaPadre.add(tfB1);
		JP_altaPadre.add(tfB2);
		JP_altaPadre.add(tfB3);
		JP_altaPadre.add(tfB4);
		JP_altaPadre.add(tfB5);
		JP_altaPadre.add(tfB6);
		JP_altaPadre.add(tfB7);
		
		// Aderimos los campos de texto de alta Profesores
		JP_altaProfe.add(tfC1);
		JP_altaProfe.add(tfC2);
		JP_altaProfe.add(tfC3);
		JP_altaProfe.add(tfC4);
				
		// Aderimos los campos de texto de alta Administrador
		JP_altaAdmin.add(tfD1);
		JP_altaAdmin.add(tfD2);
		JP_altaAdmin.add(tfD3);
		JP_altaAdmin.add(tfD4);
		
		// Aderimos los botones al alta Alumnos
		JP_altaAlumno.add(btnA1);
		JP_altaAlumno.add(btnA2);
				
		// Aderimos los botones al alta Padres
		JP_altaPadre.add(btnB2);
		
		// Aderimos los botones al alta Profesores
		JP_altaProfe.add(btnC2);
		
		// Aderimos los botones al alta Administrador
		JP_altaAdmin.add(btnD2);
		
		// Aderimos el boton de salida al panel correspondiente
		JP_backButton.add(btn_Exit1);
		
		// Aderimos el boton de salida al panel correspondiente
		JP_backBaja.add(btn_Exit2);
		
		// Aderimos el boton de salida al panel correspondiente
		JP_backMod.add(btn_Exit3);
				
		// Aderimos las etiquetas a baja Alumnos
		JP_bajaAlumno.add(lbl_B1);
		JP_bajaAlumno.add(lbl_B2);
		JP_bajaAlumno.add(lbl_B3);
		
		// Aderimos las etiquetas a baja Padres
		JP_bajaPadre.add(lblBT);
		
		// Aderimos los botones de baja Alumnos
		JP_bajaAlumno.add(bajaAlum);
				
		// Aderimos los comboBox al baja Alumno
		JP_bajaAlumno.add(cb_B1);
		JP_bajaAlumno.add(cb_B2);
		
		// Aderimos el campo de texto de baja Padres
		JP_bajaPadre.add(tfBT);
		
		// Aderimos el boton buscar de baja Padres
		JP_bajaPadre.add(btnBT);
		
		// Aderimos las etiquetas a baja Profesores
		JP_bajaProfe.add(lblBP);
		
		// Aderimos el campo de texto de baja Profesor
		JP_bajaProfe.add(tfBP);
		
		// Aderimos el boton buscar a baja Profesor
		JP_bajaProfe.add(btnBP);
		
		// Aderimos las etiquetas a baja Administrador
		JP_bajaAdmin.add(lblBA);
				
		// Aderimos el campo de texto de baja Administrador
		JP_bajaAdmin.add(tfBA);
		
		// Aderimos el boton buscar a baja Profesor
		JP_bajaAdmin.add(btnBA);
		
		// Aderimos las etiquetas a alumno Alumnos
		JP_modAlumno.add(lbl_M1);
		JP_modAlumno.add(lbl_M2);
		JP_modAlumno.add(lbl_M3);
		
		// Aderimos los Combo box a modificar alumno
		JP_modAlumno.add(cb_M1);
		JP_modAlumno.add(cb_M2);
		
		// Aderimos el boton Buscar modificar Alumno
		JP_modAlumno.add(modAlum);
		
		// Aderimos las etiquetas a modificar Padre
		JP_modPadre.add(lblMT);
		
		// Aderimos las etiquetas a modificar Profesor
		JP_modProfe.add(lblMP);
		
		// Aderimos las etiquetas a modificar Administrador
		JP_modAdmin.add(lblMA);
		
		// Aderimos el campo a modificar Tutor
		JP_modPadre.add(tfMT);
		
		// Aderimos el campo a modificar Profesor
		JP_modProfe.add(tfMP);
		
		// Aderimos el campo a modificar Modificar
		JP_modAdmin.add(tfMA);
		
		// Aderimos el boton buscar en modificar Tutor
		JP_modPadre.add(btnMT);
		
		// Aderimos el boton buscar en modificar Profesor
		JP_modProfe.add(btnMP);
		
		// Aderimos el boton buscar en modificar Administrador
		JP_modAdmin.add(btnMA);
		
		// Aderimos los combo box al panel Consulta
		jp_Consulta.add(cb_1C);
		jp_Consulta.add(cb_2C);
		
		// Aderimos el boton al panel Consulta
		jp_Consulta.add(bajaAlumC);
		
		// Aderimos el boton al panel Consulta
		jp_Consulta.add(lbl_1C);
		jp_Consulta.add(lbl_2C);
		jp_Consulta.add(lbl_3C);
		
		// Aderimos el boton salir en seccion Consulta
		JP_backCons.add(btn_Exit4);
		
									
		// ***El ultimo elemento a añadir es la etiqueta con el fondo 
		// - MUY IMPORTANTE -  se tiene que poner al final*** 
		jp1.add(jFondo);
		jp2.add(jFondo2);
		jp3.add(jFondo3);
		jp4.add(jFondo4);
		jp5.add(jFondo5);
		
		// Añadir pestañas al panel de pestañas
		tabs.addTab(null, jp1);
		tabs.addTab(null, jp2);
		tabs.addTab(null, jp3);
		tabs.addTab(null, jp4);
		//tabs.addTab(null, jp5);
		
		// Añadir con cierto orden las etiquetas a las pestañas
		tabs.setTabComponentAt(0, lbl_Alta);
		tabs.setTabComponentAt(1, lbl_Baja);
		tabs.setTabComponentAt(2, lbl_Modi);
		tabs.setTabComponentAt(3, lbl_Cons);
		//tabs.setTabComponentAt(4, lbl_Repo);
		
		//add(alta);
		add(tabs);
		
  }
	
	public void actionPerformed(ActionEvent e) {
		MySQL sq2 = new MySQL();
		
		if(e.getActionCommand().equals("buscar_Alumno")) {
			int resultado = jfc.showOpenDialog(null);
			
			if(resultado == JFileChooser.APPROVE_OPTION) {
				tfA4.setText(jfc.getSelectedFile().getPath());
			}
		}
		
		if(e.getActionCommand().equals("Salir")) {
			dispose();
		}
		
		if(e.getActionCommand().equals("alta_Alumno")) {
			String grado = (String) cbA1.getSelectedItem();
			String grupo = (String) cbA2.getSelectedItem();
			String sexo = (String) cbA3.getSelectedItem();
			String matricula = new String(tfA5.getPassword());
			
			try {
				if(!tfA1.getText().equals("")) {
					if(!tfA2.getText().equals("")) {
						if(!tfA3.getText().equals("")) {
							if(!tfA4.getText().equals("")) {
								if(!matricula.equals("")) {
																		
									sq2.alta_Alum(matricula, tfA1.getText(), tfA2.getText(), tfA3.getText(), grado, grupo,
											tfA4.getText(),sexo);
											
									JOptionPane.showMessageDialog(null, "Registro Exitoso");
									tfA1.setText("");
									tfA2.setText("");
									tfA3.setText("");
									tfA4.setText("");
									tfA5.setText("");
																		
								} else {
									JOptionPane.showMessageDialog(null,
										"Favor de llenar todos los campos",
										"Campos incompletos", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null,
									"Favor de llenar todos los campos",
									"Campos incompletos", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,
								"Favor de llenar todos los campos",
								"Campos incompletos", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
							"Favor de llenar todos los campos",
							"Campos incompletos", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
						"Favor de llenar todos los campos",
						"Campos incompletos", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Error en la base de datos", "¡Error!", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
			
		} else if(e.getActionCommand().equals("alta_Padre")) {
			String id_hijo = new String(tfB4.getPassword());
			String id_padre = new String(tfB7.getPassword());
			
			if(!tfB1.getText().equals("")) {
				if(!tfB2.getText().equals("")) {
					if(!tfB3.getText().equals("")) {
						if(!id_hijo.equals("")) {
							if(!tfB5.getText().equals("")) {
								if(!tfB6.getText().equals("")) {
									if(!id_padre.equals("")) {
										try {
											
											sq2.alta_Padre(tfB1.getText(), tfB2.getText(), tfB3.getText(), id_hijo, tfB5.getText(), 
													tfB6.getText(), id_padre);
											JOptionPane.showMessageDialog(null, "Registro Exitoso");
											tfB1.setText("");
											tfB2.setText("");
											tfB3.setText("");
											tfB4.setText("");
											tfB5.setText("");
											tfB6.setText("");
											tfB7.setText("");
											
										} catch (SQLException e1) {
											JOptionPane.showMessageDialog(null, "Error en la base de datos",
													"¡Error!", JOptionPane.ERROR_MESSAGE);
											e1.printStackTrace();
										}
									} else {
										JOptionPane.showMessageDialog(null,
												"Favor de llenar todos los campos",
												"Campos incompletos", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Favor de llenar todos los campos",
											"Campos incompletos", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Favor de llenar todos los campos",
										"Campos incompletos", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Favor de llenar todos los campos",
									"Campos incompletos", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Favor de llenar todos los campos",
								"Campos incompletos", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Favor de llenar todos los campos",
							"Campos incompletos", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Favor de llenar todos los campos",
						"Campos incompletos", JOptionPane.ERROR_MESSAGE);
			}
					
		} else if(e.getActionCommand().equals("alta_Profe")) {
			String grado = (String) cbC1.getSelectedItem();
			String matricula = new String(tfC4.getPassword());
			
			if(!tfC1.getText().equals("")) {
				if(!tfC2.getText().equals("")) {
					if(!tfC3.getText().equals("")) {
						if(!matricula.equals("")) {
							try {
								sq2.alta_Profe(matricula, tfC1.getText(), tfC2.getText(), tfC3.getText(), grado);
								JOptionPane.showMessageDialog(null, "Registro Exitoso");
								tfC1.setText("");
								tfC2.setText("");
								tfC3.setText("");
								tfC4.setText("");
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Error en la base de datos",
										"¡Error!", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Favor de llenar todos los campos",
									"Campos incompletos", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Favor de llenar todos los campos",
								"Campos incompletos", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Favor de llenar todos los campos",
							"Campos incompletos", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Favor de llenar todos los campos",
						"Campos incompletos", JOptionPane.ERROR_MESSAGE);
			}
					
		} else if(e.getActionCommand().equals("alta_Admin")) {
			String matricula = new String(tfD4.getPassword());
			
			if(!tfD1.getText().equals("")) {
				if(!tfD2.getText().equals("")) {
					if(!tfD3.getText().equals("")) {
						if(!matricula.equals("")) {
							try {
								sq2.alta_Admin(matricula, tfD1.getText(), tfD2.getText(), tfD3.getText());
								JOptionPane.showMessageDialog(null, "Registro Exitoso");
								tfD1.setText("");
								tfD2.setText("");
								tfD3.setText("");
								tfD4.setText("");
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Error en la base de datos",
										"¡Error!", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
							
						} else {
							JOptionPane.showMessageDialog(null,
									"Favor de llenar todos los campos",
									"Campos incompletos", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Favor de llenar todos los campos",
								"Campos incompletos", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Favor de llenar todos los campos",
							"Campos incompletos", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Favor de llenar todos los campos",
						"Campos incompletos", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getActionCommand().equals("buscBajAlum")) { // *******************Boton "Buscar" en baja Alumno
			System.out.println("Boton buscar alumno presionado");
			JP_bajaAlumno.setVisible(false);
			JP_bajaAlumno.removeAll();
			JP_bajaAlumno.setVisible(true);
			String grado = (String) cb_B1.getSelectedItem();
			String grupo = (String) cb_B2.getSelectedItem();
			Vector<Object> columnNames = new Vector<Object>();
	        Vector<Object> data = new Vector<Object>();
	        
	        // Extraccion de la informacion que sera utilizada en la Tabla
	        try {
	        	String url = "jdbc:mysql://localhost/system";
	            String userid = "root";
	            String password = "toor";
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection connection = DriverManager.getConnection( url, userid, password );

	            // Creacion del query a ejecutar
	            String sql = "select paterno, materno, nombre, "
	            		+ "grado, grupo from alumnos where grado='"
	            		+grado+"' and grupo='"+grupo+"' order by paterno";
	            
	            // Ejecucion del query anterior
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery( sql );
	            ResultSetMetaData md = rs.getMetaData();
	            int columns = md.getColumnCount();

	            // Obtiene los nombres de las columnas
	            for (int i = 1; i <= columns; i++) {
	                columnNames.addElement( md.getColumnName(i) );
	            }

	            // Obtiene la informacion de las filas
	            while (rs.next()) {
	            	Vector<Object> row = new Vector<Object>(columns);

	                for (int i = 1; i <= columns; i++) {
	                    row.addElement( rs.getObject(i) );
	                }

	                data.addElement( row );
	            }

	            rs.close();
	            stmt.close();
	            connection.close();
	        }
	        catch(Exception ex) {
	        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
						"¡Error!", JOptionPane.ERROR_MESSAGE);
	            System.out.println( ex + "   :D" );
	        }

	        //  Creamos la tabla con informacion de la BD
	        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
	        	@Override
	            public Class getColumnClass(int column) {
	                for (int row = 0; row < getRowCount(); row++) {
	                    Object o = getValueAt(row, column);

	                    if (o != null) {
	                        return o.getClass();
	                    }
	                }

	                return Object.class;
	            }
	        };

			tb_BAlum = new JTable(model);										// Inicializamos la tabla
			tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
			JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
	        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
	             
	        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
	        
	        lsmRow.addListSelectionListener(new ListSelectionListener() {
	        	@Override
	        	public void valueChanged(ListSelectionEvent le) {
	        		JButton e_BAlum = new JButton("Eliminar");
	        		e_BAlum.setBounds(125, 278, 100, 25);
	        		JP_bajaAlumno.setVisible(false);
	        		JP_bajaAlumno.add(e_BAlum);
	        		JP_bajaAlumno.setVisible(true);
	        		
	        		e_BAlum.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				int result = JOptionPane.showConfirmDialog(
	                                null, "Confirmar Baja de Alumno"
	                                , "Confirmación", JOptionPane.YES_NO_OPTION);
	        				
	        				if (result == JOptionPane.YES_OPTION) {
	        					String res[] = new String[5];
		        				int row = tb_BAlum.getSelectedRow();
		        				for(int i = 0; i < 5; i++) {
		        					res[i] = (String) tb_BAlum.getValueAt(row, i);
		        				}
		        				
		        				MySQL sql2 = new MySQL();
		        				
		        				try {
		        					sql2.baja_Alumn(res[0], res[1], res[2], res[3], res[4]);
									//JOptionPane.showMessageDialog(null, "Eliminación exitosa");
		        					bAlumno = true;
		        					bajaAlum.doClick();
								} catch (SQLException e1) {
									e1.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error en la base de datos",
											"¡Error!", JOptionPane.ERROR_MESSAGE);
								}
		        				
		        				System.out.println("Boton eliminar preisonado");
	                	
	        				} else if (result == JOptionPane.NO_OPTION) {
	        					System.out.println(":X");
	        				}
	                	}
	        		});
	        		System.out.println("tabla seleccionada");
	        	}
	        });
	        
	        JP_bajaAlumno.add(scrollPane);
	        JP_bajaAlumno.add(lbl_B2);
	        JP_bajaAlumno.add(cb_B1);
	        JP_bajaAlumno.add(cb_B2);
	        JP_bajaAlumno.add(bajaAlum);
	        JP_bajaAlumno.add(lbl_B1);
	        JP_bajaAlumno.add(lbl_B3);
	        
	        if(bAlumno) {
	        	bAlumno = false;
	        } else {
	        	try {
		        	String test = (String) tb_BAlum.getValueAt(0, 0);
			        System.out.println(test);
		        } catch (ArrayIndexOutOfBoundsException ex) {
		        	JOptionPane.showMessageDialog(null,
							"Su búsqueda no produjo ningún resultado",
							"Verifique los datos", JOptionPane.ERROR_MESSAGE);
		        }
	        }
		} else if (e.getActionCommand().equals("buscarTutor")) { // *******************Boton Buscar en Tutor Baja
			System.out.println("Boton buscar padre presionado");
			JP_bajaPadre.setVisible(false);
			JP_bajaPadre.removeAll();
			JP_bajaPadre.setVisible(true);
			Vector<Object> columnNames = new Vector<Object>();
	        Vector<Object> data = new Vector<Object>();
			String tex = tfBT.getText();
			if(tex.equals("")) {
				JP_bajaPadre.add(tfBT);
		        JP_bajaPadre.add(btnBT);
		        JP_bajaPadre.add(lblBT);
				JOptionPane.showMessageDialog(null, "Introduzca un nombre o matricula",
						"Mensaje", JOptionPane.ERROR_MESSAGE);
				//JP_bajaPadre.add(scrollPane);
		        
			} else {
				char tx = tex.charAt(0);
				
				// Iniciamos checando si el usuario busco por matricula
				if(Character.isDigit(tx)) { 			
					
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from padres where clave like '"+ tex + "%'";
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Eliminar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_bajaPadre.setVisible(false);
			        		JP_bajaPadre.add(e_BAlum);
			        		JP_bajaPadre.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				int result = JOptionPane.showConfirmDialog(
			                                null, "Confirmar Baja de Tutor"
			                                , "Confirmación", JOptionPane.YES_NO_OPTION);
			        				
			        				if (result == JOptionPane.YES_OPTION) {
			        					String res[] = new String[4];
				        				int row = tb_BAlum.getSelectedRow();
				        				for(int i = 0; i < 4; i++) {
				        					res[i] = (String) tb_BAlum.getValueAt(row, i);
				        				}
				        				
				        				MySQL sql2 = new MySQL();
				        				
				        				try {
				        					sql2.baja_Padre_Num(res[0], res[1], res[2], res[3]);
											//JOptionPane.showMessageDialog(null, "Eliminación exitosa");
				        					bPadre = true;
				        					btnBT.doClick();
										} catch (SQLException e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error en la base de datos",
													"¡Error!", JOptionPane.ERROR_MESSAGE);
										}
				        				
				        				//tfBT.setText("");
				        				System.out.println("Boton eliminar preisonado");
			                	
			        				} else if (result == JOptionPane.NO_OPTION) {
			        					System.out.println(":X");
			        				}
			                	}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			       				
			        JP_bajaPadre.add(scrollPane);
			        JP_bajaPadre.add(tfBT);
			        JP_bajaPadre.add(btnBT);
			        JP_bajaPadre.add(lblBT);
			        
			        if(bPadre) {
			        	bPadre = false;
			        } else {
			        	try {
				        	String test = (String) tb_BAlum.getValueAt(0, 0);
					        System.out.println(test);
				        } catch (ArrayIndexOutOfBoundsException ex) {
				        	JOptionPane.showMessageDialog(null,
									"Su búsqueda no produjo ningún resultado",
									"Verifique los datos", JOptionPane.ERROR_MESSAGE);
				        	tfBT.setText("");
				        }
			        }
								
				// Ahora buscamos pero por nombres
				} else if (Character.isAlphabetic(tx)) {
					String nombre = "";
					String paterno = "";
					String materno = "";
													
					String[] splited = tex.split("\\s+");
										
					if(splited.length == 1) {
						nombre = splited[0];
					} else if(splited.length == 2) {
						nombre = splited[0];
						paterno = splited[1];
					} else if(splited.length == 3) {
						nombre = splited[0];
						paterno = splited[1];
						materno = splited[2];
					} else if(splited.length == 4) {
						nombre = splited[0] + " " + splited[1];
						paterno = splited[2];
						materno = splited[3];
					}
										
					System.out.println(nombre);
					System.out.println(paterno);
					System.out.println(materno);
										
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from padres where "
			            		+ "nombre like '%" + nombre + "%' and paterno like '%" + paterno +
			            		"%' and materno like '%" + materno +"%'";
			            System.out.println(sql);
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Eliminar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_bajaPadre.setVisible(false);
			        		JP_bajaPadre.add(e_BAlum);
			        		JP_bajaPadre.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				int result = JOptionPane.showConfirmDialog(
			                                null, "Confirmar Baja de Tutor"
			                                , "Confirmación", JOptionPane.YES_NO_OPTION);
			        				
			        				if (result == JOptionPane.YES_OPTION) {
			        					String res[] = new String[4];
				        				int row = tb_BAlum.getSelectedRow();
				        				for(int i = 0; i < 4; i++) {
				        					res[i] = (String) tb_BAlum.getValueAt(row, i);
				        				}
				        				
				        				MySQL sql2 = new MySQL();
				        				
				        				try {
				        					sql2.baja_Padre_Num(res[0], res[1], res[2], res[3]);
											//JOptionPane.showMessageDialog(null, "Eliminación exitosa");
				        					bPadre = true;
				        					btnBT.doClick();
										} catch (SQLException e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error en la base de datos",
													"¡Error!", JOptionPane.ERROR_MESSAGE);
										}
				        				
				        				//tfBT.setText("");
				        				System.out.println("Boton eliminar preisonado");
			                	
			        				} else if (result == JOptionPane.NO_OPTION) {
			        					System.out.println(":X");
			        				}
			                	}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			        
			        JP_bajaPadre.add(scrollPane);
			        JP_bajaPadre.add(tfBT);
			        JP_bajaPadre.add(btnBT);
			        JP_bajaPadre.add(lblBT);
			        
			        if(bPadre) {
			        	bPadre = false;
			        } else {
			        	try {
				        	String test = (String) tb_BAlum.getValueAt(0, 0);
					        System.out.println(test);
				        } catch (ArrayIndexOutOfBoundsException ex) {
				        	JOptionPane.showMessageDialog(null,
									"Su búsqueda no produjo ningún resultado",
									"Verifique los datos", JOptionPane.ERROR_MESSAGE);
				        	tfBT.setText("");
				        }
			        }
			    }
			}
			//JP_bajaPadre.add(scrollPane);
	        JP_bajaPadre.add(tfBT);
	        JP_bajaPadre.add(btnBT);
	        JP_bajaPadre.add(lblBT);
	        
	        
			
			
			
		} else if (e.getActionCommand().equals("buscarProfesor")) { // *******************Boton Buscar en Profesor Baja
			System.out.println("Boton buscar profesor presionado");
			JP_bajaProfe.setVisible(false);
			JP_bajaProfe.removeAll();
			JP_bajaProfe.setVisible(true);
			Vector<Object> columnNames = new Vector<Object>();
	        Vector<Object> data = new Vector<Object>();
			String tex = tfBP.getText();
			if(tex.equals("")) {
				JP_bajaProfe.add(tfBP);
				JP_bajaProfe.add(btnBP);
				JP_bajaProfe.add(lblBP);
				JOptionPane.showMessageDialog(null, "Introduzca un nombre o matricula",
						"Mensaje", JOptionPane.ERROR_MESSAGE);
				//JP_bajaPadre.add(scrollPane);
		        
			} else {
				char tx = tex.charAt(0);
				
				// Iniciamos checando si el usuario busco por matricula
				if(Character.isDigit(tx)) { 			
					
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from profesores where clave like '"+ tex + "%'";
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "error en baja profesor..." );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);									// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Eliminar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_bajaProfe.setVisible(false);
			        		JP_bajaProfe.add(e_BAlum);
			        		JP_bajaProfe.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				int result = JOptionPane.showConfirmDialog(
			                                null, "Confirmar Baja de Profesor"
			                                , "Confirmación", JOptionPane.YES_NO_OPTION);
			        				
			        				if (result == JOptionPane.YES_OPTION) {
			        					String res[] = new String[4];
				        				int row = tb_BAlum.getSelectedRow();
				        				for(int i = 0; i < 4; i++) {
				        					res[i] = (String) tb_BAlum.getValueAt(row, i);
				        				}
				        				
				        				MySQL sql2 = new MySQL();
				        				
				        				try {
				        					sql2.baja_Profe_Num(res[0], res[1], res[2], res[3]);
											//JOptionPane.showMessageDialog(null, "Eliminación exitosa");
				        					bProfe = true;
				        					btnBP.doClick();
										} catch (SQLException e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error en la base de datos",
													"¡Error!", JOptionPane.ERROR_MESSAGE);
										}
				        				
				        				//tfBT.setText("");
				        				System.out.println("Boton eliminar preisonado en Bja Profe");
			                	
			        				} else if (result == JOptionPane.NO_OPTION) {
			        					System.out.println(":XX");
			        				}
			                	}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			       				
			        JP_bajaProfe.add(scrollPane);
			        JP_bajaProfe.add(tfBP);
			        JP_bajaProfe.add(btnBP);
			        JP_bajaProfe.add(lblBP);
			        
			        if(bProfe) {
			        	bProfe = false;
			        } else {
			        	try {
				        	String test = (String) tb_BAlum.getValueAt(0, 0);
					        System.out.println(test);
				        } catch (ArrayIndexOutOfBoundsException ex) {
				        	JOptionPane.showMessageDialog(null,
									"Su búsqueda no produjo ningún resultado",
									"Verifique los datos", JOptionPane.ERROR_MESSAGE);
				        	tfBP.setText("");
				        }
			        }
								
				// Ahora buscamos pero por nombres
				} else if (Character.isAlphabetic(tx)) {
					String nombre = "";
					String paterno = "";
					String materno = "";
													
					String[] splited = tex.split("\\s+");
										
					if(splited.length == 1) {
						nombre = splited[0];
					} else if(splited.length == 2) {
						nombre = splited[0];
						paterno = splited[1];
					} else if(splited.length == 3) {
						nombre = splited[0];
						paterno = splited[1];
						materno = splited[2];
					} else if(splited.length == 4) {
						nombre = splited[0] + " " + splited[1];
						paterno = splited[2];
						materno = splited[3];
					}
										
					System.out.println(nombre);
					System.out.println(paterno);
					System.out.println(materno);
										
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from profesores where "
			            		+ "nombre like '%" + nombre + "%' and paterno like '%" + paterno +
			            		"%' and materno like '%" + materno +"%'";
			            System.out.println(sql);
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Eliminar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_bajaProfe.setVisible(false);
			        		JP_bajaProfe.add(e_BAlum);
			        		JP_bajaProfe.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				int result = JOptionPane.showConfirmDialog(
			                                null, "Confirmar Baja de Profesor"
			                                , "Confirmación", JOptionPane.YES_NO_OPTION);
			        				
			        				if (result == JOptionPane.YES_OPTION) {
			        					String res[] = new String[4];
				        				int row = tb_BAlum.getSelectedRow();
				        				for(int i = 0; i < 4; i++) {
				        					res[i] = (String) tb_BAlum.getValueAt(row, i);
				        				}
				        				
				        				MySQL sql2 = new MySQL();
				        				
				        				try {
				        					sql2.baja_Profe_Num(res[0], res[1], res[2], res[3]);
											//JOptionPane.showMessageDialog(null, "Eliminación exitosa");
				        					bProfe = true;
				        					btnBP.doClick();
										} catch (SQLException e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error en la base de datos",
													"¡Error!", JOptionPane.ERROR_MESSAGE);
										}
				        				
				        				//tfBT.setText("");
				        				System.out.println("Boton eliminar preisonado en baja Profe :D");
			                	
			        				} else if (result == JOptionPane.NO_OPTION) {
			        					System.out.println(":D");
			        				}
			                	}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			        
			        JP_bajaProfe.add(scrollPane);
			        JP_bajaProfe.add(tfBP);
			        JP_bajaProfe.add(btnBP);
			        JP_bajaProfe.add(lblBP);
			        
			        if(bProfe) {
			        	bProfe = false;
			        } else {
			        	try {
				        	String test = (String) tb_BAlum.getValueAt(0, 0);
					        System.out.println(test);
				        } catch (ArrayIndexOutOfBoundsException ex) {
				        	JOptionPane.showMessageDialog(null,
									"Su búsqueda no produjo ningún resultado",
									"Verifique los datos", JOptionPane.ERROR_MESSAGE);
				        	tfBP.setText("");
				        }
			        }
			    }
			}
			//JP_bajaPadre.add(scrollPane);
			JP_bajaProfe.add(tfBP);
			JP_bajaProfe.add(btnBP);
			JP_bajaProfe.add(lblBP);
	    
		} else if (e.getActionCommand().equals("buscarAdmin")) { // *******************Boton Buscar en Administrador Baja
			System.out.println("Boton buscar administrador presionado");
			JP_bajaAdmin.setVisible(false);
			JP_bajaAdmin.removeAll();
			JP_bajaAdmin.setVisible(true);
			Vector<Object> columnNames = new Vector<Object>();
	        Vector<Object> data = new Vector<Object>();
			String tex = tfBA.getText();
			if(tex.equals("")) {
				JP_bajaAdmin.add(tfBA);
				JP_bajaAdmin.add(btnBA);
				JP_bajaAdmin.add(lblBA);
				JOptionPane.showMessageDialog(null, "Introduzca un nombre o matricula",
						"Mensaje", JOptionPane.ERROR_MESSAGE);
				//JP_bajaPadre.add(scrollPane);
		        
			} else {
				char tx = tex.charAt(0);
				
				// Iniciamos checando si el usuario busco por matricula
				if(Character.isDigit(tx)) { 			
					
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from administrador where clave like '" + tex + "%'";
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "error en baja profesor..." );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);									// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Eliminar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_bajaAdmin.setVisible(false);
			        		JP_bajaAdmin.add(e_BAlum);
			        		JP_bajaAdmin.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				int result = JOptionPane.showConfirmDialog(
			                                null, "Confirmar Baja de Administrador"
			                                , "Confirmación", JOptionPane.YES_NO_OPTION);
			        				
			        				if (result == JOptionPane.YES_OPTION) {
			        					String res[] = new String[4];
				        				int row = tb_BAlum.getSelectedRow();
				        				for(int i = 0; i < 4; i++) {
				        					res[i] = (String) tb_BAlum.getValueAt(row, i);
				        				}
				        				
				        				MySQL sql2 = new MySQL();
				        				
				        				try {
				        					sql2.baja_Admin_Num(res[0], res[1], res[2], res[3]);
											//JOptionPane.showMessageDialog(null, "Eliminación exitosa");
				        					bAdmin = true;
				        					btnBA.doClick();
										} catch (SQLException e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error en la base de datos",
													"¡Error!", JOptionPane.ERROR_MESSAGE);
										}
				        				
				        				//tfBT.setText("");
				        				System.out.println("Boton eliminar preisonado en Bja Admin XD");
			                	
			        				} else if (result == JOptionPane.NO_OPTION) {
			        					System.out.println(":-)");
			        				}
			                	}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			       				
			        JP_bajaAdmin.add(scrollPane);
			        JP_bajaAdmin.add(tfBA);
			        JP_bajaAdmin.add(btnBA);
			        JP_bajaAdmin.add(lblBA);
			        
			        if(bAdmin) {
			        	bAdmin = false;
			        } else {
			        	try {
				        	String test = (String) tb_BAlum.getValueAt(0, 0);
					        System.out.println(test);
				        } catch (ArrayIndexOutOfBoundsException ex) {
				        	JOptionPane.showMessageDialog(null,
									"Su búsqueda no produjo ningún resultado",
									"Verifique los datos", JOptionPane.ERROR_MESSAGE);
				        	tfBA.setText("");
				        }
			        }
								
				// Ahora buscamos pero por nombres
				} else if (Character.isAlphabetic(tx)) {
					String nombre = "";
					String paterno = "";
					String materno = "";
													
					String[] splited = tex.split("\\s+");
										
					if(splited.length == 1) {
						nombre = splited[0];
					} else if(splited.length == 2) {
						nombre = splited[0];
						paterno = splited[1];
					} else if(splited.length == 3) {
						nombre = splited[0];
						paterno = splited[1];
						materno = splited[2];
					} else if(splited.length == 4) {
						nombre = splited[0] + " " + splited[1];
						paterno = splited[2];
						materno = splited[3];
					}
										
					System.out.println(nombre);
					System.out.println(paterno);
					System.out.println(materno);
										
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from administrador where "
			            		+ "nombre like '%" + nombre + "%' and paterno like '%" + paterno +
			            		"%' and materno like '%" + materno +"%'";
			            System.out.println(sql);
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Eliminar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_bajaAdmin.setVisible(false);
			        		JP_bajaAdmin.add(e_BAlum);
			        		JP_bajaAdmin.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				int result = JOptionPane.showConfirmDialog(
			                                null, "Confirmar Baja de Administrador"
			                                , "Confirmación", JOptionPane.YES_NO_OPTION);
			        				
			        				if (result == JOptionPane.YES_OPTION) {
			        					String res[] = new String[4];
				        				int row = tb_BAlum.getSelectedRow();
				        				for(int i = 0; i < 4; i++) {
				        					res[i] = (String) tb_BAlum.getValueAt(row, i);
				        				}
				        				
				        				MySQL sql2 = new MySQL();
				        				
				        				try {
				        					sql2.baja_Admin_Num(res[0], res[1], res[2], res[3]);
											//JOptionPane.showMessageDialog(null, "Eliminación exitosa");
				        					bAdmin = true;
				        					btnBA.doClick();
										} catch (SQLException e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error en la base de datos",
													"¡Error!", JOptionPane.ERROR_MESSAGE);;
										}
				        				
				        				//tfBT.setText("");
				        				System.out.println("Boton eliminar preisonado en baja Admin :D");
			                	
			        				} else if (result == JOptionPane.NO_OPTION) {
			        					System.out.println(":D");
			        				}
			                	}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			        
			        JP_bajaAdmin.add(scrollPane);
			        JP_bajaAdmin.add(tfBA);
			        JP_bajaAdmin.add(btnBA);
			        JP_bajaAdmin.add(lblBA);
			        
			        if(bAdmin) {
			        	bAdmin = false;
			        } else {
			        	try {
				        	String test = (String) tb_BAlum.getValueAt(0, 0);
					        System.out.println(test);
				        } catch (ArrayIndexOutOfBoundsException ex) {
				        	JOptionPane.showMessageDialog(null,
									"Su búsqueda no produjo ningún resultado",
									"Verifique los datos", JOptionPane.ERROR_MESSAGE);
				        	tfBA.setText("");
				        }
			        }
			    }
			}
			//JP_bajaPadre.add(scrollPane);
			JP_bajaAdmin.add(tfBA);
			JP_bajaAdmin.add(btnBA);
			JP_bajaAdmin.add(lblBA);
	    
		}  else if (e.getActionCommand().equals("buscModAlum")) { // *******************Boton "Buscar" en modificar Alumno
			System.out.println("Boton buscar alumno presionado");
			JP_modAlumno.setVisible(false);
			JP_modAlumno.removeAll();
			JP_modAlumno.setVisible(true);
			String grado = (String) cb_M1.getSelectedItem();
			String grupo = (String) cb_M2.getSelectedItem();
			Vector<Object> columnNames = new Vector<Object>();
	        Vector<Object> data = new Vector<Object>();
	        
	        // Extraccion de la informacion que sera utilizada en la Tabla
	        try {
	        	String url = "jdbc:mysql://localhost/system";
	            String userid = "root";
	            String password = "toor";
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection connection = DriverManager.getConnection( url, userid, password );

	            // Creacion del query a ejecutar
	            String sql = "select paterno, materno, nombre, "
	            		+ "grado, grupo from alumnos where grado='"
	            		+grado+"' and grupo='"+grupo+"' order by paterno";
	            
	            // Ejecucion del query anterior
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery( sql );
	            ResultSetMetaData md = rs.getMetaData();
	            int columns = md.getColumnCount();

	            // Obtiene los nombres de las columnas
	            for (int i = 1; i <= columns; i++) {
	                columnNames.addElement( md.getColumnName(i) );
	            }

	            // Obtiene la informacion de las filas
	            while (rs.next()) {
	            	Vector<Object> row = new Vector<Object>(columns);

	                for (int i = 1; i <= columns; i++) {
	                    row.addElement( rs.getObject(i) );
	                }

	                data.addElement( row );
	            }

	            rs.close();
	            stmt.close();
	            connection.close();
	        }
	        catch(Exception ex) {
	        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
						"¡Error!", JOptionPane.ERROR_MESSAGE);
	            System.out.println( ex + "   :D" );
	        }

	        //  Creamos la tabla con informacion de la BD
	        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
	        	@Override
	            public Class getColumnClass(int column) {
	                for (int row = 0; row < getRowCount(); row++) {
	                    Object o = getValueAt(row, column);

	                    if (o != null) {
	                        return o.getClass();
	                    }
	                }

	                return Object.class;
	            }
	        };

			tb_BAlum = new JTable(model);										// Inicializamos la tabla
			tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
			JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
	        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
	             
	        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
	        
	        lsmRow.addListSelectionListener(new ListSelectionListener() {
	        	@Override
	        	public void valueChanged(ListSelectionEvent le) {
	        		JButton e_BAlum = new JButton("Editar");
	        		e_BAlum.setBounds(125, 278, 100, 25);
	        		JP_modAlumno.setVisible(false);
	        		JP_modAlumno.add(e_BAlum);
	        		JP_modAlumno.setVisible(true);
	        		
	        		e_BAlum.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				String res[] = new String[5];
	        				int row = tb_BAlum.getSelectedRow();
	        				for(int i = 0; i < 5; i++) {
	        					res[i] = (String) tb_BAlum.getValueAt(row, i);
	        				}
	        				
	        				MySQL sql2 = new MySQL();
	        				try {
								ModAlumno ma = new ModAlumno(sql2.id_alumno(res[0], res[1], res[2], res[3], res[4]));
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Error en la base de datos",
										"¡Error!", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
	        				
	        				
	        			}
	        				
	        		});
	        		System.out.println("tabla seleccionada");
	        	}
	        });
	        
	        JP_modAlumno.add(scrollPane);
	        JP_modAlumno.add(lbl_M2);
	        JP_modAlumno.add(cb_M1);
	        JP_modAlumno.add(cb_M2);
	        JP_modAlumno.add(modAlum);
	        JP_modAlumno.add(lbl_M1);
	        JP_modAlumno.add(lbl_M3);
	        
	        if(bAlumno) {
	        	bAlumno = false;
	        } else {
	        	try {
		        	String test = (String) tb_BAlum.getValueAt(0, 0);
			        System.out.println(test);
		        } catch (ArrayIndexOutOfBoundsException ex) {
		        	JOptionPane.showMessageDialog(null,
							"Su búsqueda no produjo ningún resultado",
							"Verifique los datos", JOptionPane.ERROR_MESSAGE);
		        }
	        }
		} else if(e.getActionCommand().equals("buscModTutor")) { // *******************Boton "Buscar" en modificar Tutor
			System.out.println("Boton modificar padre presionado");
			JP_modPadre.setVisible(false);
			JP_modPadre.removeAll();
			JP_modPadre.setVisible(true);
			Vector<Object> columnNames = new Vector<Object>();
	        Vector<Object> data = new Vector<Object>();
			String tex = tfMT.getText();
			if(tex.equals("")) {
				JP_modPadre.add(tfMT);
				JP_modPadre.add(btnMT);
				JP_modPadre.add(lblMT);
				JOptionPane.showMessageDialog(null, "Introduzca un nombre o matricula",
						"Mensaje", JOptionPane.ERROR_MESSAGE);
				//JP_bajaPadre.add(scrollPane);
		        
			} else {
				char tx = tex.charAt(0);
				
				// Iniciamos checando si el usuario busco por matricula
				if(Character.isDigit(tx)) { 			
					
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from padres where clave like '%"+ tex + "%'";
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Editar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_modPadre.setVisible(false);
			        		JP_modPadre.add(e_BAlum);
			        		JP_modPadre.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				String res[] = new String[4];
			        				int row = tb_BAlum.getSelectedRow();
			        				for(int i = 0; i < 4; i++) {
			        					res[i] = (String) tb_BAlum.getValueAt(row, i);
			        				}
			        				MySQL sq = new MySQL();
			        				try {
										ModTutor ma = new ModTutor(sq.id_padre(res[0], res[1], res[2], res[3]));
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "Error en la base de datos",
												"¡Error!", JOptionPane.ERROR_MESSAGE);
										e1.printStackTrace();
									}
			        				
			        				
			                	}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			       				
			        JP_modPadre.add(scrollPane);
			        JP_modPadre.add(tfMT);
			        JP_modPadre.add(btnMT);
			        JP_modPadre.add(lblMT);
			    							
				// Ahora buscamos pero por nombres
				} else if (Character.isAlphabetic(tx)) {
					String nombre = "";
					String paterno = "";
					String materno = "";
													
					String[] splited = tex.split("\\s+");
										
					if(splited.length == 1) {
						nombre = splited[0];
					} else if(splited.length == 2) {
						nombre = splited[0];
						paterno = splited[1];
					} else if(splited.length == 3) {
						nombre = splited[0];
						paterno = splited[1];
						materno = splited[2];
					} else if(splited.length == 4) {
						nombre = splited[0] + " " + splited[1];
						paterno = splited[2];
						materno = splited[3];
					}
										
					System.out.println(nombre + " " + paterno + " " + materno);
					
										
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from padres where "
			            		+ "nombre like '%" + nombre + "%' and paterno like '%" + paterno +
			            		"%' and materno like '%" + materno +"%'";
			            System.out.println(sql);
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Editar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_modPadre.setVisible(false);
			        		JP_modPadre.add(e_BAlum);
			        		JP_modPadre.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				String res[] = new String[4];
			        				int row = tb_BAlum.getSelectedRow();
			        				for(int i = 0; i < 4; i++) {
			        					res[i] = (String) tb_BAlum.getValueAt(row, i);
			        				}
			        				MySQL sq = new MySQL();
			        				try {
										ModTutor ma = new ModTutor(sq.id_padre(res[0], res[1], res[2], res[3]));
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "Error en la base de datos",
												"¡Error!", JOptionPane.ERROR_MESSAGE);
										e1.printStackTrace();
									}
			        			}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			        
			        JP_modPadre.add(scrollPane);
			        JP_modPadre.add(tfMT);
			        JP_modPadre.add(btnMT);
			        JP_modPadre.add(lblMT);
			        
			        
			    }
			}
			//JP_bajaPadre.add(scrollPane);
	        JP_modPadre.add(tfMT);
	        JP_modPadre.add(btnMT);
	        JP_modPadre.add(lblMT);
	        
	        try {
	        	String test = (String) tb_BAlum.getValueAt(0, 0);
		        System.out.println(test);
	        } catch (ArrayIndexOutOfBoundsException ex) {
	        	JOptionPane.showMessageDialog(null,
						"Su búsqueda no produjo ningún resultado",
						"Verifique los datos", JOptionPane.ERROR_MESSAGE);
	        	tfMT.setText("");
	        }
		
		} else if(e.getActionCommand().equals("buscModProfe")) { // ************* Lo que hara al presionarse el boton buscar en Modificar profesor
			System.out.println("boton modificar profesor presionado");
			JP_modProfe.setVisible(false);
			JP_modProfe.removeAll();
			JP_modProfe.setVisible(true);
			Vector<Object> columnNames = new Vector<Object>();
	        Vector<Object> data = new Vector<Object>();
			String tex = tfMP.getText();
			if(tex.equals("")) {
				JP_modProfe.add(tfMP);
				JP_modProfe.add(btnMP);
				JP_modProfe.add(lblMP);
				JOptionPane.showMessageDialog(null, "Introduzca un nombre o matricula",
						"Mensaje", JOptionPane.ERROR_MESSAGE);
				//JP_bajaPadre.add(scrollPane);
		        
			} else {
				char tx = tex.charAt(0);
				
				// Iniciamos checando si el usuario busco por matricula
				if(Character.isDigit(tx)) { 			
					
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from profesores where clave like '%"+ tex + "%'";
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Editar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_modProfe.setVisible(false);
			        		JP_modProfe.add(e_BAlum);
			        		JP_modProfe.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				String res[] = new String[4];
			        				int row = tb_BAlum.getSelectedRow();
			        				for(int i = 0; i < 4; i++) {
			        					res[i] = (String) tb_BAlum.getValueAt(row, i);
			        				}
			        				MySQL sq = new MySQL();
			        				try {
			        					System.out.println(sq.id_admin(res[0], res[1], res[2], res[3]));
			        					ModProfe ma = new ModProfe(sq.id_admin(res[0], res[1], res[2], res[3]));
			        					
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "Error en la base de datos",
												"¡Error!", JOptionPane.ERROR_MESSAGE);
										e1.printStackTrace();
									}
			        				
			        				
			                	}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			       				
			        JP_modProfe.add(scrollPane);
			        JP_modProfe.add(tfMP);
			        JP_modProfe.add(btnMP);
			        JP_modProfe.add(lblMP);
			    								
				// Ahora buscamos pero por nombres
				} else if (Character.isAlphabetic(tx)) {
					String nombre = "";
					String paterno = "";
					String materno = "";
													
					String[] splited = tex.split("\\s+");
										
					if(splited.length == 1) {
						nombre = splited[0];
					} else if(splited.length == 2) {
						nombre = splited[0];
						paterno = splited[1];
					} else if(splited.length == 3) {
						nombre = splited[0];
						paterno = splited[1];
						materno = splited[2];
					} else if(splited.length == 4) {
						nombre = splited[0] + " " + splited[1];
						paterno = splited[2];
						materno = splited[3];
					}
										
					System.out.println(nombre + " " + paterno + " " + materno);
					
										
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from profesores where "
			            		+ "nombre like '%" + nombre + "%' and paterno like '%" + paterno +
			            		"%' and materno like '%" + materno +"%'";
			            System.out.println(sql);
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Editar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_modProfe.setVisible(false);
			        		JP_modProfe.add(e_BAlum);
			        		JP_modProfe.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				String res[] = new String[4];
			        				int row = tb_BAlum.getSelectedRow();
			        				for(int i = 0; i < 4; i++) {
			        					res[i] = (String) tb_BAlum.getValueAt(row, i);
			        				}
			        				MySQL sq = new MySQL();
			        				try {
										ModProfe ma = new ModProfe(sq.id_profe(res[0], res[1], res[2], res[3]));
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "Error en la base de datos",
												"¡Error!", JOptionPane.ERROR_MESSAGE);
										e1.printStackTrace();
									}
			        			}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			        
			        JP_modProfe.add(scrollPane);
			        JP_modProfe.add(tfMP);
			        JP_modProfe.add(btnMP);
			        JP_modProfe.add(lblMP);
			        
			        
			    }
			}
			//JP_bajaPadre.add(scrollPane);
	        JP_modProfe.add(tfMP);
	        JP_modProfe.add(btnMP);
	        JP_modProfe.add(lblMP);
	        
	        try {
	        	String test = (String) tb_BAlum.getValueAt(0, 0);
		        System.out.println(test);
	        } catch (ArrayIndexOutOfBoundsException ex) {
	        	JOptionPane.showMessageDialog(null,
						"Su búsqueda no produjo ningún resultado",
						"Verifique los datos", JOptionPane.ERROR_MESSAGE);
	        	tfMP.setText("");
	        }
			
			
		} else if(e.getActionCommand().equals("buscModAdmin")) { // ************* Lo que hara al presionarse el boton buscar en Modificar administrador
			System.out.println("boton modificar administrador presionado");
			JP_modAdmin.setVisible(false);
			JP_modAdmin.removeAll();
			JP_modAdmin.setVisible(true);
			Vector<Object> columnNames = new Vector<Object>();
	        Vector<Object> data = new Vector<Object>();
			String tex = tfMA.getText();
			if(tex.equals("")) {
				JP_modAdmin.add(tfMA);
				JP_modAdmin.add(btnMA);
				JP_modAdmin.add(lblMA);
				JOptionPane.showMessageDialog(null, "Introduzca un nombre o matricula",
						"Mensaje", JOptionPane.ERROR_MESSAGE);
				//JP_bajaPadre.add(scrollPane);
		        
			} else {
				char tx = tex.charAt(0);
				
				// Iniciamos checando si el usuario busco por matricula
				if(Character.isDigit(tx)) { 			
					
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from administrador where clave like '%"+ tex + "%'";
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Editar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_modAdmin.setVisible(false);
			        		JP_modAdmin.add(e_BAlum);
			        		JP_modAdmin.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				String res[] = new String[4];
			        				int row = tb_BAlum.getSelectedRow();
			        				for(int i = 0; i < 4; i++) {
			        					res[i] = (String) tb_BAlum.getValueAt(row, i);
			        				}
			        				MySQL sq = new MySQL();
			        				try {
			        					ModAdmin ma = new ModAdmin(sq.id_admin(res[0], res[1], res[2], res[3]));
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "Error en la base de datos",
												"¡Error!", JOptionPane.ERROR_MESSAGE);
										e1.printStackTrace();
									}
			        				
			        				
			                	}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			       				
			        JP_modAdmin.add(scrollPane);
			        JP_modAdmin.add(tfMA);
			        JP_modAdmin.add(btnMA);
			        JP_modAdmin.add(lblMA);
			    								
				// Ahora buscamos pero por nombres
				} else if (Character.isAlphabetic(tx)) {
					String nombre = "";
					String paterno = "";
					String materno = "";
													
					String[] splited = tex.split("\\s+");
										
					if(splited.length == 1) {
						nombre = splited[0];
					} else if(splited.length == 2) {
						nombre = splited[0];
						paterno = splited[1];
					} else if(splited.length == 3) {
						nombre = splited[0];
						paterno = splited[1];
						materno = splited[2];
					} else if(splited.length == 4) {
						nombre = splited[0] + " " + splited[1];
						paterno = splited[2];
						materno = splited[3];
					}
										
					System.out.println(nombre + " " + paterno + " " + materno);
					
										
					// Extraccion de la informacion que sera utilizada en la Tabla
					try {
			        	String url = "jdbc:mysql://localhost/system";
			            String userid = "root";
			            String password = "toor";
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection connection = DriverManager.getConnection( url, userid, password );

			            // Creacion del query a ejecutar
			            String sql = "select nombre, paterno, materno, clave from administrador where "
			            		+ "nombre like '%" + nombre + "%' and paterno like '%" + paterno +
			            		"%' and materno like '%" + materno +"%'";
			            System.out.println(sql);
			            
			            // Ejecucion del query anterior
			            Statement stmt = connection.createStatement();
			            ResultSet rs = stmt.executeQuery( sql );
			            ResultSetMetaData md = rs.getMetaData();
			            int columns = md.getColumnCount();

			            // Obtiene los nombres de las columnas
			            for (int i = 1; i <= columns; i++) {
			                columnNames.addElement( md.getColumnName(i) );
			            }

			            // Obtiene la informacion de las filas
			            while (rs.next()) {
			            	Vector<Object> row = new Vector<Object>(columns);

			                for (int i = 1; i <= columns; i++) {
			                    row.addElement( rs.getObject(i) );
			                }

			                data.addElement( row );
			            }

			            rs.close();
			            stmt.close();
			            connection.close();
			        }
			        catch(Exception ex) {
			        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
								"¡Error!", JOptionPane.ERROR_MESSAGE);
			            System.out.println( ex + "XD" );
			        }
					
					//  Creamos la tabla con informacion de la BD
			        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			        	@Override
			            public Class getColumnClass(int column) {
			                for (int row = 0; row < getRowCount(); row++) {
			                    Object o = getValueAt(row, column);

			                    if (o != null) {
			                        return o.getClass();
			                    }
			                }

			                return Object.class;
			            }
			        };

					tb_BAlum = new JTable(model);										// Creacion de tabla con info de BD
					tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
					JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
					tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(80);
			        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
			        
			        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
			        
			        lsmRow.addListSelectionListener(new ListSelectionListener() {
			        	@Override
			        	public void valueChanged(ListSelectionEvent le) {
			        		JButton e_BAlum = new JButton("Editar");
			        		e_BAlum.setBounds(125, 278, 100, 25);
			        		JP_modAdmin.setVisible(false);
			        		JP_modAdmin.add(e_BAlum);
			        		JP_modAdmin.setVisible(true);
			        		
			        		e_BAlum.addActionListener(new ActionListener() {
			        			public void actionPerformed(ActionEvent e) {
			        				String res[] = new String[4];
			        				int row = tb_BAlum.getSelectedRow();
			        				for(int i = 0; i < 4; i++) {
			        					res[i] = (String) tb_BAlum.getValueAt(row, i);
			        				}
			        				MySQL sq = new MySQL();
			        				try {
										ModAdmin ma = new ModAdmin(sq.id_admin(res[0], res[1], res[2], res[3]));
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "Error en la base de datos",
												"¡Error!", JOptionPane.ERROR_MESSAGE);
										e1.printStackTrace();
									}
			        			}
			        		});
			        		System.out.println("tabla seleccionada");
			        	}
			        });
			        
			        JP_modAdmin.add(scrollPane);
			        JP_modAdmin.add(tfMA);
			        JP_modAdmin.add(btnMA);
			        JP_modAdmin.add(lblMA);
			        
			        
			    }
			}
			//JP_bajaPadre.add(scrollPane);
			JP_modAdmin.add(tfMA);
			JP_modAdmin.add(btnMA);
			JP_modAdmin.add(lblMA);
			
			try {
	        	String test = (String) tb_BAlum.getValueAt(0, 0);
		        System.out.println(test);
	        } catch (ArrayIndexOutOfBoundsException ex) {
	        	JOptionPane.showMessageDialog(null,
						"Su búsqueda no produjo ningún resultado",
						"Verifique los datos", JOptionPane.ERROR_MESSAGE);
	        	tfMA.setText("");
	        }
			
			
		} else if (e.getActionCommand().equals("Consultar")) { // *******************Boton CoNSuLtA
			System.out.println("Boton Consulta presionado");
			jp_Consulta.setVisible(false);
			jp_Consulta.removeAll();
			jp_Consulta.setVisible(true);
			String grado = (String) cb_1C.getSelectedItem();
			String grupo = (String) cb_2C.getSelectedItem();
			Vector<Object> columnNames = new Vector<Object>();
	        Vector<Object> data = new Vector<Object>();
	        
	        // Extraccion de la informacion que sera utilizada en la Tabla
	        try {
	        	String url = "jdbc:mysql://localhost/system";
	            String userid = "root";
	            String password = "toor";
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection connection = DriverManager.getConnection( url, userid, password );

	            // Creacion del query a ejecutar
	            String sql = "select paterno, materno, nombre, "
	            		+ "grado, grupo from alumnos where grado='"
	            		+grado+"' and grupo='"+grupo+"' order by paterno";
	            
	            // Ejecucion del query anterior
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery( sql );
	            ResultSetMetaData md = rs.getMetaData();
	            int columns = md.getColumnCount();

	            // Obtiene los nombres de las columnas
	            for (int i = 1; i <= columns; i++) {
	                columnNames.addElement( md.getColumnName(i) );
	            }

	            // Obtiene la informacion de las filas
	            while (rs.next()) {
	            	Vector<Object> row = new Vector<Object>(columns);

	                for (int i = 1; i <= columns; i++) {
	                    row.addElement( rs.getObject(i) );
	                }

	                data.addElement( row );
	            }

	            rs.close();
	            stmt.close();
	            connection.close();
	        }
	        catch(Exception ex) {
	        	JOptionPane.showMessageDialog(null, "Error en la base de datos",
						"¡Error!", JOptionPane.ERROR_MESSAGE);
	            System.out.println( ex + "   :D" );
	        }

	        //  Creamos la tabla con informacion de la BD
	        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
	        	@Override
	            public Class getColumnClass(int column) {
	                for (int row = 0; row < getRowCount(); row++) {
	                    Object o = getValueAt(row, column);

	                    if (o != null) {
	                        return o.getClass();
	                    }
	                }

	                return Object.class;
	            }
	        };

			tb_BAlum = new JTable(model);										// Inicializamos la tabla
			tb_BAlum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		// Limitamos la seleccion a una fila
			JScrollPane scrollPane = new JScrollPane(tb_BAlum);					// Creamos el panel con barras de desplazamiento y el aderimos la tabla
	        scrollPane.setBounds(10, 70, 330, 200);								// Medidas del panel anterior
	             
	        ListSelectionModel lsmRow = tb_BAlum.getSelectionModel();			// Escucha eventos en la tabla
	        
	        lsmRow.addListSelectionListener(new ListSelectionListener() {
	        	@Override
	        	public void valueChanged(ListSelectionEvent le) {
	        		JButton e_BAlum = new JButton("Consultar");
	        		e_BAlum.setBounds(115, 278, 120, 25);
	        		jp_Consulta.setVisible(false);
	        		jp_Consulta.add(e_BAlum);
	        		jp_Consulta.setVisible(true);
	        		
	        		e_BAlum.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				String res[] = new String[5];
	        				int row = tb_BAlum.getSelectedRow();
	        				for(int i = 0; i < 5; i++) {
	        					res[i] = (String) tb_BAlum.getValueAt(row, i);
	        				}
	        				
	        				MySQL sql2 = new MySQL();
	        				try {
								Consulta c = new Consulta(sql2.alumMatri(res[0], res[1], res[2], res[3], res[4]));
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Error en la base de datos",
										"¡Error!", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
	        				
	        				
	        			}
	        				
	        		});
	        		System.out.println("tabla seleccionada");
	        	}
	        });
	        
	        jp_Consulta.add(scrollPane);
	        jp_Consulta.add(cb_1C);
	        jp_Consulta.add(cb_2C);
	        jp_Consulta.add(bajaAlumC);
	        jp_Consulta.add(lbl_1C);
	        jp_Consulta.add(lbl_2C);
	        jp_Consulta.add(lbl_3C);
	        
	        if(c_Alumno) {
	        	c_Alumno = false;
	        } else {
	        	try {
		        	String test = (String) tb_BAlum.getValueAt(0, 0);
			        System.out.println(test);
		        } catch (ArrayIndexOutOfBoundsException ex) {
		        	JOptionPane.showMessageDialog(null,
							"Su búsqueda no produjo ningún resultado",
							"Verifique los datos", JOptionPane.ERROR_MESSAGE);
		        }
	        }
		}
		
	}
		
	public static void main(String[] args) {
		
		/*try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException |InstantiationException |
				IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.out.println("checar look and feel....");
		}*/
			
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
	        public void run() {
				A2 frame = new A2();
	        }
		});
	}  
}

