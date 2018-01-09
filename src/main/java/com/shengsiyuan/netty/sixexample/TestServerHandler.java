package com.shengsiyuan.netty.sixexample;

import com.google.protobuf.ByteString;
import com.shengsiyuan.netty.sixexample.MyDataInfo.AbstractType;
import com.shengsiyuan.netty.sixexample.MyDataInfo.People;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.AbstractType>{

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, AbstractType msg) throws Exception {
       if(msg.getType().getNumber() == AbstractType.Type.People_VALUE){
           System.out.println("People类型");
           ByteString byteString = msg.getUnknownFields().getField(People.REQUEST_FIELD_NUMBER)
               .getLengthDelimitedList().get(0);
           People people = People.parseFrom(byteString);
           System.out.println(people.getName());
       }else if(msg.getType().getNumber() == AbstractType.Type.Person_VALUE){
           System.out.println("Person类型");
           System.out.println(msg.getClass().getName());
           System.out.println("---------------");
       }else{
          throw new RuntimeException("无法识别的消息类型");
       }
  }
}
