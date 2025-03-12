import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;


public class LineItems extends JPanel
{
    private JTextField productQty;
    private JComboBox productSelect;
    private List<String> selectedProducts;
    private JButton confirmBtn, quitBtn, finishBtn ;
    private double totalInvoiceAmount = 0.0;

    // here is where the LineItems popup frame is created and formatted, as well as the Products to select from are
    // concretely established here.

    public LineItems(JFrame frame)
    {
        setLayout(new GridLayout(5, 2, 5, 5));
        selectedProducts = new ArrayList<>();

        Product[] products = {
                new Product("Laptop          ", 1399.99),
                new Product("Frozen Pizza", 4.99),
                new Product("Duracell AA  ", 9.99)
        };

        //This JComboBox houses the products and allows the user to select from them.
        add(new JLabel("Select Product"));
        productSelect = new JComboBox(products);
        add(productSelect);

        //this JTextField allows the user to enter the quantity of the item that they previously selected in the JComboBox
        add(new JLabel("Enter Quantity"));
        productQty = new JTextField(15);
        add(productQty);

        //This confirm button, when pressed, does several things. This includes adding products, along with their calculated
        //prices for a single unit and a single unit * the quantity, to the selectedProducts list. Adds the item total
        //for each item to a running total for the entire order, and clears the quantity text field for another entry.
        confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = (Product) productSelect.getSelectedItem();
                String quantityString = productQty.getText();

                try {
                    int quantity = Integer.parseInt(quantityString);
                    double productPrice = product.getProductPrice();
                    double itemTotal = quantity * productPrice;
                    selectedProducts.add(product.getProductName() + "\t" + quantity + "\t$" + productPrice + "\t$" + itemTotal);

                    totalInvoiceAmount += itemTotal;
                    System.out.println("Running Total Updated: " + totalInvoiceAmount);
                    productQty.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Quantity");
                }
            }
        });
        add(confirmBtn);

        //This finish button, when pressed, gets rid of the LineItems popup frame and leaves the main InvoiceFrame as the
        // only frame on the screen. In the InvoiceFrame code, there is reference to this button to also initiate the printout
        // of the line items and totals in the text area.
        finishBtn = new JButton("Finish");
        finishBtn.addActionListener(e -> {
            System.out.println("Finish Button Pressed!");

            SwingUtilities.getWindowAncestor(finishBtn).dispose();
        });
        add(finishBtn);



        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> System.exit(0));
        add(quitBtn);

    //getters
    }
    public List<String> getLineItems() {
        return selectedProducts;
    }
    public double getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }


}
