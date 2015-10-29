
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

// Clase utilizada para realizar la conexion

public class MySQL {
	private String url = "jdbc:mysql://localhost/system";
	private String user = "root";
	private String password = "toor";
	private String con = null;
		
	// Metodo que recibe como parametro un String, lo busca en la columna 2 de *ALUMNOS*
	// si lo encuentra regresa verdadero, de lo contrario regresa falso
	
	public boolean alumno (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
		
		Statement s = conexion.createStatement();
		String queryCheck = "SELECT * from alumnos WHERE matricula = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);
		
		while (rs.next())
		{
			con = rs.getString(2);
		}
		
		try {
			if (con.equals(id)) {
				return true;
			}
		} catch (NullPointerException e1) {
			return false;
		}
				
		conexion.close();
		
		return false;
	}
	
	// Metodo que recibe como parametro un String, lo busca en la columna 2 de *PADRES*
	// si lo encuentra regresa verdadero, de lo contrario regresa falso
		
	public boolean padre (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
		
		Statement s = conexion.createStatement();
		String queryCheck = "SELECT * from padres WHERE clave = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);
		
		while (rs.next())
		{
			con = rs.getString(2);
		}
		
		try {
			if (con.equals(id)) {
				return true;
			}
		} catch (NullPointerException e1) {
			return false;
		}
				
		conexion.close();
		
		return false;
	}
	
	// Metodo que recibe como parametro un String, lo busca en la columna 2 de *PROFESORES*
	// si lo encuentra regresa verdadero, de lo contrario regresa falso
			
	public boolean profe (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
		
		Statement s = conexion.createStatement();
		String queryCheck = "SELECT * from profesores WHERE clave = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);
		
		while (rs.next())
		{
			con = rs.getString(2);
		}
		
		try {
			if (con.equals(id)) {
				return true;
			}
		} catch (NullPointerException e1) {
			return false;
		}
				
		conexion.close();
		
		return false;
	}
		
	// Metodo que recibe como parametro un String, lo busca en la columna 2 de *ADMINISTRADOR*
	// si lo encuentra regresa verdadero, de lo contrario regresa falso
				
	public boolean admin (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
		
		Statement s = conexion.createStatement();
		String queryCheck = "SELECT * from administrador WHERE clave = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);
		
		while (rs.next())
		{
			con = rs.getString(2);
		}
		
		try {
			if (con.equals(id)) {
				return true;
			}
		} catch (NullPointerException e1) {
			return false;
		}
				
		conexion.close();
		
		return false;
	}
		
	// Metodo que devuelve TRUE si es administrador
	
	public boolean checkAdmin (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
		Statement s = conexion.createStatement();
		
		String queryCheck = "SELECT * from alumnos WHERE clave = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);
		
		while (rs.next())
		{
			con = rs.getString(5);
		}
		
		try {
			if (con.equals("1")) {
				return true;
			}
		} catch (NullPointerException e1) {
			return false;
		}
				
		conexion.close();
		
		return false;
	}
	
	// Metodo que devuelve el ultimo valor en la columna responsable de la tabla control basado en nada
	
	public String valRes () throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		
		String myQuery = "select count(responsable) from control";
		ResultSet myRs = s.executeQuery (myQuery);
		myRs.next();
		int contador = myRs.getInt(1);
		System.out.println("contador: " + contador);
						
		String queryCheck = "select responsable from control where id_control=" + contador;
		
		System.out.println(queryCheck);
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	
	// Metodo que registra todo tipo de usuario en la talba control en cado de ENTRADA
	
