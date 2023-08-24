package com.me.metricexample.order.gauge;

import com.me.metricexample.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfigV1 {


    @Bean
    public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry meterRegistry) {
        return new MyStockMetric(orderService, meterRegistry);
    }

    @Slf4j
    static class MyStockMetric {
        private OrderService orderService;
        private MeterRegistry meterRegistry;

        public MyStockMetric(OrderService orderService, MeterRegistry meterRegistry) {
            this.orderService = orderService;
            this.meterRegistry = meterRegistry;
        }

        @PostConstruct
        public void init() {
            // 이제부터 외부에서 호출이 들어올 때 마다, 람다식이 호출된다. 리턴값이 측정값으로 사용된다.
            Gauge.builder("my.stock", orderService, service -> {
                log.info("sotkc gauge call");
                return orderService.getStock().get();
            }).register(meterRegistry);
        }
    }

}
