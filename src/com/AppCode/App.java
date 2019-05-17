package com.AppCode;


import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
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


                //mySQL connection info
                String host = "jdbc:mysql://localhost:3306/students";
                String user = "root";
                String pass = "torres";


                //TODO: clean GUI
                //TODO: add courses functionality

                //grab ID

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

                //get student ID
                studentID = generateID(host, user, pass);


                //if first/last name are empty
                //if classification is unselected
                if (firstNameTextField.getText().trim().length() == 0 || lastNameTextField.getText().trim().length() == 0 || classification == "empty") {
                    //then display error
                    displayError();
                } else {
                    //display output
                    displayOutput(first, last);
                    //getSQLStatement(firstNameTextField.getText(), lastNameTextField.getText(), classification, major);
                }


                //SQL
                try {
                    Connection connection = DriverManager.getConnection(host, user, pass);
                    System.out.println("Connected!");
                    Statement statement = connection.createStatement();

                    String query = " insert into users (studentID, firstName, lastName, Major, Classification)"
                            + " values (?, ?, ?, ?, ?)";

                    String rowCountQuery = "SELECT COUNT(*) FROM users";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, studentID);
                    preparedStatement.setString(2, first);
                    preparedStatement.setString(3, last);
                    preparedStatement.setString(4, major);
                    preparedStatement.setString(5, classification);


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

    public void displayOutput(String first, String last)
    {
        JOptionPane.showMessageDialog(null, "Name: " + first + " " + last +
                "\nClassification: " + classification);
    }

    //not how actual ID's could be generated. since everyone starts off as a freshman
    //TODO: redo logic
    public int generateID(String host, String user, String pass)
    {

        int rowNumber = 0;

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(host, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Getting rows!");
        try {
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String rowQuery = "select count(*) from users";
        PreparedStatement rowStatement = null;
        try {
            rowStatement = connection.prepareStatement(rowQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = rowStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                rowNumber = resultSet.getInt("studentID");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return rowNumber;

    }


//
}