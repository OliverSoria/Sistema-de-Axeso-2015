
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


public class ModProfe extends JFrame implements ActionListener {
	int id;
	JFrame ventanaMP;
	private JTextField tfC1;
	private JTextField tfC2;
	private JTextField tfC3;
	private JPasswordField tfC4;
	private JComboBox cbC1;
	private JButton btnC1;
	private JButton btnC2;
	
	
	ModProfe(int id) throws SQLException {
		this.id = id;
		iniciaComponentes(id);
	}

	void iniciaComponentes(int id)throws SQLException { 
		MySQL mTsql = new MySQL();
		ventanaMP = new JFrame("Modificar Tutor");
		ventanaMP.setLayout(null);
		ventanaMP.setResizable(false);
		ventanaMP.setSize(350, 225);
		ventanaMP.setVisible(true);
		ventanaMP.setLocationRelativeTo(null);
		ventanaMP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Inicializacion de las etiquetas de alta Profesor
		JLabel lblC2 = new JLabel("Apellido paterno:", SwingConstants.RIGHT);
		JLabel lblC3 = new JLabel("Apellido materno:", SwingConstants.RIGHT);
		JLabel lblC4 = new JLabel("Nombre(s):", SwingConstants.RIGHT);
		JLabel lblC5 = new JLabel("Grado:", SwingConstants.RIGHT);
		JLabel lblC6 = new JLabel("Tarjeta:", SwingConstants.RIGHT);
		
		
		
		
		
		// Inicializacion de los campos
		tfC1 = new JTextField();
		tfC2 = new JTextField();
		tfC3 = new JTextField();
		tfC4 = new JPasswordField();
		
		tfC1.addActionListener(this);
		tfC2.addActionListener(this);
		tfC3.addActionListener(this);
		tfC4.addActionListener(this);
		
		
		
		tfC1.setText(mTsql.paterProfCons(id));
		tfC2.setText(mTsql.materProfCons(id));
		tfC3.setText(mTsql.nomProfCons(id));
		
		tfC4.setText(mTsql.claProfCons(id));
		
		
		
		// Creacion del ComboBox
		String[] sexC2 = new String[6];
		sexC2[0] = "Primero";
		sexC2[1] = "Segundo";
		sexC2[2] = "Tercero";
		sexC2[3] = "Cuarto";
		sexC2[4] = "Quinto";
		sexC2[5] = "Sexto";
		cbC1 = new JComboBox(sexC2);
		cbC1.setSelectedItem(mTsql.graProfCons(id));
		
		
		
		// Inicializacion de los botones
		btnC1 = new JButton("Cancelar");
		btnC2 = new JButton("Aceptar");
		
		// Aderimos los action listener
		btnC1.addActionListener(this);
		btnC2.addActionListener(this);
		
		// Medidas del combo box
		cbC1.setBounds(163, 98, 128, 20);
		
		// Medidas de los campos
		tfC1.setBounds(163, 10, 128, 20);
		tfC2.setBounds(163, 38, 128, 20);
		tfC3.setBounds(163, 68, 128, 20);
		tfC4.setBounds(163, 128, 128, 20);
		
		// Medidas de las etiquetas de alta Profesores
		lblC2.setBounds(35, 10, 123, 15);
		lblC3.setBounds(32, 40, 126, 15);
		lblC4.setBounds(80, 70, 78, 15);
		lblC5.setBounds(112, 100, 48, 15);
		lblC6.setBounds(102, 130, 56, 15);
		
		// Medidas de los botones
		btnC1.setBounds(58, 160, 100, 25);
		btnC2.setBounds(185, 160, 100, 25);
		
		// Aderomos los botones
		ventanaMP.add(btnC1);
		ventanaMP.add(btnC2);
				
		// Aderimos etiquetas de alta Padres
		ventanaMP.add(lblC2);
		ventanaMP.add(lblC3);
		ventanaMP.add(lblC4);
		ventanaMP.add(lblC5);
		ventanaMP.add(lblC6);
		
		// aderimos el combo box
		ventanaMP.add(cbC1);
		
		// Aderimos los campos de texto
		ventanaMP.add(tfC1);
		ventanaMP.add(tfC2);
		ventanaMP.add(tfC3);
		ventanaMP.add(tfC4);
				
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancelar")) {
			ventanaMP.dispose();
		} else if(e.getActionCommand().equals("Aceptar")) {
			MySQL mda = new MySQL();
			String paterno = tfC1.getText();
			String materno = tfC2.getText();
			String nombre = tfC3.getText();
			String grado = (String)cbC1.getSelectedItem();
			String clave = tfC4.getText();
			
			try {
				mda.modProfe(id, paterno, materno, nombre, grado, clave);
				System.out.println("Modificacion hecha en Profesor");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,
						"Error en Base de Datos",
						"¡Error!", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
			A2.tfMP.setText(clave);
			A2.btnMP.doClick();
			A2.tfMP.setText("");
			ventanaMP.dispose();
			
		} 
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new ModProfe(4);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		

	}

}
