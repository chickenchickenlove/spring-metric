package com.me.metricexample.order;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {
    void order();

    void cancel();

    AtomicInteger getStock();
}
