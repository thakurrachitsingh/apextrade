package com.rachit.apextrade.ingress;

import com.lmax.disruptor.EventHandler;
import com.rachit.apextrade.common.CommandType;
import com.rachit.apextrade.common.event.OrderCommandEvent;
import com.rachit.apextrade.matching.MatchingEngine;

public class OrderCommandHandler implements EventHandler<OrderCommandEvent> {

    private final MatchingEngine matchingEngine;

    public OrderCommandHandler(MatchingEngine matchingEngine) {
        this.matchingEngine = matchingEngine;
    }

    @Override
    public void onEvent(OrderCommandEvent event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getCommandType() == null) {
            return;
        }

        if (event.getCommandType() == CommandType.PLACE_ORDER) {
            matchingEngine.processOrder(
                    event.getUserId(),
                    event.getOrderId(),
                    event.getSide(),
                    event.getPrice(),
                    event.getQuantity(),
                    event.getOrderType(),
                    event.getTimestamp()
            );
        } else if (event.getCommandType() == CommandType.CANCEL_ORDER) {
            matchingEngine.cancelOrder(event.getOrderId());
        }
    }
}
