package com.yyovo.adam.common.base.enums;

import lombok.Getter;

@Getter
public enum MessageQueue {

    ADAM_NOTIFY_MSG("wx.pay.notify.queue.name", "wx.pay.notify.exchange.name", "wx.pay.notify.routing.key.name");


    private final String queue;

    private final String exchange;

    private final String routing;

    MessageQueue(String queue, String exchange, String routing) {
        this.queue = queue;
        this.exchange = exchange;
        this.routing = routing;
    }
}
