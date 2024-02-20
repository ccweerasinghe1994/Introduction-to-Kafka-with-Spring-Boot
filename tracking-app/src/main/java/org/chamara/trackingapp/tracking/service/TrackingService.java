package org.chamara.trackingapp.tracking.service;

import lombok.RequiredArgsConstructor;
import org.chamara.trackingapp.dispatch.message.DispatchPreparing;
import org.chamara.trackingapp.dispatch.message.TrackingStatusUpdated;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final KafkaTemplate<String, Object> kafkaProducer;
    private final static String TRACKING_STATUS_TOPIC = "tracking.status";

    public void process(DispatchPreparing payload) throws Exception {
        TrackingStatusUpdated trackingStatusUpdated = TrackingStatusUpdated.builder()
                .orderId(payload.getOrderId())
                .status(TrackingStatus.PREPARING)
                .build();
        kafkaProducer.send(TRACKING_STATUS_TOPIC, trackingStatusUpdated).get();
    }
}
