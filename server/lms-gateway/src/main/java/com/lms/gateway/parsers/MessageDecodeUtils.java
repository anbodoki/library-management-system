package com.lms.gateway.parsers;

import com.lms.gateway.ProtocolConfig;
import com.lms.gateway.model.*;
import io.netty.buffer.ByteBuf;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MessageDecodeUtils {

    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static Packet parse(String msg) throws ParseException {
        if (msg.isEmpty()) {
            return null;
        }
        if (!MessageDecodeUtils.syncByteIsPresent(msg)) {
            return null;
        }
//        if (!MessageDecodeUtils.checkCRC(msg)) {
//            return null;
//        }
        return getPacket(msg.substring(1, msg.lastIndexOf(ProtocolConfig.MSG_DELIMITER)));
    }

    public static String getStringFromBytes(ByteBuf msg) {
        if (msg == null) {
            return null;
        }
        msg.resetReaderIndex();
        byte[] arr = new byte[msg.readableBytes()];
        msg.getBytes(0, arr);
        StringBuilder result = new StringBuilder();
        for (byte b : arr) {
            result.append((char) b);
        }
        return result.toString().trim();
    }

    private static boolean syncByteIsPresent(String message) {
        return message.charAt(0) == ProtocolConfig.SYNC_BYTE1;
    }

    private static boolean checkCRC(String data) {
        String withoutCRC = data.substring(0, data.lastIndexOf(ProtocolConfig.MSG_DELIMITER) + 1);
        String[] split = data.split(ProtocolConfig.MSG_DELIMITER);
        String crc = split[split.length - 1];
        String result = CRC16.calculateCRC(withoutCRC);
        return result.equals(crc);
    }

    private static Packet getPacket(String data) throws ParseException {
        String[] split = data.split(ProtocolConfig.MSG_DELIMITER);
        if (split.length < 3) {
            return null;
        }
        Packet packet = new Packet();
        packet.setMessageType(MessageType.valueOf(split[0].charAt(0)));
        packet.setMessageStatus(MessageStatus.valueOf(split[1].charAt(0)));
        packet.setDate(formatter.parse(split[2]));
        switch (packet.getMessageType()) {
            case SUBMIT:
                packet.setMessageData(buildSubmitMessageData(split[3]));
                break;
            case CHECK_BOOK:
                packet.setMessageData(buildCheckBookMessageData(split[3]));
                break;
            case CHECK_CLIENT:
                packet.setMessageData(buildCheckClientMessageData(split[3]));
                break;
        }
        return packet;
    }

    private static RequestSubmitMessageData buildSubmitMessageData(String data) throws ParseException {
        RequestSubmitMessageData result = new RequestSubmitMessageData();
        String[] split = data.split(ProtocolConfig.MSG_DATA_DELIMITER);
        result.setBookId(split[0]);
        result.setClientId(split[1]);
        if (split.length > 2 && !split[2].equals("null")) {
            result.setDate(formatter.parse(split[2]));
        }
        return result;
    }

    private static RequestCheckBookMessageData buildCheckBookMessageData(String data) {
        RequestCheckBookMessageData result = new RequestCheckBookMessageData();
        result.setBookIdentifier(data);
        return result;
    }

    private static RequestCheckClientMessageData buildCheckClientMessageData(String data) {
        RequestCheckClientMessageData result = new RequestCheckClientMessageData();
        result.setClientCardId(data);
        return result;
    }
}
