
import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/template/template_widget.dart';
import 'package:web_socket_channel/io.dart';

class WebSocketTest extends TemplateRoute {
  WebSocketTest(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return WebSocketRoute();
  }


}

class WebSocketRoute extends StatefulWidget {
  @override
  _WebSocketRouteState createState() => _WebSocketRouteState();
}

class _WebSocketRouteState extends State<WebSocketRoute> {
  TextEditingController _editingController = TextEditingController();
  late IOWebSocketChannel _channel;
  String _text = "";

  @override
  void initState() {
    super.initState();
    _channel = IOWebSocketChannel.connect('ws://echo.websocket.org');
  }

  @override
  void dispose() {
    super.dispose();
    _channel.sink.close();
  }

  void _sendMessage() {
    LogUtils.dd("_sendMessage -->");
    if(_editingController.text.isNotEmpty) {
      _channel.sink.add(_editingController.text);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Form(
            child: TextFormField(
              controller: _editingController,
              decoration: InputDecoration(labelText: 'Send a message'),
            ),
          ),

          StreamBuilder(
            stream: _channel.stream,
            builder: (context, snapshot) {
              //网络不通会走到这
              if (snapshot.hasError) {
                _text = "网络错误..";
              } else if( snapshot.hasData) {
                _text = "echo: "+ snapshot.data.toString();
              }
              LogUtils.dd("onResponse --> ${_text}");
              return Padding(
                padding: EdgeInsets.symmetric(vertical: 24.0),
                child: Text(_text),
              );
            }
          ),

          Padding(padding: EdgeInsets.only(top: 10.0),
          child: ElevatedButton(
            onPressed: _sendMessage,
            child: Text("发送消息"),
          ) ,
          )
        ],
      ),
    );
  }


}