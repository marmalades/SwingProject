package com.AppCode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App
{
    private JButton buttonMsg;
    private JPanel panelMain;
    private JTextField lastName;
    private JTextField firstName;
    private JRadioButton sophomoreRadioButton;
    private JRadioButton freshmanRadioButton;
    private JRadioButton juniorRadioButton;
    private JRadioButton seniorRadioButton;


    public App()
    {
        buttonMsg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                //TODO: add first and last verifier
                //TODO: clean GUI
                //TODO: add courses functionality

                //get classification from text
                //can be constants. decide if not
                String classification = "empty";
                String freshman = freshmanRadioButton.getText();
                String sophomore = sophomoreRadioButton.getText();
                String junior = juniorRadioButton.getText();
                String senior = seniorRadioButton.getText();


                //grab first and last name
                String first = firstName.getText();
                String last = lastName.getText();


                //radio button selection
                if (freshmanRadioButton.isSelected())
                {
                    classification = freshman;
                }
                else if (sophomoreRadioButton.isSelected())
                {
                    classification = sophomore;
                }
                else if (juniorRadioButton.isSelected())
                {
                    classification = junior;
                }
                else if (seniorRadioButton.isSelected())
                {
                    classification = senior;
                }
                else
                {
                    //request user to select a classification.
                    JOptionPane optionPane = new JOptionPane("Please select a classification!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }

                //if classification is not empty
                if (classification != "empty")
                {
                    //Info output pane
                    JOptionPane.showMessageDialog(null, "Name: " + first + " " + last +
                            "\nClassification: " + classification);
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


}
