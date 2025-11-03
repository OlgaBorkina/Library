package coffeeshot;

public class Order {
    private int numberOrder;
    private String nameClient;

    public Order(int numberOrder, String nameClient) {
        this.numberOrder = numberOrder;
        this.nameClient = nameClient;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public String getNameClient() {
        return nameClient;
    }

    @Override
    public String toString() {
        return "numberOrder =" + numberOrder +
                ", nameClient =" + nameClient;
    }
}
