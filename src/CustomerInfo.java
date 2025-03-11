import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInfo extends JPanel
{
    private JTextField customerName, customerAddress, customerTown, customerState, customerZip;

    //Here I am laying out all of the JLabels for the customer information

    public CustomerInfo(JFrame frame)
    {
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Customer Name"));
        customerName = new JTextField(15);
        add(customerName);

        add(new JLabel("Customer Address"));
        customerAddress = new JTextField(15);
        add(customerAddress);

        add(new JLabel("Customer Town"));
        customerTown = new JTextField(15);
        add(customerTown);

        add(new JLabel("Customer State"));
        customerState = new JTextField(15);
        add(customerState);

        add(new JLabel("Customer Zip"));
        customerZip = new JTextField(15);
        add(customerZip);

        //Here I created a confirm button for the CustomerInfo Popup window that will send the user
        //to the next screen and allow the JTextArea to print the informaiton

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Customer Info Saved!");

                SwingUtilities.getWindowAncestor(confirmBtn).dispose();


                if (frame instanceof InvoiceFrame) {
                    ((InvoiceFrame) frame).getLineItems();
                }
            }
        });
        add(confirmBtn);

        //Quit Button that quits out of the entire program

        JButton quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {
            System.exit(0);
        });
        add(quitBtn);
    }

    //Getters to allow the InvoiceFrame.java to pull the customer information to the frame for later organization
    //and display

    public String getCustomerName() {
        return customerName.getText();
    }

    public String getCustomerAddress() {
        return customerAddress.getText();
    }

    public String getCustomerTown() {
        return customerTown.getText();
    }

    public String getCustomerState() {
        return customerState.getText();
    }

    public String getCustomerZip() {
        return customerZip.getText();
    }
}
