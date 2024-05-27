package tema8_ejercicio2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.sql.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Tema8_ejercicio2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tema8_ejercicio2 window = new Tema8_ejercicio2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tema8_ejercicio2() {
		initialize();
	//    cargarDatosComboBoxComunidad();
	 //   cargarDatosComboBoxProvincia();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 232);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox provinciaComboBox = new JComboBox();
		provinciaComboBox.setBounds(299, 54, 177, 24);
		frame.getContentPane().add(provinciaComboBox);
		
		JComboBox comunidadComboBox = new JComboBox();
		comunidadComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				//COMUNIDAD
				String url = "jdbc:mysql://127.0.0.1:3307/Spain";
				String user = "alumno";
				String password = "alumno";
				
				try {
					Connection con = DriverManager.getConnection(url, user, password);
		            String comunidadSeleccionada = (String) comunidadComboBox.getSelectedItem(); // Obtener el nombre de la comunidad seleccionada
					
		            PreparedStatement sel_pstmt = con.prepareStatement("SELECT nombreProvincia FROM provincia WHERE idComunidad = (SELECT idComunidad FROM comunidad WHERE nombreComunidad = ?)");
					 sel_pstmt.setString(1, comunidadSeleccionada); 
					 ResultSet rs_sel = sel_pstmt.executeQuery();
					 
			         provinciaComboBox.removeAllItems();

					 
					 while (rs_sel.next()) {
			
					 String nomprov=rs_sel.getString("nombreProvincia");			
					 provinciaComboBox.addItem(nomprov);
					 
					 }
					 rs_sel.close();
					 sel_pstmt.close();
					 con.close();
					} catch (SQLException e) {
					e.printStackTrace();
					}
			}
		});
		comunidadComboBox.setBounds(90, 56, 177, 24);
		frame.getContentPane().add(comunidadComboBox);
		
		
	
		
	
		//COMUNIDAD
		String url = "jdbc:mysql://127.0.0.1:3307/Spain";
		String user = "alumno";
		String password = "alumno";
			
		try {

			Connection con = DriverManager.getConnection(url, user, password);
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT nombreComunidad FROM comunidad");
      
			 while (rs.next()) {
	
				 String nomcom=rs.getString("nombreComunidad");
				 comunidadComboBox.addItem(nomcom);
				 }
			 
            	 rs.close();
            	 stmt.close();
            	 con.close();
            	} catch (SQLException e) {
            	e.printStackTrace();
            	}

		
		
		
		
		JLabel lblNewLabel = new JLabel("Comunidad Autonoma:");
		lblNewLabel.setBounds(90, 27, 177, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Provincia:");
		lblNewLabel_1.setBounds(299, 27, 70, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
	
	}
}
