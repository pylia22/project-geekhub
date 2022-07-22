package org.geekhub.oleg.order.controller;

import org.geekhub.oleg.event.service.EventServiceImpl;
import org.geekhub.oleg.order.model.Order;
import org.geekhub.oleg.order.service.OrderExelExporter;
import org.geekhub.oleg.order.service.OrderPDFExporter;
import org.geekhub.oleg.order.service.OrderServiceImpl;
import org.geekhub.oleg.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final EventServiceImpl eventService;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl, UserServiceImpl userServiceImpl, EventServiceImpl eventService) {
        this.orderServiceImpl = orderServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.eventService = eventService;
    }

    @PostMapping("/event/{id}")
    public String purchaseEvent(@PathVariable("id") long id, @RequestParam("quantity") int quantity) {
        Order order = new Order();
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        order.setEvent(eventService.getEventById(id));
        order.setQuantity(quantity);
        order.setUser(userServiceImpl.findByLogin(login));
        orderServiceImpl.purchaseEvent(order);

        return "order/success";
    }

    @GetMapping("/event/cabinet")
    public String getAllOrdersByUserId(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        long userId = userServiceImpl.findByLogin(userDetails.getUsername()).getId();
        model.addAttribute("orders", orderServiceImpl.getOrdersByUserId(userId));

        return "user/cabinet";
    }

    @GetMapping("event/order/refund/{id}")
    public String refundTicket(@PathVariable("id") long id) {
        LocalDateTime eventDate = orderServiceImpl.getOrderById(id).getEvent().getDate();
        if (eventDate.isAfter(LocalDateTime.now())) {
            orderServiceImpl.refundTicket(id);
            return "order/refund-success";
        }
        return "order/error";
    }

    @GetMapping("/event/analytic")
    public String getTopOrderedEvents(Model model) {
        model.addAttribute("orders", orderServiceImpl.getTopOrderedEvents());

        return "admin/analytic";
    }

    @GetMapping("event/analytic/last-month-orders")
    public String getLastMonthOrders(Model model) {
        model.addAttribute("lastMonthOrders", orderServiceImpl.getLastMonthOrders());

        return "admin/last-month-orders";
    }

    @GetMapping("event/export")
    public void exportTicketPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ticket.pdf";
        response.setHeader(headerKey, headerValue);

        OrderPDFExporter exporter = new OrderPDFExporter(orderServiceImpl.getLastOrder().get(0));
        exporter.export(response);
    }

    @GetMapping("event/analytic/export")
    public void exportTopOrdersToExel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=statistic.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Order> topOrderedEvents = orderServiceImpl.getTopOrderedEvents();
        OrderExelExporter exporter = new OrderExelExporter(topOrderedEvents);
        exporter.export(response);
    }
}
