package otopark;

import java.awt.*;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.util.Date;


public class otopark extends JFrame {
	
	
	  int aracSayisi=0;
	JFrame frame;
	JPanel panel;
	JButton btn_login;
	JButton Kayıtsil;
	JTextField txt_plaka;
	JLabel lbl_plaka;
	JRadioButton rd_btn2;
	JComboBox combo;
	JLabel zaman;
	JLabel durum;
    JProgressBar bar;
    JLabel bardurum; 
   
 
  public otopark() {
	 
	  super("Otopark Otomasyonu");
	  
	  
 
		try {


          Class.forName("com.mysql.jdbc.Driver");


          String url = "jdbc:mysql://127.0.0.1:3306/otopark";


          String kullaniciad = "root";

 
          String sifre = "toor";


          Connection con;

 
          con = DriverManager.getConnection(url, kullaniciad, sifre);


          Statement st=con.createStatement();
          Statement sts=con.createStatement();

          System.out.println("Baglandi");
      
          
          

	  
	  setSize(300,380);
	  
	  setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      
      panel=new JPanel();
      panel.setLayout(null);
    
      	lbl_plaka=new JLabel("Plakayı (34 HKY 2312) şeklinde giriniz   ");
      	lbl_plaka.setBounds(30, 20, 300, 20);
      		
      			txt_plaka=new JTextField();
      			txt_plaka.setBounds(65, 50, 150, 20);
      		     
      		   
      				btn_login = new JButton("Otoparka Giriş");
      				btn_login.setBounds(70, 150, 140, 40);
      				btn_login.setBackground(Color.DARK_GRAY);
      				btn_login.setForeground(Color.white);
      
      			durum=new JLabel("Araç Durumu :");
      			durum.setBounds(20,115,90,20);
      				
      					rd_btn2=new JRadioButton("ÜCRET ALINDI");
      					rd_btn2.setBounds(115,115, 150,20 );
      
      						zaman=new JLabel("Kalma Süresi :"); 
      						zaman.setBounds(20,80,100,20); 
      	
      combo = new JComboBox();
       
        combo.addItem("Belirtilmedi");
        combo.addItem("30 Dakikadan az");
        combo.addItem("30 Dakika");
        combo.addItem("1 Saat");
        combo.addItem("3 Saatten az");
        combo.addItem("6 saatten az");
        combo.addItem("12 saatten az");
        combo.addItem("1 Gün");
        combo.addItem("3 Günden az");
        combo.addItem("1 hafta");
        combo.addItem("1 haftadan fazla");
       
      combo.setBounds(115, 80, 120, 20);
      
      
          Kayıtsil = new JButton("Kayıt Sil");
          Kayıtsil.setBounds(70,200,140,40);
          Kayıtsil.setBackground(Color.RED);
          Kayıtsil.setForeground(Color.WHITE);
          
          bardurum=new JLabel("Doluluk Oranı (Araç Kapasitesi 100)");
          bardurum.setBounds(40,258,230,20);
          
          
          String sqlSayi ="select count(*)from enson"; 
          ResultSet sonuc=sts.executeQuery(sqlSayi);
          while(sonuc.next()) {
        	  aracSayisi=sonuc.getInt(1);
          }
         
         
          
           bar = new JProgressBar();
          bar.setStringPainted(true);
          bar.setFont(new Font("Dialog", Font.BOLD, 14));
  	      bar.setForeground(Color.GRAY);
  		  bar.setBackground(Color.GREEN);
  		  bar.setValue(aracSayisi);
          bar.setBounds(40, 280,200,20);
          
           
          
       
      panel.add(rd_btn2);  
      panel.add(lbl_plaka);
      panel.add(txt_plaka);
      panel.add(btn_login);
      panel.add(combo);
      panel.add(zaman);
      panel.add(durum); 
      panel.add(Kayıtsil);
      panel.add(bar);
      panel.add(bardurum);
      add(panel);
      
      
    setVisible(true);
      
      
      
      /// !!!!!!!!!!!!!!!!!!!1 BUTON ACTİON
      
btn_login.addActionListener(new ActionListener() {
		 
		@Override
public void actionPerformed(ActionEvent e) {
			
			
			  bar.setValue(aracSayisi);
			 
	if(txt_plaka.getText().length()>5   ) {
		
		
			
				  String sql="INSERT INTO enson VALUES (?,?,?,?)";
		int deger=0;
		deger=bar.getValue()+1;
		bar.setValue(deger);
			         
			         
				  
				   
		 try { 
					PreparedStatement st=con.prepareStatement(sql);
					
					String esas=txt_plaka.getText(); 
					esas=esas.toUpperCase(); 
					String icerik=(String) combo.getSelectedItem();
					
					  st.setString(1, esas);
					 
					  st.setString(2, icerik);
				 	   
					
					  if(rd_btn2.isSelected()) {
						  st.setString(3, "Alındı");
					  }else {
						  st.setString(3, "Alınmadı");
					  } 
					  Date zeman = new Date(); 
			     	  String zemanu = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ").format(zeman);
					 
					  
					st.setString(4,zemanu); 
					 
					  st.executeUpdate();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace(); 
				} 
		 
		     		  Date simdikiZaman = new Date(); 
		     		 String str = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ").format(simdikiZaman);
			JOptionPane.showMessageDialog(null, "ARAÇ OTOPARKA GİRİŞ YAPTI.\n"+str.toString(),"GİRİŞ",JOptionPane.DEFAULT_OPTION); 
			
			 
			}
	
		
	
		else { 
				JOptionPane.showMessageDialog(null, "LÜTFEN BELİRTİLEN KURALA UYGUN PLAKA GİRİNİZ.","HATA!",JOptionPane.WARNING_MESSAGE);
				
			}
	
			 
	txt_plaka.setText("");
	
	 
	  
	String sure1=(String) combo.getSelectedItem();
	 if(sure1=="Belirtilmedi") {
		   
		 rd_btn2.doClick(1);
	 }
	
	
	 rd_btn2.doClick(0);
	 
	combo.setSelectedIndex(0); 
	
 
	
		}
		
	});


Kayıtsil.addActionListener(new ActionListener() {
	 
	@Override
public void actionPerformed(ActionEvent e) { 
		
		
		
     kayıtsilme elm=new kayıtsilme(); 
   
	};
	}); 
       


	      } catch (ClassNotFoundException ex) {


	          ex.printStackTrace();        


	         System.out.println("Sürücü projeye eklenmemiş!");


	      } catch (SQLException ex) {


	          ex.printStackTrace();


	        System.out.println("Veritabanına bağlantı sağlanamadı!");


	      }

      
      
  } 
    
	
} 


