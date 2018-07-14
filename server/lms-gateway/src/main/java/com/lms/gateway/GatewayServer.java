package com.lms.gateway;

import com.lms.gateway.model.Packet;
import com.lms.gateway.parsers.MessageDecoder;
import com.lms.gateway.parsers.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayServer {

    private Logger logger = LoggerFactory.getLogger(GatewayServer.class);

    private int serverPort;
    private int executorThreadNumber;
    private int groupThreadNumber;
    private int idleStateTimeout;

    private EventLoopGroup group;
    private NioEventLoopGroup bossGroup;
    private ChannelFuture future;

    private boolean shuttingDown;

    private DeviceSessionCache deviceSessionCache;

    public GatewayServer(Integer serverPort, Integer executorThreadNumber, Integer groupThreadNumber, Integer idleStateTimeout, DeviceMessageHandlerFactory deviceMessageHandlerFactory) {
        deviceSessionCache = new DeviceSessionCache(deviceMessageHandlerFactory);
        this.serverPort = serverPort;
        this.executorThreadNumber = executorThreadNumber;
        this.groupThreadNumber = groupThreadNumber;
        this.idleStateTimeout = idleStateTimeout;
    }

    public void start() throws InterruptedException {
        bossGroup = new NioEventLoopGroup(1);
        group = new NioEventLoopGroup(groupThreadNumber);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventExecutorGroup eventExecutor = new DefaultEventExecutorGroup(executorThreadNumber);
        serverBootstrap.group(bossGroup, group);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new IdleStateHandler(idleStateTimeout, 0, 0));
                pipeline.addLast(new MessageDecoder());
                pipeline.addLast(new MessageEncoder());
                pipeline.addLast(eventExecutor, new ServerHandler(deviceSessionCache));
            }
        });
        future = serverBootstrap.bind(serverPort).sync();
    }


    public void shutdown() {
        shuttingDown = true;
        try {
            future.channel().close();
        } finally {
            bossGroup.shutdownGracefully();
            group.shutdownGracefully();
        }
    }

    public boolean isShuttingDown() {
        return shuttingDown;
    }

    private class ServerHandler extends ChannelInboundHandlerAdapter {

        private DeviceSessionCache deviceSessionCache;

        public ServerHandler(DeviceSessionCache deviceSessionCache) {
            this.deviceSessionCache = deviceSessionCache;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (!isShuttingDown()) {
                deviceSessionCache.getSession(ctx.channel()).packetReceived((Packet) msg);
            }
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            super.channelInactive(ctx);
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            super.userEventTriggered(ctx, evt);
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent e = (IdleStateEvent) evt;
                if (e.state() == IdleState.READER_IDLE) {
                    ctx.close();
                }
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
