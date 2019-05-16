package com.AppCode;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App
{
    //panel
    private JButton buttonMsg;
    private JPanel panelMain;

    //user info
    private int studentID;
    private JTextField lastNameTextField;
    private JTextField firstNameTextField;
    private JTextField majorTextField;
    private String classification = "empty";

    //radio button group
    private JRadioButton sophomoreRadioButton;
    private JRadioButton freshmanRadioButton;
    private JRadioButton juniorRadioButton;
    private JRadioButton seniorRadioButton;

    public App()
    {

        buttonMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {

                //TODO: clean GUI
                //TODO: add courses functionality

                //grab text from inputs
                String first = firstNameTextField.getText();
                String last = lastNameTextField.getText();
                String major = majorTextField.getText();

                //Radio button group selection
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
                //if classification is unselected
                if (firstNameTextField.getText().trim().length() == 0 || lastNameTextField.getText().trim().length() == 0 || classification == "empty")
                {
                    //then display error
                    displayError();
                } else
                    {
                        //display output
                        displayOutput(first, last);
                        getSQLStatement(firstNameTextField.getText(), lastNameTextField.getText(), classification, major);
                    }


                //mySQL connection info
                String host = "jdbc:mysql://localhost:3306/students";
                String user = "root";
                String pass = "torres";

                //SQL
                try {
                    Connection connection = DriverManager.getConnection(host, user, pass);
                    System.out.println("Connected!");
                    Statement stmt = connection.createStatement();

                    String query = " insert into users (StudentID, First_Name, Last_Name, Major, Classification)"
                            + " values (?, ?, ?, ?, ?)";

                    //create preparedStatement
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, s);
                    preparedStatement.setString(2, first);
                    preparedStatement.setString(3, last);
                    preparedStatement.setString(4, major);
                    preparedStatement.setString(5, classification);

                    //  ResultSet rs = stmt.executeQuery(query);
                    try {
                        preparedStatement.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//            while (rs.next())
//            {
//                int ID_col = rs.getInt("StudentID");
//                String first_name = rs.getString("First_Name");
//                String last_name = rs.getString("Last_Name");
//                String major = rs.getString("Major");
//                String classification = rs.getString("Classification");
//
//                String print = ID_col + " " + first_name + " " + last_name + " " + major;
//                System.out.println(print);

                } catch (SQLException error) {
                    error.printStackTrace();
                    System.out.println(error.getMessage());
                }
            }//action performed ^^

        });//action listener

    }//app


    public static void main(String[] args)
    {


        //JFrame stuff
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

        public void displayOutput (String first, String last)
        {
            JOptionPane.showMessageDialog(null, "Name: " + first + " " + last +
                    "\nClassification: " + classification);
        }

        public int generateID(String classification, JTextField lastNameTextField)
        {
            int suffix = Integer.parseInt(lastNameTextField.getText());
            if (classification == "freshman")
            {
                return 1 + suffix;
            }
            else if (classification == "sophomore")
            {
                return 2 + suffix;
            }
            else if (classification == "junior")
            {
                return 3 + suffix;
            }
            else if(classification == "senior")
            {
                return 4 + suffix;
            }
            return 999;
        }


//        public String getSQLStatement (String first, String last, String major, String classification)//,String classification)
//        {
//            return "INSERT INTO students.users (studentID, First_Name, Last_Name, Major, Classification) VALUES (" +
//           a         first + "," + last + "," + major + classification + ");";
//        }
}