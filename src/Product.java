public class Product
{
    private String productName;
    private double productPrice;


    // Simple object class that provides the product name and price. These are set concretely in the LineItems class.
    public Product(String productName, double productPrice)
    {
        this.productName = productName;
        this.productPrice = productPrice;

    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String toString()
    {
        return productName + " $" + productPrice;
    }
}
