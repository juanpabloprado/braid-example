package nh.graphql.braidexample.shopservice.domain;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class OrderRepository {

    private final List<Order> orders = new LinkedList<>();

    public Optional<Order> findById(String orderId) {
        return orders.stream()
            .filter(order -> orderId.equals(order.getId()))
            .findFirst();
    }

    public List<Order> findAll() {
        return Collections.unmodifiableList(orders);
    }

    @PostConstruct
    public void initOrders() {
        orders.add(
            new Order(
                    "order-1",
                    alsZeit("2020-03-17 13:02"),
                    new Customer("customer-1", "Klaus Müller", "Active")
                )
                .addOrderItem("orderitem-1-1", 2, "Jever")
                .addOrderItem("orderitem-1-2", 1, "Becks")
                .addOrderItem("orderitem-1-3", 4, "Mineralwasser")
        );

        orders.add(
            new Order(
                    "order-2",
                    alsZeit("2020-04-12 17:19"),
                    new Customer("customer-2", "Susi Meier", "Premium")
                )
                .addOrderItem("orderitem-2-1", 6, "Ratsherrn")
                .addOrderItem("orderitem-2-2", 12, "Holsten")
                .addOrderItem("orderitem-2-3", 1, "Gin")
        );
    }

    private static LocalDateTime alsZeit(String zeitstring) {
        return LocalDateTime.parse(zeitstring, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private static LocalDate alsDatum(String datumString) {
        return LocalDate.parse(datumString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
