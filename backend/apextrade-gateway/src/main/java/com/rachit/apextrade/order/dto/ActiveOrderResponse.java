package com.rachit.apextrade.order.dto;

import com.rachit.apextrade.common.Side;

public record ActiveOrderResponse(
        long orderId,
        Side side,
        long price,
        long originalQuantity,
        long remainingQuantity,
        String status,
        long timestampNs
) {
}

