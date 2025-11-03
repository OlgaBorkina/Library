package coffeeshot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CoffeeOrderBoard {
    private static final Logger logger = LogManager.getLogger(CoffeeOrderBoard.class);
    private Queue<Order> orders;
    private int countOrder;

    public CoffeeOrderBoard() {
        orders = new LinkedList<>();
        countOrder = 1;
    }

    public void add(String nameClient) {
        try {
            Order newOrder = new Order(countOrder++, nameClient);
            orders.add(newOrder);
            logger.info("Order added: " + newOrder);
        } catch (Exception e) {
            logger.error("Error adding order ", e);
       }
    }

    public Order deliver() {
        Order order = orders.poll();

        if (order != null) {
          int n =  order.getNumberOrder();
            logger.info("Order delivered:  " + n);
        } else {
            logger.info("No orders in queue");
        }
        return order;
    }

    public Order deliver(int countOrder) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getNumberOrder() == countOrder) {
                logger.info("Order delivered: " + countOrder);
                iterator.remove();
                return order;
            }
        }
        return null;

    }

    public void draw() {
        System.out.println("Num|  Name ");
        for (Order ord : orders) {
            System.out.println(ord.getNumberOrder() + "  |  " + ord.getNameClient());
        }
    }


}
