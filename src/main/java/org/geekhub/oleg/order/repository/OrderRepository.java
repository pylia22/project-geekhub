package org.geekhub.oleg.order.repository;

import org.geekhub.oleg.order.model.Order;

import java.util.List;

public interface OrderRepository {
    void purchaseEvent(Order order);
    List<Order> getOrdersByUserId(long userId);
    List<Order> getOrdersStatistic();
    List<Order> getLastMonthOrders();
    List<Order> getLastOrder();
    void refundTicket(long id);
    Order getOrderById(long id);
}
