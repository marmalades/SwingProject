package com.codebind;

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


                freshmanRadioButton.setActionCommand(freshmanRadioButton.getText());
                sophomoreRadioButton.setActionCommand(sophomoreRadioButton.getText());
                juniorRadioButton.setActionCommand(juniorRadioButton.getText());
                seniorRadioButton.setActionCommand(seniorRadioButton.getText());

                String freshman = freshmanRadioButton.getText();
                String sophomore = sophomoreRadioButton.getText();
                String junior = juniorRadioButton.getText();
                String senior = seniorRadioButton.getText();



                String first = firstName.getText();
                String last = lastName.getText();
                String classification = "empty";

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
                    JOptionPane.showMessageDialog(null, "Please select a classification!");
                }

                JOptionPane.showMessageDialog(null, "Name: " + first + " " + last +
                                                                          "\nClassification: " + classification);


            }
        });
    }

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
