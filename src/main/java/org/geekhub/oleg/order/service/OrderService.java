package org.geekhub.oleg.order.service;

import org.geekhub.oleg.order.model.Order;

import java.util.List;

public interface OrderService {
    void purchaseEvent(Order order);
    List<Order> getOrdersByUserId(long id);
    List<Order> getTopOrderedEvents();
    List<Order> getLastMonthOrders();
    List<Order> getLastOrder();
    void refundTicket(long id);
    Order getOrderById(long id);
}
