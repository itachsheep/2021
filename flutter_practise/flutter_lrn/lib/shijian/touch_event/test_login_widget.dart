
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/shijian/touch_event/test_event_bus.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class LoginWidget extends TemplateRoute {
  LoginWidget(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return Center(
      child: ElevatedButton(
        onPressed: (){
          LogUtils.dd("login clicked");
          bus.emit("login","你已经登录了");
          Navigator.pop(context);
        },
        child: Text("点击登录"),
      ),
    );
  }
}