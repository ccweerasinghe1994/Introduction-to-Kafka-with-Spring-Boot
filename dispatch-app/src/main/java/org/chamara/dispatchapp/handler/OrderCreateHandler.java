package org.chamara.dispatchapp.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreateHandler {
    @KafkaListener(
            id = "orderConsumerClient",
            topics = "order.created",
            groupId = "dispatch.order.created.consumer"
    )
    public void listen(String payload) {
        log.info("Received message: payload {}", payload);
    }
}
