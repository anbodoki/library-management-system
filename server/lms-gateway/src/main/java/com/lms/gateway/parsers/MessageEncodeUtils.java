package com.lms.gateway.parsers;

import com.lms.gateway.ProtocolConfig;
import com.lms.gateway.model.Packet;

import java.text.SimpleDateFormat;

public class MessageEncodeUtils {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String buildMessage(Packet packet) {
        String result = "*";
        switch (packet.getMessageType()) {
            case CHECK_BOOK:
                result += "b";
                break;
            case CHECK_CLIENT:
                result += "c";
                break;
            case SUBMIT:
                result += "s";
                break;
        }
        result += ProtocolConfig.MSG_DELIMITER;
        switch (packet.getMessageStatus()) {
            case OK:
                result += "o";
                break;
            case ERROR:
                result += "e";
                break;
            case IN_PROGRESS:
                result += "i";
                break;
        }
        result += ProtocolConfig.MSG_DELIMITER;
        result += formatter.format(packet.getDate());
        result += ProtocolConfig.MSG_DELIMITER;
        if (packet.getMessageData() != null) {
            result += packet.getMessageData().customToString();
            result += ProtocolConfig.MSG_DELIMITER;
        }
        result += CRC16.calculateCRC(result.substring(1, result.length()));
        return result;
    }
}
