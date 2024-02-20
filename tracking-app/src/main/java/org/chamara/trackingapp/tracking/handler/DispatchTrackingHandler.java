package org.chamara.trackingapp.tracking.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chamara.trackingapp.dispatch.message.DispatchPreparing;
import org.chamara.trackingapp.tracking.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DispatchTrackingHandler {

    @Autowired
    private final TrackingService trackingService;

    @KafkaListener(
            id = "dispatchTrackingConsumerClient",
            topics = "dispatch.tracking",
            groupId = "tracking.dispatch.tracking",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(DispatchPreparing payload) {

        log.info("📨📨📨 Received message from dispatch: {}", payload);
        try {
            trackingService.process(payload);
        } catch (Exception e) {
            log.error("Error processing message", e);
        }
    }
}
