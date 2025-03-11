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

    public LineItems(JFrame frame)
    {
        setLayout(new GridLayout(5, 2, 5, 5));
        selectedProducts = new ArrayList<>();

        Product[] products = {
                new Product("Laptop          ", 1399.99),
                new Product("Frozen Pizza", 4.99),
                new Product("Duracell AA  ", 9.99)
        };

        add(new JLabel("Select Product"));
        productSelect = new JComboBox(products);
        add(productSelect);

        add(new JLabel("Enter Quantity"));
        productQty = new JTextField(15);
        add(productQty);

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

        finishBtn = new JButton("Finish");
        finishBtn.addActionListener(e -> {
            System.out.println("Finish Button Pressed!");

            SwingUtilities.getWindowAncestor(finishBtn).dispose();
        });
        add(finishBtn);



        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> System.exit(0));
        add(quitBtn);


    }
    public List<String> getLineItems() {
        return selectedProducts;
    }
    public double getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }


}
