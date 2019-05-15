package com.AppCode;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class App {
    private JButton buttonMsg;
    private JPanel panelMain;
    private JTextField lastName;
    private JTextField firstName;
    private JRadioButton sophomoreRadioButton;
    private JRadioButton freshmanRadioButton;
    private JRadioButton juniorRadioButton;
    private JRadioButton seniorRadioButton;
    private String classification = "empty";

    public App()
    {
        buttonMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                //TODO: clean GUI
                //TODO: add courses functionality
                //get classification from text
                //can be constants. decide if not

                //grab first and last name
                String first = firstName.getText();
                String last = lastName.getText();


                if (freshmanRadioButton.isSelected()) {
                    classification = "Freshman";
                } else if (sophomoreRadioButton.isSelected()) {
                    classification = "Sophomore";
                } else if (juniorRadioButton.isSelected()) {
                    classification = "Junior";
                } else if (seniorRadioButton.isSelected()) {
                    classification = "Senior";
                }


                //if first/last name are empty
                //if classification is empty
                if (firstName.getText().trim().length() == 0 || lastName.getText().trim().length() == 0 || classification == "empty")
                {
                    //then display error
                    displayError();
                }
                else
                {
                    //display output
                    displayOutput(first, last);
                }
            }
        });
    }


    //JFrame stuffs
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Student Registration");
        frame.setContentPane(new App().panelMain);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    public void displayError()
    {
        //request user to select a classification.
        JOptionPane optionPane = new JOptionPane("Please enter name and classification!", JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Failure");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public void displayOutput(String first, String last)
    {
        JOptionPane.showMessageDialog(null, "Name: " + first + " " + last +
                "\nClassification: " + classification);
    }
}


