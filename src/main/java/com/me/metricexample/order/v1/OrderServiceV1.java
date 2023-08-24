package com.me.metricexample.order.v1;

import com.me.metricexample.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV1 implements OrderService {

    private final MeterRegistry registry;
    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV1(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();

        // io.micrometer꺼
        Counter counter = Counter.builder("my.order") // 메트릭 이름이 됨.
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("order")
                .register(registry);

        counter.increment(); // 이렇게 하면 메트릭 값이 1이 증가가 됨. 
    }

    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();

        Counter counter = Counter.builder("my.order") // 메트릭 이름이 됨.
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("cancel")
                .register(registry);

        counter.increment(); // 이렇게 하면 메트릭 값이 1이 증가가 됨.
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
