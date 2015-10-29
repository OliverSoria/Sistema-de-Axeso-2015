
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ModTutor_Prof extends JFrame implements ActionListener {
	int id;
	JFrame ventanaMT;
	private JTextField tfB1;
	private JTextField tfB2;
	private JTextField tfB3;
	private JPasswordField tfB4;
	private JTextField tfB5;
	private JTextField tfB6;
	private JPasswordField tfB7;
	private JButton btnB1;
	private JButton btnB2;
	
	ModTutor_Prof(int id) throws SQLException {
		this.id = id;
		iniciaComponentes(id);
	}
	
	void iniciaComponentes(int id) throws SQLException { 
		MySQL mTsql = new MySQL();
		ventanaMT = new JFrame("Modificar Tutor");
		ventanaMT.setLayout(null);
		ventanaMT.setResizable(false);
		ventanaMT.setSize(350, 285);
		ventanaMT.setVisible(true);
		ventanaMT.setLocationRelativeTo(null);
		ventanaMT.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Inicializacion de las etiquetas
		JLabel lblB2 = new JLabel("Apellido paterno:", SwingConstants.RIGHT);
		JLabel lblB3 = new JLabel("Apellido materno:", SwingConstants.RIGHT);
		JLabel lblB4 = new JLabel("Nombre(s):", SwingConstants.RIGHT);
		JLabel lblB5 = new JLabel("Tarjeta de Hijo:", SwingConstants.RIGHT);
		JLabel lblB6 = new JLabel("Parentesco:", SwingConstants.RIGHT);
		JLabel lblB7 = new JLabel("Correo electrónico:", SwingConstants.RIGHT);
		JLabel lblB8 = new JLabel("Tarjeta:", SwingConstants.RIGHT);
		
		// Inicializacion de los campos de alta Padres
		tfB1 = new JTextField();
		tfB2 = new JTextField();
		tfB3 = new JTextField();
		tfB4 = new JPasswordField();
		tfB5 = new JTextField();
		tfB6 = new JTextField();
		tfB7 = new JPasswordField();
		
		/*tfB1.addActionListener(this);
		tfB2.addActionListener(this);
		tfB3.addActionListener(this);
		tfB4.addActionListener(this);
		tfB5.addActionListener(this);
		tfB6.addActionListener(this);
		tfB7.addActionListener(this);*/
		
		tfB1.setText(mTsql.paterTutoCons(id));
		tfB2.setText(mTsql.materTutoCons(id));
		tfB3.setText(mTsql.nomTutoCons(id));
		tfB4.setText(mTsql.matHijoTutoCons(id));
		tfB5.setText(mTsql.parenTutoCons(id));
		tfB6.setText(mTsql.correoTutoCons(id));
		tfB7.setText(mTsql.matriTutoCons(id));
		
		// Inicializacion de los botones
		btnB1 = new JButton("Cancelar");
		btnB2 = new JButton("Aceptar");
		
		// Aderimos los action listener
		btnB1.addActionListener(this);
		btnB2.addActionListener(this);
		
		// Medidas de las etiquetas
		lblB2.setBounds(35, 10, 123, 15);
		lblB3.setBounds(32, 42, 126, 15);
		lblB4.setBounds(80, 72, 78, 15);
		lblB5.setBounds(34, 102, 124, 15);
		lblB6.setBounds(72, 130, 86, 15);
		lblB7.setBounds(22, 160, 136, 15);
		lblB8.setBounds(102, 190, 56, 15);
		
		// Medidas de los campos
		tfB1.setBounds(163, 8, 128, 20);
		tfB2.setBounds(163, 38, 128, 20);
		tfB3.setBounds(163, 68, 128, 20);
		tfB4.setBounds(163, 98, 128, 20);
		tfB5.setBounds(163, 128, 128, 20);
		tfB6.setBounds(163, 158, 128, 20);
		tfB7.setBounds(163, 188, 128, 20);
		
		// Medidas de los botones
		btnB1.setBounds(58, 220, 100, 25);
		btnB2.setBounds(185, 220, 100, 25);
		
		// Aderimos las etiquetas
		ventanaMT.add(lblB2);
		ventanaMT.add(lblB3);
		ventanaMT.add(lblB4);
		ventanaMT.add(lblB5);
		ventanaMT.add(lblB6);
		ventanaMT.add(lblB7);
		ventanaMT.add(lblB8);
		
		// Aderimos los campos
		ventanaMT.add(tfB1);
		ventanaMT.add(tfB2);
		ventanaMT.add(tfB3);
		ventanaMT.add(tfB4);
		ventanaMT.add(tfB5);
		ventanaMT.add(tfB6);
		ventanaMT.add(tfB7);
		
		// Aderomos los botones
		ventanaMT.add(btnB1);
		ventanaMT.add(btnB2);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancelar")) {
			ventanaMT.dispose();
		} else if(e.getActionCommand().equals("Aceptar")) {
			MySQL mda = new MySQL();
			String paterno = tfB1.getText();
			String materno = tfB2.getText();
			String nombre = tfB3.getText();
			String tarjetaHijo = tfB4.getText();
			String parentesco = tfB5.getText();
			String correo = tfB6.getText();
			String tarjeta = tfB7.getText();
			
			
			try {
				mda.modTutor(id, paterno, materno, nombre, tarjetaHijo, parentesco, correo, tarjeta);
				System.out.println("modificacion hecha en tutor");
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null,
						"Error en Base de Datos",
						"¡Error!", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			
			A3.tfMT.setText(tarjeta);
			A3.btnMT.doClick();
			A3.tfMT.setText("");
			ventanaMT.dispose();
		}
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new ModTutor_Prof(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
	}
}
