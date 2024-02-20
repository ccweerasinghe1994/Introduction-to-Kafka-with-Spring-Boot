package org.chamara.trackingapp.dispatch.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chamara.trackingapp.tracking.service.TrackingStatus;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackingStatusUpdated {
    UUID orderId;
    TrackingStatus status;
}
