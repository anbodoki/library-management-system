package com.lms.gateway;

import com.lms.gateway.model.*;
import io.netty.channel.Channel;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceSession {

    private Channel channel;
    private DeviceMessageHandlerFactory deviceMessageHandlerFactory;

    public DeviceSession(Channel channel, DeviceMessageHandlerFactory deviceMessageHandlerFactory) {
        this.channel = channel;
        this.deviceMessageHandlerFactory = deviceMessageHandlerFactory;
    }

    public void packetReceived(Packet packet) {
        switch (packet.getMessageType()) {
            case CHECK_CLIENT:
                switch (packet.getMessageStatus()) {
                    case IN_PROGRESS:
                        processCheckClient(packet);
                    case ERROR:
                    case OK:
                        channel.close();
                        break;
                }
                break;
            case CHECK_BOOK:
                switch (packet.getMessageStatus()) {
                    case IN_PROGRESS:
                        processCheckBook(packet);
                    case ERROR:
                    case OK:
                        channel.close();
                        break;
                }
                break;
            case SUBMIT:
                switch (packet.getMessageStatus()) {
                    case IN_PROGRESS:
                        processSubmit(packet);
                    case ERROR:
                    case OK:
                        channel.close();
                        break;
                }
                break;
        }
    }

    private void processSubmit(Packet packet) {
        try {
            RequestSubmitMessageData messageData = (RequestSubmitMessageData) packet.getMessageData();
            String response = deviceMessageHandlerFactory.processSubmit(messageData.getBookId(), messageData.getClientId(), messageData.getDate());
            ResponseSubmitMessageData result = new ResponseSubmitMessageData();
            result.setData(response);
            packet.setDate(new Date());
            packet.setMessageData(result);
            packet.setMessageStatus(MessageStatus.OK);
            channel.writeAndFlush(packet);
        } catch (Exception e) {
            packet.setMessageData(new ErrorResponseData());
            packet.setDate(new Date());
            packet.setMessageStatus(MessageStatus.ERROR);
            channel.writeAndFlush(packet);
        }
    }

    private void processCheckBook(Packet packet) {
        try {
            RequestCheckBookMessageData messageData = (RequestCheckBookMessageData) packet.getMessageData();
            String response = deviceMessageHandlerFactory.processCheckBook(messageData.getBookIdentifier());
            ResponseCheckBookMessageData result = new ResponseCheckBookMessageData();
            result.setData(response);
            packet.setDate(new Date());
            packet.setMessageData(result);
            packet.setMessageStatus(MessageStatus.OK);
            channel.writeAndFlush(packet);
        } catch (Exception e) {
            packet.setMessageData(new ErrorResponseData());
            packet.setDate(new Date());
            packet.setMessageStatus(MessageStatus.ERROR);
            channel.writeAndFlush(packet);
        }
    }

    private void processCheckClient(Packet packet) {
        try {
            RequestCheckClientMessageData messageData = (RequestCheckClientMessageData) packet.getMessageData();
            String response = deviceMessageHandlerFactory.processCheckClient(messageData.getClientCardId());
            ResponseCheckClientMessageData result = new ResponseCheckClientMessageData();
            result.setData(response);
            packet.setDate(new Date());
            packet.setMessageStatus(MessageStatus.OK);
            packet.setMessageData(result);
            channel.writeAndFlush(packet);
        } catch (Exception e) {
            packet.setMessageData(new ErrorResponseData());
            packet.setDate(new Date());
            packet.setMessageStatus(MessageStatus.ERROR);
            channel.writeAndFlush(packet);
        }
    }

    public Channel getChannel() {
        return channel;
    }
}
