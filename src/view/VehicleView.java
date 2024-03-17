package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleView extends JFrame {
		
		public VehicleView() {
			
			//Erstellung des Hintergrundpanels + Alignment
			
			JPanel mPanel = new JPanel();
			mPanel.setLayout(new BorderLayout());
			mPanel.setBackground(Color.darkGray);
			
			//Erstellung des linken Panels
		
			JPanel lPanel = new JPanel();
			mPanel.add(lPanel, BorderLayout.WEST);
			
			//Liste mit Fahrzeugen und Hinzufügen zu linkem Panel
			JTextArea vehicleList = new JTextArea(
				    "Fahrzeug A \n" +
				    "Fahrzeug B \n" +
				    "Fahrzeug C \n" +
				    "Fahrzeug D \n"
				);
			vehicleList.setFont(new Font("Serif", Font.ITALIC, 18));
			vehicleList.setLineWrap(true);
			vehicleList.setBackground(Color.lightGray);
			JScrollPane scrollPane = new JScrollPane(vehicleList); 
			vehicleList.setEditable(false);
			lPanel.setBackground(Color.lightGray);
			lPanel.add(scrollPane);
			
			//Erstellen des unteren Panels + Hinzufügen von Button
			JPanel uPanel = new JPanel();
			uPanel.setBackground(Color.darkGray);
			mPanel.add(uPanel, BorderLayout.SOUTH);
			
			//Button für RentedView mit ActionListener
			JButton rentedVehicleButton = new JButton("Vermietete Fahrzeuge");
			rentedVehicleButton.setBackground(Color.white);
			uPanel.add(rentedVehicleButton);
			rentedVehicleButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        
			        VehicleView.this.setVisible(false);
			        RentedView opening = new RentedView();
			        opening.setVisible(true);
			    }
	        });
		
			/*
		//Erstellen der Tabelle für Autos + Hinzufügen zu lPanel FUNKTONIERT NICHT, SOBALD DIE GRÖßE verändert wird WARUM?
			String[] columnNames = {"VehicleType", "Model", "RentStatus", "Picture"};
			//Für Testzwecke!!!!
			Object[][] data = {
					{"Model 1", true, "picture"},
					{"Model 2", false, "picture"},
					{"Model 3", true, "picture"},
					{"Model 4", false, "picture"}
			};
			
			JTable overview = new JTable(data, columnNames);
			JScrollPane secondScrollPane = new JScrollPane(overview);
			lPanel.add(secondScrollPane);
			*/
			
		
			this.add(mPanel);
		
			this.setSize(400, 600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		
		}
		
		
		//Für Testzwecke:

		public static void main(String[] args) {
			VehicleView view = new VehicleView();
	  }

}
