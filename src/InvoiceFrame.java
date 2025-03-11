import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InvoiceFrame extends JFrame
{

    JPanel mainPnl, titlePnl, customerPnl, lineItemPnl;
    JTextArea customerArea, lineItemArea;
    JLabel titleLbl;
    JTextField invoiceTotal;



    public InvoiceFrame()
    {

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);
        createTitlePnl();
        createCustomerPnl();
        createLineItemPnl();


        setTitle("Invoice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenH = screenSize.height;
        int screenW = screenSize.width;
        int frameH = screenH * 1/4;
        int frameW = screenW * 1/5;

        setSize(frameW, frameH);
        setLocation((screenW - frameW) / 2, (screenH - frameH) / 2);

        getCustomerInfo();
        invoiceTotal.setText("Total: $0.00");
    }

    public void createTitlePnl(){
        titlePnl = new JPanel();
        titlePnl.setLayout(new BorderLayout());
        titleLbl = new JLabel("Invoice");

        titleLbl.setFont(new Font("Monospaced", Font.BOLD, 30));
        titlePnl.add(titleLbl, BorderLayout.CENTER);
        mainPnl.add(titlePnl, BorderLayout.NORTH);

    }

    public void createCustomerPnl(){
        customerPnl = new JPanel();
        customerPnl.setLayout(new BorderLayout());

        customerArea = new JTextArea();
        customerArea.setEditable(false);
        customerArea.setLineWrap(true);
        customerArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(customerArea);

        customerPnl.add(scrollPane, BorderLayout.CENTER);
        mainPnl.add(customerPnl, BorderLayout.CENTER);
    }

    public void createLineItemPnl(){
        lineItemPnl = new JPanel();
        lineItemPnl.setLayout(new BorderLayout());
        lineItemArea = new JTextArea();
        lineItemArea.setEditable(false);
        lineItemArea.setLineWrap(true);
        lineItemArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(lineItemArea);
        invoiceTotal = new JTextField("Total: $0.00");
        invoiceTotal.setEditable(false);

        lineItemPnl.add(scrollPane, BorderLayout.NORTH);
        lineItemPnl.add(invoiceTotal, BorderLayout.SOUTH);
        mainPnl.add(lineItemPnl, BorderLayout.SOUTH);
    }

    public void getCustomerInfo(){
        JDialog customerDialog = new JDialog(this, "Customer Info", true);
        customerDialog.setSize(400, 300);
        customerDialog.setLocationRelativeTo(this);

        CustomerInfo customerPanel = new CustomerInfo(this);
        customerDialog.add(customerPanel);
        customerDialog.setVisible(true);

        customerArea.setText(getCustomerDetailsFromPanel(customerPanel));

        getLineItems();
    }

    private String getCustomerDetailsFromPanel(CustomerInfo customerPanel){
        return customerPanel.getCustomerName() + "\n" +
                customerPanel.getCustomerAddress() + "\n" +
                customerPanel.getCustomerTown() +
                ", " + customerPanel.getCustomerState() +
                ", " + customerPanel.getCustomerZip();
    }

    public void getLineItems(){
        JDialog lineItemsDialog = new JDialog(this, "Line Items", true);
        lineItemsDialog.setSize(400, 300);
        lineItemsDialog.setLocationRelativeTo(this);

        LineItems lineItemsPanel = new LineItems(this);
        lineItemsDialog.add(lineItemsPanel);
        lineItemsDialog.setVisible(true);

        List<String> lineItems = lineItemsPanel.getLineItems();
        System.out.println("Line Items Retrieved: " + lineItems);

        if (!lineItems.isEmpty()) {
            StringBuilder itemsText = new StringBuilder(lineItemArea.getText());
            for (String item : lineItems) {
                itemsText.append(item).append("\n");
            }
            lineItemArea.setText(itemsText.toString());

            double totalAmount = lineItemsPanel.getTotalInvoiceAmount();
            System.out.println("Total Amount Retrieved in InvoiceFrame: " + totalAmount);
            invoiceTotal.setText("Total: $" + String.format("%.2f", totalAmount));
        } else {
            System.out.println("No items retrieved; skipping update.");
        }
        lineItemsDialog.dispose();
        revalidate();
        repaint();


    }


}
