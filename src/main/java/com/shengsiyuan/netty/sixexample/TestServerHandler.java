package com.shengsiyuan.netty.sixexample;

import com.shengsiyuan.netty.sixexample.MyDataInfo.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person>{

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Person msg) throws Exception {

  }

}
