package com.shengsiyuan.netty.sixexample;

import com.shengsiyuan.netty.sixexample.MyDataInfo.AbstractType;
import com.shengsiyuan.netty.sixexample.MyDataInfo.AbstractType.Type;
import com.shengsiyuan.netty.sixexample.MyDataInfo.People;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.AbstractType> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, AbstractType msg) throws Exception {

  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    MyDataInfo.People people = MyDataInfo.People.newBuilder().
        setName("张三").setAge(20).setAddress("北京").build();
    MyDataInfo.AbstractType ar = MyDataInfo.AbstractType
        .newBuilder()
        .setType(Type.People)
        .setExtension(People.request,people)
        .build();
    ctx.channel().writeAndFlush(ar);
  }

}
