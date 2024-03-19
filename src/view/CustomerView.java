package view;

import controller.XMLController;
import model.CustomerModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CustomerView extends JFrame{
    private JLabel nameLabel;
    private JLabel dateOfBirthLabel;
    private JLabel addressLabel;
    private JTextField nameTextField;
    private JTextField dateOfBirthTextField;
    private JTextField addressTextField;
    private JButton saveButton;
    private JPanel CustomerView;
    private CustomerModel newCustomer;
    private final XMLController myXMLController = new XMLController();

    public CustomerView() {
        setContentPane(CustomerView);
        setTitle("Create New Customer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //get parameters from form
                String name = nameTextField.getText();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date dayOfBirth = null;
                try {
                    dayOfBirth = formatter.parse(dateOfBirthTextField.getText());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                Date registeredAt = new Date();
                String address = addressTextField.getText();

                //create CustomerModel
                newCustomer = new CustomerModel(name, dayOfBirth, registeredAt, address);

                //send newCustomer to XMLController
                myXMLController.writeCustomerToList(newCustomer);

            }
        });
    }

}
