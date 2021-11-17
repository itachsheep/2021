

import 'package:flutter/material.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestNotification extends TemplateRoute {
  TestNotification(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return NotificationListener(
      child: ListView.builder(
          itemCount: 100,
          itemBuilder: (context, index) {
            return ListTile(title: Text("$index"),);
          }
      ),
      onNotification: (notification) {
        switch (notification.runtimeType){
          case ScrollStartNotification:
            print("开始滚动");
            break;
          case ScrollUpdateNotification:
            print("正在滚动");
            break;
          case ScrollEndNotification:
            print("滚动停止");
            break;
          case OverscrollNotification:
            print("滚动到边界");
            break;
        }
        return false;
      },
    );
  }

}