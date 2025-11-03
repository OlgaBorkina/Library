package coffeeshot;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CoffeeOrderBoard {
    private Queue<Order> orders;
    private int countOrder;

    public CoffeeOrderBoard() {
        orders = new LinkedList<>();
        countOrder = 1;
    }

    public void add(String nameClient) {
        Order newOrder = new Order(countOrder++, nameClient);
        orders.add(newOrder);
    }

    public Order deliver(){
        Order order = orders.poll();
        if(order != null){
            System.out.println("Order delivered: " + order);
        }else{
            System.out.println("No orders in queue");
        }
        return order;
    }
    public Order deliver(int countOrder ){
       Iterator<Order> iterator = orders.iterator();
       while(iterator.hasNext()){
           Order order = iterator.next();
           if(order.getNumberOrder() == countOrder){
               System.out.println("Order delivered: " + order);
               iterator.remove();
               return order;
           }
       }
        return null;

    }
    public void draw (){
        System.out.println("Num|  Name ");
        for(Order ord : orders){
            System.out.println(ord.getNumberOrder() +"  |  "+ord.getNameClient());
        }
    }




}
