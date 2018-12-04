package otopark;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class kayıtsilme extends JFrame {
	
	
		JFrame f;
		JPanel p;
		JButton sil;
		JTable tablo;
		JScrollPane sc;
		String [] sutun= {"PLAKA"};
		String [] [] satır;

		
		
		public kayıtsilme() {
		     super("Kayıt Sil");
		     
				try {

		          Class.forName("com.mysql.jdbc.Driver");


		          String url = "jdbc:mysql://127.0.0.1:3306/otopark";
 

		          String kullaniciad = "root";

		 
		          String sifre = "toor";


		          Connection con; 


		          con = DriverManager.getConnection(url, kullaniciad, sifre);

		         
                   //Statement tanımlmaları.
		          Statement st=con.createStatement();
                  Statement st1=con.createStatement();
                   
                    
                   
		          System.out.println("Baglandi");
		         
		          
		           String sql = "SELECT PLAKALAR FROM enson"; 
	               ResultSet rs = st.executeQuery(sql);
	               
	               int index = 0;
	               
			          String sql1 ="select count(*)from enson"; 
			          ResultSet sonuc=st1.executeQuery(sql1);
				      
			          while(sonuc.next()) {
			        index = sonuc.getInt(1);
			         System.out.println(index);
				      }
				      sonuc.close();
				      int i=0;
				      satır =new String [index][1];
	            
				      while(rs.next()){
	               
	                String plaka = rs.getString("PLAKALAR");
	                
	 
	        		satır [i][0]= plaka;  
	        		
	        		i++; 
	        		  
	                System.out.print("PLAKALAR:  " + plaka +"\n");
	               
	            }
	            rs.close(); 
		     
	   
		     
			 
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(400,600);
			this.setVisible(true);
			
			this.setLocation(800, 20);
			this.setResizable(false);
			 p=new JPanel(); 
		      p.setLayout(null); 
		    
		      tablo=new JTable(satır,sutun);
		    sil=new JButton("Kaydı sil");
		    sil.setBounds(120, 500, 140, 40);
		    sil.setBackground(Color.RED);
		    sil.setForeground(Color.white);
	
		    final DefaultTableModel model = new DefaultTableModel(satır, sutun);

		    final JTable tablo = new JTable(model);
		    tablo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
         
		 sil.addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				
				if (tablo.getSelectedRow() != -1) {
			        
					 
					
					int row = tablo.getSelectedRow();
					
					String value = tablo.getModel().getValueAt(row, 0).toString();
				
					 
					  model.removeRow(tablo.getSelectedRow());
					  String sql3 ="DELETE FROM enson WHERE PLAKALAR LIKE "+ "'"+value+"'";
					   
					  try {
						  PreparedStatement st2=con.prepareStatement(sql3);
						  st2.executeUpdate();
					} catch (SQLException e) { 
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			        } 
			}
		}); 
		   
	 
		    sc=new JScrollPane(tablo);
		    sc.setBounds(2,2,380,490);  
		   p.add(sc);
		   p.add(sil);
		   add(p);
				 } catch (ClassNotFoundException ex) {


			          ex.printStackTrace();        


			         System.out.println("Sürücü projeye eklenmemiş!");


			      } catch (SQLException ex) { 


			          ex.printStackTrace();


			        System.out.println("Veritabanına bağlantı sağlanamadı!");


			      }
		    
		}
	}


