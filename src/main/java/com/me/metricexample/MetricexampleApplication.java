package com.me.metricexample;

import com.me.metricexample.order.v0.OrderConfigV0;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(OrderConfigV0.class) // 이렇게만 해두면 나머지 다 들어가버림. 그래서 ComponentScan 위치를 정해둔다.
public class MetricexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricexampleApplication.class, args);
	}

}