	public void regTrue (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
				
	    LocalDateTime myTime = LocalDateTime.now();
	    String time = myTime.toString();
	    String fecha = time.substring(0, 10);
		String hora = time.substring(11, 16);
		String evento = "Entrada";
	    
	    String registrar = "INSERT INTO control (reg_clave, fecha, reg_status, evento, hora, responsable)" +
		" values(?,?,?,?,?,?)";
		
		PreparedStatement ps = conexion.prepareStatement(registrar);
	    ps.setString (1, id);
	    ps.setString (2, fecha);
	    ps.setBoolean(3, true);
	    ps.setString(4, evento);
	    ps.setString(5, hora);
	    ps.setString(6, "- - - - - - - - - - - - - - - - ");
	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo que registra todo tipo de usuario en la talba control en caso de SALIDA
	
	public void regTrueSal (String id, String responsable) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
		
	    LocalDateTime myTime = LocalDateTime.now();
	    String time = myTime.toString();
	    String fecha = time.substring(0, 10);
		String hora = time.substring(11, 16);
		String evento = "Salida";
	    
	    String registrar = "INSERT INTO control (reg_clave, fecha, reg_status, evento, hora, responsable)" +
		" values(?,?,?,?,?,?)";
		System.out.println(registrar);
		PreparedStatement ps = conexion.prepareStatement(registrar);
	    ps.setString (1, id);
	    ps.setString (2, fecha);
	    ps.setBoolean(3, true);
	    ps.setString(4, evento);
	    ps.setString(5, hora);
	    ps.setString(6, responsable);
	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo que regresa un String del nombre del Padre basado en ID del alumno
	
	public String nomPad() throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);	
		Statement s = conexion.createStatement();
		String con2 = null;
		
		String myQuery = "select count(responsable) from control";
		ResultSet myRs = s.executeQuery (myQuery);
		myRs.next();
		int contador = myRs.getInt(1);
		System.out.println("contador: " + contador);
		
		String myQuery_2 = "select reg_clave from control where id_control=" + contador;
		ResultSet rs_2 = s.executeQuery (myQuery_2);	
		while (rs_2.next()) {
			con2 = rs_2.getString(1);
		}
		
		String queryCheck = "SELECT nombre from padres WHERE clave ='" + con2 + "'";
		
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
		
		conexion.close();				
		System.out.println("Nombre del padre: " + con);
		
