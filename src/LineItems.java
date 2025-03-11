import javax.swing.*;
import java.awt.*;

public class LineItems extends JPanel
{
    private JTextField itemName, itemQty, itemPrice;

    public LineItems(JFrame frame)
    {
        setLayout(new GridLayout(5, 2, 5, 5));
    }
}
