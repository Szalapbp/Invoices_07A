import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInfo extends JPanel
{
    private JTextField customerName, customerAddress, customerTown, customerState, customerZip;

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

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Customer Info Saved!");

                frame.getContentPane().removeAll();
                frame.add(new LineItems(frame));
                frame.revalidate();
                frame.repaint();
            }
        });
        add(confirmBtn);

        JButton quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {
            System.exit(0);
        });
        add(quitBtn);
    }
}
