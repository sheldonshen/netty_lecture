package com.shengsiyuan.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

// 自定义初始化
public class TestServerInitializer extends ChannelInitializer<SocketChannel>{

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();

    pipeline.addLast("httpServerCodec",new HttpServerCodec());
    pipeline.addLast("testHttpServerHandler",new TestHttpServerHandler());
  }

}
