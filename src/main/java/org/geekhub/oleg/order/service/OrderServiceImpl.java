package org.geekhub.oleg.order.service;

import org.geekhub.oleg.order.model.Order;
import org.geekhub.oleg.order.repository.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepositoryImpl orderRepositoryImpl;

    @Autowired
    public OrderServiceImpl(OrderRepositoryImpl orderRepositoryImpl) {
        this.orderRepositoryImpl = orderRepositoryImpl;
    }

    @Override
    public void purchaseEvent(Order order) {
        orderRepositoryImpl.purchaseEvent(order);
    }

    @Override
    public List<Order> getOrdersByUserId(long id) {
        return orderRepositoryImpl.getOrdersByUserId(id);
    }

    @Override
    public List<Order> getTopOrderedEvents() {
        return orderRepositoryImpl.getOrdersStatistic();
    }

    @Override
    public List<Order> getLastMonthOrders() {
        return orderRepositoryImpl.getLastMonthOrders();
    }

    @Override
    public List<Order> getLastOrder() {
        return orderRepositoryImpl.getLastOrder();
    }

    @Override
    public void refundTicket(long id) {
        orderRepositoryImpl.refundTicket(id);
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepositoryImpl.getOrderById(id);
    }
}
