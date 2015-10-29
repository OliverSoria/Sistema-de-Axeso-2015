
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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultStyledDocument;


public class ModAlumno extends JFrame implements ActionListener{
	int id;
	JFrame ventanaMA;
	private JTextField tfA1;
	private JTextField tfA2;
	private JTextField tfA3;
	private JTextField tfA4;
	private JPasswordField tfA5;
	private JButton btnA1;
	private JButton btnA2;
	private JButton btnA3;
	private JComboBox cbA1;
	private JComboBox cbA2;
	private JComboBox cbA3;
	private JFileChooser jfc; // Selector de Archivos
	
	ModAlumno(int id) throws SQLException {
		this.id = id;
		iniciaComponentes(id);
	}

	private void iniciaComponentes(int id) throws SQLException {
		MySQL mAsql = new MySQL();
		
		ventanaMA = new JFrame("Modificar Alumno");
		ventanaMA.setLayout(null);
		ventanaMA.setResizable(false);
		ventanaMA.setSize(350, 310);
		ventanaMA.setVisible(true);
		ventanaMA.setLocationRelativeTo(null);
		ventanaMA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		JLabel lblA1 = new JLabel("MODIFICAR ALUMNO", SwingConstants.CENTER);
		JLabel lblA2 = new JLabel("Apellido paterno:", SwingConstants.RIGHT);
		JLabel lblA3 = new JLabel("Apellido materno:", SwingConstants.RIGHT);
		JLabel lblA4 = new JLabel("Nombre(s):", SwingConstants.RIGHT);
		JLabel lblA5 = new JLabel("Foto:", SwingConstants.RIGHT);
		JLabel lblA6 = new JLabel("Grado:", SwingConstants.RIGHT);
		JLabel lblA7 = new JLabel("Grupo:", SwingConstants.RIGHT);
		JLabel lblA8 = new JLabel("Sexo:", SwingConstants.RIGHT);
		JLabel lblA10 = new JLabel("Tarjeta:", SwingConstants.RIGHT);
		
		lblA1.setFont(new Font("Arial", Font.BOLD, 20));
		
		// Inicializacion y filtrado de archivos del Selector de Archivos
		jfc = new JFileChooser();
		jfc.setFileFilter(new JavaFileFilter());
		
		// Inicializacion de los campos alta alumnos
		tfA1 = new JTextField();
		tfA2 = new JTextField();
		tfA3 = new JTextField();
		tfA4 = new JTextField(500);
		tfA5 = new JPasswordField();
		
		// Añadimos los action listener a los campos de alta Alumnos
		/*tfA1.addActionListener(this);
		tfA2.addActionListener(this);
		tfA3.addActionListener(this);
		tfA4.addActionListener(this);
		tfA5.addActionListener(this);*/
		
		
		tfA1.setText(mAsql.patAlumCons(id));
		tfA2.setText(mAsql.matAlumCons(id));
		tfA3.setText(mAsql.nomAlumCons(id));
		tfA4.setText(mAsql.fotAlumCons(id));
		tfA5.setText(mAsql.claAlumCons(id));
		
		
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
		cbA1.setSelectedItem(mAsql.graAlumCons(id));		
		
		// Combo box de Grupo en alta Alumno
		String[] grupA2 = new String[2];
		grupA2[0] = "A";
		grupA2[1] = "B";
		cbA2 = new JComboBox(grupA2);
		cbA2.addActionListener(this);
		cbA2.setSelectedItem(mAsql.gruAlumCons(id));
				
		// Combo box de Sexo en alta Alumno
		String[] sexA2 = new String[2];
		sexA2[0] = "M";
		sexA2[1] = "F";
		cbA3 = new JComboBox(sexA2);
		cbA3.addActionListener(this);
		cbA3.setSelectedItem(mAsql.sexAlumCons(id));
				
		// Inicializacion de los botones de alta Alumnos
		btnA1 = new JButton("Buscar foto");
		btnA1.addActionListener(this);
		btnA1.setActionCommand("buscar_Alumno");
		btnA2 = new JButton("Cancelar");
		btnA2.addActionListener(this);
		btnA3 = new JButton("Aceptar");
		btnA3.addActionListener(this);
		
		
		// Medidas de los botones de alta Alumno
		btnA1.setBounds(163, 128, 128, 20);
		btnA2.setBounds(58, 243, 100, 25);
		btnA3.setBounds(185, 243, 100, 25);
		
		// Medias etiquetas de alta Alumnos
		lblA1.setBounds(0, 10, 350, 20);
		lblA2.setBounds(35, 10, 123, 15);
		lblA3.setBounds(32, 42, 126, 15);
		lblA4.setBounds(80, 72, 78, 15);
		lblA5.setBounds(121, 102, 37, 15);
		lblA6.setBounds(112, 159, 48, 15);
		lblA7.setBounds(112, 188, 48, 15);
		lblA8.setBounds(208, 188, 39, 15);
		lblA10.setBounds(102, 215, 56, 15);
		
		// Medias de campos de texto alta Alumnos
		tfA1.setBounds(163, 7, 128, 20);
		tfA2.setBounds(163, 38, 128, 20);
		tfA3.setBounds(163, 68, 128, 20);
		tfA4.setBounds(163, 98, 128, 20);
		tfA5.setBounds(163, 213, 128, 20);
		
		// Medidas de los ComboBox alta Alumnos
		cbA1.setBounds(163, 158, 128, 20);
		cbA2.setBounds(163, 185, 39, 20);
		cbA3.setBounds(252, 185, 39, 20);
		
		// Aderimos los comboBox al alta de Alumnos
		ventanaMA.add(cbA1);
		ventanaMA.add(cbA2);
		ventanaMA.add(cbA3);
		
		// Aderimos los campos de texto de alta Alumnos
		ventanaMA.add(tfA1);
		ventanaMA.add(tfA2);
		ventanaMA.add(tfA3);
		ventanaMA.add(tfA4);
		ventanaMA.add(tfA5);
		
		// Aderimos los botones al alta Alumnos
		ventanaMA.add(btnA1);
		ventanaMA.add(btnA2);
		ventanaMA.add(btnA3);
		
		// Aderimos etiquetas de alta Alumnos
		//ventanaMA.add(lblA1);
		ventanaMA.add(lblA2);
		ventanaMA.add(lblA3);
		ventanaMA.add(lblA4);
		ventanaMA.add(lblA5);
		ventanaMA.add(lblA6);
		ventanaMA.add(lblA7);
		ventanaMA.add(lblA10);
		ventanaMA.add(lblA8);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancelar")) {
			ventanaMA.dispose();
		} else if(e.getActionCommand().equals("buscar_Alumno")) {
			int resultado = jfc.showOpenDialog(null);
			
			if(resultado == JFileChooser.APPROVE_OPTION) {
				tfA4.setText(jfc.getSelectedFile().getPath());
			}
		} else if(e.getActionCommand().equals("Aceptar")) {
			MySQL mda = new MySQL();
			String paterno = tfA1.getText();
			String materno = tfA2.getText();
			String nombre = tfA3.getText();
			String foto = tfA4.getText();
			String grado = (String)cbA1.getSelectedItem();
			String grupo = (String)cbA2.getSelectedItem();
			String sexo = (String)cbA3.getSelectedItem();
			String matricula = tfA5.getText();
			
			try {
				mda.modAlumno(id, paterno, materno, nombre, foto, grado, grupo, sexo, matricula);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,
						"Error en Base de Datos",
						"¡Error!", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
			try {
				A2.cb_M1.setSelectedItem(mda.graAlumCons(id));
				A2.cb_M2.setSelectedItem(mda.gruAlumCons(id));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			A2.modAlum.doClick();
			ventanaMA.dispose();
		
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
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new ModAlumno(23);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});

	}

}
