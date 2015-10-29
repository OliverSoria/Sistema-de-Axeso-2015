
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ModAdmin extends JFrame implements ActionListener {
	int id;
	JFrame ventanaMA;
	private JTextField tfD1;
	private JTextField tfD2;
	private JTextField tfD3;
	private JPasswordField tfD4;
	private JButton btnD1;
	private JButton btnD2;
	
	ModAdmin(int id) throws SQLException {
		this.id = id;
		iniciaComponentes(id);
	}
	
	void iniciaComponentes(int id)throws SQLException {
		MySQL mAsql = new MySQL();
		ventanaMA = new JFrame("Modificar Administrador");
		ventanaMA.setLayout(null);
		ventanaMA.setResizable(false);
		ventanaMA.setSize(350, 192);
		ventanaMA.setVisible(true);
		ventanaMA.setLocationRelativeTo(null);
		ventanaMA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Inicializacion de etiquetas
		JLabel lblD2 = new JLabel("Apellido paterno:", SwingConstants.RIGHT);
		JLabel lblD3 = new JLabel("Apellido materno:", SwingConstants.RIGHT);
		JLabel lblD4 = new JLabel("Nombre(s):", SwingConstants.RIGHT);
		JLabel lblD5 = new JLabel("Tarjeta:", SwingConstants.RIGHT);
		
		// Inicializacion de campos
		tfD1 = new JTextField();
		tfD2 = new JTextField();
		tfD3 = new JTextField();
		tfD4 = new JPasswordField();
		
		// Aderimos los action listener a los campos
		tfD1.addActionListener(this);
		tfD2.addActionListener(this);
		tfD3.addActionListener(this);
		tfD4.addActionListener(this);
		
		// Rellenamos los campos con la info correspondiente
		tfD1.setText(mAsql.paterAdminCons(id));
		tfD2.setText(mAsql.materAdminCons(id));
		tfD3.setText(mAsql.nomAdminCons(id));
		tfD4.setText(mAsql.claAdminCons(id));
		
		// Inicializacion de los botones
		btnD1 = new JButton("Cancelar");
		btnD2 = new JButton("Aceptar");
		
		// Aderimos los action listener
		btnD1.addActionListener(this);
		btnD2.addActionListener(this);
		
		// Medidas de las etiquetas 
		lblD2.setBounds(35, 10, 123, 15);
		lblD3.setBounds(32, 40, 126, 15);
		lblD4.setBounds(80, 70, 78, 15);
		lblD5.setBounds(102, 100, 56, 15);
		
		// Meidas de los campos
		tfD1.setBounds(163, 8, 128, 20);
		tfD2.setBounds(163, 38, 128, 20);
		tfD3.setBounds(163, 68, 128, 20);
		tfD4.setBounds(163, 98, 128, 20);
		
		// Medidas de los botones
		btnD1.setBounds(58, 128, 100, 25);
		btnD2.setBounds(185, 128, 100, 25);
		
		// Aderimos las etiquetas
		ventanaMA.add(lblD2);
		ventanaMA.add(lblD3);
		ventanaMA.add(lblD4);
		ventanaMA.add(lblD5);
		
		// Aderimos los campos de texto
		ventanaMA.add(tfD1);
		ventanaMA.add(tfD2);
		ventanaMA.add(tfD3);
		ventanaMA.add(tfD4);
		
		// Aderimos los botones
		ventanaMA.add(btnD1);
		ventanaMA.add(btnD2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancelar")) {
			ventanaMA.dispose();
		}  else if(e.getActionCommand().equals("Aceptar")) {
			MySQL mda = new MySQL();
			String paterno = tfD1.getText();
			String materno = tfD2.getText();
			String nombre = tfD3.getText();
			String clave = tfD4.getText();
			
			try {
				mda.modAdmin(id, paterno, materno, nombre, clave);
				System.out.println("Modificacion hecha en Administrador");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,
						"Error en Base de Datos",
						"¡Error!", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
			A2.tfMA.setText(clave);
			A2.btnMA.doClick();
			A2.tfMA.setText("");
			ventanaMA.dispose();
		}
	}
	
	public static void main(String[] args)  {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new ModAdmin(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
	}
}
