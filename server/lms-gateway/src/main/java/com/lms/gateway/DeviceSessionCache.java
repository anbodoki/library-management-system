package com.lms.gateway;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeviceSessionCache {

    private Map<Channel, DeviceSession> cache = new ConcurrentHashMap<>();

    private DeviceMessageHandlerFactory deviceMessageHandlerFactory;

    public DeviceSessionCache(DeviceMessageHandlerFactory deviceMessageHandlerFactory) {
        this.deviceMessageHandlerFactory = deviceMessageHandlerFactory;
    }

    public DeviceSession getSession(Channel channel) {
        if (cache.containsKey(channel)) {
            return cache.get(channel);
        } else {
            DeviceSession session = new DeviceSession(channel, deviceMessageHandlerFactory);
            cache.put(channel, session);
            return session;
        }
    }
}
