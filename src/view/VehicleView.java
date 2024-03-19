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
		
		//Initialisierung
		
		setTitle("Fahrzeugübersicht");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		// Erstellung des Hintergrundpanels + Alignment

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.darkGray);
		
		JMenuBar jMenuBar = new JMenuBar();

		// Erstellung des linken Panels

		JPanel westPanel = new JPanel();
		mainPanel.add(westPanel, BorderLayout.WEST);


		// Erstellen des unteren Panels + Hinzufügen von Button
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.darkGray);
		mainPanel.add(southPanel, BorderLayout.SOUTH);

		// Button für RentedView mit ActionListener
		JButton rentedVehicleButton = new JButton("Vermietete Fahrzeuge");
		rentedVehicleButton.setBackground(Color.white);
		southPanel.add(rentedVehicleButton);
		rentedVehicleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				VehicleView.this.setVisible(false);
				RentedView rentedView = new RentedView();
				rentedView.setVisible(true);
			}
		});

		
		String[] columnNames = {"VehicleType",
		 "Model", "RentStatus", "Picture"};
		//Für Testzwecke!!!!
		String[][] data = {
		 {"Model 1", "false", "picture", "lol"}, {"Model 2", "true", "picture", "lol"}, {"Model 3",
		 "true", "picture", "lol"}, {"Model 4", "false", "picture", "lol"} };
		 
		 JTable overview = new JTable(data, columnNames);
		 JScrollPane secondScrollPane = new JScrollPane(overview);
		 westPanel.add(secondScrollPane);
		 

		
		
		getContentPane().add(mainPanel);
		setVisible(true);

	}
}