package org.chamara.dispatchapp.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chamara.dispatchapp.message.OrderCreated;
import org.chamara.dispatchapp.service.DispatchService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreateHandler {
    private final DispatchService dispatchService;

    @KafkaListener(
            id = "orderConsumerClient",
            topics = "order.created",
            groupId = "dispatch.order.created.consumer"
    )
    public void listen(OrderCreated payload) {
        log.info("Received message: payload {}", payload);
        dispatchService.process(payload);
    }
}
