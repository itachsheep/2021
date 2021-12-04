import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class SocketRoute extends TemplateRoute {
  SocketRoute(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return Center(
      child: FutureBuilder(
          future: _request(),
          builder: (BuildContext context, AsyncSnapshot<String> snapshot) {
            return Text(snapshot.data.toString());
          }
      ),
    );
  }

  Future<String> _request() async {
    //建立连接
    Socket socket = await Socket.connect("baidu.com", 80);
    //根据http协议，发送请求头
    socket.writeln("GET / HTTP/1.1");
    socket.writeln("Host:baidu.com");
    socket.writeln("Connection:close");
    socket.writeln();
    await socket.flush(); //发送请求
    //读取返回内容，按照utf8解码为字符串
    String _response = await utf8.decoder.bind(socket).join();
    await socket.close();
    LogUtils.dd("_request ----> $_response");
    return _response;
  }


}

