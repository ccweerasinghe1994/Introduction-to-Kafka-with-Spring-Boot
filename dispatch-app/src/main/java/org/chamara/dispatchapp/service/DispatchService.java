package org.chamara.dispatchapp.service;

import lombok.RequiredArgsConstructor;
import org.chamara.dispatchapp.message.DispatchPreparing;
import org.chamara.dispatchapp.message.OrderCreated;
import org.chamara.dispatchapp.message.OrderDispatched;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchService {
    private final static String ORDER_DISPATCHED_TOPIC = "order.dispatched";
    private final static String DISPATCH_TRACKING_TOPIC = "dispatch.tracking";
    private final KafkaTemplate<String, Object> kafkaProducer;

    public void process(OrderCreated payload) throws Exception {
        
        DispatchPreparing dispatchPreparing = DispatchPreparing.builder()
                .orderId(payload.getOrderId())
                .build();
        kafkaProducer.send(DISPATCH_TRACKING_TOPIC, dispatchPreparing).get();

        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(payload.getOrderId())
                .build();
        kafkaProducer.send(ORDER_DISPATCHED_TOPIC, orderDispatched).get();

    }

}