		return con;
	}
	
	// Metodo que regresa un String del materno del Padre basado en ID del alumno
	
	public String matPad() throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);	
		Statement s = conexion.createStatement();
		String con2 = null;
		
		String myQuery = "select count(responsable) from control";
		ResultSet myRs = s.executeQuery (myQuery);
		myRs.next();
		int contador = myRs.getInt(1);
		System.out.println("contador: " + contador);
		
		String myQuery_2 = "select reg_clave from control where id_control=" + contador;
		ResultSet rs_2 = s.executeQuery (myQuery_2);	
		while (rs_2.next()) {
			con2 = rs_2.getString(1);
		}
		
		String queryCheck = "SELECT materno from padres WHERE clave ='" + con2 + "'";
		
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
		
		conexion.close();				
		System.out.println("Nombre del padre: " + con);
		
		return con;
	}

	// Metodo que regresa un String del paterno del Padre basado en ID del alumno

	public String patPad() throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);	
		Statement s = conexion.createStatement();
		String con2 = null;
		
		String myQuery = "select count(responsable) from control";
		ResultSet myRs = s.executeQuery (myQuery);
		myRs.next();
		int contador = myRs.getInt(1);
		System.out.println("contador: " + contador);
		
		String myQuery_2 = "select reg_clave from control where id_control=" + contador;
		ResultSet rs_2 = s.executeQuery (myQuery_2);	
		while (rs_2.next()) {
			con2 = rs_2.getString(1);
		}
		
		String queryCheck = "SELECT paterno from padres WHERE clave ='" + con2 + "'";
		
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
		
		conexion.close();				
		System.out.println("Nombre del padre: " + con);
		
		return con;
	}
	

	// Metodo que regresa un String del nombre del alumno basado en ID del padre

	public void regPadre (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
			
		
		String queryCheck = "SELECT matricula_hijo from padres WHERE clave = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		String conX34 = "";
		System.out.println("Con antes= " + conX34);
		while (rs.next()) {
			conX34 = rs.getString(1);
		}
		System.out.println("matricula despues= " + conX34);
		// Ahora sacamos el nombre del hijo
		
		LocalDateTime myTime = LocalDateTime.now();
		String time = myTime.toString();
		String fecha = time.substring(0, 10);
		String hora = time.substring(11, 16);
		String evento = "Retiro";
    
		String registrar = "INSERT INTO control (reg_clave, fecha, reg_status, evento, hora, responsable)" +
				" values(?,?,?,?,?,?)";
		System.out.println(registrar);
		PreparedStatement ps = conexion.prepareStatement(registrar);
		ps.setString (1, id);
		ps.setString (2, fecha);
		ps.setBoolean(3, true);
		ps.setString(4, evento);
		ps.setString(5, hora);
		ps.setString(6, conX34);
    
		ps.execute();
		conexion.close();
	}
	
	// Metodo que registra a los intrusos en la tabla control
	
	public void regFalse (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
				
	    LocalDateTime myTime = LocalDateTime.now();
	    String time = myTime.toString();
	    String fecha = time.substring(0, 10);
		String hora = time.substring(11, 16);
	    
	    String registrar = "INSERT INTO control (reg_clave, fecha, hora, reg_status, evento, responsable)" +
		" values(?,?,?,?,?,?)";
		
		PreparedStatement ps = conexion.prepareStatement(registrar);
	    ps.setString (1, id);
	    ps.setString (2, fecha);
	    ps.setString (3, hora);
	    ps.setBoolean (4, false);
	    ps.setString (5, "Intruso");
	    ps.setString (6, "NULL");
	    
	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo que devuelve verdadero si el alumno es del genero "M"
	
	public boolean sexAlum (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
		Statement s = conexion.createStatement();
		
		String queryCheck = "SELECT sexo from alumnos WHERE matricula = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);
		
		while (rs.next())
		{
			con = rs.getString(1);
		}
		
		try {
			if (con.equals("M")) {
				return true;
			}
		} catch (NullPointerException e1) {
			return false;
		}
				
		conexion.close();
			
		return false;
	}
	
	// Metodo que regresa un String con el nombre del *Alumno* basado en su ID
	
	public String nomAlum (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String queryCheck = "SELECT nombre from alumnos WHERE matricula = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
		
		conexion.close();				
		return con;
	}
	
	// Metodo que regresa un String de la foto del alumno basado en ID de alumno
	
	public String fotAlum (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String url = null;
		String queryCheck = "SELECT foto from alumnos WHERE matricula = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
		
		conexion.close();				
		url = con;
		
		return url;
	}
	
	// Metodo que regresa un String de la foto del alumno basado en ID del padre
	
	public String fotAlPa (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String url = null;
		String con2 = null;
		String queryCheck = "SELECT matricula_hijo from padres WHERE clave = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
		
		// Ahora sacamos la foto del hijo
		
		String queryCheck_2 = "SELECT foto from alumnos WHERE matricula = '" + con + "'";
		ResultSet rs_2 = s.executeQuery (queryCheck_2);			
		
		while (rs_2.next()) {
			con2 = rs_2.getString(1);
		}
								
		url = con2;
		conexion.close();
		return url;
	}
		
	// Metodo que regresa un String del nombre del alumno basado en ID del padre
		
	public String nomAlumPad (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT matricula_hijo from padres WHERE clave = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
	
		// Ahora sacamos el nombre del hijo
		
		String queryCheck_2 = "SELECT nombre from alumnos WHERE matricula = '" + con + "'";
		ResultSet rs_2 = s.executeQuery (queryCheck_2);			
		
		while (rs_2.next()) {
			con2 = rs_2.getString(1);
		}
							
		conexion.close();
		return con2;
	}
		
	// Metodo que regresa "true" si ya ingreso el alumno basado en ID de alumno
		
	public boolean ingAlum (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
		int record = 0;
		int count = 0;
		LocalDateTime myTime = LocalDateTime.now();
	    String time = myTime.toString();
	    String tiempo = time.substring(0, 10);
		
		Statement s = conexion.createStatement();
		
		String queryCheck = "SELECT COUNT(fecha) from control WHERE reg_clave = '" + id + "'";
		//SELECT COUNT(fecha) from control WHERE reg_clave = '3050515046' and fecha like '2015-06-12T16:12:35.907%';
		ResultSet rs = s.executeQuery (queryCheck);
		
		rs.next();
		record = rs.getInt(1);
			
		
		System.out.println("con = " + record);
		
			
		if(record % 2 == 0) {
			return false;
		}
		
		conexion.close();
		return true;
	}
	
	// Metodo que regresa "true" si ya ingreso el alumno basado en ID de "padre"
	
	public boolean ingAluPa (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		int record = 0;
		String queryCheck = "SELECT matricula_hijo from padres WHERE clave = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
	
		// Ahora checamos si esta o no en la escuela
		
		String queryCheck_2 = "SELECT COUNT(fecha) from control WHERE reg_clave = '" + con + "'";
		//SELECT COUNT(fecha) from control WHERE reg_clave = '3050515046' and fecha like '2015-06-12T16:12:35.907%';
		ResultSet rs_2 = s.executeQuery (queryCheck_2);
		
		rs_2.next();
		record = rs_2.getInt(1);
			
		
		System.out.println("con = " + record);
		
			
		if(record % 2 == 0) {
			return false;
		}
		
		conexion.close();
		return true;
	}
	
	// Metodo que da de Alta a Alumno
	
	public void alta_Alum (String matricula, String paterno, String materno, String nombre,
		String grado, String grupo, String foto, String sexo) throws SQLException {
				
		Connection conexion = DriverManager.getConnection(url, user, password);
			    	    
	    String registrar = "INSERT INTO alumnos (matricula, paterno, materno, nombre, grado, grupo, foto, sexo)" +
	    " values(?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = conexion.prepareStatement(registrar);
	    
	    ps.setString (1, matricula);
	    ps.setString (2, paterno);
	    ps.setString (3, materno);
	    ps.setString (4, nombre);
	    ps.setString (5, grado);
	    ps.setString (6, grupo);
	    ps.setString (7, foto);
	    ps.setString (8, sexo);
	    	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo que da de Alta a Padre
	
	public void alta_Padre (String paterno, String materno, String nombre, String matricula_hijo,
		String parentesco, String correo, String clave) throws SQLException {
					
		Connection conexion = DriverManager.getConnection(url, user, password);
			    	    
		String registrar = "INSERT INTO padres (clave, matricula_hijo, paterno, materno, nombre, parentesco, correo)" +
		" values(?,?,?,?,?,?,?)";
		
		PreparedStatement ps = conexion.prepareStatement(registrar);
		   
		ps.setString (1, clave);
		ps.setString (2, matricula_hijo);
		ps.setString (3, paterno);
		ps.setString (4, materno);
		ps.setString (5, nombre);
		ps.setString (6, parentesco);
		ps.setString (7, correo);
				    	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo que da de Alta a Profesor
	
	public void alta_Profe (String clave, String paterno, String materno, String nombre, String grado) throws SQLException {
					
		Connection conexion = DriverManager.getConnection(url, user, password);
			    	    
		String registrar = "INSERT INTO profesores (clave, paterno, materno, nombre, grado)" +
		" values(?,?,?,?,?)";
		
		PreparedStatement ps = conexion.prepareStatement(registrar);
		   
		ps.setString (1, clave);
		ps.setString (2, paterno);
		ps.setString (3, materno);
		ps.setString (4, nombre);
		ps.setString (5, grado);
		
				    	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo que da de Alta a Administrador
	
	public void alta_Admin (String clave, String paterno, String materno, String nombre) throws SQLException {
					
		Connection conexion = DriverManager.getConnection(url, user, password);
			    	    
	    String registrar = "INSERT INTO administrador (clave, paterno, materno, nombre)" +
		   " values(?,?,?,?)";
		
		PreparedStatement ps = conexion.prepareStatement(registrar);
		   
		ps.setString (1, clave);
		ps.setString (2, paterno);
		ps.setString (3, materno);
		ps.setString (4, nombre);
						    	    
		ps.execute();
		conexion.close();
	}
	
	/*// Metodo para rellenar la tabla de baja Alumnos
	
	public Object bajaAlumnoTabla() throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);
		
		String seleccionar = "select paterno, materno, nombre from alumnos";
		
		PreparedStatement ps = conexion.prepareStatement(seleccionar);
		ResultSet res = ps.executeQuery();
				
		Object datos[] = new Object[3];
		
		while(res.next()) {
			for (int i = 0; i < 3; i++) {
				datos[i] = res.getObject(i + 1);
			}
		}
				
		res.close();
		
		return datos;
		
	}*/
	
	// Metodo para dar de baja a los alumnos
	
	public void baja_Alumn (String paterno, String materno, String nombre, String grado, String grupo) 
			throws SQLException {
		
		Connection conexion = DriverManager.getConnection(url, user, password);
			    	    
	    String borrar = "DELETE FROM alumnos WHERE paterno ='" + paterno + "' and materno='" + materno + "'"
	    		+ "and nombre='" + nombre + "' and grado='" + grado + "' and grupo='" + grupo + "'";
			    
	    PreparedStatement ps = conexion.prepareStatement(borrar);
							    	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo para dar de baja a los padres
	
	public void baja_Padre_Num (String nombre, String paterno, String materno, String clave) 
			throws SQLException {
		
		Connection conexion = DriverManager.getConnection(url, user, password);
			    	    
	    String borrar = "DELETE FROM padres WHERE paterno ='" + paterno + "' and materno='" + materno + "'"
	    		+ "and nombre='" + nombre + "' and clave='" + clave + "'";
			    
	    PreparedStatement ps = conexion.prepareStatement(borrar);
							    	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo para dar de baja a los profesores
	
	public void baja_Profe_Num (String nombre, String paterno, String materno, String clave) 
			throws SQLException {
		
		Connection conexion = DriverManager.getConnection(url, user, password);
			    	    
	    String borrar = "DELETE FROM profesores WHERE paterno ='" + paterno + "' and materno='" + materno + "'"
	    		+ "and nombre='" + nombre + "' and clave='" + clave + "'";
			    
	    PreparedStatement ps = conexion.prepareStatement(borrar);
							    	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo para dar de baja a los administradores
	public void baja_Admin_Num (String nombre, String paterno, String materno, String clave) 
		throws SQLException {
	
		Connection conexion = DriverManager.getConnection(url, user, password);
			    	    
	    String borrar = "DELETE FROM administrador WHERE paterno ='" + paterno + "' and materno='" + materno + "'"
	    		+ "and nombre='" + nombre + "' and clave='" + clave + "'";
			    
	    PreparedStatement ps = conexion.prepareStatement(borrar);
							    	    
		ps.execute();
		conexion.close();
	}
	
	// Metodo que regresa un String del nombre del alumno basado su id_alumno, no confundir con matricula
	
	public String nomAlumCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT nombre from alumnos WHERE id_alumno = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del paterno del alumno basado su id_alumno, no confundir con matricula
	
	public String patAlumCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT paterno from alumnos WHERE id_alumno = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del materno del alumno basado su id_alumno, no confundir con matricula
	
	public String matAlumCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT materno from alumnos WHERE id_alumno = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String de la matricula del alumno basado su id_alumno, no confundir con matricula
	
	public String claAlumCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT matricula from alumnos WHERE id_alumno = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String de la foto del alumno basado su id_alumno, no confundir con matricula
	
	public String fotAlumCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT foto from alumnos WHERE id_alumno = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String de la grado del alumno basado su id_alumno, no confundir con matricula
	
	public String graAlumCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT grado from alumnos WHERE id_alumno = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String de la grupo del alumno basado su id_alumno, no confundir con matricula
	
	public String gruAlumCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT grupo from alumnos WHERE id_alumno = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String de la sexo del alumno basado su id_alumno, no confundir con matricula
	
	public String sexAlumCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT sexo from alumnos WHERE id_alumno = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un entero del id_alumno, no confundir con matricula usando nombre, paterno, etc.
	
	public int id_alumno (String paterno, String materno, String nombre, String grado, String grupo) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT id_alumno from alumnos WHERE paterno='" + paterno + "' and materno='" + materno + 
				"' and nombre='" + nombre + "' and grado='" + grado + "' and grupo='" + grupo + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		int conInt = Integer.parseInt(con);
		
		return conInt;
	}
	
	// Metodo que actualiza los datos en Alumnos
	
	public void modAlumno (int id_alumno, String paterno, String materno, String nombre, String foto, String grado, 
			String grupo, String sexo, String matricula) throws SQLException {
		
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		
		String queryCheck = "UPDATE system.alumnos SET paterno='" + paterno + "', materno='" + materno + "', nombre='" + nombre 
				+ "', foto='" + foto + "', grado='" + grado + "', grupo='" + grupo + "', sexo='" + sexo + "', matricula='" + matricula + "' WHERE id_alumno=" + id_alumno;
		System.out.println(queryCheck);
		int rs = s.executeUpdate (queryCheck);			
		conexion.close();
	}
	
	// Metodo que regresa un String del nombre del padre basado en su id_padre, no confundir con matricula
	
	public String nomTutoCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT nombre from padres WHERE id_padre = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del paterno del padre basado en su id_padre, no confundir con matricula
	
	public String paterTutoCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT paterno from padres WHERE id_padre = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del materno del padre basado en su id_padre, no confundir con matricula
	
	public String materTutoCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT materno from padres WHERE id_padre = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String matricula hijo del padre basado en su id_padre, no confundir con matricula
	
	public String matHijoTutoCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT matricula_hijo from padres WHERE id_padre = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String parentesco del padre basado en su id_padre, no confundir con matricula
	
	public String parenTutoCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT parentesco from padres WHERE id_padre = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String correo electronico del padre basado en su id_padre, no confundir con matricula
	
	public String correoTutoCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT correo from padres WHERE id_padre = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String matricula del padre basado en su id_padre, no confundir con matricula
	
	public String matriTutoCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT clave from padres WHERE id_padre = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que actualiza los datos en Alumnos
	
	public void modTutor (int id_padre, String paterno, String materno, String nombre, String matricula_hijo, String parentesco, 
			String correo, String clave) throws SQLException {
		
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		
		String queryCheck = "UPDATE system.padres SET paterno='" + paterno + "', materno='" + materno + "', nombre='" + nombre 
				+ "', matricula_hijo='" + matricula_hijo + "', clave='" + clave + "', correo='" + correo + "', parentesco='" + parentesco
				+ "' WHERE id_padre='" + id_padre + "'";
		System.out.println(queryCheck);
		int rs = s.executeUpdate (queryCheck);			
		conexion.close();
	}
	
	// Metodo que regresa un entero del id_alumno, no confundir con matricula, usando nombre, paterno, etc.
	
	public int id_padre (String nombre, String paterno, String materno, String clave) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT id_padre from padres WHERE paterno='" + paterno + "' and materno='" + materno + 
				"' and nombre='" + nombre + "' and clave='" + clave + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		int conInt = Integer.parseInt(con);
		
		return conInt;
	}
	
	// Metodo que regresa un String del nombre del profe basado en su id_profesor, no confundir con matricula
	
	public String nomProfCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT nombre from profesores WHERE id_profesor = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del paterno del profe basado en su id_profesor, no confundir con matricula
	
	public String paterProfCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT paterno from profesores WHERE id_profesor = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del materno del profe basado en su id_profesor, no confundir con matricula
	
	public String materProfCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT materno from profesores WHERE id_profesor = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del grado del profe basado en su id_profesor, no confundir con matricula
	
	public String graProfCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT grado from profesores WHERE id_profesor = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String matricula del profe basado en su id_profesor, no confundir con matricula
	
	public String claProfCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT clave from profesores WHERE id_profesor = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que acualiza los datos de los profesores
	
	public void modProfe (int id_profesor, String paterno, String materno, String nombre, String grado, String clave) throws SQLException {
		
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		
		String queryCheck = "UPDATE system.profesores SET paterno='" + paterno + "', materno='" + materno + "', nombre='" + nombre 
				+ "', grado='" + grado + "', clave='" + clave + "' WHERE id_profesor=" + id_profesor;
		System.out.println(queryCheck);
		int rs = s.executeUpdate (queryCheck);			
		conexion.close();
	}
	
	// Metodo que regresa un entero del id_profe, no confundir con matricula, usando nombre, paterno, etc.
	
	public int id_profe (String nombre, String paterno, String materno, String clave) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT id_profesor from profesores WHERE paterno='" + paterno + "' and materno='" + materno + 
				"' and nombre='" + nombre + "' and clave='" + clave + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		int conInt = Integer.parseInt(con);
		
		return conInt;
	}
	
	// Metodo que regresa un String del paterno del adminnistrador basado en su id_admin, no confundir con matricula
	
	public String paterAdminCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT paterno from administrador WHERE id_admin = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del materno del adminnistrador basado en su id_admin, no confundir con matricula
	
	public String materAdminCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT materno from administrador WHERE id_admin = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del nombre del adminnistrador basado en su id_admin, no confundir con matricula
	
	public String nomAdminCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT nombre from administrador WHERE id_admin = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String matricula del adminnistrador basado en su id_admin, no confundir con matricula
	
	public String claAdminCons (int id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT clave from administrador WHERE id_admin = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que acualiza los datos de los administradores
	
	public void modAdmin (int id_admin, String paterno, String materno, String nombre, String clave) throws SQLException {
		
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		
		String queryCheck = "UPDATE system.administrador SET paterno='" + paterno + "', materno='" + materno + "', nombre='" + nombre 
				+ "', clave='"+ clave + "' WHERE id_admin=" + id_admin;
		System.out.println(queryCheck);
		int rs = s.executeUpdate (queryCheck);			
		conexion.close();
	}
	
	// Metodo que regresa un entero del id_admin, no confundir con matricula, usando nombre, paterno, etc.
	
	public int id_admin (String nombre, String paterno, String materno, String clave) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT id_admin from administrador WHERE paterno='" + paterno + "' and materno='" + materno + 
				"' and nombre='" + nombre + "' and clave='" + clave + "'";
		System.out.println(queryCheck);
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		int conInt = Integer.parseInt(con);
		
		return conInt;
	}
	
	// Metodo que regresa un String del nombre del alumno basado su matricula
	
	public String nomAlumMatri (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT nombre from alumnos WHERE matricula = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del paterno del alumno basado su matricula
	
	public String patAlumMatri (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT paterno from alumnos WHERE matricula = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String del materno del alumno basado su matricula
	
	public String matAlumMatri (String id) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT materno from alumnos WHERE matricula = '" + id + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	// Metodo que regresa un String de la matricula del alumno basado su paterno, materno, nombre, etc.
	
	public String alumMatri (String paterno, String materno, String nombre, String grado, String grupo) throws SQLException {
		Connection conexion = DriverManager.getConnection(url, user, password);			
		Statement s = conexion.createStatement();
		String con2 = null;
		String queryCheck = "SELECT matricula from alumnos WHERE paterno='" + paterno + "' and materno='" + materno
				+"' and nombre='" + nombre + "' and grado='" + grado + "' and grupo='" + grupo + "'";
		ResultSet rs = s.executeQuery (queryCheck);			
		
		while (rs.next()) {
			con = rs.getString(1);
		}
										
		conexion.close();
		return con;
	}
	
	
} 