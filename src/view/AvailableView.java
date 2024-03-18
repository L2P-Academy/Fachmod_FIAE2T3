package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;


public class AvailableView extends JFrame {

	public AvailableView() {
		
		//Panel erstellen und Layout festlegen
		JPanel vehiclesAvailable = new JPanel();
		vehiclesAvailable.setLayout(new BorderLayout());
	
		
		JPanel westPanel = new JPanel();
		vehiclesAvailable.add(westPanel, BorderLayout.WEST);
		
		//Textarea mit Nachricht dass alle Fahrzeuge verfügbar sind
		JTextArea listOfAvailable = new JTextArea("Das sind die verfügbaren Fahrzeuge :D\n");
		listOfAvailable.setFont(new Font("Serif", Font.ITALIC, 18));
		listOfAvailable.setBackground(Color.lightGray);
		JScrollPane scrollPane = new JScrollPane(listOfAvailable); 
		listOfAvailable.setEditable(false);
		westPanel.add(scrollPane);
	
		this.add(vehiclesAvailable);
		this.setSize(400, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	
	}

}
