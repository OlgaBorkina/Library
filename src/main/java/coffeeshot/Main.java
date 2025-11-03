package coffeeshot;


public class Main {
    public static void main(String[] args) {
            CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();

        coffeeOrderBoard.add("Alice");
        coffeeOrderBoard.add("Bob");
        coffeeOrderBoard.add("Charlie");
        coffeeOrderBoard.add("Alice");
        coffeeOrderBoard.add("Bob");

        coffeeOrderBoard.draw();
        System.out.println("Coffee Order Board");
        Order orderDelivered = coffeeOrderBoard.deliver();
        coffeeOrderBoard.draw();

        orderDelivered = coffeeOrderBoard.deliver(3);
        coffeeOrderBoard.draw();





    }
}
