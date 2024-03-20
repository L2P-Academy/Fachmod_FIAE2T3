package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.table.TableModel;


public class VehicleView extends JFrame {

	public VehicleView() {
		
		//Initialisierung
		
		setTitle("Fahrzeugübersicht");
		setSize(1800, 1300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// Erstellung des Hintergrundpanels + Alignment

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.darkGray);
		
//		JMenuBar jMenuBar = new JMenuBar();
		
		//Fenster zum Betrachten von Bildern
		JWindow pictureWindow = new JWindow();
		pictureWindow.setSize(1600, 900);
		
		//Listener zum Ausblenden von Bildern
		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pictureWindow.setVisible(false); // Hide the floating window
            }
        });


		// Erstellung des linken Panels

		JPanel westPanel = new JPanel();
		mainPanel.add(westPanel, BorderLayout.WEST);


		
		
		// Erstellen des unteren Panels + Hinzufügen von Button
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.darkGray);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		JPanel eastPanel = new JPanel();
		mainPanel.add(eastPanel, BorderLayout.EAST);

		
		
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

		//TESTSTRING
		String[][] allData = {
				{"Car", "1988", "300 059", "Chevy", "rot, blau", "80", "true", "3", "nein"},
				{"Motorcycle", "2024", "000 002", "BMW", "schwarz", "75", "false", "1", "ja"},
				{"Car", "2000", "153 368", "Ford", "grün", "80", "true", "8", "ja"},
				{"Car", "2016", "200 000", "Fiat", "gelb", "40", "false", "3", "nein"},
				{"Motorcycle", "1988", "300 059", "Harley", "rot, blau", "80", "true", "A", "nein"},
				{"Motorcycle","2024", "000 002", "Ducati", "schwarz", "75", "false", "A1", "ja"},
				{"Car","2000", "153 368", "BMW", "grün", "80", "true", "A2", "ja"},
				{"Motorcycle","2016", "200 000", "Yamaha", "gelb", "40", "false", "A", "nein"}
		};
		
		
		
		//Aufruf der Sortierfunktion für die Data-Arrays
		String[][] carData = sortData(allData, "Car");
		String[][] motorcycleData = sortData(allData, "Motorcycle");

		String[] carColumnNames = {"Baujahr", "Kilometerstand", "Modell", "Farbe", "Preis pro Tag", "Leihstatus", "Anzahl Türen", "Klimaanlage?"};
		String[] motorcycleColumnNames = {"Baujahr", "Kilometerstand", "Modell", "Farbe", "Preis pro Tag", "Leihstatus", "Führerscheinklasse", "Topcase vorhanden?"};
		
		
		
		//Initialisierung der Tabelle für Autos
		JTable carOverview = new JTable(carData, carColumnNames);
		JScrollPane secondScrollPane = new JScrollPane(carOverview);
		westPanel.add(secondScrollPane);
		TableModel carModel = carOverview.getModel();
		
		
	
		//Timer um die Verweildauer der Maus zu bestimmen und zubestimmen
		//was passiert wenn sie lang genug verbleibt
		Timer timer = new Timer(1000, e -> {

			System.out.println("The Timer counted correctly!");
		    pictureWindow.setLocationRelativeTo(mainPanel);
		    pictureWindow.setVisible(true);
//		    carOverview.setBackground(Color.DARK_GRAY);
		});
		timer.setRepeats(false);
		
		
		//Listener für die carTabelle
		carOverview.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				    int row = carOverview.rowAtPoint(e.getPoint());
				    int column = carOverview.columnAtPoint(e.getPoint());
					System.out.println(row);
					System.out.println(column);
					
			//Anzeige variierend nach Zelle über der die Maus schwebt
					if (column == 2) 
					{
						timer.restart();
						Object value = carModel.getValueAt(row, 2);
						String switchC = value.toString();
						System.out.print(switchC);
						switch (switchC) {
						case "BMW":
							
							ImageIcon cBmwIcon = new ImageIcon("res/img/BMW.jpeg");//Evtl. Variable gleicher name, weil Unterschied nicht nötig
							JLabel pictureBLabel = new JLabel(cBmwIcon);
							pictureWindow.getContentPane().add(pictureBLabel);
							pictureWindow.pack();
							break;
							
						case "Chevy":
							
							ImageIcon cChevIcon = new ImageIcon("res/img/Auto-Chevrolet-Corvette.jpg");
							JLabel pictureCLabel = new JLabel(cChevIcon);
							pictureWindow.getContentPane().add(pictureCLabel);
							pictureWindow.pack();
							break;
							
						}
					} else {
                        timer.stop();
                        pictureWindow.setVisible(false);
                    }
			}
		});
		//Stellt sicher, dass Bilder/Veränderungen wieder verschwinden, wenn die Maus sich weiterbewegt
		 carOverview.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseExited(MouseEvent e) {
                  
					pictureWindow.setVisible(false);
					pictureWindow.getContentPane().removeAll();
					pictureWindow.revalidate();
					pictureWindow.repaint();
                 timer.stop();
             }
         });
			
		//Initialisierung der Tabelle für Motorräder
		JTable motorcycleOverview = new JTable(motorcycleData, motorcycleColumnNames);
		JScrollPane thirdScrollPane = new JScrollPane(motorcycleOverview);
		eastPanel.add(thirdScrollPane);
		
		motorcycleOverview.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
			// do some stuff
				}
			}
		});

		
		
		
		
		getContentPane().add(mainPanel);
		setVisible(true);

	}
	
	
	
	
	
	//Funktion zum Sortieren von Daten
	public String[][] sortData(String[][] vehicleData, String type) {
		int length = vehicleData.length;
		
		int timesType = 0;
		//Zählen des Anzahl der Fahrzeuge nach gesuchtem Typ
		for (int i = 0; i < length; i++) {
			if (vehicleData[i][0].equals(type)) {
				timesType++;
			}
		}
		//Erstellen des nach Typ sortierten Arrays
		String[][] sortedData = new String[timesType][8];
		int secondC = 0;
		for (int i = 0; i < length; i++) {
			if (vehicleData[i][0].equals(type)) {
			for (int j = 1; j < 9; j++)
				sortedData[secondC][j-1] = vehicleData[i][j];
				secondC++;
			}
		}
		return sortedData;
	}
}