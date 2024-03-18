package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class RentedView extends JFrame {

	public RentedView() {
		
		//Panel erstellen und Layout festlegen
		JPanel vehiclesRented = new JPanel();
		vehiclesRented.setLayout(new BorderLayout());
	
		
		JPanel westPanel = new JPanel();
		vehiclesRented.add(westPanel, BorderLayout.WEST);
		
		//Textarea mit Nachricht dass keine Fahrzeuge vermietet sind
		JTextArea listOfRented = new JTextArea("Keine Fahrzeuge vermietet! :D\n" + "Du kannst sie alle haben!");
		listOfRented.setFont(new Font("Serif", Font.ITALIC, 18));
		listOfRented.setBackground(Color.lightGray);
		JScrollPane scrollPane = new JScrollPane(listOfRented); 
		listOfRented.setEditable(false);
		westPanel.add(scrollPane);
	
		this.add(vehiclesRented);
		this.setSize(400, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	
	}

}
