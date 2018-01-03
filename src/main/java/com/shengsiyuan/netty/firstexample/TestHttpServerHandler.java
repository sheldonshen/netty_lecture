package com.shengsiyuan.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import java.net.URI;

// 自定义处理器(channelHandler)
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
      System.out.println(msg.getClass());
      System.out.println(ctx.channel().remoteAddress());
      Thread.sleep(8000);

      if(msg instanceof HttpRequest){
        HttpRequest httpRequest = (HttpRequest)msg;

        System.out.println("请求方法名:" + httpRequest.method().name());
        URI uri = new URI(httpRequest.uri());
        if("/favicon.ico".equals(uri.getPath())){
            System.out.println("请求favicon.ico");
            return;
        }

        ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);// 向客户端返回的内容
        FullHttpResponse  response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
            HttpResponseStatus.OK,content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

        ctx.writeAndFlush(response);// 返回响应
        ctx.channel().close();
      }
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel active");
    super.channelActive(ctx);
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel registered");
    super.channelRegistered(ctx);
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    System.out.println("handler added");
    super.handlerAdded(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel inactive");
    super.channelInactive(ctx);
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel unregistered");
    super.channelUnregistered(ctx);
  }

}
