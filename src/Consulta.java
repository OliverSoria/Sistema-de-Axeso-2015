
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class Consulta extends JFrame implements ActionListener {
	String id;
	JFrame vConsul;
	JTable tb_BAlum;
	private JButton btnD1;
		
	Consulta(String id) throws SQLException {
		this.id = id;
		iniciaComponentes(id);
	}
	
	void iniciaComponentes(String id) throws SQLException {
		MySQL mAsql = new MySQL();
		vConsul = new JFrame("Consulta de asistencia");
		vConsul.setLayout(null);
		vConsul.setResizable(false);
		vConsul.setSize(520, 435);
		vConsul.setVisible(true);
		vConsul.setLocationRelativeTo(null);
		vConsul.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		String name = null;
		JLabel lblDatos = new JLabel(name,SwingConstants.CENTER);
	
		String nombre = mAsql.nomAlumMatri(id);
		String paterno = mAsql.patAlumMatri(id);
		String materno = mAsql.matAlumMatri(id);
				
		name = "Alumno: " + paterno + " " + materno + " " + nombre;
		System.out.println(name);
		lblDatos.setText(name);
		
		lblDatos.setFont(new Font("Arial", Font.PLAIN, 18));
		
		
		btnD1 = new JButton("Cerrar");
		btnD1.addActionListener(this);
		
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
            String sql = "select fecha, hora, evento, responsable from control where reg_clave='" + id + "'";
            
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
		tb_BAlum.getColumnModel().getColumn(0).setPreferredWidth(115);
		tb_BAlum.getColumnModel().getColumn(1).setPreferredWidth(105);
		tb_BAlum.getColumnModel().getColumn(2).setPreferredWidth(105);
		tb_BAlum.getColumnModel().getColumn(3).setPreferredWidth(170);
		scrollPane.setBounds(10, 50, 495, 300);								// Medidas del panel anterior
        vConsul.add(scrollPane);
        lblDatos.setBounds(0, 15, 520, 20);
        btnD1.setBounds(210, 365, 100, 25);
        vConsul.add(btnD1);
        vConsul.add(lblDatos);
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cerrar")) {
			vConsul.dispose();
		}
			
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Consulta("0242051320");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		
		
	}

	

}
