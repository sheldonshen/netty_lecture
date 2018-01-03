package com.shengsiyuan.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
// 服务器
public class TestServer {

  public static void main(String[] args) throws InterruptedException {
    // 处理连接(线程组)
    EventLoopGroup bossGroup = new NioEventLoopGroup();;
    // 处理业务逻辑(线程组)
    EventLoopGroup workerGroup = new NioEventLoopGroup();;
    try{
      // 服务器
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
          childHandler(new TestServerInitializer());

      ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
      channelFuture.channel().closeFuture().sync();
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
