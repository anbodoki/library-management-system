package com.lms.gateway.parsers;

import com.lms.gateway.model.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MessageDecoder extends StringDecoder {

    private Logger logger = LoggerFactory.getLogger(MessageDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        String stringData = MessageDecodeUtils.getStringFromBytes(msg);
        logger.info("Receive : " + stringData);
        Packet packet = MessageDecodeUtils.parse(stringData);
        if (packet == null) {
            msg.resetReaderIndex();
            return;
        }
        out.add(packet);
    }
}