package otopark;
import java.awt.Color;


import javax.swing.*;
public class kayıtsilme extends JFrame {
	
	
		JFrame f;
		JPanel p;
		JButton sil;
		JTable tablo;
		JScrollPane sc;
		String [] sutun= {"PLAKA","KALMA SÜRESİ","ÜCRET","GİRİŞ TARİHİ"};
		String [] [] satır= {{"Ali","veli","sedat","hüseyin"}};
		
		
		public kayıtsilme() {
		     super("Kayıt Sil");
	
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(400,600);
			this.setVisible(true);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			 p=new JPanel();
		      p.setLayout(null);
		    
		    sil=new JButton("Kaydı sil");
		    sil.setBounds(120, 500, 140, 40);
		    sil.setBackground(Color.RED);
		    sil.setForeground(Color.white);
		    
            tablo=new JTable(satır,sutun);
		
		    sc=new JScrollPane(tablo);
		    sc.setBounds(2,2,380,490); 
		   p.add(sc);
		   p.add(sil);
		   add(p);
		    
		    
		}
	}


