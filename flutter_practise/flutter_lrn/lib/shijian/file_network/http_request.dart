

import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class HttpRoute extends TemplateRoute {
   HttpRoute(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return HttpTestRoute();
  }

}

class HttpTestRoute extends StatefulWidget {
  @override
  _HttpTestRouteState createState() => _HttpTestRouteState();
}

class _HttpTestRouteState extends State<HttpTestRoute> {
  bool _loading = false;
  String _text = "";

  request() async {
    setState(() {
      _loading = true;
      _text = "正在请求...";
    });

    try {
      //创建一个HttpClient
      HttpClient httpClient = HttpClient();
      httpClient.connectionTimeout = Duration(seconds: 10);
      
      ///该方法用于添加用户凭据,如：
      ///如果所有请求都需要认证，
      ///那么应该在HttpClient初始化时就调用addCredentials()来添加全局凭证，而不是去动态添加。
      /*Uri _uri = Uri.parse("http://www");
      httpClient.addCredentials(_uri,
        "admin",
        HttpClientBasicCredentials("username","password"), //Basic认证凭据
      );*/
      
      //打开Http连接
      HttpClientRequest httpRequest = await httpClient.getUrl(Uri.parse("https://www.baidu.com"));


      //使用iPhone的UA
      httpRequest.headers.add(
        "user-agent",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1",
      );
      //等待连接服务器（会将请求信息发送给服务器）
      HttpClientResponse response = await httpRequest.close();
      
      //读取响应内容
      _text = await response.transform(utf8.decoder).join();
      //输出响应头
      print(response.headers);

      //关闭client后，通过该client发起的所有请求都会中止。
      httpClient.close();
    } catch (e) {
      _text = "请求失败：$e";
    } finally {
      setState(() {
        _loading = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
        child: Column(
          children: [
            ElevatedButton(
              child: Text("获取百度首页"),
              onPressed: _loading ? null : request,
            ),
            Container(
              width: MediaQuery.of(context).size.width - 50.0,
              child: Text(_text.replaceAll(RegExp(r"\s"), "")),
            )
          ],
        )
    );
  }

}