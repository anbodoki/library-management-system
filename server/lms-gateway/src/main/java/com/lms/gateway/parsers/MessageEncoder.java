package com.lms.gateway.parsers;

import com.lms.gateway.model.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEncoder extends MessageToByteEncoder {

    private Logger logger = LoggerFactory.getLogger(MessageEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (msg instanceof Packet) {
            String message = MessageEncodeUtils.buildMessage((Packet) msg);
            logger.info("Send : " + message);
            out.writeBytes(message.getBytes(CharsetUtil.UTF_8));
        }
    }
}
