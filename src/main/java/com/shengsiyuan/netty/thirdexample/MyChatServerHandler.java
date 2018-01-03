package com.shengsiyuan.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

  private  static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
     Channel channel = ctx.channel();

     channelGroup.forEach(ch -> {
         if(channel != ch){
           ch.writeAndFlush(channel.remoteAddress() + " 发送的消息:" + msg + "\n");
         }else{
           ch.writeAndFlush("[自己] " + msg + "\n");
         }
     });
  }

  @Override //连接建立
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();

    channelGroup.writeAndFlush("[服务器]-" + channel.remoteAddress() + "加入\n");

    channelGroup.add(channel);
  }

  @Override//连接断开
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();

    channelGroup.writeAndFlush("[服务器]-" + channel.remoteAddress() + "离开\n");

    //netty会自动调用这句代码,无需手动调用
    //channelGroup.remove(channel);
    System.out.println(channelGroup.size());
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    System.out.println(channel.remoteAddress() + "上线");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    System.out.println(channel.remoteAddress() + "下线");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }

}
