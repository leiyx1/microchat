package com.leiyx.microchat.messaging.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class MessagingServer {

    private final EventLoopGroup group = new NioEventLoopGroup();

    private Channel channel;

    @Value("${server.port}")
    private int port;

    private final TextWebSocketFrameHandler textWebSocketFrameHandler;

    @Autowired
    public MessagingServer(TextWebSocketFrameHandler textWebSocketFrameHandler) {
        this.textWebSocketFrameHandler = textWebSocketFrameHandler;
    }

    @PostConstruct
    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new HTTPRequestHandler());
                        pipeline.addLast(new WebSocketServerProtocolHandler("/ws", true));
                        pipeline.addLast(textWebSocketFrameHandler);
                    }
                });
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(port));
        future.syncUninterruptibly();
        System.out.println("Started netty server on port " + port);
        channel = future.channel();
        future.channel().closeFuture().syncUninterruptibly();
    }

    @PreDestroy
    public void stop() {
        if (channel != null) {
            channel.close();
        }
        group.shutdownGracefully();
    }
}
