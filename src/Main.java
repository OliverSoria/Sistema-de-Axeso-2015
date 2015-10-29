
/*

Copyright 2015 Oliver Soria Pelaez

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/

// Program name: "Sistema de Axeso 2015"

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Timer;

class Main extends JFrame implements ActionListener {
	JTextField campo;
	JButton aceptar;
	JLabel eti;
	JPanel p1;
	JPanel f1;
	JLabel fotoE;
	int tiempo;
		
	// Medidas para centrar la foto del niño al ingresar
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int ra = screenSize.height - 160;
			int _1x = ra / 6;
			int _4x = _1x * 4;
			int y = _4x / 5;
			int d = 4 * y;
			int z = (screenSize.width - d)/2;
			
	public Main() {
        createAndShowGUI();
    }
    
    private void createAndShowGUI() {
        // Codigo para pantalla completa
        setTitle("Transparent Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        
		setVisible(true);
		int y1 = screenSize.height + 25;
		int y2 = y1  - 25; //valor original:25
		int y3 = screenSize.width - 20;
		setBounds(0,0,screenSize.width, y1);
		
		tiempo = 1500;
				
		final ImageIcon fondo = new ImageIcon(getClass().getResource("/fondo.jpeg"));
		JLabel jFondo = new JLabel();
		jFondo.setIcon(fondo);
		setContentPane(jFondo);				        
        
		// Asignamos el fondo de la ventana
        //setContentPane(new JLabel(new ImageIcon("images/fondo.jpeg")));
        
        // En este caso no vamos a usar ningun Layout:
        setLayout(null);
                
        // Creamos los JPanel para los componentes
        p1 = new JPanel();
        f1 = new JPanel();
        
        // Inicializamos la etiqueta para foto bienvenida alumno
        fotoE = new JLabel();
                       
        campo = new JTextField();
        aceptar = new JButton("aceptar");
        
        // Colores del JPanel en RGBA
        p1.setBackground(new Color(60,144,253,255));
        f1.setBackground(new Color(0,0,0,125));
         
              
        // Configuramos el panel y la etiqueta
        p1.setLayout(new BorderLayout());
        f1.setLayout(new BorderLayout());
               
        // Etiqueta de deslizar
        eti = new JLabel();
        eti.setText("Deslice su tarjeta");
        eti.setFont(new Font("Arial", Font.BOLD, 65));
        eti.setForeground(Color.white);
        eti.setHorizontalAlignment(JLabel.CENTER);
        eti.setVerticalAlignment(JLabel.CENTER);
        p1.add(eti, BorderLayout.CENTER);
                                       
        // Les damos medidas
        //fotoE.setBounds(50, 50, 400, 500);
        p1.setBounds(10,10,y3,150);
        f1.setBounds(z, 160 + _1x, d, _4x);
        f1.setVisible(false);
                        
        aceptar.setBounds(10, y2, 110, 25);
        campo.setBounds(130, y2, 120, 25);
        aceptar.addActionListener(this);
        
        // Al presionar Enter en el campo se activa el 
        // boton aceptar
        
        campo.addKeyListener(
				  new KeyAdapter() {
					     public void keyPressed(KeyEvent e) {
					       if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					    	   aceptar.doClick();
					       }
					     }
					  });
                        
        add(p1);			// Añadimos el panel p1 al JFrame principal
        add(aceptar);		// Añadimos el boton aceptar al JFrame principal 
        add(campo);			// Añadimos el campo al JFrame principal
        add(f1);			// Añadimos el panel f1 al JFrame principal
        
        // Le asignamos el foco al campo
        campo.requestFocusInWindow();
    }
    
    public void actionPerformed(ActionEvent e) {
        MySQL sq1 = new MySQL();
        
        try {
        	if(sq1.alumno(campo.getText())) { 			// Primero checamos si esta en alumnos
        			p1.setVisible(false); 				// Ocultamos el panel para modificaciones 
                	
                	if(!sq1.ingAlum(campo.getText())) {
                		sq1.regTrue(campo.getText());		// Se almacena el registro si esta para ENTRADA
                		if(sq1.sexAlum(campo.getText())) {
                        	eti.setText("Bienvenido " + sq1.nomAlum(campo.getText()));			// Modificamos etiqueta segun genero
                        } else {
                        	eti.setText("Bienvenida " + sq1.nomAlum(campo.getText()));			// Modificamos etiqueta segun genero
                        }
                		// Imagen exportable 
                		final ImageIcon foto = new ImageIcon(sq1.fotAlum(campo.getText()));
                		System.out.println(foto);               		
                		fotoE.setIcon(new ImageIcon(((foto.getImage()
                                .getScaledInstance(d, _4x,
                                        java.awt.Image.SCALE_SMOOTH)))));						// Agregamos la foto a la etiqueta
                		f1.add(fotoE, BorderLayout.CENTER);										// Agregamos la foto al panel f1
                		f1.setVisible(true);													// Hacemos visible el panel f1
                		
                		p1.setBackground(new Color(60,144,253,255));		// color azul
                        p1.setVisible(true);				// Volvemos a hacer visible el panel
                        campo.setText(null);				// Borramos el texto en el campo
                        campo.setEnabled(false);			// Desabilitamos el campo
                        
                        // Añadimos el Timer********************************************
                        Timer timer = new Timer(tiempo, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            	
                            	p1.setVisible(false); 				// Ocultamos el panel para modificaciones
                            	eti.setText("Deslice su tarjeta");	// Primero modificamos la etiqueta
                            	//p1.add(eti); 						// Aderimos la etiqueta al panel
                            	//add(p1);							// Aderimos el panel al JFrame
                            	p1.setBackground(new Color(60,144,253,255));
                            	p1.setVisible(true);				// Volvemos a hacer visible el panel
                            	f1.setVisible(false);
                            	fotoE.setIcon(null);
                            	campo.setEnabled(true);
                                campo.requestFocus();
                                   	
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                                    		
                	} else {
                		
                		String registroA = sq1.valRes();
                		String registroB = campo.getText();
                		System.out.println("registroA= " + registroA);
                		System.out.println("registroB= " + registroB);
                		if(registroA.equals(registroB)) {
                			String responsable = sq1.nomPad() + " " + sq1.matPad() + 
                					" " + sq1.patPad();
                			
                			sq1.regTrueSal(campo.getText(), responsable);		// Se almacena el registro si esta para SALIDA
                    		System.out.println(sq1.nomPad());
                			eti.setText("Adios " + sq1.nomAlum(campo.getText()));					// Modificando etiqueta del panel
                    		// Imagen exportable
                    		final ImageIcon foto = new ImageIcon(sq1.fotAlum(campo.getText()));
                    		
                    		fotoE.setIcon(new ImageIcon(((foto.getImage()
                                    .getScaledInstance(d, _4x,
                                            java.awt.Image.SCALE_SMOOTH)))));						// Agregamos la foto a la etiqueta
                    		f1.add(fotoE, BorderLayout.CENTER);										// Agregamos la foto al panel f1
                    		f1.setVisible(true);													// Hacemos visible el panel f1
                    		                		
                    		p1.setBackground(new Color(60,144,253,255));		// color azul
                            p1.setVisible(true);				// Volvemos a hacer visible el panel
                            campo.setText(null);				// Borramos el texto en el campo
                            campo.setEnabled(false);			// Desabilitamos el campo
                            
                            // Añadimos el Timer********************************************
                            Timer timer = new Timer(tiempo, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                	
                                	p1.setVisible(false); 				// Ocultamos el panel para modificaciones
                                	eti.setText("Deslice su tarjeta");	// Primero modificamos la etiqueta
                                	//p1.add(eti); 						// Aderimos la etiqueta al panel
                                	//add(p1);							// Aderimos el panel al JFrame
                                	p1.setBackground(new Color(60,144,253,255));
                                	p1.setVisible(true);				// Volvemos a hacer visible el panel
                                	f1.setVisible(false);
                                	fotoE.setIcon(null);
                                	campo.setEnabled(true);
                                    campo.requestFocus();
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                                     			
                		} else {
                			
                            p1.setBackground(new Color(255,0,0,255));		// color azul
                            eti.setText("Salida restringida");			// Primero modificamos la etiqueta
                            p1.setVisible(true);				// Volvemos a hacer visible el panel
                            campo.setText(null);				// Borramos el texto en el campo
                            campo.setEnabled(false);			// Desabilitamos el campo
                                                     
                            // Añadimos el Timer********************************************
                            Timer timer = new Timer(tiempo, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                	
                                	p1.setVisible(false); 				// Ocultamos el panel para modificaciones
                                	eti.setText("Deslice su tarjeta");	// Primero modificamos la etiqueta
                                	//p1.add(eti); 						// Aderimos la etiqueta al panel
                                	//add(p1);							// Aderimos el panel al JFrame
                                	p1.setBackground(new Color(60,144,253,255));
                                	p1.setVisible(true);				// Volvemos a hacer visible el panel
                                	f1.setVisible(false);
                                	fotoE.setIcon(null);
                                	campo.setEnabled(true);
                                    campo.requestFocus();
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        }
                	}
                	
            } else {
                if(sq1.padre(campo.getText())) {		// Luego checamos si esta en padres
                	sq1.regPadre(campo.getText());		// Se ejecuta si esta en la tabla
                	p1.setVisible(false); 				// Ocultamos el panel para modificaciones 
                	
                	if(sq1.ingAluPa(campo.getText())) {
                		
                        
                        eti.setText("Puede retirar a " + sq1.nomAlumPad(campo.getText()));			// Primero modificamos la etiqueta
                        // Imagen exportable 
                		final ImageIcon foto = new ImageIcon(sq1.fotAlPa(campo.getText()));
                		                		
                		fotoE.setIcon(new ImageIcon(((foto.getImage()
                                .getScaledInstance(d, _4x,
                                        java.awt.Image.SCALE_SMOOTH)))));						// Agregamos la foto a la etiqueta
                		f1.add(fotoE, BorderLayout.CENTER);										// Agregamos la foto al panel f1
                		f1.setVisible(true);													// Hacemos visible el panel f1
                		
                		p1.setBackground(new Color(60,144,253,255));	// color azul
                        p1.setVisible(true);							// Volvemos a hacer visible el panel
                        campo.setText(null);							// Borramos el texto en el campo
                        campo.setEnabled(false);			// Desabilitamos el campo
                		
                	} else {
                		
                		//p1.setVisible(false); 					// Ocultamos el panel para modificaciones 
                        eti.setText(sq1.nomAlumPad(campo.getText()) + " está ausente");			// Primero modificamos la etiqueta
                        p1.setBackground(new Color(255,0,0,255));
                        p1.setVisible(true);					// Volvemos a hacer visible el panel               
                        campo.setText(null);					// Borramos el texto en el campo
                        campo.setEnabled(false);				// Desabilitamos el campo
                	}
                	
                	// Añadimos el Timer********************************************
                    Timer timer = new Timer(tiempo, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	
                        	p1.setVisible(false); 				// Ocultamos el panel para modificaciones
                        	eti.setText("Deslice su tarjeta");	// Primero modificamos la etiqueta
                        	//p1.add(eti); 						// Aderimos la etiqueta al panel
                        	//add(p1);							// Aderimos el panel al JFrame
                        	p1.setBackground(new Color(60,144,253,255));
                        	p1.setVisible(true);				// Volvemos a hacer visible el panel
                        	f1.setVisible(false);
                        	fotoE.setIcon(null);
                        	campo.setEnabled(true);
                            campo.requestFocus();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                                    
                } else {
                	if(sq1.profe(campo.getText())) {		// Luego checamos si esta en profesores
                		campo.setText(null);				// Borramos el texto en el campo
                		A3 frameA3 = new A3();
                	              	               	
                	} else {
                    		if(sq1.admin(campo.getText())) {		// Luego checamos si esta en administradores
                        	
                    			//sq1.regTrue(campo.getText());		// Se almacena el registro si esta
                        		campo.setText(null);				// Borramos el texto en el campo
                        		A2 frameA2 = new A2();
                        	
                        } else {
                        	sq1.regFalse(campo.getText());			// Se hace el registro en la BD del intruso
                            p1.setVisible(false); 					// Ocultamos el panel para modificaciones 
                            eti.setText("ACCESO DENEGADO");			// Primero modificamos la etiqueta
                            //p1.add(eti); 							// Aderimos la etiqueta al panel
                            //add(p1);								// Aderimos el panel al JFrame
                            p1.setBackground(new Color(255,0,0,255));
                            p1.setVisible(true);					// Volvemos a hacer visible el panel               
                            campo.setText(null);					// Borramos el texto en el campo
                            
                            // Añadimos el Timer********************************************
                            Timer timer = new Timer(tiempo, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                	
                                	p1.setVisible(false); 				// Ocultamos el panel para modificaciones
                                	eti.setText("Deslice su tarjeta");	// Primero modificamos la etiqueta
                                	//p1.add(eti); 						// Aderimos la etiqueta al panel
                                	//add(p1);							// Aderimos el panel al JFrame
                                	p1.setBackground(new Color(60,144,253,255));
                                	p1.setVisible(true);				// Volvemos a hacer visible el panel
                                	f1.setVisible(false);
                                	fotoE.setIcon(null);
                                	campo.setEnabled(true);
                                    campo.requestFocus();
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        }
                    }
                }
            }
        	
        } catch (SQLException e1) {
        	p1.setVisible(false); 					// Ocultamos el panel para modificaciones 
            eti.setText("ERROR EN BASE DE DATOS");			// Primero modificamos la etiqueta
            //p1.add(eti); 							// Aderimos la etiqueta al panel
            //add(p1);								// Aderimos el panel al JFrame
            p1.setBackground(new Color(255,0,0,255));
            p1.setVisible(true);					// Volvemos a hacer visible el panel               
            campo.setText(null);					// Borramos el texto en el campo
            e1.printStackTrace();
            
            // Añadimos el Timer********************************************
            Timer timer = new Timer(tiempo, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	p1.setVisible(false); 				// Ocultamos el panel para modificaciones
                	eti.setText("Deslice su tarjeta");	// Primero modificamos la etiqueta
                	//p1.add(eti); 						// Aderimos la etiqueta al panel
                	//add(p1);							// Aderimos el panel al JFrame
                	p1.setBackground(new Color(60,144,253,255));
                	p1.setVisible(true);				// Volvemos a hacer visible el panel
                	f1.setVisible(false);
                	fotoE.setIcon(null);
                	campo.setEnabled(true);
                    campo.requestFocus();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
        
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Main();
            }
        });
        
        H1 h1 = new H1();
		Thread t2 = new Thread(h1);
		t2.start();
		t2.setPriority(Thread.MIN_PRIORITY);
    }
}

/* Comandos importantes de MySQL
 * truncate table control;
 * SET GLOBAL sql_mode='MSSQL,NO_BACKSLASH_ESCAPES';
 * 
 */

